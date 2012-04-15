package FiniteStateMachine;

public class Dyad {

		private String state, input;
		public Dyad(String currentState, String input)
		{
			state = currentState;
			this.input = input;
		}
		
		@Override
		public
		boolean equals(Object other)
		{
			boolean ret = false;
			ret = (this.state.equals(((Dyad)other).state) && this.input.equals(((Dyad)other).input) )?true:false;
			return ret;
		}
		
		@Override
		public
		int hashCode()
		{
		    return state.hashCode()+input.hashCode()+149;
		}
		
		public String toString()
		{
			return state+"-"+input+"-";
		}
		
		public String getKey()
		{
			return state;
		}
		
		public String getValue()
		{
			return input;
		}

	
}
