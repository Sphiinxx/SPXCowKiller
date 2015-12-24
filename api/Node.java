package scripts.SPXCowKiller.api;

import scripts.SPXCowKiller.Variables;

public abstract class Node {

    protected Variables vars;
    public Node(Variables v) {
        vars = v;
    }
    public abstract void execute();
    public abstract boolean validate();

}
