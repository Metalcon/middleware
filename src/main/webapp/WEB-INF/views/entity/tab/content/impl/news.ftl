<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

News Tab
<#escape x as x?html>
  <h1>Metalcon Newsstream</h1>
  <ul class="info">
    <li>User: <em>${view.entityTabContent.news.userId}</em></li>
    <li>Poster: <em>${view.entityTabContent.news.posterId}</em></li>
    <li><em>${view.entityTabContent.news.ownUpdates?string("Showing", "Not showing")}</em> own updates</li>
  </ul>
  <h2>Post</h2>
  <form action="" method="POST">
    <label for="formMessage">Message:</label>
    <textarea id="formMessage" name="formMessage" rows="5"></textarea>
    <label for="formSubmit">Submit:</label>
    <input id="formSubmit" name="formSubmit" type="submit" value="Post"/>
  </form>
  <h2>News</h2>
  <ul id="news">
    <#list view.entityTabContent.news.news as item>
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