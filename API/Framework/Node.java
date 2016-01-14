package scripts.SPXCowKiller.API.Framework;

import scripts.SPXCowKiller.data.Variables;

public abstract class Node {

    protected Variables vars;

    public Node(Variables v) {
        vars = v;
    }

    public abstract void execute();

    public abstract boolean validate();

}

