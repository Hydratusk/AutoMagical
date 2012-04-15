import FiniteStateMachine.FSAM;
import FiniteStateMachine.FiniteStateAutomaton;


public class FiniteStateAutomatonTester {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		FiniteStateAutomaton fsa1 = new FiniteStateAutomaton("IntentDo");
		fsa1.initialize();
		System.out.println(fsa1);
		System.out.println("\n\n\n");
		FSAM fsam1 = new FSAM("IntentDo","ClientIntentDo");
		fsam1.initialize();
		fsam1.printCurrentState();
		System.out.println(fsam1.delta("act"));
		
		System.out.println(fsam1.printCurrentState());
		System.out.println(fsam1.delta("do"));
		System.out.println(fsam1.printCurrentState());
		System.out.println(fsam1.delta("done"));
		System.out.println(fsam1.printCurrentState());
		System.out.println(fsam1.delta("act"));
		System.out.println(fsam1.printCurrentState());
		System.out.println(fsam1.delta("dont"));
		System.out.println(fsam1.printCurrentState());
		System.out.println(fsam1.delta("act"));
		System.out.println(fsam1.printCurrentState());
		System.out.println(fsam1.delta("do"));
		System.out.println(fsam1.printCurrentState());
		System.out.println(fsam1.delta("error"));
		System.out.println(fsam1.printCurrentState());
		System.out.println(fsam1.delta("do"));
		System.out.println(fsam1.printCurrentState());
		System.out.println(fsam1.delta("act"));
		System.out.println(fsam1.printCurrentState());
		System.out.println(fsam1.delta("done"));
		System.out.println(fsam1.printCurrentState());
	}

}
