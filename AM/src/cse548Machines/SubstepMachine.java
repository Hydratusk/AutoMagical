package cse548Machines;

import java.util.ArrayList;
import cse548interfaces.Wave;
import FiniteStateMachine.FSAM;

public class SubstepMachine extends FSAM implements cse548interfaces.deltable{

	
	public IntentDoMachine IDM;
	private String name;
	private Wave tempWave=null;
	private boolean hold =true;
	public SubstepMachine(String ServentType, String protocolStep) {
		
		super(ServentType+protocolStep, ServentType);
		IDM = new IntentDoMachine(ServentType);
		this.name=protocolStep;
	}

	@Override
	public void initialize() throws Exception
	{
		super.initialize();
		IDM.initialize();
	}
	
	public String getVariablesFromWave(Wave incomingWave) {
		return incomingWave.getProtocolSubstep();
	}

	/**
	 * Also know as get wave, needs to set this machines and submachines wave fields
	 * @throws Exception 
	 */
	@Override
	public void setWaveVariables(Wave incomingWave) throws Exception {
		incomingWave.setProtocolSubstep(super.getFieldValue("WaveMSG"));
		IDM.setWaveVariables(incomingWave);
		if(IDM.isFinished())
		{
			System.out.println("IDM+=--0908is finished");
			IDM.initialize();
		}
		
	}

	/**
	 * checks to see if sub machine is 'Finished', if not
	 * and neededinput is Wave delta submachine, else
	 * do nothing.  If submachine is 'Finished' then we delta
	 * this machine
	 * @throws Exception 
	 */
	@Override
	public boolean delta(Wave incomingWave) throws Exception{
		boolean ret=false;
		tempWave = hold?incomingWave:null;
		
		if(!IDM.isFinished())
		{
			if(IDM.NeededInput().equalsIgnoreCase("remote"))
			{
				ret=IDM.delta(incomingWave);
			}
			
		}
		
		if(IDM.isFinished())
		{
			if(this.NeededInput()!=null&&this.NeededInput().equalsIgnoreCase("remote"))
			{
				System.err.println("in big machine wave contains:\t"+getVariablesFromWave(incomingWave));
				if(getVariablesFromWave(incomingWave)!=null)
				{
				ret= super.delta(getVariablesFromWave(incomingWave));
				}
				IDM.initialize();
			}
		}
		return ret;
	}
	
	
	/**
	 * checks to see if sub machine is 'Finished', if not
	 * and neededinput is boolean delta submachine, else
	 * do nothing.  If submachine is 'Finished' then we delta
	 * this machine
	 * @throws Exception 
	 */
	@Override
	public boolean delta(boolean okay) throws Exception {
		//System.out.println("Boolean delta");
		String interim = okay?"true":"false";
		boolean ret=false;
		//Intent do never needs a decision
		//System.out.println(IDM.printCurrentState());
		if(!IDM.isFinished())
		{
			//System.out.println("IDM not finished delta");
			if(IDM.NeededInput().equalsIgnoreCase("decision"))
			{
				ret=IDM.delta(okay);
				if(IDM.isFinished())
				{
					System.err.println("Idm is finished");
					super.delta(getVariablesFromWave(tempWave));
					//IDM.initialize();
				
				}
			}
			else
			{
				//System.out.println("IDM does not need decision");
				if(this.NeededInput().equalsIgnoreCase("decision"))
				{
					//System.out.println("This does need decision");
					
					ret= super.delta(interim);
				}
			}
		}
		else
		{
			System.out.println("IDM finished delta");
			if(this.NeededInput()!=null&&this.NeededInput().equalsIgnoreCase("decision"))
			{
				System.out.println("Here");
				ret= super.delta(interim);
				IDM.initialize();
			}
			else
			{
				//System.out.println("Here2");
				if(this.NeededInput()!=null&&super.NeededInput().equalsIgnoreCase("remote"))
				{
					System.out.println("Here2");
					super.delta(getVariablesFromWave(tempWave));
				}
			}
		}
		return ret;
	}
	
	/**
	 * checks to see if sub machine is 'Finished', if not
	 * and neededinput is string delta submachine, else
	 * do nothing.  If submachine is 'Finished' then we delta
	 * this machine
	 */
	@Override
	public boolean delta(String readInput)
	{
		
		boolean ret=false;
		if(!IDM.isFinished())
		{
			if(IDM.NeededInput().equalsIgnoreCase("system"))
			{
				ret=IDM.delta(readInput);
			}
		}
		else
		{
			if(this.NeededInput().equalsIgnoreCase("system"))
			{
				ret= super.delta(readInput);
			}
		}
		return ret;
	
	}
	
	@Override
	public String getMessagesToUser()
	{
		return name+"->"+super.getMessagesToUser()+"IntentDo"+IDM.getMessagesToUser();
	}

	/**
	 * Returns input need by sub machines or this machine. sub and this are mutux,
	 * sub out ranks this
	 */
	@Override
	public String NeededInput()
	{
		String ret=null;
		if(IDM.NeededInput()!=null && super.NeededInput()!=null&&super.NeededInput().equalsIgnoreCase("remote"))
		{
			ret=IDM.NeededInput();
		}
		else
		{
			ret=super.NeededInput();
		}
		return ret;
	}
	
	
	/**
	 * Checks if IntentDo has a REST field specified,
	 * if yes returns that field else returns
	 * this machines REST field
	 * @return p=post, g=get
	 */
	public Character getOrPost()
	{
		Character ret =null;
		
		if(IDM.getRESTType()!=null)
		{
			//IDM has rest type
			ret=IDM.getRESTType().charAt(0);
		}
		else
		{
			//IDM does not have rest type
			ret=super.getRESTType().charAt(0);
		}
		return ret;
	}
	
	/**
	 * Returns prompts to user from field in file
	 * key is 'userprompt'
	 * @return
	 */
	public String getUserPrompt()
	{
		return super.getFieldValue("userprompt");
	}
	
	/**
	 *  Returns prompts to user from field in file
	 * key is 'uri'
	 * @return
	 */
	public String getURI()
	{
		return super.getFieldValue("uri");
	}
	
	/*Ask mary
	public Character getEventType();
	public boolean getIsFileList();
	public Character getResponseType();
	public Character getSentType();
	
	//needs to specified in finite state machine diagrams
	public String getPulledFileName();
	
	public String getPostedFileName();
	*/
	/**
	 * checks key field 'filepulled' in specification
	 * file and/or running machine and returns true if 
	 * it is set to true, else false
	 * Fails closed
	 * @return
	 */
	public boolean wasFilePull()
	{
		boolean ret=false;
		
		if(super.getFieldValue("filepulled")!=null)
		{
			ret=super.getFieldValue("filepulled").equals("true")?true:ret;
		}
		
		return ret;
	}
	
	
	/**
	 * Alias for NeededInput
	 * @return
	 */
	public String getNeededInput()
	{
		return NeededInput();
	}
	
	
	/**
	 * checks key field 'sid' in specification
	 * file and/or running machine and returns it
	 * Fails closed
	 * @return
	 */
	public String getSID()
	{
		return super.getFieldValue("sid");
	}
	
	/**
	 * checks key field 'username' in specification
	 * file and/or running machine and returns it
	 * Fails closed
	 * @return
	 */
	public String getUserName()
	{
		return super.getFieldValue("username");
	}
	
	
	/**
	 * checks key field 'sk' in specification
	 * file and/or running machine and returns it
	 * Fails closed
	 * @return
	 */
	public String getSessionKey()
	{
		return super.getFieldValue("sk");
	}
	
	/**
	 * checks key field 'checksum' in specification
	 * file and/or running machine and returns it
	 * Fails closed
	 * @return
	 */
	public String getFileCheckSum()
	{
		return super.getFieldValue("checksum");
				
	}

}
