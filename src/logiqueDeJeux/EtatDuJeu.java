package logiqueDeJeux;

import structure.AnnonceInterface;
import structure.Carte;
import structure.CarteInterface;
import structure.CarteList;
import structure.CarteListInterface;

public class EtatDuJeu implements EtatDuJeuInterface
{

	private AnnonceInterface annonce;
	private CarteListInterface playedCard;
	private CarteListInterface cardOnTable;
	private CarteListInterface cardTeam1;
	private CarteListInterface cardTeam2;
	private int numJoueur = 1;
	private int pointsTeam1 = 0;
	private int pointsTeam2 = 0;
	
	@Override
	public String getAtout() 
	{
		return annonce.getAtout();
	}

	@Override
	public AnnonceInterface getAnnonce() 
	{
		return annonce;
	}

	@Override
	public CarteListInterface getPlayedCard() 
	{
		return playedCard;
	}

	@Override
	public CarteListInterface getCardOnTable() 
	{
		return cardOnTable;
	}

	@Override
	public void joue(CarteInterface carte) 
	{
		playedCard.ajoute(carte);
		cardOnTable.ajoute(carte);
	}

	@Override
	public boolean valide(CarteInterface carte) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getNumJoueur() 
	{
		return numJoueur;
	}

	@Override
	public boolean annonceFaite()
	{
		return (annonce!=null);
	}

	@Override
	public void joueurSuivant() 
	{
		this.numJoueur = (numJoueur%4) +1;
	}
	
	public void finpli() 
	{
		//choix des gagnant
		this.cardOnTable = new CarteList();
	}

	@Override
	public void mancheTerminer() 
	{
		
	}

	public boolean isAtout(Carte carte) 
	{
		return (annonce.getAtout()==carte.getSuit());
	}

}
