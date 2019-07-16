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
          <div style={{margin: 'auto', width: '30%', padding: '16px'}}>
            <CommandForm errorMessage={this.state.error}
                         inputChangeHandler={this.handleInputChange}
                         sendCommandHandler={this.handleSendCommand}/>
            <Grid robotLocation={this.state.latestLocation}/>
            <CommandHistory commandHistory={this.state.commandReportHistory} resetHandler={this.handleReset}/>
          </div>
        </div>
    );
  }
}

export default App;