package com.course.practicaljava.rest.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy.class)
public class Car {

	private String brand;
	private String color;
	private String type;
	private int price;
	private boolean available;

	@JsonFormat(pattern = "dd-MMM-yyyy hh:mm:ssaZ", timezone = "Asia/Jakarta")
	private Date firstReleaseDate;

	@JsonInclude(value = Include.NON_EMPTY)
	private List<String> additionalFeatures;

	@JsonUnwrapped
	private Engine engine;

	private List<Tire> compatibleTires;

	@JsonInclude(value = Include.NON_EMPTY)
	private String secretFeature;

	public Car() {

	}

	public Car(String brand, String color, String type) {
		super();
		this.brand = brand;
		this.color = color;
		this.type = type;
	}

	public List<String> getAdditionalFeatures() {
		return additionalFeatures;
	}

	public String getBrand() {
		return brand;
	}

	public String getColor() {
		return color;
	}

	public List<Tire> getCompatibleTires() {
		return compatibleTires;
	}

	public Engine getEngine() {
		return engine;
	}

	public Date getFirstReleaseDate() {
		return firstReleaseDate;
	}

	public int getPrice() {
		return price;
	}

	public String getSecretFeature() {
		return secretFeature;
	}

	public String getType() {
		return type;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAdditionalFeatures(List<String> additionalFeatures) {
		this.additionalFeatures = additionalFeatures;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setCompatibleTires(List<Tire> compatibleTires) {
		this.compatibleTires = compatibleTires;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public void setFirstReleaseDate(Date firstReleaseDate) {
		this.firstReleaseDate = firstReleaseDate;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setSecretFeature(String secretFeature) {
		this.secretFeature = secretFeature;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", color=" + color + ", type=" + type + ", price=" + price + ", available="
				+ available + ", firstReleaseDate=" + firstReleaseDate + ", additionalFeatures=" + additionalFeatures
				+ ", engine=" + engine + ", compatibleTires=" + compatibleTires + ", secretFeature=" + secretFeature
				+ "]";
	}

}
