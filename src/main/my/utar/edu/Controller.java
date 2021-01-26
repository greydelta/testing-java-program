package my.utar.edu;
import java.util.*;

public class Controller {

	private IDataStore dataLists;
	
	// Accessor Methods
	public Controller(IDataStore dataLists) {
		this.dataLists = dataLists;
	}

	public List<Member> getAllMembers(){
		return dataLists.getAllMembers();
	}
	
	public List<Item> getAllItems(){
		return dataLists.getAllItems();
	}
	
	public List<Delivery> getAllDeliveryCharges(){
		return dataLists.getAllDeliveryCharges();
	}
	
	public List<Order> getAllOrders(){
		return dataLists.getAllOrders();	
	}
	
}
