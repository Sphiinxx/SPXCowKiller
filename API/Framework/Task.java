package scripts.SPXCowKiller.API.Framework;

import scripts.SPXCowKiller.data.Variables;

public abstract class Task {

    protected Variables vars;

    public Task(Variables v) {
        vars = v;
    }

    public abstract void execute();

    public abstract boolean validate();

}

