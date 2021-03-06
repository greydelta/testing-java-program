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
 * SECTION B (Menus / Members Module)
 * Method B  : Launch Main Menu & Sub Menu 							> public void launch()
 * Method B1 : Main Menu  											> public Member mainMenu()
 * Method B2 : Sub Menu 											> public int subMenu(Member loginValid)
 * Method B3 : Method to check member login 						> public Member login() 
 * Method B4 : Method to validate member login 						> public Member validateMember(String name, String pass)
 * Method B5 : Method to get input details (guest/register user) 	> public Member promptInputDetails()
 * Method B6 : Method to get input address (guest/register user) 	> public Address promptInputAddress()
 * Method B7 : Method to validate address 							> public int validateAddress(int state)
 * Method B8 : Method to convert address from raw data in txt file 	> public String convertAddress(int rawAddress)
 * Method B9 : Method to convert area from raw data in user input 	> public String convertArea(int rawArea) 
 * 
 * SECTION C (Place Order Module)
 * Method C  : Method to initiate Order Module 												> public void placeOrder(Member loginValid)
 * Method C1 : Method to Display all items 													> public void displayItemList(Member loginValid) 
 * Method C2 : Method to compute Item promotional price  									> public static double calculatePromotionalPrice(Item item, Member member)
 * Method C3 : Method to get input on Items Selected (Place order) 							>  public void promptInputOrderItems(Member loginValid)
 * Method C4 : Method to Compute Delivery Charges 											> public double calculateDeliveryCharge(String area)
 * Method C5 : Method to compute subTotal of an (item x quantity) + with/without promotion 	> public static double calculateItemPrice(Item item, int quantity, Member member)
 * Method C6 : Method to compute additional charge											> public int calculateAdditionalCharge(double itemTotal)
 * 
 * SECTION D (Make Payment)
 * Method D : Method to initiate payment 								> makePayment(Member loginValid, Order order)
 * Method D1 : Method to get input on Payment details (Make Payment) 	> promptInputMakePayment(Member loginValid, Order order)
 * Method D2 : Method to process Payment details 						> makingPayment(int choice, double price, Payment payment)
 * 
 * SECTION E (Track Order)
 * Method E : Method Method to initiate Track Order 	> trackOrder(Member loginValid)
 * Method E1 : Method Method to initiate Track Order 	> displayAllOrders(Member loginValid)
 * 
 * SECTION F (Utility Methods)
 * Method F1 : Method for integer input validation 		> public int intInputValidation(int min, int max) throws IllegalArgumentException 
 * Method F2 : Method for string input validation		> public String stringInputValidation() throws IllegalArgumentException
 * Method F3 : Pause execution for 5 seconds			> public void bufferFor5Miliseconds()
 */

/*
 * Assumptions
 * 1. Existing users (in cust_db) are from valid states only.
 * 2. Guest / Newly Registered users will be checked by the system on their address, if not in Melaka > cannot place order
 * 3. Delivery Address 
 * 		- Member > will use existing address (can't choose to send to new address)
 * 		- Guest / Registered member > will use recent address
 * 4. Placing Order
 * 		> Item Quantity is capped at 20 (max) PER Order
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
	
	/* =======================================
	 * ============== SECTION B ==============
	 * ======================================= */
	
	/* Method B : Launch Main Menu & Sub Menu
	*****************************************/
	public void launch() {
		Member loginSession = mainMenu();
		int choice = -1;
		while(choice != 3) {
			choice = subMenu(loginSession);
			if(choice == 3)
				System.out.println("Thank you for using our system!");
		}
	}
	
	/* Method B1 : Main Menu
	*****************************************/
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
			
			switch(mainMenuChoice){
				case 1: 
					login = login();

					break;

				case 2: 
					System.out.println("\n<<Proceed as Guest>>");
					Member guest = promptInputDetails();
					Address add = promptInputAddress();
					
					if(add == null) {
						status = 0;
					}
					
					if(add != null) {
						guest.setUnitNumber(add.getUnitNumber());
						guest.setStreetName(add.getStreetName());
						guest.setArea(add.getArea());
						guest.setDistrict(add.getDistrict());
						guest.setPostalCode(add.getPostalCode());
						guest.setState(add.getState());
						System.out.println("Delivery Address: " + guest.getAddressToString() +", "+ convertAddress(guest.getState()));
						status = 1;
					}
					login = guest; 
					break;
					
				case 3: 
					System.out.println("\n<<Register>>");
					
					Member regUser = promptInputDetails();
					regUser.setFlag(true);
					// implement check for existing account
					Address regAddressUser = promptInputAddress();
						
					if(regUser != null && regAddressUser != null) {
						login = regUser;
						regUser.setUnitNumber(regAddressUser.getUnitNumber());
						regUser.setStreetName(regAddressUser.getStreetName());
						regUser.setArea(regAddressUser.getArea());
						regUser.setDistrict(regAddressUser.getDistrict());
						regUser.setPostalCode(regAddressUser.getPostalCode());
						regUser.setState(regAddressUser.getState());
						System.out.println("<<Confirmation>>");
						System.out.println("Member Details: " + regUser.getName() +" ("+ regUser.getPhoneNumber() +") ");
						System.out.println("Delivery Address: " + regAddressUser.getAddressToString() +", "+ convertAddress(regUser.getState()));
						System.out.println("\n<<Succesfully registered!>>");
						status = 1;
					}
					else {
						status = 0;
					}
					break;
					
				case 4:
					System.out.println("\nThank you & have a nice day!");
					System.exit(0);
					break;
				}	
		}while(status != 1);
		return login;
	}
	
	/* Method B2 : Sub Menu
	*****************************************/
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

		switch(choice) {
			case 1: 
				placeOrder(loginValid);
				break;
			case 2: 
				trackOrder(loginValid);
				break;
			case 3: 
				choice = 3;
				break;
		}
		return choice;
	}
	
	/* Method B3 : Method to check member login
	*****************************************/
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
	
	/* Method B4 : Method to validate member login
	*****************************************/
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
	
    /* Method B5 : Method to get input details (guest/registering user)
	*****************************************/
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
    
    /* Method B6 : Method to get input address (guest/registering user)
	*****************************************/
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
    
    /* Method B7 : Method to validate address
   	*****************************************/
    public int validateAddress(int state) {
    	if(state == 4) 
    		return 1; // Melaka
    	else if(state >= 1 && state <= 13 && state != 4) 
    		return 0; // All other states 
    	else
    		return -1; // Input validation
    }
    
    /* Method B8 : Method to convert address from raw data in txt file
   	*****************************************/
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
    
    /* Method B9 : Method to convert area from raw data in user input
   	*****************************************/
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
    
    /* =======================================
	 * ============== SECTION C ==============
	 * ======================================= */
    
    /* Method C : Method to initiate Order Module
   	*****************************************/
    public void placeOrder(Member loginValid) {
    	displayItemList(loginValid);
		Order order = promptInputOrderItems(loginValid);
		String dumb = makePayment(loginValid, order); //why dumb?
		
    }
    
    /* Method C1 : Method to Display all items
   	*****************************************/
    public void displayItemList(Member loginValid) {
    	List<Item> items = control.getAllItems();
    	int index = 1;
    	System.out.println("\n<<Place Order>>");
		System.out.println("Item\tItem\t\t\t\t Item\t Price\t  Promotional\tPromotional");
		System.out.println(" ID\tName\t\t\t\t Type\t (RM)\t  Item\t\tPrice (RM)");
		System.out.println("----\t-------------------------------\t ------\t -------  -----------\t-----------");
		for (Item tempItem: items) {
			
			if(index <= 9)
				System.out.printf(" 0%-5d %-32s %-8s", index++, tempItem.getItemName(), tempItem.getItemType());
			else
				System.out.printf(" %-6d %-32s %-8s", index++, tempItem.getItemName(), tempItem.getItemType());
		
			if (loginValid.getFlag() == true)
				System.out.printf(" %-13.2f", tempItem.getMemberPrice());
			else
				System.out.printf(" %-13.2f", tempItem.getNonMemberPrice());
			
			System.out.printf("%-10s", tempItem.getPromotionalItem());
			if(tempItem.getPromotionalItem() == 'Y') {
				double promotionalPrice = calculatePromotionalPrice(tempItem, loginValid);
				System.out.printf("%-15.2f \n", promotionalPrice);
			}
			else
				System.out.printf("%-15s \n","N/A");
		}
    }
    
    /* Method C2 : Method to compute Item promotional price
   	*****************************************/
    public double calculatePromotionalPrice(Item item, Member member) {
		final double DISCOUNT_RATE = 0.95;
		double promotionalPrice = 0;
		if (member.getFlag() == true)
			promotionalPrice = item.getMemberPrice() * DISCOUNT_RATE;
		else 
			promotionalPrice = item.getNonMemberPrice() * DISCOUNT_RATE;
		return promotionalPrice;
	}
    
/* Method C3 : Method to get input on Items Selected (Place order)
   	*****************************************/
    public Order promptInputOrderItems(Member loginValid) {
    	List<Item> items = control.getAllItems();
    	int doWhile1 = -1, itemID = 0, choice = 0, innerDoWhile3 = -1, itemQuantity = 0;
    	Order order = new Order(); 
    	double itemTotal = 0, deliveryCharge = 0, additionalCharge = 0, totalPrice = 0;
    	int inputQuantity=0;
    	
    	do { 
    		itemID = promptInputItem();
    		inputQuantity = promptInputQuantity(itemID);
    		
    		itemQuantity= checkItemQuantity(order.getTotalNumOfItems(),inputQuantity);
    		
    		
    		if(itemQuantity != 0) {
    			int count = 0;
        		for (Item tempItem: items) {
            		++count;
            		if(count == itemID) {
            			itemTotal += calculateItemPrice(tempItem, itemQuantity, loginValid);
                    	order.addItemToOrder(String.valueOf(itemID), String.valueOf(itemQuantity));
            			break;
            		}
        		}
    		} 
        	do { //innerDoWhile3

        		System.out.println("\n<<Item Summary>>");
            	System.out.println("<<Items in current order: "+ order.getTotalNumOfItems() +">>");
            	System.out.println("1. Add more items");
            	System.out.println("2. Proceed to payment");
            	System.out.print(">>Choice: ");
            	try {
            		choice = intInputValidation(1, 2);
            		innerDoWhile3 = 1;} 
            	catch (IllegalArgumentException e) {
            		System.err.println(e.getMessage());
            		bufferFor5Miliseconds();
            		innerDoWhile3 = 0;}
            		
            	if(choice == 1)
            		doWhile1 = 0;
            	else if(choice == 2)
            		doWhile1 = 1;
        	}while(innerDoWhile3 != 1);	
    	}while(doWhile1 != 1);
    		
    	deliveryCharge = calculateDeliveryCharge(loginValid.getArea());
    	
    	additionalCharge = calculateAdditionalCharge(itemTotal);
    	
    	totalPrice = calculateGrandTotal(itemTotal, deliveryCharge, additionalCharge);
    		
    	order.setOrderItemsSubTotal(itemTotal);
    	order.setOrderDeliveryCharges(deliveryCharge);
    	order.setOrderAdditionalCharges(additionalCharge);
    	order.setOrderTotalPrice(totalPrice);
    	control.addOrders(order);
    		
    	System.out.println("\n<<Order Summary>>");
    	System.out.printf("Sub-Total: %-15.2f", order.getOrderItemsSubTotal());
    	System.out.printf("\nDelivery charges: %-15.2f", order.getOrderDeliveryCharges());
    	System.out.printf("\nAdditional charges: %-6.2f", order.getOrderAdditionalCharges()); 
    	System.out.print("\n---------------------------");
    	System.out.printf("\nGRAND TOTAL: %-15.2f", order.getOrderTotalPrice()); 
    	System.out.print("\n"); 
    	
    	return order;
    }
    
    /* Method C3(A) : Sub-Method to get input on Items Selected (Place order)
   	*****************************************/
	public int promptInputItem() {
		int itemID = 0;
		int doWhile = -1;
		do {
			System.out.println("\n<<Add Items to Order>>");
			System.out.print("Enter Item ID: ");
			try {
				itemID = intInputValidation(1, 20);
				doWhile = 1;
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
				bufferFor5Miliseconds();
				doWhile = 0;
			}
		} while (doWhile != 1);
		return itemID;
	}
    
	 /* Method C3(B) : Sub-Method to get input on Items Quantity (Place order)
   	*****************************************/
    public int promptInputQuantity(int itemID) {
    	List<Item> items = control.getAllItems();
    	int quantity = 0, doWhile = -1;
    	
    	
    	do { // doWhile
    		int count = 0;
        	for (Item tempItem: items) {
        		++count;
        		if(count == itemID) {
        			System.out.print("Enter Quantity ("+tempItem.getItemName()+"): ");
	        		try {
	            		quantity = intInputValidation(1, 20); // Capped at 20
	            		doWhile = 1;} 
	            	catch (IllegalArgumentException e) {
	            		System.err.println(e.getMessage());
	            		bufferFor5Miliseconds();
	            		doWhile = 0;
	            		break;}
        		}
        	}
        	
    	}while(doWhile != 1);
    	
    	return quantity;
    	
    }
    
    /* Method C3(C) : Sub-Method to check whether item quantity is > max items allowed (20)-> Assumption #4
   	*****************************************/
    public int checkItemQuantity(int getTotalNumOfItems,int quantity) {
    	final int maxItemQty=20;
    	int totalNumOfItemsInOrder=getTotalNumOfItems;
    	totalNumOfItemsInOrder += quantity;
    	
    	if(totalNumOfItemsInOrder>maxItemQty) {
			System.out.println("<<Total items per order is capped at 20!>>");
			System.out.println("\n<<Item Not Added>>");
			quantity = 0;
    	}
    	return quantity;
    }
    
    /* Method C4 : Method to Compute Delivery Charges
   	*****************************************/
    public double calculateDeliveryCharge(String area) {
    	List<Delivery> delivery = control.getAllDeliveryCharges();
    	double deliveryRate = 0;
    	
    	for (Delivery tempDelivery : delivery) {
    		if(area.equals(tempDelivery.getAreaCode()))
    			deliveryRate = tempDelivery.getDeliveryCharges();
    	}
    	if(deliveryRate == 0)
    		throw new IllegalArgumentException("The code is available and the deliveryRate is not 0");
    	
    	return deliveryRate;
    }
    
    /* Method C5 : Method to compute subTotal of an (item x quantity) + with/without promotion
   	*****************************************/
    public double calculateItemPrice(Item item, int quantity, Member member) {

		double price = 0, subTotal = 0;
		
		if(item.getPromotionalItem()=='Y') 
			price=calculatePromotionalPrice(item,member); //straight away call method C2
		else
			if (member.getFlag() == true)
				price = item.getMemberPrice();
			else 
				price = item.getNonMemberPrice();

		subTotal = price * quantity;
		return subTotal;
	}
    
    /* Method C6 : Method to compute additional charge
   	*****************************************/
    public double calculateAdditionalCharge(double itemTotal) {
    	double additionalCharge = 0;
    	if(itemTotal > 1 && itemTotal < 25)
    		additionalCharge = 3;
    	else if(itemTotal >= 25)
    		additionalCharge = 0;
    	else
    		throw new IllegalArgumentException("Item cannot be 0 or lesser");
    	return additionalCharge;
    }
    
    /* Method C7 : Method to compute grand total
   	*****************************************/
    public double calculateGrandTotal(double itemTotal, double deliveryCharge, double additionalCharge) {
    	double grandTotal = 0; 
    	grandTotal = itemTotal + deliveryCharge + additionalCharge;
    	if(grandTotal<0) {
    		throw new IllegalArgumentException("Invalid Grand Total");
    	}
    	return grandTotal;
    }
    
    /* =======================================
   	 * ============== SECTION D ==============
   	 * ======================================= */
    
    /* Method D : Method to initiate payment
	*****************************************/
    public String makePayment(Member loginValid, Order order) {
    	String paymentStatus;

    	int doWhile1 = -1, doWhile2 = -1, choice = -1;
    		
    	do { //doWhile1
    			//paymentStatus = promptInputMakePayment(loginValid, order);
    			choice=promptInputMakePayment(loginValid, order);
    			paymentStatus = makingPayment(choice, order.getOrderTotalPrice(), new Payment());
    			do { //doWhile2
	    	    	System.out.println("\n<<Payment Response>>");
	    	    	if(String.valueOf(paymentStatus).equals("Succesful")) {
	    	    		order.setOrderStatus(true);
	    	    		System.out.println("Paid & Ready for Delivery");
	    	    	}
	    	    	
	    			if(paymentStatus == "Fail") {
	    				order.setOrderStatus(false);
	    				
	    				System.out.println("Pending for Payment\n");
	        			System.out.println("1. Try another payment method");
	        	        System.out.println("2. Proceed without payment");
	        	        System.out.print(">>Choice: ");
	        	        try {
	            	    	choice = intInputValidation(1, 2);
	            	    	doWhile2 = 1;}
	            	    catch (IllegalArgumentException e) {
	            	    	System.err.println(e.getMessage());
	            	    	bufferFor5Miliseconds();
	            	    	doWhile2 = 0;}
	    			}
    			}while(doWhile2 != 1);
    			
    			if(choice == 1)
    				doWhile1 = 0;
    			else if(choice == 2)
    				return null;
    			else 
    				doWhile1 = 0;
    		}while(doWhile1 != -1);
    	
    	return paymentStatus;
    }
     
    /* Method D1 : Method to get input on Payment details (Make Payment)
   	*****************************************/
    public int promptInputMakePayment(Member loginValid, Order order) {
    	int choice = -1, doWhile1 = -1;
    	String paymentStatus;
    	
    	do { //doWhile1
    		System.out.println("\n<<Payment>>");
        	System.out.println("1. Online Banking");
        	System.out.println("2. Credit Card");
        	System.out.print(">>Choice: ");
        	try {
        		choice = intInputValidation(1, 2);
        		doWhile1 = 1;}
        	catch (IllegalArgumentException e) {
        		System.err.println(e.getMessage());
        		bufferFor5Miliseconds();
        		doWhile1 = 0;}
    	}while(doWhile1 != 1);
    	return choice;
    	
    }
    
    /* Method D2 : Method to process Payment details
   	*****************************************/
    public String makingPayment(int choice, double price, Payment payment) {
    	String status = "Fail";
    	switch(choice) {
		case 1: 
			status = payment.makingPayment("Online Banking", status, price);
			break;
		case 2:
			status = payment.makingPayment("Credit Card", status, price);
			break;
		default:
			throw new IllegalArgumentException("Invalid Payment Choice");
    	}
    	return status;
    }
    
    /* =======================================
	 * ============== SECTION E ==============
	 * ======================================= */
    
    /* Method E : Method Method to initiate Track Order
   	*****************************************/
    public void trackOrder(Member loginValid) {
    	displayAllOrders(loginValid);
    }
    
    /* Method E1 : Method Method to initiate Track Order
   	*****************************************/
    public void displayAllOrders(Member loginValid) {
    	List<Order> order = control.getAllOrders();
    	List<Item> item = control.getAllItems();
    	
    	System.out.println("\n<<Track Orders>>");
    	
    	if(order.size() == 0 )
    		System.out.println("<<No orders found>>");
    	else {
    	System.out.println("<<Customer Details>>");
		System.out.println("Name: " + loginValid.getName());
		if(loginValid.getFlag() == true)
			System.out.println("Membership: " + "Member");
		else
			System.out.println("Membership: " + "Guest");
		System.out.println("Phone Number: " + loginValid.getPhoneNumber());
		System.out.println("Delivery Address: " + loginValid.getAddressToString() +", "+ convertAddress(loginValid.getState()));
		
		// ------------------------------------------- Part 1
		System.out.println("\n<<List of Orders>>");
		System.out.println("=================================================");
		System.out.println(" ID\tGrand Total\tOrder Status");
		System.out.println("----\t-----------\t------------");
			
		for (Order tempOrder: order) {
			if(tempOrder.getOrderID() <= 9)
				System.out.print(" 0");
			String orderStatus = null;
			if(tempOrder.getOrderStatus() == true)
				orderStatus = "Paid & Ready for Delivery";
			else
				orderStatus = "Pending for Payment";
			String formatPrice = String.format("%.2f", tempOrder.getOrderTotalPrice());
			System.out.println(tempOrder.getOrderID() +"\tRM "+ formatPrice +"\t"+ orderStatus);
		}
 		System.out.println("=================================================");
		
 		// ------------------------------------------- Part 2
 		System.out.println("\n<<List of Orders (Detailed)>>");
 		System.out.println("===================================================================================");
 		for (Order tempOrder: order) {  // loop for (number of orders) in Order
 			if(tempOrder.getOrderID() <= 9)
				System.out.print("Order ID: 0");
 			else
 				System.out.print("Order ID: ");
 			System.out.println(tempOrder.getOrderID() + "\n"); // get OrderID
 			System.out.println("Item\tItem\t\t\t\t Item\t Price\t  Quantity\tPrice");
 	 		System.out.println(" ID\tName\t\t\t\t Type\t (RM)\t  per Item\t(RM)");
 	 		System.out.println("----\t-------------------------------\t ------\t -------  -----------\t-----------");
 			
 			for(int i = 0; i < tempOrder.getOrderItemsID().size(); i++) {	// loop for (number of items) in 1 Order
 				
 				int itemID = tempOrder.getItemIDAt(i); // get n = first item's ID, where n = 1,2,3...(number of items)
 				int iterationForItem = 0; // iteration to determine which item in Item List
 				for (Item tempItem: item) {
 					++iterationForItem;
 					if(itemID == iterationForItem) { // item found 
 						// Display itemID, itemName, itemType
 	 		 			System.out.printf(" 0%-5d %-32s %-8s", iterationForItem, tempItem.getItemName(), tempItem.getItemType());
 	 		 						
 	 		 			// Display itemPrice
 	 		 			if (tempItem.getPromotionalItem() == 'Y')
 	 		 				System.out.printf("%-10.2f", calculatePromotionalPrice(tempItem, loginValid));
 	 		 			else
 	 		 				if (loginValid.getFlag() == true)
 	 		 					System.out.printf("%-10.2f", tempItem.getMemberPrice());
 	 		 				else
 	 		 					System.out.printf("%-10.2f", tempItem.getNonMemberPrice());
 	 		 			
 	 		 			// Display itemQuantity
 	 		 			System.out.printf("%-13d", tempOrder.getItemQuantityAt(i));	
 	 		 			
 	 		 			// Display itemSubTotal
 	 		 			System.out.printf("%-15.2f \n", calculateItemPrice(tempItem,tempOrder.getItemQuantityAt(i),loginValid)); // delete this
 	 		 			break;
 					}
 				}
	 		}
 				
 			System.out.println("----\t-------------------------------\t ------\t -------  -----------\t-----------");
 			System.out.print("\n\t\t\t\t\t\t\t  Sub-Total:\t");
 			System.out.printf("%-8.2f", tempOrder.getOrderItemsSubTotal());
 			System.out.print("\n\t\t\t\t\t\t\t  (+ Charges) \t");
 			System.out.print("\n\t\t\t\t\t\t\t  Delivery: \t");
 			System.out.printf("%-8.2f", tempOrder.getOrderDeliveryCharges());
 			System.out.print("\n\t\t\t\t\t\t\t  Additional: \t");
 			System.out.printf("%-8.2f", tempOrder.getOrderAdditionalCharges());
 			System.out.print("\n\t\t\t\t\t\t\t  -------------------------");
 			System.out.print("\n\t\t\t\t\t\t\t  Grand Total:\t");
 			System.out.printf("%-8.2f", tempOrder.getOrderTotalPrice());
 			System.out.println("\n===================================================================================");
    	}
    	
    	}
    }
    
    /* =======================================
	 * ============== SECTION F ==============
	 * ======================================= */
    
    /* Method F1 : Method for integer input validation
	*****************************************/
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
    
    /* Method F2 : Method for string input validation
	*****************************************/
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
    
    /* Method F3 : Pause execution for 5 seconds
	*****************************************/
    public void bufferFor5Miliseconds() {
    	try {
			TimeUnit.MILLISECONDS.sleep(500);}
		catch (InterruptedException e1) {
			System.err.print(e1.getMessage());}
    }
}
