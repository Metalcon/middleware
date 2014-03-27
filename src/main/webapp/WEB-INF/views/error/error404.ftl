<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>
<@mtl.html>
  <@mtl.head title="404 - Not found">
    <@mtl.stylesheet href="error.css"/>
  </@mtl.head>
  <@mtl.body>
    <#escape x as x?html>
      <h1>404 - Not Found</h1>
      <p>
        Unable to find the requested URI:
        <span class="mono">${requestUri}</span>.
      </p>
    </#escape>
  </@mtl.body>
</@mtl.html>
