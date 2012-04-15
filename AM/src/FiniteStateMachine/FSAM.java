package FiniteStateMachine;

import java.util.ArrayList;
import java.util.HashMap;

public class FSAM extends FiniteStateAutomaton {

	
	String species;
	String currentState;
	String nextState;
	HashMap<String, String> nextVariables;
	
	
	public FSAM(String specificationFile, String species){
		super(specificationFile);
		this.species=species;
	}
	
	public void initialize() throws Exception
	{
		super.initialize();
		currentState=super.startState;
		
	}
	
	
	public boolean delta(String readInput)
	{
		boolean ret=false;
		if(deltacheck(readInput))
		{
			ret =execute();
		}
		
		return ret;
	}
	
	/**
	 * Transition check
	 * 
	 */
	public boolean deltacheck(String readInput)
	{
		boolean ret =false;
		ret = getNextState(currentState,readInput).equals("InvalidTransition")?false:true;
		nextState = ret?getNextState(currentState,readInput):null;
		nextVariables =ret?updateVariables(readInput):null;
		return ret;
	}
	

	private boolean execute()
	{
		boolean ret=false;
		currentState = nextState==null?currentState:nextState;
		ret = nextState==null?false:true;
		//Does not allow self arc
		nextState = ret?null:nextState;
		//Set state values that correspond to states, hm have to write those out
		super.variableValues = nextVariables==null?variableValues:nextVariables;
		//does not allow residual stuff
		nextVariables=null;
		return ret;
	}
	
	/**
	 * Update Variable values
	 */
	private HashMap<String,String> updateVariables(String readInput)
	{
		HashMap<String, String> ret = new HashMap<String,String>();
		ArrayList<Dyad> fieldvalue = super.getVariables(currentState, readInput);
		//System.out.println(fieldvalue);
		for(Dyad d : fieldvalue)
		{
			ret.put(d.getKey(), d.getValue());
		}
		return ret;
		
	}
	
	//public
	
	/**
	 * Testing Get variables key value
	 */
	public void printValues()
	{
		System.out.println(super.variableValues);
	}
	
	public String printCurrentState()
	{
		return "Current Status of "+species+"\t"+currentState + variableValues;
	}
	
	public String getFieldValue(String fieldName)
	{
		return super.variableValues.get(fieldName);
	}
}
