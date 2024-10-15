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

public class Server {
    public static void main(String[] args) {
        PetriNet ptn = new PetriNet();
        ptn.PetriNetName = "Server";
        ptn.NetworkPort = 1081;

        DataFloat p1 = new DataFloat();
        p1.SetName("p1");
        ptn.PlaceList.add(p1);

        DataFloat p2 = new DataFloat();
        p2.SetName("p2");
        ptn.PlaceList.add(p2);

        DataTransfer p3 = new DataTransfer();
        p3.SetName("p3");
        p3.Value = new TransferOperation("localhost", "1080", "p2"); // Communicate with the client
        ptn.PlaceList.add(p3);

        DataFloat p0 = new DataFloat();
        p0.SetName("p0");
        p0.SetValue(1.0f);
        ptn.PlaceList.add(p0);

        // T0
        PetriTransition t0 = new PetriTransition(ptn);
        t0.TransitionName = "t0";
        t0.InputPlaceName.add("p0");

        Condition T0Ct1 = new Condition(t0, "p0", TransitionCondition.NotNull);
        Condition T0Ct2 = new Condition(t0, "p0", TransitionCondition.Equal, "p1");
        GuardMapping grdT0 = new GuardMapping();
        grdT0.condition = T0Ct1;
        grdT0.Activations.add(new Activation(t0, "p0", TransitionOperation.Move, "p2"));

        t0.GuardMappingList.add(grdT0);
        t0.Delay = 0;
        ptn.Transitions.add(t0);


        PetriTransition t1 = new PetriTransition(ptn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("p2");

        Condition T1Ct1 = new Condition(t1, "p2", TransitionCondition.NotNull);
        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;
        grdT1.Activations.add(new Activation(t1, "p3", TransitionOperation.SendOverNetwork, "p0"));

        t1.GuardMappingList.add(grdT1);
        t1.Delay = 0;
        ptn.Transitions.add(t1);

        System.out.println("Server started");
        ptn.Delay = 300;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = ptn;
        frame.setVisible(true);
    }
}
