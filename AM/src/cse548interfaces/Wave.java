package cse548interfaces;

public class Wave {

	private String IntentDo;
	private String protocolSubstep;
	public Wave()
	{
		IntentDo="HelloWorld";
	}
	public String getIntentDo() {
		
		return IntentDo;
	}

	public void setIntentDo(String fieldValue) {
		IntentDo = fieldValue;
		
	}

	public String toString()
	{
		return "Current Status of Wave\t\t"+IntentDo+"\t\t"+protocolSubstep;
	}
	public String getHandshake() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getProtocolSubstep() {
		// TODO Auto-generated method stub
		return protocolSubstep;
	}
	public void setProtocolSubstep(String fieldValue) {
		protocolSubstep = fieldValue;
		
	}
}
