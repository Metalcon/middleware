<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__html.ftl" as html>
<@html.html>
  <@html.head title="404 - Not found">
    <@html.stylesheet href="error.css"/>
  </@html.head>
  <@html.body>
    <#escape x as x?html>
      <h1>404 - Not Found</h1>
      <p>
        Unable to find the requested URI:
        <span class="mono">${requestUri}</span>.
      </p>
    </#escape>
  </@html.body>
</@html.html>
