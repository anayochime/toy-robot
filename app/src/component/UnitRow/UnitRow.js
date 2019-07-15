import React from 'react';
import Unit from '../Unit/Unit';

const unitRow = (props) => {

    const units = [];

    for(let i = 0; i < props.noOfUnits; i++){
        if(i === props.unitWithRobot){
            units.push(<Unit key={i}
                             robotLocationBackGround={props.robotLocationBackGround}
                             robotDirectionStyle={props.robotDirectionStyle}/>)
        }else{
            units.push(<Unit key={i}/>)
        }
    }

    return (
        <div className="row">
            {units}
        </div>
    );
};

export default unitRow;