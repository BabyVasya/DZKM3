package org.example;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Arrays;

public class InitiateDistributedCalculation1 extends Behaviour {
    private double x;
    private double d;
    private double y11;
    private double y12;
    private double y13;
    private double y21;
    private double y22;
    private double y23;
    private double y31;
    private double y32;
    private double y33;;
    private double extremum1;
    private double extremum2;
    private double extremum3;
    private double sumOfExtremum;
    private double resultOfCalculations;

    private String [] splitting1;
    private String [] splitting2;
    private String [] splitting3;

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
        if (calculationResult != null) {
            if(calculationResult != null && calculationResult.getSender().getLocalName().equals("Agent1")) {
                System.out.println("результаты расчетов получены от " + calculationResult.getSender().getLocalName() + " " +calculationResult);
                splitting1 = calculationResult.getContent().split(" ");
                y11 = Double.parseDouble(splitting1[0]);
                y12 = Double.parseDouble(splitting1[1]);
                y13 = Double.parseDouble(splitting1[2]);
                extremum1 = Math.max(y11, Math.max(y12, y13));
                System.out.println("Экстремум 1 " + extremum1);
            }
            if(calculationResult != null && calculationResult.getSender().getLocalName().equals("Agent2")) {
                System.out.println("результаты расчетов получены от " + calculationResult.getSender().getLocalName() + " " +calculationResult);
                splitting2 = calculationResult.getContent().split(" ");
                y21 = Double.parseDouble(splitting2[0]);
                y22 = Double.parseDouble(splitting2[1]);
                y23 = Double.parseDouble(splitting2[2]);
                extremum2 = Math.max(y21, Math.max(y22, y23));
                System.out.println("Экстремум 2 " + extremum2);
            }
            if(calculationResult != null && calculationResult.getSender().getLocalName().equals("Agent3")) {
                System.out.println("результаты расчетов получены от " + calculationResult.getSender().getLocalName() + " " +calculationResult);
                splitting3 = calculationResult.getContent().split(" ");
                y31 = Double.parseDouble(splitting3[0]);
                y32 = Double.parseDouble(splitting3[1]);
                y33 = Double.parseDouble(splitting3[2]);
                extremum3 = Math.max(y31, Math.max(y32, y33));
                System.out.println("Экстремум 3 " + extremum3);
            }

            sumOfExtremum = extremum1 + extremum2 + extremum3;
            System.out.println(sumOfExtremum);

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