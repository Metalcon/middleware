<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/spring.ftl" as spring>
<#import "/__metalcon.ftl" as mtl>

<#--
 # This is the default metalcon page template. Every "page" on metalcon uses
 # this view. Inside it, it includes more specific views.
 #-->

<#assign lessStylesheets = lessStylesheets + ["main.less"]>

<#assign metaTags = metaTags + [{"http-equiv": "content-type", 
                                 "content": "application/xhtml+xml; charset=UTF-8"},
                                {"http-equiv": "X-UA-Compatible", 
                                 "content": "IE=edge"},
                                {"name": "viewport", 
                                 "content": "width=device-width, initial-scale=1"}]>

<#macro siteBlock>
  <@site>
    <div id="navbar" class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<@spring.url "/"/>">Metalcon</a>
          ${view_pc}
          ${view_id}
        </div>
        <form class="collapse navbar-collapse navbar-form navbar-nav" role="form">
          <div class="form-group">
            <input type="text" placeholder="Search..." class="form-control">
          </div>
        </form>
        <form class="collapse navbar-collapse navbar-form navbar-right" role="form" action="/login" method="POST">
          <div class="form-group">
            <input type="text" placeholder="email" id="email" name="email" class="form-control">
          </div>
          <div class="form-group">
            <input type="password" placeholder="password" id="password" name="password" class="form-control">
          </div>
          <button type="submit" class="btn btn-success">Sign in</button>
        </form>
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
  </@site>
</#macro>

<@mtl.html>
  
  <#--
   # Include more specific view template. These are expected to set the
   # following variables:
   # viewTitle - String containing the title to be displayed in <title>-tag.
   #-->
  <@mtl.head title="${viewTitle}" metaTags=metaTags>
    <#list stylesheets as stylesheet>
      <@mtl.stylesheet href=stylesheet/>
    </#list>
    <#list lessStylesheets as stylesheet>
      <@mtl.lessStylesheet href=stylesheet/>
    </#list>
    
    <script src="<@spring.url "/resources/libs/jquery/"+JQUERY_VERSION+"/jquery.js"/>"></script>
    <script src="<@spring.url "/resources/libs/jquery-pjaxr/"+JQUERY_PJAXR_VERSION+"/jquery.pjaxr.js"/>"></script>
    
    <script src="<@spring.url "/resources/libs/bootstrap/"+BOOTSTRAP_VERSION+"/js/affix.js"/>"></script>
    <script src="<@spring.url "/resources/libs/bootstrap/"+BOOTSTRAP_VERSION+"/js/alert.js"/>"></script>
    <script src="<@spring.url "/resources/libs/bootstrap/"+BOOTSTRAP_VERSION+"/js/button.js"/>"></script>
    <script src="<@spring.url "/resources/libs/bootstrap/"+BOOTSTRAP_VERSION+"/js/carousel.js"/>"></script>
    <script src="<@spring.url "/resources/libs/bootstrap/"+BOOTSTRAP_VERSION+"/js/collapse.js"/>"></script>
    <script src="<@spring.url "/resources/libs/bootstrap/"+BOOTSTRAP_VERSION+"/js/dropdown.js"/>"></script>
    <script src="<@spring.url "/resources/libs/bootstrap/"+BOOTSTRAP_VERSION+"/js/modal.js"/>"></script>
    <script src="<@spring.url "/resources/libs/bootstrap/"+BOOTSTRAP_VERSION+"/js/tooltip.js"/>"></script>
    <script src="<@spring.url "/resources/libs/bootstrap/"+BOOTSTRAP_VERSION+"/js/popover.js"/>"></script>
    <script src="<@spring.url "/resources/libs/bootstrap/"+BOOTSTRAP_VERSION+"/js/scrollspy.js"/>"></script>
    <script src="<@spring.url "/resources/libs/bootstrap/"+BOOTSTRAP_VERSION+"/js/tab.js"/>"></script>
    <script src="<@spring.url "/resources/libs/bootstrap/"+BOOTSTRAP_VERSION+"/js/transition.js"/>"></script>
    
    <script src="<@spring.url "/resources/js/main.js"/>"></script>
  
    <!--TODO only in develop and has to be last -->
    <script src="<@spring.url "/resources/libs/less/"+LESS_VERSION+"/less.min.js"/>" type="text/javascript"></script>
  </@mtl.head>
  <@mtl.body>
    <@siteBlock>
      <@pageBlock>
        <@contentBlock>
          <@innerContentBlock>
            ${innerContent}
          </@innerContentBlock>
        </@contentBlock>
      </@pageBlock>
    </@siteBlock>
  </@mtl.body>
</@mtl.html>
