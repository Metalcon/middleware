<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

Tracks Tab<br/>

<#if !tabPreview.tracks??>
  <p>No tracks</p>
<#else>
  <#list tabPreview.tracks as track>
    <span>${track.name}</span>
  </#list>
</#if>