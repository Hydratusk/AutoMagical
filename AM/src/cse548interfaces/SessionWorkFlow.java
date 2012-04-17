package cse548interfaces;

public interface SessionWorkFlow {

	/**
	 * Creates SessionWorkFlow for client
	 * 
	 */
	//public SessionworkFlow(String userName);
	
	public void init();
	
	public Wave getWave();
	
	public Character getOrPost();
	
	public String getUserPrompt();
	
	public void delta(String cause);
	
	public void delta(Wave cause);
	
	public void delta(boolean cause);
	
	public String getURI();
	
	public Character getEventType();
	
	public Character getResponseType();
	
	public String getPulledFileName();
	
	public String getPostedFileName();
	
	public boolean wasFilePull();
	
	public Character getSentType();
	
	public String getNeededInput();
	
	public String getSID();
	
	public String getUserName();
	
	public boolean getIsFileList();
	
	public String getSessionKey();
	
	public String getFileCheckSum();
	
}
