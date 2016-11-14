public class Soldier{
	/* Variables are protected so that the variable can be used in other classes 
    that extends from this class
    */
   protected String name;
   protected double speed;
   protected double PosX;
   protected double PosY;
   protected double distance;
   protected double time;
//After declaring the variables and type we now start the constructor
   public Soldier (String n){
       name = n;   
   }
   /* To get the location we use coordiates X and Y, which they are generated using
   the methods below, where a random number between 0 - 100, will be assigned to
   X and Y positions. So theoretically the solders will be in a square 
   Battlefield, that will have an length and width of 100. 
   */    
   public double getXPositions(){
       PosX = (double)(Math.random()*100);
       return PosX;   
   } 
    
   public double getYPositions(){
       PosY = (double) (Math.random()*100);
       return PosY;
   }
   /*
   I have used updateXPositions method, so that when the winning soldier moves,
   he will be in the killed soldiers position, where this method will update 
   for the winning soldier.
   */
   public double updateXPositions(Soldier opponent){
       this.PosX = opponent.PosX;
       return opponent.PosX;
   }
   
   public double updateYPositions(Soldier opponent){
       this.PosY = opponent.PosY;
       return opponent.PosY;
   }
   /*
   As we are using coordinates X and Y for each soldiers position, I have used 
   pythagoras's theorem to calculate the distance between the soldiers.
   */     
   public double distanceBetweenSoldier(Soldier opponent){
       this.distance = Math.sqrt(Math.pow(PosX-opponent.PosX,2)
               + Math.pow(PosY-opponent.PosY, 2));
       return this.distance;    
   }
    /*
   To calculate the time I have used the formula Distance / Speed = time
   */
   public double timeTaken(){
       this.time = this.distance / this.speed;
       return time;
   }
   /*
   For the soldiers that get skilled I have used the methods killedXPosition and
   killedYPosition, to reposition the killed soldiers, where it is not even in
   the battle field. 
   */
   public double killedXPosition(){
       PosX = (double)(Math.random()* -100);
      return PosX; 
   }
   
   public double killedYPosition(){
       PosY = (double)(Math.random()* -100);
      return PosX; 
   }
} 