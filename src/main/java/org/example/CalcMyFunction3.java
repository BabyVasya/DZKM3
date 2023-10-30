package org.example;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class CalcMyFunction3 extends Behaviour {
    private double y1;
    private double x;
    private double y2;
    private double y3;
    private double d;
    private String [] splitting;


    @Override
    public void action() {
        ACLMessage receiveRequest3 = getAgent().receive();
        System.out.println("Полученный запрос на расчет функции 3 выглядит так: " + receiveRequest3);
        splitting = receiveRequest3.getContent().split(" ");
        try {
            d = Double.parseDouble(splitting[1]);
            x = Double.parseDouble(splitting[0]);
        } catch (NumberFormatException e ){
            e.printStackTrace();
        }
        System.out.println("Полученные из запроса значения: " + "d = " + d + "x = " + x);
        if( receiveRequest3 != null) {
            System.out.println(receiveRequest3);
            System.out.println("Запрос на расчет функции 3 получен");
            System.out.println(x);
            ACLMessage answerY = new ACLMessage(ACLMessage.INFORM);
            y1 = Math.cos(x);
            y2 = Math.cos(x-d);
            y3 = Math.cos(x+d);
            System.out.println("результат расчетов 3 функции: " + "y1 = " + y1 + "y2 = " +y2 + "y3 = " + y3);
            answerY.setContent(String.valueOf(y1) + " " + String.valueOf(y2) + " " + String.valueOf(y3));
            answerY.addReceiver(new AID("Agent1", false));
            if(answerY != null) {
                System.out.println("Отправка результатов расчета функции 3: " + answerY);
            }
            getAgent().send(answerY);
        }
        if(receiveRequest3 == null) {
            System.out.println("Чтото не так");
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
