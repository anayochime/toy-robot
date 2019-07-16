import {inputCommandsIsValid} from './validator';


describe('Validator', () => {
    let command;
    let spy_errorUpdater;

    beforeEach(() => {
        spy_errorUpdater = jest.fn();
    });

    it('should validate input command successfully', () => {
        command = "PLACE 0 , 0 ,  NORTH LEFT PLACE 0, 0,  NORTH RIGHT MOVE";
        expect(inputCommandsIsValid(command, spy_errorUpdater)).toEqual(true);
        expect(spy_errorUpdater).toHaveBeenCalledTimes(0);
    });

    it('should fail validation for missing facing', () => {
        command = "PLACE 0 , 0 , LEFT PLACE 0, 0,  NORTH RIGHT MOVE";
        expect(inputCommandsIsValid(command, spy_errorUpdater)).toEqual(false);
        expect(spy_errorUpdater).toHaveBeenCalledTimes(1);
        expect(spy_errorUpdater).toHaveBeenCalledWith("Facing [LEFT] in PLACE command is invalid, Supported facings are: NORTH EAST WEST SOUTH");
    });

    it('should fail validation for invalid PLACE args', () => {
        command = "PLACE 0 ,  NORTH PLACE 0, 0,  NORTH RIGHT MOVE";
        expect(inputCommandsIsValid(command, spy_errorUpdater)).toEqual(false);
        expect(spy_errorUpdater).toHaveBeenCalledTimes(1);
        expect(spy_errorUpdater).toHaveBeenCalledWith("PLACE command args are invalid");
    });

    it('should fail validation for wrong command', () => {
        command = "PLACE 0 , 0 , NORTH LEFTTT PLACE 0, 0,  NORTH RIGHT MOVE";
        expect(inputCommandsIsValid(command, spy_errorUpdater)).toEqual(false);
        expect(spy_errorUpdater).toHaveBeenCalledTimes(1);
        expect(spy_errorUpdater).toHaveBeenCalledWith("LEFTTT is Invalid, supported commands are: PLACE MOVE LEFT RIGHT REPORT");
    });

    it('should fail validation for invalid lowercase command', () => {
        command = "REPORT rep LEFT";
        expect(inputCommandsIsValid(command, spy_errorUpdater)).toEqual(false);
        expect(spy_errorUpdater).toHaveBeenCalledTimes(1);
        expect(spy_errorUpdater).toHaveBeenCalledWith("rep is Invalid, supported commands are: PLACE MOVE LEFT RIGHT REPORT");
    });

    it('should fail validation for non word character', () => {
        command = ",,,,,,";
        expect(inputCommandsIsValid(command, spy_errorUpdater)).toEqual(false);
        expect(spy_errorUpdater).toHaveBeenCalledTimes(1);
        expect(spy_errorUpdater).toHaveBeenCalledWith("Provided [,,,,,,] command is invalid");
    });

    it('should fail validation for a sequence of input without a valid command', () => {
        command = "ABC EFG UVXSYZ";
        expect(inputCommandsIsValid(command, spy_errorUpdater)).toEqual(false);
        expect(spy_errorUpdater).toHaveBeenCalledTimes(1);
        expect(spy_errorUpdater).toHaveBeenCalledWith("ABC is Invalid, supported commands are: PLACE MOVE LEFT RIGHT REPORT");
    });

    it('should fail validation for empty string', () => {
        command = "    ";
        expect(inputCommandsIsValid(command, spy_errorUpdater)).toEqual(false);
        expect(spy_errorUpdater).toHaveBeenCalledTimes(1);
        expect(spy_errorUpdater).toHaveBeenCalledWith("Command cannot be empty");
    });
    it('should fail validation for null', () => {
        command = null;
        expect(inputCommandsIsValid(command, spy_errorUpdater)).toEqual(false);
        expect(spy_errorUpdater).toHaveBeenCalledTimes(1);
        expect(spy_errorUpdater).toHaveBeenCalledWith("Command cannot be empty");
    });
});
