<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__pjaxr.ftl" as pjaxr>

<pjaxr-head>
  <title>${viewTitle?html}</title>
  <@mtl.printStylesheets/>
  <@mtl.printLessStylesheets/>
  <@mtl.printMetaTags/>
</pjaxr-head>
<pjaxr-body>
  <@site>
    <@page>
      <@content>
        <@innerContent/>
      </@content>
    </@page>
  </@site>
  <#list pjaxr.additionalBlocks as additionalBlock>
    ${additionalBlock}
  </#list>
</pjaxr-body>
<pjaxr-namespace>${pjaxr.namespace}</pjaxr-namespace>
