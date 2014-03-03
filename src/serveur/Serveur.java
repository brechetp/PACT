package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import logiqueDeJeux.BeloteCoinche;

public class Serveur 
{
	private Socket socket=null;
	private BeloteCoinche belote;

	public void start() 
	{
	    try 
	    {
	    	ServerSocket socketserver = new ServerSocket(6665);
	    	this.socket = socketserver.accept();
	    	BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    	Thread t1 = new Thread(new ServeurReception(in, belote));
	    	t1.start();
	    } 
	    catch (IOException e) 
	    {         
	    	System.err.println("Erreur serveur");
	    }
	        
	}
	
	
	
}
