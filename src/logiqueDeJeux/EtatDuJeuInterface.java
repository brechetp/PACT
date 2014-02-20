package logiqueDeJeux;

import structure.*;


public interface EtatDuJeuInterface 
{
	//renvoi l'atout actuel
	public String getAtout();
	
	
	//renvoi true si une anonce a ete faite
	public boolean annonceFaite();
	
	//Ajoute une nouvelle carte jouer
	public void joue(CarteInterface carte);
	
	//non jouer et valide. Renvoi true si c'est le cas
	public boolean valide(CarteInterface carte);
	
	//donne le numéro du joueur actuel
	public int getNumJoueur();
	
	//passe au joueur suivant
	public void joueurSuivant();
	
	//met fin au pli actuel et calcule les points
	public void finpli();

	//Termine la manche
	public void mancheTerminer();
	
	//Donne le numéro de l'equipe que a jouer la carte "carte"
	public int numTeamCarte(CarteInterface carte);

	//Augmente la valeur de l'annonce
	public void annonceValueUp();

	//Passe a la couleur suivante
	public void annonceNextSuit();
	
	//Initialise un annonce si il n'y en avais pas, sinon augmente la valeur une fois
	public void initAnnonce();

	//Valide l'annonce en cour faite pas le joueur en cour
	public void valideAnnonce();

	//Donne la valeur de l'annonce
	public int valeurAnnonce();

	//Baisse la valeur de l'annonce
	public void annonceValueDown();

	//Multiplie coefCoinche par 2
	public void coinche();

	//Indique si le dernier pli a été jouer
	public boolean dernierPli();

	//setter pour le numero joueur
	public void setNumJoueur(int premierAJouer);
}
