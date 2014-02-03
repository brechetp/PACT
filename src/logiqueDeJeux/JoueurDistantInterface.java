package logiqueDeJeux;

import structure.*;

public interface JoueurDistantInterface 
{
	//Envoi la carte joué au joueur distant
	public void sendCard(CarteInterface cate);
	
	//Attend que le joueur distant joue une carte
	public CarteInterface waitCard();
	
	//Attend que le joueur fasse son annonce
	public AnnonceInterface waitAnnonce();
	
	//Envoi l'annonce faite
	public void sendAnnonce(AnnonceInterface annonce);
	
	//ajoute un carte au joeur distant
	public void addCard(CarteInterface carte);
	
	
}
