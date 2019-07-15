import React from 'react';
import {configure, shallow} from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import UnitRow from './UnitRow';
import Unit from '../Unit/Unit';

configure({adapter: new Adapter()});

describe('<UnitRow />', () => {

    let wrapper;

    beforeEach(() => {
        wrapper = shallow(<UnitRow/>);
    });

    it('should render the given number of <Unit /> elements in noOfUnits field in props', () => {
        wrapper.setProps({noOfUnits : 5});
        expect(wrapper.find(Unit)).toHaveLength(5);

        wrapper.setProps({noOfUnits : 6});
        expect(wrapper.find(Unit)).toHaveLength(6);

        wrapper.setProps({noOfUnits : 0});
        expect(wrapper.contains(<Unit/>)).toEqual(false);

        wrapper.setProps({noOfUnits : null});
        expect(wrapper.contains(<Unit/>)).toEqual(false);
    });

    it('should render correct <Unit /> element with robot location background and orientation styling', () => {
        const robotLocationBackGroundValue = 'red';
        const robotDirectionStyleValue = 'pointingNorth';
        const unitWithRobotValue = 1;
        wrapper.setProps({noOfUnits : 5,unitWithRobot: unitWithRobotValue,
            robotLocationBackGround: robotLocationBackGroundValue
            , robotDirectionStyle: robotDirectionStyleValue});

        expect(wrapper.find(Unit).at(0).props().robotLocationBackGround).toBeUndefined();
        expect(wrapper.find(Unit).at(unitWithRobotValue).props().robotLocationBackGround).toEqual(robotLocationBackGroundValue);
        expect(wrapper.find(Unit).at(unitWithRobotValue).props().robotDirectionStyle).toEqual(robotDirectionStyleValue);

    })
});