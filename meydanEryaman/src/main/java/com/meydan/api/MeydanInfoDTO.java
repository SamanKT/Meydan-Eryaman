package com.meydan.api;

import java.util.Objects;

public class MeydanInfoDTO {
	
	
	private Long tel;
	private String block;
	private int no;
	private boolean carOwn;
	private int capacity;
	private String name;
	private String lastName;
	private boolean participate;
	private boolean haveRepresentation;
	private boolean wishRepresented;
	
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

	@Override
	public int hashCode() {
		return Objects.hash(tel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeydanInfoDTO other = (MeydanInfoDTO) obj;
		return Objects.equals(tel, other.tel);
	}

	public boolean isParticipate() {
		return participate;
	}

	public void setParticipate(boolean participate) {
		this.participate = participate;
	}

	public boolean isHaveRepresentation() {
		return haveRepresentation;
	}

	public void setHaveRepresentation(boolean haveRepresentation) {
		this.haveRepresentation = haveRepresentation;
	}

	public boolean isWishRepresented() {
		return wishRepresented;
	}

	public void setWishRepresented(boolean wishRepresented) {
		this.wishRepresented = wishRepresented;
	}



	
	
	
}
