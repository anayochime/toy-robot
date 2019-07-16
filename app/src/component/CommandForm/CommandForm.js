import React from 'react';
import {Button, Input, InputGroup, InputGroupAddon} from "reactstrap";
import PropTypes from 'prop-types';

const commandForm = (props) => {

    return (
        <div style={{width: '40%', margin: 'auto', padding: '16px'}}>
            {props.errorMessage ? <p className={"text-danger"}>{props.errorMessage}</p> : null};
            <InputGroup style={{marginBottom: '15px'}}>
                <Input type="text" placeholder="Enter Command" onChange={props.inputChangeHandler}/>
                <InputGroupAddon addonType="append">
                    <Button color="primary" onClick={props.sendCommandHandler}>Send Command</Button>
                </InputGroupAddon>
            </InputGroup>
        </div>
    );
};

commandForm.propTypes = {
    inputChangeHandler: PropTypes.func.isRequired,
    sendCommandHandler: PropTypes.func.isRequired
};

export default commandForm;