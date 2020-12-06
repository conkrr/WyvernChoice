

function requestChoiceList(result){
  processChoiceListRequest(result);
}


//Might be different due to it being a list?
function processChoiceListRequest(val){
  var xhr = new XMLHttpRequest();
  xhr.open("GET", choice_list_url, true);
  xhr.send();
  console.log("choicelistrequest sent");

  // This will process results and update HTlML as appropriate.
  xhr.onloadend = function ()
  {
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
      if (xhr.status == 200) {
        console.log ("XHR:" + xhr.responseText);
        processRequestChoiceListResponse(xhr.responseText);
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
//xhr.send(null);  //  NEED TO GET IT GOING
}


function processRequestChoiceListResponse(result){

  console.log("requested: " + result);

  var jsonObj = JSON.parse(result);
  var choiceList = document.getElementById('choiceList');

  var output = "";

  for(let i=0; i < jsonObj.listOfChoices.length; i++){
    var choiceJson = jsonObj.listOfChoices[i];

    //var cName = choiceJson.choice.name;
    //var cID = choiceJson.choice.choiceID;
   // var cFinalized = choiceJson.choice.isFinalized;
    //var cDate = choiceJson.choice.creationDate;
     var cID = choiceJson.id;
     var cFinalized = choiceJson.isFinalized;
     var cDate = choiceJson.creationDate;

    if(cFinalized === true){
      cFinalized = "Closed"
    } else {
      cFinalized = "Open";
    }

    output = output + "<div id=\"choice" + cID + "\"><b>" + cID + "</b>   <b>" + cDate + "</b>     <b>"+ cFinalized + "</b></div><br>";
  }
  //Output list of choices here
  choiceList.innerHTML = output;
};


/*
function refreshChoiceList() {
  //Make and send GET request
  var xhr = new XMLHttpRequest();
  xhr.open("GET", get_choice_url, true);
  xhr.send();
   
  console.log("sent");

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      processListResponse(xhr.responseText);
    } else {
      processListResponse("N/A");
    }
  };
}

/**
 * Respond to server JSON object.
 *
 * Replace the contents of 'choiceList' with a <br>-separated list of name,value pairs.
function processListResponse(result) {
  console.log("res:" + result);
  // Can grab any DIV or SPAN HTML element and can then manipulate its contents dynamically via javascript
  var js = JSON.parse(result);
  var choiceList = document.getElementById('choiceList');
  
  var output = "";
  for (var i = 0; i < js.list.length; i++) {
    var choiceJson = js.list[i];
    console.log(choiceJson);
    
    var cname = choiceJson["name"];
    var cval = choiceJson["id"];
    var sysvar = choiceJson["system"];

    output = output + "<div id=\"const" + cname + "\"><b>" + cname + ":</b> = " + cval + "<br></div>";

    //Not useful but could be good reference
    /*
    if (sysvar) {
    	output = output + "<div id=\"const" + cname + "\"><b>" + cname + ":</b> = " + cval + "<br></div>";
    } else {
    	output = output + "<div id=\"const" + cname + "\"><b>" + cname + ":</b> = " + cval + "(<a href='javaScript:requestDelete(\"" + cname + "\")'><img src='deleteIcon.png'></img></a>) <br></div>";
    }
    
  }

  // Update computation result
  choiceList.innerHTML = output;
}
}
*/
