<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>
<#import "/__html.ftl" as html>
<@html.html>
  <@html.head title="Home - Metalcon"/>
  <@html.body>
    <h1>Metalcon Middleware</h1>
    <p>Welcome to the Metalcon Middleware!</p>
    <p>Find information under <a href="https://github.com/renepickhardt/metalcon/wiki/componentMiddleware">componentMiddleware (Github Wiki)</a>.</p>
    <h2>URLs</h2>
    <p>URLs are specified under <a href="https://github.com/renepickhardt/metalcon/wiki/UrlMapping">UrlMapping (Github Wiki)</a>.</p>
    <p>There currently only exists a few dummy entities. They can be acces from
    the following URLs:</p>
    <ul>
      <li>
        <strong>Band</strong>
        <ul>
          <li><a href="<@mtl.url "/music/Ensiferum"/>"><code>/music/Ensiferum</code></a></li>
        </ul>
      </li>
      <li>
        <strong>City</strong>
        <ul>
          <li><a href="<@mtl.url "/city/Koblenz"/>"><code>/city/Koblenz</code></a></li>
        </ul>
      </li>
      <li>
        <strong>Record</strong>
        <ul>
          <li><a href="<@mtl.url "/music/Ensiferum/Victory-Songs"/>"><code>/music/Ensiferum/Victory-Songs</code></a></li>
        </ul>
      </li>
      <li>
        <strong>Tour</strong>
      </li>
      <li>
        <strong>Track</strong>
        <ul>
          <li><a href="<@mtl.url "/music/Ensiferum/Victory-Songs/Ahti"/>"><code>/music/Ensiferum/Victory-Songs/Ahti</code></a></li>
        </ul>
      </li>
      <li>
        <strong>User</strong>
        <ul>
          <li><a href="<@mtl.url "/user/James-Hetfield"/>"><code>/user/James-Hetfield</code></a></li>
        </ul>
      </li>
      <li>
        <strong>Venue</strong>
        <ul>
          <li><a href="<@mtl.url "/venue/Druckluftkammer"/>"><code>/venue/Druckluftkammer</code></a></li>
        </ul>
      </li>
    </ul>
    <h2>Tabs</h2>
    <p>Currently if no other tab is specified, the default tab for all entities
    is the NewsTab.</p>
    <p>You can append a tab mapping to any of the URLs. (Note that some
    entity types dont support a tap mapping so they will 404).</p>
    <ul>
      <li><code>/about</code></li>
      <li><code>/bands</code></li>
      <li><code>/events</code></li>
      <li><code>/news</code></li>
      <li><code>/photos</code></li>
      <li><code>/recommendations</code></li>
      <li><code>/records</code></li>
      <li><code>/reviews</code></li>
      <li><code>/tracks</code></li>
      <li><code>/users</code></li>
      <li><code>/venues</code></li>
    </ul>
    <h2>Model</h2>
    <p>You can append <code>.json</code> to any URL to view the model as
    json data instead of the view.</p>
  </@html.body>
</@html.html>
