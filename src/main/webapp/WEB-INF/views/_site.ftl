<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>
<#import "/__pjaxr.ftl" as pjaxr>

<#assign viewPc><a id="view_pc" class="navbar-brand" href="#">pagecounter: ${view.pc!"null"}</a></#assign>
<#assign viewId><a id="view_id" class="navbar-brand" href="#">userid: ${view.id!"null"}</a></#assign>
<@pjaxr.addAdditionalBlocks [viewPc,viewId]/>

<#-- SITE -->
<#if !pjaxr.site>
  <#macro site>
    <#nested>
  </#macro>
<#else>
  <#macro site>
    <div id="site">
      <div id="navbar" class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<@mtl.url "/"/>">Metalcon</a>
            ${viewPc}
            ${viewId}
          </div>
          <form class="collapse navbar-collapse navbar-form navbar-nav" role="form">
            <div class="form-group">
              <input type="text" placeholder="Search..." class="form-control">
            </div>
          </form>
          <#if !view.userLogin??>
            <form action="<@mtl.url "/login"/>" method="post" class="collapse navbar-collapse navbar-form navbar-right" role="form">
              <div class="form-group">
                <input type="text" placeholder="email" id ="username" name="username" class="form-control"/>
              </div>
              <div class="form-group">
                <input type="password" placeholder="password" id="password" name="password" class="form-control"/>
              </div>
              <div class="form-group">
                <input type="checkbox" id="rememberme" name="rememberme" class="form-control"/> <label for="rememberme" style="color: white;">Remember Me</label>
              </div>
              <button type="submit" class="btn btn-success">Sign in</button>
              <@mtl.csrfInput/>
            </form>
          <#else>
            <span style="color: white;">
              Hallo, ${view.userLogin.username} (${view.userSession.muid}!
            </span>
            <form action="<@mtl.url "/logout"/>" method="post" class="collapse navbar-collapse navbar-form navbar-right" role="form">
              <button type="submit" class="btn btn-success">Logout</button>
              <@mtl.csrfInput/>
            </form>
          </#if>
        </div>
      </div>
      <#nested>
      <footer id="footer" class="navbar navbar-inverse">
        <div class="container">
          <div class="row">
            <div class="col-xs-12">
              <ul class="nav navbar-nav">
                <li><a href="#">Metalcon</a></li>
              </ul>
            </div>
          </div>
        </div>
      </footer>
    </div>
  </#macro>
</#if>

<#include "_page.ftl">