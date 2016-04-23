package structure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class CarteList extends ArrayList<CarteInterface>
implements CarteListInterface 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CarteInterface carteMaitresse; 
	private CarteInterface firstCard;
	
	public CarteList()
	{
		this.carteMaitresse = null;
		this.firstCard = null;
	}
	
	public void ajoute(CarteInterface carte) 
	{
		if (this.isEmpty())
		{
			firstCard=carte;
			carteMaitresse=carte;
			this.add(carte);
		}
		else if (carteMaitresse.compare(carte)<0)
		{
			carteMaitresse=carte;
			this.add(carte);
		}
		else this.add(carte);
	}

	@Override
	public boolean contain(CarteInterface carte) 
	{
		boolean retour= false;
		for(int i=0;i<size();i++)
		{
			if (get(i).getLabelNum().equals(carte.getLabelNum())&&get(i).getSuit().equals(carte.getSuit()))
				retour = true;
		}
		
		return retour;
	}

	public int getPoint() 
	{
		int point = 0;
		Iterator<CarteInterface> iterator = this.iterator();
		while (iterator.hasNext())
		{
			point = point + iterator.next().getValue();
		}
		return point;
	}

	public CarteInterface getPlusFort() 
	{
		return carteMaitresse;
	}

	@Override
	public String getFirstCardSuit() 
	{
		return firstCard.getSuit();
	}
	
	

}
