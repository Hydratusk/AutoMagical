import FiniteStateMachine.FSAM;
import FiniteStateMachine.FiniteStateAutomaton;


public class FiniteStateAutomatonTester {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/*FiniteStateAutomaton fsa1 = new FiniteStateAutomaton("IntentDo");
		fsa1.initialize();
		System.out.println(fsa1);
		System.out.println("\n\n\n");*/
		FSAM fsam1 = new FSAM("fsamTester","ClientIntentDo");
		fsam1.initialize();
		
		System.out.println("Start State");
		System.out.println(fsam1.printCurrentState());
		
		System.out.println("B State");
		fsam1.delta("1");
		System.out.println(fsam1.printCurrentState());
		
		System.out.println("C State");
		fsam1.delta("1");
		System.out.println(fsam1.printCurrentState());
		
		System.out.println("D State");
		fsam1.delta("1");
		System.out.println(fsam1.printCurrentState());
		
		System.out.println("E State");
		fsam1.delta("1");
		System.out.println(fsam1.printCurrentState());
		
		System.out.println("Final Values of variables");
		System.out.println(fsam1.printCurrentState());
		System.out.println("Unkown");
		FSAM fsam2 = new FSAM("fsamTester","ClientIntentDo");
		fsam2.initialize();
		
		System.out.println("Start State");
		System.out.println(fsam2.printCurrentState());
		
		System.out.println("Start State after adding new variables");
		fsam2.setVariable("Famous", "SAMS");
		
		System.out.println(fsam2.printCurrentState());
		
		System.out.println("B State");
		System.out.println("Retrieving field value Famous");
		fsam2.delta("1");
		System.out.println(fsam2.getFieldValue("Famous"));
		System.out.println(fsam2.printCurrentState());
		fsam2.setVariable("Famous", "nothing");
		
		System.out.println("C State");
		System.out.println("Retrieving field value k2");
		fsam2.delta("1");
		System.out.println(fsam2.getFieldValue("k2"));
		System.out.println(fsam2.printCurrentState());
		
		System.out.println("D State");
		fsam2.delta("1");
		System.out.println(fsam2.printCurrentState());
		
		System.out.println("E State");
		fsam2.delta("1");
		System.out.println(fsam2.printCurrentState());
		
		System.out.println("Final Values of variables");
		System.out.println(fsam2.printCurrentState());
		
		//fsam1.printCurrentState();
		//fsam1.deltacheck("1");
		/*System.out.println(fsam1.delta("act"));
		
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
		System.out.println(fsam1.printCurrentState());*/
	}//End main
}//End class

