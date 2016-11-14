public class Battlefield{
    public static void main(String[]args){
        // Creating new instances for the three different soldier types
        Archer a = new Archer ("Archer");
        Cuirassier b = new Cuirassier ("Cuirassier");
        Halberdier c = new Halberdier ("Halberdier");
        // I have used a for loop because I want the battle to be repeated three times
        for (int i = 1; i<=3;i++){
        System.out.println("\nNEW BATTLE START " + i);
        System.out.println("The Archer is coordinated at x: " + a.getXPositions() +" y: "+a.getYPositions());
        System.out.println("The Cuirassier is coordinated at x: " + b.getXPositions() +" y: "+b.getYPositions());
        System.out.println("The Halberdier is coordinated at x: " + c.getXPositions() +" y: "+c.getYPositions());
        System.out.println("\nThe distance between Archer's Arrow, and Halberdier "
                + "is " + a.distanceBetweenSoldier(c));
        System.out.println("The distance between Cuirassier, and Archer "
                + "is " + b.distanceBetweenSoldier(a));
        System.out.println("The distance between Halberdier, and Cuirassier "
                + "is " + c.distanceBetweenSoldier(b));
        System.out.println("\nThe time taken between the Archer's arrow, and Halberdier is " + a.timeTaken());
        System.out.println("The time taken between Cuirassier, and Archer is " + b.timeTaken() );
        System.out.println("The time taken between Halberdier, and Cuirassier is " + c.timeTaken() );
        // I have used an if-else statement to create the battle of the three soldiers, where time between soldiers is used to calculate winner
        if(a.timeTaken() < b.timeTaken() && a.distanceBetweenSoldier(c) <= 12.3 && a.timeTaken()<= c.timeTaken()){
            System.out.println("\nWINNER of battle: Archer, \nLocated at x:" + a.updateXPositions(a) + " y: " + a.updateYPositions(a));	
            c.killedXPosition();
            c.killedYPosition();
		}
        else if (b.timeTaken() < a.timeTaken() && a.timeTaken() < a.timeTaken()){
				System.out.println("\nWINNER of battle: Cuirassier,  \nLocated at x:" + b.updateXPositions(a) + " y: " + b.updateYPositions(a));
                a.killedXPosition();
                a.killedYPosition();
		}
		else if (c.timeTaken() < b.timeTaken() ){
                System.out.println("\nWINNER of battle: Halberdier, \nLocated at x:" + c.updateXPositions(b) + " y: " + c.updateYPositions(b));
		}
        else {
            System.out.println("\nWINNER of battle: Cuirassier,  \nLocated at x:" + b.updateXPositions(a) + " y: " + b.updateYPositions(a));
            a.killedXPosition();
            a.killedYPosition();
        }
        
     }
    
    }
 }