<#ftl encoding="UTF-8" strict_syntax=true>

<#global pjaxrSite = (pjaxrMatching &lt;= 0)>
<#global pjaxrPage = (pjaxrMatching &lt;= 1)>
<#global pjaxrContent = (pjaxrMatching &lt;= 2)>
<#global pjaxrInnerContent = (pjaxrMatching &lt;= 3)>

<#--
 # Pjaxr utilities
 # -->
<#macro pjaxrHead title metaTags=[]>
<pjaxr-head>
  <title>${title?html}</title>
  <#if metaTags??>
    <#list metaTags as metaTag>
        <meta<#list metaTag?keys as key> ${key}="${metaTag[key]}"</#list> />
    </#list>
  </#if>
  <#nested>
</pjaxr-head>
</#macro>
<#global pjaxrHead=pjaxrHead>

<#macro pjaxrBody>
  <pjaxr-body>
    <#nested>
  </pjaxr-body>
</#macro>
<#global pjaxrBody=pjaxrBody>

<#macro pjaxrNamespace>
  <pjaxr-namespace><#nested></pjaxr-namespace>
</#macro>
<#global pjaxrNamespace=pjaxrNamespace>

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
