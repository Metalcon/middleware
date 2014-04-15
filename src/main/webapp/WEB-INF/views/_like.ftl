<#macro printLikeButton uid>
    <#assign divID = "like_${uid}"> 
    <div id=${divID}>
    	${uid}
        <a id="${divID}_up" href="/like/${uid}/up">up</a>
        <a id="${divID}_neutral" href="/like/${uid}/down">neutral</a>
        <a id="${divID}_down" href="/like/${uid}/neutral">down</a>
        
        <#if likemessage??>
    		${likemessage}
    	</#if>
    </div>
    
    <script type="text/javascript">
	    function onLikeClick(event){
	        event.preventDefault();
	        // Send GET request to the href-URL
	        $.ajax({
	           url: $(this).attr('href')
	           ,success: function(response) {
	        	   $('#${divID}').html(response);
	           }
	        });
	        return false;
	    }
    
	    $(document).ready(function() {
	    	$("#${divID}_up").click(onLikeClick);
	    	$("#${divID}_neutral").click(onLikeClick);
	    	$("#${divID}_down").click(onLikeClick);
	    });
    </script>
</#macro>
