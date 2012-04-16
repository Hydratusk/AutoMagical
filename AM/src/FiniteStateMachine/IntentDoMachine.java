package FiniteStateMachine;

import java.util.ArrayList;

import cse548interfaces.Wave;

public class IntentDoMachine extends FSAM implements cse548interfaces.deltable{

	
	public IntentDoMachine(String ServentType) {
		super(ServentType+"IntentDo", ServentType);
		
	}

	@Override
	public String getVariablesFromWave(Wave incomingWave) {
		return incomingWave.getIntentDo();
	}

	@Override
	public void setWaveVariables(Wave incomingWave) {
		incomingWave.setIntentDo(super.getFieldValue("WaveMSG"));
		
	}

	@Override
	public boolean delta(Wave incomingWave) {
		return super.delta(getVariablesFromWave(incomingWave));
	}

	@Override
	public boolean delta(boolean okay) {
		String interim = okay?"true":"false";
		return super.delta(interim);
		
	}
	
	@Override
	public String getMessagesToUser()
	{
		//ArrayList<String> ret = new ArrayList<String>();
		return super.getMessagesToUser();
		//return ret;
	}

	@Override
	public String NeededInput()
	{
		
		return super.NeededInput();
		
	}

	@Override
	public boolean isFinished()
	{
		return super.isFinished();
	}
}
