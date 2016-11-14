/*
This will be a subclass from the Soilder class which is the parent class,
so this class will be inheriting features from the parent class, thus we add
after public class Cuirassier, extends Soilder 
*/
public class Cuirassier extends Soldier{
    public Cuirassier (String n){
        //Super was used to implement the features from the superclass
        super(n);
        // n, was added in super as the name of the soldiers hasn't been defined
        speed = 5.4;    
     }
 }