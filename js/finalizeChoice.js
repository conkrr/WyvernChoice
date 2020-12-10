function handleFinalizeClick(e){
    if(savedIsFinalized === false){
        console.log("Input: " + e);
        let data = {};

        data["choiceID"] = savedChoiceID;
        data["alternativeID"] = savedAlternatives[e].id;
        data["isFinalized"] = true;

        document.getElementById("GreaterAlternative" + (e+1)).class = "container p-3 my-3 bg-dark text-white";

        //Change container type of the correct alternative
        //Do other things?

        var js = JSON.stringify(data);
        console.log("JS:" + js);
        var xhr = new XMLHttpRequest();
        xhr.open("POST", finalize_choice_url, true);

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
        document.getElementById("errorView").innerHTML = "Cannot finalize, choice is already finalized";
    }

}