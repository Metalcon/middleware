<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__pjaxr.ftl" as pjaxr>

<#macro siteRender>
  <#if pjaxr.site>
    <@site>
      <#nested>
    </@site>
  <#else>
    <#nested>
  </#if>
</#macro>

<#macro pageRender>
  <#if pjaxr.page>
    <@page>
      <#nested>
    </@page>
  <#else>
    <#nested>
  </#if>
</#macro>

<#macro contentRender>
  <#if pjaxr.content>
    <@content>
      <#nested>
    </@content>
  <#else>
    <#nested>
  </#if>
</#macro>

<#macro innerContentRender>
  <#if pjaxr.innerContent>
    <@innerContent>
      <#nested>
    </@innerContent>
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
  <@siteRender>
    <@pageRender>
      <@contentRender>
        <@innerContentRender/>
      </@contentRender>
    </@pageRender>
  </@siteRender>
  <#list pjaxr.additionalBlocks as additionalBlock>
    ${additionalBlock}
  </#list>
</pjaxr-body>
<pjaxr-namespace>${pjaxr.namespace}</pjaxr-namespace>
