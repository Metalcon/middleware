<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>
<#--
 # Lists of all css files to be included in the output. Inside a view a view
 # specific stylesheet can be added like this:
 # <#assign stylesheets = stylesheet + ["mystyle.css"]>
 #-->
<#assign stylesheets = []>

<#--
 # Lists of all less files to be included in the output. Inside a view a view
 # specific stylesheet can be added like this:
 # <#assign lessStylesheets = lessStylesheets + ["mystyle.less"]>
 #-->
<#assign lessStylesheets = []>

<#assign metaTags = []>

<#macro inner_content_block>
  <#nested>
</#macro>

<#macro content_block>
  <@mtl.content>
    <#nested>
  </@mtl.content>
</#macro>

<#macro page_block>
  <@mtl.page>
    <#nested>
  </@mtl.page>
</#macro>

<#macro site_block>
  <@mtl.site>
    <#nested>
  </@mtl.site>
</#macro>

<#assign additional_pjaxr_blocks = []>

<#assign view_pc><a id="view_pc" class="navbar-brand" href="#">pagecounter: ${view.pc}</a></#assign>
<#assign view_id><a id="view_id" class="navbar-brand" href="#">userid: ${view.id}</a></#assign>
<#assign additional_pjaxr_blocks = additional_pjaxr_blocks + [view_pc, view_id]>

<#if view.type == "entity">
  <#include "entity/_entity.ftl">
</#if>

<#if (view.pjaxrMatching == 0)>
  <#include "/_site.ftl">
<#else>
  <#include "/_pjaxr.ftl">
</#if>
