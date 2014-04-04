<#ftl encoding="UTF-8" strict_syntax=true>

<#if !view??>
  <#stop "view is missing.">
</#if>

<#-- START: Content macros to be redefined -->
<#macro site>
  <#nested>
</#macro>

<#macro page>
  <#nested>
</#macro>

<#macro content>
  <#nested>
</#macro>

<#macro innerContent>
  <#nested>
</#macro>
<#-- END: Content macros to be redefined -->

<#include "_site.ftl">

<#include "_render.ftl">
