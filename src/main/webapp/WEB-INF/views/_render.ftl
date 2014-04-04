<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__pjaxr.ftl" as pjaxr>

<#if pjaxr.site>
  <#include "_renderHtml.ftl">
<#else>
  <#include "_renderPjaxr.ftl">
</#if>