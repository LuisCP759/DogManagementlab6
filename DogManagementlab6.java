/*--------------------------------------------
Program 6: MPLS Dog Management System
	
    [REPLACE MY INFORMATION WITH YOURS]
    Course: COMP 170, Spring I 2023
    System: Visual Studio Code, Windows 10
    Author: C. Fulton
 */

import java.util.Scanner; //Importing Scanner Class
import java.util.ArrayList;

public class DogManagementlab6 { // class that contains your program 

    /*
     * Global Declaration for parallel arrays and Scanner Object
     */
    //DECLARING PARALEL ARRAYS OUTSIDE OF MAIN METHOD TO HOLD DOG DATA use the static keyword
    // we need to have variables where we will stored the dogs information when user input is given 
    //The class-level arrays are the storage: they keep those details after the method finishes, 
    //so the get and update methods can use them later.
    
 //Dog = the name of our ArrayList

    private static final int max_dogs = 12;
    //This ArrayList is only supposed to hold Dog objects
    // new arrayList<Dog>() actually creates the empty ArrayList
    static ArrayList<Dog> dogs = new ArrayList<Dog>();

    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws Exception {  // main nothing here yet
        welcome();
        boolean running = true;
        while (running) { // while loop will show the menu 
            int choice = displayPrompt();  // ask the user for chice and returns a number 
            switch (choice) { // calls the matching method 
                case 1:
                    Attendantrecording();
                    break; // breaks execution from the continuining into the next case 
                case 2:
                    getrecord();
                    break;
                case 3:
                    updaterecord();
                    break;
                case 4:
                    displaydogages();
                    break;

                case 5:
                    System.out.println("Exiting Dog Management");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid menu option");

            }

        }
    }    //Welcome method that outputs introductory text explaining program

    public static void welcome() { // only function is to welcome display 
        System.out.println("Welcome, this program allows for a care attendant to be able to create, retrieve and update a dog record from the system.");
    }

    //Method to display prompt and return integer values
    public static int displayPrompt() {  // this should display all of the prompts from the user each time they pick a prompt 
        System.out.println("\nSelect a menu option:"); // menu first options show
        String[] menuoptions = { // four local array of strings it hold the foru menu lables and exits only while display prompt runs 
            "Create a dog record",
            "Display dog record",
            "Update dog record",
            "Display dogs with age in human years",
            "Exit Program"
        };
        for (int row = 0; row < menuoptions.length; row++) { // increase row up to 3 
            System.out.println((row + 1) + ")" + menuoptions[row]);

        }
        int selection = 0;
        while (selection < 1 || selection > menuoptions.length) {
            System.out.print("Enter selection here --> ");
            selection = Integer.parseInt(scn.nextLine());  //scn.nextline reads wht the user types as text 
            // integer.parseInt() converts that text into integer 

            if (selection < 1 || selection > menuoptions.length) {
                System.out.println("Invalid menu option:" + selection);
            }
        }
        return selection; //local variable is saved and goes  back to the Displayprompt    

    }

    public static void Attendantrecording() { // first creationg of the record for each array 
        System.out.println("Please filled out form:");  // usec just needs to go in and filled out dog ID information 

        if (dogs.size() >= Max_Dogs) { // keep track of the amount of dogs we want to add 
            System.out.println("Dog record storage is full");   // were making sure we have space in storage to keep adding dog information 
        } else { // else will always run as logn as memory is never full 
            System.out.print("Enter dog ID:");
            int entereddogID = Integer.parseInt(scn.nextLine());

            System.out.print("Enter dog name:");   // storing dog information 
            String enterdogname = (scn.nextLine());

            System.out.print("Enter dog age:");
            int enterdogage = Integer.parseInt(scn.nextLine());

            System.out.print("Enter dog weight:");
            Double enterdogweight = Double.parseDouble(scn.nextLine());

            System.out.print("Enter dog breed:");
            String enterdogbreed = scn.nextLine();

            System.out.print("Enter dog owner name:");
            String enterdogowner = scn.nextLine();

            // we need to indentify the index of each input to get the right array when user comes back to get information
            Dog newDog = new Dog(
                entereddogID, 
                enterdogname, 
                enterdogweight, 
                enterdogage, 
                enterdogbreed, 
                enterdogowner
            );
            dogs.add(newDog);

            System.out.println("Dog record has been added, Total dogs stored:" + dogs.size());
            System.out.println();

            System.out.println("Displaying Dog Record");
            System.out.println(newDog);
            
        }
    }

    public static int finddogrecord() { // this will search for the index of the ID before we go into updating or displaying the info 
        System.out.print("Enter dog ID:"); // enter dog ID to find 
        int idtofind = Integer.parseInt(scn.nextLine());//idtofind is the id user use to find loop will go thur the list to find it 
        for (int index = 0; index < dogs.size(); index++) {
            if (dogs.get(index).getdogID() == idtofind) {
                return index;
                // dogs = the ArrayList
                //.get(index) get one Dog object
                // getdogID() .get that Dog object's ID 
            }

        }
        return -1; // return will act as if it was a print saying no dog ID foudn so we keep it the same way 
    }

    public static void getrecord() { // this will run for option 2 when user wants to display data 

        if (dogs.size() == 0) {
            System.out.println("No dog ID visible");
            return;
        } //now user will be able to see the informaiton of the dog 
        System.out.println("Available dog IDs");
        for (int ID = 0; ID < dogs.size(); ID++) {
            System.out.println("Dog ID:" + dogs.get(ID).getdogID()); // focus on only getting dog IDS list 
            System.out.println();
        }//finddogrecord() asks the user for an ID and returns its array index.
        int index = finddogrecord();
        if (index == -1) {
            System.out.println("NO ID found");
            return;
        }
        System.out.println();
        System.out.println("Displaying ID of choosing");
        System.out.println("ID: " + dogs.get(index).getdogID());
        System.out.println("Name: " + dogs.get(index).getDogname());
        System.out.println("Age: " + dogs.get(index).getDogage());
        System.out.println("Weight: " + dogs.get(index).getDogweight());
        System.out.println("Owner Name: " + dogs.get(index).getOwnername());
        System.out.println("Breed: " + dogs.get(index).getDogbreed());
        

    }

    public static void updaterecord() {
        int index = finddogrecord();
        if (index == -1) {
            System.out.println("Invalid ID to update record");
            return;

        }
        System.out.print("New dog ID:");
        dogs.get(index).setdogID(Integer.parseInt(scn.nextLine())); // store and update the information when ask to update the ID and record 

        System.out.print("Name:");
        dogs.get(index).setdogname(scn.nextLine()); // this variable[index] will make sure to grab the correct array location 

        System.out.print("Age:");
        dogs.get(index).setdogage(Integer.parseInt(scn.nextLine()));

        System.out.print("Weight");
        dogs.get(index).setdogweight(Double.parseDouble(scn.nextLine()));

        System.out.print("Breed:");
        dogs.get(index).setdogbreed(scn.nextLine());

        System.out.print("Owner Name:");
        dogs.get(index).setownername(scn.nextLine());

        System.out.println();
        System.out.println("Dog record updated"); // show updated record information folowing the index 
        System.out.println(dogs.get(index));
    }

    public static void exitprogram() {
        System.out.println("Exit program");
    }

    public static void displaydogages() {
        if (dogs.size() == 0) { // will verify record of dogs 
            System.out.println("no dog record to display");
        }
        return; // Start recursion with the first dog at index 0
    }

    private static void displayDogage(int index) {
        if (index >= dogs.size()) { // stop recursion after all stored dogs have been displayed
            return;
        }

        int ageinhumans = dogs.get(index).getDogage() * 15; // variable that will multiply dog years by 15 
        System.out.println("Displaying dogs age in human years");
        System.out.println("Name:" + dogs.get(index).getDogname());
        System.out.println("Age:" + dogs.get(index).getDogage());
        System.out.println("Age in human years:" + ageinhumans); // Display this dog's age converted to human years, then recursively process the next dog.

        //allow to display the next dog
        displayDogage(index + 1); // Recursive call moves to the next stored dog
    }
}
