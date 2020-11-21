function processCreateChoiceResponse(result) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("result:" + result);
  
    refreshChoiceList();
}

function handleCreateClick(e){
    var form = document.createForm;
    var data = {};
    data["name"] = form.choiceName.value;
    data["choiceID"] = form.choiceID.value;
    //Flag if a choice is completed here
    if (form.isFinalized.checked) {  
        data["isFinalized"] = true;
        data["completionDate"] = form.completionDate.value;
    } else {
        //If choice is not complete, flag completion date as open
        data["isFinalized"] = false;
        data["completionDate"] = "Open";
    }
    //What do for lists?

    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", create_url, true);

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
        processCreateResponse("N/A");
        }
    };
}