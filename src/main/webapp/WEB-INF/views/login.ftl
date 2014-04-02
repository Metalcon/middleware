<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/metalcon.ftl" as mtl>
<#import "/spring.ftl" as spring>

<#if pjaxr.content>
  <#macro content>
    <form action="<@spring.url "/login"/>" method="post">
      <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username"/>
      </p>
      <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>
      </p>
      <@mtl.security.csrfInput/>
      <button type="submit">Log in</button>
    </form>
  </#macro>
</#if>