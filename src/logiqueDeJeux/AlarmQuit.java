package logiqueDeJeux;

import machineEtat.StateMachine;
import iug.ViewControllerInterface;

public class AlarmQuit implements Runnable 
{
	private ViewControllerInterface vci;
	private BeloteCoinche belote;
	private boolean distrib;
	private JoueurDistantInterface joueurD;
	private StateMachine machine;
	
	public AlarmQuit(ViewControllerInterface vci, BeloteCoinche belote, boolean distrib, JoueurDistantInterface joueurD, StateMachine stateMachine) 
	{
		this.machine = stateMachine;
		this.joueurD=joueurD;
		this.distrib = distrib;
		this.belote=belote;
		this.vci=vci;
	}

	public void run() 
	{
		try {
			
			for (int i = 4; i >= 0; i--) 
			{
				Thread.sleep(1000);
				vci.modeQuitter(i);
			}
			
			if (distrib)
			{
				vci.modePartie();
				machine.previousState();
			}
			else
			{
				vci.modePartie();
				machine.previousState();
			}
			
			
		} catch (InterruptedException e) 
		{
			joueurD.quit();
			belote.quit();
		}
	}

}
