<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/spring.ftl" as spring>
<#import "/__metalcon.ftl" as mtl>
<#import "/__html.ftl" as html>
<@html.html>
  <@html.head title="Home - Metalcon"/>
  <@html.body>
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
  </@html.body>
</@html.html>