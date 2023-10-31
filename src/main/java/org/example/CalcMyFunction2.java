package org.example;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class CalcMyFunction2 extends Behaviour {
    private double d;
    private double x;
    private double y21;
    private double y22;
    private double y23;
    private String [] splitting;
    @Override
    public void action() {
        try {
            ACLMessage receiveRequest2 = getAgent().receive();
            if (receiveRequest2!=null) {
                System.out.println("Полученный запрос на расчет функции для " + myAgent.getLocalName()+ " выглядит так: " + receiveRequest2);
                splitting = receiveRequest2.getContent().split(" ");
                try {
                    d = Double.parseDouble(splitting[1]);
                    x = Double.parseDouble(splitting[0]);

                } catch (NumberFormatException e ){
                    e.printStackTrace();
                }
                y21 = Functions.agent2Function(x);
                y22 = Functions.agent2Function(x+d);
                y23 = Functions.agent2Function(x-d);
                ACLMessage responseMsg2 = new ACLMessage(ACLMessage.CONFIRM);
                responseMsg2.setContent(y21 + " " + y22 + " " + y23);
                if (receiveRequest2.getSender().getLocalName().equals("Agent1")) {
                    responseMsg2.addReceiver(new AID("Agent1", false));
                    getAgent().send(responseMsg2);
                    System.out.println("Отправка результата 2 функции агенту 1: " + responseMsg2);
                }
                if (receiveRequest2.getSender().getLocalName().equals("Agent3")) {
                    responseMsg2.addReceiver(new AID("Agent3", false));
                    getAgent().send(responseMsg2);
                    System.out.println("Отправка результата 2  агенту 3: " + responseMsg2);
                }
            }
        } catch (NullPointerException e ) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean done() {
        return false;
    }
}
