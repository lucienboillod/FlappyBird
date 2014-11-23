public class Main
{ 
  /***************/
  /**** MAIN ****/
  /**************/
  
  /* <<< TO USE THE FLAPPY BIRD YOU HAVE TO RUN HERE >>> */
  /* It's the main intput of the program
   * It's contain the Game Loop 
   * Here I can switch between the game and the Menu with the differents Class I created
   */
  
  //Booleans 
  static Boolean isGame = false; //Used to know if the game is launched or not (and so swap Menu/Game)
  static Boolean iniGame= false, iniMenu =false; //Used to initialize just one time the menu or the game
  static Boolean iniMap = false; //Used to generate Random Map
  //Select the Map
  static long selectMap;
  //Boolean to know if the buttons are press 
  static Boolean one= false, two= false, three= false, four= false, five = false;
  
  //New instances of Class
  static Game game = new Game();
  static Menu menu = new Menu();
  
  public static void main(String[] args)
  {   
    //Game Loop
    while(Game.game)
    { 
      if(iniMap==false)
      {
        //Set window's size
        StdDraw.setCanvasSize(600,800);
        //Set Scale of the window
        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(800,0);
        
        //Interface to select the map
        while(iniMap == false)
        {
          StdDraw.clear();
          StdDraw.picture(300,400,"Contents//SelectMap.png",660,880);
          //Check if the Button is already press to avoid bug
          if(!(StdDraw.isKeyPressed((int)'1')))
          {one = false;}
          if(!(StdDraw.isKeyPressed((int)'2')))
          {two = false;}
          if(!(StdDraw.isKeyPressed((int)'3')))
          {three = false;}
          if(!(StdDraw.isKeyPressed((int)'4')))
          {four = false;}
          if(!(StdDraw.isKeyPressed((int)'5')))
          {five = false;}
          //Select the map when the aas
          if(StdDraw.isKeyPressed((int)'1') && !one)
          {
            one = true;
            selectMap = 1;
            iniMap=true;
          }
          if(StdDraw.isKeyPressed((int)'2') && !two)
          {
            two = true;
            selectMap = 2;
            iniMap=true;
          }
          if(StdDraw.isKeyPressed((int)'3') && !three)
          {
            three = true;
            selectMap = 3;
            iniMap=true;
          }
          if(StdDraw.isKeyPressed((int)'4') && !four)
          {
            four = true;
            selectMap = 4;
            iniMap=true;
          }
          if(StdDraw.isKeyPressed((int)'5') && !five)
          {
            five = true;
            selectMap = 5;
            iniMap=true;
          }
          StdDraw.show(10); 
        }
      } 
      
      if(isGame==false)
      {
        if(iniMenu==false)
        {iniMenu=true; menu.Initialize();}
        menu.Update();
        menu.Draw(selectMap); 
      }
      else
      {
        if(iniGame==false)
        {iniGame=true; game.Initialize();}
        Game.Update();
        Game.Draw(selectMap); 
      }
    }
  }
}