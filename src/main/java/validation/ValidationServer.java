package validation;


import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter; 
import java.io.File; 
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties; 
 
/**
 * ValidationServer handles the validating of credentials
 * @author Joey Siy, Rosy Ren
 *
 */
public class ValidationServer {
	/**
	 * An ArrayList containing ArrayLists of valid pairs for the purposes of validation
	 */
	private ArrayList<ArrayList<String>> validpairs;
	
	/**
	 * An instance of the validation server, here to employ singleton design pattern
	 */
	private static ValidationServer instance;
	
	/**
	 * a way to invoke the constructor without allowing the client to make more than one validation server
	 * @return the instance of ValidationServer
	 */
	public static ValidationServer getInstance() {
		if (instance==null) {
			instance = new ValidationServer();
		}
		return instance;
	}
	
	/**
	 * Constructor; reads the validation db and constructs 2d matrix with valid pairs
	 */
	private ValidationServer() {
		ArrayList<ArrayList<String>> pairs = new ArrayList<ArrayList<String>>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("./ValidationDataBase.txt"));
			String line = reader.readLine();
			int counter = 0;
			String[] pair;
			while (line != null) {
				pair = line.split(",");
				pairs.add(new ArrayList<String>());
				pairs.get(counter).add(pair[0]);
				pairs.get(counter).add(pair[1]);
				counter++;
				line = reader.readLine();
			}
			this.validpairs = pairs;
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Validates a username and password combination
	 * @param user the username
	 * @param pw the password
	 * @return true if valid, false otherwise
	 */
	public boolean validate(String user, String pw) {
		for(int i = 0; i < validpairs.size(); i++) {
			if(validpairs.get(i).get(0).equals(user)&&validpairs.get(i).get(1).equals(pw)) {
				return true;
			}
		}
		return false;
	}
} 