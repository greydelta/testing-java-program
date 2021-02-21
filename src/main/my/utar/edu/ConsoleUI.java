package my.utar.edu;
// @@@@@@@ Expand comment below for assumptions  @@@@@@@

/* Class: ConsoleUI
 * Function: 
 * 
 * Summary of Content:
 * Variables
 * Constructor
 * Accessor Methods
 * 
 * SECTION A (Load all data from text file)
 * Method A  : Execute all load methods 							> public void loadAllData()
 * Method A1 : Load all Members 									> public void loadMembersData()
 * Method A2 : Load all Items 										> public void loadItemsData()
 * Method A3 : Load all Charges 									> public void loadDeliveryChargesData()
 * 
 */


import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class ConsoleUI {

	/* Variables
	****************************************/
	private Scanner scan;
	private Controller control;
	
	/* Constructor 
	****************************************/
	public ConsoleUI() {
		scan = null;
		control = null;
	}
	
	/* Accessor Methods
	*****************************************/
	public void setScanner(Scanner sc) {
		this.scan = sc;
	}
	public void setController(Controller cont) {
		this.control = cont;
	}
	public Controller getController() {
		return control;
	}
	
	/* =======================================
	 * ============== SECTION A ==============
	 * ======================================= */
	
	/* Method A : Execute all load methods
	*****************************************/
	public void loadAllData() {
		loadMembersData();
		loadItemsData();
		loadDeliveryChargesData();
	}
	
	/* Method A1 : Load all Members
	*****************************************/
	public void loadMembersData() {
		File member = new File("member.txt");
		try {
			setScanner(new Scanner(member));
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		int count=0;
		while(scan.hasNextLine()) {
			String dataFromFile = scan.nextLine();
			String[] split = dataFromFile.split(";");
			String name = split[0];
			String password = split[1];
			String number = split[2];
			boolean flag = Boolean.parseBoolean(split[3]);
			String unit = split[4];
			String street = split[5];
			String area = split[6];
			String district = split[7];
			String postal = split[8];
			int state = Integer.parseInt(split[9]);
			control.addMember(name, password, number, flag, unit, street, area, district, postal, state);
			count++;
		}
		System.out.println("Loaded "+count+" rows of data from member.txt");
	}
	
	/* Method A2 : Load all Items
	*****************************************/
	public void loadItemsData() {
		File item = new File("items.txt");
		try {
			setScanner(new Scanner(item));
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		int count=0;
		while(scan.hasNextLine()) {
			String dataFromFile = scan.nextLine();
			String[] split = dataFromFile.split(";");
			String name = split[0];
			String type = split[1];
			double mprice = Double.parseDouble(split[2]);
			double nmprice = Double.parseDouble(split[3]);
			char promo = split[4].charAt(0);

			control.addItem(name, type, mprice, nmprice, promo);
			count++;
		}
		System.out.println("Loaded "+count+" rows of data from items.txt");
	}
	
	/* Method A3 : Load all Charges
	*****************************************/
	public void loadDeliveryChargesData() {
		File deliveryCharges = new File("delivery_charges.txt");
		try {
			setScanner(new Scanner(deliveryCharges));
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		int count=0;
		while(scan.hasNextLine()) {
			String dataFromFile = scan.nextLine();
			String[] split = dataFromFile.split(";");
			String areaCode = split[0];
			String area = split[1];
			double charges = Double.parseDouble(split[2]);

			control.addDeliveryCharges(areaCode, area, charges);
			count++;
		}
		System.out.println("Loaded "+count+" rows of data from delivery_charges.txt");
	}
	
	public void launch() {
		Member loginSession = mainMenu();
		int choice = -1;
		while(choice != 3) {
			choice = subMenu(loginSession);
			if(choice == 3)
				System.out.println("Thank you for using our system!");
		}
	}
	
	public Member mainMenu() {
		setScanner(new Scanner(System.in));
		Member login = null;
		int mainMenuChoice, status =-1, checkAddress, loop =-1;
		
		do {
			mainMenuChoice = -1;
			login = null;
			checkAddress = 2;
			System.out.println("\n<<Main Menu>>");
			System.out.println("==================================");
			System.out.println("       Welcome to MK Bakery");
			System.out.println("    Homemade Cakes & Pastries");
			System.out.println("==================================");
			System.out.println("Would you like to, select (1 to 4)");
			System.out.println("1. Login as Member");
			System.out.println("2. Login as Guest");
			System.out.println("3. Register as Member");
			System.out.println("4. Exit this system");
			
			do {
				System.out.print(">>Choice: ");
				try {
					mainMenuChoice = intInputValidation(1, 4); 
					System.out.print("\n");
					status = 1;} // Valid > Proceed to Choice
				catch (IllegalArgumentException e) {
					System.err.println(e.getMessage());
					bufferFor5Miliseconds();
					status = 0;} // Invalid > Request for input again
			}while(status != 1);
			
		return login;
	}
	
	public int subMenu(Member loginValid) {
		int choice = -1;
		
		System.out.println("\n<<Sub-Menu>>");
		if(loginValid.getFlag() == true)
			System.out.println(" >>Logged In as (Member): "+loginValid.getName());
		else if(loginValid.getFlag() == false)
			System.out.println(" >>Logged In as (Guest): "+loginValid.getName());
		System.out.println(" 1. Place Order");
		System.out.println(" 2. Track Orders");
		System.out.println(" 3. Exit this system");
		System.out.print(">>Choice: ");
		try {
			choice = intInputValidation(1, 3);}
		catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
			bufferFor5Miliseconds();}

		return choice;
	}
	
	public Member login() {
		int signInChoice = -1, doWhile1 = -1, doWhile2 = -1, doWhile3 = -1,
			innerDoWhile1 = -1, innerDoWhile2 = -1;
		Member loginValid = null;
		String name = null, pass = null;
		
		do { // doWhile3
			do { //doWhile1
				System.out.println("\n<<Login>>");
				
				do { // innerDoWhile1
					System.out.print("Enter Name: ");
					// Check if input is valid
					try {
						name = stringInputValidation();
						innerDoWhile1 = 1;}
					catch (IllegalArgumentException e) {
						System.err.println(e.getMessage());
						bufferFor5Miliseconds();
						innerDoWhile1 = 0;}
				}while(innerDoWhile1 != 1);
				
				do {
					System.out.print("Enter Password: ");
					// Check if input is valid
					try {
						pass = stringInputValidation();
						innerDoWhile2 = 1;}   
					catch (IllegalArgumentException e) {
						System.err.println(e.getMessage());
						bufferFor5Miliseconds();
						innerDoWhile2 = 0;}
				}while(innerDoWhile2 != 1);
				
				// Both input are valid, proceed to validate
				loginValid = validateMember(name, pass);
				doWhile1 = 1;
			}while(doWhile1 != 1);
			
			if(loginValid.getFlag() == true) {
				System.out.println("\nLogged in as: " + loginValid.getName());
				System.out.println("Delivery Address: " + loginValid.getAddressToString() +", "+ convertAddress(loginValid.getState()));
				return loginValid;
			}
			else {
				do {
					System.out.println("\nIncorrect name/password.");
					System.out.println("1. Login again");
					System.out.println("2. Exit to Main Menu");
					System.out.print(">>Choice: ");
					try {
						signInChoice = intInputValidation(1, 2); 
					} 
					catch (IllegalArgumentException e) {
						bufferFor5Miliseconds();
						System.err.println(e.getMessage());
					}
					if(signInChoice == 1) {
						doWhile1 = 0; 
						doWhile2 = 1; // Prompt enter input
					}
					else if(signInChoice == 2) {
						return loginValid;
					}
					else {
						doWhile1 = 1;
						doWhile2 = 0;
					}
				}while(doWhile2 != 1);
			}
		}while(doWhile3 != 1);
		
		return loginValid;
	}
	
	public Member validateMember(String name, String pass) {
		List<Member> members = control.getAllMembers();
		Member result = new Member();
		for (Member tempMember: members) {
			if(name.equals(tempMember.getName()) && pass.equals(tempMember.getPassword())) {
				result = tempMember;
				break;
			}
			else
				result.setFlag(false);
		}
		return result;
	}
	
    public Member promptInputDetails(){
    	String name = null, phone = null;
    	int doWhile1 = -1, doWhile2 = -1;
    	System.out.println("<<Contact Details>>");
		do {
			System.out.print("Enter Name: ");
			try {
				name = stringInputValidation();
				doWhile1 = 1;}   
			catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
				bufferFor5Miliseconds();
				doWhile1 = 0;} 
		}while(doWhile1 != 1);
    	
		do {
			System.out.print("Enter Phone Number: ");
			try {
				phone = stringInputValidation();
				doWhile2 = 1;}   
			catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
				bufferFor5Miliseconds();
				doWhile2 =0;}
		}while(doWhile2 != 1);

		boolean flag = false;
		Member member = new Member(name, phone, flag);
    	return member;
    }
    
    public Address promptInputAddress() {
    	setScanner(new Scanner(System.in));
    	
    	int inputState = 0, inputArea = 0, inputAddressChoice = -1, 
    	doWhile1 = -1, doWhile2 = -1, doWhile3 = -1, doWhile4 = -1,
    	doWhile5 = -1, doWhile6 = -1, doWhile7 = -1, doWhile8 = -1;
    	String unit = null, street = null, district = null, postal = null;
    	
    	do { //doWhile1
    		System.out.println("\n<<Enter Address>>");
	    	do { //doWhile2
	    		System.out.println("Which state do you live in?");
	    		System.out.print(" 1. Johor\t 6. Pahang\t 10. Sabah\n "
	    						+ "2. Kedah\t 7. Penang\t 11. Sarawak\n "
	    						+ "3. Kelantan\t 8. Perak\t 12. Selangor\n "
	    						+ "4. Melaka\t 9. Perlis\t 13. Terengganu\n "
	    						+ "5. N.Sembilan\n");
	    		System.out.print(">>Choice: ");
	    		try {
	    			inputState = intInputValidation(1, 13); 
	    			if(inputState != 4)
	    				doWhile3 = 1;
	    			doWhile2 = 1; // Valid input
				} 
				catch (IllegalArgumentException e) {
					System.err.println(e.getMessage());
					bufferFor5Miliseconds();
					doWhile2 = 0; // Invalid input
				}
	    	}while(doWhile2 != 1);
	    	
	    	while(doWhile3 != 1){ //doWhile3
	    		System.out.println("\nWhich area do you live in?");
	    		System.out.print(" 1. Alor Gajah\t\t 6. Bemban\t\t 11. Kuala Sungai Baru\t 16. Selandar\n "
	    						+ "2. Asahan\t\t 7. Bukit Beruang\t 12. Lubok China\t 17. Sungai Rambai\n "
	    						+ "3. Ayer Keroh\t\t 8. Durian Tunggal\t 13. Masjid Tanah\t 18. Sungai Udang\n "
	    						+ "4. Bandar Hilir\t 9. Jasin\t\t 14. Melaka Tengah\t 19. Tanjong Kling\n "
	    						+ "5. Batu Berendam\t 10. Kuala Linggi\t 15. Merlimau\t\t 20. Ujong Pasir\n");
	    		System.out.print(">>Choice: ");
	    		try {
	    			inputArea = intInputValidation(1, 20); 
	    			doWhile3 = 1; // Valid input
				} 
				catch (IllegalArgumentException e) {
					System.err.println(e.getMessage());
					bufferFor5Miliseconds();
					doWhile3 = 0; // Invalid input
				}
	    	}
	    	
	    	do { //doWhile4
	    		int validState = validateAddress(inputState);
	    		
	    		if(validState == 1) { // proceed
    	    		doWhile4 = 1; 
    	    		doWhile1 = 1; // Exit both loops
	    		}
	    		else if(validState == 0) {
	    			System.out.println("\n<<Delivery only available for MELAKA!>>");
					System.out.println("1. Enter address again");
					System.out.println("2. Exit to Main Menu");
					System.out.print(">>Choice: ");
					try {
						inputAddressChoice = intInputValidation(1, 2); 
					} 
					catch (IllegalArgumentException e) {
						System.err.println(e.getMessage());
						bufferFor5Miliseconds();
					}
					
					if(inputAddressChoice == 1){
						doWhile4 = 1; 
						doWhile2 = 0; 
						doWhile3 = 0; // Exit doWhile4 and enter doWhile2/3
					}
					else if(inputAddressChoice == 2) {
						return null;
					}
					else {
						doWhile4 = 0;
						validState = 0; // Invalid choice
					}
	    		}
	    	}while(doWhile4 != 1);
    	}while(doWhile1 != 1);
    	
    	do { //doWhile5
    		System.out.print("Enter Unit Number: ");
    		try {
    			unit = stringInputValidation();
    			doWhile5 = 1;}   
    		catch (IllegalArgumentException e) {
    			System.err.println(e.getMessage());
    			bufferFor5Miliseconds();
    			doWhile5 = 0;}
    	}while(doWhile5 !=1);
    	
    	do { //doWhile6
    		System.out.print("Enter Street Name: ");
    		try {
    			street = stringInputValidation();
    			doWhile6 = 1;}   
    		catch (IllegalArgumentException e) {
    			System.err.println(e.getMessage());
    			bufferFor5Miliseconds();
    			doWhile6 = 0;}
    	}while(doWhile6 !=1);

		do { //doWhile7
			System.out.print("Enter District: ");
			try {
				district = stringInputValidation();
				doWhile7 = 1;}   
			catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
				bufferFor5Miliseconds();
				doWhile7 = 0;}
		}while(doWhile7 !=1);

		do { //doWhile8
			System.out.print("Enter Postal Code: ");
			try {
				postal = stringInputValidation();
				doWhile8 = 1;}   
			catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
				bufferFor5Miliseconds();
				doWhile8 = 0;}
		}while(doWhile8 !=1);
		
		String area = convertArea(inputArea);
		Address add = new Address(unit, street, area, district, postal, 4);
		return add;
    }
    
    public int validateAddress(int state) {
    	if(state == 4) 
    		return 1; // Melaka
    	else if(state >= 1 && state <= 13 && state != 4) 
    		return 0; // All other states 
    	else
    		return -1; // Input validation
    }
    
    public String convertAddress(int rawAddress) {
    	switch(rawAddress) {
    		case 1: return "Johor"; 
    		case 2: return "Kedah";
    		case 3: return "Kelantan";
    		case 4: return "Melaka";
    		case 5: return "N.Sembilan";
    		case 6: return "Pahang";
    		case 7: return "Penang";
    		case 8: return "Perak";
    		case 9: return "Perlis";
    		case 10: return "Sabah";
    		case 11: return "Sarawak";
    		case 12: return "Selangor";
    		case 13: return "Terengganu";
    		default: return null;
    	}
    }
    
    public String convertArea(int rawArea) {
    	switch(rawArea) {
    		case 1: return "AG"; 
    		case 2: return "AH";
    		case 3: return "AK";
    		case 4: return "BH";
    		case 5: return "BD";
    		case 6: return "BB";
    		case 7: return "BR";
    		case 8: return "DT";
    		case 9: return "JN";
    		case 10: return "KG";
    		case 11: return "KS";
    		case 12: return "LC";
    		case 13: return "MT";
    		case 14: return "ME";
    		case 15: return "ML";
    		case 16: return "SE";
    		case 17: return "SR";
    		case 18: return "SU";
    		case 19: return "TK";
    		case 20: return "UP";
    		default: return null;
    	}
    }
    
    public int intInputValidation(int lower, int upper) throws IllegalArgumentException {
        setScanner(new Scanner(System.in));
        int userInput;
        
        if (scan.hasNextLine()){
        	String tempString = scan.nextLine();
        	
        	// Check if blank / only whitespace
        	if(tempString.isEmpty() == true)
        		throw new IllegalArgumentException("\nNot allowed to enter blank values!");
        	else
        	
        	// Check if is valid integer
        	try {
        		// Convert to integer
        		userInput = Integer.parseInt(tempString);
        	}
        	catch (NumberFormatException e) {
        		throw new IllegalArgumentException("\nEnter integers Only!");
        	}
        	
        	// Check if range is same, then can only enter that value
            if(lower == upper) 
            	if(userInput != lower)
            		throw new IllegalArgumentException("\nEnter "+lower+" only!");
            	else
            		return userInput;
           
            // Check if it is within range
            if (userInput < lower || userInput > upper)
                throw new IllegalArgumentException("\nEnter values between "+lower+" and "+upper+" only!");
            else
                return userInput;
        } 
        else
            throw new IllegalArgumentException("\nEnter integers Only!");
    }
    
    public String stringInputValidation() throws IllegalArgumentException{
    	setScanner(new Scanner(System.in));
    	String userInput;
    	if (scan.hasNextLine()){
    		userInput = scan.nextLine();
    		if(userInput.isEmpty() == true || userInput.equals(" ")==true)
        		throw new IllegalArgumentException("Not allowed to enter blank values!");
    		else
    			return userInput;
    	}
    	else
    		throw new IllegalArgumentException("Scanner is closed");	
    }
    
    public void bufferFor5Miliseconds() {
    	try {
			TimeUnit.MILLISECONDS.sleep(500);}
		catch (InterruptedException e1) {
			System.err.print(e1.getMessage());}
    }
}
