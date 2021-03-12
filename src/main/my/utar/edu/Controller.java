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
	
	// Methods
	public void addMember(String name, String password, String number, boolean flag,
			String unit, String street, String area, String district, 
			String postal, int state) {
		Member member = new Member(name, password, number, flag);
		member.setUnitNumber(unit);
		member.setStreetName(street);
		member.setArea(area);
		member.setDistrict(district);
		member.setPostalCode(postal);
		member.setState(state);
		dataLists.addMember(member);
	}
	
	public void addItem(String itemName, String itemType, double memberPrice, double nonMemberPrice,
			char promotionalItem) {
		Item item = new Item(itemName, itemType, memberPrice, nonMemberPrice, promotionalItem);
		dataLists.addItem(item);
	}
	
	public void addDeliveryCharges(String areaCode, String area, double charges) {
		Delivery deliveryCharges = new Delivery(areaCode, area, charges);
		dataLists.addDeliveryCharges(deliveryCharges);
	}
	
	public void addOrders(Order orderRaw) {
		Order order = new Order(orderRaw);
		int tempSize = getAllOrders().size(), size = 0;
		if(tempSize == 0)
			size = 1;
		else 
			size = tempSize + 1;
		order.setOrderID(size);
		dataLists.addOrders(order);
	}
}
