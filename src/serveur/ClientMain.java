package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {

	public static void main(String[] args) 
	{
		try {
			Socket clientSocket = new Socket("localhost", 6665);
			PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
			String msg;
			Thread.sleep(100000);
//			System.out.println("envoi carte");
//			outToServer.println("carte");
//			outToServer.println("10");
//			outToServer.println("carreau");
//			outToServer.flush();
//			Thread.sleep(10000);
//			outToServer.println("annonce");
//			outToServer.println("100");
//			outToServer.println("carreau");
//			outToServer.flush();
			clientSocket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
