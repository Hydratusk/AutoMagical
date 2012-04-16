package cse548interfaces;

public class Wave {

	private String IntentDo;
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
		return "Current Status of Wave\t\t"+IntentDo;
	}
}
