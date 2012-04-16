import cse548interfaces.Wave;
import FiniteStateMachine.IntentDoMachine;


public class IDtester {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		IntentDoMachine cid = new IntentDoMachine("Client");
		cid.initialize();
		
		TestTransitions(cid);
		TestUserMessages(cid);
		
		IntentDoMachine clientEnd = new IntentDoMachine("Client");
		IntentDoMachine serverEnd = new IntentDoMachine("Server");
		
		TestClientServerTransitions(clientEnd,serverEnd);
		
		

	}
	
	public static void TestTransitions(IntentDoMachine client) throws Exception
	{
		//System.out.println("")
		log("All Green");
		client.initialize();
		log(client.printCurrentState());
		log(client.delta("Do it"));
		log(client.printCurrentState());
		log(client.delta("Success"));
		log(client.printCurrentState());
		log("Action incomplete");
		client.initialize();
		log(client.printCurrentState());
		log(client.delta("Do it"));
		log(client.printCurrentState());
		log(client.delta("Failure"));
		log(client.printCurrentState());
		log("Action not allowed");
		client.initialize();
		log(client.printCurrentState());
		log(client.delta("Failure"));
		log(client.printCurrentState());

	}
	
	
	public static void  TestServerTransitions(IntentDoMachine server) throws Exception
	{
		log("\nAll Green\n");
		server.initialize();
		log(server.printCurrentState());
		log(server.delta("Can I"));
		log(server.printCurrentState());
		log(server.delta("true"));
		log(server.printCurrentState());
		log(server.delta("Execute"));
		log(server.printCurrentState());
		log(server.delta("true"));
		log(server.printCurrentState());
		
		
		log("\nAction incomplete\n");
		server.initialize();
		log(server.printCurrentState());
		log(server.delta("Can I"));
		log(server.printCurrentState());
		log(server.delta("true"));
		log(server.printCurrentState());
		log(server.delta("Execute"));
		log(server.printCurrentState());
		log(server.delta("false"));
		log(server.printCurrentState());
		
		
		log("\nAction not allowed\n");
		server.initialize();
		log(server.printCurrentState());
		log(server.delta("Can I"));
		log(server.printCurrentState());
		log(server.delta("false"));
		log(server.printCurrentState());

	}
	
	public static void TestClientServerTransitions(IntentDoMachine clientEnd, IntentDoMachine serverEnd) throws Exception
	{
		log("\nAll Green\n");
		clientEnd.initialize();
		serverEnd.initialize();
		
		Wave passing = new Wave();
		
		clientEnd.setWaveVariables(passing);
		log(clientEnd.printCurrentState());
		log(passing);
		
		serverEnd.delta(passing);
		log(serverEnd.printCurrentState());
		serverEnd.delta(true);
		log(serverEnd.printCurrentState());
		serverEnd.setWaveVariables(passing);
		log(passing);
		
		clientEnd.delta(passing);
		clientEnd.setWaveVariables(passing);
		log(clientEnd.printCurrentState());
		log(passing);
		
		serverEnd.delta(passing);
		log(serverEnd.printCurrentState());
		serverEnd.delta(true);
		log(serverEnd.printCurrentState());
		serverEnd.setWaveVariables(passing);
		log(passing);
		
		clientEnd.delta(passing);
		clientEnd.setWaveVariables(passing);
		log(clientEnd.printCurrentState());
		log(passing);
		
		log("\nAction incomplete\n");
		clientEnd.initialize();
		serverEnd.initialize();
		
		passing = new Wave();
		
		clientEnd.setWaveVariables(passing);
		log(clientEnd.printCurrentState());
		log(passing);
		
		serverEnd.delta(passing);
		log(serverEnd.printCurrentState());
		serverEnd.delta(true);
		log(serverEnd.printCurrentState());
		serverEnd.setWaveVariables(passing);
		log(passing);
		
		clientEnd.delta(passing);
		clientEnd.setWaveVariables(passing);
		log(clientEnd.printCurrentState());
		log(passing);
		
		serverEnd.delta(passing);
		log(serverEnd.printCurrentState());
		serverEnd.delta(false);
		log(serverEnd.printCurrentState());
		serverEnd.setWaveVariables(passing);
		log(passing);
		
		clientEnd.delta(passing);
		clientEnd.setWaveVariables(passing);
		log(clientEnd.printCurrentState());
		log(passing);
		
		log("\nAction not allowed\n");
		clientEnd.initialize();
		serverEnd.initialize();
		
		passing = new Wave();
		
		clientEnd.setWaveVariables(passing);
		log(clientEnd.printCurrentState());
		log(passing);
		
		serverEnd.delta(passing);
		log(serverEnd.printCurrentState());
		serverEnd.delta(false);
		log(serverEnd.printCurrentState());
		serverEnd.setWaveVariables(passing);
		log(passing);
		
		clientEnd.delta(passing);
		clientEnd.setWaveVariables(passing);
		log(clientEnd.printCurrentState());
		log(passing);
		
		
	}
	public static void TestUserMessages(IntentDoMachine client) throws Exception
	{
		//System.out.println("")
		log("All Green");
		client.initialize();
		log(client.getMessagesToUser());
		
		log(client.printCurrentState());
		client.delta("Do it");
		log(client.printCurrentState());
		log(client.getMessagesToUser());
		
		client.delta("Success");
		log(client.printCurrentState());
		log(client.getMessagesToUser());
		
		log("Action incomplete");
		client.initialize();
		log(client.printCurrentState());
		log(client.getMessagesToUser());
		client.delta("Do it");
		log(client.printCurrentState());
		log(client.getMessagesToUser());
		client.delta("Failure");
		log(client.printCurrentState());
		log(client.getMessagesToUser());
		log("Action not allowed");
		client.initialize();
		log(client.printCurrentState());
		log(client.getMessagesToUser());
		client.delta("Failure");
		log(client.printCurrentState());
		log(client.getMessagesToUser());

	}
	
	public static void log(Object o)
	{
		System.out.println(o);
	}

}
