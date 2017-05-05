package com.unifi.fattureApp.mongoIT;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.MedicalOfficeController;
import com.unifi.fattureApp.App.Patient;
import com.unifi.fattureApp.helpTestTools.MongoTestHelperTool;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

public class MedicalOfficeIntegrationTest {
	private MedicalOfficeController medicalController;

	// helper for testing with Mongo
	private MongoTestHelperTool mongoTestHelper;

	@Before
	public void setUp() throws Exception {
		// in-memory java implementation of MongoDB
		// so that we don't need to install MongoDB in our computer
		Fongo fongo = new Fongo("mongo server 1");
		MongoClient mongoClient = fongo.getMongo();
		mongoTestHelper = new MongoTestHelperTool(mongoClient);

		// we don't mock the database:
		// we use a real instance of MongoDatabaseWrapper
		Database database = new MongoWrapper(mongoClient);
		medicalController = new MedicalOfficeController(database);
	}

	@Test
	public void testGetAllStudentsWhenThereAreNoStudents() {
		List<Patient> allStudents = medicalController.getAllPatients();
		assertEquals(0, allStudents.size());
	}

	@Test
	public void testGetAllStudentsWhenThereIsOneStudent() {
		mongoTestHelper.addStudent("1", "test");
		List<Patient> allStudents = medicalController.getAllPatients();
		assertEquals(1, allStudents.size());	
	}

	@Test
	public void testGetStudentByIdWhenStudentIsNotThere() {
		mongoTestHelper.addStudent("1", "test");
		Patient patient = medicalController.getPatientId("2");
		assertNull(patient);
	}

	@Test
	public void testGetStudentByIdWhenStudentIsThere() {
		mongoTestHelper.addStudent("1", "test");
		Patient patient = medicalController.getPatientId("1");
		assertNotNull(patient);
		assertEquals("test", patient.getName());
	}

}
