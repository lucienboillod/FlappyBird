//Import Audioclip
import java.applet.*;
import java.io.*;
import java.net.*;

public class Bird
{
  /***********************/
  /***** DEFINITIONS *****/
  /***********************/
  //Set the Bird
  static double px, py; //Position
  int height, width; //Size
  //Set velocity
  static double vi, vf;
  //Boolean and int
  boolean press; //Use to avoid bugs when button still press
  boolean down; //Rotate when go down
  int jump = 0; //Use to jump properly
  //Music
  private static AudioClip[] flap = new AudioClip[1];
  
  
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
  //Check the collisions with sky or ground
  public boolean isColli(double px, double py)
  {
    if(py<-40){py = -5; vf = 0; vi =0; return true;}
    if(py>680){py=805; vf=0; vi=0; return true;}
    else{return false;}
  }
  
  
  /**************************/
  /***** INITIALIZATION *****/
  /**************************/
  public void Initialize()
  {  
    height = 40;
    width = 40;
    px=30;
    py=400;
    vi= 1.5;
    vf= Math.exp(vi); //To simulate the gravity when go down (exponentielle because in Flappy Bird the Bird fall very quickly)
    press=false;
    flap[0] = initialize("Contents//flap.wav");
  }
  
  
  /*****************************/
  /***** UPDATING THE GAME *****/ 
  /*****************************/
  public void Update()
  {
    //Acceleration when go down
    vf += vi;
    py = py + vf;
    //Put the down variable into true to rotate the bird
    down = true;
    //The Jump when Space is Pressed
    if(jump > 0)
    {
      down = false; //When jump dont rotate the bird
      jump --; //Use the counter Jump to avoid the teleportation of bird during jump
      py -= 20; 
    }
    if(StdDraw.isKeyPressed((int)' '))
    {
      if(!(press))
      {
        flap[0].play();
        jump = 7;
        vf = 1.5;
      }
      press = true;
    }
    if(!(StdDraw.isKeyPressed((int)' ')))
    {
      press = false; //Use Press to know if the button is already Press and so avoid bug
    }
  }
  
  
  /*****************************/
  /***** DRAWING THE GAME *****/
  /****************************/
  public void Draw(int x)
  {
    StdDraw.setPenColor(StdDraw.YELLOW);
    if(x==1 && down == false) //If not down then dont rotate, NB: using of variable to use in the Game Class
    {
      StdDraw.picture(px,py,"Contents//bird.gif",height,width);
    }
    else //If down then rotate the Bird (80 degree)
    {
      StdDraw.picture(px,py,"Contents//bird.gif",height,width,-80);
    }
  }
}
