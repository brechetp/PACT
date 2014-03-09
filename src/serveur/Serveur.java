package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import structure.AnnonceInterface;
import structure.CarteInterface;
import logiqueDeJeux.BeloteCoinche;

public class Serveur 
{
	private Socket socket=null;
	private BeloteCoinche belote;

	public Serveur(BeloteCoinche belote)
	{
		this.belote=belote;
	}
	
	public void start() 
	{
	    try 
	    {
	    	ServerSocket socketserver = new ServerSocket(6665);
	    	System.out.println("attente de conection");
	    	this.socket = socketserver.accept();
	    	System.out.println("connection etablie");
	    	BufferedReader
	    	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    	Thread t1 = new Thread(new ServeurReception(in, belote));
	    	t1.start();
	    } 
	    catch (IOException e) 
	    {         
	    	System.err.println("Erreur serveur");
	    }
	        
	}
	
	public void envoiCarteDistribution(CarteInterface carte)
	{
		try 
		{
			PrintWriter out =new PrintWriter(socket.getOutputStream());
			Thread t2 = new Thread(new ServeurEmission(out,"distribution "+carte.getLabelNum()+" "+carte.getSuit()));
			t2.start();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void envoiCarte(CarteInterface carte, int i)
	{
		try 
		{
			PrintWriter out =new PrintWriter(socket.getOutputStream());
			Thread t2 = new Thread(new ServeurEmission(out,"carte "+carte.getLabelNum()+" "+carte.getSuit()+" "+i));
			t2.start();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void envoiCoinche(int i)
	{
		try 
		{
			PrintWriter out =new PrintWriter(socket.getOutputStream());
			Thread t2 = new Thread(new ServeurEmission(out,"coinche "+i));
			t2.start();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void envoiTourAnnonce()
	{
		try 
		{
			PrintWriter out =new PrintWriter(socket.getOutputStream());
			Thread t2 = new Thread(new ServeurEmission(out,"a_toi_annonce"));
			t2.start();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void waitCarte()
	{
		try 
		{
			PrintWriter out =new PrintWriter(socket.getOutputStream());
			Thread t2 = new Thread(new ServeurEmission(out,"a_ton_tour"));
			t2.start();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void envoiPartieTerminer()
	{
		try 
		{
			PrintWriter out =new PrintWriter(socket.getOutputStream());
			Thread t2 = new Thread(new ServeurEmission(out,"fin_de_partie"));
			t2.start();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void envoiAnnonce(AnnonceInterface annonce, int i)
	{
		try 
		{
			PrintWriter out =new PrintWriter(socket.getOutputStream());
			if (annonce==null)
			{
				Thread t2 = new Thread(new ServeurEmission(out,"annonce passe "+i));
				t2.start();
			}
			else 
			{
				Thread t2 = new Thread(new ServeurEmission(out,"annonce "+annonce.getValue()+" "+annonce.getAtout()+" "+i));
				t2.start();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void envoiFinDeTour()
	{
		try 
		{
			PrintWriter out =new PrintWriter(socket.getOutputStream());
			Thread t2 = new Thread(new ServeurEmission(out,"fin_de_tour"));
			t2.start();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void envoiFinAnnonce()
	{
		try 
		{
			PrintWriter out =new PrintWriter(socket.getOutputStream());
			Thread t2 = new Thread(new ServeurEmission(out,"fin_annonce"));
			t2.start();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
