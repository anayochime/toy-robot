import React, {Component} from 'react';
import './App.css';
import axios from 'axios';
import Grid from './component/Grid/Grid';
import CommandForm from './component/CommandForm/CommandForm';
import CommandHistory from './component/CommandHistory/CommandHistory';
import {inputCommandsIsValid} from './util/validator';

class App extends Component {

    state = {
        inputCommand: null,
        commandReportHistory: [],
        latestLocation: null,
        error: null
    };

    handleInputChange = (event) => {
        if (this.state.error) {
            this.setState({inputCommand: event.target.value, error: null});
        } else {
            this.setState({inputCommand: event.target.value});
        }
    };

    errorUpdater = (errorMessage) => {
        this.setState({error: errorMessage});
    }

    handleSendCommand = () => {
        if (inputCommandsIsValid(this.state.inputCommand, this.errorUpdater)) {
            return this.doCommandSend(this.state.inputCommand);
        }
    };

    handleReset = () => {
        this.setState({commandReportHistory: []})
    };

    updateCommandOutputHistory = (content) => {
        const newCommandOutput = [...this.state.commandReportHistory, content];
        this.setState({commandReportHistory: newCommandOutput});
    };

    userCommandHandler = (issuedCommand, response) => {
        if (issuedCommand !== "") {
            if (response.data.report !== "") {
                this.updateCommandOutputHistory(issuedCommand.concat(" => ").concat(response.data.report.toString()))
            } else {
                this.updateCommandOutputHistory(issuedCommand)
            }
        }
    };

    gridDisplayCommandHandler = (response) => {
        if (response.data.report !== "") {
            const latestLocation = response.data.report;
            this.setState({latestLocation: latestLocation.replace("Output:", "")});
        }
    };

    doCommandSend = (issuedCommand) => {
        return axios.post('/toy-robot/rest/command', {input: issuedCommand}, {headers: {'Content-Type': 'application/json'}})

            .then(response => {
                this.userCommandHandler(issuedCommand, response);
                return axios.post('/toy-robot/rest/command', {input: 'REPORT'}, {headers: {'Content-Type': 'application/json'}})
            })
            .then(gridRes => {
                this.gridDisplayCommandHandler(gridRes);
            })
            .catch(error => {
                if (error.response) {
                    this.setState({error: error.response.data.debugMessage})
                } else {
                    this.setState({error: error.message})
                }
            });
    };

    render() {
        return (
            <div className="App">
                <h1 className={"display-3"}>Welcome to Toy Robot Simulator</h1>
                <h5>The graphic grid shows the result of all command except a REPORT, A single robot exists, issue a
                    command to reveal its location</h5>
                <div style={{margin: 'auto', width: '60%'}}>
                    <CommandForm errorMessage={this.state.error}
                                 inputChangeHandler={this.handleInputChange}
                                 sendCommandHandler={this.handleSendCommand}/>
                    <Grid robotLocation={this.state.latestLocation}/>
                    <div className={"container-commands"} >
                        <h3 style={{textAlign: 'center', padding: '16px'}}><strong>Commands</strong></h3>
                        <div>
                            <div className={"col-lg-4"}>
                                <img width="50" height="50" className="rounded-circle" alt="Generic placeholder"
                                     src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="/>
                                <h6><strong>PLACE X,Y,FACING</strong></h6>
                                <ul style={{textAlign: 'left'}}>
                                    <li>
                                        <p>Puts the toy robot on the table in position X,Y and facing NORTH,
                                            SOUTH, EAST or WEST.</p>
                                    </li>
                                    <li>
                                        <p>The origin (0,0) is the SOUTH WEST most corner.</p>
                                    </li>
                                    <li>
                                        <p>The first valid command to the robot is a PLACE command, after that, any
                                            sequence of commands may be issued, in any order, including another
                                            PLACE command.</p>
                                    </li>
                                    <li>
                                        <p>The application will discard all commands in the sequence until a valid
                                            PLACE command has been executed.</p>
                                    </li>
                                </ul>
                            </div>
                            <div className={"col-lg-4"}>
                                <img width="50" height="50" className="rounded-circle" alt="Generic placeholder"
                                     src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="/>
                                <h6><strong>MOVE</strong></h6>
                                <p>Moves the toy robot one unit forward in the direction it is currently facing.</p>
                            </div>
                            <div className={"col-lg-4"}>
                                <img width="50" height="50" className="rounded-circle" alt="Generic placeholder"
                                     src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="/>
                                <h6><strong>LEFT</strong></h6>
                                <p>Will rotate the robot 90° anticlockwise without changing the position of the
                                    robot.</p>
                            </div>
                            <div className={"col-lg-4"}>
                                <img width="50" height="50" className="rounded-circle" alt="Generic placeholder"
                                     src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="/>
                                <h6><strong>RIGHT</strong></h6>
                                <p>Rotate the robot 90° clockwise without changing the position of the robot.</p>
                            </div>
                            <div className={"col-lg-4"}>
                                <img width="50" height="50" className="rounded-circle" alt="Generic placeholder"
                                     src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="/>
                                <h6><strong>REPORT</strong></h6>
                                <p>Outputs the X,Y and F of the robot. This can be in any form, but standard output</p>
                            </div>

                        </div>
                    </div>
                    <CommandHistory commandHistory={this.state.commandReportHistory} resetHandler={this.handleReset}/>
                </div>
            </div>
        );
    }
}

export default App;