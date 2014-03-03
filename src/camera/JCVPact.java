package camera;

import java.nio.*;
import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.*;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

import com.googlecode.javacv.OpenCVFrameGrabber;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import java.awt.GridLayout;

public class JCVPact {
    public static void main(String[] args) throws Exception {
      boolean record = false;
      int codec4cc = CV_FOURCC('D','I','B',' ');
      try{ 
      

        
        /*createion de la fenetre principale*/
        JFrame mainframe = new JFrame();
        mainframe.setLayout(new GridLayout(1, 1));
        mainframe.setVisible(true);

        /*creation de la fenetre utilisée pour l'affichage de la video. L'objet CanvasFrame en JavaCV peut utiliser
        l'accélération materielle pour afficher les vidéos, profitons-en ! */
        CanvasFrame rgb_frame = new CanvasFrame("AVI Playback Demo");        
        mainframe.getContentPane().add(rgb_frame.getCanvas() );
        rgb_frame.setVisible(false);

        /*creation de l'objet d'acquisition de trames video à partir du fichier indiqué comme paramêtre du programme*/
        OpenCVFrameGrabber grabber = null;
        grabber = new OpenCVFrameGrabber(0);

        grabber.start();

        IplImage rgb_image = grabber.grab();
        int width  = rgb_image.width();
        int height = rgb_image.height();
        mainframe.setSize(width, height);

        /* Ligne magique de JavaCV - elle permet de faire en sorte que les trames videos non utilisées sont bien libérées de la mémoire
        (en quelque sorte en forcant un appel au "Garbage Collector"*/
        CvMemStorage storage = CvMemStorage.create();

        while (mainframe.isVisible() ) {
           if ( (rgb_image = grabber.grab()) != null) {
              
            /*informations pratiques:
              les images en couleurs sont la plupart du temps représentées sous forme d'un ensemble de 
              valeurs codées sur 8 bits, représentant par exemple l'intensité de rouge, vert et bleu d'un pixel
              
              Travail biblio #1: formats de représentation des images dans le domaine non-compréssé: RGB, YYV, ...
              
              on suppose dans cet exemple que le format de l'image est RGB non compréssé, échantillonné sur 24 bits. 
              Chaque pixel de l'image est représenté par 24 bits donnant dans l'ordre la composante Bleu, Verte et Rouge du pixel 
              (l'inversion bleu/rouge est lié au format AVI non compréssé)
              L'image est alors représentée en mémoire comme une succession de pixels, stockés ligne par ligne, comme suit :
                en bits: 
              BBBBBBBBGGGGGGGGRRRRRRRRBBBBBBBBGGGGGGGGRRRRRRRRBBBBBBBBGGGGGGGGRRRRRRRRBBBBBBBBGGGGGGGGRRRRRRRR ....
                ou en octets:
              BGRBGRBGR ....
              
              afin de manipuler les pixels de l'image, nous devons
              1- récupérer les données de pixels présents dans la mémoire
              2- localiser le pixel
            */
            
            /*1 - on récupére la mémoire où sont stoqués les pixels*/
            ByteBuffer rgb_data = rgb_image.getByteBuffer();
            
            /*2 - La mémoire des pixels étant construite de manière linéaire, afin de sauter une ligne entière de pixels 
            il faut aller width pixels plus loin. Comme chaque pixel est codé sur 1 octet, cela revient à 
            sauter 3*width pixels. Cette valeur s'appelle "stride" ou "pitch" de l'image", et est aussi acessible 
            dans la fonction IplImage.widthStep()
            
            Note: certains formats d'images utilisent un pitch supérieur à la largeur des images en pixels (pour
            d'obscures raisons qui ne nous concernent pas dans ce module), vous ne devriez pas avoir à voue en occuper.

            Le même raisonement vaut pour le déplacement horizontal de pixel.
            
                Par exemple, pour accéder accéder au pixel 30 horizontal et 20 vertical:
            */
            int pixel_index = 3*30 + 3*width*20;
            
            /*3 - lire la composante bleu de notre pixel*/
            int blue_value = rgb_data.get(pixel_index);
            /*ATTENTION!! Pour Java, le type Byte est toujours signé, donc une valeur non signée dans l'image de 200 est 
            en fait lue par cette fonction comme 200 = 0b11001000 -> signé (bit de poid fort mis)
            -> 0b11111111 - 0b11001000 = -55  !!!
            Ceci est propre à Java et à la manipulation des pixels via des bytes 
            Vous n'aurez pas le même problème dans d'autres langages ou en utilisant les autres "buffer" fourni
            par l'objet IplImage, mais ces methodes sont moins rapides. 
            
            La correction s'écrit donc:
            */
            if (blue_value < 0 ) blue_value = 255 + blue_value;
            
            /*4 - mettre la composante verte à 0 pour ce même pixel (ou +2 pour la composante rouge */
            rgb_data.put(pixel_index + 1, (byte) 0);
            

             /*Exercices: 
              0- (optionnel mais amusant) utilisez la WebCam au lieu d'un fichier video
              1- saturez la composante ROUGE de chaque image à pleine intensité
              2- saturez la composante bleu du quart inférieur droit de l'image à pleine intensité
              3- effectuez les mêmes manipulations avec deux CanvasFrame, l'un pour l'image originale, l'autre pour l'image modifiée
                  Regardez la documentation d'IplImage pour créer une nouvelle image vide
              4- seuillez les pixels à noir si l'intensité du vert dépasse 50%
              5- identifiez les pixels qui peuvent faire partie d'une zone de la peau (ou autre critère) et effacez tous les autres pixels
              6- identifiez les différentes régions dans l'exercice précédent, et déssinez un rectangle de couleur autour de chaque région

              Travail biblio #2: quels outils pour la detection de région (segmentation par région) ? 

             NOTE: pour ces exercices, vous devrez coder les algorithmes vous-memes (e.g. interdit d'utilser cvFindContour ...)
             une fois l'algorithme codé, vous avez le droit d'utiliser la version OpenCV equivalente si elle existe afin 
             de comparer les performances de vos algorithmes avec JavaCV
             
             Votre code sera bien entendu rendu disponible sous le GIT de votre projet pact, et vous aurez la sympathique attention de 
             prévenir votre expert JavaCV de l'endroit où il peut trouver le code.
             */
           }
 
 
           /*affichage de l'image*/          
           rgb_frame.showImage(rgb_image);
      

          /*deuxième ligne magique JavaCV, à appeler régulièrement (après chaque capture ou affichage de trame, ...)*/
           cvClearMemStorage(storage);
        }
        //nettoyage des ressources        
        grabber.stop();
        rgb_frame.dispose();
      } catch(Exception e){
        System.out.println(e.getMessage());
       }       
    }
}
