import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

import {configure, shallow} from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import CommandForm from './component/CommandForm/CommandForm'
import CommandHistory from './component/CommandHistory/CommandHistory'
import axios from 'axios'

configure({adapter: new Adapter()});

jest.mock('axios');

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<App/>, div);
  ReactDOM.unmountComponentAtNode(div);
});

describe('<App/>', () => {

  let wrapper;
  beforeEach(() => {
    wrapper = shallow(<App/>);

  });

  it('should update userCommandHistory after sending command', () => {
    const payload = {report: 'Output:0,0,NORTH'};
    const resp = {data: payload};
    wrapper.setState({inputCommand: "REPORT"});

    axios.post.mockResolvedValue(resp);


    const sendCommandPromise = wrapper.find(CommandForm).prop('sendCommandHandler')();

    return sendCommandPromise.then(
        () => {
          expect(wrapper.state(['commandReportHistory'])[0]).toEqual("REPORT => Output:0,0,NORTH");
        }
    ).catch((error) => {
      fail(error.message)
    });
  });

  it('should clear errors and updates input command state with new change', () => {
    const event = {target: {value: "M",}};
    const errorMessage = "Error occurred";

    wrapper.setState({inputCommand: null});
    wrapper.find(CommandForm).prop('inputChangeHandler')(event);
    expect(wrapper.state(['inputCommand'])).toEqual("M");

    wrapper.setState({inputCommand: null, error : errorMessage});

    expect(wrapper.state(['error'])).toEqual(errorMessage);
    wrapper.find(CommandForm).prop('inputChangeHandler')(event);

    expect(wrapper.state(['inputCommand'])).toEqual("M");
    expect(wrapper.state(['error'])).toBeNull()
  });

  it('should clear command history', () => {

    wrapper.setState({commandReportHistory: ["MOVE", "LEFT"]});

    expect(wrapper.state(['commandReportHistory'])).toHaveLength(2);

    wrapper.find(CommandHistory).prop('resetHandler')();
    expect(wrapper.state(['commandReportHistory'])).toHaveLength(0);
  });

  it('should update commandHistory state with new user command send', () => {
    let response = {data:{report: "Output:0,0,NORTH"}};

    const spy = jest.spyOn(wrapper.instance(), 'userCommandHandler');
    wrapper.instance().forceUpdate();

    spy("REPORT", response);
    expect(wrapper.state(['commandReportHistory'])).toHaveLength(1);
    expect(wrapper.state(['commandReportHistory'])[0]).toEqual("REPORT => Output:0,0,NORTH");

    response = {data:{report: ""}};;
    spy("MOVE", response);
    expect(wrapper.state(['commandReportHistory'])).toHaveLength(2);
    expect(wrapper.state(['commandReportHistory'])[1]).toEqual("MOVE");
  })

  it('should update latestLocation with report output if available', () => {
    let response = {data:{report: "Output:0,0,NORTH"}};
    wrapper.setState({latestLocation: "1,1,EAST"});

    const spy = jest.spyOn(wrapper.instance(), 'gridDisplayCommandHandler');
    wrapper.instance().forceUpdate();

    spy(response);
    expect(wrapper.state(['latestLocation'])).toEqual("0,0,NORTH");

    response = {data:{report: ""}};
    spy(response);
    expect(wrapper.state(['latestLocation'])).toEqual("0,0,NORTH");
  });




  it('should send command from handleSendCommand', () => {

    wrapper.setState({inputCommand: "Invalid command"});
    const spy_handleSendCommand = jest.spyOn(wrapper.instance(), 'handleSendCommand');
    const spy_doCommandSend = jest.spyOn(wrapper.instance(), 'doCommandSend');

    wrapper.instance().forceUpdate();
    spy_handleSendCommand();

    expect(spy_doCommandSend).toHaveBeenCalledTimes(0)


    wrapper.setState({inputCommand: "REPORT"});
    spy_handleSendCommand();

    expect(spy_doCommandSend).toHaveBeenCalledTimes(1)
  })
});