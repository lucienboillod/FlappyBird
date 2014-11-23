public class Pipe
{
  /***********************/
  /***** DEFINITIONS *****/
  /***********************/
  //Set Pipe
  double oldPosx = 500;
  double posx = 500;
  double pipeRand = (Math.random()) * 490;
  //Getter-Setter
  public double get()
  {return posx;}
  public double oldGet()
  {return oldPosx;}
  public void set(double Posx)
  {posx = Posx;}
  //Constructor
  public Pipe(double Posx)
  {posx = Posx;}
  
  
  /*********************/
  /***** FUNCTIONS *****/
  /*********************/
  //Check collisions between Pipe and Bird
  public boolean isColli(double px, double py)
  {
    // We are through a pipe
    if((posx - 20) <= px + 20.0 && (posx + 20) > px - 20.0)
    {
      //Did we touch the pipe ?
      if(((py-20.0) < pipeRand - 15) || ((py+10.0) > 800 - ((800 - pipeRand) - 150)))
      { return true;}
    }
    return false;
  }
  
  
  /*****************************/
  /***** UPDATING THE GAME *****/ 
  /*****************************/
  public void Update()
  {
    //Move Pipe to the left
    oldPosx = posx;
    posx -= 4.75;
    //Replace Pipe when go out of limits
    if(posx <= -90) posx = 810;
  }
  
  
  /*****************************/
  /***** DRAWING THE GAME *****/
  /****************************/
  public void Draw()
  {
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.picture(posx,(pipeRand/2) - 40,"Contents//pipe2.png",60,pipeRand + 60);
    StdDraw.picture(posx,(640 - (((640 - pipeRand) - 150)/2)) + 40 ,"Contents//pipe.png",60,((640 - pipeRand) - 150) + 80);  
  }
}