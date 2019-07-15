import React from 'react';
import {configure, shallow} from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import Unit from './Unit';

configure({adapter: new Adapter()});

describe('<Unit />', () => {

    let wrapper;

    beforeEach(() => {
        wrapper = shallow(<Unit/>);
    });

    it('should style unit if robotDirectionStyle is available', () => {
        const robotLocationBackGroundValue = 'red';
        const robotDirectionStyleValue = 'pointingNorth';

        wrapper.setProps({robotDirectionStyle : robotDirectionStyleValue, robotLocationBackGround: robotLocationBackGroundValue});

        expect(wrapper.contains(<div className={"col-sm bg-secondary ".concat(robotLocationBackGroundValue)}
                                     style={{height: '20px', border: '2px solid #3e3e3e', marginRight: '1px'}}>
            <span className={robotDirectionStyleValue}/></div>)).toEqual(true);

        expect(wrapper.contains(<span className={robotDirectionStyleValue}/>)).toEqual(true);

    });

    it('should not style unit if robotDirectionStyle is not available', () => {
        const robotLocationBackGroundValue = 'red';
        const robotDirectionStyleValue = 'pointingNorth';

        expect(wrapper.contains(<div className={"col-sm bg-secondary ".concat(robotLocationBackGroundValue)}
                                     style={{height: '20px', border: '2px solid #3e3e3e', marginRight: '1px'}}>
            <span className={robotDirectionStyleValue}/></div>)).toEqual(false);

        expect(wrapper.contains(<span className={robotDirectionStyleValue}/>)).toEqual(false);
    });

});