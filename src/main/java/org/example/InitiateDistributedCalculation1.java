package org.example;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Arrays;

public class InitiateDistributedCalculation1 extends Behaviour {
    private double x;
    private double d;
    private double resultOfCalculations;

    private String [] splitting;

    @Override
    public void action() {
        if (getAgent().getLocalName().equals("Agent1")) {
            ACLMessage calcultionRequest = new ACLMessage(ACLMessage.REQUEST);
            x = Math.random();
            d = Math.random()*0.1;
            calcultionRequest.setContent(x + " " + d);
            calcultionRequest.addReceiver(new AID("Agent1", false));
            calcultionRequest.addReceiver(new AID("Agent2", false));
            calcultionRequest.addReceiver(new AID("Agent3", false));
            getAgent().send(calcultionRequest);
            System.out.println("Запрос для агентов 1,2,3 сформирован и выглядит так: " + calcultionRequest);
        }
        ACLMessage calculationResult = getAgent().receive();
        if(calculationResult != null) {
            System.out.println("результаты расчетов получены: " + calculationResult);
        }
    }

    @Override
    public boolean done() {
        return false;
    }


}
//            splitting = calcultionRequest.getContent().split(" ");
//            System.out.println("x при отправлении запроса выглядит так: " + splitting[0]);
//            System.out.println("d при отправлении запроса выглядит так: " + splitting[1]);