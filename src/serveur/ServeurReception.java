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
private Serveur serveur;
	
	public ServeurReception(BufferedReader in, BeloteCoinche belote, Serveur serveur)
	{
		this.in = in;
		this.belote = belote;
		this.serveur = serveur;
	}
	
	public void run() 
	{
		try 
		{
			String msg = null;
			boolean test=true;
			while (test) 
			{
				msg = in.readLine();
				System.out.println(msg);
				if (msg!=null) 
				{
					if (msg.equals("carte"))
						this.newCarte();
					else if (msg.equals("coinche"))
						this.newCoinche();
					else if (msg.equals("annonce"))
						this.newAnnonce();
				}
				else
				{
					serveur.start();
					test=false;
				}
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
			if (valeur.equals("passe"))
				belote.setAnnonce(null);
			else
			{
				System.out.println("recuption annonce");
				String couleur =in.readLine();
				System.out.println(valeur+" "+couleur);
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
		System.out.println("JD coinche");
		MouvementEvent mouvement = new MouvementEvent("coinche");
		belote.nouveauGeste(mouvement);
	}

	private void newCarte() 
	{
		try 
		{
			String carte =in.readLine();
			String couleur ="";
			String valeur ="";
			if (carte.startsWith("trefle"))
			{
				couleur = "trefle";
				valeur = carte.substring(6);
			}
			else if (carte.startsWith("pique"))
			{
				couleur = "pique";
				valeur = carte.substring(5);
			}
			else if (carte.startsWith("coeur"))
			{
				couleur = "coeur";
				valeur = carte.substring(5);
			}
			else
			{
				couleur = "carreau";
				valeur = carte.substring(7);
			}
			
			Carte carteb = new Carte(valeur, couleur, (EtatDuJeu)belote.getEtat());
			belote.nouvelleCarteJoueurDistant(new CardEvent(carteb));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

}
