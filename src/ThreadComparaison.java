import structure.Carte;
import logiqueDeJeux.BeloteCoinche;
import machineEtat.CardEvent;
import camera.Card;
import camera.Database;


public class ThreadComparaison implements Runnable
{
	private Card carte;
	private BeloteCoinche belote;
	
	public ThreadComparaison(Card carte, BeloteCoinche belote)
	{
		this.carte = carte;
		this.belote = belote;
	}
	@Override
	public void run() 
	{
		envoyerCarteBelote(carte.find(Database.database));
	}
	
	
	private void envoyerCarteBelote(String string)
	{
		System.out.println(string);
		if (string.endsWith("1.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("as","pique", belote.getEtat())));
		else if (string.endsWith("2.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("as","pique", belote.getEtat())));
		else if (string.endsWith("3.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("as","trefle", belote.getEtat())));
		else if (string.endsWith("4.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("as","trefle", belote.getEtat())));
		else if (string.endsWith("5.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("7","pique", belote.getEtat())));
		else if (string.endsWith("6.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("7","pique", belote.getEtat())));
		else if (string.endsWith("7.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("7","trefle", belote.getEtat())));
		else if (string.endsWith("8.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("7","trefle", belote.getEtat())));
		else if (string.endsWith("9.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("8","pique", belote.getEtat())));
		else if (string.endsWith("10.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("8","pique", belote.getEtat())));
		else if (string.endsWith("11.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("8","trefle", belote.getEtat())));
		else if (string.endsWith("12.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("8","trefle", belote.getEtat())));
		else if (string.endsWith("13.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("9","pique", belote.getEtat())));
		else if (string.endsWith("14.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("9","pique", belote.getEtat())));
		else if (string.endsWith("15.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("9","trefle", belote.getEtat())));
		else if (string.endsWith("16.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("9","trefle", belote.getEtat())));
		else if (string.endsWith("17.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("10","pique", belote.getEtat())));
		else if (string.endsWith("18.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("10","pique", belote.getEtat())));
		else if (string.endsWith("19.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("10","trefle", belote.getEtat())));
		else if (string.endsWith("20.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("10","trefle", belote.getEtat())));
		else if (string.endsWith("21.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("valet","pique", belote.getEtat())));
		else if (string.endsWith("22.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("valet","pique", belote.getEtat())));
		else if (string.endsWith("23.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("valet","trefle", belote.getEtat())));
		else if (string.endsWith("24.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("valet","trefle", belote.getEtat())));
		else if (string.endsWith("25.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("reine","pique", belote.getEtat())));
		else if (string.endsWith("26.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("reine","pique", belote.getEtat())));
		else if (string.endsWith("27.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("reine","trefle", belote.getEtat())));
		else if (string.endsWith("28.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("reine","trefle", belote.getEtat())));
		else if (string.endsWith("29.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("roi","pique", belote.getEtat())));
		else if (string.endsWith("30.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("roi","pique", belote.getEtat())));
		else if (string.endsWith("31.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("roi","trefle", belote.getEtat())));
		else if (string.endsWith("32.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("roi","trefle", belote.getEtat())));
		else if (string.endsWith("33.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("as","coeur", belote.getEtat())));
		else if (string.endsWith("34.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("as","coeur", belote.getEtat())));
		else if (string.endsWith("35.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("as","carreau", belote.getEtat())));
		else if (string.endsWith("36.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("as","carreau", belote.getEtat())));
		else if (string.endsWith("37.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("7","coeur", belote.getEtat())));
		else if (string.endsWith("38.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("7","coeur", belote.getEtat())));
		else if (string.endsWith("39.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("7","carreau", belote.getEtat())));
		else if (string.endsWith("40.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("7","carreau", belote.getEtat())));
		else if (string.endsWith("41.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("8","coeur", belote.getEtat())));
		else if (string.endsWith("42.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("8","coeur", belote.getEtat())));
		else if (string.endsWith("43.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("8","carreau", belote.getEtat())));
		else if (string.endsWith("44.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("8","carreau", belote.getEtat())));
		else if (string.endsWith("45.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("9","coeur", belote.getEtat())));
		else if (string.endsWith("46.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("9","coeur", belote.getEtat())));
		else if (string.endsWith("47.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("9","carreau", belote.getEtat())));
		else if (string.endsWith("48.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("9","carreau", belote.getEtat())));
		else if (string.endsWith("49.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("10","coeur", belote.getEtat())));
		else if (string.endsWith("50.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("10","coeur", belote.getEtat())));
		else if (string.endsWith("51.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("10","carreau", belote.getEtat())));
		else if (string.endsWith("52.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("10","carreau", belote.getEtat())));
		else if (string.endsWith("53.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("valet","coeur", belote.getEtat())));
		else if (string.endsWith("54.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("valet","coeur", belote.getEtat())));
		else if (string.endsWith("55.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("valet","carreau", belote.getEtat())));
		else if (string.endsWith("56.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("valet","carreau", belote.getEtat())));
		else if (string.endsWith("57.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("reine","coeur", belote.getEtat())));
		else if (string.endsWith("58.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("reine","coeur", belote.getEtat())));
		else if (string.endsWith("59.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("reine","carreau", belote.getEtat())));
		else if (string.endsWith("60.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("reine","carreau", belote.getEtat())));
		else if (string.endsWith("61.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("roi","coeur", belote.getEtat())));
		else if (string.endsWith("62.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("roi","coeur", belote.getEtat())));
		else if (string.endsWith("63.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("roi","carreau", belote.getEtat())));
		else if (string.endsWith("64.jpg"))
			belote.nouvelleCarte(new CardEvent(new Carte("roi","carreau", belote.getEtat())));
	}
	
}
