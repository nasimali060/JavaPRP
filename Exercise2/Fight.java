public class Fight{
public static void main (String[]args){
Superhero BlackWidow = new Superhero ("Black Widow"); /*By refering to the Superhero.java as a skeleton we created
a new superhero called black widow, as no strength is given she will be based at strength 10
*/
Superhero Drax = new Superhero ("Drax",79); //Another hero is profilled with this one given a strength of 10


Superhero outcome1 = Drax.fight(BlackWidow); //at this stage both heroes will be compared 

 System.out.println(outcome1); // the hero with the most strength will be printed


BlackWidow.powerup(100); // a power up is given to a specific hero, name is written

    
 Superhero outcome2 = Drax.fight(BlackWidow);   // the heros are compared again, now with the power up stored
System.out.println(outcome2); // hero with the most strength interger value will be printed

}
}