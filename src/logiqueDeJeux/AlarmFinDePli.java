package logiqueDeJeux;

import machineEtat.StateMachine;

public class AlarmFinDePli implements Runnable
{

	private StateMachine machine;
	
	public AlarmFinDePli(StateMachine machine)
	{
		this.machine=machine;
	}
	
	public void run() 
	{
		try 
		{
			Thread.sleep(5000);
			machine.eventFinPli();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
