package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import structure.Annonce;
import structure.Carte;
import logiqueDeJeux.BeloteCoinche;
import logiqueDeJeux.EtatDuJeu;
import machineEtat.CardEvent;
import machineEtat.MouvementEvent;

public class ServeurReception implements Runnable
{
	private BufferedReader in;
	private BeloteCoinche belote;
	
	public ServeurReception(BufferedReader in, BeloteCoinche belote)
	{
		this.in = in;
		this.belote = belote;
	}
	
	public void run() 
	{
		try 
		{
			while (true) 
			{
				String msg = null;;
				msg = in.readLine();
				System.out.println(msg);
				if (msg.equals("carte"))
					this.newCarte();
				else if (msg.equals("coinche"))
					this.newCoinche();
				else if (msg.equals("annonce"))
					this.newAnnonce();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		
	}

	private void newAnnonce() 
	{
		try 
		{
			String valeur =in.readLine();
			if (valeur.equals("passer"))
				belote.nouveauGeste(new MouvementEvent("passer"));
			else
			{
				System.out.println("recuption annonce");
				String couleur =in.readLine();
				Annonce annonce = new Annonce(couleur, Integer.parseInt(valeur), -1);
				belote.setAnnonce(annonce);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	private void newCoinche() 
	{
		MouvementEvent mouvement = new MouvementEvent("coinche");
		belote.nouveauGeste(mouvement);
	}

	private void newCarte() 
	{
		try 
		{
			String valeur =in.readLine();
			String couleur =in.readLine();
			Carte carte = new Carte(valeur, couleur, (EtatDuJeu)belote.getEtat());
			System.out.println("carte recu");
			belote.nouvelleCarte(new CardEvent(carte));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

}
