//Importations of Fonts
import java.awt.*;
//Importations for Audioclip
import java.applet.*;
import java.io.*;
import java.net.*;

public class Game
{
  /***********************/
  /***** DEFINITIONS *****/
  /***********************/
  //Boolean and int
  static boolean game = true, dead = false;
  public static boolean on = true; // Music on or off
  static boolean begin; //Use for the tutorial "Flap the wings"
  static int count; //Use to do the Bird falls to the ground when dead
  static int ground = 300; //Use to scroll the ground
  static int score;
  static Boolean spacePressed = false; //If space is already press ?
  //Instances of Objects
  static Bird bird = new Bird();
  static Pipe[] pipe = new Pipe[3];
  //Instance of Class
  static Menu menu = new Menu();
  static Main main = new Main();
  //Others
  static final Font DEFAULT_FONT = new Font("SansSerif", Font.PLAIN, 30);
  private static AudioClip[] audio = new AudioClip[5];
  
  
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
    //Pipes
    pipe[0] = new Pipe(900);
    pipe[1] = new Pipe(1200);
    pipe[2] = new Pipe(1500);
    //Bird
    bird.Initialize();
    //Others
    StdDraw.setFont(DEFAULT_FONT);
    audio[0] = initialize("Contents//game_music.wav"); 
    audio[1] = initialize("Contents//piece.wav");
    audio[2] = initialize("Contents//impact.wav");
    audio[3] = initialize("Contents//game-over.wav");
    if(main.isGame == true) //launche the music for the game only 
    {audio[0].loop();} 
    begin = false;
    score =0;
  }
  
  
  /*****************************/
  /***** UPDATING THE GAME *****/ 
  /*****************************/
  public static void Update()
  {  
    //Tutorial
    if(!(StdDraw.isKeyPressed((int)' '))) //To check if the button is already press
    {
      spacePressed = false;
    }
    if(StdDraw.isKeyPressed((int)' ') && !spacePressed) //Then the game begin
    {
      spacePressed = true;
      begin = true;
    }
    //Game
    if(begin == true)
    {
      bird.Update();
      pipe[0].Update();
      pipe[1].Update(); 
      pipe[2].Update(); 
      //Upgrade the score
      for(int i=0; i<3;i++)
      {
        if(pipe[i].oldPosx >= 30 && pipe[i].posx < 30)
        {
          audio[1].play();
          score++;
        }
      }
      //When dead ?
      for(int i=0; i<3;i++)
      {
        // If the pipe is near the bird ?
        if(pipe[i].posx < 200)
        {dead = pipe[i].isColli(bird.px,bird.py);}
      }
      if(bird.isColli(bird.px,bird.py))
      {dead = true;}
      //Exit the game
      if(StdDraw.isKeyPressed((int)'Q'))
      {
        audio[0].stop();
        System.exit(0);
      }
    }
    //Scrolling the ground
    if(ground <= 100)
    {ground = 300;}
    ground -= 8;
  }
  
  
  /*****************************/
  /***** DRAWING THE GAME *****/
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
    //Tutorial 
    if(begin == false)
    {
      //Scroll the sky and ground during the tuto
      if(x==2 || x==3)
      {StdDraw.picture(ground,400,"Contents//groundTutoNight.png",1200,880);} // Put start when it's the night
      else
      {StdDraw.picture(ground,400,"Contents//groundTuto.png",1200,880);}
      if(x==4 || x==5)
      {StdDraw.setPenColor(StdDraw.BLACK);}
      StdDraw.text(300.0,350.0,"Press Space to Flap your wings",.0);
    }
    //Real Game
    StdDraw.picture(ground,400,"Contents//ground.png",1200,880);
    pipe[0].Draw();
    pipe[1].Draw();
    pipe[2].Draw();
    if(!dead)
    {
      bird.Draw(1);
      StdDraw.text(40.0,50.0,""+score,.0);
    }
    StdDraw.show(15); 
    //Display when dead
    if(dead)
    {
      //Play the impact song
      audio[2].play();
      count = 0;
      //Display the Animation : falling of the bird
      while(bird.py< 720 && count < 15000)
      {
        count ++;
        bird.py += 7;
        audio[0].stop();
        StdDraw.clear();
        //Generate a Random Map each time you start a game
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
        StdDraw.picture(ground,400,"Contents//ground.png",1200,880);
        pipe[0].Draw();
        pipe[1].Draw();
        pipe[2].Draw();
        StdDraw.picture(300,400,"Contents//gameOver.png",660,880);
        bird.Draw(2);
        StdDraw.show(15);
      }
      // Play the game over music
      audio[3].play(); 
      //Display the Score interface
      while(!(StdDraw.isKeyPressed((int)' ')) && !spacePressed)
      {
        StdDraw.clear();
        //Generate a Random Map each time you start a game
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
        StdDraw.picture(ground,400,"Contents//ground.png",1200,880);
        StdDraw.picture(300,400,"Contents//Score.png",660,880);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.text(300.0,250.0,"Your score is " + ""+score,.0);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text(300.0,350.0,"Best Score: " + ""+score,.0);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(300.0,550.0,"Press Space",.0);
        StdDraw.text(300.0,600.0,"to return to the menu",.0);
        StdDraw.text(300.0,650.0,"Or Q to leave",.0);
        bird.Draw(2);
        if(StdDraw.isKeyPressed((int)'Q'))
        {
          audio[0].stop();
          System.exit(0);
        }
        StdDraw.show(15);
      }
      //Re-Initialize the elements to be ready to relaunched the Game
      StdDraw.clear();
      spacePressed = true;
      audio[3].stop();
      bird.Draw(2);
      bird.down = false;
      Initialize();
      dead = false;
      main.isGame = false;
      main.iniGame = false;
      main.iniMenu = false;
      main.iniMap = false;
      audio[0].stop();
    }
  }
}