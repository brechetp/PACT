package camera;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import comparaison.Letter;
import comparaison.Symbol;



public class Initialisation {

	public static void setCardSize(String fileName) throws NumberFormatException, IOException{
		
		double[] res = new double[2];
	
		for(int i=0; i<2; i++){
			FileReader fis = new FileReader(fileName);
			BufferedReader bis = new BufferedReader (fis);
			String value;
			

			while((value = bis.readLine()) != null){
				res[i]=(Double.parseDouble(value));
				// Calcul de average et sigma 

			}
			bis.close();
		
			
		}


		Card.setWIDTH(res[0]);
		Card.setHEIGHT(res[1]);
		System.out.println("Taille initialisée à "+ Card.getWIDTH()+", "+Card.getHEIGHT()+".");
		

	}

	public static void setSymbolDatabase(String fileName){

		try{

			Symbol.setSymbolsDatabase(fileName);
		}
		catch(Exception e){ // probleme general

			e.printStackTrace();
		}
	}
	
	public static void setLetterDatabase(String fileName){

		try{

			Letter.setLetterDatabase(fileName);
		}
		catch(Exception e){ // probleme general

			e.printStackTrace();
		}
	}

}


