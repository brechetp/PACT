package logiqueDeJeux;

import machineEtat.CardEvent;
import structure.*;


public interface EtatDuJeuInterface 
{
	//renvoi l'atout actuel
	public String getAtout();
	
	//renvoi l'anonce faite
	public AnnonceInterface getAnnonce();
	
	//renvoi true si une anonce a ete faite
	public boolean annonceFaite();
	
	//renvoi la liste des cartes jouer
	public CarteListInterface getPlayedCard();
	
	//renvoi la liste des carte sur la table
	public CarteListInterface getCardOnTable();
	
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
}
