package logiqueDeJeux;

import structure.*;

public class JoueurDistant implements JoueurDistantInterface
{
	
	private CarteListInterface cardList;
	
	public JoueurDistant()
	{
		this.cardList = new CarteList();
	}
	
	@Override
	public void sendCard(CarteInterface cate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CarteInterface waitCard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnnonceInterface waitAnnonce() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendAnnonce(AnnonceInterface annonce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCard(CarteInterface carte) 
	{
		// TODO Auto-generated method stub
		cardList.ajoute(carte);
		
	}

	@Override
	public boolean aHuitCarte() 
	{
		return cardList.size()==8;
	}

}
