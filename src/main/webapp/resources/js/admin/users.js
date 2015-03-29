/**
 * 
 */

function getRequestObject() {
	if (window.XMLHttpRequest) {
		return(new XMLHttpRequest());
	} else if (window.ActiveXObject) {
		return(new ActiveXObject("Microsoft.XMLHTTP"));
	} else {
		return(null);
	}
}

function showMessage(request, el, add){
	
	var jsonObj = JSON.parse(request.responseText);
	var messagearea = document.getElementById("messagearea");
	messagearea.innerHTML += "<div>" + jsonObj.message + "</div>";
//	el.parentNode.parentNode.innerHTML += "<td>"+jsonObj.message+"</td>";
//	el.checked = add;
}

function switchRole(el, role, uid){
	var request = getRequestObject();
	
	var add = el.checked;
	
	request.onreadystatechange = function(){
		if(request.readyState == 4 && request.status == 200){
			showMessage(request, el, add);
		}
	};
	
	var address = document.getElementById("ajax_role_switch_address").innerHTML;
	
	request.open("POST", address ,true);
	
	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	request.setRequestHeader("Accept", "application/json");
	request.send("uid="+uid+"&role="+role+"&add="+add);
}