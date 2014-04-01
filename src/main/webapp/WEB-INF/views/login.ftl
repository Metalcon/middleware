<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/spring.ftl" as spring>

<#macro content>
  <form action="<@spring.url "/login"/>" method="post">
    <#if param?? && param.error??>
      <p>Invalid username and password.</p>
    </#if>
    <#if param?? && param.logout??>
      <p>You have been logged out.</p>
    </#if>
    <p>
      <label for="username">Username</label>
      <input type="text" id="username" name="username"/>
    </p>
    <p>
      <label for="password">Password</label>
      <input type="password" id="password" name="password"/>
    </p>
    <#if _crsf??>
      <input type="hidden" name="${_csrf.parameterName}" value="${_crsf.token}"/>
    </#if>
    <button type="submit">Log in</button>
  </form>
</#macro>