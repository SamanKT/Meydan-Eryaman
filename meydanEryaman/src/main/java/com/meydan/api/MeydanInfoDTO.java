package com.meydan.api;

public class MeydanInfoDTO {
	
	
	private Long tel;
	private String block;
	private int no;
	private boolean carOwn;
	private int capacity;
	private String name;
	private String lastName;
	
	
	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public boolean isCarOwn() {
		return carOwn;
	}

	public void setCarOwn(boolean carOwn) {
		this.carOwn = carOwn;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getTel() {
		return tel;
	}

	public void setTel(Long tel) {
		this.tel = tel;
	}


	
	
}
