<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

<#if !view.entityTabContent??>
  <#stop "view.entityTabContent is missing.">
</#if>
<#if !view.entityTabPreviews??>
  <#stop "view.entityTabPreviews is missing.">
</#if>

<#--
 # Will include the current TabContent to "innerContent".
 #-->
<#assign tabContent = view.entityTabContent>
<#include "tab/content/_tab_content.ftl">

<#--
 # Macro to include tabPreview given as tabPreviewName out tabPreviews.
 #-->
<#assign tabPreviews = view.entityTabPreviews>
<#macro includeTabPreview tabPreviewName>
  <#assign tabPreview = tabPreviews[tabPreviewName]>
  <#if tabPreview??>
    <#include "tab/preview/_tab_preview.ftl">
  <#else>
    <#stop "tabPreview with name \"" + tabPreviewName + "\" doesn't exist.">
  </#if>
</#macro>

<#--
 # Will include the corrrect entityType subtemplate.
 # For example, if view.entityType is "BAND" this will include "impl/band.ftl"
 # These are expected to set the foloowing variables:
 # entity_tabPreviews - HTML string containing the rendered tabPreviews. 
 #-->
<#include "impl/__" + view.entityType?lower_case + ".ftl">

<@mtl.addStylesheet "entity.css"/>
<#assign viewTitle = entity_title>

<#macro content>
  <div id="content" class="col-xs-12">
    <ol class="breadcrumb">
      <li><a href="#">Home</a></li>
      <li><a href="#">Metallica</a></li>
      <li class="active">News</li>
    </ol>
    <div class="row">
      <div class="col-xs-8">
        <h1>${viewTitle}</h1>
        <#nested>
      </div>
      <div id="tabs" class="col-xs-4">
        <ul>
          <#list entity_tabPreviews as entity_tabPreview>
            <@includeTabPreview entity_tabPreview/>
          </#list>
        </ul>
      </div>
    </div>
  </div>
</#macro>
