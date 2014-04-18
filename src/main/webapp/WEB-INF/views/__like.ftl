<#macro printLikeButton uid currentVote upNum downNum>
    <#assign divID = "like_${uid}"> 
    <div id=${divID}>
        <a id="${divID}_up" href="up">up</a>(<span id="${divID}_upNum">${upNum}</span>)
        <a id="${divID}_down" href="down">down</a>(<span id="${divID}_downNum">${downNum}</span>)
        <#if likemessage??>
    		${likemessage}
    	</#if>
    </div>
    
    <script type="text/javascript">
        
        function updateLinks(baseID, userVote){
            if(userVote=="up"){
                $("#"+baseID+"_up").prop("href", "neutral")
            } else {
                $("#"+baseID+"_up").prop("href", "up")
            }
            
            if(userVote=="down"){
                $("#"+baseID+"_down").prop("href", "neutral")
            } else {
                $("#"+baseID+"_down").prop("href", "down")
            }
        }
        
	    function onLikeClick(event){
            event.preventDefault();
            mainDiv=$(this).data("mainLikeDiv")
            UID=mainDiv.data("likeUID")
            
            upNum=mainDiv.data("upNum")
            downNum=mainDiv.data("downNum")
            
	        // Send GET request to the href-URL
	        vote=$(this).attr('href')

	        $.ajax({
                url: "/like/"+UID+"/"+vote
                ,success: function(response) {
                    if(vote=="up"){
                        upNum++
                        if(mainDiv.data("userVote") =="down"){
                            downNum--
                        }
                    } else if(vote=="down"){
                        downNum++
                        if(mainDiv.data("userVote") =="up"){
                            upNum--
                        }
                    } else  {
                         if(mainDiv.data("userVote") =="up"){
                            upNum--
                        } else {
                            downNum--
                        }
                    }
                    mainDiv.data("userVote", vote);
                    mainDiv.data("upNum", upNum)
                    mainDiv.data("downNum", downNum)
                    
                    mainDiv.data("upNumField").text(upNum)
                    mainDiv.data("downNumField").text(downNum)
                    
                    updateLinks(mainDiv.attr("id"), vote);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) { 
                    alert("Status: " + textStatus+"\nError: " + errorThrown); 
                }  
	        });
	        return false;
	    }
	    
	    function installLikeButton(mainLikeDiv, element){
            element.data("mainLikeDiv",mainLikeDiv)
            element.click(onLikeClick)
	    }
	    
	    function installLikeButtons(baseID, UID, userVote,upNum, downNum){
	        mainLikeDiv=$("#"+baseID)

 	        installLikeButton(mainLikeDiv,$("#"+baseID+"_up"))
	        installLikeButton(mainLikeDiv,$("#"+baseID+"_down"))
 	       
	        mainLikeDiv.data("likeUID", UID)
	        mainLikeDiv.data("userVote", userVote)
	        mainLikeDiv.data("upNum", upNum)
	        mainLikeDiv.data("downNum", downNum)
	       
            mainLikeDiv.data("upNumField", $("#"+baseID+"_upNum"))
	        mainLikeDiv.data("downNumField", $("#"+baseID+"_downNum"))
	       
	        updateLinks(baseID, userVote);
	    }
    
	    $(document).ready(function() {
            installLikeButtons("${divID}", "${uid}", "${currentVote}", ${upNum}, ${downNum});
	    });
	    
	    $(document).ready(function() {
            alert("a")
        });
         $(document).ready(function() {
            alert("b")
        });
    </script>
</#macro>
