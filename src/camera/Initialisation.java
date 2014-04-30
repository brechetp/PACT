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

		FileReader fis = new FileReader(fileName);
		BufferedReader bis = new BufferedReader (fis);
		String value;
		int i=0;

		while((value = bis.readLine()) != null){
			res[i]=(Double.parseDouble(value));
			i++;
		}// 


		bis.close();





		Card.setWIDTH(res[0]);
		Card.setHEIGHT(res[1]);
		System.out.println("Taille initialisée à "+ Card.getWIDTH()+", "+Card.getHEIGHT()+".");

	}

	public static void setSymbolDatabase(String fileName) throws NumberFormatException, IOException{



		Symbol.setSymbolsDatabase(fileName);

	}

	public static void setLetterDatabase(String fileName) throws IOException{



		Letter.setLetterDatabase(fileName);


	}

}


