import { render } from '@testing-library/react';
import React from 'react';
import { Container, Row, Col } from 'reactstrap';
import { Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import Alternative from './alternative';


class Choice extends React.Component{
    constructor(props){
        this.choicename = choicename;
        this.choiceID = choiceID;
        this.completionDate = completionDate;
        this.isFinalized = isFinalized;
        this.alternatives = [];
    }
  
    render(){
      return (
        <Container>
            <Row>
                <Col>
                    <Label>{this.choicename || ""}</Label>
                </Col>
                <Col>
                    <Label>{this.choiceID || ""}</Label>
                </Col>
            </Row>
            <Row>
                <Label>{this.alternatives}</Label>
            </Row>
        </Container>

      )
  }  
}