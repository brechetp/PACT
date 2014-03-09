package logiqueDeJeux;

import iug.ViewControllerInterface;
import structure.*;


public interface EtatDuJeuInterface 
{
	//renvoi l'atout actuel
	public String getAtout();
	
	
	//renvoi true si une anonce a ete faite
	public boolean annonceFaite();
	
	//Ajoute une nouvelle carte jouer
	public void joue(CarteInterface carte, ViewControllerInterface vci, JoueurDistantInterface joueurD, int i);
	
	//non jouer et valide. Renvoi true si c'est le cas
	public boolean valide(CarteInterface carte);
	
	//donne le num�ro du joueur actuel
	public int getNumJoueur();
	
	//passe au joueur suivant
	public void joueurSuivant(ViewControllerInterface vci, JoueurDistantInterface joueurD, String string, int numJoueurDistant);
	
	//met fin au pli actuel et calcule les points
	public void finpli(ViewControllerInterface vci);

	//Termine la manche
	public void mancheTerminer();
	
	//Donne le num�ro de l'equipe que a jouer la carte "carte"
	public int numTeamCarte(CarteInterface carte);

	//Augmente la valeur de l'annonce
	public void annonceValueUp(ViewControllerInterface vci);

	//Passe a la couleur suivante
	public void annonceNextSuit(ViewControllerInterface vci);
	
	//Initialise un annonce si il n'y en avais pas, sinon augmente la valeur une fois
	public void initAnnonce(ViewControllerInterface vci);

	//Valide l'annonce en cour faite pas le joueur en cour
	public void valideAnnonce(ViewControllerInterface vci);

	//Donne la valeur de l'annonce
	public int valeurAnnonce();

	//Baisse la valeur de l'annonce
	public void annonceValueDown();

	//Multiplie coefCoinche par 2
	public void coinche();

	//Indique si le dernier pli a �t� jouer
	public boolean dernierPli();

	//setter pour le numero joueur
	public void setNumJoueur(int premierAJouer);

	//met la bonne valeur de l'annonce
	public void setAnnonce(AnnonceInterface annonce);


	public AnnonceInterface getAnnonce();
}
