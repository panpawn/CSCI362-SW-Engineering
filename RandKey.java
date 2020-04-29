/*
 * RandKey - a class dedicated to
 * 1. generating a random transaction ID for an order
 * 2. a method to fetch the transaction ID given an
 * account number
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// RandKey - a class that can be instantiated that includes the aspects of a transaction,
// where it also will assign a random transaction ID.
public class RandKey {
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

	// All possible characters that could be included in a random transaction ID
	private String possibleChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public RandKey(String account, String company, String firstName, String lastName, String address1, String address2, String city, String state, String zip) {
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
	// In here, we will generate the transaction ID
	private String genKey(int length) {
		String buffer = "";
		for (int i = 0; i < length; i++) {
			int character = (int)(Math.random() * this.possibleChars.length());
			buffer += possibleChars.charAt(character);
		}
		return buffer;
	}
	public String getAccount() {
		return this.account;
	}
	public String getTransID() {
		return this.randomKey;
	}

	// We override toString here to better show the aspects of each transaction
	// and its random transaction ID
	public String toString() {
		return account + " | " + company + " | " + firstName + " | " + lastName + " | " + address1 + " | " + address2 + " | " + city + " | " + state + " | " + zip + " | " + randomKey;
	}

	// findTransactionId - this is the second required method that will
	// fetch the transaction ID given an account number and the array of
	// RandKey objects.
	public static void fetchTransID(String accountNumber, RandKey[] transactions) {
		System.out.println("Searching for transaction ID for account number: " + accountNumber + "...");
		String results = "(None)";
		for (int i = 0; i < transactions.length; i++) {
			if (transactions[i].getAccount().equals(accountNumber)) results = transactions[i].getTransID();
		}
		System.out.println("Transaction ID found: " + results);
	}

	public static void main(String[] args) {
		String line = "";
		String separator = ",";
		BufferedReader br = null;
		try {
			br = new BufferedReader((new FileReader("./customers.csv")));
			Path path = Paths.get("./customers.csv");
			long lineCount = Files.lines(path).count();
			RandKey[] transactions = new RandKey[(int)lineCount];
			//System.out.println("Total lines: " + lineCount);
			int counter = 0;
			while ((line = br.readLine()) != null) {

				String[] data = line.split(separator, -1);

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
				transactions[counter++] = entry;
				System.out.println(entry);
			}

			System.out.println("\n\n");

			// give transaction ID given account number:
			fetchTransID("3897532874ceadc89c21d2", transactions);
			fetchTransID("13078069694beb11d285a3e", transactions);
			fetchTransID("15586517244b59d42faad04", transactions);
			// finally, fetching a non-existent account number
			fetchTransID("13078069694beb11d285a32", transactions);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
