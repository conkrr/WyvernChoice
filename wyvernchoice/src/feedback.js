import { render } from '@testing-library/react';
import React from 'react';
import { Container, Row, Col } from 'reactstrap';
import { Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';

class Feedback extends React.Component{
    constructor(props){
        this.username = "Billy";
        this.feedbacktext = "Please help me";
        this.timestamp = "11/29/10";
    }


    render(){
        return(
            <Container>
                <Row>
                    <Col>
                    <Label>{this.username}</Label>
                    </Col>
                    <Col>
                    <Label>{this.feedbacktext}</Label>
                    </Col>
                    <Col>
                    <Label>{this.timestamp}</Label>
                    </Col>
                </Row>
            </Container>
        )
    }
}