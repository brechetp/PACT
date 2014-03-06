package adaBoost;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import leapmotion.main.ListeDeMouvements;

public class BaseApprentissage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double[][] base = new double[287][9] ;
		double[][] base_test = new double[62][9];		
		ArrayList<File> tableau_fichiers = new ArrayList<File>() ;
		
		tableau_fichiers.add(new File(".Gestes/Geste0 (passer)/Geste0 Edouard M (passer).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste0 (passer)/Geste0 Michael (passer).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste0 (passer)/Geste0 Pierre (passer).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste0 (passer)/Geste0 Tim (passer).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste1 (retour)/Geste1 Benjamin (retour).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste1 (retour)/Geste1 Edouard M (retour).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste1 (retour)/Geste1 Michael (retour).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste1 (retour)/Geste1 Pierre (retour).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste1 (retour)/Geste1 Tim (retour).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste2 (coinche)/Geste2 Benjamin (coinche).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste2 (coinche)/Geste2 Edouard E (coinche).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste2 (coinche)/Geste2 Michael (coinche).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste2 (coinche)/Geste2 Pierre (coinche).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste2 (coinche)/Geste2 Tim (coinche).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste3 (accepter)/Geste3 Benjamin (accepter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste3 (accepter)/Geste3 Edouard E (accepter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste3 (accepter)/Geste3 Edouard M (accepter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste3 (accepter)/Geste3 Pierre (accepter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste3 (accepter)/Geste3 Tim (accepter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste4 (quitter)/Geste4 Benjamin (quitter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste4 (quitter)/Geste4 Edouard E (quitter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste4 (quitter)/Geste4 Edouard M (quitter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste4 (quitter)/Geste4 Michael (quitter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste4 (quitter)/Geste4 Tim (quitter).ser"));

		int compteur = 0 ;
		
		try
		{
			for (int k = 0 ; k < 24 ; k++)
			{
				ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(tableau_fichiers.get(k))) ;
				ListeDeMouvements hyk = (ListeDeMouvements)ois.readObject();
    		
				int nb_exemples = hyk.size() ;
				System.out.println("Nombre exemples dans fichier " + k + " = " + nb_exemples) ;
				
				for (int i = 0 ; i < nb_exemples ; i++)
				{					
					int nb_frames = hyk.get(i).getSize() ;
					
					double[] descripteurs = new double[9] ;
    			
					for (int j = 5 ; j < nb_frames ; j++) //On enlève les 5 premières frames pour l'entrée du geste dans la zone
					{
						
						descripteurs[0] =+ hyk.get(i).get(j).get(0) ; //nombre de mains
						descripteurs[1] =+ hyk.get(i).get(j).get(7) ; //nombre de doigts
						
						double X1 = hyk.get(i).get(j).get(38) ; double X2 = hyk.get(i).get(j-1).get(38) ;
						double Y1 = hyk.get(i).get(j).get(39) ; double Y2 = hyk.get(i).get(j-1).get(39) ;
						double Z1 = hyk.get(i).get(j).get(40) ; double Z2 = hyk.get(i).get(j-1).get(39) ;
						double Norme_normal = Math.sqrt(X1*X1+Y1*Y1+Z1*Z1) ;
						descripteurs[2] =+ X1; //normale X
    					descripteurs[3] =+ Y1; //normale Y
    					descripteurs[4] =+ Z1; //normale Z
    					
    					double X = hyk.get(i).get(j).get(50) ;
    					double Y = hyk.get(i).get(j).get(51) ;
    					double Z = hyk.get(i).get(j).get(52) ;
    					double Norme_translation = Math.sqrt(X1*X1+Y1*Y1+Z1*Z1) ;
    					descripteurs[5] =+ X; //translation X
    					descripteurs[6] =+ Y; //translation Y
    					descripteurs[7] =+ Z; //translation Z
    					
    					descripteurs[8] =+ Math.acos(X1*X2 + Y1*Y2 + Z1*Z2) ; //rotation
    				
					}
					
					
					for (int m = 0 ; m < 9 ; m++)
					{
						descripteurs[m] = descripteurs[m] / nb_frames ;
						base[compteur][m] = descripteurs[m] ;
					}
			
					compteur++ ;
    			}
				ois.close() ;
			}
			
			
			ArrayList<File> tableau_fichiers_test = new ArrayList<File>() ;
			
			tableau_fichiers_test.add(new File("./Gestes/Geste0 (passer)/Geste0 Benjamin (passer).ser"));
			tableau_fichiers_test.add(new File("./Gestes/Geste1 (retour)/Geste1 Edouard E (retour).ser"));
			tableau_fichiers_test.add(new File("./Gestes/Geste2 (coinche)/Geste2 Edouard M (coinche).ser"));
			tableau_fichiers_test.add(new File("./Gestes/Geste3 (accepter)/Geste3 Michael (accepter).ser"));
			tableau_fichiers_test.add(new File("./Gestes/Geste4 (quitter)/Geste4 Pierre (quitter).ser"));
			
			int compteur_test = 0 ;
			
			for (int k = 0 ; k < 5 ; k++)
			{
				ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(tableau_fichiers_test.get(k))) ;
				ListeDeMouvements hyk = (ListeDeMouvements)ois.readObject();
	    	
				int nb_exemples = hyk.size() ;
				System.out.println("Nombre exemples dans fichier test " + k + " = " + nb_exemples) ;
					
				for (int i = 0 ; i < nb_exemples ; i++)
				{					
					int nb_frames = hyk.get(i).getSize() ;
						
					double[] descripteurs = new double[9] ;
	    		
					for (int j = 5 ; j < nb_frames ; j++) //On enlève les 5 premières frames pour l'entrée du geste dans la zone
					{
							
						descripteurs[0] =+ hyk.get(i).get(j).get(0) ; //nombre de mains
						descripteurs[1] =+ hyk.get(i).get(j).get(7) ; //nombre de doigts
							
						double X1 = hyk.get(i).get(j).get(38) ; double X2 = hyk.get(i).get(j-1).get(38) ;
						double Y1 = hyk.get(i).get(j).get(39) ; double Y2 = hyk.get(i).get(j-1).get(39) ;
						double Z1 = hyk.get(i).get(j).get(40) ; double Z2 = hyk.get(i).get(j-1).get(39) ;
						double Norme_normal = Math.sqrt(X1*X1+Y1*Y1+Z1*Z1) ;
						descripteurs[2] =+ X1 ; //normale X
    					descripteurs[3] =+ Y1 ; //normale Y
    					descripteurs[4] =+ Z1 ; //normale Z
    					
    					double X = hyk.get(i).get(j).get(50) ;
    					double Y = hyk.get(i).get(j).get(51) ;
    					double Z = hyk.get(i).get(j).get(52) ;
    					double Norme_translation = Math.sqrt(X1*X1+Y1*Y1+Z1*Z1) ;
    					descripteurs[5] =+ X ; //translation X
    					descripteurs[6] =+ Y ; //translation Y
    					descripteurs[7] =+ Z ; //translation Z
	    				
	    				descripteurs[8] =+ Math.acos(X1*X2 + Y1*Y2 + Z1*Z2) ; //rotation
	    			
					}
					
					for (int m = 0 ; m < 9 ; m++)
					{
						descripteurs[m] = descripteurs[m] / nb_frames ;
						base_test[compteur_test][m] = descripteurs[m] ;
					}
					
					compteur_test++ ;
	    		}
				ois.close() ;
			}
    	}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		mainada2 test = new mainada2();
		test.main(base,base_test);
	}

}
