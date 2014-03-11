<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>
<@mtl.html>
  <@mtl.head title="Home - Metalcon Middleware"/>
  <@mtl.body>
    <#escape x as x?html>
      <ul>
        <#list bands as band>
          <li>${band}</li>
        </#list>
      </ul>
    </#escape>
  </@mtl.body>
</@mtl.html>