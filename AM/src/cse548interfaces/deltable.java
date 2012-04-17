package cse548interfaces;

//import FiniteStateMachine.Dyad;

public interface deltable {

	/**
	 * Extracts class specific variable needed for transition from Wave object
	 */
	String getVariablesFromWave(Wave incomingWave);
	
	/**
	 * Sets class specific variable fields in Wave object
	 * @throws Exception 
	 */
	void setWaveVariables(Wave incomingWave) throws Exception;
	
	/**
	 * Executes delta of machine based upon class specific Wave variables
	 * Uses getVariablesFromWave
	 */
	boolean delta(Wave incomingWave) throws Exception;
	
	/**
	 * Executes delta of machine based upon boolean return from system
	 */
	boolean delta(boolean okay) throws Exception;
	
	/**
	 * Executes delta of machine based upon String input
	 */
	boolean delta(String readInput) throws Exception;
	
}