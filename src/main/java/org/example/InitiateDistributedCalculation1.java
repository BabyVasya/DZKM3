package org.example;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Arrays;

public class InitiateDistributedCalculation1 extends Behaviour {
    private double x = Math.random();
    private double d = Math.random()*0.01;
    private double y11;
    private double y12;
    private double y13;
    private double y21;
    private double y22;
    private double y23;
    private double y31;
    private double y32;
    private double y33;;
    private double extremum;
    private double sumOfExtremum1;
    private double sumOfExtremum2;
    private double sumOfExtremum3;
    private double resultOfCalculations;
    private String maxArgs;

    private String [] splitting1;
    private String [] splitting2;
    private String [] splitting3;
    private String maxCase1;
    private String maxCase2;
    private String maxCase3;

    @Override
    public void action() {
        while (d > 0.0001) {
            System.out.println("Начало итерации: d = " + d);
            ACLMessage calculationRequest2 = new ACLMessage(ACLMessage.REQUEST);
            ACLMessage calculationRequest3 = new ACLMessage(ACLMessage.REQUEST);
            calculationRequest2.setContent(x + " " + d);
            calculationRequest2.addReceiver(new AID("Agent2", false));
            calculationRequest3.setContent(x + " " + d);
            calculationRequest3.addReceiver(new AID("Agent3", false));
            getAgent().send(calculationRequest2);
            getAgent().send(calculationRequest3);
            System.out.println("Запрос для агента 2 сформирован и выглядит так: " + calculationRequest2);
            System.out.println("Запрос для агента 3 сформирован и выглядит так: " + calculationRequest3);

            if (myAgent.getLocalName().equals("Agent1")) {
                y11 = Functions.agent1Function(x);
                y12 = Functions.agent1Function(x - d);
                y13 = Functions.agent1Function(x + d);
                System.out.println("Agent1 ведет расчеты у себя, значит инициатор " + myAgent.getLocalName());
            }
            if (myAgent.getLocalName().equals("Agent2")) {
                y21 = Functions.agent2Function(x);
                y22 = Functions.agent2Function(x - d);
                y23 = Functions.agent2Function(x + d);
                System.out.println("Agent2 ведет расчеты у себя, значит инициатор " + myAgent.getLocalName());
            }
            if (myAgent.getLocalName().equals("Agent3")) {
                y31 = Functions.agent3Function(x);
                y32 = Functions.agent3Function(x - d);
                y33 = Functions.agent3Function(x + d);
                System.out.println("Agent3 ведет расчеты у себя, значит инициатор " + myAgent.getLocalName());
            }

            ACLMessage calculationResult = getAgent().receive();

            if (calculationResult != null) {
                if (calculationResult != null && calculationResult.getSender().getLocalName().equals("Agent2")) {
                    System.out.println("результаты расчетов получены от " + calculationResult.getSender().getLocalName() + " " + calculationResult);
                    splitting2 = calculationResult.getContent().split(" ");
                    y21 = Double.parseDouble(splitting2[0]);
                    y22 = Double.parseDouble(splitting2[1]);
                    y23 = Double.parseDouble(splitting2[2]);
                }
                if (calculationResult != null && calculationResult.getSender().getLocalName().equals("Agent3")) {
                    System.out.println("результаты расчетов получены от " + calculationResult.getSender().getLocalName() + " " + calculationResult);
                    splitting3 = calculationResult.getContent().split(" ");
                    y31 = Double.parseDouble(splitting3[0]);
                    y32 = Double.parseDouble(splitting3[1]);
                    y33 = Double.parseDouble(splitting3[2]);
                }
                sumOfExtremum1 = y11 + y21 + y31;
                sumOfExtremum2 = y12 + y22 + y32;
                sumOfExtremum3 = y13 + y23 + y33;
                extremum = Math.max(sumOfExtremum1, Math.max(sumOfExtremum2, sumOfExtremum3));
                if (extremum == sumOfExtremum1) {
                    maxArgs = "x";
                    System.out.println("Максимальная сумма экстремумов на первой итерации: " + extremum + " при значении аргумента " + maxArgs);
                }
                if (extremum == sumOfExtremum2) {
                    maxArgs = "x+d";
                    System.out.println("Максимальная сумма экстремумов на первой итерации: " + extremum + " при значении аргумента " + maxArgs);
                }
                if (extremum == sumOfExtremum3) {
                    maxArgs = "x-d";
                    System.out.println("Максимальная сумма экстремумов на первой итерации: " + extremum + " при значении аргумента " + maxArgs);
                }

                if(maxArgs.equals("x-d") || maxArgs.equals("x+d")) {
                    ACLMessage nextAgentQueue = new ACLMessage(ACLMessage.INFORM);
                    if(nextAgentQueue.getSender().getLocalName().equals("Agent1")) {
                        nextAgentQueue.addReceiver(new AID("Agent2", false));
                        if(maxArgs.equals("x-d")) {
                            x = x - d;
                            nextAgentQueue.setContent(x + " " + d);
                            getAgent().send(nextAgentQueue);
                        }
                        if(maxArgs.equals("x+d")) {
                            x = x + d;
                            nextAgentQueue.setContent(x + " " + d);
                            getAgent().send(nextAgentQueue);
                        }
                    }
                    if(nextAgentQueue.getSender().getLocalName().equals("Agent2")) {
                        nextAgentQueue.addReceiver(new AID("Agent3", false));
                        if(maxArgs.equals("x-d")) {
                            x = x - d;
                            nextAgentQueue.setContent(x + " " + d);
                            getAgent().send(nextAgentQueue);
                        }
                        if(maxArgs.equals("x+d")) {
                            x = x + d;
                            nextAgentQueue.setContent(x + " " + d);
                            getAgent().send(nextAgentQueue);
                        }
                    }
                    if(nextAgentQueue.getSender().getLocalName().equals("Agent3")) {
                        nextAgentQueue.addReceiver(new AID("Agent1", false));
                        if(maxArgs.equals("x-d")) {
                            x = x - d;
                            nextAgentQueue.setContent(x + " " + d);
                            getAgent().send(nextAgentQueue);
                        }
                        if(maxArgs.equals("x+d")) {
                            x = x + d;
                            nextAgentQueue.setContent(x + " " + d);
                            getAgent().send(nextAgentQueue);
                        }
                    }
                }
                if(maxArgs.equals("x")) {
                    ACLMessage nextIterationWithCurrentAgent = new ACLMessage(ACLMessage.INFORM);
                    d = d/2;
                    nextIterationWithCurrentAgent.setContent(x + " " + d);
                    getAgent().send(nextIterationWithCurrentAgent);
                }

            }
        }


    }
    @Override
    public boolean done() {
        return false;
    }


}
