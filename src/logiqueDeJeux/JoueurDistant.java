package logiqueDeJeux;

import java.io.IOException;

import serveur.Serveur;
import structure.*;

public class JoueurDistant implements JoueurDistantInterface
{
	
	private CarteListInterface cardList;
	private Serveur serveur;
	
	public JoueurDistant(BeloteCoinche belote) throws IOException
	{
		this.cardList=new CarteList();
		this.serveur= new Serveur(belote);
		serveur.start();
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
		System.out.println("carte envoyée");
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

	public void sendFinAnnonce() 
	{
		serveur.envoiFinAnnonce();
	}
	
	public boolean aLaCarte(CarteInterface carte)
	{
		return cardList.contain(carte);
	}

	@Override
	public void quit() 
	{
		//previent le joueurDistant
		serveur.stop();
	}

	@Override
	public int nbCard() {
		return cardList.size();
	}
	
	public void finDePli(int numJoueur) 
	{
		serveur.envoiFinDeTour(numJoueur);
	}

	@Override
	public void mancheTerminer(int premierAJouer) 
	{
		serveur.envoiFinManche(premierAJouer);
	}

}
