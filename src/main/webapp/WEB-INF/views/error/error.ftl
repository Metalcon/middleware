<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__html.ftl" as html>
<@html.html>
  <#--
   # Be careful when editing this template. FreeMarker errors that occur while
   # parsing this template will not be displayed in the browser, since this
   # template is responsible for displaying them.
   #-->
  <@html.head title="${statusCode} - ${statusMessage}">
    <@html.stylesheet href="error.css"/>
  </@html.head>
  <@html.body>
    <#escape x as x?html>
      <h1>${statusCode} - ${statusMessage}</h1>
    </#escape>
    <#if exception??>
      <p>
        An error occured when trying to acces the request URI:
        <span class="mono">${requestUri}</span>
      </p>
      <p class="mono">
        ${exception?html
          ?replace("\n", "<br/>\n")
          ?replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;")}
      </p>
    </#if>
  </@html.body>
</@html.html>
