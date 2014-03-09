package logiqueDeJeux;

import machineEtat.StateMachine;
import iug.ViewControllerInterface;
import structure.*;

public class EtatDuJeu implements EtatDuJeuInterface
{

	private AnnonceInterface annonce;
	private CarteListInterface playedCard;
	private CarteListInterface cardOnTable;
	private TeamInterface TeamPair;
	private TeamInterface TeamImpair;
	private int numJoueur = 1;
	private int pointsTeamPair = 0;
	private int pointsTeamImpair = 0;
	private int coefCoinche = 1;
	private int valeurFinPartie = 2000;
	
	public EtatDuJeu ()
	{
		this.annonce = null;
		this.playedCard = new CarteList();
		this.cardOnTable = new CarteList();
		this.TeamPair = new Team();
		this.TeamImpair = new Team();
	}
	
	public void setNumJoueur(int i)
	{
		this.numJoueur=i;
	}
	
	public String getAtout() 
	{
		return annonce.getAtout();
	}

	public void joue(CarteInterface carte,ViewControllerInterface vci) 
	{
		playedCard.ajoute(carte);
		cardOnTable.ajoute(carte);
		if (numJoueur%2==0)
			TeamPair.ajoute(carte, numJoueur);
		else
			TeamImpair.ajoute(carte, numJoueur);
		this.joueurSuivant(vci);
	}

	public boolean valide(CarteInterface carte) 
	{
		//pas deja jouer et 
		//		soit atout
		//		soit premiere carte joueur
		//		soit de la meme couleur
		//		soit l'allier est maitre
		return !playedCard.contain(carte);
		
	}

	public int getNumJoueur() 
	{
		return numJoueur;
	}

	public boolean annonceFaite()
	{
		return annonce!=null&&annonce.getTeam()!=-1;
	}

	public void joueurSuivant(ViewControllerInterface vci,JoueurDistant joueurD,String string,int numJoueurD) 
	{
		this.numJoueur = (numJoueur%4) +1;
		vci.joueurEnCours(numJoueur);
		if (numJoueur == numJoueurD)
		{
			if (string.equals(annonce))
				joueurD.waitAnnonce();
			else
				joueurD.waitCard();
		}
	}
	
	public void finpli(ViewControllerInterface vci) 
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
		 this.numJoueur = this.numRemportePli();
		 System.out.println(numJoueur);
		 vci.effacerCartes();
		 vci.joueurEnCours(numJoueur);
		 this.cardOnTable = new CarteList();
	}

	private int numRemportePli() 
	{
		CarteInterface carte = cardOnTable.getPlusFort();
		int numPair = this.TeamPair.jouerParJoueur(carte);
		int numImpair = this.TeamImpair.jouerParJoueur(carte);
		if (numImpair==1)
			return 1;
		else if (numImpair==2)
			return 3;
		else return 2*numPair; 
	}



	public void mancheTerminer() 
	{
		if(this.numJoueur%2==0)
			this.TeamPair.dernierPli();
		else this.TeamImpair.dernierPli();
		
		int teamPairPoint = TeamPair.getPoint();
		int teamImpairPoint = TeamImpair.getPoint(); //on peux revoir ici en calculant juste la team avec le mmoin de carte et en faisant 162-ans pour l'autre.
		
		//pour test
		System.out.println("Point carte team 1-3 = "+teamImpairPoint);
		System.out.println("Point carte team 2-4 = "+teamPairPoint);
		//
		
		int teamG = annonce.getTeam();
		if (teamG==0)
		{
			if (teamPairPoint>=annonce.getValue())
			{
				this.pointsTeamPair = pointsTeamPair + coefCoinche*(teamPairPoint + annonce.getValue());
				this.pointsTeamImpair = pointsTeamImpair + teamImpairPoint;
			}
			else
			{
				this.pointsTeamImpair = pointsTeamImpair + coefCoinche*(162 + annonce.getValue());
			}
		}
		else
		{
			if (teamImpairPoint>=annonce.getValue())
			{
				this.pointsTeamImpair = pointsTeamImpair + coefCoinche*(teamImpairPoint + annonce.getValue());
				this.pointsTeamPair = pointsTeamPair + teamPairPoint;
			}
			else
			{
				this.pointsTeamPair = pointsTeamPair + coefCoinche*(162 + annonce.getValue());
			}
		}
		//pour les tests
		System.out.println("Point team 1-3 = "+this.pointsTeamImpair);
		System.out.println("Point team 2-4 = "+this.pointsTeamPair);
		//reinitialisation des variable de manche
		this.playedCard = new CarteList();
		this.annonce=null;
		this.TeamPair=new Team();
		this.TeamImpair = new Team();
		this.coefCoinche = 1;
		
		//if (this.pointsTeamPair>this.valeurFinPartie)
			
			
	}

	public boolean isAtout(Carte carte) 
	{
		if (annonce==null)
			return false;
		return (annonce.getAtout()==carte.getSuit());
	}

	public int numTeamCarte(CarteInterface carte) 
	{
		if(TeamPair.jouerParTeam(carte))
			return 0;
		else return 1;
	}

	public void annonceValueUp(ViewControllerInterface vci) 
	{
		this.annonce.valueUp();
		//Actualise l'annonce
		int valeur = this.valeurAnnonce();
		String val;
		if (valeur==250)
			val="capot";
		else if (valeur==500)
			val="générale";
		else
			val =""+valeur;
		String couleur = this.getAtout();
		vci.actualiseAnnonce(val,couleur);
		
	}

	public void annonceNextSuit(ViewControllerInterface vci) 
	{
		this.annonce.nextSuit();
		//Met a jour la couleur
		int valeur = this.valeurAnnonce();
		String val;
		if (valeur==250)
			val="capot";
		else if (valeur==500)
			val="générale";
		else
			val =""+valeur;
		String couleur = this.getAtout();
		vci.actualiseAnnonce(val,couleur);
		
	}

	public void initAnnonce(ViewControllerInterface vci) 
	{
		if (this.annonce==null)
			annonce=new Annonce("carreau",80, -1);
		else
			annonce.valueUp();
		//Affiche l'annonce
		int valeur = this.valeurAnnonce();
		String val;
		if (valeur==250)
			val="capot";
		else if (valeur==500)
			val="générale";
		else
			val =""+valeur;
		String couleur = this.getAtout();
		vci.afficheAnnonce(val,couleur);
		//Terminer affiche annonce
	}

	public void valideAnnonce(ViewControllerInterface vci) 
	{
		annonce.setTeam(numJoueur%2);
		vci.effaceAnnonce();
		
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

	@Override
	public void setAnnonce(AnnonceInterface annonce) 
	{
		this.annonce=annonce;
	}
}
