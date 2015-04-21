/**
 * 
 */

function initnoe(params){
	
	$("#artist").jqxInput({
	    minLength: 1,
	    theme:'energyblue'
	});

	$.get(
			params.availableArtistsUrl,
			{},
			function(data, status){
				
				if(status == "success"){
					
					$("#artist").jqxInput({
						   source: data.artists,
						   theme: "bootstrap"
					});
					
				}
				
				$("#artist").className = "form-control";
				
			}
	);
	
}