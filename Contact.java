
public class Contact {
	
	private String name;
	private String address;
	private String phone_number;
	public Contact(String name,String address,String phone_number) {
		this.name = name;
		this.address = address;
		this.phone_number = phone_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	@Override
	public String toString() {
		String s = String.format("%-30s%-40s%-20s", name, address,phone_number);
		return s;
	}
}