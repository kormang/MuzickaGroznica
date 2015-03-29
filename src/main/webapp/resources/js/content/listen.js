/**
 * 
 */

function initcl(params){

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
	
}