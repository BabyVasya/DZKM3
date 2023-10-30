package org.example;

import jade.core.Agent;

public class FunctionAgent1 extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new InitiateDistributedCalculation1());
        addBehaviour(new CalcMyFunction());
    }
}
