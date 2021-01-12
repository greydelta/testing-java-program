package my.utar.edu;
public class Member extends Address{ 

	private String Name, Password, PhoneNumber;
	private boolean Flag;
	
	public Member(){
		this.Name = "";
		this.Password = "";
		this.PhoneNumber = "";
		this.setFlag(false);
	}
	
	public Member(String Name, String Password, String Number, boolean Flag){
		this.Name = Name;
		this.Password = Password;
		this.PhoneNumber = Number;
		this.Flag = Flag;
	}
	
	public Member(String Name, String Number, boolean Flag){
		this.Name = Name;
		this.Password = "";
		this.PhoneNumber = Number;
		this.Flag = Flag;
	}
	
	public void setName(String Name){
		this.Name = Name;}
	
	public String getName(){
		return this.Name;}

	public void setPassword(String Password) {
		this.Password = Password;}
	
	public String getPassword() {
		return this.Password;}
	
	public void setPhoneNumber(String Number){
		this.PhoneNumber = Number;}
	
	public String getPhoneNumber(){
		return this.PhoneNumber;}

	public void setFlag(boolean flag) {
		this.Flag = flag;}
	
	public boolean getFlag() {
		return Flag;}
}
