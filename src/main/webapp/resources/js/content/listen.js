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
//	if(rateValue != null){
//			$("#rate").val(rateValue);
//	}
	$("#rate").val(params.totalRating);
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
	
//playlists
	
	$("#atpl_window").hide();
	
	$("#atpl_btn").click(
			function(){
				$("#atpl_btn").hide();
				$("#playlists").load(
						params.loadPlaylistsUrl,
						{"mcid": params.mcid},
						function(data, status){
							if(status == "success"){
								$("#atpl_window").show();
							}else{
								$("#atpl_btn").show();
							}
						}
				);
			}
	);
	
	$("#atpl_cancel").click(
			function(){
				$("#atpl_window").hide();
				$("#atpl_btn").show();
			}
	);
	
	$("#atpl_save").click(
			function(){
				reqparams = {"mcid": params.mcid};
				var plid = $("[name=playlist]:checked").val();
				reqparams.plid = plid;
				if(plid == -1){
					reqparams.pltitle = $("#npl_title").val();
				}
				$.get(
						params.addToPlaylistUrl,
						reqparams,
						function(data, status){
							if(status == "success" && data.result){
								$("#atpl_btn").show();
								$("#atpl_window").hide();
							}else{
								//TODO: error report
							}
						}
				);
			}
	);
	
	//load embed code
	$("#embedcodearea").load(
			params.embedCodeUrl,
			{mcid: params.mcid}
	);
	
	//comment
	var load_comments = function(){
		$("#commentlist").load(
				params.commentsUrl,
				{ "mcid": params.mcid },
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
	};
	
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