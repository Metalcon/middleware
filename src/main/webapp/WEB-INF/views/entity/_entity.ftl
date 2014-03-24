<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>


<#--
 # Will include the current TabContent to "inner_content" if available.
 # @example
 #   <#includeTabContent/>
 #-->
<#assign inner_content = "">
<#if view?? && view.entityTabContent??>
  <#assign tabContent = view.entityTabContent>
  <#assign tab = tabContent>
  <#include "tab/content/_tab_content.ftl">
</#if>

<#--
 # Will assign the current entitiy's tabPreviews' names to tabPreviews, if available.
 # @example
 #   <#includeTabPreview tabPreviewName/>
 #-->
<#assign tabPreviews=[]>
<#if view?? && view.entityTabPreviews??>
  <#assign tabPreviews = view.entityTabPreviews>
</#if>


<#--
 # Macro to include tabPreview given as tabPreviewName out tabPreviews.
 # @example
 #   <#includeTabPreview tabPreviewName/>
 #-->
<#macro includeTabPreview tabPreviews tabPreviewName>
  <#if tabPreviews??>
    <#assign tab = tabPreviews[tabPreviewName]>
    <#if tab??>
      <#include "tab/preview/_tab_preview.ftl">
    <#else>
      <!-- Error: Failed to load tab -->
      <#-- TODO: raise some kind of error -->
    </#if>
  </#if>
</#macro>

<#--
 # Will include the corrrect entityType subtemplate.
 # For example, if view.entityType is "BAND" this will include "impl/band.ftl"
 # These are expected to set the foloowing variables:
 # entity_tabPreviews - HTML string containing the rendered tabPreviews. 
 #-->
<#include "impl/__" + view.entityType?lower_case + ".ftl">

<#assign stylesheets = stylesheets + ["entity.css"]>
<#assign view_title = entity_title>

<#-- 
 # if pjaxrNamespace matching the current entity already, tabs don't have to 
 # be updated
 #-->
<#if pjaxrContent>
  <#macro content_block>
    <@mtl.content>
      <ol class="breadcrumb">
        <li><a href="#">Home</a></li>
        <li><a href="#">Metallica</a></li>
        <li class="active">News</li>
      </ol>
      <div class="row">
        <div class="col-xs-8">
          <h1>${entity_title}</h1>
          <#nested>
        </div>
        <div id="tabs" class="col-xs-4">
          <ul>
            <#list entity_tabPreviews as entity_tabPreview>
              <@includeTabPreview tabPreviews entity_tabPreview/>
            </#list>
          </ul>
        </div>
      </div>
    </@mtl.content>
  </#macro>
<#else>
  <#macro content_block>
    <@mtl.content>
      <#nested>
    </@mtl.content>
  </#macro>
</#if>

<#macro inner_content_block>
  <@mtl.innerContent>
    ${inner_content}
  </@mtl.innerContent>
</#macro>
