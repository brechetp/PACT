package logiqueDeJeux;

import machineEtat.CardEvent;
import structure.*;


public interface EtatDuJeuInterface 
{
	//renvoi l'atout actuel
	public String getAtout();
	
	//renvoi l'anonce faite
	public Annonce getAnnonce();
	
	//renvoi true si une anonce a ete faite
	public boolean annonceFaite();
	
	//renvoi la liste des cartes jouer
	public CarteList getPlayedCard();
	
	//renvoi la liste des carte sur la table
	public CarteList getCardOnTable();
	
	//Ajoute une nouvelle carte jouer
	public void add(CarteInterface carte);
	
	//non jouer et valide. Renvoi true si c'est le cas
	public boolean valide(CarteInterface carte);
	
	//donne le numéro du joueur actuel
	public int getNumJoueur();
	
	//passe au joueur suivant
	public void joueurSuivant();
}
