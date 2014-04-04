<#ftl encoding="UTF-8" strict_syntax=true>

<#-- PAGE -->
<#if !pjaxr.page>
  <#macro page>
    <#nested>
  </#macro>
<#else>
  <#macro page>
    <div id="page" class="container">
      <div class="row">
        <#nested>
        <a href="/instrument/Guitar-19/about">Guitar</a> | <a href="/music/Ensiferum-12/about">Ensiferum-12</a> | <a href="/music/Ensiferum-22/about">Ensiferum-22</a>
      </div>
    </div>
  </#macro>
</#if>

<#-- START: variables set by includes -->
<#assign viewTitle = "">
<#-- END: variables set by includes -->

<#if view.type == "entity">
  <#include "entity/_entity.ftl">
<#elseif view.type == "login">
  <#include "login.ftl">
</#if>