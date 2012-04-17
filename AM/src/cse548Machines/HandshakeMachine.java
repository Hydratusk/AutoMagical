package cse548Machines;

import cse548interfaces.Wave;


public class HandshakeMachine extends SubstepMachine implements cse548interfaces.deltable{
	
	
	public HandshakeMachine(String ServentType) {
		super(ServentType, "Handshake");
	}
	
}
