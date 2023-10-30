package org.example;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class CalcMyFunction2 extends Behaviour {
    private double y1;
    private double x;
    private double y2;
    private double y3;
    private double d;
    private String [] splitting;



    @Override
    public void action() {
        ACLMessage receiveRequest2 = getAgent().receive();
        System.out.println("Полученный запрос на расчет функции 2 выглядит так: " + receiveRequest2);
        splitting = receiveRequest2.getContent().split(" ");
        try {
            d = Double.parseDouble(splitting[1]);
            x = Double.parseDouble(splitting[0]);
        } catch (NumberFormatException e ){
            e.printStackTrace();
        }
        System.out.println("Полученные из запроса занчения: " + "d = " + d + "x = " + x);
        if( receiveRequest2 != null) {
            System.out.println(receiveRequest2);
            System.out.println("Запрос на расчет функции 2 получен");
            System.out.println(x);
            ACLMessage answerY = new ACLMessage(ACLMessage.INFORM);
            y1 = Math.pow(2, -0.1*(x));
            y2 = Math.pow(2, -0.1*(x-d));
            y3 = Math.pow(2, -0.1*(x+d));
            System.out.println("результат расчетов 2 функции: " + "y1 = " + y1 + "y2 = " +y2 + "y3 = " + y3);
            answerY.setContent(String.valueOf(y1) + " " + String.valueOf(y2) + " " + String.valueOf(y3));
            answerY.addReceiver(new AID("Agent1", false));
            if(answerY != null) {
                System.out.println("Отправка результатов расчета функции 2: " + answerY);
            }
            getAgent().send(answerY);
        }
        if(receiveRequest2 == null) {
            System.out.println("Чтото не так");
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
