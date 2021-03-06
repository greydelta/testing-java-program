package my.utar.edu;
import java.util.List;

public interface IDataStore {

	public List<Member> getAllMembers();
	public List<Item> getAllItems();
	public List<Delivery> getAllDeliveryCharges();
	public List<Order> getAllOrders();
	
	public void addMember(Member member);
	public void addItem(Item item);
	public void addDeliveryCharges(Delivery deliveryCharges);
	public void addOrders(Order order);
}
