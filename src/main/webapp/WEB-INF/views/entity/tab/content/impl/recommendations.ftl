<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

Recommendations Tab

<#if tabContent.recommendedBands??>
    <h2>recommended bands</h2>
    <ul>
    <#list tabContent.recommendedBands as band>
        <li><a href="<@mtl.url band[0]/>/recommendations">${band[1]}</a></li>
    </#list>
    </ul>     
</#if>
<#if tabContent.myRecords??>
    <h2>records and tracks</h2>
    
    <#list tabContent.myRecords?keys as record>
        <h3>${record}</h3>
        <ul>
        <#list tabContent.myRecords[record] as track>
            <li><a href="https://www.youtube.com/watch?v=${track[1]}">${track[0]}</a>
            </li>
        </#list>
        <iframe width="560" height="315" src="//www.youtube.com/embed/${tabContent.myRecords[record][0][1]}" frameborder="0" allowfullscreen></iframe>
        </ul>
    </#list>
</#if>
    