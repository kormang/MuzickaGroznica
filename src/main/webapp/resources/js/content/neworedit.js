/**
 * 
 */

function initnoe(params){
	
	$("#artist").jqxInput({
	    minLength: 1,
	    theme:'bootstrap'
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
				
				var node = document.getElementById("artist");
				node.className = "form-control";
				
			}
	);
	
}