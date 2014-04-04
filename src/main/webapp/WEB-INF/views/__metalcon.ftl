<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__html.ftl" as html>

<#--
 # __metalcon.ftl
 #
 # Collections of macros to easy view development.
 #
 # Include in your view like this:
 # <#import "/__metalcon.ftl" as mtl>
 #-->
 
<#setting locale="de_DE">


<#-- TODOC: Stylesheets -->
<#assign stylesheets = []>
<#macro addStylesheet href>
  <#assign stylesheets = stylesheets + [href]>
</#macro>
<#macro printStylesheets>
  <#list stylesheets as stylesheet>
    <@html.stylesheet href=stylesheet/>
  </#list>
</#macro>

<#-- TODOC: LessStylesheets -->
<#assign lessStylesheets = []>
<#macro addLessStylesheet href>
  <#assign lessStylesheets = lessStylesheets + [href]>
</#macro>
<#macro printLessStylesheets>
  <#list lessStylesheets as stylesheet>
    <@html.lessStylesheet href=stylesheet/>
  </#list>
</#macro>

<#-- TODOC: MetaTags -->
<#assign metaTags = []>
<#macro addMetaTag attr key value>
  <#assign metaTags = metaTags + [{
    "attr": attr,
    "key": key,
    "value": value
  }]>
</#macro>
<#macro printMetaTags>
  <#list metaTags as metaTag>
    <meta ${metaTag["attr"]}="${metaTag["key"]}" content="${metaTag["value"]}"/>
  </#list>
  <@csrfMetaTags/>
</#macro>


<#-- TODOC: url -->
<#macro url relativeUrl extra...><#if extra?? && extra?size!=0>${springMacroRequestContext.getContextUrl(relativeUrl,extra)}<#else>${springMacroRequestContext.getContextUrl(relativeUrl)}</#if></#macro>


<#--
 # Load Spring Security JSP Taglib.
 #-->
<#assign security = JspTaglibs["http://www.springframework.org/security/tags"]>

<#-- TODOC: crsfInput -->
<#macro csrfInput>
  <@security.csrfInput/>
</#macro>
<#-- TODOC: crsfMetaTags -->
<#macro csrfMetaTags>
  <@security.csrfMetaTags/>
</#macro>