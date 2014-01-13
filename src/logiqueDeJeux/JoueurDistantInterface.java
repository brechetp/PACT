package logiqueDeJeux;

import structure.Annonce;
import structure.Carte;

public interface JoueurDistantInterface 
{
	//Envoi la carte joué au joueur distant
	public void sendCard(Carte cate);
	
	//Attend que le joueur distant joue une carte
	public Carte waitCard();
	
	//Attend que le joueur fasse son annonce
	public Annonce waitAnnonce();
	
	//Envoi l'annonce faite
	public void sendAnnonce(Annonce annonce);
	
	
}
