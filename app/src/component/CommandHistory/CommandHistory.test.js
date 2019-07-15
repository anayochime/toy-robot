import React from 'react';
import {configure, shallow, mount} from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import CommandHistory from './CommandHistory';
import {Button} from "reactstrap";

configure({adapter: new Adapter()});

describe('<CommandHistory/>', () => {

    let wrapper;

    beforeEach(() => {
        wrapper = shallow(<CommandHistory resetHandler={()=>{}}/>);
    });

    it('should render command history successfully', () => {
        let commandHistoryArray = ["MOVE", "REPORT=>Output:0,0,NORTH"];

        wrapper.setProps({commandHistory : commandHistoryArray});
        expect(wrapper.find('p')).toHaveLength(3);
        expect(wrapper.find('p').at(1).text()).toEqual("REPORT=>Output:0,0,NORTH");
        expect(wrapper.find('p').at(2).text()).toEqual("MOVE");
    });

    it('should not render command history if not available', () => {
        let commandHistoryArray = [];

        wrapper.setProps({commandHistory : commandHistoryArray});
        expect(wrapper.find('p')).toHaveLength(1);

       commandHistoryArray = null;

        wrapper.setProps({commandHistory : commandHistoryArray});
        expect(wrapper.find('p')).toHaveLength(1);
    });

    it('should fire resetHandler in props correctly', () =>{
        let onClickFunction_Spy = jasmine.createSpy('mockOnclickFunction');
        const wrapper = mount(<CommandHistory resetHandler={onClickFunction_Spy}/>)
        wrapper.find(Button).prop('onClick')();

        expect(onClickFunction_Spy).toHaveBeenCalled();
    })

});