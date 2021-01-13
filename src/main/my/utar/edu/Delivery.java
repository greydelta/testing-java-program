package my.utar.edu;
public class Delivery {

	private String areaCode;
	private String area;
	private double charges;
	
	public Delivery(){
		this.areaCode = "";
		this.area = "";
		this.charges = 0;
	}
	
	public Delivery(String areaCode, String area, double charges) {
		this.areaCode = areaCode;
		this.area = area;
		this.charges = charges;
	}
	
	public String getAreaCode() {
		return areaCode;}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;}
	
	public String getDeliveryArea() {
		return this.area;}
	
	public void getDeliveryArea(String area) {
		this.area = area;}
	
	public double getDeliveryCharges() {
		return this.charges;}
	
	public void setDeliveryCharges(double charges) {
		this.charges = charges;}
}
