package logiqueDeJeux;

import javax.swing.JOptionPane;

import iug.ViewControllerInterface;
import structure.AnnonceInterface;
import machineEtat.*;

public class BeloteCoinche implements GlobalListener
{
	private StateMachine machine;
	
	
	public BeloteCoinche(ViewControllerInterface vci)
	{
		
		this.machine = new StateMachine(new JoueurDistant(this), vci);
	}
	
	public void nouvelleCarte(CardEvent carte) 
	{
		String label = carte.getCarte().getLabelNum();
		System.out.println(label);
		String suit = carte.getCarte().getSuit();
		System.out.println(suit);
		
		String newCarte =(String) JOptionPane.showInputDialog(
			    null,
			    "Carte: ",
			    "New Card",
			    JOptionPane.PLAIN_MESSAGE,
			    null,
			    null,
			    label+" "+suit );
		
		if (!newCarte.equals(""))
		{
			String[] card =newCarte.split(" ");
		
			carte.setLabel(card[0]);
			carte.setSuit(card[1]);
		
			synchronized (machine) {
				machine.eventCarte(carte);
			}
		}
	}
	
	public void nouvelleCarteJoueurDistant(CardEvent carte)
	{
		synchronized (machine) {
			machine.eventCarte(carte);
		}
	}

	public void nouveauGeste(MouvementEvent mouvement) 
	{
		System.out.println("Message Recu !!");
		String label = mouvement.getLabel();
		
		int n = JOptionPane.showConfirmDialog(
			    null,
			    "Geste : "+label,
			    "New movement",
			    JOptionPane.YES_NO_OPTION);
		
		if (n==0) 
		{
			if (mouvement.getLabel() == "passer") {
				synchronized (machine) {
					machine.eventGestePasser();
				}
			} else if (mouvement.getLabel() == "retour") {
				synchronized (machine) {
					machine.eventGesteRetour();
				}
			} else if (mouvement.getLabel() == "coinche") {
				synchronized (machine) {
					machine.eventGesteCoinche();
				}
			} else if (mouvement.getLabel() == "accepter") {
				synchronized (machine) {
					machine.eventGesteAccept();
				}
			} else if (mouvement.getLabel() == "quitter") {
				synchronized (machine) {
					machine.eventQuit();
				}
			}
		}
	}

	public void finPli() 
	{
		System.out.println("fin de pli");
		synchronized (machine) 
		{
			machine.eventFinPli();
		}
	}
	
	public void setAnnonce(AnnonceInterface annonce)
	{
		synchronized (machine) 
		{
			machine.setAnnonce(annonce);
		}
	}

	public EtatDuJeuInterface getEtat() 
	{
		synchronized (machine) 
		{
			return machine.getEtat();
		}
	}
}
