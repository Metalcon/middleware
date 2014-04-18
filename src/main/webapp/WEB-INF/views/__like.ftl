<#macro printLikeButton uid message upNum downNum>
    <#assign divID = "like_${uid}"> 
    <div id=${divID}>
    	<a id="${divID}_up" href="/like/${uid}/up">up</a>(${upNum})
        <a id="${divID}_neutral" href="/like/${uid}/neutral">neutral</a>
        <a id="${divID}_down" href="/like/${uid}/down">down</a>(${downNum})
        
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
    	        	   alert("executed");
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) { 
                    alert("Status: " + textStatus+"\nError: " + errorThrown); 
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
