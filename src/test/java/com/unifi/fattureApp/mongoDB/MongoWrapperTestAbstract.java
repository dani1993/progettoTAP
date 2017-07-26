package com.unifi.fattureApp.mongoDB;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.Invoice;
import com.unifi.fattureApp.helpTestTools.MongoTestHelperTool;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

public abstract class MongoWrapperTestAbstract { 
	private MongoWrapper mongoDatabase;

	public abstract MongoClient createMongoClient() throws UnknownHostException;

	private MongoTestHelperTool mongoTestHelper;

	@Before
	public void initDB() throws UnknownHostException {
		// in-memory java implementation of MongoDB
		// so that we don't need to install MongoDB in our computer
		MongoClient mongoClient = createMongoClient();
		mongoTestHelper = new MongoTestHelperTool(mongoClient);
		mongoDatabase = new MongoWrapper(mongoClient);
	}

	@Test
	public void testGetAllPatientsEmpty() {
		assertTrue(mongoDatabase.getAllClientsList().isEmpty());
	}

	@Test
	public void testGetAllClientsNotEmpty() {
		mongoTestHelper.addClient("1", "first","firstFC","firstCR","firstPhone","firstEmail"/*,"firstBD"*/);
		mongoTestHelper.addClient("2", "second","secondFC","secondCR","secondPhone","secondEmail"/*,"secondBD"*/);
	
		assertEquals(2, mongoDatabase.getAllClientsList().size());
	}

	@Test
	public void testFindPatientByIdNotFound() {
		mongoTestHelper.addClient("1", "first","firstFC","firstCR","firstPhone","firstEmail"/*,"firstBD"*/);
		assertNull(mongoDatabase.findClientById("2"));
	}

	@Test
	public void testPatientIsSaved() {
		mongoDatabase.saveClient(new Client("1", "test","testFC","testCR","testPhone","testEmail"/*,"testBD"*/));
		assertTrue(mongoTestHelper.containsClient("1", "test", "testFC", "testCR","testPhone","testEmail"/*, "testBD"*/));

		//assertNotNull(mongoTestHelper.containsClient("1", "test","testFC","testCR","testBD"));
	}
	
	@Test
	public void testPatientIsNotSaved() {
		mongoDatabase.saveClient(new Client("1", "test","testFC","testCR","testPhone","testEmail"/*,"testBD"*/));
		assertFalse(mongoTestHelper.containsClient("2", "test2", "testFC2", "testCR2","testPhone2","testEmail2"/*, "testBD2"*/));
	}
	

	@Test
	public void testFindPatientByIdFound() {
		mongoTestHelper.addClient("1", "first","firstFC","firstCR","firstPhone","firstEmail"/*,"firstBD"*/);
		mongoTestHelper.addClient("2", "second","secondFC","secondCR","secondPhone","secondEmail"/*,"secondBD"*/);
	
		Client findClientById = mongoDatabase.findClientById("2");
		assertNotNull(findClientById);
		assertEquals("2", findClientById.getId());
		assertEquals("second", findClientById.getName());
	}
	
	
	
	
	
	
	@Test
	public void testGetAllCompaniesEmpty() {
		assertTrue(mongoDatabase.getAllCompaniesList().isEmpty());
	}

	@Test
	public void testGetAllCompaniesNotEmpty() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		mongoTestHelper.addCompany("2", "nameC2", "vatCode2", "address2", "city2", "province2", "zipCode2", "country2", "phone2", "email2");

	
		assertEquals(2, mongoDatabase.getAllCompaniesList().size());
	}

	@Test
	public void testFindCompanyByIdNotFound() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		assertNull(mongoDatabase.findCompanyById("2"));
	}

	@Test
	public void testCompanyIsSaved() {
		mongoDatabase.saveCompany(new Company("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1"));
		assertTrue(mongoTestHelper.containsCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1"));
	}

	@Test
	public void testFindCompanyByIdFound() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		mongoTestHelper.addCompany("2", "nameC2", "vatCode2", "address2", "city2", "province2", "zipCode2", "country2", "phone2", "email2");

		Company findCompanyById = mongoDatabase.findCompanyById("2");
		assertNotNull(findCompanyById);
		assertEquals("2", findCompanyById.getId());
		assertEquals("nameC2", findCompanyById.getName());
	}
	
	
	
	
	
	@Test
	public void testGetAllInvoicesEmpty() {
		assertTrue(mongoDatabase.getAllInvoicesList().isEmpty());
	}

	@Test
	public void testGetAllInvoicesNotEmpty() {
		mongoTestHelper.addInvoice("1", "nameI1", "100", "basic invoice type1");
		mongoTestHelper.addInvoice("2", "nameI2", "200", "basic invoice type2");
	
		assertEquals(2, mongoDatabase.getAllInvoicesList().size());
	}

	@Test
	public void testFindInvoiceByIdNotFound() {
		mongoTestHelper.addInvoice("1", "nameI1", "100", "basic invoice type1");
		assertNull(mongoDatabase.findInvoiceById("2"));
	}

	@Test
	public void testInvoiceIsSaved() {
		mongoDatabase.saveInvoice(new Invoice("1", "nameI1", "100", "basic invoice type1"));
		assertTrue(mongoTestHelper.containsInvoice("1", "nameI1", "100", "basic invoice type1"));
	}

	@Test
	public void testFindInvoiceByIdFound() {
		mongoTestHelper.addInvoice("1", "nameI1", "100", "basic invoice type1");
		mongoTestHelper.addInvoice("2", "nameI2", "200", "basic invoice type2");

		Invoice findInvoiceById = mongoDatabase.findInvoiceById("2");
		assertNotNull(findInvoiceById);
		assertEquals("2", findInvoiceById.getId());
		assertEquals("nameI2", findInvoiceById.getName());
	}
	
	
	
	
	
	
	
}