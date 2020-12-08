function processDeleteResponse(result){
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("processDeleteResponse result:" + result);

	const jsonObj = JSON.parse(result);
	const id = jsonObj.deleteDate;
    requestChoice(id);
}

function handleDeleteClick(e){
    console.log("Input: " + e);
    let data = {};
    let form = document.deleteChoicesForm;
    data["deleteDate"] = form.deleteDay.value + (form.deleteHour.value)/24;

    console.log("DeleteDate: " + data["deleteDate"]);

    //Make an HTTP request
    var js = JSON.stringify(data);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", delete_date_url, true);

    // send the collected data as JSON
    xhr.send(js);

    // This will process results and update HTML as appropriate.
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
            console.log ("XHR:" + xhr.responseText);
            processDeleteResponse(xhr.responseText);
            } else {
                console.log("actual:" + xhr.responseText)
                var js = JSON.parse(xhr.responseText);
                var err = js["response"];
                alert (err);
            }
        } else {
        processDeleteResponse("N/A");
        }
    };
}