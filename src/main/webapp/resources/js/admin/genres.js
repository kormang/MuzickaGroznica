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

function addGenre(){
	
	var intxt = document.getElementById("genreName");
	
	var request = getRequestObject();
	var genre = intxt.value;
	
	var msgarea = document.getElementById("msgarea");
	
	request.onreadystatechange = function () {
		if(request.readyState == 4 && request.status == 200){
			var jsonObj = JSON.parse(request.responseText);
			var genres = document.getElementById("genres").children[0];
			
			if(jsonObj.result){
				genres.innerHTML += '<li class="list-group-item">' + genre + '</li>';
			}
			
			msgarea.innerHTML = jsonObj.message;
			
		} else if (request.readyState == 4) {
			var defaulterrormsg = document.getElementById("defaulterrormsg").innerHTML;
			msgarea.innerHTML = defaulterrormsg;
		}
		
	}
	
	var address = document.getElementById("ajax_add_genre_address").innerHTML;
	
	request.open("POST", address ,true);
	
	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	request.setRequestHeader("Accept", "application/json");
	request.send("name="+genre);
	
	
}