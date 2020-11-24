import { render } from '@testing-library/react';
import React from 'react';
import { Container, Row, Col } from 'reactstrap';
import { Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';
import Feedback from './feedback';

class Alternative extends React.Component{
    constructor(props){
        this.alternativename = alternativename;
        this.approvals = approvals;
        this.disapprovals = disapprovals;
        this.isChosen = isChosen;
        this.feedback = [];
    }

    render(){
        return(
            <Container>
                <Row>
                    <Col>
                        <Label>{this.alternativename}</Label>
                    </Col>
                    <Col>
                        <Label>{this.approvals}</Label>
                    </Col>
                    <Col>
                        <Label>{this.disapprovals}</Label>
                    </Col>
                    <Col>
                        <Button>Finalize</Button>
                    </Col>
                </Row>
                <Row>
                    <Label>{this.feedback}</Label>
                    <Button>Add Feedback</Button>
                </Row>
                
            </Container>
        )
    }
}