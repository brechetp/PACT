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
}
