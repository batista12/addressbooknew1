package com.capgemini.addressbooknew;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class AddressBookIOfile {
	public static String ADDRESS_BOOK_NAME = "addressbook.txt";

	/**
	 * @param contactDetailsList
	 */
	public void writeData(List<ContactDetails> contactDetailsList) {
		StringBuffer contactBuffer = new StringBuffer();
		contactDetailsList.forEach(contact -> {
			String contactDataString = contact.toString().concat("\n");
			contactBuffer.append(contactDataString);
		});

		try {
			Files.write(Paths.get(ADDRESS_BOOK_NAME), contactBuffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printEntries() {
		try {
			Files.lines(new File(ADDRESS_BOOK_NAME).toPath()).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<ContactDetails> readEntries() {
		List<ContactDetails> contactDetailsList = new ArrayList();
		try {
			contactDetailsList = Files.lines(new File(ADDRESS_BOOK_NAME).toPath()).map(line -> line.trim())
					.map(line -> {
						String[] contactDetailsArray = line.split(", ");
						return new ContactDetails(contactDetailsArray[0].split(" ")[0],
								contactDetailsArray[0].split(" ")[1], contactDetailsArray[1], contactDetailsArray[2],
								Integer.parseInt(contactDetailsArray[4]),
								Long.parseLong(contactDetailsArray[5]), contactDetailsArray[6]);
					}).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contactDetailsList;
	}

}
