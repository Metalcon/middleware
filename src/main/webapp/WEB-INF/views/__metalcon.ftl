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

<#--
 # Convenience macro to create a <html> tag. Saves us from writing XHTML-
 # boilerplate code everytime.
 # 
 # Usage:
 # <@mtl.html>
 #   Stuff inside <html> tag.
 # </@mtl.html>
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
<#macro head title>
<head>
  <title>${title?html}</title>
  <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
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
<body>
  <#nested>
</body>
</#macro>

<#--
 # Convenience macro to create a page.
 #
 # @example
 #   <@mtl.page>
 #     Stuff inside page container.
 #   </@mtl.page>
 #-->
<#macro page>
<div id="page" class="container">
  <#nested>
</div>
</#macro>

<#--
 # Convenience macro to create a content.
 #
 # @example
 #   <@mtl.content>
 #     Stuff inside content container.
 #   </@mtl.content>
 #-->
<#macro content>
<div id="content" class="col-xs-12">
  <#nested>
</div>
</#macro>

<#--
 # Convenience macro to create a inner_content.
 #
 # @example
 #   <@mtl.content>
 #     Stuff inside inner_content container.
 #   </@mtl.content>
 #-->
<#macro inner_content>
<div id="inner_content">
  <#nested>
</div>
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