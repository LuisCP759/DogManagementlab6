/* Program 6: MPLS Dog Management System using OOP principles. */

import java.util.Scanner; // Provides a way to read text from the keyboard and files.
import java.util.ArrayList; // Provides the growable list used to store dog objects.
import java.io.File; // Represents the CSV file that the program reads.
import java.io.FileNotFoundException; // Lets the file-reading method handle a missing CSV file.


public class DogManagementlab6 { // Contains the dog-management program and its methods.

    // Sets the maximum number of dog records this program keeps in its list.
    private static final int MAX_DOGS = 12;

    // Shared list that stores Dog objects for the duration of the program.
    static ArrayList<Dog> dogs = new ArrayList<Dog>();

    // Reads the user's menu choices and dog information from the keyboard.
    static Scanner scn = new Scanner(System.in);

    // Program entry point; loads saved dogs, then keeps showing the menu until Exit is chosen.
    public static void main(String[] args) throws Exception {
        readDogsFromFile(); // Load existing dog records into the list before showing the menu.
        welcome(); // Display the program's greeting.
        boolean running = true; // Controls whether the menu loop continues.
        while (running) { // Repeat the menu until the user selects the exit option.
            int choice = displayPrompt(); // Show the menu and get a valid selection.
            switch (choice) { // Run the method associated with the selected menu number.
                case 1:
                    createDogRecord(); // Create a new dog record from keyboard input.
                    break; // Stop this switch case from continuing into the next case.
                case 2:
                    displayDogRecord(); // Find and display a dog record.
                    break;
                case 3:
                    updateDogRecord(); // Find a dog and replace its stored information.
                    break;
                case 4:
                    displayDogAges(); // Display each dog's age converted to human years.
                    break;
                case 5:
                    System.out.println("Exiting Dog Management"); // Tell the user the program is exiting.
                    running = false; // Make the while loop stop after this menu selection.
                    break;
                default:
                    System.out.println("Invalid menu option"); // Handle a choice not matched by a case.
            }
        }
    }

    // Reads dog records from doginfo.csv and stores valid records in the dogs list.
    public static void readDogsFromFile() throws Exception {
        try {
            int lineNumber = 0; // Tracks the number of data rows read for error messages.
            File dogFile = new File("doginfo.csv"); // Refers to the CSV file in the working directory.
            Scanner fileScanner = new Scanner(dogFile); // Opens the file so its text can be read.

            // Skip the column-name row because it is not a dog record.
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine(); // Read and discard the header line.
            }
            while (fileScanner.hasNextLine()) { // Continue while another file row is available.
                String line = fileScanner.nextLine().trim(); // Read a row and remove spaces at its ends.

                lineNumber++; // Count this data row.
                if (line.isEmpty()) { // Ignore blank rows in the file.
                    continue; // Move to the next row.
                }
                if (dogs.size() >= MAX_DOGS) { // Do not load more than the list's allowed capacity.
                    System.out.println("Dog record storage is full; remaining file records were not loaded.");
                    break; // Stop reading because no more records can be stored.
                }
                String[] fields = line.split(",", -1); // Separate the row into comma-delimited values.
                if (fields.length != 4) { // Each file row must contain ID, name, weight, and age.
                    System.out.println("Skipping invalid dog record on line " + lineNumber);
                    continue; // Ignore this malformed row and try the next one.
                }
                try {
                    Dog dog = new Dog( // Create a Dog using the values from this CSV row.
                        Integer.parseInt(fields[0].trim()), // Convert the ID text to an integer.
                        fields[1].trim(), // Use the second field as the dog's name.
                        Double.parseDouble(fields[2].trim()), // Convert the weight text to a decimal.
                        Integer.parseInt(fields[3].trim()), // Convert the age text to an integer.
                        "", // The four-column CSV does not provide a breed.
                        "" // The four-column CSV does not provide an owner.
                    );
                    dogs.add(dog); // Keep this dog object in the program's list.
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid numbers on line " + lineNumber); // Report rows with invalid numeric fields.
                }
            }
            fileScanner.close(); // Close the file after all rows have been read.
            System.out.println("Loaded " + dogs.size() + " dogs from doginfo.csv."); // Report the number of dogs in the list.
        } catch (FileNotFoundException e) {
            System.out.println("Could not find doginfo.csv. Starting with no dogs loaded."); // Explain why no file records were loaded.
        }
    }

    // Prints a short introduction when the program starts.
    public static void welcome() {
        System.out.println("Welcome, this program allows for a care attendant to be able to create, retrieve and update a dog record from the system.");
    }

    // Displays the menu and returns a selection within the menu's valid range.
    public static int displayPrompt() {
        System.out.println("\nSelect a menu option:"); // Print a heading before listing the menu.
        String[] menuoptions = { // Store the menu labels so they can be printed in a loop.
            "Create a dog record",
            "Display dog record",
            "Update dog record",
            "Display dogs with age in human years",
            "Exit Program"
        };
        for (int row = 0; row < menuoptions.length; row++) { // Visit each menu label by its array position.
            System.out.println((row + 1) + ")" + menuoptions[row]); // Print a 1-based option number and its label.
        }
        int selection = 0; // Start outside the valid menu range so the prompt runs at least once.
        while (selection < 1 || selection > menuoptions.length) { // Keep asking until the choice matches a menu option.
            System.out.print("Enter selection here --> "); // Ask the user to enter an option number.
            selection = readInteger(""); // Read the typed text and convert it to an integer.

            if (selection < 1 || selection > menuoptions.length) { // Check whether the number is outside the menu range.
                System.out.println("Invalid menu option:" + selection); // Tell the user the choice was not valid.
            }
        }
        return selection; // Send the valid menu choice back to main().
    }

    // Prompts the user for a dog's details, creates a Dog, and adds it to the list.
    public static void createDogRecord() {
        System.out.println("Enter the dog information:"); // Tell the user to enter the dog's information.

        if (dogs.size() >= MAX_DOGS) { // Check whether the list has reached its maximum size.
            System.out.println("Dog record storage is full"); // Explain why a new record cannot be added.
        } else { // Collect the details only when there is room for another dog.
            System.out.print("Enter dog ID:"); // Prompt for this dog's unique ID.
            int entereddogID = readInteger(""); // Read and convert the ID to an integer.

            System.out.print("Enter dog name:"); // Prompt for the dog's name.
            String enterdogname = (scn.nextLine()); // Save the name typed by the user.

            System.out.print("Enter dog weight:"); // Prompt for the dog's weight.
            Double enterdogweight = readWeight(); // Read and convert the weight to a decimal.

            System.out.print("Enter dog age:"); // Prompt for the dog's age.
            int enterdogage = readInteger(""); // Read and convert the age to an integer.

            String enterdogbreed = ""; // Optional attribute not provided by the assignment CSV.
            String enterdogowner = "";

            Dog newDog = new Dog( // Build one Dog object from the six values just collected.
                entereddogID,
                enterdogname,
                enterdogweight,
                enterdogage,
                enterdogbreed,
                enterdogowner
            );
            dogs.add(newDog); // Add the new dog to the shared list.

            System.out.println("Dog record has been added, Total dogs stored:" + dogs.size()); // Confirm the record and show the list size.
            System.out.println(); // Print a blank line to separate the output.

            System.out.println("Displaying Dog Record"); // Label the new record's details.
            System.out.println(newDog); // Print the Dog object's text representation.
        }
    }

    // Asks for a dog ID and returns that dog's list position, or -1 if it is not found.
    public static int findDogRecord() {
        int requestedId = readInteger("Enter dog ID: ");
        ArrayList<Integer> matches = new ArrayList<Integer>();
        for (int index = 0; index < dogs.size(); index++) {
            if (dogs.get(index).getdogID() == requestedId) {
                matches.add(index);
            }
        }
        if (matches.isEmpty()) {
            return -1;
        }
        if (matches.size() == 1) {
            return matches.get(0);
        }
        // The supplied CSV contains two dogs with ID 388; keep both records accessible.
        System.out.println("More than one dog has this ID:");
        for (int index : matches) {
            System.out.println(dogs.get(index).getDogname());
        }
        while (true) {
            System.out.print("Enter the dog's name: ");
            String requestedName = scn.nextLine().trim();
            for (int index : matches) {
                if (dogs.get(index).getDogname().equalsIgnoreCase(requestedName)) {
                    return index;
                }
            }
            System.out.println("Choose one of the names listed above.");
        }
    }

    // Lists available IDs, asks for one, then prints that dog's saved details.
    public static void displayDogRecord() {
        if (dogs.size() == 0) { // Check whether any dog records are stored.
            System.out.println("No dog ID visible"); // Tell the user there are no records to display.
            return; // Stop because there is no dog to look up.
        }
        System.out.println("Available dog IDs"); // Introduce the IDs the user can choose from.
        for (int ID = 0; ID < dogs.size(); ID++) { // Visit each stored dog.
            System.out.println("Dog ID:" + dogs.get(ID).getdogID()); // Print this dog's ID.
            System.out.println();
        }
        int index = findDogRecord(); // Ask for an ID and get the matching list position.
        if (index == -1) { // Check whether the requested ID was missing.
            System.out.println("NO ID found"); // Report that no matching record exists.
            return; // Stop because there is no record to print.
        }
        System.out.println(); // Separate the ID list from the selected record.
        System.out.println("Displaying ID of choosing"); // Label the details being displayed.
        System.out.println(dogs.get(index)); // Use the object's formatted display method.
    }

    // Finds a dog by ID and asks the user to replace all of its stored details.
    public static void updateDogRecord() {
        int index = findDogRecord(); // Get the list position of the dog to update.
        if (index == -1) { // Check whether the requested dog ID was found.
            System.out.println("Invalid ID to update record"); // Tell the user the ID cannot be updated.
            return; // Stop because there is no matching Dog object.
        }
        System.out.print("New dog ID:"); // Ask for the replacement ID.
        dogs.get(index).setdogID(readInteger("")); // Convert the input and save it in the selected Dog.

        System.out.print("Name:"); // Ask for the replacement name.
        dogs.get(index).setdogname(scn.nextLine()); // Save the new name in the selected Dog.

        System.out.print("Weight: "); // Ask for the replacement weight.
        dogs.get(index).setdogweight(readWeight()); // Convert the input and save the new weight.

        System.out.print("Age:"); // Ask for the replacement age.
        dogs.get(index).setdogage(readInteger("")); // Convert the input and save the new age.



        System.out.println(); // Separate the prompts from the confirmation.
        System.out.println("Dog record updated"); // Confirm that the in-memory record was updated.
        System.out.println(dogs.get(index)); // Print the updated Dog object.
    }

    // Prints an exit message; the main menu currently exits directly in its case 5.
    public static void exitprogram() {
        System.out.println("Exit program"); // Display the exit message.
    }

    // Starts the recursive display of dog ages, beginning with the first dog.
    public static void displayDogAges() {
        if (dogs.size() == 0) { // Check whether the list has any records.
            System.out.println("no dog record to display"); // Tell the user there are no ages to show.
        }
        displayDogage(0); // Start with the dog at list position zero.
    }

    // Prints one dog's age in human years, then calls itself for the next dog.
    private static void displayDogage(int index) {
        if (index >= dogs.size()) { // Stop when the index passes the last dog in the list.
            return; // End this recursive call.
        }

        int ageinhumans = dogs.get(index).getdogage() * 15; // Calculate this dog's human-age estimate using the program's factor.
        System.out.println("Displaying dogs age in human years"); // Label this dog's age information.
        System.out.println("Name:" + dogs.get(index).getDogname()); // Print this dog's name.
        System.out.println("Age:" + dogs.get(index).getdogage()); // Print this dog's age in dog years.
        System.out.println("Age in human years:" + ageinhumans); // Print the calculated age in human years.

        displayDogage(index + 1); // Call this method for the next dog in the list.
    }
    // Retry invalid numeric input instead of terminating the program.
    private static int readInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scn.nextLine().trim());
                if (value >= 0) {
                    return value;
                }
            } catch (NumberFormatException exception) {
                // Show the same explanation for malformed and negative values.
            }
            System.out.println("Enter a whole number of zero or greater.");
        }
    }

    private static double readWeight() {
        while (true) {
            try {
                double weight = Double.parseDouble(scn.nextLine().trim());
                if (Double.isFinite(weight) && weight > 0) {
                    return weight;
                }
            } catch (NumberFormatException exception) {
                // Ask again below.
            }
            System.out.print("Enter a valid weight greater than zero: ");
        }
    }
}


