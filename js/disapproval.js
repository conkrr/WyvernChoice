function handleDisapprovalClick(e){
    if(savedIsFinalized === false){
        console.log(" " + e);
        var data = {};
        data["disapprovingUser"] = savedUserName;
        data["disapprovingUserID"] = savedUserId;
        data["alternativeID"] = savedAlternatives[e].id;
        data["choiceID"] = savedChoiceID;

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
                processRefreshChoice(xhr.responseText);
                } else {
                    console.log("actual:" + xhr.responseText)
                    var js = JSON.parse(xhr.responseText);
                    var err = js["response"];
                    alert (err);
                }
            } else {

            }
        };
    } else {
        document.getElementById("errorView").innerHTML = "Cannot disapprove, choice is already finalized";
    }
    
}