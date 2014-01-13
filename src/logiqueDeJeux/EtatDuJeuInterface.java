package logiqueDeJeux;

import structure.Annonce;
import structure.Carte;
import structure.CarteList;

public interface EtatDuJeuInterface 
{
	//renvoi l'atout actuel
	public String getAtout();
	
	//renvoi l'anonce faite
	public Annonce getAnnonce();
	
	//renvoi la liste des cartes joué
	public CarteList getPlayedCard();
	
	//renvoi la liste des carte sur la table
	public CarteList getCardOnTable();
	
	//Ajoute une nouvelle carte joué
	public void add(Carte carte);
}
