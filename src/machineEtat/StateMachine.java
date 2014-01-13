package machineEtat;

public class StateMachine 
{
	private enum State {STATE1, STATE2, STATE3};
	private State state;
	
	 public StateMachine() 
	 {
		    this.state = State.STATE1;
	 }
	 
	 public void event1() 
	 {
		 switch(this.state) {
		 case STATE1:
			 //Action
			 this.state = State.STATE2;
			 break;
		 case STATE2:
			 //Action
			 this.state = State.STATE1;
			 break;
		 case STATE3:
			 //Action
			 this.state = State.STATE1;
			 break;
		 default:
		    	//Nothing
			 break;
		 }
	 }
		 
	 public void event2() {
		 switch(this.state) {
		 case STATE1:
			 //Action
			 this.state = State.STATE3;
			 break;
		 case STATE2:
			 //Action
			 this.state = State.STATE3;
			 break;
		 case STATE3:
			 //Action
			 this.state = State.STATE2;
			 break;
		 }
	 }
	 
	 public void event3() 
	 {
		 switch(this.state) 
		 {
		 case STATE1:
			 //Action
			 this.state = State.STATE1;
			 break;
		 case STATE2:
			 //Action
			 this.state = State.STATE1;
			 break;
		 case STATE3:
			 //Action
			 this.state = State.STATE1;
			 break;
		 default:
			 //Nothing
			 break;
		 }
	 }
	 
	 public State getState()
	 {
		 return this.state;
	 }
	 
	 public void setState(State state)
	 {
		 this.state=state;
	 }
	 
}
