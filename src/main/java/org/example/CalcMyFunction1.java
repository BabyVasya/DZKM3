package org.example;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class CalcMyFunction1 extends Behaviour {
    private double d;
    private double x;
    private double y11;
    private double y12;
    private double y13;
    private String [] splitting;
    @Override
    public void action() {
         try {
             ACLMessage receiveRequest1 = getAgent().receive();
             if (receiveRequest1!=null) {
                 System.out.println("Полученный запрос на расчет функции агента " + myAgent.getLocalName()+ " выглядит так: " + receiveRequest1);
                 splitting = receiveRequest1.getContent().split(" ");
                 try {
                     d = Double.parseDouble(splitting[1]);
                     x = Double.parseDouble(splitting[0]);

                 } catch (NumberFormatException e ){
                     e.printStackTrace();
                 }
                 y11 = Functions.agent1Function(x);
                 y12 = Functions.agent1Function(x+d);
                 y13 = Functions.agent1Function(x-d);
                 ACLMessage responseMsg1 = new ACLMessage(ACLMessage.CONFIRM);
                 responseMsg1.setContent(y11 + " " + y12 + " " + y13);
                 if (receiveRequest1.getSender().getLocalName().equals("Agent2")) {
                     responseMsg1.addReceiver(new AID("Agent2", false));
                     getAgent().send(responseMsg1);
                     System.out.println("Отправка результата 1 функции агенту 2: " + responseMsg1);
                 }
                 if (receiveRequest1.getSender().getLocalName().equals("Agent3")) {
                     responseMsg1.addReceiver(new AID("Agent3", false));
                     getAgent().send(responseMsg1);
                     System.out.println("Отправка результата 1  агенту 3: " + responseMsg1);
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
