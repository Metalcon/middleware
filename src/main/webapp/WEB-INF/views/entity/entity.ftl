<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/metalcon.ftl" as mtl>

<#--
 # Will include the correct TabPreview template.
 # @param tabPreviewName (String)
 #        Name of the included TabPreview, for example "ABOUT_TAB".
 # @example
 #   <@includeTabPreview "ABOUT_TAB"/>
 #-->
<#assign tabPreviews = view.entityTabPreviews>
<#macro includeTabPreview tabPreviewName>
  <#assign tab = tabPreviews[tabPreviewName]>
  <#if tab??>
    <#include "tab/preview/tab_preview.ftl">
  <#else>
    <!-- Error: Failed to load tab -->
    <#-- TODO: raise some kind of error -->
  </#if>
</#macro>

<#--
 # Will include the current TabContent.
 # @example
 #  <#includeTabContent/>
 #-->
<#assign tabContent = view.entityTabContent>
<#macro inner_content>
  <#assign tab = tabContent>
  <#include "tab/content/tab_content.ftl">
</#macro>

<#--
 # Will include the corrrect entityType subtemplate.
 # For example, if view.entityType is "BAND" this will include "impl/band.ftl"
 # These are expected to set the foloowing variables:
 # entity_tabPreviews - HTML string containing the rendered tabPreviews. 
 #-->
<#include "impl/" + view.entityType?lower_case + ".ftl">

<#assign stylesheets = stylesheets + ["entity.css"]>
<#assign view_title = entity_title>
<#assign content>
  <ol class="breadcrumb">
    <li><a href="#">Home</a></li>
    <li><a href="#">Metallica</a></li>
    <li class="active">Newsfeed</li>
  </ol>
  <div class="row">
    <div class="col-xs-8">
      <h1>Metallica</h1>
      <@inner_content/>
    </div>
    <div id="tabs" class="col-xs-4">
      <ul>
        ${entity_tabPreviews}
      </ul>
    </div>
  </div>
</#assign>