<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

<#--
 # Frontend lib versions.
 #-->
<#assign BOOTSTRAP_VERSION    = '3.1.1'>
<#assign FONTAWESOME_VERSION  = '4.0.3'>
<#assign JQUERY_VERSION       = '2.1.0'>
<#assign JQUERY_PJAXR_VERSION = '1.2.0rc1'>
<#assign LESS_VERSION         = '1.7.0'>

<#--
 # Convenience macro to create a <html> tag. Saves us from writing XHTML-
 # boilerplate code everytime.
 #-->
<#macro html>
  <!DOCTYPE html>
  <html lang="de">
    <#nested>
  </html>
</#macro>

<#--
 # Convenience macro to create a <head> tag.
 #
 # @param title
 #        A string to be used for the <title> tag.
 #-->
<#macro head title>
  <head>
    <title>${title?html}</title>
    <#nested>
  </head>
</#macro>

<#--
 # Convenience macro to include a CSS-file. Use inside <head> tag.
 #-->
<#macro stylesheet href>
  <link rel="stylesheet" type="text/css" href="<@mtl.url "/resources/css/${href}"/>"/>
</#macro>

<#--
 # Convenience macro to include a LESS-file. Use inside <head> tag.
 #-->
<#macro lessStylesheet href>
  <link rel="stylesheet/less" type="text/css" href="<@mtl.url "/resources/less/${href}"/>"/>
</#macro>

<#--
 # Convenience macro to create a <body> tag.
 #-->
<#macro body pjaxrNamespace="">
  <body data-pjaxr-namespace="${pjaxrNamespace}">
    <#nested>
  </body>
</#macro>