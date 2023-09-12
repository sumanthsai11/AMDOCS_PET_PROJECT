package com.amdocs.PetSearch.model;

public class Pet {

    private int petId;
    private String petCategory;
    private String petType;
    private String color;
    private int age;
    private float price;
    private boolean isVaccinated;
    private String foodHabits;
    public Pet(int petId, String petCategory, String petType, String color, int age, float price, boolean isVaccinated, String foodHabits) {
        this.petId = petId;
        this.petCategory = petCategory;
        this.petType = petType;
        this.color = color;
        this.age = age;
        this.price = price;
        this.isVaccinated = isVaccinated;
        this.foodHabits = foodHabits;
    }
    // Getters and setters for each property
    public int getPetId() {
        return petId;
    }
    public void setPetId(int petId) {
        this.petId = petId;
    }
    public String getPetCategory() {
        return petCategory;
    }
    public void setPetCategory(String petCategory) {
        this.petCategory = petCategory;
    }
    public String getPetType() {
        return petType;
    }
    public void setPetType(String petType) {
        this.petType = petType;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public boolean isVaccinated() {
        return isVaccinated;
    }
    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }
    public String getFoodHabits() {
        return foodHabits;
    }
    public void setFoodHabits(String foodHabits) {
        this.foodHabits = foodHabits;
    }
    @Override
    public String toString() {
    return "Pet: \n[petId=" + petId + ", petCategory=" + petCategory + ", petType=" + petType
			+ ", color=" + color + ", age=" + age + ", price=" + price + ", isVaccinated=" + isVaccinated + ", foodHabits="
			+ foodHabits + "]";
    }
}







    