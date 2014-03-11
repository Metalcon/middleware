<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

<li class="tabPreview">
  <#--
   # Will include the corrrect tabPreview subtemplate.
   # For example, if tabPreviewName is "ABOUT_TAB" this will include "impl/about_tab.ftl"
   #-->
  <a href="${tab.entityTabType?lower_case}"><#include "impl/" + tab.entityTabType?lower_case + ".ftl"></a>
</li>