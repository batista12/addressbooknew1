package com.capgemini.addressbooknew;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import junit.framework.Assert;
/**
 * 
 */
import java.util.Arrays;
import java.util.List;
public class AddressBookNewTest {

	@Test
	public void given3Contacts_WhenWrittenToFile_ShouldMatchContactEntries() {
		ContactDetails infoOne = new ContactDetails("manasi", "singh", "agrico", "jhar", 8327843, 14354,
				"manasisingh@gmail.com");
		ContactDetails infoTwo = new ContactDetails("riya", "dafs", "agrico", "jhar", 24354, 243546,
				"manasi@gamil.com");
		ContactDetails infoThree = new ContactDetails("manas", "qrewfr", "agrico", "jhar",324345, 2234543,
				"manasi12@gmailcom");
		List<ContactDetails> contactDetailsList = Arrays.asList(new ContactDetails[] { infoOne, infoTwo, infoThree });
		AddressBookIOfile AddressBookIOfile = new AddressBookIOfile();
		AddressBookIOfile.writeData(contactDetailsList);
		AddressBookIOfile.printEntries();
		List<ContactDetails> readContacts = AddressBookIOfile.readEntries();
		System.out.println(readContacts);
		Assert.assertEquals(infoOne.toString(), readContacts.get(0).toString());
		Assert.assertEquals(infoTwo.toString(), readContacts.get(1).toString());
		Assert.assertEquals(infoThree.toString(), readContacts.get(2).toString());
	}
}
