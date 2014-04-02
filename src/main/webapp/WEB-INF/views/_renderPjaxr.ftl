<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__pjaxr.ftl" as pjaxr>

<#--
 # Renders a block only if it was requested for pjaxr, else only its contents.
 #
 # @param block
 #        Name of the block to be rendered. This needs to be the same as the
 #        name of the macro that renders the block, possible ones at the moment
 #        are: "site", "page", "content", "innerContent".
 #
 # @example
 #   <@render "site">
 #     Contents
 #   </@render>
 #-->
<#macro render block>
  <#-- if block= "site", checks pjaxr.site -->
  <#if pjaxr[block]>
    <#-- if block= "site", calls @site -->
    <#assign macro = .vars[block]>
    <@macro>
      <#nested>
    </@macro>
  <#else>
    <#nested>
  </#if>
</#macro>

<pjaxr-head>
  <title>${viewTitle?html}</title>
  <@mtl.printStylesheets/>
  <@mtl.printLessStylesheets/>
  <@mtl.printMetaTags/>
</pjaxr-head>
<pjaxr-body>
  <@render "site">
    <@render "page">
      <@render "content">
        <@render "innerContent"/>
      </@render>
    </@render>
  </@render>
  <#list pjaxr.additionalBlocks as additionalBlock>
    ${additionalBlock}
  </#list>
</pjaxr-body>
<pjaxr-namespace>${pjaxr.namespace}</pjaxr-namespace>
