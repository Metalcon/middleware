<#macro printLikeButton uid likeData>
	Current vote:${likeData.currentVote}
    <#assign divID = "like_${uid}"> 
    <div id=${divID}>
        <a id="${divID}_up" href="UP">up</a>(<span id="${divID}_upNum">${likeData.upVoteNum}</span>)
        <a id="${divID}_down" href="DOWN">down</a>(<span id="${divID}_downNum">${likeData.downVoteNum}</span>)
    </div>
    
    <script type="text/javascript">
        
        function updateLinks(baseID, userVote){
            if(userVote=="UP"){
                $("#"+baseID+"_up").prop("href", "NEUTRAL")
            } else {
                $("#"+baseID+"_up").prop("href", "UP")
            }
            
            if(userVote=="DOWN"){
                $("#"+baseID+"_down").prop("href", "NEUTRAL")
            } else {
                $("#"+baseID+"_down").prop("href", "DOWN")
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
                    if(vote=="UP"){
                        upNum++
                        if(mainDiv.data("userVote") =="DOWN"){
                            downNum--
                        }
                    } else if(vote=="DOWN"){
                        downNum++
                        if(mainDiv.data("userVote") =="UP"){
                            upNum--
                        }
                    } else  {
                         if(mainDiv.data("userVote") =="UP"){
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
            installLikeButtons("${divID}", "${uid}", "${likeData.currentVote}", ${likeData.upVoteNum}, ${likeData.downVoteNum});
	    });
    </script>
</#macro>
