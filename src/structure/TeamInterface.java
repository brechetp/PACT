package structure;

public interface TeamInterface 
{
	//Rajoute une carte jou� par le joueure num i
	public void ajoute(CarteInterface carte,int i); 
	
	//Renvoi les point de l'equipe
	public int getPoint();
	
	//Rajoute le pli aux carte remporter
	public void remporte(CarteListInterface pli);
	
	//Indique si une carte est jouer par un des joueur de l'equipe
	public boolean jouerParTeam(CarteInterface carte);

	//Donne ne numero du joeur qui a joueur la carte et 0 si aucun ne la jouer
	public int jouerParJoueur(CarteInterface carte);
	
	//indique que cet team a fait le dernier pli
	public void dernierPli();

	//renvoi la carte joueur au premier tour par le joueur numJoueur
	public CarteInterface getCartes(int numJoueur);
	
	
}
