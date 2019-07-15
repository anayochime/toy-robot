import React from 'react';

const unit = (props) => {
    return (
    <div className={"col-sm bg-secondary " + props.robotLocationBackGround} style={{height: '20px', border: '2px solid #3e3e3e', marginRight: '1px'}}>
        {props.robotDirectionStyle ? <span className={props.robotDirectionStyle}/> : <span/>}
        </div>
    );
};

export default unit;