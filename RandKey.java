import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class RandKey {
	private String account;
	private String company;
	public String firstName;
	public String lastName;
	public String address1;
	public String address2;
	public String city;
	public String state;
	public String zip;

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
	}
	private String genKey() {
		return "KEY HERE";
	}
	public String toString() {
		return account + " | " + company + " | " + firstName + " | " + lastName + " | " + address1 + " | " + address2 + " | " + city + " | " + state + " | " + zip + "\n" +
				this.genKey();
	}
	// toString method?

	public static void main(String[] args) {
		String line = "";
		String seperator = ",";
		BufferedReader br = null;
		try {
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
		// for loop read file process
	}
}
