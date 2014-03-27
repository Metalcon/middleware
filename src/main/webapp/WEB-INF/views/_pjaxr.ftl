<#ftl encoding="UTF-8" strict_syntax=true>

<@pjaxrHead title="${viewTitle}" metaTags=metaTags></@pjaxrHead>
<@pjaxrBody>
  <@siteBlock>
    <@pageBlock>
      <@contentBlock>
        <@innerContentBlock>
          ${innerContent}
        </@innerContentBlock>
      </@contentBlock>
    </@pageBlock>
  </@siteBlock>
  <#list additionalPjaxrBlocks as additionalPjaxrBlock>
    ${additionalPjaxrBlock}
  </#list>
</@pjaxrBody>
<@pjaxrNamespace>${pjaxrNamespaceBlock}</@pjaxrNamespace>
