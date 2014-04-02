<#ftl encoding="UTF-8" strict_syntax=true>

<#if pjaxr.page>
  <#macro page>
    <div id="page" class="container">
      <div class="row">
        <#nested>
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