package logiqueDeJeux;

import structure.*;

public class EtatDuJeu implements EtatDuJeuInterface
{

	private AnnonceInterface annonce;
	private CarteListInterface playedCard;
	private CarteListInterface cardOnTable;
	private TeamInterface Team1;
	private TeamInterface Team2;
	private int numJoueur = 1;
	private int pointsTeam1 = 0;
	private int pointsTeam2 = 0;
	
	public String getAtout() 
	{
		return annonce.getAtout();
	}

	public void joue(CarteInterface carte) 
	{
		playedCard.ajoute(carte);
		cardOnTable.ajoute(carte);
		if (numJoueur%2==0)
			Team1.ajoute(carte, numJoueur);
		else
			Team2.ajoute(carte, numJoueur);
		this.joueurSuivant();
	}

	public boolean valide(CarteInterface carte) 
	{
		//pas deja jouer et 
		//		soit atout
		//		soit premiere carte joueur
		//		soit de la meme couleur
		//		soit l'allier est maitre
		return (!playedCard.contain(carte))&&(carte.isAtout()||(cardOnTable.size()==0)||(cardOnTable.getFirstCardSuit()==carte.getSuit())||(numTeamCarte(cardOnTable.getPlusFort())==(this.numJoueur%2)));
		
	}

	public int getNumJoueur() 
	{
		return numJoueur;
	}

	public boolean annonceFaite()
	{
		return (annonce!=null);
	}

	public void joueurSuivant() 
	{
		//Appel au joueur distant
		this.numJoueur = (numJoueur%4) +1;
	}
	
	public void finpli() 
	{
		 int numTeam = this.numTeamCarte(cardOnTable.getPlusFort());
		 if (numTeam==0)
		 {
			 Team1.remporte(cardOnTable);
		 }
		 else 
		 {
			 Team2.remporte(cardOnTable);
		 }
		 this.cardOnTable = new CarteList();
	}

	public void mancheTerminer() 
	{
		int team1Point = Team1.getPoint();
		int team2Point = Team2.getPoint();
		int teamG = annonce.getTeam();
		if (teamG==0)
		{
			if (team1Point>=annonce.getValue())
			{
				this.pointsTeam1 = pointsTeam1 + team1Point + annonce.getValue();
				this.pointsTeam2 = pointsTeam2 + team2Point;
			}
			else
			{
				this.pointsTeam2 = pointsTeam2 + 162 + annonce.getValue();
			}
		}
		else
		{
			if (team2Point>=annonce.getValue())
			{
				this.pointsTeam2 = pointsTeam2 + team2Point + annonce.getValue();
				this.pointsTeam1 = pointsTeam1 + team1Point;
			}
			else
			{
				this.pointsTeam1 = pointsTeam1 + 162 + annonce.getValue();
			}
		}
		this.playedCard = new CarteList();
		this.annonce=null;
	}

	public boolean isAtout(Carte carte) 
	{
		return (annonce.getAtout()==carte.getSuit());
	}

	@Override
	public int numTeamCarte(CarteInterface carte) 
	{
		if(Team1.jouerParTeam(carte))
			return 0;
		else return 1;
	}

}
