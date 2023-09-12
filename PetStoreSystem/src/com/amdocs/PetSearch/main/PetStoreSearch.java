package com.amdocs.PetSearch.main;
import java.util.*;
import com.amdocs.PetSearch.model.*;
import com.amdocs.PetSearch.dao.*;
import com.amdocs.PetSearch.exception.PetSearchException;
    public class PetStoreSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetDAO petDAO = new PetDAO();
        while (true) {
            System.out.println("Enter your choice:");
            System.out.println("1. Add new pet details");
            System.out.println("2. Update pet price and Vaccination Status");
            System.out.println("3. Delete pet details");
            System.out.println("4. View all pets");
            System.out.println("5. Count pets by category");
            System.out.println("6. Find by price");
            System.out.println("7. Find by vaccination status for pet type");
            System.out.println("8. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            // Consume the newline character
            switch (choice) {
                case 1:
                    // Add new pet details
                                                                                                                  System.out.println("Enter pet details:");
                    System.out.print("Pet id: ");
                    int petid = scanner.nextInt();
                    System.out.print("Pet Category: ");
                    String category = scanner.next();
                    System.out.print("Pet Type: ");
                    String type = scanner.next();
                    System.out.print("Color: ");
                    String color = scanner.next();
                    System.out.print("Age: ");
                    int age = scanner.nextInt();
                    System.out.print("Price: ");
                    float price = scanner.nextFloat();
                    System.out.print("Is Vaccinated (true/false): ");
                    boolean isVaccinated = scanner.nextBoolean();
                    System.out.print("Food Habits: ");
                    scanner.nextLine(); // Consume the newline character
                    String foodHabits = scanner.nextLine();
                    Pet newPet = new Pet(petid, category, type, color, age, price, isVaccinated, foodHabits);
				try {
					petid = petDAO.addpet(newPet);
				} catch (PetSearchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    System.out.println("Pet added" );
                    break;
                case 2:
                    // Update pet price and vaccination status
                    System.out.print("Enter pet ID to update: ");
                    int updatePetId = scanner.nextInt();
                    System.out.print("Enter new price: ");
                    double newPrice = scanner.nextDouble();
                    System.out.print("Is Vaccinated (true/false): ");
                    boolean updatedVaccinationStatus = scanner.nextBoolean();
				    boolean updated = false;
				try {
					updated = petDAO.updatePetPV(updatePetId, newPrice, updatedVaccinationStatus);
				} catch (PetSearchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    if (updated) {
                        System.out.println("Pet updated successfully.");
                    } 
                    else {                    
                        System.out.println("Pet not found.");
                    }
                    break;
                    case 3:
                    // Delete pet details
                    System.out.print("Enter pet ID to delete: ");
                    int deletePetId = scanner.nextInt();
				    int deleteResult = 0;
				try {
					deleteResult = petDAO.deletePet(deletePetId);
				} catch (PetSearchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    if (deleteResult == 1) {
                        System.out.println("Pet deleted successfully.");
                    } else {
                        System.out.println("Pet not found.");
                    }
                    break;
                case 4:
                    // View all pets
				try {
					List<Pet>p=petDAO.showAllPets();
					if (!p.isEmpty()) {
						System.out.println("pet found succesfully");
						for (int i=0;i<p.size();i++) {
							System.out.println(p.get(i).toString());
						}
					}
				} catch (PetSearchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                         break;
                case 5:
                    // Count pets by category
                    System.out.print("Enter pet category: ");
                    String countCategory = scanner.next();
				    int petCount = 0;
				try {
					petCount = petDAO.countPetsByCategory(countCategory);
				} catch (PetSearchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    System.out.println("Number of pets in category " + countCategory + ": " + petCount);
                    break;
                case 6:
                    // Find by price
                    System.out.print("Enter minimum price: ");
                    float minPrice = scanner.nextFloat();
                    System.out.print("Enter maximum price: ");
                    float maxPrice = scanner.nextFloat();
				    List<Pet> petsInRange = null;
				try {
					petsInRange = petDAO.searchByPrice(minPrice, maxPrice);
				} catch (PetSearchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    System.out.println("Pets in price range " + minPrice + " - " + maxPrice + ":");
                    for (Pet pet : petsInRange) {
                    System.out.println(pet.getPetId() + ": " + pet.getPetCategory() + " - " + pet.getPetType());
                    }
                    break;
                case 7:
                    // Find by vaccination status for pet type
                    System.out.print("Enter pet type: ");
                    String petType = scanner.next();
                    System.out.print("enter vaccination status");
                    Boolean isVaccinated1 = scanner.nextBoolean();
				int vaccinatedCount = 0;
				try {
					vaccinatedCount = petDAO.countByVaccinationStatusForPetType(petType,isVaccinated1);
				} catch (PetSearchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                    System.out.println("Number of vaccinated " + petType + " pets: " + vaccinatedCount);
                    break;
                case 8:
                    // Exit the program
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

    
    