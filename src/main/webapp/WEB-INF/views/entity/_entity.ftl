<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>
<#import "/__pjaxr.ftl" as pjaxr>
<#include "/__like.ftl">

<#include "impl/__" + view.entityType?lower_case + ".ftl">

<#assign viewTitle = entity_title>

<#-- CONTENT -->
<#if !pjaxr.content>
  <#macro content>
    <#nested>
  </#macro>
<#else>
  <#if !view.entityTabPreviews??>
    <#stop "view.entityTabPreviews is missing but needed to render content.">
  </#if>
  
  <#assign tabPreviews = view.entityTabPreviews>
  
  <#macro includeTabPreview tabPreviewName>
    <#assign tabPreview = tabPreviews[tabPreviewName]>
    <#if tabPreview??>
      <#include "tab/preview/_tab_preview.ftl">
    <#else>
      <#stop "tabPreview with name \"" + tabPreviewName + "\" doesn't exist.">
    </#if>
  </#macro>
  
  <@mtl.addStylesheet "entity.css"/>

  <#macro content>
    <div id="content" class="col-xs-12">
      <ol class="breadcrumb">
        <li><a href="#">Home</a></li>
        <li><a href="#">Metallica</a></li>
        <li class="active">News</li>
      </ol>
      <div class="row">
        <div class="col-xs-8">
          <h1>${entity_title}</h1>
<<<<<<< HEAD
          <@printLikeButton uid=view.muidSerialized currentVote="up" upNum=view.numLikeUp downNum=view.numLikeDown/>
=======
          <@printLikeButton uid=view.muidSerialized message="" upNum=view.numLikeUp downNum=view.numLikeDown/>
>>>>>>> f15162d943a625dfcf535f70359fd6b0b0d572e2
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
</#if>

<#-- INNER_CONTENT -->
<#if !pjaxr.innerContent>
  <#macro innerContent>
    <#nested>
  </#macro>
<#else>
  <#if !view.entityTabContent??>
    <#stop "view.entityTabContent is missing but needed to render innerContent.">
  </#if>
  
  <#assign tabContent = view.entityTabContent>
  
  <#macro innerContent>
    <div id="inner_content">
      <#include "tab/content/_tab_content.ftl">
      <#nested>
    </div>
  </#macro>
</#if>
