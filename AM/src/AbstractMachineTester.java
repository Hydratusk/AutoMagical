import java.util.ArrayList;

import FiniteStateMachine.AbstractMachine;
import FiniteStateMachine.Dyad;


public class AbstractMachineTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractMachine t = new AbstractMachine();
		
		t.add("1", "2","a");
		t.add("1", "3", "b");
		t.add("2", "4","a");
		t.add("3", "4", "b");
		ArrayList<Dyad>variablesHolder = new ArrayList<Dyad>();
		variablesHolder.add(new Dyad("Hello","Mother"));
		variablesHolder.add(new Dyad("Goodbye","Fucker"));
		t.addVariable("1", variablesHolder, "a");
		variablesHolder = new ArrayList<Dyad>();
		variablesHolder.add(new Dyad("Song","Sweet Dreams"));
		variablesHolder.add(new Dyad("Artist","Nicki Minaj"));
		t.addVariable("1", variablesHolder, "b");
		System.out.println(t.getNextState("1","a"));
		System.out.println(t.getNextState("2","a"));
		System.out.println(t.getNextState("1","b"));
		System.out.println(t.getNextState("3","b"));
		System.out.println(t.getNextState("3","a"));
		
		System.out.println(t.getVariables("1","a"));
		System.out.println(t.getVariables("2","a"));
		System.out.println(t.getVariables("1","b"));
		System.out.println(t.getVariables("3","b"));
		System.out.println(t.getVariables("3","a"));
		
		System.out.println(t);

		
	}

}
