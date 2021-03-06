<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

<h3>Tracks</h3>
<#if !tabContent.tracks??>
  <p>No tracks</p>
<#else>
  <ul>
    <#list tabContent.tracks as track>
      <li><a href="<@mtl.url "/music/" + track.url/>">${track.trackNumber} ${track.name}</a>
        <@printLikeButton uid=track.muidSerialized likeData=track.likeData/>
      </li>
    </#list>
  </ul>
</#if>