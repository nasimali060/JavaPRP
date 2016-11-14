public class Superhero {
	public String superHeroname; //Initialised the variables that will be used in this program
	public int strength;
	
	public Superhero(String name)
	{
          superHeroname = name;
          strength = 10; // gave the initial base strength as ten q1.a
			
	}

 
    public Superhero(String name,int strength)
        {
            superHeroname = name;
            this.strength = strength;
        
        }   //created a constructor which will be used as the skeleton for Fight class .java
public void powerup (int a)
	{
		this.strength = strength + a; //As we do not know the actual strength we will denote a interger as 'a' where a can be anything
		
		
	}


    public Superhero fight(Superhero second) // At this stage is the fight where the two super heros are compared
	{
            
            if
                    (this.strength < second.strength){
					return second;
					
					}
                
          
            else if
                (this.strength < second.strength) {} else {
                return this;
            }
            
                return this;
            
           
            
            
	
	} 

  
    	

        
        public String toString() // this is used to return the name to the strings
	{
		return superHeroname;
	}	
	

	
}