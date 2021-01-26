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
	}
}
