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
		
		
		return loginValid;
	}
	
}
