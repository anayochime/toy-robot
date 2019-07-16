import React from 'react';
import {Button} from "reactstrap";
import PropTypes from 'prop-types';

const commandHistory = (props) => {

    let reportHistory = null;
    if (props.commandHistory && props.commandHistory.length > 0 ) {
        const copyOfHistory = [...props.commandHistory];
        copyOfHistory.reverse();
        reportHistory = (
            <div className="row p-3 bg-secondary justify-content-center text-white">
                <div>
                    {
                        copyOfHistory.map((report, index) => {
                            return (<p key={index}>{report}</p>)
                        })
                    }
                </div>
            </div>
        )
    }

    return (
        <div style={{width: '50%',marginTop: '10px', marginLeft: 'auto', marginRight: 'auto', padding: '106px'}}>
            <div className="row p-1 mb-2 align-items-end bg-secondary text-white">
                <div className="col-10 ">
                    <p className="display-4">Command History</p>
                </div>
                <div className="col-2">
                    <Button color="danger" onClick={props.resetHandler}>Reset</Button>
                </div>
            </div>
            {reportHistory}
        </div>
    );
};

commandHistory.propTypes = {
    resetHandler: PropTypes.func.isRequired,
};

export default commandHistory;