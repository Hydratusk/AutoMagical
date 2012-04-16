package FiniteStateMachine;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Creates Specific machine based upon file input
 * @author Mike
 *
 */
public class FiniteStateAutomaton extends AbstractMachine {

		String specificationFile;
		HashMap<String,String> variableValues;
		String startState;
	
		
		public FiniteStateAutomaton(String specificationFile)
		{
			this.specificationFile=specificationFile;
			
		}
		
		public void initialize()throws Exception
		{
			InputStream file = FiniteStateAutomaton.class.getResourceAsStream(this.specificationFile);
			//System.out.println(FiniteStateAutomaton.class.getResource(specificationFile));
			//System.out.println(file.available());
			BufferedReader in = new BufferedReader(new InputStreamReader(file,"UTF-8"));
			variableValues = new HashMap<String,String>();
			String str;
			while((str=in.readLine()) !=null)
			{
				//System.out.println(str);
				parse(str);
			}
			in.close();
			
		}
		
		private void parse(String inLine)
		{
			//Get the states
			if(!(inLine.charAt(0)=='$'))
			{
			//split the transition from the output
			String[] first = inLine.split(":");
			//Get the transition aka delta
			String[] transition = first[0].split("-");
			String[] output=null;
			//parse out the output
			if(first !=null&&first.length>1)
			{
				output=first[1].split(",");
			}
			super.add(transition[0], transition[2], transition[1]);
			//Parse the individal outputs
			String[] subout =null;
			ArrayList<Dyad> variables = new ArrayList<Dyad>();
			if(output!=null)
			{
				for(String s:output)
				{	
					//System.out.println(s);
					subout = s.split("<");
					variables.add(new Dyad(subout[0],subout[1]));				
				}
			}
			super.addVariable(transition[0], variables, transition[1]);
			}
			else
			{
				//Get the start state
				String ret  = inLine.substring(1);
				startState = inLine.substring(1).split(":")[0];
				//System.err.println("*********************************\t"+ret);
				String[] keyvalueBuilder = ret.split(":");
				String[] keyvalueRaw = keyvalueBuilder[1].split(",");
				String[] keyvalueHolder=null;
				for(String s : keyvalueRaw)
				{
					//System.err.println(s+"Hello");
					keyvalueHolder=s.split("<");
					//System.err.println(keyvalueHolder[0]);
					//System.err.println(keyvalueHolder[1]);
					variableValues.put(keyvalueHolder[0], keyvalueHolder[1]);
				}
			}
			
		}
		
		public String toString(){
			StringBuilder ret = new StringBuilder();
			ret.append("Source Specification File:\t"+specificationFile);
			ret.append(super.toString());
			ret.append(variableValues.toString());
			return ret.toString();
		}
	

}
