<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

<#if !tabContent.tracks??>
  <p>No tracks</p>
<#else>
  <ul>
    <#list tabContent.tracks as track>
      <li>${track.name}</li>
    </#list>
  </ul>
</#if>