/**
 * 
 */


function initcl(params){

	
	function prepend_comment(){
		var newcomm = params.commentTemplate.replace("{{COMMENT_TEXT}}", $("#commentarea").val());
		$("#commentlist").html(newcomm + $("#commentlist").html());
	}
	
	
	

	//favorite:
	
	$("#favorite").change(function (){
		$.get(
			params.favoriteUrl,
			{"onoff" : this.checked, "mcid" : params.mcid },
			function(data, status){
				if(status != "success" || !data.result){
					this.checked = favorite;
				}else{
					params.favorite = data.status;
				}
			}
		);
	});
	

	//rate:
	
	$("#rate").jqxRating({ width: 350, height: 35, theme: 'classic'});
	if(rateValue != null){
			$("#rate").val(rateValue);
	}
	$("#rate").bind('change', function(event){
			$.get(
				params.rateUrl,
				{"rateval" : event.value, "mcid" : params.mcid},
				function(data, status){
					if(status != "success" || !data.result){
						$("#rate").val(params.rateValue);
					} else {
						params.rateValue = data.state;
					}
				}
			);
	});
	
	//comment
	function load_comments(){
		$("#commentlist").load(
				params.commentsUrl,
				
				{
					"mcid": mcid
				},
				function(){
					$(".deletecomment").click(function(){
						$.get(
								params.deleteCommentUrl,
								{"commid": this.id.replace("_commid_","")},
								function(data, status){
									if(status == "success" && data.result){
										load_comments();
									}
								}
						);
						return false;
					});
				}
		);
	}
	
	load_comments();
	
	$("#addcomment").click(function(){
			$.get(
				params.addCommentUrl,
				{
					mcid: params.mcid,
					commtext: $("#commentarea").val()
				},
				function(data, status){
					if(status == "success" && data.result){
						load_comments();
					}
				}
			);

			return false;
	});
	

}