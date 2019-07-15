import React from 'react';
import UnitRow from '../UnitRow/UnitRow';

const grid = (props) => {
    const unitRows = [];
    let xCoordinate = -1;
    let yCoordinate = -1;
    let reportArray = null;

    const glyphiconOrientation =  {
        NORTH : 'glyphicon glyphicon-arrow-up',
        EAST : 'glyphicon glyphicon-arrow-right',
        SOUTH : 'glyphicon glyphicon-arrow-down',
        WEST : 'glyphicon glyphicon-arrow-left'
    };

    if(props.robotLocation){
        reportArray = props.robotLocation.split(",");
        xCoordinate = parseInt(reportArray[0]);
        yCoordinate = parseInt(reportArray[1]);
    }

    const maxXCoordinate = 4;

    for(let i = maxXCoordinate; i >= 0; i--){
        if(i === yCoordinate ) {
            unitRows.push(<UnitRow key={i} noOfUnits={5} unitWithRobot={xCoordinate}
                                   robotLocationBackGround="bg-danger"
                                   robotDirectionStyle={glyphiconOrientation[reportArray[2]]}/>)
        }else{
            unitRows.push(<UnitRow key={i} noOfUnits={5}/>)
        }
    }

    return(
        <div>
           {unitRows}
        </div>
    );
};

export default grid;