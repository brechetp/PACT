package logiqueDeJeux;

import structure.*;

public interface JoueurDistantInterface 
{
	//Envoi la carte jou� au joueur distant et le numero du joueur i
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
	public void partieTerminer(int i);

	//indique que le mode annonce est fini
	public void sendFinAnnonce();

	//envoi vrai si le joueur a la carte en sa possession
	public boolean aLaCarte(CarteInterface carte);

	//ferme la connexion
	public void quit();

	//renvoi le nombre de carte actuelement dans la main du joueur distant
	public int nbCard();
	
	//pli terminé, numéro du joueur gagnant le pli.
	public void finDePli(int numJoueur);

	//manche terminet, numéro du joueur qui va joué apès.
	public void mancheTerminer(int premierAJouer);
	
	public void sendCoinche(int i);

	public void enoyerScore(int i, int j, int pointsTeamPair,
			int pointsTeamImpair);
}
