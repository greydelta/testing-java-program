package my.utar.edu;
import java.util.*;

public class Order {

	private int orderID;
	private List<String> orderItemsID; // All itemID in this Order
	private List<String> orderItemsQuantity; // All item's quantity in this Order
	private double orderItemsSubTotal; // SubTotal += (all items in order *  each respective quantity)
	// note: promotional item, member/non-member price is calculated in method
	private double orderDeliveryCharges; // Based on user address area
	private double orderAdditionalCharges; // Add RM3 if orderTotalPrice < RM25
	private double orderTotalPrice; // Grand Total = SubTotal + Delivery + Additional
	private boolean orderStatus; 
	
	public Order() {
		this.setOrderID(0);
		this.orderItemsID = new ArrayList<>();
		this.orderItemsQuantity = new ArrayList<>();
		this.setOrderItemsSubTotal(0);
		this.setOrderDeliveryCharges(0);
		this.setOrderAdditionalCharges(0);
		this.setOrderTotalPrice(0);
	}
	
	public Order(Order orderRaw) {
		this.orderID = orderRaw.getOrderID();
		this.orderItemsID = orderRaw.getOrderItemsID();
		this.orderItemsQuantity = orderRaw.getOrderItemsQuantity();
		this.orderItemsSubTotal = orderRaw.getOrderItemsSubTotal();
		this.orderDeliveryCharges = orderRaw.getOrderDeliveryCharges();
		this.orderAdditionalCharges = orderRaw.getOrderAdditionalCharges();
		this.orderTotalPrice = orderRaw.getOrderTotalPrice();
		this.orderStatus = false;
	}
	
	public Order(int orderID, List<String> orderItemsID, List<String> orderItemsQuantity,
		double orderItemsSubTotal, double orderCharges, double orderAdditionalCharges, 
		double orderTotalPrice, boolean orderStatus) {
		this.setOrderID(orderID);
		this.setOrderItemsID(orderItemsID);
		this.setOrderItemsQuantity(orderItemsQuantity);
		this.setOrderItemsSubTotal(orderItemsSubTotal);
		this.setOrderDeliveryCharges(orderCharges);
		this.setOrderAdditionalCharges(orderAdditionalCharges);
		this.setOrderTotalPrice(orderTotalPrice);
		this.setOrderStatus(orderStatus);
	}

	public int getOrderID() {
		return orderID;}

	public void setOrderID(int orderID) {
		this.orderID = orderID;}

	public List<String> getOrderItemsID() {
		return orderItemsID;}

	public void setOrderItemsID(List<String> orderItemsID) {
		this.orderItemsID = orderItemsID;}

	public List<String> getOrderItemsQuantity() {
		return orderItemsQuantity;}

	public void setOrderItemsQuantity(List<String> orderItemsQuantity) {
		this.orderItemsQuantity = orderItemsQuantity;}

	public double getOrderItemsSubTotal() {
		return orderItemsSubTotal;}

	public void setOrderItemsSubTotal(double orderItemsSubTotal) {
		this.orderItemsSubTotal = orderItemsSubTotal;}

	public double getOrderDeliveryCharges() {
		return orderDeliveryCharges;}

	public void setOrderDeliveryCharges(double orderDeliveryCharges) {
		this.orderDeliveryCharges = orderDeliveryCharges;}

	public double getOrderAdditionalCharges() {
		return orderAdditionalCharges;}

	public void setOrderAdditionalCharges(double orderAdditionalCharges) {
		this.orderAdditionalCharges = orderAdditionalCharges;}

	public double getOrderTotalPrice() {
		return orderTotalPrice;}

	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;}
	
	public boolean getOrderStatus() {
		return orderStatus;}

	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;}

	// Method to add itemID and quantity to this Order
	public void addItemToOrder(String itemID, String itemQuantity) {
		this.orderItemsID.add(itemID);
		this.orderItemsQuantity.add(itemQuantity);
	}
	
	// Method to get total number of items in this order
	public int getTotalNumOfItems() {
		int totalItems = 0;
		for (int i=0; i<orderItemsQuantity.size(); i++)
			totalItems += Integer.parseInt(orderItemsQuantity.get(i));
		return totalItems;
	}
	
	// Method to get (n) item's itemID, where n = 0,1,2...
	public int getItemIDAt(int iteration) {
		int count = -1, result = 0;
		for (String tempItem : orderItemsID){
			count++;
			if(count == iteration) {
				result = Integer.parseInt(tempItem);
				break;
			}
		}
		return result;
	}
	
	// Method to get (n) item's quantity, where n = 0,1,2...
	public int getItemQuantityAt(int iteration) {
		int count = -1, result = 0;
		for (String tempItem : orderItemsQuantity){
			count++;
			if(count == iteration) {
				result = Integer.parseInt(tempItem);
			}
		}
		return result;
	}
}
