<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

<#--
<div id="filters">
  <form class="inline_form">
    <div class="form_group">
      <label class="checkbox">
        <input type="checkbox" checked="checked">&nbsp;Show everything
      </label>
    </div>
  </form>
</div>

Records Tab
-->

<#if !tabContent.records??>
  <p>No records</p>
<#else>
  <ul>
    <#list tabContent.records as record>
      <li>${record.name}</li>
    </#list>
  </ul>
</#if>
