<#ftl encoding="UTF-8" strict_syntax=true>

<#if view?? && view.pjaxrMatching??>
  <#global pjaxrMatching=view.pjaxrMatching>
<#else>
  <#global pjaxrMatching=0>
</#if>

<#if view?? && view.pjaxrNamespace??>
  <#global pjaxrNamespaceBlock=view.pjaxrNamespace>
<#else>
  <#global pjaxrNamespaceBlock="">
</#if>

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
<body data-pjaxr-namespace="${pjaxrNamespaceBlock}">
  <#nested>
</body>
</#macro>

<#macro site>
  <#if pjaxrSite>
    <div id="site">
      <#nested>
    </div>
  <#else>
    <#nested>
  </#if>
</#macro>
<#global site=site>

<#macro page>
  <#if pjaxrPage>
    <div id="page" class="container">
      <div class="row">
        <#nested>
        <a href="/instrument/Guitar-19/about">Guitar</a> | <a href="/music/Ensiferum-12/about">Ensiferum-12</a> | <a href="/music/Ensiferum-22/about">Ensiferum-22</a>
      </div>
    </div>
  <#else>
    <#nested>
  </#if>
</#macro>
<#global page=page>

<#macro content>
  <#if pjaxrContent>
    <div id="content" class="col-xs-12">
      <#nested>
    </div>
  <#else>
    <#nested>
  </#if>
</#macro>
<#global content=content>

<#macro innerContent>
  <#if pjaxrInnerContent>
    <div id="inner_content">
      <#nested>
    </div>
  <#else>
    <#nested>
  </#if>
</#macro>
<#global innerContent=innerContent>

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
 # frontend lib versions
 #-->

<#global BOOTSTRAP_VERSION = '3.1.1'>
<#global FONTAWESOME_VERSION = '4.0.3'>
<#global JQUERY_VERSION = '2.1.0'>
<#global JQUERY_PJAXR_VERSION = '1.2.0rc1'>
<#global LESS_VERSION = '1.7.0'>
