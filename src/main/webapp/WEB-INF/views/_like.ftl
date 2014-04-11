<#import "/__pjaxr.ftl" as pjaxr>
<#macro printLikeButton uid>
    <div>
        <a class="navbar-brand" href="/like/${uid}/up">up</a>
        <a class="navbar-brand" href="/like/${uid}/down">neutral</a>
        <a class="navbar-brand" href="/like/${uid}/neutral">down</a>
    </div>
</#macro>

<#assign likeButton>asdfasdflkjadslfj</#assign>
<@pjaxr.addAdditionalBlocks [likeButton]/>