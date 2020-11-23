function refreshChoice() {
    //Make and send GET request
    var xhr = new XMLHttpRequest();
    xhr.open("GET", list_url, true);
    xhr.send();
     
    console.log("sent");
  
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
      if (xhr.readyState == XMLHttpRequest.DONE) {
        console.log ("XHR:" + xhr.responseText);
        processChoiceResponse(xhr.responseText);
      } else {
        processChoiceResponse("N/A");
      }
    };
}

function processChoiceResponse(result) {
    console.log("res:" + result);
    // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
    var js = JSON.parse(result);
    var choice = document.getElementById('Choice');

    var output = "";
    var cName = choice["name"];
    var cID = choice["choiceID"];
    var cParticipants = choice["numParticipants"];
    var cDate = choice["completionDate"];
    var cFinalized = choice["isFinalized"];
    //Heh transforming variables
    if(cFinalized == true){
        cFinalized = "Choice Finalized!";
    } else {
        cFinalized = "Choice is still open...";
    }

    var cAlternatives = choice["ListofAlternatives"];
    for(let i = 0; i < cAlternatives.length; i++){
        let alternativeJson = cAlternatives[i];
        console.log(alternativeJson);
        let aname = alternativeJson["name"];
        let aID = alternativeJson["alternativeID"];
        let aApprovals = alternativeJson["Approvals"];
        let aDisapprovals = alternativeJson["Disapprovals"];
        let aChosen = alternativeJson["isChosen"];
        var aFeedback = alternativeJson["ListofFeedback"];
        
        for(let j = 0; j < aFeedback.length; j++){
            let feedbackJson = aFeedback[j];
            console.log(feedbackJson);
            let fUser = feedbackJson["user"];
            let fText = feedbackJson["text"];
            let fTimestamp = feedbackJson["timestamp"];

            //Should this come from the alternative or feedback json?
            let fID = alternativeJson["alternativeID"];
        }
    }

    console.log(choice);

    //Output choice here
    output = output + "<div id=\"choiceID" + cID + "\"><b>" + cName + "</b><b>#" + cID + "</b><br>" + cFinalized + "<br>" + cAlternatives + "<br></div>";

    // Update computation result
    currentChoice.innerHTML = output;
}
