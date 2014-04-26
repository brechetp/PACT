package logiqueDeJeux;

import iug.ViewControllerInterface;

public class AlarmQuit implements Runnable 
{
	private ViewControllerInterface vci;
	private BeloteCoinche belote;
	private volatile boolean quit = false;
	private boolean distrib;
	
	public AlarmQuit(ViewControllerInterface vci, BeloteCoinche belote, boolean distrib) 
	{
		this.distrib = distrib;
		this.belote=belote;
		this.vci=vci;
	}

	public void run() 
	{
		try {
			
			for (int i = 4; i >= 0; i--) 
			{
				synchronized (this) {
					if (i == 0 && quit) {
						vci.modeQuitter(i);
						belote.quit();
					} else if (i == 0) {
						if (distrib) {
							vci.distribution();
						} else {
							vci.modeJeu();
						}
					} else {
						if (!quit) {
							vci.modeQuitter(i);
						} else {
							belote.quit();
						}
					}
				}
				Thread.sleep(1000);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized	 void quit() 
	{
		quit=true;
	}

}
