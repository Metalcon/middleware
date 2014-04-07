<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

Recommendations Tab

<#if tabContent.recommendedBands??>
    <ul>
    <#list tabContent.recommendedBands as band>
        <li><a href="<@mtl.url band[0]/>/recommendations">${band[1]}</a></li>
    </#list>
    </ul> 
</#if>