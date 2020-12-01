function processApprovalResponse(result){
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("processApprovalResponse result:" + result);
  
    const jsonObj = JSON.parse(result);
    //ID is probably bad and wrong???
	const id = jsonObj.choice.alternative.Approval
    requestChoice(id);
}

function handleApprovalClick(e){
    console.log("Registering approval request...");
    let response = document.getElementById('');
    let data = {};
    let userAlreadyApproved = false;
    //for list of users registered
        //check if the user currently responding has requested 
        //if this turns out to be the case, userAlreadyApproved is true
    
    if(userAlreadyApproved === false){
        addApprovalRequest();
    } else {
        removeApprovalRequest();
    }
}

function addApprovalRequest(){
    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", add_approval_url, true);

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
        processApprovalResponse("N/A");
        }
    };
}

function removeApprovalRequest(){
    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", remove_approval_url, true);

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
        processApprovalResponse("N/A");
        }
    };
}