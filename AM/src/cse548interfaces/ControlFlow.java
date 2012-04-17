package cse548interfaces;

public interface ControlFlow {
	
	/**
	 * This is a control flow for the person who initiates the connection
	 * @param FriendName
	 */
	//public SessionWorkFlow(String FriendName);
	
	/**
	 * 1) Sets number of open connections to 1//first open connection is indexed at 0 however
	 * 2) Instantiates managing connections which contains all ControlFlows this session is
	 * responsible for
	 * 3a) Generates a UUID for the first ControlFlow Object
	 * 3b) Creates the first ControlFlow Object and places it into managing at index 1
	 * 
	 */
	public void initializeFlow();
	
	/**
	 * Looks up ControlFlow associated with Wave
	 * Calls ControlFlow.delta(String) of that object
	 * returns the result
	 * @throws Exception 
	 *
	 */
	public boolean delta(Wave incomingWave, String userInput) throws Exception;

	/**
	 * Looks up ControlFlow associated with Wave
	 * Calls ControlFlow.delta(Wave) of that object
	 * returns the result
	 * @throws Exception 
	 *
	 */
	public boolean delta(Wave incomingWave) throws Exception;

	/**
	 * Looks up ControlFlow associated with Wave
	 * Calls ControlFlow.delta(boolean) of that object
	 * returns the result
	 * @throws Exception 
	 *
	 */
	public boolean delta(Wave incomingWave, boolean fileOkay) throws Exception;
	
	/**
	 * CHANGE TO TYPE OF INPUT EXPECTED
	 * Looks up ControlFlow associated with Wave
	 * Calls ControlFlow.expectingUserInput() of that object
	 * returns the result
	 * @throws Exception 
	 *
	 */
	public boolean expectingUserInput(Wave incomingWave) throws Exception;
	
	/**
	 * Looks up ControlFlow associated with Wave
	 * Calls ControlFlow.hasMessageForUser() of that object
	 * returns the result
	 * @throws Exception 
	 *
	 */
	public boolean hasMessageForUser(Wave incomingWave) throws Exception;
	
	/**
	 * Looks up ControlFlow associated with Wave
	 * Calls ControlFlow.getMessageToUser() of that object
	 * returns the result
	 * @throws Exception 
	 *
	 */
	public String getUserPrompt(Wave incomingWave) throws Exception;
	
	public String getURI(Wave incomingWave);
	
	/**
	 * if incomingWave is null & isFirstConnection
	 * returns Wave from first ControlFlow getWave()
	 * if incomingWave is not null
	 * looks up ControlFlow associated with Wave
	 * calls ControlFlow.getWave() of that object
	 * returns result
	 * @throws Exception 
	 * 
	 *
	 */
	public Wave getWave(Wave incomingWave) throws Exception;
	

	/**
	 * looks up ControlFlow associated with wave
	 * calls controlflow.getOrPost() of that flow
	 * returns result
	 * @throws Exception 
	 */
	public Character getOrPost(Wave incomingWave) throws Exception;
	
	/**
	 * Uses ArrayList.contains to get Flow associated with this uuid
	 * returns that flow or null if not present
	 * @throws Exception 
	 *
	 */
	public ControlFlow getFlow(Wave incomingWave) throws Exception;
	
	/**
	 * checks number of connections, if it is 1 returns true else 
	 * returns false
	 *
	 */
	public boolean isFirstConnection();

}
