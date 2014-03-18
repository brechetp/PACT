package structure;

import java.util.ArrayList;

public class CircularArray<E> 
{
	
	private E[] array;
	
	
	public CircularArray (E[] array)
	{
		this.array=array;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param index dans la liste
	 * @return element a cet indice
	 */
	public E get(int index)
	{
		return array[index];
	}
	
	/**
	 * Déplace tout les éleemnts vers la gauche
	 */
	public void moveLeft()
	{
		E dernier = get(0);
		for (int i = 0;i<array.length-1;i++)
		{
			array[i]=array[i+1];
		}
		array[array.length]= dernier;
	}
	
	/**
	 * Deplace tout les elements vers la droite
	 */
	public void moveRight()
	{
		E premier = get(array.length);
		for (int i = array.length-1;i>0;i--)
		{
			array[i]=array[i-1];
		}
		array[0]= premier;
	}

}
