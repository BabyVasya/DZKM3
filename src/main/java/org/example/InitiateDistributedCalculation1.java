package org.example;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class InitiateDistributedCalculation1 extends OneShotBehaviour {
    private double x;
    private double d;
    private double resultOfCalculations;

    private String [] splitting;

    @Override
    public void action() {
        ACLMessage calcultionRequest = new ACLMessage(ACLMessage.REQUEST);
        x = Math.random();
        d = Math.random()*0.1;
        calcultionRequest.setContent(x + " " + d);
        System.out.println("Запрос на начальной стадии выглядит так: " + calcultionRequest);
        calcultionRequest.addReceiver(new AID("Agent1", false));
        calcultionRequest.addReceiver(new AID("Agent2", false));
        calcultionRequest.addReceiver(new AID("Agent3", false));
        splitting = calcultionRequest.getContent().split(" ");
        System.out.println("X при отправлении запроса выглядит так" + splitting[0]);
        System.out.println("D при отправлении запроса выглядит так" + splitting[1]);
        getAgent().send(calcultionRequest);
        if (calcultionRequest.getContent() != null) {
            System.out.println("запрос на расчеты функций 1, 2, 3 не пуст и в процессе отправки" + calcultionRequest);
        }

//        ACLMessage calculationResult = getAgent().receive();
//        if(calculationResult != null) {
//            System.out.println("результаты расчетов получены: " + calculationResult);
//            try {
//                resultOfCalculations = Double.parseDouble(String.valueOf(calculationResult));
//            } catch (NumberFormatException e ) {
//                e.printStackTrace();
//            }
//        }
    }


}
