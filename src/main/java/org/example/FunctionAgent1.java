package org.example;

import jade.core.Agent;

public class FunctionAgent1 extends Agent {
    @Override
    protected void setup() {

        addBehaviour(new CalcMyFunction());
        addBehaviour(new InitiateDistributedCalculation1());
    }
}
