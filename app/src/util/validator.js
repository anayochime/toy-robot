export const inputCommandsIsValid = (issuedCommand, errorUpdater) => {

    if (!issuedCommand) {
        errorUpdater("Command cannot be empty");
        return false;
    } else if (issuedCommand.trim().length === 0) {
        errorUpdater("Command cannot be empty");
        return false;
    }
    issuedCommand = " ".concat(issuedCommand);
    const commandPattern = /(?<![,]\s*)(\s+[A-z]{4,})/g;
    let match = commandPattern.exec(issuedCommand);
    let matchCount = 0;
    while (match != null) {
        matchCount++;
        if (match[0].trim().toUpperCase() === "PLACE") {
            if (!validatePLACEArgs(commandPattern.lastIndex, issuedCommand, errorUpdater)) {
                return false
            }
        } else if (!validCommand(match[0], errorUpdater)) {
            return false
        }

        match = commandPattern.exec(issuedCommand);
    }

    if (matchCount > 0) {
        return true
    }
    errorUpdater(`Provided [${issuedCommand.slice(1)}] command is invalid`);
    return false;
};

const validCommand = (matchedCommand, errorUpdater) => {
    const validCommands = ["PLACE", "MOVE", "LEFT", "RIGHT", "REPORT"];
    if (!validCommands.includes(matchedCommand.trim())) {
        errorUpdater(`${matchedCommand.trim()} is Invalid, supported commands are: ${validCommands.join(" ")}`);
        return false
    }
    return true
};

const validatePLACEArgs = (indexToStartSearch, searchString, errorUpdater) => {
    const validDirection = ["NORTH", "EAST", "WEST", "SOUTH"];
    const stringsToMatch = searchString.slice(indexToStartSearch);
    const argPattern = /(^\s*\d\s*,\s*\d\s*,\s*[A-Z]{3,5})/;
    const match = argPattern.exec(stringsToMatch)
    if (match != null) {
        const splitElements = match[0].split(",");
        if (splitElements.length !== 3) {
            errorUpdater(`PLACE command args [${match[0].trim()}] are incomplete`);
            return false;
        }
        const direction = splitElements[2];
        if (!validDirection.includes(direction.trim())) {
            errorUpdater(`Facing [${direction.trim()}] in PLACE command is invalid, Supported facings are: ${validDirection.join(" ")}`);
            return false
        }
        return true;
    }
    errorUpdater(`PLACE command args are invalid`);
    return false
};
