<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>
<#import "/__html.ftl" as html>

<@mtl.addLessStylesheet "main.less"/>

<@mtl.addMetaTag "http-equiv" "content-type" "text/html; charset=UTF-8"/>
<@mtl.addMetaTag "http-equiv" "X-UA-Compatible" "IE=edge"/>
<@mtl.addMetaTag "name" "viewport" "width=device-width, initial-scale=1"/>

<@html.html>
  <@html.head title="${viewTitle}">
    <@mtl.printStylesheets/>
    <@mtl.printLessStylesheets/>
    <@mtl.printMetaTags/>
    
    <script src="<@mtl.url "/resources/libs/jquery/"+html.JQUERY_VERSION+"/jquery.js"/>"></script>
    <script src="<@mtl.url "/resources/libs/jquery-pjaxr/"+html.JQUERY_PJAXR_VERSION+"/jquery.pjaxr.js"/>"></script>
    
    <script src="<@mtl.url "/resources/libs/bootstrap/"+html.BOOTSTRAP_VERSION+"/js/affix.js"/>"></script>
    <script src="<@mtl.url "/resources/libs/bootstrap/"+html.BOOTSTRAP_VERSION+"/js/alert.js"/>"></script>
    <script src="<@mtl.url "/resources/libs/bootstrap/"+html.BOOTSTRAP_VERSION+"/js/button.js"/>"></script>
    <script src="<@mtl.url "/resources/libs/bootstrap/"+html.BOOTSTRAP_VERSION+"/js/carousel.js"/>"></script>
    <script src="<@mtl.url "/resources/libs/bootstrap/"+html.BOOTSTRAP_VERSION+"/js/collapse.js"/>"></script>
    <script src="<@mtl.url "/resources/libs/bootstrap/"+html.BOOTSTRAP_VERSION+"/js/dropdown.js"/>"></script>
    <script src="<@mtl.url "/resources/libs/bootstrap/"+html.BOOTSTRAP_VERSION+"/js/modal.js"/>"></script>
    <script src="<@mtl.url "/resources/libs/bootstrap/"+html.BOOTSTRAP_VERSION+"/js/tooltip.js"/>"></script>
    <script src="<@mtl.url "/resources/libs/bootstrap/"+html.BOOTSTRAP_VERSION+"/js/popover.js"/>"></script>
    <script src="<@mtl.url "/resources/libs/bootstrap/"+html.BOOTSTRAP_VERSION+"/js/scrollspy.js"/>"></script>
    <script src="<@mtl.url "/resources/libs/bootstrap/"+html.BOOTSTRAP_VERSION+"/js/tab.js"/>"></script>
    <script src="<@mtl.url "/resources/libs/bootstrap/"+html.BOOTSTRAP_VERSION+"/js/transition.js"/>"></script>
    
    <script src="<@mtl.url "/resources/js/main.js"/>"></script>
    <script src="<@mtl.url "/resources/js/like.js"/>"></script>
  
    <!--TODO only in develop and has to be last -->
    <script src="<@mtl.url "/resources/libs/less/"+html.LESS_VERSION+"/less.min.js"/>" type="text/javascript"></script>
  </@html.head>
  <@html.body "${pjaxr.namespace}">
    <@site>
      <@page>
        <@content>
          <@innerContent/>
        </@content>
      </@page>
    </@site>
  </@html.body>
</@html.html>
