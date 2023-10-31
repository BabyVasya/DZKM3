package org.example;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class CalcMyFunction3 extends Behaviour {
    private double d;
    private double x;
    private double y31;
    private double y32;
    private double y33;
    private String [] splitting;
    @Override
    public void action() {
        try {
            ACLMessage receiveRequest3 = getAgent().receive();
            if(receiveRequest3!= null) {
                System.out.println("Полученный запрос на расчет функции для " + myAgent.getLocalName()+ " выглядит так: " + receiveRequest3);
                splitting = receiveRequest3.getContent().split(" ");
                try {
                    d = Double.parseDouble(splitting[1]);
                    x = Double.parseDouble(splitting[0]);

                } catch (NumberFormatException e ){
                    e.printStackTrace();
                }
                y31 = Functions.agent3Function(x);
                y32 = Functions.agent3Function(x+d);
                y33 = Functions.agent3Function(x-d);
                ACLMessage responseMsg3 = new ACLMessage(ACLMessage.CONFIRM);
                responseMsg3.setContent(y31 + " " + y32 + " " + y33);
                if (receiveRequest3.getSender().getLocalName().equals("Agent1")) {
                    responseMsg3.addReceiver(new AID("Agent1", false));
                    getAgent().send(responseMsg3);
                    System.out.println("Отправка результата 3 функции агенту 1: " + responseMsg3);
                }
                if (receiveRequest3.getSender().getLocalName().equals("Agent2")) {
                    responseMsg3.addReceiver(new AID("Agent2", false));
                    getAgent().send(responseMsg3);
                    System.out.println("Отправка результата 3  агенту 2: " + responseMsg3);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
