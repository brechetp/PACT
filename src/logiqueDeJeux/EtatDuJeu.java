package logiqueDeJeux;

import machineEtat.CardEvent;
import structure.Annonce;
import structure.Carte;
import structure.CarteInterface;
import structure.CarteList;

public class EtatDuJeu implements EtatDuJeuInterface
{

	private Annonce annonce;
	private CarteList PlayedCard;
	private CarteList CardOnTable;
	private CarteList CardTeam1;
	private CarteList CardTeam2;
	private int numJoueur = 1;
	
	@Override
	public String getAtout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Annonce getAnnonce() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarteList getPlayedCard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarteList getCardOnTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(CarteInterface carte) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean valide(CarteInterface carte) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getNumJoueur() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean annonceFaite() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void joueurSuivant() {
		// TODO Auto-generated method stub
		
	}

}
