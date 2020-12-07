function processFinalizeResponse(result){

}

function handleFinalizeClick(e){
    console.log("Input: " + e);
    let data = {};

    data["choiceID"] = savedChoiceID;

    //Not sure how to get the alternative we click in...
    //Whenever you sign into the choice, that's when the info
    //When you know it's finalized, disable a bunch of stuff
    //Check calculator example!
        //use an href tag. Do a request.
        //5 different constants, with a different trash can
        //Use deleteChoice, embed information
    data["alternativeID"] = savedAlternatives;

    //Am I missing something here?
    data["isFinalized"] = true;

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
            processFinalizeResponse(xhr.responseText);
            } else {
                console.log("actual:" + xhr.responseText)
                var js = JSON.parse(xhr.responseText);
                var err = js["response"];
                alert (err);
            }
        } else {
        processFinalizeResponse("N/A");
        }
    };
}