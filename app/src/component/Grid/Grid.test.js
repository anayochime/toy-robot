import React from 'react';
import {configure, shallow} from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import Grid from './Grid';
import UnitRow from "../UnitRow/UnitRow";

configure({adapter: new Adapter()});

describe('<Grid/>', () => {

    let wrapper;

    beforeEach(() => {
        wrapper = shallow(<Grid/>);
    });

    it('should render 5 UnitRows', () => {
        const robotLocationValue = "0,0,NORTH";

        wrapper.setProps({robotLocation : robotLocationValue});
        expect(wrapper.find(UnitRow)).toHaveLength(5);
    });

    it('should render UnitRow with robot location and direction styling successfully', () => {
        const robotLocationValue = "0,0,NORTH";

        wrapper.setProps({robotLocation : robotLocationValue});


        expect(wrapper.find(UnitRow).at(4).props().robotLocationBackGround).toEqual("bg-danger");
        expect(wrapper.find(UnitRow).at(4).props().unitWithRobot).toEqual(0);
        expect(wrapper.find(UnitRow).at(4).props().robotDirectionStyle).toEqual("glyphicon glyphicon-arrow-up");

        expect(wrapper.find(UnitRow).at(3).props().robotDirectionStyle).toBeUndefined()
    });

    it('should render UnitRow with robot location and direction styling successfully', () => {
        const robotLocationValueNORTH = "0,0,NORTH";
        const robotLocationValueEAST = "0,0,EAST";
        const robotLocationValueSOUTH = "0,0,SOUTH";
        const robotLocationValueWEST = "0,0,WEST";

        wrapper.setProps({robotLocation : robotLocationValueNORTH});
        expect(wrapper.find(UnitRow).at(4).props().robotDirectionStyle).toEqual("glyphicon glyphicon-arrow-up");
        wrapper.setProps({robotLocation : robotLocationValueEAST});
        expect(wrapper.find(UnitRow).at(4).props().robotDirectionStyle).toEqual("glyphicon glyphicon-arrow-right");
        wrapper.setProps({robotLocation : robotLocationValueSOUTH});
        expect(wrapper.find(UnitRow).at(4).props().robotDirectionStyle).toEqual("glyphicon glyphicon-arrow-down");
        wrapper.setProps({robotLocation : robotLocationValueWEST});
        expect(wrapper.find(UnitRow).at(4).props().robotDirectionStyle).toEqual("glyphicon glyphicon-arrow-left");
    });

    it('should not render a UnitRow with robot location and direction styling for missing robotLocation', () => {
        const robotLocationValueUndefined = null;
        const robotLocationValueBlank = "";

        wrapper.setProps({robotLocation : robotLocationValueUndefined});

        wrapper.find(UnitRow).map(unitRow => {
            expect(unitRow.props().unitWithRobot).toBeUndefined();
            expect(unitRow.props().robotDirectionStyle).toBeUndefined();
            expect(unitRow.props().robotLocationBackGround).toBeUndefined();
        });

        wrapper.setProps({robotLocation : robotLocationValueBlank});

        wrapper.find(UnitRow).map(unitRow => {
            expect(unitRow.props().unitWithRobot).toBeUndefined();
            expect(unitRow.props().robotDirectionStyle).toBeUndefined();
            expect(unitRow.props().robotLocationBackGround).toBeUndefined();
        });
    });

});