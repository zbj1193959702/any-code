package com.hainiu.cat.service.enums;

public enum HouseTypeEnum {
	er_shou_fang(1, "二手房"),
	new_house(3, "新房"),
	;


	public final Integer value;
	public final String name;

	public int getValue() {
		return this.value;
	}

	public String getName() {
		return this.name;
	}

	private HouseTypeEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}
}
