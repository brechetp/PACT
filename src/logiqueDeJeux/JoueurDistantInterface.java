package logiqueDeJeux;

import structure.*;

public interface JoueurDistantInterface 
{
	//Envoi la carte joué au joueur distant et le numero du joueur i
	public void sendCard(CarteInterface carte,int i);
	
	//demande que le joueur distant joue une carte
	public void waitCard();
	
	//demande que le joueur fasse son annonce
	public void waitAnnonce();
	
	//Envoi l'annonce faite par le joueur i
	public void sendAnnonce(AnnonceInterface annonce,int i);
	
	//ajoute un carte au joeur distant
	public void addCard(CarteInterface carte);
	
	//indique si la distribution est terminer
	public boolean aHuitCarte();
	
	//indique que la partie est terminer
	public void partieTerminer();

	//indique que le mode annonce est fini
	public void sendFinAnnonce();
	
}
