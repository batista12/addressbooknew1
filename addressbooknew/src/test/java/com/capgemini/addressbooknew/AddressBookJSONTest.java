package com.capgemini.addressbooknew;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
public class AddressBookJSONTest {
	@Test
	public void given3ContactsWhenWrittenToJsonFileShouldMatchContactEntries() {
		ContactDetails manasi=new ContactDetails("manasi", "singh", "agrico", "jharkhand",  21, 12345, "manasi@gmail.com");
		ContactDetails riya=new ContactDetails("riya", "singh", "agrico", "jharkhand", 21, 7890, "riya@gmail.com");
		ContactDetails neha=new ContactDetails("neha", "singh", "agrico", "jharkhand", 21, 1343, "neha@gmail.com");
		List<ContactDetails> contacts = Arrays.asList(new ContactDetails[] {manasi, riya, neha});
		AddressBookJSON AddressBookJSON = new AddressBookJSON("addressBook-test-Json.json");
		AddressBookJSON.writeContactDetails(contacts);
		List<ContactDetails> readContacts = AddressBookJSON.readContactDetails();
		Assert.assertEquals(manasi.toString(),readContacts.get(0).toString());
		Assert.assertEquals(riya.toString(),readContacts.get(1).toString());
		Assert.assertEquals(neha.toString(),readContacts.get(2).toString());
	}

}
