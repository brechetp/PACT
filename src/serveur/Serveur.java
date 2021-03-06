package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import structure.AnnonceInterface;
import structure.CarteInterface;
import logiqueDeJeux.BeloteCoinche;

public class Serveur 
{
	private Socket socket=null;
	private BeloteCoinche belote;
	private ServerSocket socketserver;

	public Serveur(BeloteCoinche belote)
	{
		this.belote=belote;
	}
	
	public void start() throws IOException
	{

		socketserver = new ServerSocket(6665);
		System.out.println("attente de connexion");
		socketserver.setSoTimeout(15000);
		try {
			socket = socketserver.accept();
		} catch (SocketTimeoutException e) {
			socketserver.close();
			throw new SocketTimeoutException();
		}
		socket.setSoTimeout(0);
		socketserver.setSoTimeout(0);
		System.out.println("connexion etablie");
		BufferedReader
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		Thread t1 = new Thread(new ServeurReception(in, belote, this));
		t1.start();
	    
	        
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
	
	public void envoiPartieTerminer(int i)
	{
		try 
		{
			PrintWriter out =new PrintWriter(socket.getOutputStream());
			Thread t2 = new Thread(new ServeurEmission(out,"fin_de_partie "+i));
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
				Thread t2 = new Thread(new ServeurEmission(out,"annonce "+annonce.getAtout()+" "+annonce.getValue()+" "+i));
				t2.start();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void envoiFinManche(int joueurSuivant)
	{
		try {
			PrintWriter out =new PrintWriter(socket.getOutputStream());
			Thread t2 = new Thread(new ServeurEmission(out,"fin_de_manche "+joueurSuivant));
			t2.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void envoiFinDeTour(int joueurGagne)
	{
		try 
		{
			PrintWriter out =new PrintWriter(socket.getOutputStream());
			Thread t2 = new Thread(new ServeurEmission(out,"fin_de_pli "+joueurGagne));
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

	public void envoiScore(int i, int j, int pointsTeamPair,
			int pointsTeamImpair) 
	{
		try 
		{
			PrintWriter out =new PrintWriter(socket.getOutputStream());
			Thread t2 = new Thread(new ServeurEmission(out,"score_partie "+pointsTeamPair+" "+pointsTeamImpair+" score_manche "+i+" "+j));
			t2.start();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

	public void stop() 
	{
		try {
			socket.close();
			socketserver.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
