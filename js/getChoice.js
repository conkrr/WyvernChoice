

function processRequestChoiceResponse(result) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("requested: " + result);

    //refreshConstantsList();

	var jsonObj = JSON.parse(result);
    var choice = document.getElementById('currentChoice');

    var output = "";
    var cName = jsonObj.choice.name;
    var cID = jsonObj.choice.choiceID;
    var cParticipants = jsonObj.choice.numParticipants;
    var cDate = jsonObj.choice.completionDate;
    var cFinalized = jsonObj.choice.isFinalized;

      //Heh transforming variables

    console.log("js = " + jsonObj);
    console.log("cID = " + cID);
    console.log("cName = " + cName);

    if(cFinalized == true){
        cFinalized = "Choice Finalized!";
    } else {
        cFinalized = "Still open for discussion.";
    }

    var cAlternatives = choice["listofAlternatives"];

    /*
    output = output + "<div id=\"choice" + cName + "\">"+ cName + "<br> ID:" + cID + "<br>" + cFinalized + "<br></div>";

    var cAlternatives = jsonObj.choice.listofAlternatives;

    console.log("cAlternatives = " + cAlternatives);

    output = output + "<div id=\"alternatives\"> Alternatives: <br></div>";
    
    for(let i = 0; i < cAlternatives.length; i++){
        let alternativeJson = cAlternatives[i];
        console.log("alt " + i + +"   " + alternativeJson);
        output = output + "<div id=\"alternativeID" + alternativeJson.id + "\"><b>" +  alternativeJson.description + "</b><div id> " + alternativeJson.Approvals + "</b><b>" + alternativeJson.Disapprovals + "</b><br>";
    }
    
    output = output + "</div>"
    console.log(choice);
    */

    /////////////////////////////
    //Initialize Choice Headers//
    /////////////////////////////
    document.getElementById("NameOfChoice").innerHTML = cName;
    document.getElementById("IDOfChoice").innerHTML = cID;
    document.getElementById("ChoiceStatus").innerHTML = cFinalized;

    /////////////////////////////
    //Initialize Choice Headers//
    /////////////////////////////
    let alternativeJson;
    if(cAlternatives[0] !== null){
        alternativeJson = cAlternatives[0];
        document.getElementById("alternative1name").innerHTML = alternativeJson.description;
        document.getElementById("alternative1approvalcount").innerHTML = alternativeJson.Approvals.approvalCount;
        //Deal with list of users

        document.getElementById("alternative1disapprovalcount").innerHTML = alternativeJson.Disapprovals.disapprovalCount;
        //Deal with list of users
    }

    if(cAlternatives[1] !== null){
        alternativeJson = cAlternatives[1];
        document.getElementById("alternative2name").innerHTML = alternativeJson.description;
        document.getElementById("alternative2approvalcount").innerHTML = alternativeJson.Approvals.approvalCount;
        //Deal with list of users

        document.getElementById("alternative2disapprovalcount").innerHTML = alternativeJson.Disapprovals.disapprovalCount;
        //Deal with list of users

    }

    if(cAlternatives[2] !== null){
        alternativeJson = cAlternatives[2];
        document.getElementById("alternative3name").innerHTML = alternativeJson.description;
        document.getElementById("alternative3approvalcount").innerHTML = alternativeJson.Approvals.approvalCount;
        //Deal with list of users

        document.getElementById("alternative3disapprovalcount").innerHTML = alternativeJson.Disapprovals.disapprovalCount;
        //Deal with list of users
    }

    if(cAlternatives[3] !== null){
        alternativeJson = cAlternatives[3];
        document.getElementById("alternative4name").innerHTML = alternativeJson.description;
        document.getElementById("alternative4approvalcount").innerHTML = alternativeJson.Approvals.approvalCount;
        //Deal with list of users

        document.getElementById("alternative4disapprovalcount").innerHTML = alternativeJson.Disapprovals.disapprovalCount;
        //Deal with list of users
    }

    if(cAlternatives[4] !== null){
        alternativeJson = cAlternatives[4];
        document.getElementById("alternative5name").innerHTML = alternativeJson.description;
        document.getElementById("alternative5approvalcount").innerHTML = alternativeJson.Approvals.approvalCount;
        //Deal with list of users

        document.getElementById("alternative5disapprovalcount").innerHTML = alternativeJson.Disapprovals.disapprovalCount;
        //Deal with list of users
    }

    document.getElementById("currentChoice").style.visibility = visible;

    // Update computation result
    currentChoice.innerHTML = output;

}

function requestChoice(val) { 
    //called by create choice -> entry point
    processChoiceRequest (val);
}


function processChoiceRequest (val){
  var xhr = new XMLHttpRequest();
  xhr.open("GET", get_choice_url + "/" + val, true);  // Can't be DELETE b/c of preflight CORS error with non-simple requests

  // This will process results and update HTML as appropriate.
  xhr.onloadend = function ()
  {
	  console.log(xhr);
	  console.log(xhr.request);
	  if (xhr.readyState == XMLHttpRequest.DONE) {
		  if (xhr.status == 200) {
			  console.log ("XHR:" + xhr.responseText);
			  processRequestChoiceResponse(xhr.responseText);
		  } else {
			  console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["error"];
			  alert (err);
		  }
	  } else {
		  processDeleteResponse("N/A");
	  }
  };

  xhr.send(null);  //  NEED TO GET IT GOING
}

//Reference

/*
function processChoiceResponse(result) {
    console.log("res:" + result);
    // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
    var js = JSON.parse(result);
    var choice = document.getElementById('currentChoice');

    var output = "";
    var cName = js["name"];
    var cID = js["choiceID"];
    var cParticipants = js["numParticipants"];
    var cDate = js["completionDate"];
    var cFinalized = js["isFinalized"];
    //Heh transforming variables
    if(cFinalized == true){
        cFinalized = "Choice Finalized!";
    } else {
        cFinalized = "Choice is still open...";
    }


    //var cAlternatives = choice["listofAlternatives"];

    output = output + "<div id=\"choiceID" + cID + "\"><b>" + cName + "</b><b>#" + cID + "</b><br>" + cFinalized + "<br>";
    /*
    for(let i = 0; i < cAlternatives.length; i++){
        let alternativeJson = cAlternatives[i];
        console.log(alternativeJson);
        //let aname = alternativeJson["name"];
        //let aID = alternativeJson["alternativeID"];

        output = output + "<div id=\"alternativeID" + aID + "\"><b>" + aname + "</b>";

       // output = output + "<div id=\"alternativeID" + aID + "\"><b>" + aname + "</b>";
        /*
        let aApprovals = alternativeJson["Approvals"];
        let aDisapprovals = alternativeJson["Disapprovals"];
        let aChosen = alternativeJson["isChosen"];
        var aFeedback = alternativeJson["ListofFeedback"];
        output = output + "<div id=\"alternativeID" + aID +"\"><b>" + aname + "</b><b>" + aApprovals + "</b><b>" + aDisapprovals + "</b><b>" + aChosen;

        for(let j = 0; j < aFeedback.length; j++){
            let feedbackJson = aFeedback[j];
            console.log(feedbackJson);
            let fUser = feedbackJson["user"];
            let fText = feedbackJson["text"];
            let fTimestamp = feedbackJson["timestamp"];
            let faID = feedbackJson["alternativeID"];
            let fID = feedbackJson["feedbackID"];

            output = output + "<div id=\"feedbackID" + fID + "\"><b>" + fUser + "</b><b>" + fText + "</b><b>" + fTimestamp;
        }
    }


    output = output + "</div>"
    console.log(choice);

    // Update computation result
    currentChoice.innerHTML = output;



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
}
*/
