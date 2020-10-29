package com.capgemini.addressbooknew;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
public class AddressBookJSON {
	public String addressBookJsonFileName;
	public AddressBookJSON(String addressBookJsonFileName) {
		super();
		this.addressBookJsonFileName = addressBookJsonFileName;
	}
	/**
	 * @param contacts
	 */
	public void writeContactDetails(List<ContactDetails> contacts) {
		try {
			Gson gson = new Gson();
			String json = gson.toJson(contacts);
			FileWriter writer = new FileWriter(this.addressBookJsonFileName);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<ContactDetails> readContactDetails() {
		List<ContactDetails> contacts = null;
		try {
			Gson gson = new Gson();
			BufferedReader bufferedReader = new BufferedReader(new FileReader(this.addressBookJsonFileName));
			ContactDetails[] contactsArray = gson.fromJson(bufferedReader, ContactDetails[].class);
			contacts = new LinkedList<ContactDetails>(Arrays.asList(contactsArray));
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return contacts;
	}

}