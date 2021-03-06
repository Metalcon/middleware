<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

<#if pjaxr.content>
  <#macro content>
    <form action="<@mtl.url "/login"/>" method="post">
      <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username"/>
      </p>
      <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>
      </p>
      <p>
        <input type="checkbox" id="rememberme" name="rememberme" class="form-control"/> <label for="rememberme" style="color: white;">Remember Me</label>
      </p>
      <button type="submit">Log in</button>
      <@mtl.csrfInput/>
    </form>
  </#macro>
</#if>