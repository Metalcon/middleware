<#ftl encoding="UTF-8" strict_syntax=true>

<#assign matching  = view.pjaxrMatching!0>
<#assign namespace = view.pjaxrNamespace!"">

<#assign site         = (matching &lt;= 0)>
<#assign page         = (matching &lt;= 1)>
<#assign content      = (matching &lt;= 2)>
<#assign innerContent = (matching &lt;= 3)>

<#assign additionalBlocks = []>
<#macro addAdditionalBlock block>
  <#assign additionalBlocks = additionalBlocks + [block]>
</#macro>
<#macro addAdditionalBlocks blocks>
  <#assign additionalBlocks = additionalBlocks + blocks>
</#macro>
