package logiqueDeJeux;

import iug.ViewControllerInterface;

public class AlertJoueurDistant implements Runnable
{
	private ViewControllerInterface vci;

	public AlertJoueurDistant (ViewControllerInterface vci)
	{
		this.vci = vci;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 15; i++) {
				Thread.sleep(1000);
				vci.timeOutJD(14-i);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}
