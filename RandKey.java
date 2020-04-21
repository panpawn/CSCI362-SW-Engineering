import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class RandKey {  //class that initializes the various characteristics of the object
	private String account;
	private String company;
	private String firstName;
	private String lastName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String randomKey;
	private int randomKeyLength = 24;

	private String possibleChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; //a list of characters the key can be made from

	public RandKey(String account, String company, String firstName, String lastName, String address1, String address2, String city, String state, String zip) {//class that takes inputs and assigns them to the characteristics of the object
		this.account = account;
		this.company = company;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.randomKey = this.genKey(this.randomKeyLength);
	}
	private String genKey(int length) {//a private class that is used to generate a key to be assigned to an object
		String buffer = "";
		for (int i = 0; i < length; i++) {
			int character = (int)(Math.random() * this.possibleChars.length());
			buffer += possibleChars.charAt(character);
		}
		this.randomKey = buffer;
		return buffer;
	}

	public String toString() {//returns the object as a string
		return account + " | " + company + " | " + firstName + " | " + lastName + " | " + address1 + " | " + address2 + " | " + city + " | " + state + " | " + zip + " | " + randomKey;
	}
	// toString method?

	public static void main(String[] args) {//main method
		String line = "";
		String seperator = ",";
		BufferedReader br = null;
		try {//try, to create the object and then print it
			// try-catch is wanted he said
			br = new BufferedReader((new FileReader("customers.csv")));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(seperator, -1);

				String account = (data[0].length() != 0 ? data[0].trim() : "N/A");
				String company = (data[1].length() != 0 ? data[1].trim() : "N/A");
				String firstName = (data[2].length() != 0 ? data[2].trim() : "N/A");
				String lastName = (data[3].length() != 0 ? data[3].trim() : "N/A");
				String address1 = (data[4].length() != 0 ? data[4].trim() : "N/A");
				String address2 = (data[5].length() != 0 ? data[5].trim() : "N/A");
				String city = (data[6].length() != 0 ? data[6].trim() : "N/A");
				String state = (data[7].length() != 0 ? data[7].trim() : "N/A");
				String zip = (data[8].length() != 0 ? data[8].trim() : "N/A");

				RandKey entry = new RandKey(account, company, firstName, lastName, address1, address2, city, state, zip);

				System.out.println(entry);
			}
		} catch (FileNotFoundException e) {//if file not found
			e.printStackTrace();
		} catch (IOException e) {//if an exception passes
			e.printStackTrace();
		} finally {
			if (br != null) {//used to close out the bufferedReader
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// for loop read file process
	}
}
