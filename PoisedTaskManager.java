import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class PoisedTaskManager {

    /**
     * main Method.
     */
    public static void main(String[] args) {

        // Company Logo:
        System.out.println("-------------------------------");
        System.out.println("|     POISED TASK MANAGER     |");
        System.out.println("-------------------------------"
                + "\n");

        // Start: Main Menu:
        mainMenu();
    }

    /**
     * Request Main menu option.
     */
    public static void mainMenu() {
        // Title of Method:
        System.out.println("Main Menu:");
        System.out.println("______________________________________\n");

        // Set Scanner:
        Scanner menuScanner = new Scanner(System.in);

        // Main Menu Options:
        System.out.print("Please select from the following options below:\n" +
                "1. Create New Project\n" +
                "2. Update Existing Project\n" +
                "3. Update Contractor Details\n" +
                "4. Finalise Existing Project\n" +
                "5. View All Complete Projects \n" +
                "6. View All Incomplete Projects\n" +
                "7. View All Overdue Projects\n" +
                "8. Find Existing Project\n" +
                "9. Exit\n" +
                ": "
        );
        // Get User's menu choice:
        int menuChoice = Integer.parseInt(menuScanner.nextLine());

        System.out.println("______________________________________\n");

        // Return User's to Menu Execute with Scanner & menuChoice as parameters:
        menuExe(menuScanner, menuChoice);
    }


    /**
     * Executes Main menu choice and returns choice to method.
     *
     * @param menuProjectScanner - Scanner for menu.
     * @param userMenuChoice - int for user input on menu choice.
     */
    public static void menuExe(Scanner menuProjectScanner, int userMenuChoice) {
        // try: Handle Input:
        try {
            switch (userMenuChoice) {
                // Create Project:
                case 1: {
                    System.out.println("New Project Customer:\n");
                    newProjectCustomer(menuProjectScanner);
                    break;
                }
                // Update Existing Project:
                case 2: {
                    System.out.println("Update Existing Project:\n");
                    updateProject(menuProjectScanner);
                    break;
                }
                // Update Contractor Details:
                case 3: {
                    System.out.println("Update Contractor Contact Details:\n");
                    updateContractContacts(menuProjectScanner);
                    break;
                }
                // Finalise Existing Project:
                case 4: {
                    projectFinaliseNumber(menuProjectScanner);
                    break;
                }
                // View ALl Completed Projects:
                case 5: {
                    System.out.println("View All Completed Projects:\n");
                    viewCompletedProjects();
                    break;
                }
                // View All Incomplete Projects:
                case 6: {
                    System.out.println("View Incomplete Projects:\n");
                    viewIncompleteProjects(menuProjectScanner);
                    break;
                }
                // View All Overdue Projects:
                case 7: {
                    System.out.println("Overdue Projects:\n");
                    overdueProjects();
                    break;
                }
                // Find Existing Project:
                case 8: {
                    findExistingProject(menuProjectScanner);
                    break;
                }
                // Exit:
                case 9: {
                    // Close Scanner:
                    menuProjectScanner.close();

                    System.out.println("Goodbye :)");
                    System.exit(0);
                }
                // else: throw Exception Error:
                default: {
                    throw new InputMismatchException();
                }
            }
        }
        // catch: Exception Error:
        catch (InputMismatchException exception) {
            System.out.println("Invalid Input...\n" +
                    "Enter Option Number (1-8).\n" +
                    "Please make sure enter the correct details & try again:\n");
            // return to mainMenu:
            mainMenu();
        }
    }

    /**
     * Requests input for customer details.
     * uses writeProject method to write data to txt file.
     *
     * @param customerScanner - Scanner for Customer
     */
    // New Customer: -- > Uses: WriteProject:
    public static void newProjectCustomer(Scanner customerScanner) {
        // try: Request Information:
        try {
            // Request Customer Name:
            System.out.print("Customer Name\n" +
                    ": ");
            String customerName = customerScanner.nextLine();
            // Handle Invalid Input:
            if (customerName.matches(".*\\d.*") || customerName.isEmpty()) {
                throw new InputMismatchException();
            }

            // Customer Telephone Number:
            System.out.print("Customer Telephone\n" +
                    ": ");
            String customerTelNum;
            customerTelNum = customerScanner.nextLine();
            // Handle Invalid Input:
            if (!customerTelNum.matches("[0-9]+")) {
                throw new InputMismatchException();
            }

            // Customer Email Address:
            System.out.print("Customer Email Address\n" +
                    ": ");
            String customerEmail;
            customerEmail = customerScanner.nextLine();
            // Handle Invalid Input:
            if (customerEmail.isEmpty()) {
                throw new InputMismatchException();
            }

            // Customer Physical Address:
            System.out.print("Customer Physical Address\n" +
                    ": ");
            String customerPhyAdd = customerScanner.nextLine();
            // Handle Invalid Input:
            if (customerPhyAdd.isEmpty()) {
                throw new InputMismatchException();
            }

            System.out.println("______________________________________\n");

            // Confirm New Project Customer:
            System.out.print("Confirm New Project:\n" +
                    "\n" +
                    "1. Continue\n" +
                    "2. Go Back\n" +
                    "3. Main Menu\n" +
                    ": ");
            int newCustomerConfirmation = Integer.parseInt(customerScanner.nextLine());

            // If confirmed: Send Attribute Values to Class:
            // Return to New Customer method to get details:
            if (newCustomerConfirmation == 1) {
                System.out.println("Confirmed !!");
                // Create new Object of NewCustomer:
                ProjectPersonal.NewCustomer newCustomer = new ProjectPersonal.NewCustomer(
                        customerName,
                        customerTelNum,
                        customerEmail,
                        customerPhyAdd
                );
                // Print to Screen:
                System.out.println(newCustomer);

                // Customer Details:
                String customerDetails = "";
                customerDetails = ProjectPersonal.NewCustomer.toTxtFile(customerDetails);

                // Call method: write to ProjectFile.txt:
                writeProject(customerDetails);

                // Title of Next:
                System.out.println("\n" +
                        "Create New Project:\n");
                newProject(customerScanner);
            }
            // Return back to top:
            else if (newCustomerConfirmation == 2) {
                System.out.println("Canceled !!");
                newProjectCustomer(customerScanner);
            }
            // Return to Main Menu:
            else if (newCustomerConfirmation == 3) {
                mainMenu();
            }
            // else: throw Exception:
            else {
                throw new InputMismatchException();
            }
        }
        // catch: Exception Error:
        catch (InputMismatchException exception) {
            System.out.println("Invalid input...\n" +
                    "Please make sure enter the correct details & try again:\n");

            // Call Method Again:
            newProjectCustomer(customerScanner);
        }
    }

    /**
     * Requests input for project details.
     * uses writeProject method to write data to txt file.
     *
     * @param projectScanner - Scanner for Project.
     */
    // New Project: -- > Uses: WriteProject:
    public static void newProject(Scanner projectScanner) {
        // try: Request Information:
        try {
            // Request Project Number:
            System.out.print("Project Number\n" +
                    ": ");
            String proNumberString = projectScanner.nextLine();

            // Handle Invalid Input:
            if (!proNumberString.matches("[0-9]+") || proNumberString.isEmpty()) {
                throw new InputMismatchException();
            }
            // Cast String number to Integer:
            int proNumber = Integer.parseInt(proNumberString);

            // Request Project Name:
            System.out.print("Project Name\n" +
                    ": ");
            String proName = projectScanner.nextLine();
            // Handle Invalid Input:
            if (proName.matches(".*\\d.*")) {
                throw new InputMismatchException();
            }

            // Request Project Building Type:
            String proType;

            System.out.print(
                    "Please Select Building Type Below:" +
                            "\n1. Residential Buildings." +
                            "\n2. Educational Buildings." +
                            "\n3. Institutional Buildings." +
                            "\n4. Assembly Buildings." +
                            "\n5. Business Buildings." +
                            "\n6. Industrial Buildings." +
                            "\n7. Storage Buildings." +
                            "\n: ");
            int buildChoice = Integer.parseInt(projectScanner.nextLine());

            // Check choice:
            if (buildChoice == 1) {
                proType = "Residential Building";
            } else if (buildChoice == 2) {
                proType = "Educational Building";
            } else if (buildChoice == 3) {
                proType = "Institutional Buildings";
            } else if (buildChoice == 4) {
                proType = "Assembly Buildings";
            } else if (buildChoice == 5) {
                proType = "Business Buildings";
            } else if (buildChoice == 6) {
                proType = "Industrial Buildings";
            } else if (buildChoice == 7) {
                proType = "StorageStorage Buildings";
            }
            // else: throw Exception:
            else {
                throw new InputMismatchException();
            }

            // Request Project Physical Address:
            System.out.print("Project Physical Address\n" +
                    ": ");
            String proPhysAdd = projectScanner.nextLine();
            // Handle Invalid Input:
            if (proPhysAdd.isEmpty()) {
                throw new InputMismatchException();
            }

            // Request Project ERF Number:
            System.out.print("Project ERF Number\n" +
                    ": ");
            String proERFString = projectScanner.nextLine();
            // Handle Invalid Input:
            if (!proERFString.matches("[0-9]+") || proERFString.isEmpty()) {
                throw new InputMismatchException();
            }
            // Cast String number to Integer:
            int proERF = Integer.parseInt(proERFString);

            // Request Project Total Fee:
            System.out.print("Project Total Fee\n" +
                    ": ");
            String proTotFeeString = projectScanner.nextLine();
            // Handle Invalid Input:
            if (!proTotFeeString.matches("[0-9]+") || proTotFeeString.isEmpty()) {
                throw new InputMismatchException();
            }
            // Cast String number to Float:
            float proTotFee = Float.parseFloat(proTotFeeString);

            // Request Project Total Fee Paid To Date:
            System.out.print("Project Total Fee Paid To Date\n" +
                    ": ");
            String proTotPaidString = projectScanner.nextLine();
            // Handle Invalid Input:
            if (!proTotPaidString.matches("[0-9]+") || proTotPaidString.isEmpty()) {
                throw new InputMismatchException();
            }
            // Cast String number to Float:
            float proTotPaid = Float.parseFloat(proTotPaidString);

            // Request Project Deadline:
            // Day:
            System.out.print("Enter Deadline Day (DD)" +
                    "\n: ");
            String day = projectScanner.nextLine();
            if (!day.matches("[0-9]+")) {
                throw new InputMismatchException();
            }

            // Month:
            System.out.print("Enter Deadline Month (MM)" +
                    "\n: ");
            String month = projectScanner.nextLine();
            if (!month.matches("[0-9]+")) {
                throw new InputMismatchException();
            }

            // Year:
            System.out.print("Enter Deadline Year (YYYY)" +
                    "\n: ");
            String year = projectScanner.nextLine();
            if (!year.matches("[0-9]+")) {
                throw new InputMismatchException();
            }

            // Create Date:
            String proDeadline = (day + "-" + month + "-" + year);
            System.out.println("______________________________________\n");

            // Confirm New Project:
            System.out.print("Confirm New Project:\n" +
                    "\n" +
                    "1. Continue\n" +
                    "2. Go Back\n" +
                    "3. Main Menu\n" +
                    ": ");
            int newProjectConfirm = Integer.parseInt(projectScanner.nextLine());

            // If confirmed: Send Attribute Values to Class:
            // Return to New Architect method to get details:
            if (newProjectConfirm == 1) {
                System.out.println("Confirmed !!");
                CreateNewProject newProject = new CreateNewProject(
                        proNumber,
                        proName,
                        proType,
                        proPhysAdd,
                        proERF,
                        proTotFee,
                        proTotPaid,
                        proDeadline
                );
                System.out.println(newProject);

                // Project Details:
                String projectDetails = "";
                projectDetails = CreateNewProject.toTxtFile(projectDetails);

                // Write to file:
                writeProject(projectDetails);

                // Title of next method:
                System.out.println("\n" +
                        "New Project Architect:\n");
                newProjectArchitect(projectScanner);
            }
            // Return back to top:
            else if (newProjectConfirm == 2) {
                System.out.println("Canceled !!");
                newProject(projectScanner);
            }
            // Return to MainMeu:
            else if (newProjectConfirm == 3) {
                mainMenu();
            }
            // else: throw Exception:
            else {
                throw new InputMismatchException();
            }
        }
        // catch: Exception:
        catch (InputMismatchException exception) {
            System.out.println("Invalid input.\n" +
                    "Please make sure enter the correct details & try again:\n");
            newProject(projectScanner);
        }

        // Close Scanner:
        projectScanner.close();
    }

    /**
     * Requests input for Architect details.
     * uses writeProject method to write data to txt file.
     *
     * @param architectScanner - Scanner for Architect.
     */
    // New Architect: -- > Uses: WriteProject:
    public static void newProjectArchitect(Scanner architectScanner) {
        // try: Request Information:
        try {
            // Architect Name:
            System.out.print("Architect Name\n" +
                    ": ");
            String archName = architectScanner.nextLine();
            if (archName.matches(".*\\d.*") || archName.isEmpty()) {
                throw new InputMismatchException();
            }

            // Architect Telephone Number:
            System.out.print("Architect Telephone Number\n" +
                    ": ");
            String archTelNum = architectScanner.nextLine();
            if (!archTelNum.matches("[0-9]+")) {
                throw new InputMismatchException();
            }

            // Architect Email Address:
            System.out.print("Architect Email Address\n" +
                    ": ");
            String archEmail = architectScanner.nextLine();
            if (archEmail.isEmpty()) {
                throw new InputMismatchException();
            }

            // Architect Physical Address:
            System.out.print("Architect Physical Address\n" +
                    ": ");
            String archPhyAdd = architectScanner.nextLine();
            if (archPhyAdd.isEmpty()) {
                throw new InputMismatchException();
            }

            System.out.println("______________________________________\n");

            // Confirm New Project Architect:
            System.out.print("Confirm New Project:\n" +
                    "\n" +
                    "1. Continue\n" +
                    "2. Go Back\n" +
                    "3. Main Menu\n" +
                    ": ");
            int newArchitectConfirm = Integer.parseInt(architectScanner.nextLine());
            // If confirmed: Send Attribute Values to Class:
            // Return to New Builder method to get details:
            if (newArchitectConfirm == 1) {
                System.out.println("Confirmed !!");
                ProjectPersonal.NewArchitect newArchitect = new ProjectPersonal.NewArchitect(
                        archName,
                        archTelNum,
                        archEmail,
                        archPhyAdd
                );
                System.out.println(newArchitect);

                // Architect Details:
                String architectDetails = "";
                architectDetails = ProjectPersonal.NewArchitect.toTxtFile(architectDetails);

                // Write to file:
                writeProject(architectDetails);

                // Title of Next:
                System.out.println("New Project Builder:\n");
                newProjectBuilder(architectScanner);
            }
            // Return back to top:
            else if (newArchitectConfirm == 2) {
                System.out.println("Canceled !!");
                newProjectArchitect(architectScanner);
            }
            // Return to Main Menu:
            else if (newArchitectConfirm == 3) {
                mainMenu();
            }
            // else: throw Exception:
            else {
                throw new InputMismatchException();
            }
        }
        // catch: Handle Exception:
        catch (InputMismatchException exception) {
            System.out.println("Invalid input.\n" +
                    "Please make sure enter the correct details & try again:\n");
            newProjectArchitect(architectScanner);
        }
        // Close Scanner:
        architectScanner.close();
        architectScanner.close();
    }

    /**
     * Requests input for Builder details.
     * uses writeProject method to write data to txt file.
     *
     * @param builderScanner - Scanner for Builder.
     */
    // New Builder: -- > Uses: WriteProject:
    public static void newProjectBuilder(Scanner builderScanner) {
        // try: Request Information:
        try {
            // Builder Name:
            System.out.print("Builder Name\n" +
                    ": ");
            String buildName = builderScanner.nextLine();
            if (buildName.matches(".*\\d.*") || buildName.isEmpty()) {
                throw new InputMismatchException();
            }

            // Builder Telephone Number:
            System.out.print("Builder Telephone\n" +
                    ": ");
            String buildTelNum = builderScanner.nextLine();
            if (!buildTelNum.matches("[0-9]+")) {
                throw new InputMismatchException();
            }

            // Builder Email Address:
            System.out.print("Builder Email Address\n" +
                    ": ");
            String buildEmail = builderScanner.nextLine();
            if (buildEmail.isEmpty()) {
                throw new InputMismatchException();
            }

            // Builder Physical Address:
            System.out.print("Builder Physical Address\n" +
                    ": ");
            String buildPhyAdd = builderScanner.nextLine();
            if (buildPhyAdd.isEmpty()) {
                throw new InputMismatchException();
            }

            System.out.println("______________________________________\n");

            // Confirm New Project Builder:
            System.out.print("Confirm New Project:\n" +
                    "\n" +
                    "1. Continue\n" +
                    "2. Go Back\n" +
                    "3. Main Menu\n" +
                    ": ");
            int newBuilderConfirmation = Integer.parseInt(builderScanner.nextLine());
            // if: confirmed:
            if (newBuilderConfirmation == 1) {
                System.out.println("Confirmed !!");
                ProjectPersonal.NewBuildContractor newBuilder = new ProjectPersonal.NewBuildContractor(
                        buildName,
                        buildTelNum,
                        buildEmail,
                        buildPhyAdd
                );
                // Display Details:
                System.out.println(newBuilder);

                // Builder Details:
                String builderDetails = "";
                builderDetails = ProjectPersonal.NewBuildContractor.toTxtFile(builderDetails);

                // Write to file:
                writeProject(builderDetails);

                // return to main menu:
                mainMenu();
            } else if (newBuilderConfirmation == 2) {
                System.out.println("Canceled !!");
                newProjectBuilder(builderScanner);
            } else if (newBuilderConfirmation == 3) {
                mainMenu();
            }
            // else: throw Exception:
            else {
                throw new InputMismatchException();
            }
        }
        // catch: Handle Exception:
        catch (InputMismatchException exception) {
            System.out.println("Invalid input.\n" +
                    "Please make sure enter the correct details & try again:\n");
            newProjectBuilder(builderScanner);
        }

        // Close Scanner:
        builderScanner.close();
    }

    /**
     * Writes input data to ProjectFiles.txt.
     *
     * @param appendLine - append: String for Project Details.
     */
    // Write Project Details:
    public static void writeProject(String appendLine) {
        try {
            // Open File:
            File projectFile = new File("ProjectFile.txt");
            FileWriter fileWriter = new FileWriter(projectFile, true);
            PrintWriter printWriter = new PrintWriter(new FileWriter(projectFile, true));

            // Write String:
            printWriter.append(appendLine).append("\n");
            //Close:
            printWriter.close();
            fileWriter.close();
        } catch (Exception projectFileNotFound) {
            System.out.println("File Not Found.");
        }
    }

    /**
     * Retrieves all lines in ProjectFile.txt and stores
     * it in a StringBuilder. The StringBuilder is returned
     * to calling method.
     */
    // Read Project Details:
    public static StringBuilder readProject(Scanner updateScanner) {
        // Projects:
        StringBuilder projectLineString = new StringBuilder();

        try {
            // Open File for Read.
            File projectFile = new File("ProjectFile.txt");
            Scanner projectFilesScanner = new Scanner(projectFile);

            while (projectFilesScanner.hasNextLine()) {
                projectLineString.append(projectFilesScanner.nextLine()).append("\n");
            }

            //Close File Scanner:
            projectFilesScanner.close();

        } catch (Exception exception) {
            System.out.println("File Not Found.");
            updateProject(updateScanner);
        }
        // StringBuilder:
        return projectLineString;
    }

    /**
     * Requests input on update.
     * User can either update: Project Deadline or Project Total
     * Paid to Date.
     *
     * @param updateScanner - Scanner for Update Project.
     */
    // Update Existing Project:
    public static void updateProject(Scanner updateScanner) {
        // try: Request Update Information:
        try {
            // Request User's input of what they would like to update:
            System.out.print("Please select from the following options:\n" +
                    "\n" +
                    "1. Update Project Deadline\n" +
                    "2. Update Total Paid Fee To Date\n" +
                    "3. Main Menu\n" +
                    ": ");
            int updateChoice = Integer.parseInt(updateScanner.nextLine());

            System.out.println("______________________________________\n");

            // Update Project Deadline:
            if (updateChoice == 1) {

                // Display Error Message for Invalid Input:
                String invalidInput = "Input Invalid.";

                // try:
                try {
                    // Request Project Number:
                    System.out.print("Enter Project Number\n" +
                            ": ");
                    int userProjectNumber = Integer.parseInt(updateScanner.nextLine());

                    // Request New Deadline Date:
                    System.out.println("Update Project Deadline:\n");

                    // Day:
                    System.out.print("Enter Deadline Day (DD)" +
                            "\n: ");
                    String day = updateScanner.nextLine();
                    if (!day.matches("[0-9]+")) {
                        System.out.println(invalidInput);
                        throw new InputMismatchException();
                    }
                    // Month:
                    System.out.print("Enter Deadline Month (MM)" +
                            "\n: ");
                    String month = updateScanner.nextLine();
                    if (!month.matches("[0-9]+")) {
                        System.out.println(invalidInput);
                        throw new InputMismatchException();
                    }
                    // Year:
                    System.out.print("Enter Deadline Year (YYYY)" +
                            "\n: ");
                    String year = updateScanner.nextLine();
                    if (!year.matches("[0-9]+")) {
                        System.out.println(invalidInput);
                        throw new InputMismatchException();
                    }

                    // Create Updated Deadline Date:
                    String newProjectDeadline = (day + "-" + month + "-" + year);


                    // Call method: Read File: Get File Contents:
                    StringBuilder projectDetailsEdit = readProject(updateScanner);

                    // Set String Builder for updated project:
                    StringBuilder updatedProject = new StringBuilder();

                    // for line in Project File:
                    for (String detailLine : projectDetailsEdit.toString().split("[\n]")) {
                        ArrayList<String> projectList;
                        projectList = new ArrayList<>(Arrays.asList(detailLine.split(", ")));

                        // if project is found:
                        if (projectList.get(1).equals(String.valueOf(userProjectNumber))) {
                            // if Project Deadline is the equals previous Deadline:
                            if (projectList.get(8).equals(newProjectDeadline)) {
                                System.out.println("Updated Project Deadline matches previous Project Deadline (" + projectList.get(8) + ")\n" +
                                        "Please make sure you enter a new Deadline & try again:" +
                                        "\n");
                                // return to method : try again:
                                updateProject(updateScanner);
                            }
                            // Update:
                            else {
                                // Display Updated Date:
                                System.out.println("\n" +
                                        "New Date: " + newProjectDeadline +
                                        "\n");

                                projectList.set(8, newProjectDeadline);
                                String changedProject =
                                        projectList.get(0) + ", " +
                                                projectList.get(1) + ", " +
                                                projectList.get(2) + ", " +
                                                projectList.get(3) + ", " +
                                                projectList.get(4) + ", " +
                                                projectList.get(5) + ", " +
                                                projectList.get(6) + ", " +
                                                projectList.get(7) + ", " +
                                                projectList.get(8);
                                updatedProject.append(changedProject).append("\n");
                            }
                        }
                        // else: project not found.
                        else {
                            updatedProject.append(detailLine).append("\n");
                        }
                    }

                    // Confirm Update:
                    System.out.print("Confirm Update:\n" +
                            "1. Confirm\n" +
                            "2. Go Back\n" +
                            "3. Main Menu\n" +
                            ": ");
                    int confirmUpdate = Integer.parseInt(updateScanner.nextLine());

                    // Update Deadline:
                    if (confirmUpdate == 1) {
                        System.out.println("Confirmed!!" +
                                "\n");
                        // try: write update:
                        try {
                            FileWriter projectFile = new FileWriter("ProjectFile.txt");
                            PrintWriter updateProject = new PrintWriter(projectFile);
                            // Write:
                            updateProject.write(updatedProject.toString());
                            // Close:
                            updateProject.close();

                            // return to Main Menu :
                            mainMenu();
                        }
                        // catch:
                        catch (Exception exception) {
                            System.out.println("Update Failed.");

                            // return to Main Menu :
                            mainMenu();
                        }
                    }
                    // Return back to top:
                    else if (confirmUpdate == 2) {
                        updateProject(updateScanner);
                    }
                    // Return to Main Menu:
                    else if (confirmUpdate == 3) {
                        mainMenu();
                    }
                    // else: throw Exception:
                    else {
                        throw new InputMismatchException();
                    }
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("Please make sure enter the correct details & try again:\n");
                    updateProject(updateScanner);
                }
            }

            // Update Total Paid Fee To Date:
            else if (updateChoice == 2) {
                // Title:
                System.out.println("Update Total Paid Fee To Date:" +
                        "\n");
                // try:
                try {
                    // Request Project Number:
                    System.out.print("Enter Project Number\n" +
                            ": ");
                    int userProjectNumber = Integer.parseInt(updateScanner.nextLine());

                    // Request New amount:
                    System.out.print("New Total Paid Fee To Date:" +
                            "\n" +
                            "Enter Amount Paid Today\n" +
                            ": ");
                    float updatedTotalPaid = Float.parseFloat(updateScanner.nextLine());

                    // Call method: Read File: Get File Contents:
                    StringBuilder projectDetailsEdit = readProject(updateScanner);

                    // Set String Builder for updated project:
                    StringBuilder updatedProject = new StringBuilder();

                    // for line in Project File:
                    for (String detailLine : projectDetailsEdit.toString().split("[\n]")) {
                        ArrayList<String> projectList;
                        projectList = new ArrayList<>(Arrays.asList(detailLine.split(", ")));

                        // if project is found:
                        if (projectList.get(1).equals(String.valueOf(userProjectNumber))) {

                            // Previous Total Paid Amount:
                            float beforeTotalPaid = Float.parseFloat(projectList.get(7));
                            // New updated Total:
                            float newProjectAmount = beforeTotalPaid + updatedTotalPaid;
                            // Display new amount:

                            if (Float.parseFloat(projectList.get(6)) < newProjectAmount) {
                                System.out.println("Amount paid exceeds Project Total Fee of " + projectList.get(6) +
                                        "\n Please make sure you enter the correct amount & try again:");
                                // return to method to try again:
                                updateProject(updateScanner);
                            } else {
                                System.out.println("\n" +
                                        "New Total Paid: " + newProjectAmount);

                                // Make change:
                                projectList.set(7, String.valueOf(newProjectAmount));
                                String changedProject =
                                        projectList.get(0) + ", " +
                                                projectList.get(1) + ", " +
                                                projectList.get(2) + ", " +
                                                projectList.get(3) + ", " +
                                                projectList.get(4) + ", " +
                                                projectList.get(5) + ", " +
                                                projectList.get(6) + ", " +
                                                projectList.get(7) + ", " +
                                                projectList.get(8);
                                updatedProject.append(changedProject).append("\n");
                            }
                        } else {
                            updatedProject.append(detailLine).append("\n");
                        }
                    }

                    // Confirm Update:
                    System.out.print("Confirm Update:\n" +
                            "\n" +
                            "1. Confirm\n" +
                            "2. Go Back\n" +
                            "3. Main Menu\n" +
                            ": ");
                    int confirmUpdate = Integer.parseInt(updateScanner.nextLine());


                    if (confirmUpdate == 1) {
                        System.out.println("Confirmed!!" +
                                "\n");
                        // try: write update:
                        try {
                            FileWriter projectFile = new FileWriter("ProjectFile.txt");
                            PrintWriter updateProject = new PrintWriter(projectFile);
                            // Write:
                            updateProject.write(updatedProject.toString());
                            // Close:
                            updateProject.close();

                            // Return to Main Menu:
                            mainMenu();
                        }
                        // catch:
                        catch (Exception exception) {
                            System.out.println("Update Failed.");

                            // return to Main Menu :
                            mainMenu();
                        }
                    }
                    // Return back to top:
                    else if (confirmUpdate == 2) {
                        updateProject(updateScanner);
                    }
                    // Return to Main Menu:
                    else if (confirmUpdate == 3) {
                        mainMenu();
                    }
                }
                // catch:
                catch (Exception exception) {
                    System.out.println("Update Failed.");
                }
            }
            // Return to Main Menu:
            else if (updateChoice == 3) {
                mainMenu();
            }
            // else: throw Exception:
            else {
                throw new InputMismatchException();
            }
        } catch (
                InputMismatchException exception) {
            System.out.println("Please make sure enter the correct details & try again:\n");
            updateProject(updateScanner);
        }
        // Close Scanner:
        updateScanner.close();
    }

    /**
     * Requests input on update of Contact details.
     * User can either update: Architect Details or Builder contact
     * details.
     *
     * @param updateContactScanner - Scanner for Update Contact Details.
     */
    // Update Contract Contact Details:
    public static void updateContractContacts(Scanner updateContactScanner) {
        try {
            // Request User's input on what they would like to update:
            System.out.print("Please select from the following options:\n" +
                    "\n" +
                    "1. Update Architect Contact Details\n" +
                    "2. Update Builder Contact Details\n" +
                    "3. Main Menu\n" +
                    ": ");
            int updateChoice = Integer.parseInt(updateContactScanner.nextLine());
            System.out.println("______________________________________\n");

            // Update Architect Contact Details:
            if (updateChoice == 1) {
                try {
                    System.out.println("Update Architect Contact Details:\n");

                    // Request Project Number:
                    System.out.print("Enter Project Number\n" +
                            ": ");
                    int userProjectNumber = Integer.parseInt(updateContactScanner.nextLine());

                    // Update Telephone Number:
                    System.out.print("New Architect Telephone Number\n" +
                            ": ");
                    String updatedArchTelNum = updateContactScanner.nextLine();

                    if (!updatedArchTelNum.matches("[0-9]+") || updatedArchTelNum.isEmpty()) {
                        throw new InputMismatchException();
                    }
                    if (updatedArchTelNum.equals(ProjectPersonal.NewArchitect.archTelNum)) {
                        System.out.println("Updated Telephone Number matches previous Telephone Number.");
                        throw new InputMismatchException();
                    }

                    // Update Email:
                    System.out.print("New Architect Email Address\n" +
                            ": ");
                    String updatedArchEmail = updateContactScanner.nextLine();

                    if (updatedArchEmail.isEmpty()) {
                        throw new InputMismatchException();
                    }

                    // Call method: Read File: Get File Contents:
                    StringBuilder projectDetailsEdit = readProject(updateContactScanner);

                    // Set String Builder for updated project:
                    StringBuilder updatedArchitect = new StringBuilder();

                    // Set Bool value to false: Architect is not next:
                    boolean archIsNxt = false;

                    // for line in Project File:
                    for (String detailLine : projectDetailsEdit.toString().split("[\n]")) {
                        ArrayList<String> projectList;
                        projectList = new ArrayList<>(Arrays.asList(detailLine.split(", ")));

                        // if Architect line is found Details:
                        if (archIsNxt) {
                            // change Tel-Number:
                            projectList.set(2, updatedArchTelNum);
                            // change Email:
                            projectList.set(3, updatedArchEmail);

                            // Check: if details match:
                            if (updatedArchTelNum.equals(projectList.get(2)) || updatedArchEmail.equals(projectList.get(3))) {
                                System.out.println("Updated details match previous details.");
                                throw new InputMismatchException();
                            }
                            String updateArchitect =
                                    projectList.get(0) + ", " +
                                            projectList.get(1) + ", " +
                                            projectList.get(2) + ", " +
                                            projectList.get(3) + ", " +
                                            projectList.get(4);
                            updatedArchitect.append(updateArchitect).append("\n");

                            // set back to false:
                            archIsNxt = false;
                        }
                        // if project is found:
                        else if (projectList.get(1).equals(String.valueOf(userProjectNumber))) {
                            // set to true as next line is Architect for the desire project:
                            archIsNxt = true;
                            updatedArchitect.append(detailLine).append("\n");
                        }

                        // else: project not found.
                        else {
                            updatedArchitect.append(detailLine).append("\n");
                        }
                    }
                    System.out.println("\n" +
                            "Updated Details:" +
                            "\n");
                    System.out.println("Telephone Number: " + updatedArchTelNum);
                    System.out.println("Email Address: " + updatedArchEmail);

                    System.out.println("______________________________________\n");

                    // Confirm Update:
                    System.out.print("Confirm Update:\n" +
                            "\n" +
                            "1. Confirm\n" +
                            "2. Go Back\n" +
                            "3. Main Menu\n" +
                            ": ");
                    int confirmUpdate = Integer.parseInt(updateContactScanner.nextLine());

                    // Write to File: Return to Main Menu:
                    if (confirmUpdate == 1) {
                        // Write update to File:
                        try {
                            FileWriter projectFile = new FileWriter("ProjectFile.txt");
                            PrintWriter updateProject = new PrintWriter(projectFile);
                            // Write:
                            updateProject.write(updatedArchitect.toString());
                            // Close:
                            updateProject.close();

                            // return to Main Menu :
                            mainMenu();
                        } catch (FileNotFoundException exception) {
                            System.out.println("Update Failed.");

                            // return to Main Menu :
                            mainMenu();
                        }
                    }
                    // Return back to top:
                    else if (confirmUpdate == 2) {
                        updateContractContacts(updateContactScanner);
                    }
                    // Return to Main Menu:
                    else if (confirmUpdate == 3) {
                        mainMenu();
                    } else {
                        throw new InputMismatchException();
                    }
                } catch (Exception exception) {
                    System.out.println("Please make sure you enter the correct details & try again:");
                    updateContractContacts(updateContactScanner);
                }
            }
            // Update Builder Contact Details:
            else if (updateChoice == 2) {
                try {
                    // Request Project Number:
                    System.out.print("Enter Project Number\n" +
                            ": ");
                    int userProjectNumber = Integer.parseInt(updateContactScanner.nextLine());

                    System.out.println("Update Builder Contact Details:\n");

                    // Update Telephone Number:
                    System.out.print("New Builder Telephone Number\n" +
                            ": ");
                    String updatedBuildTelNum = updateContactScanner.nextLine();

                    if (!updatedBuildTelNum.matches("[0-9]+") || updatedBuildTelNum.isEmpty()) {
                        throw new InputMismatchException();
                    }
                    if (updatedBuildTelNum.equals(ProjectPersonal.NewBuildContractor.buildTelNum)) {
                        System.out.println("Updated Telephone Number matches previous Telephone Number.");
                        throw new InputMismatchException();
                    }

                    // Update Email:
                    System.out.print("New Builder Email Address\n" +
                            ": ");
                    String updatedBuildEmail = updateContactScanner.nextLine();

                    if (updatedBuildEmail.isEmpty()) {
                        throw new InputMismatchException();
                    }

//                    if (updatedBuildEmail.equals(ProjectPersonal.NewBuildContractor.buildEmail)) {
//                        System.out.println("Updated Email Address matches previous Email Address.");
//                        throw new InputMismatchException();
//                    }

                    // Call method: Read File: Get File Contents:
                    StringBuilder projectDetailsEdit = readProject(updateContactScanner);

                    // Set String Builder for updated project:
                    StringBuilder updatedBuilder = new StringBuilder();

                    // Set Bool value to false: Architect is not next:
                    boolean archIsNext = false;
                    boolean buildIsNext = false;

                    // for line in Project File:
                    for (String detailLine : projectDetailsEdit.toString().split("[\n]")) {
                        ArrayList<String> projectList;
                        projectList = new ArrayList<>(Arrays.asList(detailLine.split(", ")));

                        // if Architect line is found Details:
                        if (buildIsNext) {
                            // change Tel-Number:
                            projectList.set(2, updatedBuildTelNum);
                            // change Email:
                            projectList.set(3, updatedBuildEmail);

                            String updateArchitect =
                                    projectList.get(0) + ", " +
                                            projectList.get(1) + ", " +
                                            projectList.get(2) + ", " +
                                            projectList.get(3) + ", " +
                                            projectList.get(4);
                            updatedBuilder.append(updateArchitect).append("\n");

                            // set back to false:
                            archIsNext = false;
                            buildIsNext = false;
                        }
                        // if builderLine is next:
                        else if (archIsNext) {
                            buildIsNext = true;
                            updatedBuilder.append(detailLine).append("\n");
                        }
                        // if project is found:
                        else if (projectList.get(1).equals(String.valueOf(userProjectNumber))) {
                            // set to true as next line is Builder for the desire project:
                            archIsNext = true;
                            updatedBuilder.append(detailLine).append("\n");
                        } else {
                            updatedBuilder.append(detailLine).append("\n");
                        }
                    }

                    // Confirm Update:
                    System.out.println("______________________________________\n");
                    System.out.print("Confirm Update:\n" +
                            "\n" +
                            "1. Confirm\n" +
                            "2. Go Back\n" +
                            "3. Main Menu\n" +
                            ": ");
                    int confirmUpdate = Integer.parseInt(updateContactScanner.nextLine());
                    // Return to Main Menu:
                    if (confirmUpdate == 1) {
                        try {
                            FileWriter projectFile = new FileWriter("ProjectFile.txt");
                            PrintWriter updateProject = new PrintWriter(projectFile);
                            // Write:
                            updateProject.write(updatedBuilder.toString());
                            // Close:
                            updateProject.close();

                            // return to Main Menu :
                            mainMenu();
                        } catch (FileNotFoundException exception) {
                            System.out.println("Update Failed.");

                        }
                    }
                    // Return back to top:
                    else if (confirmUpdate == 2) {
                        updateContractContacts(updateContactScanner);
                    }
                    // Return Main Menu:
                    else if (confirmUpdate == 3) {
                        mainMenu();
                    } else {
                        throw new InputMismatchException();
                    }
                } catch (Exception exception) {
                    System.out.println("failed...");
                }
            }
            // Return To Main Menu:
            else if (updateChoice == 3) {
                mainMenu();
            }
            // else: throw Exception:
            else {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException exception) {
            System.out.println("Invalid input.\n" +
                    "Please make sure enter the correct details & try again:\n");
            updateContractContacts(updateContactScanner);
        }
        // Close Scanner:
        updateContactScanner.close();
    }

    /**
     * Requests project number,
     * Project is searched for in ProjectFile.txt
     * Project is stored in StringBuilder and returned to finaliseProject.
     *
     * @param finaliseScanner -  Scanner for Finalising Project.
     */
    // Finalise Project: Get Data: -----> 1
    public static void projectFinaliseNumber(Scanner finaliseScanner) {
        // Get Project Number
        System.out.print("Enter Project Number\n" +
                ": ");
        int userProjectNumber = Integer.parseInt(finaliseScanner.nextLine());

        // Build Project:
        StringBuilder completeProject = new StringBuilder();

        // Store Projects - completeProject:
        StringBuilder incompleteProjects = new StringBuilder();

        // try: Read File: Store Complete & Incomplete:
        try {
            // Read File:
            File projectFile = new File("ProjectFile.txt");
            Scanner projectScannerFile = new Scanner(projectFile);
            // 4 lines in ProjectFile.txt = One Project:
            while (projectScannerFile.hasNext()) {
                String customerLine = projectScannerFile.nextLine();
                String projectLine = projectScannerFile.nextLine();
                String architectLine = projectScannerFile.nextLine();
                String builderLine = projectScannerFile.nextLine();

                // Project Line to List:
                ArrayList<String> projectList;
                projectList = new ArrayList<>(Arrays.asList(projectLine.split(", ")));

                // if Project Number equals User Project Number
                if (projectList.get(1).equals(String.valueOf(userProjectNumber))) {
                    completeProject.append(customerLine).append("\n");
                    completeProject.append(projectLine).append("\n");
                    completeProject.append(architectLine).append("\n");
                    completeProject.append(builderLine).append("\n");

                }
                // else: Store Incomplete // Left Overs:
                else {
                    incompleteProjects.append(customerLine).append("\n");
                    incompleteProjects.append(projectLine).append("\n");
                    incompleteProjects.append(architectLine).append("\n");
                    incompleteProjects.append(builderLine).append("\n");
                }
            }
            // if Project is not found:
            if (completeProject.length() <= 0) {
                System.out.println("Project Number " + userProjectNumber + " does not exist.\n" +
                        "Please make sure you enter the correct Project Number." +
                        "\n");
                // return to method: try again:
                projectFinaliseNumber(finaliseScanner);
            }
            // Replace old projects with incomplete projects // left over:
            try {
                File projectFileIncomplete = new File("ProjectFile.txt");
                PrintWriter incompleteWriter = new PrintWriter(projectFileIncomplete);

                // Write:
                incompleteWriter.write(incompleteProjects.toString());
                // Close:
                incompleteWriter.close();
            }
            // catch: Exception:
            catch (FileNotFoundException incompleteEx) {
                System.out.println("Finalise Failed - File Not Found:");
                // return to Main Menu:
                mainMenu();
            }
            // Call finaliseProject to Write Invoice:
            finaliseProject(completeProject);

            // Close File Scanner:
            projectScannerFile.close();
        }
        // catch: Exception
        catch (FileNotFoundException finaliseEx) {
            System.out.println("File Not Found.");
            // return Main Menu:
            mainMenu();
        }
    }

    /**
     * Generates Project Invoice.
     * A Unique txt File is generated for each Project Invoice.
     *
     * @param completeProject - Scanner for Completing Project.
     */
    // Finalise Project: Invoice: -----> 2
    public static void finaliseProject(StringBuilder completeProject) {

        // Create Copy of String Builder:
        StringBuilder copyCompletedProject = new StringBuilder();
        copyCompletedProject.append(completeProject);

        // Store Project Lines as Array List:
        ArrayList<String> listCustomer;
        ArrayList<String> listProject;
        ArrayList<String> listArchitect;
        ArrayList<String> listBuilder;

        // for: Line in copy of CompletedProject: Set Details:
        for (String detailLine : copyCompletedProject.toString().split("\n")) {

            listCustomer = new ArrayList<>(Arrays.asList(detailLine.split(", ")));
            if (listCustomer.get(0).equals("Customer Details")) {
                // Customer:
                ProjectPersonal.NewCustomer.customerName = listCustomer.get(1);
                ProjectPersonal.NewCustomer.customerTelNum = listCustomer.get(2);
                ProjectPersonal.NewCustomer.customerEmail = listCustomer.get(3);
                ProjectPersonal.NewCustomer.customerPhyAdd = listCustomer.get(4);

            }
            listProject = new ArrayList<>(Arrays.asList(detailLine.split(", ")));
            if (listProject.get(0).equals("Project Details")) {
                // Project:
                CreateNewProject.proNumber = Integer.parseInt((listProject.get(1)));
                CreateNewProject.proName = listProject.get(2);
                CreateNewProject.proType = listProject.get(3);
                CreateNewProject.proPhysAdd = listProject.get(4);
                CreateNewProject.proERF = (Integer.parseInt(listProject.get(5)));
                CreateNewProject.proTotFee = (Float.parseFloat(listProject.get(6)));
                CreateNewProject.proTotPaid = (Float.parseFloat(listProject.get(7)));
                CreateNewProject.proDeadline = listProject.get(8);

            }
            listArchitect = new ArrayList<>(Arrays.asList(detailLine.split(", ")));
            if (listArchitect.get(0).equals("Architect Details")) {
                // Architect:
                ProjectPersonal.NewArchitect.archName = listArchitect.get(1);
                ProjectPersonal.NewArchitect.archTelNum = listArchitect.get(2);
                ProjectPersonal.NewArchitect.archEmail = listArchitect.get(3);
                ProjectPersonal.NewArchitect.archPhyAdd = listArchitect.get(4);
            }
            listBuilder = new ArrayList<>(Arrays.asList(detailLine.split(", ")));
            if (detailLine.equals("Builder Details")) {
                // Builder:
                ProjectPersonal.NewBuildContractor.buildName = listBuilder.get(1);
                ProjectPersonal.NewBuildContractor.buildName = listBuilder.get(2);
                ProjectPersonal.NewBuildContractor.buildName = listBuilder.get(3);
                ProjectPersonal.NewBuildContractor.buildName = listBuilder.get(4);
            }
        }

        //File Location:
        String fileLocation;
        // try: Open File and Write Completed Project:
        try {
            // Create Time Stamp: DATE - (TIME - Hour.Min.Sec)
            String fileDateTime = new SimpleDateFormat("yyyy-MM-dd(HH.mm.ss)").format(new Date());

            // Create File: Write File:
            File finaliseFile = new File("Invoices\\" + fileDateTime + "--Invoice.txt");
            FileWriter fileWriter = new FileWriter(finaliseFile, true);
            PrintWriter finaliseWriter = new PrintWriter(new FileWriter(finaliseFile, true));

            // File Location/Path:
            fileLocation = finaliseFile.getAbsolutePath();

            // New Project:
            finaliseWriter.append("______________________________________________________________________\n");
            finaliseWriter.append("Invoice: " + "Project Number: ").append(String.valueOf(CreateNewProject.proNumber));

            // Date Invoice was generated:
            LocalDate invoiceDate = LocalDate.now();
            finaliseWriter.append("\nDate: ").append(String.valueOf(invoiceDate));
            finaliseWriter.append("\n______________________________________________________________________\n");

            // Customer Contact Details:
            finaliseWriter.append("Customer Details:\n");
            finaliseWriter.append("______________________________________________________________________\n");
            finaliseWriter.append("Customer Name:.........................  ").append(ProjectPersonal.NewCustomer.customerName)
                    .append("\nCustomer Telephone Number:.............  ").append(ProjectPersonal.NewCustomer.customerTelNum);

            // Outstanding Fee Amount:
            finaliseWriter.append("\n" +
                    "\nCustomer Outstanding Fees:");
            float outstandingProjectFee = CreateNewProject.proTotFee - CreateNewProject.proTotPaid;
            finaliseWriter.append("\nTotal Amount:..........................  ").append(String.valueOf(outstandingProjectFee));
            finaliseWriter.append("\n______________________________________________________________________\n");

            //Completed Project:
            finaliseWriter.append("Completed Project:\n");
            CreateNewProject finalizedProject = new CreateNewProject(
                    CreateNewProject.proNumber,
                    CreateNewProject.proName,
                    CreateNewProject.proType,
                    CreateNewProject.proPhysAdd,
                    CreateNewProject.proERF,
                    CreateNewProject.proTotFee,
                    CreateNewProject.proTotPaid,
                    CreateNewProject.proDeadline
            );

            // Print Final Completed Project:
            finaliseWriter.append(finalizedProject.toString());

            // Display Message & Invoice Path:
            System.out.println("Invoice Complete." +
                    "\n");
            System.out.println("Invoice Location: " + fileLocation +
                    "\n");

            // Close:
            finaliseWriter.close();
            fileWriter.close();
        }
        // catch: Exception:
        catch (Exception finalisedEx) {
            System.out.println("File Can't Be Generated.");
        }

        // Write to Project Complete File:
        completedProject(completeProject);

        // Return to Main Menu:
        mainMenu();
    }

    /**
     * Writes completed project to CompletedFile.txt.
     * Removes Project From Incomplete Projects (ProjectFile.txt
     *
     * @param completeProject - Scanner for Completing Project.
     */
    // Finalise Project: Complete Project: ----- 3
    public static void completedProject(StringBuilder completeProject) {
        // try : Create Invoice:
        try {
            // Make String Builder List:
            StringBuilder completedProject = new StringBuilder();

            // Time Project was Finalized:
            String finalizedTime = new SimpleDateFormat("yyyy-MM-dd(HH.mm.ss)").format(new Date());
            completedProject.append("___________________________________________________________________________________________________________\n");
            completedProject.append("___________________________________________________________________________________________________________\n");
            completedProject.append("Finalised Date: ").append(finalizedTime).append("\n");

            // Create Array:
            ArrayList<String> completedList;

            for (String completedLine : completeProject.toString().split("\n")) {
                completedList = new ArrayList<>(Arrays.asList(completedLine.split(", ")));


                if (completedList.get(0).equals("Customer Details")) {
                    ProjectPersonal.NewCustomer customerDetails = new ProjectPersonal.NewCustomer(
                            completedList.get(1),
                            completedList.get(2),
                            completedList.get(3),
                            completedList.get(4)
                    );
                    completedProject.append(customerDetails.toString()).append("\n");

                }
                if (completedList.get(0).equals("Project Details")) {
                    CreateNewProject projectDetails = new CreateNewProject(
                            Integer.parseInt(completedList.get(1)),
                            completedList.get(2),
                            completedList.get(3),
                            completedList.get(4),
                            Integer.parseInt(completedList.get(5)),
                            Float.parseFloat(completedList.get(6)),
                            Float.parseFloat(completedList.get(7)),
                            completedList.get(8)
                    );
                    completedProject.append(projectDetails.toString()).append("\n");
                }

                if (completedList.get(0).equals("Architect Details")) {
                    ProjectPersonal.NewArchitect architectDetails = new ProjectPersonal.NewArchitect(
                            completedList.get(1),
                            completedList.get(2),
                            completedList.get(3),
                            completedList.get(4)
                    );
                    completedProject.append(architectDetails.toString()).append("\n");
                }
                if (completedList.get(0).equals("Builder Details")) {
                    ProjectPersonal.NewBuildContractor builderDetails = new ProjectPersonal.NewBuildContractor(
                            completedList.get(1),
                            completedList.get(2),
                            completedList.get(3),
                            completedList.get(4)
                    );
                    completedProject.append(builderDetails.toString()).append("\n");
                }
            }

            // OpenFile:
            File completedFile = new File("CompletedFile.txt");
            PrintWriter completedWrite = new PrintWriter(new FileWriter(completedFile, true), true);


            // Write to file:
            completedWrite.append(completedProject.toString());

            //Close:
            completedWrite.close();

            // return mainMenu:
            mainMenu();
        } catch (Exception completedEx) {
            System.out.println("File Not Found");
        }
    }

    /**
     * Reads all lines in CompletedFile.txt
     * Displays lines/ completed projects.
     */
    // View All Completed Projects:
    public static void viewCompletedProjects() {
        // try: Read File:
        try {
            File completedFile = new File("CompletedFile.txt");
            Scanner completedScanner = new Scanner(completedFile);

            // Display: Line in CompletedFile.txt:
            while (completedScanner.hasNextLine()) {
                System.out.println(completedScanner.nextLine());
            }

            // File is empty:
            if (completedFile.length() <= 0) {
                System.out.println("\n" +
                        "No projects have been finalised.\n" +
                        "To mark a project Complete: [Main Menu - Option - 4.]" +
                        "\n");
            }
            // return to mainMenu:
            mainMenu();
        }
        //catch: Exception:
        catch (FileNotFoundException viewCompletedEx) {
            System.out.println("File Not Found.");
            // return to Main Menu:
            mainMenu();
        }
    }

    /**
     * Reads all line in ProjectFile.txt
     * Displays lines/ incomplete projects.
     *
     * @param incompleteProjectScanner - Scanner for Incomplete Projects
     */
    // View All Incomplete Projects:
    public static void viewIncompleteProjects(Scanner incompleteProjectScanner) {
        // String Builder: Projects:
        StringBuilder projectFile = readProject(incompleteProjectScanner);

        // String Builder: Incomplete Project:
        StringBuilder incompleteProject = new StringBuilder();

        // for : Line in ProjectFile:
        for (String incompleteLine : projectFile.toString().split("\n")) {
            // Control Line with ArrayList:
            ArrayList<String> incompleteList = new ArrayList<>(Arrays.asList(incompleteLine.split(", ")));

            // Customer Details:
            if (incompleteList.get(0).equals("Customer Details")) {
                // Incomplete Project:
                incompleteProject.append(
                        "_____________________________________________________________________________________\n" +
                                "_____________________________________________________________________________________\n" +
                                "Incomplete Project:" +
                                "\n");
                ProjectPersonal.NewCustomer customerDetails = new ProjectPersonal.NewCustomer(
                        incompleteList.get(1),
                        incompleteList.get(2),
                        incompleteList.get(3),
                        incompleteList.get(4)
                );
                // append: Customer Object to String Builder:
                incompleteProject.append(customerDetails.toString()).append("\n");

            }
            // Project Details:
            if (incompleteList.get(0).equals("Project Details")) {
                CreateNewProject projectDetails = new CreateNewProject(
                        Integer.parseInt(incompleteList.get(1)),
                        incompleteList.get(2),
                        incompleteList.get(3),
                        incompleteList.get(4),
                        Integer.parseInt(incompleteList.get(5)),
                        Float.parseFloat(incompleteList.get(6)),
                        Float.parseFloat(incompleteList.get(7)),
                        incompleteList.get(8)
                );
                // append: Project Object to String Builder:
                incompleteProject.append(projectDetails.toString()).append("\n");
            }
            // Architect Details:
            if (incompleteList.get(0).equals("Architect Details")) {
                ProjectPersonal.NewArchitect architectDetails = new ProjectPersonal.NewArchitect(
                        incompleteList.get(1),
                        incompleteList.get(2),
                        incompleteList.get(3),
                        incompleteList.get(4)
                );
                // append: Architect Object to String Builder:
                incompleteProject.append(architectDetails.toString()).append("\n");
            }
            // Builder Details:
            if (incompleteList.get(0).equals("Builder Details")) {
                ProjectPersonal.NewBuildContractor builderDetails = new ProjectPersonal.NewBuildContractor(
                        incompleteList.get(1),
                        incompleteList.get(2),
                        incompleteList.get(3),
                        incompleteList.get(4)
                );
                // append: Builder Object to String Builder:
                incompleteProject.append(builderDetails.toString()).append("\n");
            }
        }
        // Display Incomplete Projects:
        System.out.println(incompleteProject.toString());

        // return Main Menu:
        mainMenu();
    }

    /**
     * Reads data in ProjectFile.txt
     * Changes String Date to type Date.
     * Displays all projects that have passed the Project Deadline.
     */
    // View All Overdue Projects:
    public static void overdueProjects() {
        // try: Get Overdue Projects by comparing dates:
        try {
            // Open File: Read File:
            File projectFile = new File("ProjectFile.txt");
            Scanner projectFileScanner = new Scanner(projectFile);

            // Create String Builder for Incomplete Projects:
            StringBuilder overdueProjects = new StringBuilder();

            // Create Array to store projectLines:
            ArrayList<String> projectList;

            // Set Date Format:
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            // Get Current Date:
            String currentDateString = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            Date currentDate = simpleDateFormat.parse(currentDateString);

            // Store Lines in ProjectFile.txt:
            while (projectFileScanner.hasNext()) {
                String projectLine = projectFileScanner.nextLine();

                // Cast projectLine to ListArray:
                projectList = new ArrayList<>(Arrays.asList(projectLine.split(", ")));

                // if projectList is Project Details Line:
                if (projectList.get(0).equals("Project Details")) {
                    // Project Date:
                    String projectDateString = projectList.get(8);
                    Date projectDate = simpleDateFormat.parse(projectDateString);

                    // if: Project is overdue:
                    if (projectDate.before(currentDate)) {
                        overdueProjects.append("______________________________________________________\n" + "Date Today: ")
                                .append(currentDateString)
                                .append("\nOverdue Project:")
                                .append("\n");

                        CreateNewProject projectDetails = new CreateNewProject(
                                Integer.parseInt(projectList.get(1)),
                                projectList.get(2),
                                projectList.get(3),
                                projectList.get(4),
                                Integer.parseInt(projectList.get(5)),
                                Float.parseFloat(projectList.get(6)),
                                Float.parseFloat(projectList.get(7)),
                                projectList.get(8)
                        );
                        // append: Overdue Project to String Builder:
                        overdueProjects.append(projectDetails.toString()).append("\n");
                    }
                }
            }
            // if: No Projects are Overdue and Incomplete:
            if (overdueProjects.length() <= 0) {
                System.out.println("All projects are up to date:\n");
                // return Main Menu:
                mainMenu();
            } else {
                // Display Overdue Projects:
                System.out.println(overdueProjects.toString());
                mainMenu();
            }
        }
        // catch: Exception:
        catch (FileNotFoundException | ParseException overdueEx) {
            System.out.println("File Not Found.");
            // return to Main Menu:
            mainMenu();
        }
    }

    /**
     * Requests input on Project Number.
     * Searches for Project Number in ProjectFile.txt
     * Displays Project.
     *
     * @param findProjectScanner - Scanner for Finding Existing Project.
     */
    // Find Existing Task:
    public static void findExistingProject(Scanner findProjectScanner) {
        // try: Read File: Find Project:
        try {
            // Request Project number:
            System.out.print("Enter Project Number\n" +
                    ": ");
            int userProjectNumber = Integer.parseInt(findProjectScanner.nextLine());

            // Read File:
            File projectFile = new File("ProjectFile.txt");
            Scanner projectScannerFile = new Scanner(projectFile);

            StringBuilder foundProject = new StringBuilder();

            // Store: Every 4 Lines as 4 Lines = One Project:
            while (projectScannerFile.hasNext()) {
                String customerLine = projectScannerFile.nextLine();
                String projectLine = projectScannerFile.nextLine();
                String architectLine = projectScannerFile.nextLine();
                String builderLine = projectScannerFile.nextLine();

                // Customer List:
                ArrayList<String> customerList;
                customerList = new ArrayList<>(Arrays.asList(customerLine.split(", ")));
                // Project List:
                ArrayList<String> projectList;
                projectList = new ArrayList<>(Arrays.asList(projectLine.split(", ")));
                // Architect List:
                ArrayList<String> architectList;
                architectList = new ArrayList<>(Arrays.asList(architectLine.split(", ")));
                // Builder List:
                ArrayList<String> builderList;
                builderList = new ArrayList<>(Arrays.asList(builderLine.split(", ")));

                // if Project Number equals User Project Number
                if (projectList.get(1).equals(String.valueOf(userProjectNumber))) {

                    ProjectPersonal.NewCustomer customerDetails = new ProjectPersonal.NewCustomer(
                            customerList.get(1),
                            customerList.get(2),
                            customerList.get(3),
                            customerList.get(4)
                    );
                    // append: Customer Details to StringBuilder foundProject:
                    foundProject.append(customerDetails.toString()).append("\n");

                    CreateNewProject projectDetails = new CreateNewProject(
                            Integer.parseInt(projectList.get(1)),
                            projectList.get(2),
                            projectList.get(3),
                            projectList.get(4),
                            Integer.parseInt(projectList.get(5)),
                            Float.parseFloat(projectList.get(6)),
                            Float.parseFloat(projectList.get(7)),
                            projectList.get(8)
                    );
                    // append: Project Details to StringBuilder foundProject:
                    foundProject.append(projectDetails.toString()).append("\n");

                    ProjectPersonal.NewArchitect architectDetails = new ProjectPersonal.NewArchitect(
                            architectList.get(1),
                            architectList.get(2),
                            architectList.get(3),
                            architectList.get(4)
                    );
                    // append: Architect Details to StringBuilder foundProject:
                    foundProject.append(architectDetails).append("\n");

                    ProjectPersonal.NewBuildContractor builderDetails = new ProjectPersonal.NewBuildContractor(
                            builderList.get(1),
                            builderList.get(2),
                            builderList.get(3),
                            builderList.get(4)
                    );
                    // append: Builder Details to StringBuilder foundProject:
                    foundProject.append(builderDetails).append("\n");
                }
            }
            if (foundProject.length() <= 0) {
                System.out.println("Project Doesn't Exist.");
                // return Main Menu:
                mainMenu();
            } else {
                // Display Project:
                System.out.println(foundProject.toString());

                // Return mainMenu:
                mainMenu();
            }

        }
        // catch: Exception:
        catch (FileNotFoundException exception) {
            System.out.println("File Not Found.");

            // Return mainMenu:
            mainMenu();
        }
    }
}