package com.company.toyrobotsimulator.rule;

import com.company.toyrobotsimulator.model.command.Command;
import com.company.toyrobotsimulator.model.grid.GridItem;

public interface Rule {
    /**
     * Takes a command and executes it based on the requirements of the rule. A decision and result are returned.
     * A rule might decide not to take any action if the given command does not relate to it.
     * A rule can return a decision of CONTINUE or END. CONTINUE instructs that other rules can attempt to run while an END
     * signifies that no other rule should execute
     * @param command A command to be executed in the rule
     * @param gridItem An item that the command can act on based on the requirements on the rule
     * @return
     */
    RuleResult execute(Command command, GridItem gridItem);
}
