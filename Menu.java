//Importations graphic
import java.awt.*;
//Importations for Audioclip
import java.applet.*;
import java.io.*;
import java.net.*;

public class Menu
{
  /***********************/
  /***** DEFINITIONS *****/
  /***********************/
  
  //Score
  static int score = 0;
  //Instanciation of Class
  static Main main = new Main();
  //Others
  static int sky = 300; //Use to scroll the sky
  static final Font DEFAULT_FONT = new Font("SansSerif", Font.PLAIN, 30);
  private static AudioClip[] audio = new AudioClip[1];
  
  /*********************/
  /***** FUNCTIONS *****/
  /*********************/
  //Function to initialize an AudioCLip (found on the standart library)
  private static AudioClip initialize(String filename)
  {
    URL url = null;
    try 
    {
      File file = new File(filename);
      if(file.canRead()) url = file.toURI().toURL();
    }
    catch(MalformedURLException e){e.printStackTrace();}
    if(url == null) throw new RuntimeException("audio " + filename + " not found");
    return Applet.newAudioClip(url);
  }
  
  
  /**************************/
  /***** INITIALIZATION *****/
  /**************************/
  public static void Initialize()
  {
    //Set window's size
    StdDraw.setCanvasSize(600,800);   
    //Set Scale of the window
    StdDraw.setXscale(0, 600);
    StdDraw.setYscale(800,0);
    //Others
    StdDraw.setFont(DEFAULT_FONT);
    audio[0] = initialize("Contents//menu_music.wav"); 
    audio[0].loop();
  }
  
  
  /*****************************/
  /***** UPDATING THE MENU *****/ 
  /*****************************/
  public static void Update()
  {    
    if(StdDraw.isKeyPressed((int)'Q'))
    {
      System.exit(0);
    }
    if(StdDraw.isKeyPressed((int)'\n'))
    {
      audio[0].stop();
      main.isGame = true;
    }
    //Scrolling the ground
    if(sky <= 0)
    {sky = 600;}
    sky -= 5;
  }
  
  /*****************************/
  /***** DRAWING THE MENU *****/
  /****************************/
  public static void Draw(long x)
  {
    StdDraw.clear();
    //Generate the selectionned map each time you start a game
    if(x==1)
    {StdDraw.picture(300,400,"Contents//FlappyMap.png",660,880);}
    else if(x==2)
    {StdDraw.picture(300,400,"Contents//FlappyMapNight.png",660,880);}
    else if(x==3)
    {StdDraw.picture(300,400,"Contents//FlappyMapNight2.png",660,880);}
    else if(x==4)
    {StdDraw.picture(300,400,"Contents//FlappyMapNeige.png",660,880);}
    else if(x==5)
    {StdDraw.picture(300,400,"Contents//FlappyMapDesert.png",660,880);}
    //StdDraw.text(300.0,400.0,"WELCOME TO FLAPPY BIRD",.0);
    StdDraw.picture(300,400,"Contents//Title.png",660,880);
    StdDraw.text(300.0,375.0,"Press Enter to play",.0);
    //Scroll the sky
    if(x==2 || x==3)
    {StdDraw.picture(sky,400,"Contents//skyNight.png",1200,880);} // Put start when it's the night
    else
    {StdDraw.picture(sky,400,"Contents//sky.png",1200,880);}
    StdDraw.show(10); 
  }
}