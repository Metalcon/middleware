<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/__metalcon.ftl" as mtl>

<#--
 # Will include the corrrect tabPreview subtemplate.
 # For example, if tabPreviewName is "ABOUT_TAB" this will include "impl/about_tab.ftl"
 #-->
<#include "impl/" + tabContent.entityTabType?lower_case + ".ftl">