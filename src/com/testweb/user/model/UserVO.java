package com.testweb.user.model;

public class UserVO {
	private String id;
	private String pw;
	private String pwck;
	private String name;
	private int phone1;
	private int phone2;
	private int phone3;
	private String email;
	private String email2;
	private String addressbasic;
	private String addressdetail;
	
	public UserVO() {

	}
	
	
	public UserVO(String id, String pw, String name, int phone1, int phone2, int phone3, String email,
			String email2, String addressbasic, String addressdetail) {
		super();
		this.id = id;
		this.pw = pw;
		this.pwck = pwck;
		this.name = name;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.email = email;
		this.email2 = email2;
		this.addressbasic = addressbasic;
		this.addressdetail = addressdetail;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPwck() {
		return pwck;
	}
	public void setPwck(String pwck) {
		this.pwck = pwck;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhone1() {
		return phone1;
	}
	public void setPhone1(int phone1) {
		this.phone1 = phone1;
	}
	public int getPhone2() {
		return phone2;
	}
	public void setPhone2(int phone2) {
		this.phone2 = phone2;
	}
	public int getPhone3() {
		return phone3;
	}
	public void setPhone3(int phone3) {
		this.phone3 = phone3;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getAddressbasic() {
		return addressbasic;
	}
	public void setAddressbasic(String addressbasic) {
		this.addressbasic = addressbasic;
	}
	public String getAddressdetail() {
		return addressdetail;
	}
	public void setAddressdetail(String addressdetail) {
		this.addressdetail = addressdetail;
	}
	
	
	

}
