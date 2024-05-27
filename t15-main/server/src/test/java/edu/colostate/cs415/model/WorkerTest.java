package edu.colostate.cs415.model;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

import edu.colostate.cs415.dto.WorkerDTO;
import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.model.Worker;
import org.junit.rules.ExpectedException;


public class WorkerTest {
	@Test
	public void test() {
		assert (true);
	}

	@Test 
	public void workerConstructorWithNull(){
		try{
			String name = null;
			Qualification qual = new Qualification("Thrifty");
			Set<Qualification> quali = new HashSet<Qualification>();
			quali.add(qual);
			double salary = 30000;
			Worker work = new Worker(name, quali, salary);
		} catch (NullPointerException e){
			assertEquals("Invalid null", e.getMessage());
		}
	}

	@Test 
	public void workerConstructorWithNullQual(){
		try{
			String name = "Jim";
			Set<Qualification> quali = null;
			double salary = 30000;
			Worker work = new Worker(name, quali, salary);
		} catch (NullPointerException e){
			assertEquals("Invalid null", e.getMessage());
		}
	}

	@Test
	public void testCapitalizedFirstLetter() {

		String name = "Mark";
		Qualification qual = new Qualification("Thrifty");
		Set<Qualification> quali = new HashSet<Qualification>();
		quali.add(qual);
		double salary = 30000;
		Worker work = new Worker(name, quali, salary);

		assertEquals(work.getName().toString(), name);
	}

	@Test
	public void testAddQualification() {
		Qualification qual = new Qualification("Thrifty");
		Qualification test = new Qualification("Smart");
		Set<Qualification> quali = new HashSet<Qualification>();
		quali.add(qual);
		quali.add(test);

		Worker work = new Worker("Worker", quali, 40.00);
		assertEquals(work.getQualifications(), quali);
	}

	@Test
	public void testAddNewQualification() {
		Qualification qual = new Qualification("Thrifty");
		Qualification test = new Qualification("Smart");
		Set<Qualification> quali = new HashSet<Qualification>();
		quali.add(qual);
		quali.add(test);

		Worker work = new Worker("Worker", quali, 40.00);
		Qualification qual2 = new Qualification("Java");
		work.addQualification(qual2);

		assertEquals(work.getQualifications().size(), 3);
	}

	@Test
	public void testDuplicateQualification() {
		Qualification qual = new Qualification("Thrifty");
		Qualification test = new Qualification("Smart");
		Set<Qualification> quali = new HashSet<Qualification>();
		quali.add(qual);
		quali.add(test);

		Worker work = new Worker("Worker", quali, 40.00);
		try {
			work.addQualification(test);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid qualification", e.getMessage());
		}
	}

	@Test
	public void testAddQualificationNull() {
		Qualification qual = new Qualification("Thrifty");
		Qualification test = null;
		Set<Qualification> quali = new HashSet<Qualification>();
		quali.add(qual);
		quali.add(test);

		Worker work = new Worker("Worker", quali, 40.00);
		try {
			work.addQualification(test);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid qualification", e.getMessage());
		}
	}



	@Test
	public void testGetName() {

		Set<Qualification> qualifications = new HashSet<>();
		Worker worker = new Worker("Sarthak", qualifications, 40000.0);
		assertEquals("Sarthak", worker.getName());

	}

	@Test
	public void testGetname_1() {

		Set<Qualification> qualifications = new HashSet<>();
		Worker worker = new Worker("Bharadwaj", qualifications, 40000.0);
		assertFalse("Failed", worker.getName() == "Sarthak");

	}


	@Test
	public void testGetSalary() {

		Set<Qualification> qualifications = new HashSet<>();
		Worker worker = new Worker("Bharadwaj", qualifications, 40000.0);
		assertEquals(40000.0, worker.getSalary(), 0.0001);

	}


	@Test
	public void testGetSalary_1() {

		Set<Qualification> qualifications = new HashSet<>();
		Worker worker = new Worker("Bharadwaj", qualifications, 40000.0);
		assertFalse("Failed", worker.getSalary() == 90000.0);

	}

	@Test
	public void testSetSalary() {

		Set<Qualification> qualifications = new HashSet<>();
		Worker worker = new Worker("Bharadwaj", qualifications, 40000.0);
		worker.setSalary(40000.0);
		assertFalse("Failed", worker.getSalary() == 90000.0);

	}


	@Test
	public void testSetSalary_1() {

		Set<Qualification> qualifications = new HashSet<>();
		Worker worker = new Worker("Bharadwaj", qualifications, 40000.0);
		worker.setSalary(40000.0);
		assertEquals(40000.0, worker.getSalary(), 0.0001);

	}

	@Test
	public void testhashCode() {
		String name = "Bob";
		String name2 = "Tim";
		Set<Qualification> quali = new HashSet<>();
		Qualification qual = new Qualification("Thrifty");
		quali.add(qual);

		Worker work1 = new Worker(name, quali, 4000.00);
		Worker work2 = new Worker(name2, quali, 4000.00);

		assertFalse(work1.getName().hashCode() == work2.getName().hashCode());


	}


	@Test
	public void testAddProjectEqualsSize() {
		Set<Qualification> qualifications = new HashSet<>();
		Qualification qualification = new Qualification("Thrifty");
		qualifications.add(qualification);

		Worker worker = new Worker("Sarthak", qualifications, 40000.0);
		Project project = new Project("Project1", qualifications, ProjectSize.BIG);
		worker.addProject(project);
		assertEquals(worker.getProjects().size(), 1);
	}

	@Test
	public void testAddProjectNotEqualsSize() {
		Set<Qualification> qualifications = new HashSet<>();
		Qualification qualification = new Qualification("Thrifty");
		qualifications.add(qualification);

		Worker worker = new Worker("Sarthak", qualifications, 40000.0);
		Project project = new Project("Project1", qualifications, ProjectSize.BIG);
		worker.addProject(project);
		assertFalse(worker.getProjects().size() == 0);
	}

	@Test
	public void testAddProjectNull() {
		Set<Qualification> qualifications = new HashSet<>();
		Qualification qualification = new Qualification("Thrifty");
		qualifications.add(qualification);

		Worker worker = new Worker("Sarthak", qualifications, 40000.0);
		Project project = null;
		try {
			worker.addProject(project);
		} catch (NullPointerException e) {
			assertEquals("Invalid project", e.getMessage());
		}
	}


	@Test
	public void testWorkerEqualsFalse() {
		String name = "Bob";
		String name2 = "Tim";
		Set<Qualification> quali = new HashSet<>();
		Qualification qual = new Qualification("Thrifty");
		quali.add(qual);

		Worker work1 = new Worker(name, quali, 4000.00);
		Worker work2 = new Worker(name2, quali, 4000.00);

		assertFalse(work1.equals(work2));
	}

	@Test
	public void testWorkerEqualsTrue() {
		String name = "Bob";
		Set<Qualification> quali = new HashSet<>();
		Qualification qual = new Qualification("Thrifty");
		quali.add(qual);

		Worker work1 = new Worker(name, quali, 4000.00);
		Worker work2 = new Worker(name, quali, 4000.00);

		assertTrue(work1.equals(work2));
	}

	@Test
	public void testWorkerEqualsFalseNull() {
		String name = "Bob";
		Set<Qualification> quali = new HashSet<>();
		Qualification qual = new Qualification("Thrifty");
		quali.add(qual);

		Worker work1 = new Worker(name, quali, 4000.00);
		Worker work2 = null;

		assertFalse(work1.equals(work2));
	}

	@Test
	public void testWorkerEqualsFalseDifferentClass() {
		String name = "Bob";
		Set<Qualification> quali = new HashSet<>();
		Qualification qual = new Qualification("Thrifty");
		quali.add(qual);

		Worker work1 = new Worker(name, quali, 4000.00);
		String work2 = "Bob";

		assertFalse(work1.equals(work2));
	}

	@Test
	public void testWorkerEqualsFalseNonWorkerObject() {
		String name = "Bob";
		Set<Qualification> quali = new HashSet<>();
		Qualification qual = new Qualification("Thrifty");
		quali.add(qual);

		Worker work1 = new Worker(name, quali, 4000.00);
		Qualification work2 = new Qualification("Bob");

		assertFalse(work1.equals(work2));

	}

	@Test
	public void testWorkerToString(){
		String name = "Bob";
		Set<Qualification> quali = new HashSet<>();
		Qualification qual = new Qualification("Thrifty");
		quali.add(qual);

		Worker work1 = new Worker(name, quali, 4000.00);
		assertEquals("Bob:0:1:4000", work1.toString());
	}

	@Test
	public void testWorkerToStringMultipleQualifications(){
		String name = "Bob";
		Set<Qualification> quali = new HashSet<>();
		Qualification qual = new Qualification("Thrifty");
		Qualification qual2 = new Qualification("Smart");
		quali.add(qual);
		quali.add(qual2);

		Worker work1 = new Worker(name, quali, 4000.00);
		assertEquals("Bob:0:2:4000", work1.toString());
	}

	@Test
	public void testWorkerToStringMultipleProjects(){
		String name = "Bob";
		Set<Qualification> quali = new HashSet<>();
		Qualification qual = new Qualification("Thrifty");
		quali.add(qual);
		Project project = new Project("Project1",quali, ProjectSize.BIG);
		Project project2 = new Project("Project2",quali, ProjectSize.BIG);
		Worker work1 = new Worker(name, quali, 4000.00);
		work1.addProject(project);
		work1.addProject(project2);
		assertEquals("Bob:2:1:4000", work1.toString());
	}


	@Test
	public void testWorkerToStringMultipleProjectsAndQualifications(){
		String name = "Bob";
		Set<Qualification> quali = new HashSet<>();
		Qualification qual = new Qualification("Thrifty");
		Qualification qual2 = new Qualification("Smart");
		quali.add(qual);
		quali.add(qual2);
		Project project = new Project("Project1",quali, ProjectSize.BIG);
		Project project2 = new Project("Project2",quali, ProjectSize.BIG);
		Worker work1 = new Worker(name, quali, 4000.00);
		work1.addProject(project);
		work1.addProject(project2);
		assertEquals("Bob:2:2:4000", work1.toString());
	}

  @Test  
	public void testWorkerRemoveProject(){
		Set<Qualification> qualifications = new HashSet<>();
		Qualification qualification = new Qualification("Thrifty");
		qualifications.add(qualification);

		Worker worker = new Worker("Sarthak", qualifications, 40000.0);
		Project project = new Project("Project1",qualifications, ProjectSize.BIG);
		worker.addProject(project);
		assertEquals(worker.getProjects().size(), 1);
		worker.removeProject(project);
		assertEquals(worker.getProjects().size(), 0);
	}

	@Test
	public void testWorkerRemoveProjectNotInWorker(){
		Set<Qualification> qualifications = new HashSet<>();
		Qualification qualification = new Qualification("Thrifty");
		qualifications.add(qualification);

		Worker worker = new Worker("Kelsey", qualifications, 40000.0);
		Project project = new Project("Project1",qualifications, ProjectSize.BIG);
		Project project2 = new Project("Project2",qualifications, ProjectSize.BIG);

		worker.addProject(project);
		assertEquals(worker.getProjects().size(), 1);
		worker.removeProject(project2);
		assertEquals(worker.getProjects().size(), 1);

	}
    
  @Test
	public void testWorkerRemoveProjectNull(){
		Set<Qualification> qualifications = new HashSet<>();
		Qualification qualification = new Qualification("Thrifty");
		qualifications.add(qualification);

		Worker worker = new Worker("Kelsey", qualifications, 40000.0);
		Project project = new Project("Project1",qualifications, ProjectSize.BIG);
		Project project2 = null;

		worker.addProject(project);
		assertEquals(worker.getProjects().size(), 1);

		try {
			worker.removeProject(project2);
		} catch (NullPointerException e) {
			assertEquals("Invalid project", e.getMessage());
		}
	}

  @Test
	public void testWorkerGetProjectsEqualTrue() {
		Set<Qualification> quali = new HashSet<>();
		Qualification qual = new Qualification("Thrifty");
		quali.add(qual);
		Worker worker = new Worker("Bob", quali, 40000.0);
		Project project = new Project("Project1", quali, ProjectSize.BIG);
		worker.addProject(project);
		assertEquals(worker.getProjects().size(), 1);
	}

	@Test
	public void testWorkerGetProjectsEqualsFalse() {
		Set<Qualification> quali = new HashSet<>();
		Qualification qual = new Qualification("Thrifty");
		quali.add(qual);
		Worker worker = new Worker("Bob", quali, 40000.0);
		Project project = new Project("Project1", quali, ProjectSize.BIG);
		worker.addProject(project);
		assertFalse(worker.getProjects().size() == 0);

	}

	@Test
	public void testGetAvailableWorkersEmpty() {
		Company company = new Company("Company");
		assertTrue(company.getAvailableWorkers().isEmpty());
	}

	@Test
	public void testGetAvailableWorkers() {
		Company company = new Company("Company");
		Set<Qualification> quali = new HashSet<>();
		Qualification qual = new Qualification("Thrifty");
		Qualification qual2 = new Qualification("Smart");
		Qualification qual3 = new Qualification("Java");
		quali.add(qual3);
		quali.add(qual2);
		quali.add(qual);

		Worker worker = new Worker("Bob", quali, 40000.0);
		for (Qualification q : quali) {
			company.createQualification(q.toString());
		}

		assertEquals(company.getQualifications().size(), 3);
		assertEquals(company.getAvailableWorkers().size(), 0);
		company.createWorker(worker.getName(), worker.getQualifications(), worker.getSalary());
		assertEquals(company.getAvailableWorkers().size(), 1);
		assertFalse(company.getAvailableWorkers().isEmpty());
	}

	@Test
	public void testGetAvailableWorkersMultiple() {
		Company company = new Company("Company");
		Set<Qualification> quali = new HashSet<>();
		Qualification qual = new Qualification("Thrifty");
		Qualification qual2 = new Qualification("Smart");
		Qualification qual3 = new Qualification("Java");
		quali.add(qual3);
		quali.add(qual2);
		quali.add(qual);

		Worker worker = new Worker("Bob", quali, 40000.0);
		Worker worker2 = new Worker("Tim", quali, 40000.0);
		for (Qualification q : quali) {
			company.createQualification(q.toString());
		}

		assertEquals(company.getQualifications().size(), 3);
		assertEquals(company.getAvailableWorkers().size(), 0);
		company.createWorker(worker.getName(), worker.getQualifications(), worker.getSalary());
		company.createWorker(worker2.getName(), worker2.getQualifications(), worker2.getSalary());
		assertEquals(company.getAvailableWorkers().size(), 2);
		assertFalse(company.getAvailableWorkers().isEmpty());
	}

	@Test
	public void testGetWorkload() {
		Set<Qualification> qual = new HashSet<>();
		Qualification qual2 = new Qualification("Hard");
		qual.add(qual2);
		Worker worker = new Worker("Heidi", qual, 40000.0);
		Project project = new Project("Project", qual, ProjectSize.BIG);
		worker.addProject(project);
		assertEquals(worker.getWorkload(), 3);

	}

	@Test 
	public void testIsAvailableTrue() {
		Set<Qualification> qual = new HashSet<>();
		Qualification qual2 = new Qualification("Hard");
		qual.add(qual2);
		Worker worker = new Worker("Heidi", qual, 40000.0);
		Project project = new Project("Project", qual, ProjectSize.BIG);
		worker.addProject(project);
		assertEquals(worker.isAvailable(), true);
	}
	@Test 
	public void testIsAvailableFalse() {
		Set<Qualification> qual = new HashSet<>();
		Qualification qual2 = new Qualification("Hard");
		qual.add(qual2);
		Worker worker = new Worker("Heidi", qual, 40000.0);
		Project project = new Project("Project1", qual, ProjectSize.BIG);
		worker.addProject(project);
		Project project2 = new Project("Project2", qual, ProjectSize.BIG);
		worker.addProject(project2);
		Project project3 = new Project("Project3", qual, ProjectSize.BIG);
		worker.addProject(project3);
		Project project4 = new Project("Project4", qual, ProjectSize.BIG);
		worker.addProject(project4);
		assertEquals(worker.isAvailable(), false);
	}

	@Test
	public void testWillOverload(){
		Set<Qualification> qual = new HashSet<>();
		Qualification qual2 = new Qualification("Java");
		qual.add(qual2);
		Worker worker = new Worker("joe", qual, 40000.0);
		Project project = new Project("Project1", qual, ProjectSize.BIG);
		worker.addProject(project);
		Project project2 = new Project("Project2", qual, ProjectSize.BIG);
		worker.addProject(project2);
		Project project3 = new Project("Project3", qual, ProjectSize.BIG);
		worker.addProject(project3);
		Project project4 = new Project("Project4", qual, ProjectSize.BIG);
		assertFalse(worker.willOverload(project4));

	}

	@Test
	public void testWillOverloadTrue(){
		Set<Qualification> qual = new HashSet<>();
		Qualification qual2 = new Qualification("Java");
		qual.add(qual2);
		Worker worker = new Worker("joe", qual, 40000.0);
		Project project = new Project("Project1", qual, ProjectSize.BIG);
		worker.addProject(project);
		Project project2 = new Project("Project2", qual, ProjectSize.BIG);
		worker.addProject(project2);
		Project project3 = new Project("Project3", qual, ProjectSize.BIG);
		worker.addProject(project3);
		Project project4 = new Project("Project4", qual, ProjectSize.BIG);
		worker.addProject(project4);
		Project project5 = new Project("Project5", qual, ProjectSize.BIG);
		assertTrue(worker.willOverload(project5));

	}

	@Test
	public void testWillOverloadAlreadyOnProject(){
		Set<Qualification> qual = new HashSet<>();
		Qualification qual2 = new Qualification("Java");
		Company comp = new Company("comp");
		qual.add(qual2);
		Worker worker = new Worker("joe", qual, 40000.0);
		Project project = new Project("Project1", qual, ProjectSize.BIG);
		comp.assign(worker, project);
		Project project2 = new Project("Project2", qual, ProjectSize.BIG);
		comp.assign(worker, project2);
		Project project3 = new Project("Project3", qual, ProjectSize.BIG);
		comp.assign(worker, project3);
		Project project4 = new Project("Project4", qual, ProjectSize.BIG);
		comp.assign(worker, project4);
		assertFalse(worker.willOverload(project4));

	}
	@Test
	public void testWillOverloadNull(){
		Set<Qualification> qual = new HashSet<>();
		Qualification qual2 = new Qualification("Java");
		qual.add(qual2);
		Worker worker = new Worker("joe", qual, 40000.0);
		Project project = new Project("Project1", qual, ProjectSize.BIG);
		worker.addProject(project);

		try {
			worker.willOverload(null);
		} catch (NullPointerException e) {
			assertEquals("Invalid project", e.getMessage());
		}

	}

	@Test
    public void testToDTOWithProjectsAndQualifications() {
        String workerName = "John Doe";
        double salary = 50000.0;
        Set<Project> projects = new HashSet<>();
        Set<Qualification> qualifications = new HashSet<>();
		qualifications.add(new Qualification("Qualification 1"));
        // qualifications.add(new Qualification("Qualification 2"));
        projects.add(new Project("Project 1", qualifications, ProjectSize.BIG));
        projects.add(new Project("Project 2", qualifications, ProjectSize.BIG));

        
        Worker worker = new Worker(workerName, qualifications, salary);
        projects.forEach(worker::addProject); 

        WorkerDTO dto = worker.toDTO();

        assertEquals(workerName, dto.getName());
		assertEquals(50000.0, dto.getSalary(), 0.001); 
        assertArrayEquals(new String[]{"Project 1", "Project 2"}, dto.getProjects());
        assertArrayEquals(new String[]{"Qualification 1"}, dto.getQualifications());

        assertTrue(dto.getWorkload() >= 0); 
    }
    
    @Test
    public void testToDTOWithEmptyProjectsAndQualifications() {
        Worker worker = new Worker("Jane Doe", new HashSet<>(), 60000.0);

        WorkerDTO dto = worker.toDTO();

        assertEquals("Jane Doe", dto.getName());
		assertEquals(60000.0, dto.getSalary(), 0.001); 
        assertArrayEquals(new String[]{}, dto.getProjects());
        assertArrayEquals(new String[]{}, dto.getQualifications());
        assertEquals(0, dto.getWorkload());
    }



}
