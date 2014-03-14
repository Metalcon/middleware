<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

<#--
 # This is the default metalcon page template. Every "page" on metalcon uses
 # this view. Inside it, it includes more specific views.
 #-->

<#--
 # Lists of all css files to be included in the output. Inside a view a view
 # specific stylesheet can be added like this:
 # <#assign stylesheets = stylesheet + ["mystyle.css"]>
 #-->

<@mtl.pjaxrHead title="${view_title}" metaTags=metaTags>
</@mtl.pjaxrHead>
<@mtl.pjaxrBody>
  <@site_block>
    <@page_block>
      <@content_block>
        <@inner_content_block></@inner_content_block>
      </@content_block>
    </@page_block>
  </@site_block>
  <#list additional_pjaxr_blocks as additional_pjaxr_block>
    ${additional_pjaxr_block}
  </#list>
</@mtl.pjaxrBody>
<@mtl.pjaxrNamespace>${view.pjaxrNamespace}</@mtl.pjaxrNamespace>
