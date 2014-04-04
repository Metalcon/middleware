<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__html.ftl" as html>
<@html.html>
  <@html.head title="Home - Metalcon Middleware"/>
  <@html.body>
    <#escape x as x?html>
      <ul>
        <#list bands as band>
          <li>${band}</li>
        </#list>
      </ul>
    </#escape>
  </@html.body>
</@html.html>