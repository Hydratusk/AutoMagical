package FiniteStateMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
		HashMap<String,String> tempholder =ret?updateVariables(readInput):null;
		nextVariables = ret?updateVariables(tempholder):null;
		//System.out.println("cleaned up variables:\t"+nextVariables);
		return ret;
	}
	

	/**
	 * Checks to ensure that passed in arguements are a valid transition,
	 * excutes said transition and then updates variables
	 * @return
	 */
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
	 * Looks up variables that file specifies for next transition,
	 * returns those variables in String key, string value hashmap
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
	
	/**
	 * Compares nextStatesVariables to the variables in the current state and returns 
	 * the next states variables based upon.
	 * add to ret <- nextStatesVariables key, value && value!=nothing
	 * add to ret <- currentStatesVariables key,value && !ret.contains(key)
	 * 
	 */
	
	private HashMap<String,String> updateVariables(HashMap<String,String> nextStatesVariables)
	{
		HashMap<String, String> ret = new HashMap<String,String>();
		Set<String> iterator = nextStatesVariables.keySet();
		ArrayList<String> dropThese = new ArrayList<String>();
		
		//Printing out the key fields which are specified by this transition in file
		//if they are not the sentinel value of nothing
		for(String s: iterator)
		{
			//System.out.println("InnerNext:\t"+s);
			if(!nextStatesVariables.get(s).equals("NOTHING")&&!nextStatesVariables.get(s).equals("nothing"))
			{
				ret.put(s, nextStatesVariables.get(s));
			}
			else
			{
				dropThese.add(s);
			}
		}
		
		//Getting current variablres keyset
		iterator = super.variableValues.keySet();
		//Adding current Variables to ret if they are not already present
		for(String s: iterator)
		{
			//System.out.println("InnerCurrent:\t"+s);
			if(!ret.containsKey(s))
			{
				ret.put(s, super.variableValues.get(s));
			}
		}
		
		//Drop the ones from dropThese from the list
		//iterator = dropThese;
		for(String s: dropThese)
		{
			ret.remove(s);
		}
		
		
		return ret;
	}
	
	/**
	 * either sets a keys value if it already exists in the FiniteStateMachine
	 * or creates a new key,value pair in the Finite State machine.  If 'nothing'|'NOTHNG' is
	 * passed in as the newValue and the key exists, then that key is removed.
	 * @param key Variables name
	 * @param newValue Variables value
	 * @return true if able to set/create key,value
	 * false if unable to set/create key,value, key and/or value are null
	 */
	public boolean setVariable(String key, String newValue)
	{
		boolean ret=false;
		//update key if it exists
		if(key!=null&&newValue!=null)
		{
			if(!newValue.equals("nothing")&&!newValue.equals("NOTHING"))
			{
				super.variableValues.put(key, newValue);
				ret=true;
			}
			else
			{
				if(super.variableValues.containsKey(key))
				{
					super.variableValues.remove(key);
					ret=true;
				}
				else
				{
					ret=false;
				}
			}
		}
		return ret;
	}
	
	/**
	 * Testing Get variables key value
	 */
	public void printValues()
	{
		//System.out.println(super.variableValues);
	}
	
	public String printCurrentState()
	{
		return "Current Status of "+species+"\t"+currentState + variableValues;
	}
	
	public String getFieldValue(String fieldName)
	{
		return super.variableValues.get(fieldName);
	}

	/*
	 * Specific to CSE548 Aww
	 */
	
	public String getMessagesToUser()
	{
		//ArrayList<String> ret = new ArrayList<String>();
		String ret=null;
		if(super.variableValues.containsKey("MessageToUser"))
		{
			//System.out.println("Here");
			ret=super.variableValues.get("MessageToUser");
		}
		else
		{
				//System.out.println("Not here");
		}
		return ret;
		//return ret;
	}

	/**
	 * Type of Rest message we are sending, Either get or post
	 * @return
	 */
	public String getRestType()
	{
		String ret=null;
		if(super.variableValues.containsKey("REST"))
		{
			//System.out.println("Here");
			ret=super.variableValues.get("REST");
		}
		
		return ret;
		
	}
	
	/**
	 * Type of Message we are sending out
	 * @return
	 */
	public String getMsgOutType()
	{
		String ret=null;
		if(super.variableValues.containsKey("outtype"))
		{
			//System.out.println("Here");
			ret=super.variableValues.get("outtype");
		}
		
		return ret;
		
	}
	
	/**
	 * Type of Response we expect from our message
	 * @return
	 */
	public String getMsgInType()
	{
		String ret=null;
		if(super.variableValues.containsKey("intype"))
		{
			//System.out.println("Here");
			ret=super.variableValues.get("intype");
		}
		
		return ret;
		
	}
	
	public String NeededInput()
	{
		
		String ret=null;
		if(super.variableValues.containsKey("NeededInput"))
		{
			ret=super.variableValues.get("NeededInput");
		}
		return ret;
		
	}

	/**
	 * Checks to see if it is finished
	 * fails open
	 * @return
	 */
	public boolean isFinished()
	{
		boolean ret =true;
		if(super.variableValues.containsKey("Finished"))
		{
			ret=super.variableValues.get("Finished").equals("true")?true:false;
		}
		return ret;
	}
}
