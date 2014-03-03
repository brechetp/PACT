package logiqueDeJeux;

import serveur.Serveur;
import structure.*;

public class JoueurDistant implements JoueurDistantInterface
{
	
	private CarteListInterface cardList;
	private Serveur serveur;
	
	public JoueurDistant(BeloteCoinche belote)
	{
		this.cardList=new CarteList();
		this.serveur= new Serveur(belote);
	}
	
	public void sendCard(CarteInterface carte,int i) 
	{
		serveur.envoiCarte(carte, i);
	}

	public void waitCard() 
	{
		serveur.waitCarte();
	}

	public void waitAnnonce() 
	{
		serveur.envoiTourAnnonce();
	}

	public void sendAnnonce(AnnonceInterface annonce, int i) 
	{
		serveur.envoiAnnonce(annonce, i);
	}

	public void addCard(CarteInterface carte) 
	{
		cardList.ajoute(carte);
		serveur.envoiCarteDistribution(carte);
	}

	public boolean aHuitCarte() 
	{
		return cardList.size()==8;
	}

	public void partieTerminer() 
	{
		serveur.envoiPartieTerminer();
	}

}
