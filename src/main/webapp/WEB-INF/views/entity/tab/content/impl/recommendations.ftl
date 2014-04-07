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
    <#list tabContent.records as record>
        <h3><a href="">${record[1]}</a></h3>
        <ul>
        </ul>
    </#list>
</#if>
    