package logiqueDeJeux;

import structure.*;

public class EtatDuJeu implements EtatDuJeuInterface
{

	private AnnonceInterface annonce;
	private CarteListInterface playedCard;
	private CarteListInterface cardOnTable;
	private TeamInterface TeamPair;
	private TeamInterface TeamImpair;
	private int numJoueur = 1;
	private int pointsTeam1 = 0;
	private int pointsTeam2 = 0;
	private int coefCoinche = 1;
	
	public String getAtout() 
	{
		return annonce.getAtout();
	}

	public void joue(CarteInterface carte) 
	{
		playedCard.ajoute(carte);
		cardOnTable.ajoute(carte);
		if (numJoueur%2==0)
			TeamPair.ajoute(carte, numJoueur);
		else
			TeamImpair.ajoute(carte, numJoueur);
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
		return annonce!=null&&annonce.getTeam()!=-1;
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
			 TeamPair.remporte(cardOnTable);
		 }
		 else 
		 {
			 TeamImpair.remporte(cardOnTable);
		 }
		 this.cardOnTable = new CarteList();
	}

	public void mancheTerminer() 
	{
		int teamPairPoint = TeamPair.getPoint();
		int teamImpairPoint = TeamImpair.getPoint();
		int teamG = annonce.getTeam();
		if (teamG==0)
		{
			if (teamPairPoint>=annonce.getValue())
			{
				this.pointsTeam1 = pointsTeam1 + coefCoinche*(teamPairPoint + annonce.getValue());
				this.pointsTeam2 = pointsTeam2 + teamImpairPoint;
			}
			else
			{
				this.pointsTeam2 = pointsTeam2 + coefCoinche*(162 + annonce.getValue());
			}
		}
		else
		{
			if (teamImpairPoint>=annonce.getValue())
			{
				this.pointsTeam2 = pointsTeam2 + coefCoinche*(teamImpairPoint + annonce.getValue());
				this.pointsTeam1 = pointsTeam1 + teamPairPoint;
			}
			else
			{
				this.pointsTeam1 = pointsTeam1 + coefCoinche*(162 + annonce.getValue());
			}
		}
		//reinitialisation des variable de manche
		this.playedCard = new CarteList();
		this.annonce=null;
		this.TeamPair=new Team();
		this.TeamImpair = new Team();
		this.coefCoinche = 1;
	}

	public boolean isAtout(Carte carte) 
	{
		return (annonce.getAtout()==carte.getSuit());
	}

	public int numTeamCarte(CarteInterface carte) 
	{
		if(TeamPair.jouerParTeam(carte))
			return 0;
		else return 1;
	}

	public void annonceValueUp() 
	{
		this.annonce.valueUp();
		
	}

	public void annonceNextSuit() 
	{
		this.annonce.nextSuit();
		
	}

	public void initAnnonce() 
	{
		if (this.annonce==null)
			annonce=new Annonce("carreau",80, -1);
		else
			annonce.valueUp();
	}

	public void valideAnnonce() 
	{
		annonce.setTeam(numJoueur%2);
	}

	public int valeurAnnonce() 
	{
		return annonce.getValue();
	}

	public void annonceValueDown() 
	{
		annonce.valueDown();
		
	}

	public void coinche() 
	{
		if (this.coefCoinche<4)
			this.coefCoinche=this.coefCoinche*2;
	}

	@Override
	public boolean dernierPli() 
	{
		return playedCard.size()==32;
	}

}
