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
			String msg=null;
			boolean msgrecu = false;
			while(!msgrecu)
			{
				msg = in.readLine();
				if (msg!=null)
					msgrecu = true;
			}
			if (msg=="carte")
				this.newCarte();
			else if (msg=="coinche")
				this.newCoinche();
			else if (msg=="annonce")
				this.newAnnonce();
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
			String couleur =in.readLine();
			Annonce annonce = new Annonce(couleur, Integer.parseInt(valeur), -1);
			belote.setAnnonce(annonce);
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
			belote.nouvelleCarte(new CardEvent(carte));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

}
