<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

Newsfeed Tab
    <#escape x as x?html>
      <h1>Metalcon Newsstream</h1>
      <ul class="info">
        <li>User: <em>${view.entityTabContent.newsFeed.userId}</em></li>
         <li>Poster: <em>${view.entityTabContent.newsFeed.posterId}</em></li>
         <li><em>${view.entityTabContent.newsFeed.ownUpdates?string("Showing", "Not showing")}</em> own updates</li>
      </ul>
      <h2>Post</h2>
        <label for="formMessage">Message:</label>
        <textarea id="formMessage" name="formMessage" rows="5"></textarea>
        <label for="formSubmit">Submit:</label>
        <input id="formSubmit" name="formSubmit" type="submit" value="Post"/>
      <h2>News</h2>
      <ul id="news">
        <#list view.entityTabContent.newsFeed.news as item>
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