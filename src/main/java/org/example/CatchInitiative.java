package org.example;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class CatchInitiative extends Behaviour {
    @Override
    public void action() {
        ACLMessage receiveRequestQueue = getAgent().receive();
        if(receiveRequestQueue != null) {
            getAgent().addBehaviour(new InitiateDistributedCalculation1());
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
