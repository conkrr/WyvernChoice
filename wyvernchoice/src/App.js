import { render } from '@testing-library/react';
import React from 'react';
import { Container, Row, Col } from 'reactstrap';
import { Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import Choice from './choice';

//Export default ensures that app
export default class App extends React.Component {
  //props auto-pass arguments

  constructor(props){
    //create variables that you'll be using
  }
  //Assumes this is a function, don't worry about it
  handleClick(e){
    e.preventDefault();
    console.log("Button has been clicked!");
  }

  //Executes this code upon clicking the submit button
  handleCreateChoiceClick(e){
    var form = document.createForm;
    var data = {};
    data["name"] = form.choiceName.value;
    data["choiceID"] = form.choiceID.value;
    //Flag if a choice is completed here
    if (form.isFinalized.checked) {  
        data["isFinalized"] = true;
        data["completionDate"] = form.completionDate.value;
    } else {
        //If choice is not complete, flag completion date as open
        data["isFinalized"] = false;
        data["completionDate"] = "Open";
    }
    //What do for lists?
    var alternativeList = {}
    //for each alternative
        //get info and push it into the array
    


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


  render(){
    return (
      <Container>
        <Row>
          <Col>
              <Label>Username:</Label>
              <Input type="email" id="usernameLabel" placeholder="Username"/>

              <Label>Password (optional):</Label>
              <Input type="password" id="passwordLabel"/>

              <Label>Choice ID:</Label>
              <Input type="email" id="choiceIDLabel"/>

              <Button onClick={handleClick}>Submit</Button>
          </Col>
          <Col>
              <Label>Create Choice</Label><br></br>

              <Label>Name of Choice:</Label>
              <Input type="email" id="choicenameLabel"/>

              <Label>Number of Choices:</Label>
              <Input type="email" id="numchoiceLabel"/>

              <Label>Alternatives:</Label>
              <Input type="email" id="alternativeLabel1"/>
              <Input type="email" id="alternativeLabel2"/>
              <Input type="email" id="alternativeLabel3"/>
              <Input type="email" id="alternativeLabel4"/>
              <Input type="email" id="alternativeLabel5"/>
              <Button>Create Choice</Button>
          </Col>
        </Row>
        <Row>
          <Col>
            <Label>List of Users:</Label>
          </Col>
          <Col>
            <Label>Choice:</Label>
          </Col>
        </Row>
      </Container>
    );
  }
  
}
