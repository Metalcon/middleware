<#ftl encoding="UTF-8" strict_syntax=true>

<#global pjaxrSite = (pjaxrMatching &lt;= 0)>
<#global pjaxrPage = (pjaxrMatching &lt;= 1)>
<#global pjaxrContent = (pjaxrMatching &lt;= 2)>
<#global pjaxrInnerContent = (pjaxrMatching &lt;= 3)>

<#macro innerContentBlock>
  <@innerContent>
    <#nested>
  </@innerContent>
</#macro>
<#global innerContentBlock=innerContentBlock>

<#macro contentBlock>
  <@content>
    <#nested>
  </@content>
</#macro>
<#global contentBlock=contentBlock>

<#macro pageBlock>
  <@page>
    <#nested>
  </@page>
</#macro>
<#global pageBlock=pageBlock>

<#macro siteBlock>
  <@site>
    <#nested>
  </@site>
</#macro>
<#global siteBlock=siteBlock>

<#global additionalPjaxrBlocks = []>
