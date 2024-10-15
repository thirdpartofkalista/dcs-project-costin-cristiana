package Enumerations;

import java.io.Serializable;

public enum TransitionOperation implements Serializable{
	Undefined,
	Add,
	Sub,
	Prod,
	Div,
	Mod,
	Move,
	Copy,
	AddElement,
	PopElementWithTarget,
	PopElementWithoutTarget,
	PopElementWithTargetToQueue,
	PopElementWithoutTargetToQueue,
	SendOverNetwork,
	SendROverNetwork,
	SendPetriNetOverNetwork,
	PopElement_R_E,
	ActivateSubPetri,
	StopPetriNet,
	MakeNull,
	
	Add_FloatFlaot,  //the new activations for floatfloat
	Sub_FloatFloat,
	Prod_FloatFloat,
	Div_FloatFloat,
	
	DynamicDelay
}
