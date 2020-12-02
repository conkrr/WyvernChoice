function processDisapprovalResponse(result){
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("processDisapprovalResponse result:" + result);
  
    const jsonObj = JSON.parse(result);
    //ID is probably bad and wrong???
	const id = jsonObj.choice.alternative.Disapproval
    requestChoice(id);
}

function handleDisapprovalClick(e){
    console.log("Registering disapproval request...");
    //Get the disapproval count?
    //let response = document.getElementById('');
    //let data = {};
    
    //If a cookie exists, 
    //force the cookie to expire and do remove disapproval request
    //else
    //Create a cookie that says the user clicked on disapproval
    if(document.cookie.split(";").some(function(item){
        return item.trim().indexOf("I clicked on Disapproval!") == 0
    })){
        //disapprovalCount += 1;
        document.cookie = "I clicked on Disapproval!=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
        removeDisapprovalRequest();
    } else {
        //disapprovalCount -= 1;
        document.cookie = "I clicked on Disapproval!"
        addDisapprovalRequest();
    }
}

function addDisapprovalRequest(){
    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", add_disapproval_url, true);

    // send the collected data as JSON
    xhr.send(js);

    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
            console.log ("XHR:" + xhr.responseText);
            processCreateResponse(xhr.responseText);
            } else {
                console.log("actual:" + xhr.responseText)
                var js = JSON.parse(xhr.responseText);
                var err = js["response"];
                alert (err);
            }
        } else {
        processDisapprovalResponse("N/A");
        }
    };
}

function removeDisapprovalRequest(){
    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", remove_disapproval_url, true);

    // send the collected data as JSON
    xhr.send(js);

    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
            console.log ("XHR:" + xhr.responseText);
            processCreateResponse(xhr.responseText);
            } else {
                console.log("actual:" + xhr.responseText)
                var js = JSON.parse(xhr.responseText);
                var err = js["response"];
                alert (err);
            }
        } else {
        processDisapprovalResponse("N/A");
        }
    };
}