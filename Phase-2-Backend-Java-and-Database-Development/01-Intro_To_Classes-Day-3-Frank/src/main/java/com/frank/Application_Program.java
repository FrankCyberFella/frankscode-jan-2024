package com.frank;

// Give me access to the PlayingCard class in the com.frank.playingcard package
import com.frank.playingcard.PlayingCard;

/**
 * Application Program to Demonstrate Basic Use of Classes and Object
 *
 */
public class Application_Program 
{
    public static void main( String[] args )
    {
        System.out.println("Welcome to our class and object usage demo!\n" );
        
        // Instantiate a PlayingCard object (define a PlayingCard class object)
        
//      datatype name = initial-value; - define a primitive variable
//      classname objectname = new classname(); instantiate an object   
        PlayingCard aCard    = new PlayingCard();  // Instantiate a default card
        
        // instantiate a PlayingCard using the 3-arg constuctor;
        PlayingCard myCard   = new PlayingCard(8,"Spades", "Black");
        
        System.out.println("myCard is: " + myCard);
        
        // Display just the value in myCard
        System.out.println("The value of myCard is: " + myCard.getValue());
        myCard.setValue(3);
        System.out.println("myCard after .setValue(3): " + myCard);
        
        System.out.println("aCard object: " + aCard);
        // System.out.println only can display Strings
        //
        // Any variable or object it is asked to display must be convertible to a String
        // primitive data types have built-in conversion to String processes
        // Programmer defined Classes/Objects do not have built-in conversion to String processes
        //
        // When System.out.println() sees an object is to displayed:
        // 
        //   1. it looks for a toString() method in the Class of the object to convert to a String
        //   2. if there is not a toString() method for the class, it look to next level up
        //                  in the Class hierarchy for one and uses it if it has one.
        //   3. Repeat looking up in the hierarchy for toString() until it gets to the top.
        //   4. If it hasn't found one, an error is generate.
        //
        // If the PlayingCard class does not have a toString() method, 
        //        Java will use the Object classes generic toString() method
        //
        // The Object class generic method returns the:
        
        //     package-the-class-is-in.classname@memory-location
        
        
        System.out.println("\nThanks for visiting our class and object usage demo!" );
    }
}
