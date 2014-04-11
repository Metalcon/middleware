<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

Records Tab<br/>

<#if !tabPreview.records??>
  <p>No reviews</p>
<#else>
  <#list tabPreview.records as record>
    <span>${record.name}</span>
  </#list>
</#if>
