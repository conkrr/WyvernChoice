let data = {};
let currentUserID = ;

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
    //Get the approval count?
    //let response = document.getElementById('');
    
    data["approvingUser"] = 
    data["approvingUserID"] = 
    
    //store user data in approval
    

    //return a user id
    //If a cookie exists, 
    //force the cookie to expire and do remove approval request
    //else
    //Create a cookie that says the user clicked on approval
    if(document.cookie.split(";").some(function(item){
        return item.trim().indexOf("I clicked on Approval!") == 0
    })){
        //approvalCount += 1;
        document.cookie = "I clicked on Approval!=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
        removeApprovalRequest();
    } else {
        //approvalCount -= 1;
        document.cookie = "I clicked on Approval!"
        addApprovalRequest();
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