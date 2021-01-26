package my.utar.edu;
import java.util.*;

public class DataLists implements IDataStore{

	// Lists to store data
	private List<Member> member;
	private List<Item> item;
	private List<Delivery> deliveryCharges;
	private List<Order> orders;
	
	// Constructor
	public DataLists() {
		member = new ArrayList<>();
		item = new ArrayList<>();
		deliveryCharges = new ArrayList<>();
		orders = new ArrayList<>();
	}
	
	// Accessor methods
	public List<Member> getAllMembers(){
		return member;
	}
	public List<Item> getAllItems(){
		return item;
	}
	public List<Delivery> getAllDeliveryCharges(){
		return deliveryCharges;
	}
	public List<Order> getAllOrders(){
		return orders;
	}
	
}
