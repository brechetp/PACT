package serveur;

import java.io.PrintWriter;
import java.util.Scanner;

public class ServeurEmission implements Runnable
{

	private PrintWriter out;
	private String msg;
	
	public ServeurEmission(PrintWriter out,String msg)
	{
		this.out=out;
		this.msg=msg;
	}
	
	public void run() 
	{
		Scanner sc = new Scanner(msg);
		while (sc.hasNext())
		{
			out.println(sc.next());
		}
	}

}
