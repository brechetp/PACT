package structure;

import java.util.HashSet;
import java.util.Iterator;

public class CarteList extends HashSet<CarteInterface>
implements CarteListInterface 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CarteInterface carteMaitresse; 
	private CarteInterface firstCard;

	public void ajoute(CarteInterface carte) 
	{
		if (this.isEmpty())
		{
			firstCard=carte;
			carteMaitresse=carte;
		}
		
		this.add(carte);
		if (carteMaitresse.compare(carte)<0)
			carteMaitresse=carte;
	}

	public boolean contain(CarteInterface carte) 
	{
		return this.contains(carte);
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
