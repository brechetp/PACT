package iug;

public interface ViewControllerInterface 
{
	//vide l'affichage de carte (fin pli)
	public void effacerCartes();
	
	//affiche la carte correspondant a valeurcouleur
	public void afficherCarte(String s);
	
	//Affiche que le joueur i a coincher
	public void coinche(int i);

	//met a jour l'affichage du joueur en cour
	public void joueurEnCours(int i);
	
	//affiche fin de parti
	public void partieTerminer();
	
	//suprime les message afficher
	public void supprimerMessage();
	
	public void modeAnnonce();
	
	public void modeJeu();
	
	public void effaceAnnonce();

	public void contreCoinche();

	public void afficheAnnonce(String val, String couleur);
	
	public void valideValeurAnnonce();
	
	public void actualiseAnnonce(String valeur, String couleur);
	
	

}
