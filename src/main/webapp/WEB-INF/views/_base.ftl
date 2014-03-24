<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>
<#import "/__pjaxr.ftl" as pjaxr>

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

<#assign view_pc><a id="view_pc" class="navbar-brand" href="#">pagecounter: ${view.pc}</a></#assign>
<#assign view_id><a id="view_id" class="navbar-brand" href="#">userid: ${view.id}</a></#assign>
<#assign additionalPjaxrBlocks = additionalPjaxrBlocks + [view_pc, view_id]>

<#if view.type == "entity">
  <#include "entity/_entity.ftl">
</#if>

<#if pjaxrSite>
  <#include "/_site.ftl">
<#else>
  <#include "/_pjaxr.ftl">
</#if>
