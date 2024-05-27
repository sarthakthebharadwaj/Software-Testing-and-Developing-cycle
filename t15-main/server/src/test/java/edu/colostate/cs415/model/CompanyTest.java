package edu.colostate.cs415.model;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import edu.colostate.cs415.model.Qualification;

public class CompanyTest {
	@Test
	public void test() {
		assert (true);
	}

	@Test
	public void testConstructorWithNull(){
		String name = null;
		try{
			Company company = new Company(name);
		}catch(NullPointerException e){
			assertEquals(null, e.getMessage());
		}
	}

	@Test
	public void testConstructor(){
		String name = "Sbeve Inc";
		Company company = new Company(name);
		assertEquals(company.getName(), name);
	}

	@Test
	public void testConstructorEmptyString(){
		String name = "";
		try{
			Company company = new Company(name);
		}catch(NullPointerException e){
			assertEquals(null, e.getMessage());
		}
	}

	@Test
	public void testConstructorWhitespaceString(){
		String name = "   ";
		try{
			Company company = new Company(name);
		}catch(NullPointerException e){
			assertEquals(null, e.getMessage());
		}
	}

	@Test 
	public void testforquals1(){
		Company company = new Company("Bharadwaj LTD");
		assertTrue(company.equals(company));
	}

	@Test 
	public void testforquals2(){
		Company company = new Company("Bharadwaj LTD");
		assertNotNull(company);
	}

	@Test 
	public void testforquals3(){
		Company company = new Company("Bharadwaj LTD");
		Object object = new Object();
		assertFalse(company.equals(object));
	}

	@Test 
	public void testforquals4(){
		Company company = new Company("Bharadwaj LTD");
		Company company2 = new Company("Sarthak LTD");
		assertFalse(company.equals(company2));
	}

	@Test
	public void testforHashcode1(){
		Company c1 = new Company("Sarthak123");
		Company c2 = new Company("Sarthak123");
		assertEquals(c1.hashCode(),c2.hashCode()); 
	}

	@Test
	public void testforHashcode2(){
		Company c1 = new Company("Sarthak123");
		Company c2 = new Company("Bharadwaj123");
		assertNotEquals(c1.hashCode(),c2.hashCode()); 
	}

	@Test
	public void testforHashcodeWithQualifications(){
		Company c1 = new Company("Sarthak123");
		Company c2 = new Company("Sarthak123");
		c2.createQualification("Python");
		c1.createQualification("Python");

		assertEquals(c1.hashCode(),c2.hashCode()); 
	}

	@Test
	public void testforHashcodeWithQualificationsExactValue(){
			Company c1 = new Company("Sarthak123");
			c1.createQualification("Python");

			assertEquals(c1.hashCode(),-1494763731); 
	}

	@Test
	public void testforHashcodeWithQualificationsNotEqualß(){
		Company c1 = new Company("Sarthak123");
		Company c2 = new Company("Sarthak123");
		c2.createQualification("Python");
		c1.createQualification("Java");

		assertNotEquals(c1.hashCode(),c2.hashCode()); 
	}

	@Test
	public void testCreateQualification(){
		Company c1 = new Company("Test company");
		String qual = "Java";
		Qualification q = c1.createQualification(qual);
		assertTrue(c1.getQualifications().contains(q));

	}

	@Test
	public void testCreateQualificationNoDescription(){
		Company c1 = new Company("Test company");
		String qual = "";
		Qualification q = c1.createQualification(qual);
		assertEquals(null, q);

	}

	@Test
	public void testCreateQualificationNull(){
		Company c1 = new Company("Test company");
		String qual = null;
		Qualification q = c1.createQualification(qual);
		assertEquals(null, q);

	}

	@Test
	public void testCreateQualificationWithWhitespace(){
		Company c1 = new Company("Test company");
		String qual = "   ";
		Qualification q = c1.createQualification(qual);
		assertEquals(null, q);

	}

	@Test
	public void testCreateQualificationWithDuplicate(){
		Company c1 = new Company("Test company");
		String qual = "   ";
		Qualification q2 = c1.createQualification(qual);

		assertEquals(null, q2);

	}
  
	public void testCreateProject1(){
		Company company = new Company("Test Company");

		Qualification qualification = new Qualification("Java Developer");
		company.createQualification("Java Developer");
		Set <Qualification> qualifications = new HashSet<>();
		qualifications.add(qualification);

		Project project = company.createProject("", qualifications , ProjectSize.SMALL);

		assertNull(project);
	}

	@Test
	public void testCreateProject2(){
    Company company = new Company("Test Company");
		Qualification qualification = new Qualification("Java Developer");
		company.createQualification("Java Developer");
		Set <Qualification> qualifications = new HashSet<>();
		qualifications.add(qualification);

		ProjectSize size = ProjectSize.SMALL;
		String name = "proj";

		Project project = company.createProject(name, qualifications , size);
		assertEquals("proj", name);
		assertEquals(qualifications, project.getRequiredQualifications());
		assertEquals(ProjectSize.SMALL, size);

	}

	@Test 
	public void testCreateProject3(){
		Company company = new Company("Test Company");

		Qualification qualification = new Qualification("Java Developer");
		company.createQualification("Java Developer");
		Set <Qualification> qualifications = new HashSet<>();
		qualifications.add(qualification);

		Project project = company.createProject("test project", qualifications , null);

		assertNull(project);
	}


	@Test 
	public void testCreateProject4(){
    Company company = new Company("Test Company");
		Qualification qualification = new Qualification("Java Developer");
		company.createQualification("Java Developer");
		Set <Qualification> qualifications = new HashSet<>();
		qualifications.add(qualification);

		Project project = company.createProject("Test Project", qualifications , ProjectSize.MEDIUM);

		assertNotNull(project);
	}

	@Test 
	public void testCreateProjectNoQualifications(){
    Company company = new Company("Test Company");
		company.createQualification("Java Developer");
		Set <Qualification> qualifications = new HashSet<>();

		Project project = company.createProject("Test Project", qualifications , ProjectSize.MEDIUM);

		assertNull(project);
	}

	@Test
	public void create_worker_test1(){
		Company c = new Company("Test Company");
		String name = "Sarthak Bharadwaj";
		double salary = 220000.0;

		
		Worker w = c.createWorker(name,null,salary);
		assertNull(w);
	}

	@Test
	public void create_worker_test2(){
		Company company = new Company("Test Company");
		String name = "Sarthak Bharadwaj";
		double salary = -220000.0;
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("Java"));

	
		Worker w = company.createWorker(name,qualifications,salary);
		assertNull(w);

	}
	@Test
	public void create_worker_test3(){
		Company company = new Company("Test Company");
		String name = "Sarthak Bharadwaj";
		double salary = -220000.0;
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("Java"));
		company.createQualification("Python");
	
		Worker worker = company.createWorker(name,qualifications,salary);
		assertNull(worker);
	
	}

	@Test
	public void create_worker_test4(){
		Company company = new Company("Test Company");
		String name = "    ";
		double salary = 220000.0;
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("Java"));
		Worker w = company.createWorker(name,qualifications,salary);
		assertNull(w);
	}

	@Test
	public void create_worker_test_no_qual_incomp(){
		Company company = new Company("Test Company");
		String name = "bill";
		double salary = 220000.0;
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("Java"));
		company.createQualification("Python");
		Worker worker = company.createWorker(name,qualifications,salary);
		assertNull(worker);
	}

	@Test
	public void create_worker_test_name_null(){
		Company company = new Company("Test Company");
		double salary = 10.0;
		String name = null;
		Set<Qualification> qualifications = new HashSet<>();
		
		Worker worker = company.createWorker(name,qualifications,salary);
		assertNull(worker);
	}

	@Test
	public void create_worker_test_Neg_salary(){
		Company company = new Company("Test Company");
		String name = "nill";
		double salary = -220000.0;
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("Java"));
		Worker worker = company.createWorker(name, qualifications,salary);
		assertNull(worker);
	}

	@Test
	public void create_worker_test_No_name(){
		Company company = new Company("Test Company");
		String name = "";
		double salary = 220000.0;
		Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("Java"));
		Worker worker = company.createWorker(name,qualifications,salary);
		assertNull(worker);
	}

	@Test
	public void create_worker_happy(){
		Company c2 = new Company("Test");
		Set<Qualification> quals = new  HashSet<>();
		Qualification qual2 = new Qualification("Java");
		quals.add(qual2);
		c2.createQualification("Java");
		Worker w1 = c2.createWorker("sarthak",quals,20000);


		assertEquals(w1.getName(), "sarthak");
		assertEquals(w1.getSalary(), 20000, .00001);
	}

	@Test
	public void create_worker_test_Zero(){
		Company c2 = new Company("Test");
		Set<Qualification> quals = new  HashSet<>();
		Qualification qual2 = new Qualification("Java");
		quals.add(qual2);
		c2.createQualification("Java");
		Worker w1 = c2.createWorker("sarthak",quals,0.0);


		assertEquals(w1.getName(), "sarthak");
		assertEquals(w1.getSalary(), 0.0, .00001);
	}



	@Test 
	public void getname_1(){
		Company company = new Company("Sarthak LTD");
		assertEquals("Sarthak LTD",company.getName());
	}

	@Test 
	public void getname_2(){
		Company company = new Company("Sarthak LTD");
		assertFalse(". ".equals(company.getName()));
	}

	@Test
	public void testStartWithPlannedMetQualifcications(){
		Set<Qualification> quals = new HashSet<>();
		quals.add(new Qualification("Java"));
		quals.add(new Qualification("Python"));
		ProjectSize s = ProjectSize.SMALL;

		Company testCompany = new Company("Google");
		Project proj = new Project("testProj", quals, s);

		Set<Qualification> quals2 = new HashSet<>();
		quals.add(new Qualification("Java"));
		
		proj.addWorker(new Worker("Bill", quals2, 20000.00));

		testCompany.start(proj);
		assertEquals(ProjectStatus.ACTIVE, proj.getStatus());

		
	}

	@Test
	public void testStartWithSuspendedMetQualifcications(){
		Set<Qualification> quals = new HashSet<>();
		quals.add(new Qualification("Java"));
		quals.add(new Qualification("Python"));
		ProjectSize s = ProjectSize.SMALL;

		Company testCompany = new Company("Google");
		Project proj = new Project("testProj", quals, s);

		Set<Qualification> quals2 = new HashSet<>();
		quals.add(new Qualification("Java"));
		
		
		proj.addWorker(new Worker("Bill", quals2, 20000.00));
		proj.addWorker(new Worker("Jackie", quals2, 30000.00));

		proj.setStatus(ProjectStatus.SUSPENDED);


		try {
			testCompany.start(proj);
		} catch (IllegalArgumentException e) {
			assertEquals("Incorrect ProjectStatus", e.getMessage());
		}

	}

	@Test
	public void testStartWithActiveMetQualifcications(){
		try{
			Set<Qualification> quals = new HashSet<>();
			quals.add(new Qualification("Java"));
			quals.add(new Qualification("Python"));
			ProjectSize s = ProjectSize.SMALL;

			Company testCompany = new Company("Google");
			Project proj = new Project("testProj", quals, s);

			Set<Qualification> quals2 = new HashSet<>();
			quals.add(new Qualification("Java"));

			proj.addWorker(new Worker("Bill", quals2, 20000.00));
			proj.addWorker(new Worker("Jackie", quals2, 30000.00));

			proj.setStatus(ProjectStatus.ACTIVE);

			testCompany.start(proj);
		}
		catch(IllegalArgumentException e){
			assertEquals("Incorrect ProjectStatus", e.getMessage());
		}
		
	}

	@Test
	public void testStartWitFinishedhMetQualifcications(){
		try{
			Set<Qualification> quals = new HashSet<>();
			quals.add(new Qualification("Java"));
			quals.add(new Qualification("Python"));
			ProjectSize s = ProjectSize.SMALL;

			Company testCompany = new Company("Google");
			Project proj = new Project("testProj", quals, s);

			Set<Qualification> quals2 = new HashSet<>();
			quals2.add(new Qualification("Java"));

			
			proj.addWorker(new Worker("Bill", quals2, 20000.00));
			proj.addWorker(new Worker("Jackie", quals2, 30000.00));

			proj.setStatus(ProjectStatus.FINISHED);

			testCompany.start(proj);
		}
		catch(IllegalArgumentException e){
			assertEquals("Incorrect ProjectStatus", e.getMessage());
		}
		
	}

	@Test
	public void testStartWithPlannedNotMetQualifcications(){
		try{
			Set<Qualification> quals = new HashSet<>();
			quals.add(new Qualification("Java"));
			quals.add(new Qualification("Python"));

			Set<Qualification> quals2 = new HashSet<>();
			quals2.add(new Qualification("Java"));

			ProjectSize s = ProjectSize.SMALL;

			Company testCompany = new Company("Google");
			Project proj = new Project("testProj", quals, s);
			
			
			proj.addWorker(new Worker("Bill", quals2, 20000.00));
			proj.addWorker(new Worker("Jackie", quals2, 30000.00));

			testCompany.start(proj);
		}
		catch(IllegalArgumentException e){
			assertEquals("Project has missing qualifications", e.getMessage());
		}
		
	}

	@Test
	public void testStartWithNull(){
		try{
			Company testCompany = new Company("Google");
			Project proj = null;

			testCompany.start(proj);
		}
		catch(NullPointerException e){
			assertEquals("Invalid null input", e.getMessage());
		}
		
	}
	@Test
	public void testGetUnavailable(){
		Set<Qualification> quals = new HashSet<>();
		quals.add(new Qualification("Java"));
		quals.add(new Qualification("Python"));
		ProjectSize s = ProjectSize.BIG;

		Company testCompany = new Company("inc. inc.");
		Project proj = new Project("testProj", quals, s);

		quals.remove(quals.size()-1);

		Worker w1 = new Worker("Bill", quals, 20000.00);
		Worker w2 = new Worker("Jackie", quals, 30000.00);

		proj.addWorker(w1);
		proj.addWorker(w2);

		assertTrue(testCompany.getUnavailableWorkers().isEmpty());
	}

	@Test
	public void testGetUnavailableAddtoUnavailable(){
		Set<Qualification> quals = new HashSet<>();
		quals.add(new Qualification("Java"));
		quals.add(new Qualification("Python"));
		ProjectSize s = ProjectSize.BIG;

		Company testCompany = new Company("inc. inc.");
		testCompany.createQualification("Java");
		testCompany.createQualification("Python");
		Project proj = new Project("testProj", quals, s);
		Project proj2 = new Project("testProj1", quals, s);
		Project proj3 = new Project("testProj2", quals, s);
		Project proj4 = new Project("testProj3", quals, s);

		Worker w1 = testCompany.createWorker("Bill", quals, 2000.000);

		testCompany.assign(w1, proj);
		testCompany.assign(w1, proj2);
		testCompany.assign(w1, proj3);
		testCompany.assign(w1, proj4);

		assertFalse(testCompany.getUnavailableWorkers().isEmpty());
		assertTrue(testCompany.getUnavailableWorkers().contains(w1));
		assertTrue(testCompany.getAssignedWorkers().contains(w1));
	}

	@Test
	public void testGetUnavailableNoUnavailable(){
		Set<Qualification> quals = new HashSet<>();
		quals.add(new Qualification("Java"));
		quals.add(new Qualification("Python"));
		ProjectSize s = ProjectSize.BIG;

		Company testCompany = new Company("inc. inc.");
		testCompany.createQualification("Java");
		testCompany.createQualification("Python");
		Project proj = new Project("testProj", quals, s);
		Project proj2 = new Project("testProj1", quals, s);
		Project proj3 = new Project("testProj2", quals, s);

		Worker w1 = testCompany.createWorker("Bill", quals, 2000.000);

		testCompany.assign(w1, proj);
		testCompany.assign(w1, proj2);
		testCompany.assign(w1, proj3);

		assertTrue(testCompany.getUnavailableWorkers().isEmpty());
		assertFalse(testCompany.getUnavailableWorkers().contains(w1));
		assertTrue(testCompany.getAssignedWorkers().contains(w1));
	}

	@Test
	public void testGetEmployee1(){
		Company c = new Company("Test");
		Set<Qualification> quals = new  HashSet<>();
		Qualification qual = new Qualification("Java");
		quals.add(qual);
		c.createQualification("Java");
		Worker w1 = c.createWorker("sarthak",quals,20000);
		Worker w2 = c.createWorker("bharadwaj",quals,30000);

		Set<Worker> employedWorkers = c.getEmployedWorkers();
		assertEquals(2,employedWorkers.size());
		assertTrue(employedWorkers.contains(w1));
		assertTrue(employedWorkers.contains(w2));
		assertTrue(qual.getWorkers().contains(w1));
		assertTrue(qual.getWorkers().contains(w2));
	}

	@Test
	public void testGetEmployeeEmpty(){
		Company c = new Company("Test");
		Set<Worker> employedWorkers = c.getEmployedWorkers();
		assertEquals(0,employedWorkers.size());
	}

	@Test
	public void testcreateworker_1() {
		Company company = new Company("Tests for company");
		Set<Qualification> quals = new HashSet<>();
		Qualification qual = new Qualification("Java Developer");
		quals.add(qual);
		company.createQualification("Java Developer");

		Worker newWorker = company.createWorker("Sarthak", quals, 20000);

		assertNotNull(newWorker);
		assertTrue(newWorker.getQualifications().contains(qual));
		assertEquals(20000, newWorker.getSalary(), 0.01);

	}


	@Test
	public void testAssignHappyPath(){
		Company c = new Company("Test");
		Set<Qualification> quals = new  HashSet<>();
		Qualification qual = new Qualification("Java");
		quals.add(qual);

		c.createQualification("Java");
		
		Worker w1 = c.createWorker("sarthak",quals,20000.00);

		ProjectSize ps = ProjectSize.BIG;
		Project proj = c.createProject("proj", quals, ps);

		c.assign(w1, proj);
		assertTrue(proj.getWorkers().contains(w1));
		assertTrue(c.getAssignedWorkers().contains(w1));
		assertFalse(c.getUnassignedWorkers().contains(w1));

	}

	@Test
	public void testAssignNotAvailable(){
		Company c = new Company("Test");
		Set<Qualification> quals = new  HashSet<>();
		Qualification qual = new Qualification("Java");
		quals.add(qual);

		c.createQualification("Java");
		
		Worker w1 = c.createWorker("sarthak",quals,20000.00);

		ProjectSize ps = ProjectSize.BIG;
		Project proj = c.createProject("proj", quals, ps);
		
		Project proj2 = c.createProject("proj1", quals, ps);
		
		Project proj3 = c.createProject("proj2", quals, ps);
		
		Project proj4 = c.createProject("proj3", quals, ps);

		Project proj5 = c.createProject("proj4", quals, ps);

		c.assign(w1, proj);
		c.assign(w1, proj2);
		c.assign(w1, proj3);
		c.assign(w1, proj4);
		try{
			c.assign(w1, proj5);
		}
		catch(IllegalArgumentException e){
			assertEquals("Worker is not available", e.getMessage());
		}
		assertFalse(proj5.getWorkers().contains(w1));
		assertFalse(c.getAvailableWorkers().contains(w1));
		assertFalse(c.getUnassignedWorkers().contains(w1));

	}

	@Test
	public void testAssignWillOverload(){
		Company c = new Company("Test");
		Set<Qualification> quals = new  HashSet<>();
		Qualification qual = new Qualification("Java");
		quals.add(qual);

		c.createQualification("Java");
		
		Worker w1 = c.createWorker("sarthak",quals,20000.00);

		ProjectSize ps = ProjectSize.BIG;
		ProjectSize small =  ProjectSize.SMALL;
		Project proj = c.createProject("proj", quals, ps);
		
		Project proj2 = c.createProject("proj1", quals, ps);
		
		Project proj3 = c.createProject("proj2", quals, ps);
		
		Project proj4 = c.createProject("proj3", quals, small);

		Project proj5 = c.createProject("proj4", quals, ps);

		c.assign(w1, proj);
		c.assign(w1, proj2);
		c.assign(w1, proj3);
		c.assign(w1, proj4);
		try{
			c.assign(w1, proj5);
		}
		catch(IllegalArgumentException e){
			assertEquals("project will overload worker", e.getMessage());
		}
		assertFalse(proj5.getWorkers().contains(w1));
		assertTrue(c.getAvailableWorkers().contains(w1));
		assertFalse(c.getUnassignedWorkers().contains(w1));


	}

	@Test
	public void testcreateworker_2(){
		Company company = new Company("Tests for company");
		Set<Qualification> quals = new  HashSet<>();
		Qualification qual = new Qualification("Java Developer");
		quals.add(qual);
		company.createQualification("Java Developer");

		Worker newWorker = company.createWorker(null, quals, 20000);

		assertNull(newWorker);
	}

	@Test 
	public void testcreateworker_3(){
		Company company = new Company("Tests for company");
		Set<Qualification> quals = new  HashSet<>();
		Qualification qual = new Qualification("Java Developer");
		quals.add(qual);
		company.createQualification("Java Developer");

		Worker newWorker = company.createWorker("Sarthak", quals, -100);

		assertNull(newWorker);
	}

	@Test 
	public void testcreateworker_4(){
		Company company = new Company("Tests for company");
		Set<Qualification> quals = new  HashSet<>();
		Qualification qual = new Qualification("Not in there ");
		quals.add(qual);
		company.createQualification("Java Developer");

		Worker newWorker = company.createWorker("Sarthak", quals, 50000);

		assertNull(newWorker);
	}

	@Test
	public void testAssignIsNotHelpful(){
		Company c = new Company("Test");
		Set<Qualification> quals = new  HashSet<>();
		Set<Qualification> quals2 = new  HashSet<>();

		Qualification qual = new Qualification("Python");
		quals.add(qual);

		Qualification qual2 = new Qualification("Java");
		quals2.add(qual2);

		c.createQualification("Java");
		c.createQualification("Python");
		
		Worker w1 = c.createWorker("sarthak",quals,20000.00);

		ProjectSize ps = ProjectSize.BIG;
		ProjectSize small =  ProjectSize.SMALL;
		Project proj = c.createProject("proj", quals, ps);
		
		Project proj2 = c.createProject("proj1", quals, ps);
		
		Project proj3 = c.createProject("proj2", quals, ps);
		
		Project proj4 = c.createProject("proj3", quals, small);

		Project proj5 = c.createProject("proj4", quals2, ps);

		c.assign(w1, proj);
		c.assign(w1, proj2);
		c.assign(w1, proj3);
		c.assign(w1, proj4);
		try{
			c.assign(w1, proj5);
		}
		catch(IllegalArgumentException e){
			assertEquals("worker is not helpful", e.getMessage());
		}
		assertFalse(proj5.getWorkers().contains(w1));
		assertTrue(c.getAvailableWorkers().contains(w1));
		assertFalse(c.getUnassignedWorkers().contains(w1));

	}

	@Test
	public void testAssignIsAlreadyAssigned(){
		Company c = new Company("Test");
		Set<Qualification> quals = new  HashSet<>();

		Qualification qual = new Qualification("Python");
		quals.add(qual);


		c.createQualification("Java");
		c.createQualification("Python");
		
		Worker w1 = c.createWorker("sarthak",quals,20000.00);

		ProjectSize ps = ProjectSize.BIG;
		ProjectSize small =  ProjectSize.SMALL;
		Project proj = c.createProject("proj", quals, ps);
		
		Project proj2 = c.createProject("proj1", quals, ps);
		
		Project proj3 = c.createProject("proj2", quals, ps);
		
		Project proj4 = c.createProject("proj3", quals, small);

		Project proj5 = c.createProject("proj4", quals, ps);

		c.assign(w1, proj);
		c.assign(w1, proj2);
		c.assign(w1, proj3);
		c.assign(w1, proj4);
		try{
			c.assign(w1, proj4);
		}
		catch(IllegalArgumentException e){
			assertEquals("worker is already assigned", e.getMessage());
		}
		assertFalse(proj5.getWorkers().contains(w1));
		assertTrue(c.getAvailableWorkers().contains(w1));
		assertFalse(c.getUnassignedWorkers().contains(w1));

	}

	@Test
	public void testAssignState(){
		Company c = new Company("Test");
		Set<Qualification> quals = new  HashSet<>();

		Qualification qual = new Qualification("Python");
		quals.add(qual);


		c.createQualification("Java");
		c.createQualification("Python");
		
		Worker w1 = c.createWorker("sarthak",quals,20000.00);

		ProjectSize ps = ProjectSize.BIG;
		ProjectSize small =  ProjectSize.SMALL;
		Project proj = c.createProject("proj", quals, ps);
		
		Project proj2 = c.createProject("proj1", quals, ps);
		
		Project proj3 = c.createProject("proj2", quals, small);
		
		Project proj4 = c.createProject("proj3", quals, small);

		Project proj5 = c.createProject("proj4", quals, ps);
		proj5.setStatus(ProjectStatus.ACTIVE);

		c.assign(w1, proj);
		c.assign(w1, proj2);
		c.assign(w1, proj3);
		c.assign(w1, proj4);
		try{
			c.assign(w1, proj5);
		}
		catch(IllegalArgumentException e){
			assertEquals("Project is in the wrong state", e.getMessage());
		}
		assertFalse(proj5.getWorkers().contains(w1));
		assertTrue(c.getAvailableWorkers().contains(w1));
		assertFalse(c.getUnassignedWorkers().contains(w1));

		proj5.setStatus(ProjectStatus.FINISHED);
		try{
			c.assign(w1, proj5);
		}
		catch(IllegalArgumentException e){
			assertEquals("Project is in the wrong state", e.getMessage());
		}
		assertFalse(proj5.getWorkers().contains(w1));
		assertTrue(c.getAvailableWorkers().contains(w1));
		assertFalse(c.getUnassignedWorkers().contains(w1));

		proj5.setStatus(ProjectStatus.SUSPENDED);
		c.assign(w1, proj5);
		assertTrue(proj5.getWorkers().contains(w1));
		assertTrue(c.getAvailableWorkers().contains(w1));
		assertFalse(c.getUnassignedWorkers().contains(w1));

	}
	@Test
	public void testUnassign(){
		Company c = new Company("Test");
		Set<Qualification> quals = new  HashSet<>();

		Qualification qual = new Qualification("Python");
		quals.add(qual);


		c.createQualification("Java");
		c.createQualification("Python");
		
		Worker w1 = c.createWorker("sarthak",quals,20000.00);

		ProjectSize ps = ProjectSize.BIG;
		ProjectSize small =  ProjectSize.SMALL;
		Project proj = c.createProject("proj", quals, ps);
		
		Project proj2 = c.createProject("proj1", quals, ps);
		
		Project proj3 = c.createProject("proj2", quals, small);
		
		Project proj4 = c.createProject("proj3", quals, small);

		c.assign(w1, proj);
		c.assign(w1, proj2);
		c.assign(w1, proj3);
		c.assign(w1, proj4);

		c.unassign(w1, proj);
		assertFalse(proj.getWorkers().contains(w1));


	}

	@Test
	public void testUnassignNotAssigned() {
		Company c = new Company("Test");
		Set<Qualification> quals = new  HashSet<>();

		Qualification qual = new Qualification("Python");
		quals.add(qual);


		c.createQualification("Java");
		c.createQualification("Python");

		Worker w1 = c.createWorker("sarthak",quals,20000.00);

		ProjectSize ps = ProjectSize.BIG;
		ProjectSize small =  ProjectSize.SMALL;
		Project proj = c.createProject("proj", quals, ps);

		Project proj2 = c.createProject("proj1", quals, ps);

		Project proj3 = c.createProject("proj2", quals, small);

		Worker w2 = c.createWorker("sarthak",quals,20000.00);



		c.assign(w1, proj);
		c.assign(w1, proj2);
		c.assign(w2, proj3);

		try{
			c.unassign(w1, proj3);
		}
		catch(IllegalArgumentException e){
			assertEquals("Worker is not present", e.getMessage());
		}

	}



	@Test
	public void testUnassignAvailability(){
		Company c = new Company("Test");
		Set<Qualification> quals = new  HashSet<>();

		Qualification qual = new Qualification("Python");
		quals.add(qual);


		c.createQualification("Java");
		c.createQualification("Python");
		
		Worker w1 = c.createWorker("sarthak",quals,20000.00);

		ProjectSize ps = ProjectSize.BIG;
		ProjectSize small =  ProjectSize.SMALL;
		Project proj = c.createProject("proj", quals, ps);
		
		Project proj2 = c.createProject("proj1", quals, ps);
		
		Project proj3 = c.createProject("proj2", quals, small);
		
		Project proj4 = c.createProject("proj3", quals, small);


		c.assign(w1, proj);
		c.assign(w1, proj2);
		c.assign(w1, proj3);
		c.assign(w1, proj4);

		c.unassign(w1, proj);
		assertTrue(c.getAvailableWorkers().contains(w1));
	}

	@Test
	public void testUnassignProjectStatus(){
		Company c = new Company("Test");
		Set<Qualification> quals = new  HashSet<>();

		Qualification qual = new Qualification("Python");
		quals.add(qual);


		c.createQualification("Java");
		c.createQualification("Python");
		
		Worker w1 = c.createWorker("sarthak",quals,20000.00);

		ProjectSize ps = ProjectSize.BIG;
		ProjectSize small =  ProjectSize.SMALL;
		Project proj = c.createProject("proj", quals, ps);
		
		Project proj2 = c.createProject("proj1", quals, ps);
		
		Project proj3 = c.createProject("proj2", quals, small);
		
		Project proj4 = c.createProject("proj3", quals, small);

		c.assign(w1, proj);
		proj.setStatus(ProjectStatus.ACTIVE);		
		c.assign(w1, proj2);
		c.assign(w1, proj3);
		c.assign(w1, proj4);

		c.unassign(w1, proj);
		assertFalse(proj.getStatus().equals(ProjectStatus.ACTIVE));

	}

	@Test
	public void testAssignNullWorker(){
		Company c = new Company("Test");
		Set<Qualification> quals = new  HashSet<>();

		Qualification qual = new Qualification("Python");
		quals.add(qual);


		c.createQualification("Java");
		c.createQualification("Python");

		Worker w1 = c.createWorker("sarthak",quals,20000.00);

		ProjectSize ps = ProjectSize.BIG;
		ProjectSize small =  ProjectSize.SMALL;
		Project proj = c.createProject("proj", quals, ps);

		Project proj2 = c.createProject("proj1", quals, ps);

		Project proj3 = c.createProject("proj2", quals, small);

		Project proj4 = c.createProject("proj3", quals, small);

		Project proj5 = c.createProject("proj4", quals, ps);

		try{
			c.assign(null, proj);
		}
		catch(NullPointerException e){
			assertEquals("worker is null", e.getMessage());
		}

		try {
			c.assign(w1, null);
		}
		catch(NullPointerException e){
			assertEquals("project is null", e.getMessage());
		}

}






	@Test
	public void testUnassignAll() {
		Company c = new Company("Test1");
		Set<Qualification> quals = new  HashSet<>();

		Qualification qual = new Qualification("Python");
		quals.add(qual);


		c.createQualification("Java");
		c.createQualification("Python");
		
		Worker w1 = c.createWorker("Heidi",quals,20000.00);

		ProjectSize ps = ProjectSize.BIG;
		ProjectSize small =  ProjectSize.SMALL;
		Project proj = c.createProject("proj", quals, ps);
		
		Project proj2 = c.createProject("proj1", quals, ps);
		
		Project proj3 = c.createProject("proj2", quals, ps);
		
		Project proj4 = c.createProject("proj3", quals, small);

		c.assign(w1, proj);
		c.assign(w1, proj2);
		c.assign(w1, proj3);
		c.assign(w1, proj4);

		c.unassignAll(w1);
		assertEquals(true, w1.getProjects().isEmpty());
	}

	@Test
	public void testUnassignAllEmpty() {
		Company c = new Company("Test1");
		Set<Qualification> quals = new  HashSet<>();

		Qualification qual = new Qualification("Python");
		quals.add(qual);


		c.createQualification("Java");
		c.createQualification("Python");
		
		Worker w1 = c.createWorker("Heidi",quals,20000.00);
		try{
			c.unassignAll(w1);
		}
		catch(IllegalArgumentException e){
			assertEquals("Worker has no assigned projects", e.getMessage());
		}
	}

	@Test
    public void finishProject_UpdatesStatusToFinished() {
		Company c = new Company("Test1");
		Set<Qualification> quals = new  HashSet<>();
		Set<Qualification> qualss = new  HashSet<>();

		Qualification qual = new Qualification("Python");
		Qualification quali = new Qualification("Java");

		quals.add(qual);
		quals.add(quali);

		qualss.add(quali);
		qualss.add(qual);

		c.createQualification("Java");
		c.createQualification("Python");
		
		Worker w1 = c.createWorker("Heidi",quals,20000.00);
		Worker w2 = c.createWorker("Bob",qualss,20000.00);
		ProjectSize ps = ProjectSize.BIG;
		ProjectSize small =  ProjectSize.SMALL;
		Project proj = c.createProject("proj", quals, ps);

		c.assign(w1, proj);
	//	c.assign(w2, proj);


		c.start(proj);
 		c.finish(proj);
	
        assertEquals(ProjectStatus.FINISHED, proj.getStatus());
    }
	
	@Test
    public void finishProject_MakesAllAssignedWorkersAvailable() {
		Company c = new Company("Test1");
		Set<Qualification> quals = new  HashSet<>();
		Set<Qualification> qualss = new  HashSet<>();

		Qualification qual = new Qualification("Python");
		Qualification quali = new Qualification("Java");

		quals.add(qual);
		quals.add(quali);

		qualss.add(quali);
		qualss.add(qual);

		c.createQualification("Java");
		c.createQualification("Python");
		
		Worker w1 = c.createWorker("Heidi",quals,20000.00);
		Worker w2 = c.createWorker("Bob",qualss,20000.00);
		ProjectSize ps = ProjectSize.BIG;
		ProjectSize small =  ProjectSize.SMALL;
		Project proj = c.createProject("proj", quals, ps);

		c.assign(w1, proj);
		//c.assign(w2, proj);

		c.start(proj);
        c.finish(proj);
        assertTrue("All workers assigned to the finished project should be made available.", (c.getAvailableWorkers().contains(w1)));
    }

	@Test
    public void finishProject_WithNullProject_ThrowsNullPointerException() {
		Company c = new Company("Test1");
		Set<Qualification> quals = new  HashSet<>();
		Set<Qualification> qualss = new  HashSet<>();

		Qualification qual = new Qualification("Python");
		Qualification quali = new Qualification("Java");

		quals.add(qual);
		quals.add(quali);

		qualss.add(quali);
		qualss.add(qual);

		c.createQualification("Java");
		c.createQualification("Python");
		
		Worker w1 = c.createWorker("Heidi",quals,20000.00);
		Worker w2 = c.createWorker("Bob",qualss,20000.00);
		ProjectSize ps = ProjectSize.BIG;
		ProjectSize small =  ProjectSize.SMALL;
		Project proj = c.createProject("proj", quals, ps);

		c.assign(w1, proj);

		c.start(proj);
		Exception exception = null;
		try {
			c.finish(null);
		} catch (NullPointerException e) {
			exception = e;
		}
		assertNotNull( "Finishing a null project should throw NullPointerException.", exception);
    }

    @Test
    public void finishProject_NotActiveProject_ThrowsIllegalArgumentException() {
		Company c = new Company("Test1");
		Set<Qualification> quals = new  HashSet<>();
		Set<Qualification> qualss = new  HashSet<>();

		Qualification qual = new Qualification("Python");
		Qualification quali = new Qualification("Java");

		quals.add(qual);
		quals.add(quali);

		qualss.add(quali);
		qualss.add(qual);

		c.createQualification("Java");
		c.createQualification("Python");
		
		Worker w1 = c.createWorker("Heidi",quals,20000.00);
		Worker w2 = c.createWorker("Bob",qualss,20000.00);
		ProjectSize ps = ProjectSize.BIG;
		ProjectSize small =  ProjectSize.SMALL;
		Project proj = c.createProject("proj", quals, ps);

		c.assign(w1, proj);
      //  c.assign(worker2, project);

		c.start(proj);

		// Reset project status to mimic non-active project
		proj.setStatus(ProjectStatus.SUSPENDED);
		
		Exception exception = null;
		try {
			c.finish(proj);
		} catch (IllegalArgumentException e) {
			exception = e;
		}
		assertNotNull( "Trying to finish a project that is not ACTIVE should throw IllegalArgumentException.", exception);
    }

	@Test
	public void testToString(){
		Company c = new Company("Test1");
		assertEquals("Test1:0:0", c.toString());
	}

	@Test
	public void testGetProjects(){
		Company c = new Company("Test1");
		Set<Qualification> quals = new  HashSet<>();
		Set<Qualification> qualss = new  HashSet<>();

		Qualification qual = new Qualification("Python");
		Qualification quali = new Qualification("Java");

		quals.add(qual);
		quals.add(quali);

		qualss.add(quali);
		qualss.add(qual);

		c.createQualification("Java");
		c.createQualification("Python");

		Worker w1 = c.createWorker("Heidi",quals,20000.00);
		Worker w2 = c.createWorker("Bob",qualss,20000.00);
		ProjectSize ps = ProjectSize.BIG;
		ProjectSize small =  ProjectSize.SMALL;
		Project proj = c.createProject("proj", quals, ps);
		Project proj2 = c.createProject("proj1", quals, ps);
		Project proj3 = c.createProject("proj2", quals, small);
		Project proj4 = c.createProject("proj3", quals, small);
		Project proj5 = c.createProject("proj4", quals, ps);

		c.assign(w1, proj);
		c.assign(w1, proj2);
		c.assign(w1, proj3);
		c.assign(w1, proj4);
		c.assign(w1, proj5);
		assertEquals(5, c.getProjects().size());
	}

	@Test
	public void testNewQualificition(){
		Company c = new Company("Test1");
		Qualification q = c.createQualification("Java");
		Qualification x = c.createQualification("Java");
		assertEquals(null,x);

	}

	@Test 
	public void emptySetGetUnassignedWorkers(){
		Company company = new Company("Test");
		Set<Qualification> quals = new  HashSet<>();
		Qualification qual = new Qualification("Java");
		quals.add(qual);

		company.createQualification("Java");
		
		Worker w1 = company.createWorker("sarthak",quals,20000.00);
		Worker w2 = company.createWorker("tegan",quals,20000.00);

		ProjectSize ps = ProjectSize.BIG;
		Project proj = company.createProject("proj", quals, ps);

		company.assign(w1, proj);
		assertTrue(company.getUnassignedWorkers().contains(w2));
	}

	@Test 
	public void hashCodeCoverage(){
		Company comp = new Company("Banana");
		comp.createQualification("Java");
		comp.createQualification("Python");

		assertEquals(674081182, comp.hashCode());
	}


}
