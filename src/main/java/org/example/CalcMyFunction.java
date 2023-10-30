package org.example;
import java.lang.Math;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Random;

public class CalcMyFunction extends Behaviour {

    private double y1;
    private double x;
    private double y2;
    private double y3;
    private double d;
    private String [] splitting;



    @Override
    public void action() {
        ACLMessage receiveRequest = getAgent().receive();
        System.out.println("Полученный запрос на расчет функции 1 выглядит так: " + receiveRequest);
        splitting = receiveRequest.getContent().split(" ");
        try {
            d = Double.parseDouble(splitting[1]);
            x = Double.parseDouble(splitting[0]);
        } catch (NumberFormatException e ){
            e.printStackTrace();
        }
        System.out.println("Полученные из запроса занчения: " + "d = " + d + "x = " + x);
        if( receiveRequest != null) {
            System.out.println(receiveRequest);
            System.out.println("Запрос на расчет функции 1 получен");
            System.out.println(x);
            ACLMessage answerY = new ACLMessage(ACLMessage.INFORM);
            y1 = -0.5*Math.pow(x, 2) - 4;
            y2 = -0.5*Math.pow(x+d, 2) - 4;
            y3 = -0.5*Math.pow(x-d, 2) - 4;
            System.out.println("результат расчетов 1 функции: " + "y1 = " + y1 + "y2 = " +y2 + "y3 = " + y3);
            answerY.setContent(String.valueOf(y1) + " " + String.valueOf(y2) + " " + String.valueOf(y3));
            answerY.addReceiver(new AID("Agent1", false));
            if(answerY != null) {
                System.out.println("Отправка результатов расчета функции 1: " + answerY);
            }
            getAgent().send(answerY);
        }
        if(receiveRequest == null) {
            System.out.println("Чтото не так");
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
