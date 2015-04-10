/**
 * 
 */

function initpe(params){
	
	function appDisapp(self, approve){
		var id = self.name.replace("_eid_","");
		$.get(
				params.approvalUrl,
				{ "eid": id, "approve": approve },
				function(data, status){
					if(status == "success" && data.result){
						$("[name="+self.name+"]").hide();
					}else{
						//TODO: place appropriate message here
						alert("ERROR");
					}
					
				}
		);
	}
	
	$(".approvebutton").click(
			function(){
				appDisapp(this, true);
			}		
	);
	
	$(".disapprovebutton").click(
			function(){
				appDisapp(this, false);
			}
	);
	
}