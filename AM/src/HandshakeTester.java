import cse548Machines.HandshakeMachine;
import cse548interfaces.Wave;


public class HandshakeTester {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		HandshakeMachine client = new HandshakeMachine("Client");
		HandshakeMachine tracker = new HandshakeMachine("Tracker");
		HandshakeMachine server = new HandshakeMachine("Server");
		client.initialize();
		tracker.initialize();
		server.initialize();
		System.out.println("\n\nTesting Client\n\n");
		TestClientTransitions(client);
		System.out.println("\n\nTesting Server\n\n");
		TestServerTransitions(server);
		System.out.println("\n\nTesting Tracker\n\n");
		TestTrackerTransitions(tracker);
		/*TestTransitions(cid);
		TestUserMessages(cid);
		
		HandshakeMachine clientEnd = new HandshakeMachine("Client");
		HandshakeMachine serverEnd = new HandshakeMachine("Server");
		
		TestClientServerTransitions(clientEnd,serverEnd);*/
		
		System.out.println("\n\nTesting CTS\n\n");
		TestClientTrackerServerTransitions(client,server,tracker);
		
		

	}
	
	public static void TestClientTransitions(HandshakeMachine client) throws Exception
	{
		
		Wave passing = new Wave();
		log("All Green");
		client.initialize();
		log(client.printCurrentState());
		passing.setProtocolSubstep("bobip");
		passing.setIntentDo("Do it");
		log(client.delta(passing));
		passing.setIntentDo("Success");
		log(client.delta(passing));
		
		
		
		log(client.printCurrentState());
		log(client.delta(true));
		
		
		log(client.printCurrentState());
		passing.setProtocolSubstep("heyalice");
		passing.setIntentDo("Do it");
		log(client.delta(passing));
		passing.setIntentDo("Success");
		log(client.delta(passing));
		
		log(client.printCurrentState());
		log(client.delta(true));
		
		log(client.printCurrentState());
		log(client.delta(true));
		
		log(client.printCurrentState());
		passing.setProtocolSubstep("answertoalice");
		passing.setIntentDo("Do it");
		log(client.delta(passing));
		passing.setIntentDo("Success");
		log(client.delta(passing));
	
		log(client.printCurrentState());
		log(client.delta(true));
		log(client.printCurrentState());
		log(client.delta(true));
		log(client.printCurrentState());

	}
	
	
	public static void  TestServerTransitions(HandshakeMachine server) throws Exception
	{
		Wave passing = new Wave();
		log("All Green");
		server.initialize();
		log(server.printCurrentState());
		passing.setProtocolSubstep("heybob");
		passing.setIntentDo("Can I");
		log(server.delta(passing));
		//Server says action is allowed
		server.delta(true);
		passing.setIntentDo("Execute");
		log(server.delta(passing));
		
		
		//Server says action completed
		log(server.printCurrentState());
		log(server.delta(true));
		System.out.println("IntentDo should be done\n"+server.IDM.printCurrentState());
		
		
		log(server.printCurrentState());
		log(server.delta(true));
		
		log(server.printCurrentState());
		passing.setProtocolSubstep("aliceip");
		passing.setIntentDo("Can I");
		log(server.delta(passing));
		//Server says action is allowed
		server.delta(true);
		passing.setIntentDo("Execute");
		log(server.delta(passing));
		
		
		//Server says action completed
		log(server.printCurrentState());
		log(server.delta(true));//gets us to challenge
		
		log(server.printCurrentState());
		log("Gets us to alice:\t"+server.delta(true));//gets us to alice
		log(server.printCurrentState());
		
		//passing.setProtocolSubstep("answertobob");
		passing.setProtocolSubstep("answertobob");
		passing.setIntentDo("Can I");
		log(server.delta(passing));
		//Server says action is allowed
		server.delta(true);
		passing.setIntentDo("Execute");
		log(server.delta(passing));
		//Server says action is completed
		server.delta(true);
		log(server.printCurrentState());
		log("Gets us to calculate answer:\t"+server.delta(true));
		log(server.printCurrentState());
		log(server.delta(true));
		log(server.printCurrentState());
		log(server.delta(true));
		log(server.printCurrentState());
	
	
		
	}
	
	
	public static void TestTrackerTransitions(HandshakeMachine tracker) throws Exception
	{
		Wave passing = new Wave();
		log("All Green");
		tracker.initialize();
		log(tracker.printCurrentState());
		passing.setProtocolSubstep("wherebob");
		passing.setIntentDo("Can I");
		log(tracker.delta(passing));
		//tracker says action is allowed
		log(tracker.delta(true));
		passing.setIntentDo("Execute");
		log(tracker.delta(passing));
		
		
		//tracker says action completed
		log(tracker.printCurrentState());
		log(tracker.delta(true));
		//System.out.println("IntentDo should be done\n"+server.IDM.printCurrentState());
		
		
		log(tracker.printCurrentState());
		passing.setProtocolSubstep("wherealice");
		passing.setIntentDo("Can I");
		log(tracker.delta(passing));
		//Server says action is allowed
		tracker.delta(true);
		passing.setIntentDo("Execute");
		log(tracker.delta(passing));
		
		
		//Server says action completed
		log(tracker.printCurrentState());
		log(tracker.delta(true));//gets us to challenge
		tracker.setWaveVariables(passing);
		log(tracker.printCurrentState());
	
	}
	
	public static void TestClientTrackerServerTransitions(HandshakeMachine client, HandshakeMachine server, HandshakeMachine tracker) throws Exception
	{
		Wave passing = new Wave();
		client.initialize();
		server.initialize();
		tracker.initialize();
		log("\n\n\t\t--1Client and tracker lookup");
		log(client.printCurrentState());
		client.setWaveVariables(passing);
		log(passing);
		log(tracker.printCurrentState());
		tracker.delta(passing);
		tracker.delta(true);
		tracker.setWaveVariables(passing);
		log(passing);
		client.delta(passing);
		log(client.printCurrentState());
		client.setWaveVariables(passing);
		log(passing);
		tracker.delta(passing);
		tracker.delta(true);
		log(tracker.printCurrentState());
		tracker.setWaveVariables(passing);
		log(passing);
		client.delta(passing);
		log(client.printCurrentState());
		log("\n\n\t\t--2Client internal work");
		client.delta(true);
		log(client.printCurrentState());
		log("\n\n\t\t--3Client and server hey");
		log(client.printCurrentState());
		client.setWaveVariables(passing);
		log(passing);
		log(server.printCurrentState());
		server.delta(passing);
		server.delta(true);
		server.setWaveVariables(passing);
		log(passing);
		client.delta(passing);
		log(client.printCurrentState());
		client.setWaveVariables(passing);
		log(passing);
		server.delta(passing);
		server.delta(true);
		log(server.printCurrentState());
		server.setWaveVariables(passing);
		log(passing);
		client.delta(passing);
		log(client.printCurrentState());
		log("IDM "+client.IDM.printCurrentState());
		
		log("\n\n\t\t--4 Server internal work");
		server.delta(true);
		log(server.printCurrentState());
		log("IDM "+server.IDM.printCurrentState());
		
		log("\n\n\t\t--5 Server and tracker lookup");
		log(server.printCurrentState());
		server.setWaveVariables(passing);
		log(passing);
		log(tracker.printCurrentState());
		tracker.delta(passing);
		tracker.delta(true);
		tracker.setWaveVariables(passing);
		log(passing);
		server.delta(passing);
		log(server.printCurrentState());
		server.setWaveVariables(passing);
		log(passing);
		tracker.delta(passing);
		tracker.delta(true);
		log(tracker.printCurrentState());
		tracker.setWaveVariables(passing);
		log(passing);
		server.delta(passing);
		log(server.printCurrentState());
		
		log("\n\n\t\t--6 Server internal work");
		server.delta(true);
		log(server.printCurrentState());
		
		log("\n\n\t\t--7 Client and server re hey");
		log(client.printCurrentState());
		client.setWaveVariables(passing);
		log(passing);
		log(server.printCurrentState());
		server.delta(passing);
		server.delta(true);
		server.setWaveVariables(passing);
		log(passing);
		client.delta(passing);
		log(client.printCurrentState());
		client.setWaveVariables(passing);
		log(passing);
		server.delta(passing);
		server.delta(true);
		log(server.printCurrentState());
		server.setWaveVariables(passing);
		log(passing);
		client.delta(passing);
		log(client.printCurrentState());
		log("IDM "+client.IDM.printCurrentState());
		
		log("\n\n\t\t--8 Client internal work");
		client.delta(true);
		log(client.printCurrentState());
		client.delta(true);
		log(client.printCurrentState());
		
		log("\n\n\t\t--9 Client and server re hey");
		log(client.printCurrentState());
		client.setWaveVariables(passing);
		log(passing);
		log(server.printCurrentState());
		server.delta(passing);
		server.delta(true);
		server.setWaveVariables(passing);
		log(passing);
		client.delta(passing);
		log(client.printCurrentState());
		client.setWaveVariables(passing);
		log(passing);
		server.delta(passing);
		server.delta(true);
		log(server.printCurrentState());
		server.setWaveVariables(passing);
		log(passing);
		client.delta(passing);
		log(client.printCurrentState());
		log("IDM "+client.IDM.printCurrentState());
		
		
		log("\n\n\t\t--10 Server internal work");
		server.delta(true);
		log(server.printCurrentState());
		server.delta(true);
		log(server.printCurrentState());
		server.delta(true);
		log(server.printCurrentState());
		
		log("\n\n\t\t--11 Client and server re hey");
		log(client.printCurrentState());
		client.setWaveVariables(passing);
		log(passing);
		log(server.printCurrentState());
		server.delta(passing);
		server.delta(true);
		server.setWaveVariables(passing);
		log(passing);
		client.delta(passing);
		log(client.printCurrentState());
		client.setWaveVariables(passing);
		log(passing);
		server.delta(passing);
		server.delta(true);
		log(server.printCurrentState());
		server.setWaveVariables(passing);
		log(passing);
		client.delta(passing);
		log(client.printCurrentState());
		log("IDM "+client.IDM.printCurrentState());
		
		log("\n\n\t\t--12 client internal work");
		client.delta(true);
		log(client.printCurrentState());
		client.delta(true);
		log(client.printCurrentState());
		/*
		log("\t\t--4tracker state and out");

		log("\t\t--5Client state and out");
		
		*/
		
		
		
		
		/*log("\t\t--6Server state and out");
		log(server.printCurrentState());
		server.delta(passing);
		server.delta(true);
		server.setWaveVariables(passing);
		log(server.printCurrentState());
		log(passing);
		
		log("\t\t--7Client state and out");
		client.delta(passing);
		//client.delta(true);
		client.setWaveVariables(passing);
		log(client.printCurrentState());
		log(passing);
	
		log("\t\t--8Server state and out");
		log(server.printCurrentState());
		server.delta(passing);
		server.delta(true);
		server.setWaveVariables(passing);
		log(server.printCurrentState());
		
		log("\t\t--9Client state and out");
		client.delta(passing);
		//client.delta(true);
		log(client.printCurrentState());
		log(passing);
		
		log("\t\t--10Server state and out");
		server.delta(true);
		log(server.printCurrentState());
		log(passing);
	
		log("\t\t--11tracker state and out");
		log(tracker.printCurrentState());
		tracker.delta(passing);
		tracker.delta(true);
		log(tracker.printCurrentState());
		tracker.setWaveVariables(passing);
		log(passing);
	*/
	}
	public static void TestUserMessages(HandshakeMachine client) throws Exception
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
