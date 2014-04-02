<#ftl encoding="UTF-8" strict_syntax=true strip_whitespace=true>
<#import "/__html.ftl" as html>
<#import "/spring.ftl" as spring>
<@html.html>
  <@html.head title="News ${userId} - Metalcon Middleware">
    <@html.stylesheet href="test/news.css"/>
  </@html.head>
  <@html.body>
    <#escape x as x?html>
      <h1>Metalcon Newsstream</h1>
      <ul class="info">
        <li>User: <em>${userId}</em></li>
         <li>Poster: <em>${posterId}</em></li>
         <li><em>${ownUpdates?string("Showing", "Not showing")}</em> own updates</li>
      </ul>
      <h2>Post</h2>
      <form id="post" action="<@spring.url "/test/news/${userId}/${posterId}/${ownUpdates?c}/post"/>" method="POST">
        <label for="formMessage">Message:</label>
        <textarea id="formMessage" name="formMessage" rows="5"></textarea>
        <label for="formSubmit">Submit:</label>
        <input id="formSubmit" name="formSubmit" type="submit" value="Post"/>
      </form>
      <h2>News</h2>
      <ul id="news">
        <#list news as item>
          <li class="item">
            <p class="head">
              <span class="verb">${item.verb}</span>
              <span class="actor">${item.actor.displayName}</span>
              <span class="actor_info">(${item.actor.id} - ${item.actor.objectType})</span>
              <span class="published">${item.published}</span>
            </p>
            <p class="body">
              <span class="body_info">(${item.object.id} - ${item.object.objectType},${item.object.type})</span>
              ${item.object.message}
            </p>
          </li>
        </#list>
      </ul>
    </#escape>
  </@html.body>
</@html.html>