package org.example;

import jade.core.Agent;

public class FunctionAgent1 extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new CalcMyFunction1());
        addBehaviour(new CalcMyFunction2());
        addBehaviour(new CalcMyFunction3());
        addBehaviour(new CatchInitiative());
    }
}
