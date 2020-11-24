function processCreateChoiceResponse(result) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("result:" + result);
  
    refreshChoice();
}

function handleCreateChoiceClick(e){
    console.log("Creating choice...");
    //Form is actually a local reference to the form that was already created
    //Generate IDs on backend
    var form = document.createChoiceForm;
    var data = {};
    data["name"] = form.choiceName.value;
    data["choiceID"] = Math.random()*1000;
    data["numParticipants"] = form.participants.value;
    data["isFinalized"] = false;
    data["completionDate"] = "Open";
    
    //Make an alternative list and check to see which ones are filled. Create alternatives out of the ones that are pushed
    var alternativeList = [];
    if(form.alternative1.value !== null){
        alternativeList.push(createAlternative(form.alternative1.value));
    }

    if(form.alternative2.value !== null){
        alternativeList.push(createAlternative(form.alternative2.value));
    }

    if(form.alternative3.value !== null){
        alternativeList.push(createAlternative(form.alternative3.value));
    }

    if(form.alternative4.value !== null){
        alternativeList.push(createAlternative(form.alternative4.value));
    }

    if(form.alternative5.value !== null){
        alternativeList.push(createAlternative(form.alternative5.value));
    }

    //If there are at least two alternatives, return the list of alternatives
    if(alternativeList.length > 1){
        data["ListOfAlternatives"] = alternativeList;
    } else {
        //display element that says "not enough choices"
        console.log("Not enough choices...");
    }

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

/**
 * Create an alternative given a name. All other parameters are initialized.
 * @param {*} alternativeName the name of the alternative
 */
function createAlternative(alternativeName){
    return {name = alternativeName, alternativeID = Math.random()*1000, Approvals = 0, Disapprovals = 0, ListofFeedback = []};
}