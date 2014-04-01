<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__pjaxr.ftl" as pjaxr>

<#macro siteRender>
  <#if pjxr.site>
    <@site>
      <#nested>
    </@site>
  <#else>
    <#nested>
  </#if>
</#macro>

<#macro pageRender>
  <#if pjxr.page>
    <@page>
      <#nested>
    </@page>
  <#else>
    <#nested>
  </#if>
</#macro>

<#macro contentRender>
  <#if pjxr.content>
    <@content>
      <#nested>
    </@content>
  <#else>
    <#nested>
  </#if>
</#macro>

<#macro innerContentRender>
  <#if pjxr.innerContent>
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
