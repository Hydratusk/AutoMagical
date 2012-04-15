package FiniteStateMachine;


import java.util.HashMap;
/*
import java.util.Iterator;
import java.util.Map;
import java.util.Set;*/
import java.util.ArrayList;


/**
 * Represents state to transition machines based upon string input.  
 * Each state may variables associated with it which may be updated 
 * as a result of a transition.
 * @author Mike
 *
 */
public class AbstractMachine {
	
	/* Dyad -> State,input String -> State
	 * Transitions from Dyad.State to State are allowed if variable read in
	 * is =input
	 */
	private HashMap<Dyad, String> allowed;
	
	/* Dyad ->State,input  Dyad -> VariableName, VariableValue
	 * 
	 */
	private HashMap<Dyad, ArrayList<Dyad>> variables;
	
	/**
	 * Creates new AbstractMachine object with empty but
	 * non null allowed transitions, and resulting variables 
	 * HashMap sets
	 */
	public AbstractMachine()
	{
		allowed = new HashMap<Dyad,String>();
		variables = new HashMap<Dyad,ArrayList<Dyad>>();
	}
	
	/**
	 * Creates arc between startSate and endState labeled input
	 * @param startState initial node of arc/edge
	 * @param endState terminating node of arc/edge
	 * @param input label applied to edge
	 */
	public void add(String startState, String endState, String input)
	{
		Dyad stateInput=new Dyad(startState,input);
		allowed.put(stateInput, endState);
	}
	
	
	/**
	 * Specifies state variables which result from startState and input combination
	 * @param startState initial node of edge/arc
	 * @param variables ArrayList of Key/Value pairs representing variables resulting from
	 * traversing edge/arc
	 * @param input label of arc
	 */
	public void addVariable(String startState, ArrayList<Dyad>variables, String input)
	{
		Dyad stateInput=new Dyad(startState,input);
		this.variables.put(stateInput, variables);
	}
	
	
	/**
	 * Checks to see if there is an edge/arc labeled readInput leaving
	 * currentState
	 * @param currentState initial node of arc/edge
	 * @param readInput label of edge/arc
	 * @return true edge from currentState labeled readInput exists
	 * false edge from currentState labeled readInput does not exist
	 */
	public boolean isValid(String currentState, String readInput)
	{
		return allowed.containsKey(new Dyad(currentState,readInput));
	}
	
	
	/**
	 * Returns next state given currentState and readInput
	 * @param currentState initial node of arc
	 * @param readInput label of arc
	 * @return terminating node of arc
	 */
	public String getNextState(String currentState, String readInput)
	{
		String ret="InvalidTransition";
		if(isValid(currentState,readInput))
		{
			ret = (String)allowed.get(new Dyad(currentState,readInput));
		}
		
		return ret;
	}
	
	/**
	 * Gathers state variables which result from traversing arc labeled
	 * readInput from currentState
	 * @param currentState initial node of arc
	 * @param readInput label of arc
	 * @return ArrayList of keyValue pairs resulting from arc traversal
	 * null if no variables present
	 */
	public ArrayList<Dyad> getVariables(String currentState, String readInput)
	{
		ArrayList<Dyad> ret=null;
		if(isValid(currentState,readInput))
		{
			ret = (ArrayList<Dyad>)variables.get(new Dyad(currentState,readInput));
		}
		
		return ret;
	}
	
	public String toString()
	{
		//Set set = allowed.keySet();
		//Iterator i = set.iterator(); 
		StringBuilder ret = new StringBuilder();
		// Display elements 
		/*while(i.hasNext()) { 
		Map.Entry me = (Map.Entry)i.next(); 
		ret.append(me.getKey() + ": "); 
		ret.append(me.getValue()+"\n"); 
		} */
		ret.append("\t\tAllowed Transitions\n");
		for(Dyad key : allowed.keySet())
		{
			ret.append(key); 
			ret.append(allowed.get(key)+"\n"); 
		}
		ret.append("\t\tResultant Variables\n");
		for(Dyad key : variables.keySet())
		{
			ret.append(key); 
			ret.append(variables.get(key)+"\n"); 
		}
		return ret.toString();
	}
	

	
}
