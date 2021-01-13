package my.utar.edu;
public class Item {
	
	private String itemName;
	private String itemType;
	private double memberPrice;
	private double nonMemberPrice ;
	private char promotionalItem ;

	public Item() {
		this.itemName = "";
		this.itemType = "";
		this.memberPrice = 0;
		this.nonMemberPrice = 0;
		this.promotionalItem = '\u0000';
	}

	public Item(String itemName, String itemType, double memberPrice, double nonMemberPrice, char promotionalItem) {
		this.setItemName(itemName);
		this.setItemType(itemType);
		this.setMemberPrice(memberPrice);
		this.setNonMemberPrice(nonMemberPrice);
		this.setPromotionalItem(promotionalItem);
	}

	public Item(Item item) {
		this.setItemName(item.getItemName());
		this.setItemType(item.getItemType());
		this.setMemberPrice(memberPrice);
		this.setNonMemberPrice(nonMemberPrice);
		this.setPromotionalItem(promotionalItem);
	}
	
	public String getItemName() {
		return itemName;}

	public void setItemName(String itemName) {
		this.itemName = itemName;}

	public String getItemType() {
		return itemType;}

	public void setItemType(String itemType) {
		this.itemType = itemType;}

	public double getMemberPrice() {
		return memberPrice;}

	public void setMemberPrice(double memberPrice) {
		this.memberPrice = memberPrice;}

	public double getNonMemberPrice() {
		return nonMemberPrice;}

	public void setNonMemberPrice(double nonMemberPrice) {
		this.nonMemberPrice = nonMemberPrice;}

	public char getPromotionalItem() {
		return promotionalItem;}

	public void setPromotionalItem(char promotionalItem) {
		this.promotionalItem = promotionalItem;}
}
