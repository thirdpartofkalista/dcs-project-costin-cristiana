package FloatFloatExample;

import java.util.ArrayList;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;

import DataObjects.DataFloatFloat;
import DataOnly.FloatFloat;

import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class FloatFloatExample {
	public static void main(String[] args) {

		PetriNet pn = new PetriNet();
		pn.PetriNetName = "TestFloatFloat";
		pn.NetworkPort = 1080;

		DataFloatFloat p1 = new DataFloatFloat();
		p1.SetName("p1");
		p1.SetValue(new FloatFloat(1.0f, 1.0f));
		pn.PlaceList.add(p1);

		DataFloatFloat p2 = new DataFloatFloat();
		p2.SetName("p2");
		pn.PlaceList.add(p2);
		// ----------Constant value to increment p1------------
		DataFloatFloat Const = new DataFloatFloat();
		Const.SetName("Const");
		Const.SetValue(new FloatFloat(2.0f, 2.0f));
		pn.ConstantPlaceList.add(Const);

		// T1 ------------------------------------------------
		PetriTransition t1 = new PetriTransition(pn);
		t1.TransitionName = "t1";
		t1.InputPlaceName.add("p1");

		Condition T1Ct1 = new Condition(t1, "p1", TransitionCondition.NotNull);

		GuardMapping grdT1 = new GuardMapping();
		grdT1.condition = T1Ct1;

		ArrayList<String> IsInput = new ArrayList<String>();
		IsInput.add("p1");
		IsInput.add("Const");

		grdT1.Activations.add(new Activation(t1, IsInput, TransitionOperation.Add_FloatFlaot, "p2"));

		t1.GuardMappingList.add(grdT1);
		t1.Delay = 0;
		pn.Transitions.add(t1);

		// T2---------------------------------------------------------
		PetriTransition t2 = new PetriTransition(pn);
		t2.TransitionName = "t2";
		t2.InputPlaceName.add("p2");

		Condition T2Ct1 = new Condition(t2, "p2", TransitionCondition.NotNull);

		GuardMapping grdT2 = new GuardMapping();
		grdT2.condition = T2Ct1;

		grdT2.Activations.add(new Activation(t2, "p2", TransitionOperation.Move, "p1"));

		t2.GuardMappingList.add(grdT2);
		t2.Delay = 0;
		pn.Transitions.add(t2);

		System.out.println("Exp2 started \n ------------------------------");
		pn.Delay = 2000;

		PetriNetWindow frame = new PetriNetWindow(false);
		frame.petriNet = pn;
		frame.setVisible(true);
	}
}
