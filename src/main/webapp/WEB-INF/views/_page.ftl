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
        <a href="/instrument/Guitar/about">Guitar</a> | <a href="/music/Ensiferum/about">Ensiferum</a> | <a href="/music/Ensiferum/Victory-Songs/Ahti">Ahti</a>
      </div>
    </div>
  </#macro>
</#if>

<#-- START: variables set by includes -->
<#assign viewTitle = "">
<#-- END: variables set by includes -->

<#if view.name == "entity">
  <#include "entity/_entity.ftl">
<#elseif view.name == "login">
  <#include "login.ftl">
</#if>