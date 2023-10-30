package org.example;
import java.lang.Math;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Random;

public class CalcMyFunction extends Behaviour {

    private double y11;
    private double y12;
    private double y13;
    private double y21;
    private double y22;
    private double y23;
    private double y31;
    private double y32;
    private double y33;
    private double d;
    private double x;
    private String [] splitting;

    @Override
    public void action() {
        ACLMessage receiveRequest = getAgent().receive();
        if (receiveRequest != null) {
            System.out.println("Полученный запрос на расчет функции для " + myAgent.getLocalName()+ " выглядит так: " + receiveRequest);
            splitting = receiveRequest.getContent().split(" ");
            try {
                d = Double.parseDouble(splitting[1]);
                x = Double.parseDouble(splitting[0]);

            } catch (NumberFormatException e ){
                e.printStackTrace();
            }
            System.out.println("Полученные из запроса занчения: " + "d = " + d + "x = " + x);
                    y11 = -0.5*Math.pow(x, 2) - 4;
                    y12 = -0.5*Math.pow(x+d, 2) - 4;
                    y13 = -0.5*Math.pow(x-d, 2) - 4;
                    y21 = Math.pow(2, -0.1*(x));
                    y22 = Math.pow(2, -0.1*(x-d));
                    y23 = Math.pow(2, -0.1*(x+d));
                    y31 = Math.cos(x);
                    y32 = Math.cos(x-d);
                    y33 = Math.cos(x+d);

            if(myAgent.getLocalName().equals("Agent1")) {
                        ACLMessage answerAg1 = new ACLMessage(ACLMessage.CONFIRM);
                        answerAg1.addReceiver(new AID("Agent1", false));
                        answerAg1.setContent(y11 + " " + y12 + " " + y13);
                        getAgent().send(answerAg1);
                        System.out.println("Отправка результата 1 функции: " + answerAg1);
                        System.out.println("результат расчетов 1 функции: " + "y1(x) = " + y11 + "y1(x+d) = " +y12 + "y1(x-d) = " + y13);
            }

            if(myAgent.getLocalName().equals("Agent2")) {
                ACLMessage answerAg2 = new ACLMessage(ACLMessage.CONFIRM);
                answerAg2.addReceiver(new AID("Agent1", false));
                answerAg2.setContent(String.valueOf(y21) + " " + String.valueOf(y22) + " " + String.valueOf(y23));
                getAgent().send(answerAg2);
                System.out.println("Отправка результата 2 функции: " + answerAg2);
                System.out.println("результат расчетов 2 функции: " + "y2(x) = " + y21 + "y2(x+d) = " +y22 + "y2(x-d) = " + y23);
            }
            if(myAgent.getLocalName().equals("Agent3")) {
                ACLMessage answerAg3 = new ACLMessage(ACLMessage.CONFIRM);
                answerAg3.addReceiver(new AID("Agent1", false));
                answerAg3.setContent(String.valueOf(y31) + " " + String.valueOf(y32) + " " + String.valueOf(y33));
                getAgent().send(answerAg3);
                System.out.println("Отправка результата 3 функции: " + answerAg3);
                System.out.println("результат расчетов 3 функции: " + "y3(x) = " + y31 + "y3(x+d) = " +y32 + "y3(x-d) = " + y33);
            }

        }

    }

    @Override
    public boolean done() {
        return false;
    }

}
