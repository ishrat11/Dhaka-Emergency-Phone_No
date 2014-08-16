package com.example.dhakaemergencyphone_no;

public class RowItem {

	private int image;
	private String title;
	private String police_no;
	public RowItem(int image, String title, String police_no) {
		//super();
		this.image = image;
		this.title = title;
		this.police_no = police_no;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPolice_no() {
		return police_no;
	}
	public void setPolice_no(String police_no) {
		this.police_no = police_no;
	}
	
	
}
