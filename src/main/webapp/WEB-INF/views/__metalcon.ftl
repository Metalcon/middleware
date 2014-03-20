<#ftl encoding="UTF-8" strict_syntax=true>

<#--
 # __metalcon.ftl
 #
 # Collections of macros to easy view development.
 #
 # Include in your view like this:
 # <#import "/__metalcon.ftl" as mtl>
 #-->
 
<#import "/spring.ftl" as spring>
<#setting locale="de_DE">
<#if view?? && view.pjaxrMatching??>
  <#global pjaxrMatching=view.pjaxrMatching>
<#else>
  <#global pjaxrMatching=0>
</#if>

<#--
 # Convenience macro to create a <html> tag. Saves us from writing XHTML-
 # boilerplate code everytime.
 # 
 # @example
 #   <@mtl.html>
 #     Stuff inside <html> tag.
 #   </@mtl.html>
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
 # @example
 #   <@mtl.head title="My Page Title">
 #     Stuff inside <head> tag.
 #   </@mtl.head>
 #-->
<#macro head title metaTags=[]>
<head>
  <title>${title?html}</title>
  <#if metaTags??>
    <#list metaTags as metaTag>
        <meta<#list metaTag?keys as key> ${key}="${metaTag[key]}"</#list> />
    </#list>
  </#if>
  <#nested>
</head>
</#macro>

<#--
 # Convenience macro to inclue a CSS-file. Use inside <head> tag.
 #
 # @example
 #   <mtl.stylesheet href="myStyle.css"/>
 #-->
<#macro stylesheet href>
<link rel="stylesheet" type="text/css" href="<@spring.url "/resources/css/${href}"/>"/>
</#macro>

<#--
 # Convenience macro to inclue a LESS-file. Use inside <head> tag.
 #
 # @example
 #   <mtl.lessStylesheet href="myStyle.less"/>
 #-->
<#macro lessStylesheet href>
<link rel="stylesheet/less" type="text/css" href="<@spring.url "/resources/less/${href}"/>"/>
</#macro>

<#--
 # Convenience macro to create a <body> tag.
 #
 # @example
 #   <@mtl.body>
 #     Stuff inside <body> tag.
 #   </@mtl.body>
 #-->
<#macro body>
<body<#if view?? && view.pjaxrNamespace??> data-pjaxr-namespace="${view.pjaxrNamespace}"</#if>>
  <#nested>
</body>
</#macro>

<#macro site>
  <#if pjaxrMatching &lt; 1>
    <div id="site">
      <#nested>
    </div>
  <#else>
    <#nested>
  </#if>
</#macro>

<#macro page>
  <#if pjaxrMatching &lt; 2>
    <div id="page" class="container">
      <div class="row">
        <#nested>
        </div>
    </div>
  <#else>
    <#nested>
  </#if>
</#macro>

<#macro content>
  <#if pjaxrMatching &lt; 3>
    <div id="content" class="col-xs-12">
      <#nested>
    </div>
  <#else>
    <#nested>
  </#if>
</#macro>

<#macro innerContent>
  <#if pjaxrMatching &lt; 4>
    <div id="inner_content">
      <#nested>
    </div>
  <#else>
    <#nested>
  </#if>
</#macro>

<#--
 # To be used in views that are not implemented yet.
 #
 # @example
 #   <@mtl.not_implemented/>
 #-->
<#macro not_implemented>
<@xhtml>
  <@head title="Not implemented"/>
  <@body>
    <h1>Not implemented</h1>
    <p>This view is not implemented yet.</p>
    <#nested>
  </@body>
</@xhtml>
</#macro>

<#--
 #  Pjaxr utilities
 # -->
<#macro pjaxrBody>
<pjaxr-body>
  <#nested>
</pjaxr-body>
</#macro>

<#macro pjaxrHead title metaTags=[]>
<pjaxr-head>
  <title>${title?html}</title>
  <#if metaTags??>
    <#list metaTags as metaTag>
        <meta<#list metaTag?keys as key> ${key}="${metaTag[key]}"</#list> />
    </#list>
  </#if>
  <#nested>
</pjaxr-head>
</#macro>

<#--
 # <pjaxr-namespace>, declares which namespace we are in currently
 #-->
<#macro pjaxrNamespace>
  <pjaxr-namespace><#nested></pjaxr-namespace>
</#macro>
 
 
<#--
 # frontend lib versions
 #-->

<#global BOOTSTRAP_VERSION = '3.1.1'>
<#global FONTAWESOME_VERSION = '4.0.3'>
<#global JQUERY_VERSION = '2.1.0'>
<#global JQUERY_PJAXR_VERSION = '1.2.0rc1'>
<#global LESS_VERSION = '1.7.0'>
