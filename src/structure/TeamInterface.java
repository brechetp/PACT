package structure;

public interface TeamInterface 
{
	//Rajoute une carte joué par le joueure num i
	public void ajoute(CarteInterface carte,int i); 
	
	//Renvoi les point de l'equipe
	public int getPoint();
	
	//Rajoute le pli aux carte remporter
	public void remporte(CarteListInterface pli);
	
	//Indique si une carte est jouer par un des joueur de l'equipe
	public boolean jouerParTeam(CarteInterface carte);
}
