package com.amdocs.PetSearch.dao;
import com.amdocs.PetSearch.exception.*;
import com.amdocs.PetSearch.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class PetDAO {
    private Connection connection;
	public PetDAO() {
	        connection = PetDAOC.getConnection();
	    }
	    public int addpet(Pet pet)throws PetSearchException {
	        String sql = "INSERT INTO PET (petID, petCategory, petType, color, age, price, isVaccinated, foodHabits) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        	statement.setInt(1, pet.getPetId());
	        	statement.setString(2, pet.getPetCategory());
	            statement.setString(3, pet.getPetType());
	            statement.setString(4, pet.getColor());
	            statement.setInt(5, pet.getAge());
	            statement.setDouble(6, pet.getPrice());
	            statement.setBoolean(7, pet.isVaccinated());
	            statement.setString(8, pet.getFoodHabits());
	            int affectedRows = statement.executeUpdate();
	            if (affectedRows == 0) {
	                throw new SQLException("Adding pet failed, no rows affected.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately
	            return -1; // Return a negative value to indicate failure
	        }
			return 0;
	    }
	    public int deletePet(int petId) throws PetSearchException {
	        String sql = "DELETE FROM PET WHERE petID = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, petId);
	            return statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately
	            return -1; // Return a negative value to indicate failure
	        }
	    }
	    public boolean updatePetPV(int petId, double newP, boolean newV) throws PetSearchException {
	        String sql = "UPDATE Pet SET price = ? , isVaccinated = ? WHERE petid = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setDouble(1, newP);
	            statement.setBoolean(2, newV);
	            statement.setInt(3, petId);
	            int affectedRows = statement.executeUpdate();
	            return affectedRows > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("pet is not found");
	            // Handle the exception appropriately
	            return false;
	        }
	    }
	    public List<Pet> showAllPets() throws PetSearchException {
	        String sql = "SELECT * FROM PET";
	        List<Pet> pets = new ArrayList<>();
	        try (Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(sql)) {
	            while (resultSet.next()) {
	                Pet pet = mapResultSetToPet(resultSet);
	                pets.add(pet);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately
	        }
	        return pets;
	    }
	    public List<Pet> searchByPrice(float minPrice, float maxPrice)throws PetSearchException {
	        String sql = "SELECT * FROM Pet WHERE price BETWEEN ? AND ?";
	        List<Pet> pet = new ArrayList<>();
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setFloat(1, minPrice);
	            statement.setFloat(2, maxPrice);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                while (resultSet.next()) {
	                    Pet property = mapResultSetToPet(resultSet);
	                    pet.add(property);
	                }
	                if (pet.isEmpty()) {
		            	throw new  PetSearchException("Property not available");
		            }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately
	            System.out.println("This requirement is not available in the database");
	        }
	        return pet;
	    }
	    public int countPetsByCategory(String petC ) throws PetSearchException {
	        String sql = "SELECT count(petid) FROM PET where petcategory=?";
	        int count = 0;



	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, petC);
	            ResultSet rs = statement.executeQuery();
	            while(rs.next()) {
	            	count= rs.getInt(1);
	            }
	            return count;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately
	        }
			return -1;
	    }
	    public int countByVaccinationStatusForPetType(String petT,Boolean isvaccinated) throws PetSearchException {
	        String sql = "SELECT COUNT(petid) FROM PET where petType=? and isvaccinated=?";
	        int count = 0;
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, petT);
	            statement.setBoolean(2, isvaccinated);
	            ResultSet rs = statement.executeQuery();
	            while(rs.next()) {
	            	count= rs.getInt(1);
	            }
	            return count;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception appropriate
	        }
			return -1;
	    }
	    private Pet mapResultSetToPet(ResultSet resultSet) throws SQLException {
	        int petId = resultSet.getInt("petid");
	        String petCategory = resultSet.getString("petcategory");
	        String petType = resultSet.getString("pettype");
	        String color = resultSet.getString("color");
	        int age = resultSet.getInt("age");
	        float price = resultSet.getFloat("price");
	        boolean isVaccinated = resultSet.getBoolean("isvaccinated");
	        String foodHabits = resultSet.getString("foodhabits");
	        Pet pet = new Pet(petId,petCategory,petType,color,age,price,isVaccinated,foodHabits);
	        return pet;
	    }
	    public void close() {
	    	PetDAOC.closeConnection();
	    }
	}
