/*
* Respond to a user's attempt to register
*/
var savedUserId = "";
var savedUserName = "";
function processRegisterResponse(result){
    //Grab any div or span element
    //manipulate contents dynamically via Javascript
  //  console.log("Username: " + username);
    //console.log("Password: " + password);
    const jsonObj = JSON.parse(result);

     console.log("jSON username: " + jsonObj.username + "jSON password: " + jsonObj.password + "jSON choiceiD: "+ jsonObj.choiceID);
    
	  savedUserName = jsonObj.userID;
    savedUserId = jsonObj.userID;
    
    document.getElementById("errorView").innerHTML = savedUserName + " registered for the choice!";

    requestChoice(jsonObj.choiceID);
}

function handleRegisterClick(e){
    console.log("Registering user...");
    var form = document.registerUserForm;
    var username = form.username.value;
    var password = form.password.value;
    var choiceID = form.registerID.value;

    var data = {};
    data["username"] = username;
    if(password !== null){
        data["password"] = password;
    }
    data["choiceID"] = choiceID;;

    var js = JSON.stringify(data);
    console.log("JS: " + js);

    //Send POST request
    var xhr = new XMLHttpRequest();
    xhr.open("POST", register_user_url, true);
    xhr.send(js);

    //This processes results and updates HTML as appropriat
    xhr.onloadend = function(){
        console.log(xhr);
	  console.log(xhr.request);
	  if (xhr.readyState == XMLHttpRequest.DONE) {
		  if (xhr.status == 200) {
              console.log ("XHR:" + xhr.responseText);
              var js = JSON.parse(xhr.responseText);
			  var err = js.error;
              if(js.status == 422){
                document.getElementById("errorView").innerHTML = err;
              } else{
                processRegisterResponse(xhr.responseText);
              }

			  
		  } else {
			  console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js.error;
			  alert (err);
		  }
	  } else {
		//   processDeleteResponse("N/A");
	  }
            
        
    }
}
