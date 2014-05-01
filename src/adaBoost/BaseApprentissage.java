package adaBoost;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import leapmotion.main.ListeDeMouvements;

public class BaseApprentissage {

	public static ClassiFinal[] main(String[] args) {
		// TODO Auto-generated method stub
		
		double[][] base = new double[331][14] ;	
		double[][] base_test = new double[54][14];	
		ArrayList<File> tableau_fichiers = new ArrayList<File>() ;
		
		tableau_fichiers.add(new File("./Gestes/Geste0 (passer)/Geste0 Benjamin (passer).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste0 (passer)/Geste0 Edouard M (passer).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste0 (passer)/Geste0 Michael (passer).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste0 (passer)/Geste0 Pierre (passer).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste0 (passer)/Geste0 Tim (passer).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste1 (retour)/Geste1 Benjamin (retour).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste1 (retour)/Geste1 Edouard E (retour).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste1 (retour)/Geste1 Edouard M (retour).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste1 (retour)/Geste1 Michael (retour).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste1 (retour)/Geste1 Pierre (retour).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste1 (retour)/Geste1 Tim (retour).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste2 (coinche)/Geste2 Benjamin (coinche).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste2 (coinche)/Geste2 Edouard E (coinche).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste2 (coinche)/Geste2 Edouard M (coinche).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste2 (coinche)/Geste2 Michael (coinche).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste2 (coinche)/Geste2 Pierre (coinche).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste2 (coinche)/Geste2 Tim (coinche).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste3 (accepter)/Geste3 Benjamin (accepter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste3 (accepter)/Geste3 Edouard E (accepter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste3 (accepter)/Geste3 Edouard M (accepter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste3 (accepter)/Geste3 Michael (accepter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste3 (accepter)/Geste3 Pierre (accepter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste3 (accepter)/Geste3 Tim (accepter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste4 (quitter)/Geste4 Benjamin (quitter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste4 (quitter)/Geste4 Edouard E (quitter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste4 (quitter)/Geste4 Edouard M (quitter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste4 (quitter)/Geste4 Julia (quitter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste4 (quitter)/Geste4 Michael (quitter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste4 (quitter)/Geste4 Pierre (quitter).ser"));
		tableau_fichiers.add(new File("./Gestes/Geste4 (quitter)/Geste4 Tim (quitter).ser"));

		int compteur = 0 ;
		
		try
		{
			for (int k = 0 ; k < 30 ; k++)
			{
				ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(tableau_fichiers.get(k))) ;
				ListeDeMouvements hyk = (ListeDeMouvements)ois.readObject();
    		
				int nb_exemples = hyk.size() ;
				System.out.println("Nombre exemples dans fichier " + k + " = " + nb_exemples) ;
				
				for (int i = 0 ; i < nb_exemples ; i++)
				{					
					int nb_frames = hyk.get(i).getSize() ;
					//System.out.println(nb_frames + " frames dans l'exemple " + i + " du fichier " + k);
					
					double[] descripteurs = new double[Classification.NOMBRE_DE_FEATURES] ;
    			
					for (int j = 5 ; j < nb_frames ; j++) //On enl�ve les 5 premi�res frames pour l'entr�e du geste dans la zone
					{
						
						descripteurs[0] =+ hyk.get(i).get(j).get(0) ; //nombre de mains
						descripteurs[1] =+ hyk.get(i).get(j).get(7) ; //nombre de doigts de la main 1
						
						double X_normale1 = hyk.get(i).get(j).get(38) ; 
						double Y_normale1 = hyk.get(i).get(j).get(39) ; 
						double Z_normale1 = hyk.get(i).get(j).get(40) ; 
						double Norme_normale1 = Math.sqrt(X_normale1*X_normale1+Y_normale1*Y_normale1+Z_normale1*Z_normale1) ;
						if (descripteurs[0]>0) {
							descripteurs[2] = +X_normale1 / Norme_normale1; //normale X de la main 1
							descripteurs[3] = +Y_normale1 / Norme_normale1; //normale Y de la main 1
							descripteurs[4] = +Z_normale1 / Norme_normale1; //normale Z de la main 1
						
						double X_translation1 = hyk.get(i).get(j).get(50) ;
    					double Y_translation1 = hyk.get(i).get(j).get(51) ;
    					double Z_translation1 = hyk.get(i).get(j).get(52) ;
    					double Norme_translation1 = Math.sqrt(X_translation1*X_translation1+Y_translation1*Y_translation1+Z_translation1*Z_translation1) ;
    					descripteurs[8] =+ X_translation1/Norme_translation1; //translation X de la main 1
						descripteurs[9] =+ Y_translation1/Norme_translation1; //translation Y de la main 1
						descripteurs[10] =+ Z_translation1/Norme_translation1; //translation Z de la main 1
						
						// On r�cup�re la normale lors de la frame pr�c�dente pour calculer la rotation
    					double X_normalePrecedente1 = hyk.get(i).get(j-1).get(38) ;
    					double Y_normalePrecedente1 = hyk.get(i).get(j-1).get(39) ;
    					double Z_normalePrecedente1 = hyk.get(i).get(j-1).get(40) ;
    					double Norme_normalePrecedente1 = Math.sqrt(X_normalePrecedente1*X_normalePrecedente1+Y_normalePrecedente1*Y_normalePrecedente1+Z_normalePrecedente1*Z_normalePrecedente1) ;
    					
    					//descripteurs[14] =+ Math.acos((descripteurs[2]*X_normalePrecedente1 + descripteurs[3]*Y_normalePrecedente1 + descripteurs[4]*Z_normalePrecedente1)/Norme_normalePrecedente1 ) ; //rotation de la main 1
						}
    					/* Informations de la 2eme main */
    					double X_normale2 = hyk.get(i).get(j).get(41) ; 
						double Y_normale2 = hyk.get(i).get(j).get(42) ; 
						double Z_normale2 = hyk.get(i).get(j).get(43) ; 
						double Norme_normale2 = Math.sqrt(X_normale2*X_normale2+Y_normale2*Y_normale2+Z_normale2*Z_normale2) ;
						if (descripteurs[0]>1) {
							descripteurs[5] = +X_normale2 / Norme_normale2; //normale X de la main 2
							descripteurs[6] = +Y_normale2 / Norme_normale2; //normale Y de la main 2
							descripteurs[7] = +Z_normale2 / Norme_normale2; //normale Z de la main 2
							double X_translation2 = hyk.get(i).get(j).get(53);
							double Y_translation2 = hyk.get(i).get(j).get(54);
							double Z_translation2 = hyk.get(i).get(j).get(55);
							double Norme_translation2 = Math
									.sqrt(X_translation2 * X_translation2
											+ Y_translation2 * Y_translation2
											+ Z_translation2 * Z_translation2);
							descripteurs[11] = +X_translation2
									/ Norme_translation2; //translation X de la main 2
							descripteurs[12] = +Y_translation2
									/ Norme_translation2; //translation Y de la main 2
							descripteurs[13] = +Z_translation2
									/ Norme_translation2; //translation Z de la main 2
						}
					}
					
					
					for (int m = 0 ; m < Classification.NOMBRE_DE_FEATURES ; m++)
					{
						descripteurs[m] = descripteurs[m] / nb_frames ;
						base[compteur][m] = descripteurs[m] ;
					}
			
					compteur++ ;
    			}
				ois.close() ;
			}
			
			
			/**ArrayList<File> tableau_fichiers_test = new ArrayList<File>() ;
			
			tableau_fichiers_test.add(new File("./Gestes/Geste0 (passer)/Geste0 Tim (passer).ser"));
			tableau_fichiers_test.add(new File("./Gestes/Geste1 (retour)/Geste1 Benjamin (retour).ser"));
			tableau_fichiers_test.add(new File("./Gestes/Geste2 (coinche)/Geste2 Edouard E (coinche).ser"));
			tableau_fichiers_test.add(new File("./Gestes/Geste3 (accepter)/Geste3 Edouard M (accepter).ser"));
			tableau_fichiers_test.add(new File("./Gestes/Geste4 (quitter)/Geste4 Michael (quitter).ser"));
			
			int compteur_test = 0 ;
			
			for (int k = 0 ; k < 4 ; k++)
			{
				ObjectInputStream ois_test =  new ObjectInputStream(new FileInputStream(tableau_fichiers_test.get(k))) ;
				ListeDeMouvements hyk_test = (ListeDeMouvements)ois_test.readObject();
	    	
				int nb_exemples_test = hyk_test.size() ;
				System.out.println("Nombre exemples dans fichier test " + k + " = " + nb_exemples_test) ;
					
				for (int i = 0 ; i < nb_exemples_test ; i++)
				{					
					int nb_frames_test = hyk_test.get(i).getSize() ;
						
					double[] descripteurs_test = new double[Classification.NOMBRE_DE_FEATURES] ;
	    		
					for (int j = 5 ; j < nb_frames_test ; j++) //On enl�ve les 5 premi�res frames pour l'entr�e du geste dans la zone
					{
						descripteurs_test[0] =+ hyk_test.get(i).get(j).get(0) ; //nombre de mains test
						descripteurs_test[1] =+ hyk_test.get(i).get(j).get(7) ; //nombre de doigts test
							
						double X_normale1_test = hyk_test.get(i).get(j).get(38) ;
						double Y_normale1_test = hyk_test.get(i).get(j).get(39) ;
						double Z_normale1_test = hyk_test.get(i).get(j).get(40) ;
						double Norme_normale1_test = Math.sqrt(X_normale1_test*X_normale1_test+Y_normale1_test*Y_normale1_test+Z_normale1_test*Z_normale1_test) ;
						if (descripteurs_test[0]>0) {
							descripteurs_test[2] = +X_normale1_test
									/ Norme_normale1_test; //normale X de la main 1 test
							descripteurs_test[3] = +Y_normale1_test
									/ Norme_normale1_test; //normale Y de la main 1 test
							descripteurs_test[4] = +Z_normale1_test
									/ Norme_normale1_test; //normale Z de la main 1 test
							double X_translation1_test = hyk_test.get(i).get(j)
									.get(50);
							double Y_translation1_test = hyk_test.get(i).get(j)
									.get(51);
							double Z_translation1_test = hyk_test.get(i).get(j)
									.get(52);
							double Norme_translation1_test = Math
									.sqrt(X_translation1_test
											* X_translation1_test
											+ Y_translation1_test
											* Y_translation1_test
											+ Z_translation1_test
											* Z_translation1_test);
							descripteurs_test[8] = +X_translation1_test
									/ Norme_translation1_test; //translation X de la main 1 test
							descripteurs_test[9] = +Y_translation1_test
									/ Norme_translation1_test; //translation Y de la main 1 test
							descripteurs_test[10] = +Z_translation1_test
									/ Norme_translation1_test; //translation Z de la main 1 test
							//descripteurs_test[14] = +Math.acos(descripteurs_test[2]* descripteurs_test[2]
							//		+ descripteurs_test[3]
							//				* descripteurs_test[3]
							//						+ descripteurs_test[4]
							//								* descripteurs_test[4]); //rotation
						}
						if (descripteurs_test[0]>1) { **/
							/* Informations de la 2eme main */
						/**	double X_normale2_test = hyk_test.get(i).get(j)
									.get(41);
							double Y_normale2_test = hyk_test.get(i).get(j)
									.get(42);
							double Z_normale2_test = hyk_test.get(i).get(j)
									.get(43);
							double Norme_normale2_test = Math
									.sqrt(X_normale2_test * X_normale2_test
											+ Y_normale2_test * Y_normale2_test
											+ Z_normale2_test * Z_normale2_test);
							descripteurs_test[5] = +X_normale2_test
									/ Norme_normale2_test; //normale X de la main 2 test
							descripteurs_test[6] = +Y_normale2_test
									/ Norme_normale2_test; //normale Y de la main 2 test
							descripteurs_test[7] = +Z_normale2_test
									/ Norme_normale2_test; //normale Z de la main 2 test
							double X_translation2_test = hyk_test.get(i).get(j)
									.get(53);
							double Y_translation2_test = hyk_test.get(i).get(j)
									.get(54);
							double Z_translation2_test = hyk_test.get(i).get(j)
									.get(55);
							double Norme_translation2_test = Math
									.sqrt(X_translation2_test
											* X_translation2_test
											+ Y_translation2_test
											* Y_translation2_test
											+ Z_translation2_test
											* Z_translation2_test);
							descripteurs_test[11] = +X_translation2_test
									/ Norme_translation2_test; //translation X de la main 2 test
							descripteurs_test[12] = +Y_translation2_test
									/ Norme_translation2_test; //translation Y de la main 2 test
							descripteurs_test[13] = +Z_translation2_test
									/ Norme_translation2_test; //translation Z de la main 2 test
						}
					}
					
					for (int m = 0 ; m < Classification.NOMBRE_DE_FEATURES ; m++)
					{
						descripteurs_test[m] = descripteurs_test[m] / nb_frames_test ;
						base_test[compteur_test][m] = descripteurs_test[m] ;
					}
					
					compteur_test++ ;
	    		}
				ois_test.close() ;
			}**/
    	}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		mainada2 test = new mainada2();
		ClassiFinal[] classi = test.main(base,base_test);
		
		/******************************** Classi passer vs accepter ************************************/
		
		double[][] basevs = new double[62+78][14];
		try {
			compteur = 0 ;
			for (int k = 5; k<5+6;k++)
			{
				ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(tableau_fichiers.get(k))) ;
				ListeDeMouvements hyk = (ListeDeMouvements)ois.readObject();
				int nb_exemples = hyk.size() ;
				System.out.println("geste "+k+": "+hyk.size()+" exemples");
				
				for (int i = 0 ; i < nb_exemples ; i++)
				{					
					int nb_frames = hyk.get(i).getSize() ;
					//System.out.println(nb_frames + " frames dans l'exemple " + i + " du fichier " + k);
					
					double[] descripteurs = new double[Classification.NOMBRE_DE_FEATURES] ;
    			
					for (int j = 5 ; j < nb_frames ; j++) //On enl�ve les 5 premi�res frames pour l'entr�e du geste dans la zone
					{
						
						descripteurs[0] =+ hyk.get(i).get(j).get(0) ; //nombre de mains
						descripteurs[1] =+ hyk.get(i).get(j).get(7) ; //nombre de doigts de la main 1
						
						double X_normale1 = hyk.get(i).get(j).get(38) ; 
						double Y_normale1 = hyk.get(i).get(j).get(39) ; 
						double Z_normale1 = hyk.get(i).get(j).get(40) ; 
						double Norme_normale1 = Math.sqrt(X_normale1*X_normale1+Y_normale1*Y_normale1+Z_normale1*Z_normale1) ;
						if (descripteurs[0]>0) {
							descripteurs[2] = +X_normale1 / Norme_normale1; //normale X de la main 1
							descripteurs[3] = +Y_normale1 / Norme_normale1; //normale Y de la main 1
							descripteurs[4] = +Z_normale1 / Norme_normale1; //normale Z de la main 1
						
						double X_translation1 = hyk.get(i).get(j).get(50) ;
    					double Y_translation1 = hyk.get(i).get(j).get(51) ;
    					double Z_translation1 = hyk.get(i).get(j).get(52) ;
    					double Norme_translation1 = Math.sqrt(X_translation1*X_translation1+Y_translation1*Y_translation1+Z_translation1*Z_translation1) ;
    					descripteurs[8] =+ X_translation1/Norme_translation1; //translation X de la main 1
						descripteurs[9] =+ Y_translation1/Norme_translation1; //translation Y de la main 1
						descripteurs[10] =+ Z_translation1/Norme_translation1; //translation Z de la main 1
						
						// On r�cup�re la normale lors de la frame pr�c�dente pour calculer la rotation
    					double X_normalePrecedente1 = hyk.get(i).get(j-1).get(38) ;
    					double Y_normalePrecedente1 = hyk.get(i).get(j-1).get(39) ;
    					double Z_normalePrecedente1 = hyk.get(i).get(j-1).get(40) ;
    					double Norme_normalePrecedente1 = Math.sqrt(X_normalePrecedente1*X_normalePrecedente1+Y_normalePrecedente1*Y_normalePrecedente1+Z_normalePrecedente1*Z_normalePrecedente1) ;
    					
    					//descripteurs[14] =+ Math.acos((descripteurs[2]*X_normalePrecedente1 + descripteurs[3]*Y_normalePrecedente1 + descripteurs[4]*Z_normalePrecedente1)/Norme_normalePrecedente1 ) ; //rotation de la main 1
						}
    					/* Informations de la 2eme main */
    					double X_normale2 = hyk.get(i).get(j).get(41) ; 
						double Y_normale2 = hyk.get(i).get(j).get(42) ; 
						double Z_normale2 = hyk.get(i).get(j).get(43) ; 
						double Norme_normale2 = Math.sqrt(X_normale2*X_normale2+Y_normale2*Y_normale2+Z_normale2*Z_normale2) ;
						if (descripteurs[0]>1) {
							descripteurs[5] = +X_normale2 / Norme_normale2; //normale X de la main 2
							descripteurs[6] = +Y_normale2 / Norme_normale2; //normale Y de la main 2
							descripteurs[7] = +Z_normale2 / Norme_normale2; //normale Z de la main 2
							double X_translation2 = hyk.get(i).get(j).get(53);
							double Y_translation2 = hyk.get(i).get(j).get(54);
							double Z_translation2 = hyk.get(i).get(j).get(55);
							double Norme_translation2 = Math
									.sqrt(X_translation2 * X_translation2
											+ Y_translation2 * Y_translation2
											+ Z_translation2 * Z_translation2);
							descripteurs[11] = +X_translation2
									/ Norme_translation2; //translation X de la main 2
							descripteurs[12] = +Y_translation2
									/ Norme_translation2; //translation Y de la main 2
							descripteurs[13] = +Z_translation2
									/ Norme_translation2; //translation Z de la main 2
						}
					}
					
					
					for (int m = 0 ; m < Classification.NOMBRE_DE_FEATURES ; m++)
					{
						descripteurs[m] = descripteurs[m] / (double)(nb_frames-5) ;
						basevs[compteur][m] = descripteurs[m] ;
					}
			
					compteur++ ;
    			}
				ois.close() ;
			}
			
			for(int k=17;k<23;k++)
			{
				ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(tableau_fichiers.get(k))) ;
				ListeDeMouvements hyk = (ListeDeMouvements)ois.readObject();
			
				int nb_exemples = hyk.size() ;
				System.out.println("geste "+k+": "+hyk.size()+" exemples");
				
				for (int i = 0 ; i < nb_exemples ; i++)
				{					
					int nb_frames = hyk.get(i).getSize() ;
					//System.out.println(nb_frames + " frames dans l'exemple " + i + " du fichier " + k);
					
					double[] descripteurs = new double[Classification.NOMBRE_DE_FEATURES] ;
    			
					for (int j = 5 ; j < nb_frames ; j++) //On enl�ve les 5 premi�res frames pour l'entr�e du geste dans la zone
					{
						
						descripteurs[0] =+ hyk.get(i).get(j).get(0) ; //nombre de mains
						descripteurs[1] =+ hyk.get(i).get(j).get(7) ; //nombre de doigts de la main 1
						
						double X_normale1 = hyk.get(i).get(j).get(38) ; 
						double Y_normale1 = hyk.get(i).get(j).get(39) ; 
						double Z_normale1 = hyk.get(i).get(j).get(40) ; 
						double Norme_normale1 = Math.sqrt(X_normale1*X_normale1+Y_normale1*Y_normale1+Z_normale1*Z_normale1) ;
						if (descripteurs[0]>0) {
							descripteurs[2] = +X_normale1 / Norme_normale1; //normale X de la main 1
							descripteurs[3] = +Y_normale1 / Norme_normale1; //normale Y de la main 1
							descripteurs[4] = +Z_normale1 / Norme_normale1; //normale Z de la main 1
						
						double X_translation1 = hyk.get(i).get(j).get(50) ;
    					double Y_translation1 = hyk.get(i).get(j).get(51) ;
    					double Z_translation1 = hyk.get(i).get(j).get(52) ;
    					double Norme_translation1 = Math.sqrt(X_translation1*X_translation1+Y_translation1*Y_translation1+Z_translation1*Z_translation1) ;
    					descripteurs[8] =+ X_translation1/Norme_translation1; //translation X de la main 1
						descripteurs[9] =+ Y_translation1/Norme_translation1; //translation Y de la main 1
						descripteurs[10] =+ Z_translation1/Norme_translation1; //translation Z de la main 1
						
						// On r�cup�re la normale lors de la frame pr�c�dente pour calculer la rotation
    					double X_normalePrecedente1 = hyk.get(i).get(j-1).get(38) ;
    					double Y_normalePrecedente1 = hyk.get(i).get(j-1).get(39) ;
    					double Z_normalePrecedente1 = hyk.get(i).get(j-1).get(40) ;
    					double Norme_normalePrecedente1 = Math.sqrt(X_normalePrecedente1*X_normalePrecedente1+Y_normalePrecedente1*Y_normalePrecedente1+Z_normalePrecedente1*Z_normalePrecedente1) ;
    					
    					//descripteurs[14] =+ Math.acos((descripteurs[2]*X_normalePrecedente1 + descripteurs[3]*Y_normalePrecedente1 + descripteurs[4]*Z_normalePrecedente1)/Norme_normalePrecedente1 ) ; //rotation de la main 1
						}
    					/* Informations de la 2eme main */
    					double X_normale2 = hyk.get(i).get(j).get(41) ; 
						double Y_normale2 = hyk.get(i).get(j).get(42) ; 
						double Z_normale2 = hyk.get(i).get(j).get(43) ; 
						double Norme_normale2 = Math.sqrt(X_normale2*X_normale2+Y_normale2*Y_normale2+Z_normale2*Z_normale2) ;
						if (descripteurs[0]>1) {
							descripteurs[5] = +X_normale2 / Norme_normale2; //normale X de la main 2
							descripteurs[6] = +Y_normale2 / Norme_normale2; //normale Y de la main 2
							descripteurs[7] = +Z_normale2 / Norme_normale2; //normale Z de la main 2
							double X_translation2 = hyk.get(i).get(j).get(53);
							double Y_translation2 = hyk.get(i).get(j).get(54);
							double Z_translation2 = hyk.get(i).get(j).get(55);
							double Norme_translation2 = Math
									.sqrt(X_translation2 * X_translation2
											+ Y_translation2 * Y_translation2
											+ Z_translation2 * Z_translation2);
							descripteurs[11] = +X_translation2
									/ Norme_translation2; //translation X de la main 2
							descripteurs[12] = +Y_translation2
									/ Norme_translation2; //translation Y de la main 2
							descripteurs[13] = +Z_translation2
									/ Norme_translation2; //translation Z de la main 2
						}
					}
					
					
					for (int m = 0 ; m < Classification.NOMBRE_DE_FEATURES ; m++)
					{
						descripteurs[m] = descripteurs[m] / (double)(nb_frames-5) ;
						basevs[compteur][m] = descripteurs[m] ;
					}
			
					compteur++ ;
    			}
				ois.close() ;
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClassiFinal[] classi2 = test.main(basevs, basevs);
		
		return classi;
	}

}