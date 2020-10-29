package com.capgemini.addressbooknew;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
/**
 * 
 *
 */
public class AddressBookCSV {
	public String addressBookCSVFileName;

	public AddressBookCSV(String addressBookCSVFileName) {
		super();
		this.addressBookCSVFileName = addressBookCSVFileName;
	}

	/**
	 * @param contacts
	 */
	public void writeContactDetails(List<ContactDetails> contacts) {
		try (Writer writer = Files.newBufferedWriter(Paths.get(this.addressBookCSVFileName));) {
			StatefulBeanToCsv<ContactDetails> beanToCSV = new StatefulBeanToCsvBuilder(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
			beanToCSV.write(contacts);
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<ContactDetails> readContactDetails() {
		List<ContactDetails> contacts = null;
		try (Reader reader = Files.newBufferedReader(Paths.get(this.addressBookCSVFileName));) {
			CSVReader csvReader = new CSVReader(reader);
			List<String[]> contactStrings = csvReader.readAll();
			contactStrings.remove(0);
			contacts = contactStrings.stream().map(string -> {
				String firstName = string[2], lastName = string[3], address = string[0];
				String state = string[5];
				int zip = Integer.parseInt(string[6]);
				long phoneNumber = Long.parseLong(string[4]);
				String email = string[1];
				return new ContactDetails(firstName, lastName, address, state, zip, phoneNumber, email);
			}).collect(Collectors.toList());
			csvReader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return contacts;
	}

}
