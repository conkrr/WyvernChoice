import React from 'react';
import { Container, Row, Col } from 'reactstrap';
import { Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';

function App() {
  function handleClick(e){
    e.preventDefault();
    console.log("Button has been clicked!");
  }
  return (
    <Container>
      <Row>
        <Col>
            <Label>Username:</Label>
            <Input type="email" id="username"/>
        </Col>
        <Col>
            <Label>Password:</Label>
            <Input type="password" id="username"/>
        </Col>
        <Col>
            <Label>Choice ID:</Label>
            <Input type="email" id="username"/>
        </Col>
        <Col>
          <Button onClick={handleClick}>Submit</Button>
        </Col>
      </Row>
    </Container>
  );
}

export default App;
