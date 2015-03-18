/**
 * 
 */

function validateSth(input, error, error_slot, regex){
	 var input = document.getElementById(input);
	 
	 var error_slot = document.getElementById(error_slot);
	 
	 if(! regex.test(input.value)){
		 
		 var error = document.getElementById(error);
		 error_slot.innerHTML = error.innerHTML;
		
		 return false;
	 }
	 
	 error_slot.innerHTML = "";
	 
	 return true
}

 function validateUsername(){
	 return validateSth("input_username", "username_error", "username_error_slot",/[^@#\/\\]{6,}/)

 }
 
 function validatePassword(){
	 return validateSth("input_password", "password_error", "password_error_slot", /(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}/)

 }
 
 function validateRepeatePassword(){
	 password = document.getElementById("input_password");
	 repeat_password = document.getElementById("input_repeat_password");
	 
	 repeat_password_error_slot = document.getElementById("repeat_password_error_slot");
	 
	 if(password.value != repeat_password.value){
		 repeat_password_error = document.getElementById("repeat_password_error");
		 repeat_password_error_slot.innerHTML = repeat_password_error.innerHTML;
		 return false;
	 }
	 
	 repeate_password_error_slot.innerHTML = "";
	 
	 return true;
 }

 function validateForm(){
	 
	 var ret = true;
	 
	 ret = ret && validateUsername();
	 ret = ret && validatePassword();
	 
	 if(ret){
		 ret = validateRepeatePassword();
	 }
	 
	 return ret;
	 
 }