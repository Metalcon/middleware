<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/spring.ftl" as spring>
<#import "/__metalcon.ftl" as mtl>
<#import "/__html.ftl" as html>
<#include "/_like.ftl">
<@html.html>
  <@html.head title="Like - Metalcon"/>
  <@html.body>
    <@printLikeButton uid=uid/>
  </@html.body>
</@html.html>