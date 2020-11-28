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
 */
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

    //Not useful but could be good reference
    /*
    if (sysvar) {
    	output = output + "<div id=\"const" + cname + "\"><b>" + cname + ":</b> = " + cval + "<br></div>";
    } else {
    	output = output + "<div id=\"const" + cname + "\"><b>" + cname + ":</b> = " + cval + "(<a href='javaScript:requestDelete(\"" + cname + "\")'><img src='deleteIcon.png'></img></a>) <br></div>";
    }
    */
  }

  // Update computation result
  choiceList.innerHTML = output;
}


