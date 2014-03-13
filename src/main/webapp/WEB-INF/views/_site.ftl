<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

<#--
 # This is the default metalcon page template. Every "page" on metalcon uses
 # this view. Inside it, it includes more specific views.
 #-->

<#--
 # Lists of all css files to be included in the output. Inside a view a view
 # specific stylesheet can be added like this:
 # <#assign stylesheets = stylesheet + ["mystyle.css"]>
 #-->
<#assign stylesheets = []>
<#assign lessStylesheets = ["main.less"]>

<#if view.pjaxrMatching == 0>
<@mtl.html>

  
  <#--
   # Include more specific view template. These are expected to set the
   # following variables:
   # view_title - String containing the title to be displayed in <title>-tag.
   # view_content - HTML string to contain body content.
   #-->
  <#if view.type == "entity">
    <#include "entity/_entity.ftl">
  </#if>

  <@mtl.head title="${view_title}">
    <#list stylesheets as stylesheet>
      <@mtl.stylesheet href=stylesheet/>
    </#list>
    <#list lessStylesheets as stylesheet>
      <@mtl.lessStylesheet href=stylesheet/>
    </#list>
    
    <script src="/resources/libs/jquery/2.1.0/jquery.js"></script>
    <script src="/resources/libs/jquery-pjaxr/1.1.0/jquery-pjaxr.js"></script>
    
    <script src="/resources/libs/bootstrap/3.1.1/js/affix.js"></script>
    <script src="/resources/libs/bootstrap/3.1.1/js/alert.js"></script>
    <script src="/resources/libs/bootstrap/3.1.1/js/button.js"></script>
    <script src="/resources/libs/bootstrap/3.1.1/js/carousel.js"></script>
    <script src="/resources/libs/bootstrap/3.1.1/js/collapse.js"></script>
    <script src="/resources/libs/bootstrap/3.1.1/js/dropdown.js"></script>
    <script src="/resources/libs/bootstrap/3.1.1/js/modal.js"></script>
    <script src="/resources/libs/bootstrap/3.1.1/js/tooltip.js"></script>
    <script src="/resources/libs/bootstrap/3.1.1/js/popover.js"></script>
    <script src="/resources/libs/bootstrap/3.1.1/js/scrollspy.js"></script>
    <script src="/resources/libs/bootstrap/3.1.1/js/tab.js"></script>
    <script src="/resources/libs/bootstrap/3.1.1/js/transition.js"></script>
    
    <script src="/resources/js/main.js"></script>
  
    <!--TODO only in develop and has to be last -->
    <script src="/resources/libs/less/1.7.0/less.min.js" type="text/javascript"></script>
  </@mtl.head>
  <@mtl.body>
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
            <a class="navbar-brand" href="/">Metalcon</a>
            <a class="navbar-brand" href="/">pagecounts ${view.pc}</a>
            <a class="navbar-brand" href="/">userid ${view.id}</a>
          </div>
          <form class="collapse navbar-collapse navbar-form navbar-nav" role="form">
            <div class="form-group">
              <input type="text" placeholder="Search..." class="form-control">
            </div>
          </form>
          <form class="collapse navbar-collapse navbar-form navbar-right" role="form">
            <div class="form-group">
              <input type="text" placeholder="Email" class="form-control">
            </div>
            <div class="form-group">
              <input type="password" placeholder="Password" class="form-control">
            </div>
            <button type="submit" class="btn btn-success">Sign in</button>
          </form>
        </div>
      </div>
      <@mtl.page>
        <div class="row">
          <@mtl.content>
            ${content}
          </@mtl.content>
        </div>
      </@mtl.page>
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
  </@mtl.body>
</@mtl.html>
<#elseif view.pjaxrMatching &gt; 0>
  <#if view.type == "entity">
    <#include "entity/_entity.ftl">
  </#if>
  <@mtl.pjaxrHead title="${view_title}"></@mtl.pjaxrHead>
  <@mtl.pjaxrBody>
    ${content}
  </@mtl.pjaxrBody>
</#if>
