import React from 'react';
import {configure, shallow, mount} from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import CommandForm from './CommandForm';
import {Button,Input} from "reactstrap";

configure({adapter: new Adapter()});

describe('<CommandForm/>', () => {

    let wrapper;

    beforeEach(() => {
        wrapper = shallow(<CommandForm inputChangeHandler={()=>{}} sendCommandHandler={()=>{}}/>);
    });

    it('should render error message successfully', () => {
        let errorMessageValue = "Error!!!!";

        wrapper.setProps({errorMessage : errorMessageValue});
        expect(wrapper.contains(<p className={"text-danger"}>{errorMessageValue}</p> )).toEqual(true);

        errorMessageValue = null;

        wrapper.setProps({errorMessage : errorMessageValue});
        expect(wrapper.contains(<p className={"text-danger"}>{errorMessageValue}</p> )).toEqual(false);
    });

    it('should fire commandHandler and inputChangeHandler in props correctly', () =>{
        let onClickFunction_Spy = jasmine.createSpy('OnclickFunction');
        let onChangeFunction_Spy = jasmine.createSpy('onChangeFunction');
        const wrapper = mount(<CommandForm inputChangeHandler={onClickFunction_Spy} sendCommandHandler={onChangeFunction_Spy}/>)
        wrapper.find(Input).prop('onChange')();
        wrapper.find(Button).prop('onClick')();

        expect(onClickFunction_Spy).toHaveBeenCalled();
        expect(onChangeFunction_Spy).toHaveBeenCalled();
    })
});