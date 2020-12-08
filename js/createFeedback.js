function processCreateFeedbackResponse(result){
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("processCreateFeedbackResponse result:" + result);

	const jsonObj = JSON.parse(result);
	const id = jsonObj.feedbackID
    requestFeedback(id);
}

function handleCreateFeedbackClick(e){ 
    if(savedIsFinalized === false){
        console.log("Creating feedback...");

        var form1 = document.createFeedbackForm1;
        var data = {};
        data["user"] = savedUserName;
        data["text"] = form1.feedback1.value;
        data["alternativeID"] = savedAlternatives[e];

        var js = JSON.stringify(data);
        console.log("JS:" + js);
        var xhr = new XMLHttpRequest();
        xhr.open("POST", create_feedback_url, true);

        // send the collected data as JSON
        xhr.send(js);

        // This will process results and update HTML as appropriate. 
        xhr.onloadend = function () {
            console.log(xhr);
            console.log(xhr.request);
            if (xhr.readyState == XMLHttpRequest.DONE) {
                if (xhr.status == 200) {
                console.log ("XHR:" + xhr.responseText);
                processRefreshChoice(xhr.responseText);
                } else {
                    console.log("actual:" + xhr.responseText)
                    var js = JSON.parse(xhr.responseText);
                    var err = js["response"];
                    document.getElementById("errorView").innerHTML = "Dang I'm impressed, feedback was messed up?";
                }
            } else {
                
            }
        };
    } else {
        document.getElementById("errorView").innerHTML = "Cannot add feedback, choice is already finalized";
    }
    
}