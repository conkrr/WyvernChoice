/*
* Respond to a user's attempt to register
*/

function processRegisterResponse(username, password){
    //Grab any div or span element
    //manipulate contents dynamically via Javascript
    console.log("Username: " + username);
    console.log("Password: " + password);

    let user = JSON.parse(username);
    let pw = JSON.parse(password);
 
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
        console.log("XHR: " + xhr);
        console.log("XHR Request: " + xhr.request);

        if(xhr.readyState == XMLHttpRequest.DONE){
            console.log ("XHR response text:" + xhr.responseText);
            processRegisterResponse
        }
    }
}