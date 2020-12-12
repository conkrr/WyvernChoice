function processDeleteResponse(result){
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("processDeleteResponse result:" + result);

    //Maybe delete if it doesn't go anywhere?
    requestChoiceList();
}

function handleDeleteClick(e){
    console.log("Input: " + e);
    /*
    let data = {};
    
    data["deleteDate"] = form.deleteDay.value + (form.deleteHour.value)/24;
    */

    var form = document.deleteChoicesForm;

    console.log("" + (parseFloat(form.deleteDay.value) + (parseFloat(form.deleteHour.value/24))));

    processDeleteRequest(parseFloat(form.deleteDay.value) + (parseFloat(form.deleteHour.value/24)));


    // This will process results and update HTML as appropriate.
    
}



function processDeleteRequest(deleteDate){
    var xhr = new XMLHttpRequest();
    xhr.open("POST", delete_date_url + "/" + deleteDate, true);

    xhr.onloadend = function ()
    {
        console.log(xhr);
        console.log("request:  " + xhr.request);
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                console.log ("XHR:" + xhr.responseText);
                processDeleteResponse(xhr.responseText);
            } else {
                console.log("actual:" + xhr.responseText)
                var js = JSON.parse(xhr.responseText);
                var err = js.error;
                alert (err);
            }
        } else {
            processDeleteResponse("N/A");
        }
    };

  xhr.send(null);  //  NEED TO GET IT GOING
}