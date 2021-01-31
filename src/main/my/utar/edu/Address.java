package my.utar.edu;
public class Address{

	private String UnitNumber, StreetName, Area, District, PostalCode;
	private int State;
	
	public Address()
	{
		this.UnitNumber = "";
		this.StreetName = "";
		this.Area = "";
		this.District = "";
		this.PostalCode = "";
		this.State = 0;
	}
	
	public Address (String UnitNumber, String StreetName, String Area, String District, String PostalCode, int State)
	{
		this.UnitNumber = UnitNumber;
		this.StreetName = StreetName;
		this.Area = Area;
		this.District = District;
		this.PostalCode = PostalCode;
		this.State = State;
	}
	
	public void setUnitNumber(String UnitNumber) {
		this.UnitNumber = UnitNumber;}
	
	public String getUnitNumber() {
		return this.UnitNumber;}
	
	public void setStreetName(String StreetName) {
		this.StreetName = StreetName;}
	
	public String getStreetName() {
		return this.StreetName;}
	
	public void setArea(String Area) {
		this.Area = Area;}
	
	public String getArea() {
		return this.Area;}
	
	public void setDistrict(String District) {
		this.District = District;}
	
	public String getDistrict() {
		return this.District;}
	
	public void setPostalCode(String PostalCode) {
		this.PostalCode = PostalCode;}
	
	public String getPostalCode() {
		return this.PostalCode;}
	
	public void setState(int State) {
		this.State = State;}
	
	public int getState() {
		return this.State;}
	
	public String getAddressToString() {
		String result = 
				getUnitNumber() + ", " + 
				getStreetName() + ", " +
				getArea() + ", " + 
				getDistrict() + ", " +
				getPostalCode(); 
		return result;}
}
