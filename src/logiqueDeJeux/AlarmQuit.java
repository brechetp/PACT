package logiqueDeJeux;

import iug.ViewControllerInterface;

public class AlarmQuit implements Runnable 
{
	private ViewControllerInterface vci;
	private BeloteCoinche belote;
	private boolean stop = false;
	
	public AlarmQuit(ViewControllerInterface vci, BeloteCoinche belote) 
	{
		this.belote=belote;
		this.vci=vci;
	}

	public void run() 
	{
		try {
			
			for (int i = 4; i >= 0; i--) 
			{
				if (i==0)
				{
					Thread.sleep(100);
					belote.quit();
				}
				else 
				{
					Thread.sleep(1000);
					synchronized(this) {
						if (stop)
							break;
					}
					vci.modeQuitter(i);
				}
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void stop()
	{
		stop=true;
	}

}
