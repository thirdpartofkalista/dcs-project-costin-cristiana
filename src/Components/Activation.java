package Components;

import java.io.Serializable;
import java.util.ArrayList;

import DataObjects.DataCar;
import DataObjects.DataCarQueue;
import DataOnly.CarQueue;
import DataOnly.FloatFloat;
import DataOnly.RELQueue;
import DataObjects.DataFloat;
import DataObjects.DataFloatFloat;
import DataObjects.DataInteger;
import DataObjects.DataREL;
import DataObjects.DataRELQueue;
import DataObjects.DataString;
import DataObjects.DataTransfer;
import DataObjects.DataSubPetriNet;
import Enumerations.TransitionOperation;
import Interfaces.PetriObject;
import Utilities.Functions;

public class Activation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PetriTransition Parent;

	public String InputPlaceName;
	public ArrayList<String> InputPlaceNames;
	public String OutputPlaceName;
	public ArrayList<String> OutputPlaceNames;
	public TransitionOperation Operation;
	public Functions util;

	public Activation(PetriTransition Parent) {
		util = new Functions();
	}

	public Activation(PetriTransition Parent, String InputPlaceName, TransitionOperation Condition,
			String OutputPlaceName) {
		util = new Functions();
		this.Parent = Parent;
		this.InputPlaceName = InputPlaceName;
		this.OutputPlaceName = OutputPlaceName;
		this.Operation = Condition;
	}

	public Activation(PetriTransition Parent, ArrayList<String> InputPlaceNames, TransitionOperation Condition,
			String OutputPlaceName) {
		util = new Functions();
		this.Parent = Parent;
		this.InputPlaceNames = InputPlaceNames;
		this.OutputPlaceName = OutputPlaceName;
		this.Operation = Condition;
	}

	public Activation(PetriTransition Parent, String InputPlaceName, TransitionOperation Condition,
			ArrayList<String> OutputPlaceNames) {
		util = new Functions();
		this.Parent = Parent;
		this.InputPlaceName = InputPlaceName;
		this.OutputPlaceNames = OutputPlaceNames;
		this.Operation = Condition;
	}

	
	public void Activate() throws CloneNotSupportedException {

		if (Operation == TransitionOperation.Move)
			Move();

		if (Operation == TransitionOperation.Copy)
			Copy();

		if (Operation == TransitionOperation.Add)
			Add();

		if (Operation == TransitionOperation.Prod)
			Prod();

		if (Operation == TransitionOperation.Sub)
			Sub();

		if (Operation == TransitionOperation.Div)
			Div();

		if (Operation == TransitionOperation.AddElement)
			AddElement();

		if (Operation == TransitionOperation.PopElementWithTarget)
			PopElementWithTarget();

		if (Operation == TransitionOperation.PopElement_R_E)
			PopElement_R_E();

		if (Operation == TransitionOperation.DynamicDelay)
			DynamicDelay();

		if (Operation == TransitionOperation.PopElementWithTargetToQueue)
			PopElementWithTargetToQueue();

		if (Operation == TransitionOperation.PopElementWithoutTarget)
			PopElementWithoutTarget();

		if (Operation == TransitionOperation.PopElementWithoutTargetToQueue)
			PopElementWithoutTargetToQueue();

		if (Operation == TransitionOperation.SendOverNetwork)
			SendOverNetwork();

		if (Operation == TransitionOperation.SendROverNetwork)
			SendROverNetwork();

		if (Operation == TransitionOperation.SendPetriNetOverNetwork)
			SendPetriNetOverNetwork();

		if (Operation == TransitionOperation.ActivateSubPetri)
			ActivateSubPetri();

		if (Operation == TransitionOperation.StopPetriNet)
			Parent.Parent.Stop();

		if (Operation == TransitionOperation.MakeNull)
			MakeNull();
		// -------------FloatFloat Modifications--------------------
		if (Operation == TransitionOperation.Add_FloatFlaot)
			Add_FloatFlaot();
		if (Operation == TransitionOperation.Sub_FloatFloat)
			Sub_FloatFlaot();
		if (Operation == TransitionOperation.Prod_FloatFloat)
			Prod_FloatFloat();
		if (Operation == TransitionOperation.Div_FloatFloat)
			Div_FloatFlaot();
		// ---------------------------------------------------------
	}

	private void MakeNull() throws CloneNotSupportedException {
		PetriObject temp = util.GetFromListByName(OutputPlaceName, Parent.Parent.PlaceList);
		if (temp == null) {
			util.SetNullToListByName(OutputPlaceName, Parent.Parent.ConstantPlaceList);
		} else {
			util.SetNullToListByName(OutputPlaceName, Parent.Parent.PlaceList);
		}
	}

	private void Move() throws CloneNotSupportedException {

		PetriObject temp = util.GetFromListByName(InputPlaceName, Parent.TempMarking);
		if (temp == null) {
			temp = util.GetFromListByName(InputPlaceName, Parent.Parent.ConstantPlaceList);
		}
		PetriObject result = null;

		if (temp instanceof DataFloat) {
			result = (PetriObject) ((DataFloat) temp).clone();
		}

		if (temp instanceof DataInteger) {
			result = (PetriObject) ((DataInteger) temp).clone();
		}

		if (temp instanceof DataString) {
			result = (PetriObject) ((DataString) temp).clone();
		}

		if (temp instanceof DataCar) {
			result = (PetriObject) ((DataCar) temp).clone();
		}

		if (temp instanceof DataSubPetriNet) {
			result = (PetriObject) ((DataSubPetriNet) temp).clone();
		}

		// --------------------DataFloatFloat modification--------------
		if (temp instanceof DataFloatFloat) {
			result = (PetriObject) ((DataFloatFloat) temp).clone();
		}
		// -------------------------------------------------------------

		if (result == null) {
			return;
		}

		if (OutputPlaceName.contains("-")) {
			result.SetName(OutputPlaceName.split("-")[1]);
		} else {
			result.SetName(OutputPlaceName);
		}

		result.SetValue(temp.GetValue());

		util.SetToListByName(OutputPlaceName, Parent.Parent.PlaceList, result);
	}

	private void Copy() throws CloneNotSupportedException {

		PetriObject temp = util.GetFromListByName(InputPlaceName, Parent.TempMarking);
		if (temp == null) {
			temp = util.GetFromListByName(InputPlaceName, Parent.Parent.ConstantPlaceList);
		}
		PetriObject result = null;
		PetriObject resultBack = null;

		if (temp instanceof DataFloat) {
			result = (PetriObject) ((DataFloat) temp).clone();
			resultBack = (PetriObject) ((DataFloat) temp).clone();
		}

		if (temp instanceof DataInteger) {
			result = (PetriObject) ((DataInteger) temp).clone();
			resultBack = (PetriObject) ((DataInteger) temp).clone();
		}

		if (temp instanceof DataString) {
			result = (PetriObject) ((DataString) temp).clone();
			resultBack = (PetriObject) ((DataString) temp).clone();
		}

		if (temp instanceof DataCar) {
			result = (PetriObject) ((DataCar) temp).clone();
			resultBack = (PetriObject) ((DataCar) temp).clone();
		}

		if (temp instanceof DataSubPetriNet) {
			result = (PetriObject) ((DataSubPetriNet) temp).clone();
			resultBack = (PetriObject) ((DataSubPetriNet) temp).clone();
		}

		if (result == null) {
			return;
		}

		if (OutputPlaceName.contains("-")) {
			result.SetName(OutputPlaceName.split("-")[1]);
		} else {
			result.SetName(OutputPlaceName);
		}

		result.SetValue(temp.GetValue());

		util.SetToListByName(OutputPlaceName, Parent.Parent.PlaceList, result);

		if (InputPlaceName.contains("-")) {

		} else {
			util.SetToListByName(InputPlaceName, Parent.Parent.PlaceList, resultBack);
		}
	}

	private void Add() throws CloneNotSupportedException {
		Integer outputIndex = util.GetIndexByName(OutputPlaceName, Parent.Parent.PlaceList);
		PetriObject result = null;

		for (String placeName : InputPlaceNames) {
			PetriObject temp;
			Integer inputIndex = util.GetIndexByName(placeName, Parent.TempMarking);
			if (inputIndex == -1) {
				temp = util.GetFromListByName(placeName, Parent.Parent.ConstantPlaceList);
			} else {
				temp = Parent.TempMarking.get(inputIndex);
			}

			if (temp == null) {
				continue;
			}

			if (temp instanceof DataFloat) {
				if (result == null) {
					result = (PetriObject) ((DataFloat) temp).clone();
				} else {
					result.SetValue((float) result.GetValue() + (float) temp.GetValue());
				}
			}

			if (temp instanceof DataInteger) {
				if (result == null) {
					result = (PetriObject) ((DataInteger) temp).clone();
				} else {
					result.SetValue((Integer) result.GetValue() + (Integer) temp.GetValue());
				}
			}
		}
		result.SetName(OutputPlaceName);
		Parent.Parent.PlaceList.set(outputIndex, result);
	}

	private void Prod() throws CloneNotSupportedException {
		Integer outputIndex = util.GetIndexByName(OutputPlaceName, Parent.Parent.PlaceList);
		PetriObject result = null;

		for (String placeName : InputPlaceNames) {
			PetriObject temp;
			Integer inputIndex = util.GetIndexByName(placeName, Parent.TempMarking);
			if (inputIndex == -1) {
				temp = util.GetFromListByName(placeName, Parent.Parent.ConstantPlaceList);
			} else {
				temp = Parent.TempMarking.get(inputIndex);
			}

			if (temp == null) {
				continue;
			}

//			Integer inputIndex = util.GetIndexByName(placeName, Parent.TempMarking);
//			if (inputIndex == -1)
//				continue;
//
//			PetriObject temp = Parent.TempMarking.get(inputIndex);

			if (temp instanceof DataFloat) {
				if (result == null) {
					result = (PetriObject) ((DataFloat) temp).clone();
				} else {
					result.SetValue((float) result.GetValue() * (float) temp.GetValue());

				}
			}

			if (temp instanceof DataInteger) {
				if (result == null) {
					result = (PetriObject) ((DataInteger) temp).clone();
				} else {
					result.SetValue((Integer) result.GetValue() * (Integer) temp.GetValue());
				}
			}
		}
		result.SetName(OutputPlaceName);
		Parent.Parent.PlaceList.set(outputIndex, result);
	}

	private void Sub() throws CloneNotSupportedException {
		Integer outputIndex = util.GetIndexByName(OutputPlaceName, Parent.Parent.PlaceList);
		PetriObject result = null;

		for (String placeName : InputPlaceNames) {
			PetriObject temp;
			Integer inputIndex = util.GetIndexByName(placeName, Parent.TempMarking);
			if (inputIndex == -1) {
				temp = util.GetFromListByName(placeName, Parent.Parent.ConstantPlaceList);
			} else {
				temp = Parent.TempMarking.get(inputIndex);
			}

			if (temp == null) {
				continue;
			}

			if (temp instanceof DataFloat) {
				if (result == null) {
					result = (PetriObject) ((DataFloat) temp).clone();
				} else {
					result.SetValue((float) result.GetValue() - (float) temp.GetValue());
				}
			}

			if (temp instanceof DataInteger) {
				if (result == null) {
					result = (PetriObject) ((DataInteger) temp).clone();
				} else {
					result.SetValue((Integer) result.GetValue() - (Integer) temp.GetValue());
				}
			}
		}
		result.SetName(OutputPlaceName);
		Parent.Parent.PlaceList.set(outputIndex, result);
	}

	private void Div() throws CloneNotSupportedException {
		Integer outputIndex = util.GetIndexByName(OutputPlaceName, Parent.Parent.PlaceList);
		PetriObject result = null;

		for (String placeName : InputPlaceNames) {
			PetriObject temp;
			Integer inputIndex = util.GetIndexByName(placeName, Parent.TempMarking);
			if (inputIndex == -1) {
				temp = util.GetFromListByName(placeName, Parent.Parent.ConstantPlaceList);
			} else {
				temp = Parent.TempMarking.get(inputIndex);
			}

			if (temp == null) {
				continue;
			}

//			Integer inputIndex = util.GetIndexByName(placeName, Parent.TempMarking);
//			if (inputIndex == -1)
//				continue;
//
//			PetriObject temp = Parent.TempMarking.get(inputIndex);

			if (temp instanceof DataFloat) {
				if (result == null) {
					result = (PetriObject) ((DataFloat) temp).clone();
				}
				float current = (float) result.GetValue();
				if (current == 0) {
					result.SetValue((float) temp.GetValue());
				} else {
					result.SetValue((float) result.GetValue() / (float) temp.GetValue());
				}
			}

			if (temp instanceof DataInteger) {
				if (result == null) {
					result = (PetriObject) ((DataInteger) temp).clone();
				}
				Integer current = (Integer) result.GetValue();
				if (current == 0) {
					result.SetValue((Integer) temp.GetValue());
				} else {
					result.SetValue((Integer) result.GetValue() / (Integer) temp.GetValue());
				}
			}
		}
		result.SetName(OutputPlaceName);
		Parent.Parent.PlaceList.set(outputIndex, result);
	}

	private void AddElement() throws CloneNotSupportedException {
		Integer outputIndex = util.GetIndexByName(OutputPlaceName, Parent.Parent.PlaceList);
		Integer inputIndex = util.GetIndexByName(InputPlaceName, Parent.TempMarking);

		PetriObject temp = Parent.TempMarking.get(inputIndex);

		PetriObject result = null;

		if (temp instanceof DataCar) {
			result = (PetriObject) ((DataCar) temp).clone();
		}

		if (temp instanceof DataREL) {
			result = (PetriObject) ((DataREL) temp).clone();
		}

		result.SetName(OutputPlaceName);
		result.SetValue(temp.GetValue());

		Parent.Parent.PlaceList.get(outputIndex).AddElement(result);
	}

	private void PopElementWithTarget() throws CloneNotSupportedException {
		Integer outputIndex = util.GetIndexByName(OutputPlaceName, Parent.Parent.PlaceList);
		Integer inputIndex = util.GetIndexByName(InputPlaceName, Parent.Parent.PlaceList);
		PetriObject temp = ((CarQueue) ((DataCarQueue) Parent.Parent.PlaceList.get(inputIndex)).GetValue())
				.PopCar(Parent.TransitionName);

		PetriObject result = null;

		if (temp instanceof DataCar) {
			result = (PetriObject) ((DataCar) temp).clone();
		}

		result.SetName(OutputPlaceName);
		result.SetValue(temp.GetValue());

		Parent.Parent.PlaceList.set(outputIndex, result);
	}

	private void PopElement_R_E() throws CloneNotSupportedException {

		Integer outputIndexR = util.GetIndexByName(OutputPlaceNames.get(0), Parent.Parent.PlaceList);
		Integer outputIndexE = util.GetIndexByName(OutputPlaceNames.get(1), Parent.Parent.PlaceList);
		Integer inputIndex = util.GetIndexByName(InputPlaceName, Parent.Parent.PlaceList);

		DataREL temp = ((RELQueue) ((DataRELQueue) Parent.Parent.PlaceList.get(inputIndex)).GetValue()).PopREL();

		PetriObject resultR = util.GetPetriObjectByName(OutputPlaceNames.get(0), Parent.Parent.PlaceList);
		PetriObject resultE = util.GetPetriObjectByName(OutputPlaceNames.get(1), Parent.Parent.PlaceList);

		if (temp != null) {
			resultR.SetValue(temp.Value.R);
			resultE.SetValue(temp.Value.E);

			Parent.Delay = temp.Value.E;
			Parent.Parent.PlaceList.set(outputIndexR, resultR);
			Parent.Parent.PlaceList.set(outputIndexE, resultE);
		}
	}

	private void PopElementWithTargetToQueue() throws CloneNotSupportedException {

		Integer outputIndex = util.GetIndexByName(OutputPlaceName, Parent.Parent.PlaceList);
		Integer inputIndex = util.GetIndexByName(InputPlaceName, Parent.Parent.PlaceList);
		PetriObject temp = ((CarQueue) ((DataCarQueue) Parent.Parent.PlaceList.get(inputIndex)).GetValue())
				.PopCar(Parent.TransitionName);

		PetriObject result = null;

		if (temp instanceof DataCar) {
			result = (PetriObject) ((DataCar) temp).clone();
		}

		result.SetName(OutputPlaceName);
		result.SetValue(temp.GetValue());

		DataCarQueue out = (DataCarQueue) (Parent.Parent.PlaceList.get(outputIndex));
		out.AddElement(result);
	}

	private void PopElementWithoutTarget() throws CloneNotSupportedException {

		Integer outputIndex = util.GetIndexByName(OutputPlaceName, Parent.Parent.PlaceList);
		Integer inputIndex = util.GetIndexByName(InputPlaceName, Parent.Parent.PlaceList);
		PetriObject temp = ((CarQueue) ((DataCarQueue) Parent.Parent.PlaceList.get(inputIndex)).GetValue())
				.PopCartWithoutTarget();

		PetriObject result = null;

		if (temp == null)
			return;

		if (temp instanceof DataCar) {
			result = (PetriObject) ((DataCar) temp).clone();
		}

		result.SetName(OutputPlaceName);
		result.SetValue(temp.GetValue());

		Parent.Parent.PlaceList.set(outputIndex, result);
	}

	private void PopElementWithoutTargetToQueue() throws CloneNotSupportedException {
		Integer outputIndex = util.GetIndexByName(OutputPlaceName, Parent.Parent.PlaceList);
		Integer inputIndex = util.GetIndexByName(InputPlaceName, Parent.Parent.PlaceList);
		PetriObject temp = ((CarQueue) ((DataCarQueue) Parent.Parent.PlaceList.get(inputIndex)).GetValue())
				.PopCartWithoutTarget();

		PetriObject result = null;

		if (temp == null)
			return;

		if (temp instanceof DataCar) {
			result = (PetriObject) ((DataCar) temp).clone();
		}

		result.SetName(OutputPlaceName);
		result.SetValue(temp.GetValue());

		DataCarQueue out = (DataCarQueue) (Parent.Parent.PlaceList.get(outputIndex));
		out.AddElement(result);
	}

	private void SendOverNetwork() throws CloneNotSupportedException {
		PetriObject output = util.GetPetriObjectByName(OutputPlaceName, Parent.Parent.PlaceList);

		PetriObject temp;
		Integer inputIndex = util.GetIndexByName(InputPlaceName, Parent.TempMarking);
		if (inputIndex == -1) {
			temp = util.GetFromListByName(InputPlaceName, Parent.Parent.ConstantPlaceList);
		} else {
			temp = Parent.TempMarking.get(inputIndex);
		}

		if (temp == null) {
			return;
		}
		PetriObject result = null;
		if (output instanceof DataTransfer) {
			result = (PetriObject) ((DataTransfer) output).clone();
		}
//		Integer inputIndex = util.GetIndexByName(InputPlaceName, Parent.TempMarking);
//
//
//
//		if (inputIndex == -1)
//			return;
//
//		PetriObject temp = Parent.TempMarking.get(inputIndex);

		if (temp instanceof DataFloat) {
			result.SetValue((PetriObject) ((DataFloat) temp).clone());
		}

		if (temp instanceof DataInteger) {
			result.SetValue((PetriObject) ((DataInteger) temp).clone());
		}

		if (temp instanceof DataString) {
			result.SetValue((PetriObject) ((DataString) temp).clone());
		}

		if (temp instanceof DataCar) {
			result.SetValue((PetriObject) ((DataCar) temp).clone());
		}

		if (temp instanceof DataSubPetriNet) {
			result.SetValue((PetriObject) ((DataSubPetriNet) temp).clone());
		}

	}

	private void SendROverNetwork() throws CloneNotSupportedException {

		PetriObject output = util.GetPetriObjectByName(OutputPlaceName, Parent.Parent.PlaceList);
		Integer inputIndex = util.GetIndexByName(InputPlaceName, Parent.TempMarking);

		PetriObject result = null;

		if (output instanceof DataTransfer) {
			result = (PetriObject) ((DataTransfer) output).clone();
		}

		if (inputIndex == -1)
			return;

		DataRELQueue temp = (DataRELQueue) Parent.TempMarking.get(inputIndex);

		DataInteger toSend = new DataInteger();
		toSend.SetName(OutputPlaceName);
		toSend.SetValue(temp.Value.GetFirstREL().Value.R);

		if (temp.Value.GetFirstREL().Value.R != ((DataInteger) Parent.TempMarking.get(1)).Value) {
			if (toSend instanceof DataInteger) {
				result.SetValue((PetriObject) ((DataInteger) toSend).clone());
			}
		}
	}

	private void SendPetriNetOverNetwork() throws CloneNotSupportedException {

		PetriObject output = util.GetPetriObjectByName(OutputPlaceName, Parent.Parent.PlaceList);
		Integer inputIndex = util.GetIndexByName(InputPlaceName, Parent.TempMarking);

		PetriObject result = null;

		if (output instanceof DataTransfer) {
			result = (PetriObject) ((DataTransfer) output).clone();
		}

		if (inputIndex == -1)
			return;

		PetriObject temp = Parent.TempMarking.get(inputIndex);

		if (temp instanceof DataSubPetriNet) {
			PetriObject ob = ((DataSubPetriNet) temp).clone();
			DataSubPetriNet sub = (DataSubPetriNet) ob;
			result.SetValue((PetriObject) util.PetriNetToPetriData(sub.Value.Petri));
		}
	}

	private void ActivateSubPetri() throws CloneNotSupportedException {
		PetriObject temp = util.GetFromListByName(InputPlaceName, Parent.Parent.PlaceList);
		if (temp == null)
			return;

		if (temp instanceof DataSubPetriNet) {
			((DataSubPetriNet) temp).Value.StartSubPetri();
		}
	}

	// -------------FloatFloat Modifications--------------------
	private void Add_FloatFlaot() throws CloneNotSupportedException {
		Integer outputIndex = util.GetIndexByName(OutputPlaceName, Parent.Parent.PlaceList);
		PetriObject result = null;

		for (String placeName : InputPlaceNames) {
			PetriObject temp;
			Integer inputIndex = util.GetIndexByName(placeName, Parent.TempMarking);
			if (inputIndex == -1) {
				temp = util.GetFromListByName(placeName, Parent.Parent.ConstantPlaceList);
			} else {
				temp = Parent.TempMarking.get(inputIndex);
			}

			if (temp == null) {
				continue;
			}

			if (temp instanceof DataFloatFloat) {
				if (result == null) {
					result = (PetriObject) ((DataFloatFloat) temp).clone();
				} else {
					FloatFloat ff = new FloatFloat(
							((FloatFloat) result.GetValue()).V1 + ((FloatFloat) temp.GetValue()).V1,
							((FloatFloat) result.GetValue()).V2 + ((FloatFloat) temp.GetValue()).V2);
					result.SetValue(ff);
				}
			}

		}
		result.SetName(OutputPlaceName);
		Parent.Parent.PlaceList.set(outputIndex, result);
	}

	private void Sub_FloatFlaot() throws CloneNotSupportedException {
		Integer outputIndex = util.GetIndexByName(OutputPlaceName, Parent.Parent.PlaceList);
		PetriObject result = null;

		for (String placeName : InputPlaceNames) {
			PetriObject temp;
			Integer inputIndex = util.GetIndexByName(placeName, Parent.TempMarking);
			if (inputIndex == -1) {
				temp = util.GetFromListByName(placeName, Parent.Parent.ConstantPlaceList);
			} else {
				temp = Parent.TempMarking.get(inputIndex);
			}

			if (temp == null) {
				continue;
			}

			if (temp instanceof DataFloatFloat) {
				if (result == null) {
					result = (PetriObject) ((DataFloatFloat) temp).clone();
				} else {
					FloatFloat ff = new FloatFloat(
							((FloatFloat) result.GetValue()).V1 - ((FloatFloat) temp.GetValue()).V1,
							((FloatFloat) result.GetValue()).V2 - ((FloatFloat) temp.GetValue()).V2);
					result.SetValue(ff);
				}
			}

		}
		result.SetName(OutputPlaceName);
		Parent.Parent.PlaceList.set(outputIndex, result);
	}

	private void Prod_FloatFloat() throws CloneNotSupportedException {
		Integer outputIndex = util.GetIndexByName(OutputPlaceName, Parent.Parent.PlaceList);
		PetriObject result = null;

		for (String placeName : InputPlaceNames) {
			PetriObject temp;
			Integer inputIndex = util.GetIndexByName(placeName, Parent.TempMarking);
			if (inputIndex == -1) {
				temp = util.GetFromListByName(placeName, Parent.Parent.ConstantPlaceList);
			} else {
				temp = Parent.TempMarking.get(inputIndex);
			}

			if (temp == null) {
				continue;
			}

			if (temp instanceof DataFloatFloat) {
				if (result == null) {
					result = (PetriObject) ((DataFloatFloat) temp).clone();
				} else {
					FloatFloat ff = new FloatFloat(
							((FloatFloat) result.GetValue()).V1 * ((FloatFloat) temp.GetValue()).V1,
							((FloatFloat) result.GetValue()).V2 * ((FloatFloat) temp.GetValue()).V2);
					result.SetValue(ff);
				}
			}

		}
		result.SetName(OutputPlaceName);
		Parent.Parent.PlaceList.set(outputIndex, result);
	}

	private void Div_FloatFlaot() throws CloneNotSupportedException {
		Integer outputIndex = util.GetIndexByName(OutputPlaceName, Parent.Parent.PlaceList);
		PetriObject result = null;

		for (String placeName : InputPlaceNames) {
			PetriObject temp;
			Integer inputIndex = util.GetIndexByName(placeName, Parent.TempMarking);
			if (inputIndex == -1) {
				temp = util.GetFromListByName(placeName, Parent.Parent.ConstantPlaceList);
			} else {
				temp = Parent.TempMarking.get(inputIndex);
			}

			if (temp == null) {
				continue;
			}

			if (temp instanceof DataFloatFloat) {
				if (result == null) {
					result = (PetriObject) ((DataFloatFloat) temp).clone();
				} else {
					FloatFloat ff = new FloatFloat(
							((FloatFloat) result.GetValue()).V1 / ((FloatFloat) temp.GetValue()).V1,
							((FloatFloat) result.GetValue()).V2 / ((FloatFloat) temp.GetValue()).V2);
					result.SetValue(ff);
				}
			}

		}
		result.SetName(OutputPlaceName);
		Parent.Parent.PlaceList.set(outputIndex, result);
	}

	// --------------------------DynamicDelay----------------------------------

	private void DynamicDelay() throws CloneNotSupportedException {
		PetriObject temp = util.GetFromListByName(InputPlaceName, Parent.TempMarking);
		if (temp != null) {
			if (temp instanceof DataInteger)
				Parent.Delay = ((Integer) (temp.GetValue()));
		} else {
			temp = util.GetFromListByName(InputPlaceName, Parent.Parent.ConstantPlaceList);
			if (temp != null) {
				if (temp instanceof DataInteger)
					Parent.Delay = ((Integer) (temp.GetValue()));
			}
		}
	}
}
