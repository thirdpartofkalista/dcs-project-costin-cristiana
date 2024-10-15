package Project1;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataFloat;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Client {
    public static void main(String[] args) {
        PetriNet ptn = new PetriNet();
        ptn.PetriNetName = "Client";
        ptn.NetworkPort = 1080;

        DataFloat p0 = new DataFloat();
        p0.SetName("p0");
        p0.SetValue(1.0f);
        ptn.PlaceList.add(p0);

        DataFloat p1 = new DataFloat();
        p1.SetName("p1");
        ptn.PlaceList.add(p1);

        DataFloat p5 = new DataFloat();
        p5.SetName("p5");
        ptn.PlaceList.add(p5);

        DataTransfer p3 = new DataTransfer();
        p3.SetName("p3");
        p3.Value = new TransferOperation("localhost", "1081", "p1"); // Communicate with the server
        ptn.PlaceList.add(p3);

        DataFloat p6 = new DataFloat();
        p6.SetName("p6");
        ptn.PlaceList.add(p6);

        DataFloat p7 = new DataFloat();
        p7.SetName("p7");
        ptn.PlaceList.add(p7);

        // T0
        PetriTransition t0 = new PetriTransition(ptn);
        t0.TransitionName = "t0";
        t0.InputPlaceName.add("p0");

        Condition T0Ct1 = new Condition(t0, "p0", TransitionCondition.NotNull);
        GuardMapping grdT0 = new GuardMapping();
        grdT0.condition = T0Ct1;
        grdT0.Activations.add(new Activation(t0, "p0", TransitionOperation.Move, "p1"));

        t0.GuardMappingList.add(grdT0);
        t0.Delay = 0;
        ptn.Transitions.add(t0);

        // T1
        PetriTransition t1 = new PetriTransition(ptn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("p1");

        Condition T1Ct1 = new Condition(t1, "p1", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "p3", TransitionCondition.NotNull);
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;
        grdT1.Activations.add(new Activation(t1, "p5", TransitionOperation.Move, "p6"));
        grdT1.Activations.add(new Activation(t1, "p5", TransitionOperation.Move, "p0"));

        t1.GuardMappingList.add(grdT1);
        t1.Delay = 0;
        ptn.Transitions.add(t1);

        // T3
        PetriTransition t3 = new PetriTransition(ptn);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("p6");

        Condition T3Ct1 = new Condition(t3, "p6", TransitionCondition.NotNull);
        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;
        grdT3.Activations.add(new Activation(t3, "p6", TransitionOperation.Move, "p7"));

        t3.GuardMappingList.add(grdT3);
        t3.Delay = 0;
        ptn.Transitions.add(t3);

        System.out.println("Client started");
        ptn.Delay = 300;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = ptn;
        frame.setVisible(true);
    }
}
