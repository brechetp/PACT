package logiqueDeJeux;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import machineEtat.StateMachine;
import iug.ViewControllerInterface;
import structure.*;

public class EtatDuJeu implements EtatDuJeuInterface
{
	private AnnonceInterface previousAnnonce;
	private AnnonceInterface annonce;
	private CarteListInterface playedCard;
	private CarteListInterface cardOnTable;
	private TeamInterface TeamPair;
	private TeamInterface TeamImpair;
	private int numJoueur = 1;
	private int pointsTeamPair = 0;
	private int pointsTeamImpair = 0;
	private int coefCoinche = 1;
	public static int valeurFinPartie = 2000;
	private int pointAnnonceTeamPair;
	private int pointAnnonceTeamImpair;
	private StateMachine machine;
	
	public EtatDuJeu (StateMachine machine)
	{
		this.machine=machine;
		this.annonce = null;
		this.playedCard = new CarteList();
		this.cardOnTable = new CarteList();
		this.TeamPair = new Team();
		this.TeamImpair = new Team();
	}
	
	public void setNumJoueur(int i,JoueurDistantInterface joueurD,int numJoueurDistant)
	{
		if (i==numJoueurDistant)
			joueurD.waitCard();
		this.numJoueur=i;
	}
	
	public String getAtout() 
	{
		return annonce.getAtout();
	}

	public void joue(CarteInterface carte,ViewControllerInterface vci,JoueurDistantInterface joueurD, int numJoueurDistant) 
	{
		playedCard.ajoute(carte);
		cardOnTable.ajoute(carte);
		if (numJoueur%2==0)
			TeamPair.ajoute(carte, numJoueur);
		else
			TeamImpair.ajoute(carte, numJoueur);
		this.joueurSuivant(vci, joueurD, "jeu", numJoueurDistant);
		
		if (playedCard.size()==4)
		{
			new Thread(new AlarmFinDePli(machine)).start();
		}
	}

	public boolean valide(CarteInterface carte,JoueurDistantInterface joueurD,int numJoueurDistant,boolean distrib) 
	{
		if (carte.estUneCarte()) {
			//pas deja jouer et pas dans la main du joueur si il ne joue pas
			//	not played && (si numjoueur != numjouer distant) alors 
			if (playedCard.contain(carte))
				return false;
			
			if (numJoueurDistant== numJoueur&&!distrib)
				return joueurD.aLaCarte(carte);
			
			else 
				return !joueurD.aLaCarte(carte);
		}
		return false;
	}

	public int getNumJoueur() 
	{
		return numJoueur;
	}

	public boolean annonceFaite()
	{
		return annonce!=null&&annonce.getTeam()!=-1;
	}

	public void finpli(ViewControllerInterface vci,JoueurDistantInterface joueurD) 
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
		 joueurD.finDePli(numJoueur);
		 if (numJoueur==StateMachine.numJoueurDistant&&!dernierPli())
		 {
			 joueurD.waitCard();
		 }
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



	public void joueurSuivant(ViewControllerInterface vci,JoueurDistantInterface joueurD,String string,int numJoueurD) 
	{
		if (this.cardOnTable.size()!=4) 
		{
			this.numJoueur = (numJoueur % 4) + 1;
			vci.joueurEnCours(numJoueur);
			if (numJoueur == numJoueurD) {
				if (string.equals("annonce"))
					joueurD.waitAnnonce();
				else
					joueurD.waitCard();
			}
		}
	}

	public void mancheTerminer(ViewControllerInterface vci) 
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
				this.pointsTeamPair = pointsTeamPair + coefCoinche*(teamPairPoint + annonce.getValue()) +pointAnnonceTeamPair;
				this.pointsTeamImpair = pointsTeamImpair + teamImpairPoint + pointAnnonceTeamImpair;
				vci.finManche(teamImpairPoint, teamPairPoint, pointAnnonceTeamImpair, pointAnnonceTeamPair, 
						teamImpairPoint+pointAnnonceTeamImpair, teamPairPoint+pointAnnonceTeamPair+valeurAnnonce(), pointsTeamImpair, 
						pointsTeamPair, ""+valeurAnnonce());
			}
			else
			{
				pointAnnonceTeamImpair+=pointAnnonceTeamPair;
				pointAnnonceTeamPair=0;
				this.pointsTeamImpair = pointsTeamImpair + coefCoinche*(162 + annonce.getValue())+pointAnnonceTeamImpair+pointAnnonceTeamPair;
				vci.finManche(teamImpairPoint, teamPairPoint, pointAnnonceTeamImpair, pointAnnonceTeamPair, 
						teamImpairPoint+pointAnnonceTeamImpair+valeurAnnonce(), 0, pointsTeamImpair, 
						pointsTeamPair, ""+valeurAnnonce());
			}
		}
		else
		{
			if (teamImpairPoint>=annonce.getValue())
			{
				this.pointsTeamImpair = pointsTeamImpair + coefCoinche*(teamImpairPoint + annonce.getValue()) + pointAnnonceTeamImpair;
				this.pointsTeamPair = pointsTeamPair + teamPairPoint + pointAnnonceTeamPair;
				vci.finManche(teamImpairPoint, teamPairPoint, pointAnnonceTeamImpair, pointAnnonceTeamPair, 
						teamImpairPoint+pointAnnonceTeamImpair+valeurAnnonce(), teamPairPoint+pointAnnonceTeamPair, pointsTeamImpair, 
						pointsTeamPair, ""+valeurAnnonce());
			}
			else
			{
				pointAnnonceTeamPair+=pointAnnonceTeamImpair;
				pointAnnonceTeamImpair=0;
				this.pointsTeamPair = pointsTeamPair + coefCoinche*(162 + annonce.getValue())+pointAnnonceTeamImpair+pointAnnonceTeamPair;
				vci.finManche(teamImpairPoint, teamPairPoint, pointAnnonceTeamImpair, pointAnnonceTeamPair, 
						0, teamPairPoint+pointAnnonceTeamPair+valeurAnnonce(), pointsTeamImpair, 
						pointsTeamPair, ""+valeurAnnonce());
			}
		}
		//pour les tests
		System.out.println("Point team 1-3 = "+this.pointsTeamImpair);
		System.out.println("Point team 2-4 = "+this.pointsTeamPair);
		
		vci.finManche(teamImpairPoint, teamPairPoint, pointAnnonceTeamImpair, pointAnnonceTeamPair, teamImpairPoint+pointAnnonceTeamImpair+valeurAnnonce(), teamPairPoint, pointsTeamImpair, pointsTeamPair, ""+valeurAnnonce());
		
		//reinitialisation des variable de manche
		this.playedCard = new CarteList();
		this.annonce=null;
		this.TeamPair=new Team();
		this.TeamImpair = new Team();
		this.coefCoinche = 1;
		this.pointAnnonceTeamPair=0;
		this.pointAnnonceTeamImpair=0;
		
		if (this.pointsTeamPair>this.valeurFinPartie)
			{
				vci.partieTerminer();
			}
		else if (this.pointsTeamImpair>this.valeurFinPartie)
			{
				vci.partieTerminer();
			}
			
			
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
	
	public void actualiseAnnonce(ViewControllerInterface vci)
	{
		annonce = new Annonce(previousAnnonce);
		int valeur = this.valeurAnnonce();
		String val;
		if (valeur==250)
			val="capot";
		else if (valeur==500)
			val="générale";
		else
			val =""+valeur;
		String couleur = this.getAtout();
		vci.actualiseAnnonce(val, couleur);
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
		previousAnnonce=new Annonce(annonce);
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

	public boolean dernierPli() 
	{
		return playedCard.size()==32;
	}

	public int setAnnonce(AnnonceInterface annonce, ViewControllerInterface vci, JoueurDistantInterface joueurD) 
	{
		if (annonce == null)
		{
			vci.annonceJoueurDistant("", "");
			return 0;
		}
		else
		{
			if (this.annonce==null || annonce.getValue()>this.annonce.getValue()) {
				this.annonce = annonce;
				int valeur = this.valeurAnnonce();
				String val;
				if (valeur == 250)
					val = "capot";
				else if (valeur == 500)
					val = "générale";
				else
					val = "" + valeur;
				String couleur = this.getAtout();
				vci.annonceJoueurDistant(val, couleur);
				return 1;
			}
			else {
				joueurD.waitAnnonce();
				return 2;
			}
		}
	}
	
	public AnnonceInterface getAnnonce ()
	{
		return annonce;
	}

	public CarteInterface getTeamCarte()
	{
		if (numJoueur%2==0)
			return TeamPair.getCartes(numJoueur);
		else
			return TeamImpair.getCartes(numJoueur);
	}
	
	public void verifieAnnonceCartes(JoueurDistantInterface joueurD, CarteListInterface carteAnnonce, ViewControllerInterface vci) 
	{
		ArrayList<CarteInterface> listeCartes =new ArrayList<CarteInterface>();
		listeCartes.add(getTeamCarte());
		
		Iterator<CarteInterface> itr = carteAnnonce.iterator();
		while (itr.hasNext())
		{
			listeCartes.add(itr.next());
		}

		int length = listeCartes.size();
		Collections.sort(listeCartes, new Comparator<CarteInterface>() {
			@Override
			public int compare(CarteInterface  carte1, CarteInterface  carte2)
			{
				return (carte1.getRang()-carte2.getRang());
			}
		});
		int suiteMax = 1;
		String suiteMaxSuit= listeCartes.get(0).getSuit();
		int suite = 1;
		int nbMemeValue=1;
		String value ="";
		for (int k=0;k<length-1;k++)
		{
			if (listeCartes.get(k).getRang()==listeCartes.get(k+1).getRang())
			{
				value=listeCartes.get(k).getLabelNum();
				nbMemeValue++;
				suite=1;
			}
			else if((listeCartes.get(k).getRang()==listeCartes.get(k+1).getRang()-1)
					&&listeCartes.get(k).getSuit()==listeCartes.get(k+1).getSuit())
			{
				suite++;
			}
			else if ((listeCartes.get(k).getRang()==listeCartes.get(k+1).getRang()-1)
					&&listeCartes.get(k).getSuit()==suiteMaxSuit)
			{
				suite=suiteMax+1;
			}
			else if (suite>suiteMax)
			{
				suiteMaxSuit=listeCartes.get(k-1).getSuit();
				suiteMax=suite;
				suite=1;
			}
			else suite = 1;
		}
		if (suite>suiteMax)
			suiteMax=suite;
		
		//test sur suite et nbMemeValue
		if (nbMemeValue==4)
			annonceSquare(value, vci);
		if (suiteMax==3)
			annonceTierce(vci);
		if (suiteMax==4)
			annonceCinquante(vci);
		if (suiteMax==5)
			annonceCent(vci);
	}

	private void annonceCent(ViewControllerInterface vci) 
	{
		vci.annonceCarte("Cent valid�");
		if (numJoueur%2==0)
			pointAnnonceTeamPair +=100;
		else
			pointAnnonceTeamImpair += 100;
	}

	private void annonceCinquante(ViewControllerInterface vci) {
		vci.annonceCarte("Cinquante valid�");
		if (numJoueur%2==0)
			pointAnnonceTeamPair +=50;
		else
			pointAnnonceTeamImpair += 50;
		
	}

	private void annonceTierce(ViewControllerInterface vci) {
		vci.annonceCarte("Tierce valid�e");
		if (numJoueur%2==0)
			pointAnnonceTeamPair +=20;
		else
			pointAnnonceTeamImpair += 20;
		
	}

	private void annonceSquare(String value,ViewControllerInterface vci) 
	{
		vci.annonceCarte("Carr� de "+value+" valid�");
		switch(value)
		{
		case "valet":
			if (numJoueur%2==0)
				pointAnnonceTeamPair +=200;
			else
				pointAnnonceTeamImpair += 200;
			break;
		case "9":
			if (numJoueur%2==0)
				pointAnnonceTeamPair +=150;
			else
				pointAnnonceTeamImpair += 150;
			break;
		case "as":
			if (numJoueur%2==0)
				pointAnnonceTeamPair +=100;
			else
				pointAnnonceTeamImpair += 100;
			break;
		case "roi":
			if (numJoueur%2==0)
				pointAnnonceTeamPair +=100;
			else
				pointAnnonceTeamImpair += 100;
			break;
		case "reine":
			if (numJoueur%2==0)
				pointAnnonceTeamPair +=100;
			else
				pointAnnonceTeamImpair += 100;
			break;
			default:
				break;
		}
	}

	public int getCoinche() 
	{
		return this.coefCoinche;
	}

	public void setAnnonceNull() 
	{
		annonce=null;
		
	}
}
