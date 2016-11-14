/*
This will be a subclass from the Soilder class which is the parent class,
so this class will be inheriting features from the parent class, thus we add
after public class Halberdier, extends Soilder 
*/
public class Halberdier extends Soldier{
   public Halberdier(String n){
       //Super was used to implement the features from the superclass
        super(n);
       // n, was added in super as the name of the soldiers hasn't been defined
        speed = 4.7;
   }
}