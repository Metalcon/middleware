<#ftl encoding="UTF-8" strict_syntax=true strip_whitespace=true>
<#import "/__metalcon.ftl" as mtl>
<#import "/spring.ftl" as spring>
<@mtl.html>
  <@mtl.head title="News ${userId} - Metalcon Middleware">
    <@mtl.stylesheet href="test/news.css"/>
  </@mtl.head>
  <@mtl.body>
    <#escape x as x?html>
      <h1>Metalcon MusicServerexample</h1>
      just hit the button and a post request will be send (right now it does not even contain a file but the middleware will load a local file from the file system)
      <h2>Post</h2>
      <form id="post" action="<@spring.url "/test/music/post"/>" method="POST" enctype="multipart/form-data">
        <label for="file">File upload:</label>
        <input type="file" name="file"/>

        <label for="formSubmit">Submit:</label>
        <input id="formSubmit" name="formSubmit" type="submit" value="Post"/>
      </form>
      
    </#escape>
  </@mtl.body>
</@mtl.html>