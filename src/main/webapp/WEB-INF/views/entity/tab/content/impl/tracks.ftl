<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

<h3>Tracks</h3>
<#if !tabContent.tracks??>
  <p>No tracks</p>
<#else>
  <ul>
    <#list tabContent.tracks as track>
      <li><a href="<@mtl.url "/music/" + track.url/>">${track.trackNumber} ${track.name}</a></li>
    </#list>
  </ul>
</#if>