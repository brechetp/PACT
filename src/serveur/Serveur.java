package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	public static void main(String[] args) 
	{
	    Socket socket = null;
	    try 
	    {
	    	ServerSocket socketserver = new ServerSocket(6666);
	    	socket = socketserver.accept();
	    	System.out.println("Un zéro veut se connecter  ");
	    	socket.close();
	    	socketserver.close();
	    } catch (IOException e) 
	    {         
	    	System.err.println("Erreur serveur");
	    }
	        

	}

}
