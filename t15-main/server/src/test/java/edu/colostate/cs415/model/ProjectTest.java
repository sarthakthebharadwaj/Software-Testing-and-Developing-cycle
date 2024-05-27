package edu.colostate.cs415.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import edu.colostate.cs415.dto.ProjectDTO;
import edu.colostate.cs415.model.ProjectSize;


public class ProjectTest {
	@Test
	public void happyPathForProjectConstructor() {
		Set<Qualification> qual = new HashSet();
		Qualification testqual = new Qualification("Java");
		ProjectSize projsize = ProjectSize.SMALL;
		qual.add(testqual);
		String testname = "testname";
		Project proj = new Project(testname, qual, projsize);


		assertEquals(testname, proj.getName());
		assertEquals(projsize, proj.getSize());
		assertEquals(qual, proj.getRequiredQualifications());
		assertEquals(proj.getStatus(), ProjectStatus.PLANNED);
	}

	@Test
	public void testSetNameisNull() {
		try{
			Set<Qualification> qual = new HashSet();
			Qualification testqual = new Qualification("Java");
			ProjectSize projsize = ProjectSize.SMALL;
			qual.add(testqual);
			String testname = null;
			Project proj = new Project(testname, qual, projsize);

		} 
		catch (NullPointerException e) {
			assertEquals("Invalid input of null", e.getMessage());
		}
	}

	@Test
	public void testSetNameisLength0() {
		try{
			Set<Qualification> qual = new HashSet();
			Qualification testqual = new Qualification("Java");
			ProjectSize projsize = ProjectSize.SMALL;
			qual.add(testqual);
			String testname = "";
			Project proj = new Project(testname, qual, projsize);

		} 
		catch (IllegalArgumentException e) {
			assertEquals("Invalid input", e.getMessage());
		}
	}

	@Test
	public void testSetQualificationsNull() {
		try{
			Set<Qualification> qual = null;
			Qualification testqual = new Qualification("Java");
			ProjectSize projsize = ProjectSize.SMALL;
			String testname = "testname";
			Project proj = new Project(testname, qual, projsize);
		} 
		catch (NullPointerException e) {
			assertEquals("Invalid input of null", e.getMessage());
		}
	}

	@Test 
	public void testSetQualificationsIsEmpty(){
		try{
			Set<Qualification> qual = new HashSet();;
			ProjectSize projsize = ProjectSize.SMALL;
			String testname = "testname";
			Project proj = new Project(testname, qual, projsize);
		} 
		catch (IllegalArgumentException e) {
			assertEquals("Invalid input", e.getMessage());
		}
	}

	@Test 
	public void testSetProjectSizeIsNotNull(){
		try{
			Set<Qualification> qual = new HashSet();
			ProjectSize projsize = null;
			String testname = "testname";
			Project proj = new Project(testname, qual, projsize);
		} 
		catch (NullPointerException e) {
			assertEquals("Invalid input of null", e.getMessage());
		}
	}
	@Test
	public void testHashCode(){
    String a = "Foo bar";
    String b = "Foo bar";
    String c = "bar foo";
	ProjectSize projSize = ProjectSize.SMALL;
	Qualification quali = new Qualification("beans");
	Set<Qualification> qual = new HashSet();
	qual.add(quali);
	Project proj = new Project(a, qual, projSize);
	assertTrue(proj.hashCode() == a.hashCode());
	assertTrue(proj.hashCode() != c.hashCode());


	}

	@Test
	public void testMissingQualificationsWithMoreThanOneQualifciaton(){
		//create qualification set
		Qualification qual1 = new Qualification("Software Engineering");
		Qualification qual2 = new Qualification("Java");
		Qualification qual3 = new Qualification("Python");

	
    	Set<Qualification> qualifications = new HashSet<>();
		Set<Qualification> qualifications2 = new HashSet<>();
	
    	qualifications.add(qual1);
		qualifications.add(qual2);
	
		qualifications2.add(qual1);
		qualifications2.add(qual3);
	
	
		//create worker set
		Worker w1 = new Worker("Bill", qualifications, 20000);
		Worker w2 = new Worker("Maria", qualifications2, 100000);
	
		ProjectSize projsize = ProjectSize.BIG;
		Project proj = new Project("testproj", qualifications, projsize);
	
		proj.addWorker(w1);
		proj.addWorker(w2);
	
		Set<Qualification> missing = proj.getMissingQualifications();
	
		Set<Qualification> empty = new HashSet<>();	
	
		assertEquals(empty, missing);
	}

	@Test
	public void testMissingQualificationsWithQualsMissingAndMoreThanOneQualifcation(){
		//create qualification set
		Qualification qual1 = new Qualification("Software Engineering");
		Qualification qual2 = new Qualification("Java");
		Qualification qual3 = new Qualification("Python");
		Qualification qual4 = new Qualification("Ruby");

		//has Sotfware Engineering Java and Ruby
    	Set<Qualification> qualifications = new HashSet<>();
		//has Sotfware Engineering and Python
		Set<Qualification> qualifications2 = new HashSet<>();
	
    	qualifications.add(qual1);
		qualifications.add(qual2);
		qualifications.add(qual4);
	
		qualifications2.add(qual1);
		qualifications2.add(qual3);
	
	
		//create worker set
		Set<Worker> workers = new HashSet<>();
		Worker w1 = new Worker("Bill", qualifications2, 20000);
		Worker w2 = new Worker("Maria", qualifications2, 100000);
	
		workers.add(w1);
		workers.add(w2);
	
		ProjectSize projsize = ProjectSize.BIG;
		Project proj = new Project("testproj", qualifications, projsize);
	
		proj.addWorker(w1);
		proj.addWorker(w2);
	
		Set<Qualification> missing = proj.getMissingQualifications();
	
		Set<Qualification> nonempty = new HashSet<>();	
		nonempty.add(qual2);
		nonempty.add(qual4);
	
		assertEquals(nonempty, missing);
	}

	@Test
	public void missingQualificationWhenQualificationsisZero(){
		//create qualification set
		Qualification qual1 = new Qualification("Software Engineering");
		Qualification qual2 = new Qualification("Java");
	
    	Set<Qualification> qualifications = new HashSet<>();
		Set<Qualification> qualifications2 = new HashSet<>();
	
    	qualifications.add(qual1);
		qualifications.add(qual2);
	
	
		//create worker set with no qualifications
		Set<Worker> workers = new HashSet<>();
		Worker w1 = new Worker("Bill", qualifications2, 20000);
		Worker w2 = new Worker("Maria", qualifications2, 100000);
	
		workers.add(w1);
		workers.add(w2);
	
		ProjectSize projsize = ProjectSize.BIG;
		Project proj = new Project("testproj", qualifications, projsize);
	
		proj.addWorker(w1);
		proj.addWorker(w2);
		Set<Qualification> missing = proj.getMissingQualifications();
	
		Set<Qualification> nonempty = new HashSet<>();	
		nonempty.add(qual2);
		nonempty.add(qual1);

	
		assertEquals(nonempty, missing);
	}

	@Test
	public void missingQualificationWhenQualificationsisOne(){
		//create qualification set
		Qualification qual1 = new Qualification("Software Engineering");
		Qualification qual2 = new Qualification("Java");
		Qualification qual3 = new Qualification("Python");

	
    	Set<Qualification> qualifications = new HashSet<>();
		Set<Qualification> qualifications2 = new HashSet<>();
		Set<Qualification> qualifications3 = new HashSet<>();

	
    	qualifications.add(qual1);
		qualifications.add(qual2);

		qualifications2.add(qual3);
	
	
		//create worker set with no qualifications
		Set<Worker> workers = new HashSet<>();
		Worker w1 = new Worker("Bill", qualifications2, 20000);
		Worker w2 = new Worker("Maria", qualifications3, 100000);
	
		workers.add(w1);
		workers.add(w2);
	
		ProjectSize projsize = ProjectSize.BIG;
		Project proj = new Project("testproj", qualifications, projsize);
	
		proj.addWorker(w1);
		proj.addWorker(w2);
		Set<Qualification> missing = proj.getMissingQualifications();
	
		Set<Qualification> nonempty = new HashSet<>();	
		nonempty.add(qual2);
		nonempty.add(qual1);

	
		assertEquals(nonempty, missing);
	}

	@Test
	public void testAddQualificationsWithDuplication(){
		//create qualification set
		Qualification qual1 = new Qualification("Software Engineering");
		Qualification qual2 = new Qualification("Java");
		Qualification qual3 = new Qualification("Python");

	
    	Set<Qualification> qualifications = new HashSet<>();
		Set<Qualification> qualifications2 = new HashSet<>();

	
    	qualifications.add(qual1);
		qualifications.add(qual2);

		qualifications2.add(qual3);
	
	
		//create worker set with no qualifications
		Set<Worker> workers = new HashSet<>();
		Worker w1 = new Worker("Bill", qualifications2, 20000);
		Worker w2 = new Worker("Maria", qualifications2, 100000);
	
		workers.add(w1);
		workers.add(w2);
	
		ProjectSize projsize = ProjectSize.BIG;
		Project proj = new Project("testproj", qualifications, projsize);
	
		proj.addWorker(w1);
		proj.addWorker(w2);
		try{
			proj.addQualification(qual1);
		}
		catch(IllegalArgumentException e){
			assertEquals("Invalid Input", e.getMessage());
		}

	}

	@Test
	public void testAddQualificationsWithNull(){
		//create qualification set
		Qualification qual1 = new Qualification("Software Engineering");
		Qualification qual2 = new Qualification("Java");
		Qualification qual3 = new Qualification("Python");

	
    	Set<Qualification> qualifications = new HashSet<>();
		Set<Qualification> qualifications2 = new HashSet<>();

	
    	qualifications.add(qual1);
		qualifications.add(qual2);

		qualifications2.add(qual3);
	
	
		//create worker set with no qualifications
		Set<Worker> workers = new HashSet<>();
		Worker w1 = new Worker("Bill", qualifications2, 20000);
		Worker w2 = new Worker("Maria", qualifications2, 100000);
	
		workers.add(w1);
		workers.add(w2);
	
		ProjectSize projsize = ProjectSize.BIG;
		Project proj = new Project("testproj", qualifications, projsize);
	
		proj.addWorker(w1);
		proj.addWorker(w2);
		try{
			proj.addQualification(null);
		}
		catch(NullPointerException e){
			assertEquals(null, e.getMessage());
		}

	}

	@Test
	public void testAddNewQualifications(){
		//create qualification set
		Qualification qual1 = new Qualification("Software Engineering");
		Qualification qual2 = new Qualification("Java");
		Qualification qual3 = new Qualification("Python");


		Set<Qualification> qualifications = new HashSet<>();
		Set<Qualification> qualifications2 = new HashSet<>();


		qualifications.add(qual1);
		qualifications.add(qual2);

		qualifications2.add(qual3);


		Set<Worker> workers = new HashSet<>();
		Worker w1 = new Worker("Bill", qualifications2, 20000);
		Worker w2 = new Worker("Maria", qualifications2, 100000);

		workers.add(w1);
		workers.add(w2);

		ProjectSize projsize = ProjectSize.BIG;
		Project proj = new Project("testproj", qualifications, projsize);

		proj.addWorker(w1);
		proj.addWorker(w2);
		proj.addQualification(qual3);
		Set<Qualification> qualset = proj.getMissingQualifications();
		assertEquals(qualifications, qualset);
	}

	@Test
	public void testToString(){
		Set<Qualification> quals = new HashSet<>();
		quals.add(new Qualification("Java"));
		quals.add(new Qualification("Python"));

		ProjectSize s = ProjectSize.SMALL;

		Project proj = new Project("proj", quals, s);

		Worker worker = new Worker("Bill", quals, 20000.00);
		proj.addWorker(worker);

		String expected = "proj:1:PLANNED";
		assertEquals(expected, proj.toString());
	}

	@Test
	public void testToStringNull(){
		Set<Qualification> quals = new HashSet<>();
		quals.add(new Qualification("Java"));
		quals.add(new Qualification("Python"));

		ProjectSize s = ProjectSize.SMALL;

		Project proj = new Project("test", quals, s);

		try {
			proj.toString();
		}
		catch(NullPointerException e){
			assertEquals(null, e.getMessage());
		}
	}



	@Test
   public void toDTO(){
       Set<Qualification> quals = new HashSet<>();
       quals.add(new Qualification("Java"));
       quals.add(new Qualification("Python"));

	   Set<Qualification> quals2 = new HashSet<>();
       quals2.add(new Qualification("Ruby"));

       ProjectSize s = ProjectSize.SMALL;

       Project proj = new Project("proj", quals, s);

	    proj.setStatus(ProjectStatus.FINISHED);
	
		proj.addWorker(new Worker("Bill", quals2, 20000.00));
       proj.addWorker(new Worker("Jackie", quals2, 30000.00));


       ProjectDTO projDTO = proj.toDTO();

       assertEquals(proj.getName(), projDTO.getName());
       assertEquals(proj.getSize(), projDTO.getSize());

	   String[] missingQualifications = projDTO.getMissingQualifications();
		List<String> qualificationsList = Arrays.asList(missingQualifications);

	   for(Qualification q: proj.getMissingQualifications()){
			assertTrue(qualificationsList.contains(q.toString()));
	   }

	   String[] reqQualifications = projDTO.getQualifications();
		List<String> requireQualificationsList = Arrays.asList(reqQualifications);

	   for(Qualification q: proj.getRequiredQualifications()){
			assertTrue(requireQualificationsList.contains(q.toString()));
	   }
       assertEquals(proj.getStatus(), projDTO.getStatus());

    	String[] workers = projDTO.getWorkers();
		List<String> workersList = Arrays.asList(workers);

	   for(Worker w: proj.getWorkers()){
			assertTrue(workersList.contains(w.getName()));
	   }


   }

   @Test
  public void testEquals() {
	Set<Qualification> quals = new HashSet<>();
	quals.add(new Qualification("Java"));
	quals.add(new Qualification("Python"));

	Set<Qualification> quals2 = new HashSet<>();
	quals2.add(new Qualification("Ruby"));

	ProjectSize s = ProjectSize.SMALL;
	ProjectSize b = ProjectSize.BIG;


	Project proj = new Project("proj", quals, s);

	Project proj2 = new Project("proj", quals2, b);

	boolean eq = proj.equals(proj2);
    assertTrue(eq);
  }

  @Test
  public void testEqualsFail() {
    Set<Qualification> quals = new HashSet<>();
	quals.add(new Qualification("Java"));
	quals.add(new Qualification("Python"));

	ProjectSize s = ProjectSize.SMALL;


	Project proj = new Project("proj", quals, s);

	Project proj2 = new Project("project", quals, s);

	boolean eq = proj.equals(proj2);
    
    assertFalse(eq);
  }

  @Test
  public void testEqualsInput() {
    Set<Qualification> quals = new HashSet<>();
	quals.add(new Qualification("Java"));
	quals.add(new Qualification("Python"));

	Set<Qualification> quals2 = new HashSet<>();
	quals2.add(new Qualification("Ruby"));

	ProjectSize s = ProjectSize.SMALL;

	Project proj = new Project("proj", quals, s);

	String notproj = "hello";

	boolean eq = proj.equals(notproj);
    assertFalse(eq);
  
  }

  @Test
  public void testEqualsInputNULL() {
    Set<Qualification> quals = new HashSet<>();
	quals.add(new Qualification("Java"));
	quals.add(new Qualification("Python"));

	Set<Qualification> quals2 = new HashSet<>();
	quals2.add(new Qualification("Ruby"));

	ProjectSize s = ProjectSize.SMALL;

	Project proj = new Project("proj", quals, s);

	Project notproj = null;

	boolean eq = proj.equals(notproj);
    assertFalse(eq);
  }

  @Test
  public void isHelpful(){
	Set<Qualification> quals = new HashSet<>();
       quals.add(new Qualification("Java"));
       quals.add(new Qualification("Python"));

	   Set<Qualification> quals2 = new HashSet<>();
       quals2.add(new Qualification("Java"));

       ProjectSize s = ProjectSize.SMALL;

       Project proj = new Project("proj", quals, s);

	    proj.setStatus(ProjectStatus.FINISHED);

		proj.addWorker(new Worker("Bill", quals2, 20000.00));
       proj.addWorker(new Worker("Jackie", quals2, 30000.00));

	   Worker helpfuleWorker = new Worker("Danny", quals, 300.00);
	   assertTrue(proj.isHelpful(helpfuleWorker));

  }

  @Test
  public void isHelpfulWithNull(){
	Set<Qualification> quals = new HashSet<>();
	   quals.add(new Qualification("Java"));
	   quals.add(new Qualification("Python"));

	   ProjectSize s = ProjectSize.SMALL;

	   Project proj = new Project("proj", quals, s);

	   Worker worker = null;

	   assertFalse(proj.isHelpful(worker));

  }

  @Test
  public void isHelpfulWithEmptyQualifications(){
	Set<Qualification> quals = new HashSet<>();
	   quals.add(new Qualification("Java"));
	   quals.add(new Qualification("Python"));

	   ProjectSize s = ProjectSize.SMALL;

	   Project proj = new Project("proj", quals, s);

	   Worker worker = new Worker("Danny", new HashSet<>(), 300.00);

	   assertFalse(proj.isHelpful(worker));
  }

  @Test
  public void isNotHelpful(){
	Set<Qualification> quals = new HashSet<>();
       quals.add(new Qualification("Java"));
       quals.add(new Qualification("Python"));

	   Set<Qualification> quals2 = new HashSet<>();
       quals2.add(new Qualification("Ruby"));

       ProjectSize s = ProjectSize.SMALL;

       Project proj = new Project("proj", quals, s);

	    proj.setStatus(ProjectStatus.FINISHED);
	
		proj.addWorker(new Worker("Bill", quals2, 20000.00));
       proj.addWorker(new Worker("Jackie", quals2, 30000.00));

	   Worker helpfuleWorker = new Worker("Danny", quals2, 300.00);
	   assertFalse(proj.isHelpful(helpfuleWorker));

    }

	@Test
  	public void isNotHelpfulWithNull(){
	Set<Qualification> quals = new HashSet<>();
       quals.add(new Qualification("Java"));
       quals.add(new Qualification("Python"));

	   Set<Qualification> quals2 = new HashSet<>();
       quals2.add(new Qualification("Ruby"));

       ProjectSize s = ProjectSize.SMALL;

       Project proj = new Project("proj", quals, s);

	    proj.setStatus(ProjectStatus.FINISHED);
	
		proj.addWorker(new Worker("Bill", quals2, 20000.00));
       proj.addWorker(new Worker("Jackie", quals2, 30000.00));

	   Worker helpfuleWorker = null;
	
	   assertFalse(proj.isHelpful(helpfuleWorker));

    }

	@Test
	public void projectRemoveWorker(){
		Set<Qualification> quals = new HashSet<>();
		quals.add(new Qualification("Java"));
		quals.add(new Qualification("Python"));

		Set<Qualification> quals2 = new HashSet<>();
		quals2.add(new Qualification("Ruby"));

		ProjectSize s = ProjectSize.SMALL;

		Project proj = new Project("proj", quals, s);

		proj.setStatus(ProjectStatus.FINISHED);

		Worker w1 = new Worker("Bill", quals2, 20000.00);
		Worker w2 = new Worker("Jackie", quals2, 30000.00);

		proj.addWorker(w1);
		proj.addWorker(w2);

		proj.removeWorker(w1);
		Set<Worker> workers = proj.getWorkers();
		assertFalse(workers.contains(w1));
	}

	@Test
	public void projectRemoveNullWorker(){
		Set<Qualification> quals = new HashSet<>();
		quals.add(new Qualification("Java"));
		quals.add(new Qualification("Python"));

		Set<Qualification> quals2 = new HashSet<>();
		quals2.add(new Qualification("Ruby"));

		ProjectSize s = ProjectSize.SMALL;

		Project proj = new Project("proj", quals, s);

		proj.setStatus(ProjectStatus.FINISHED);

		Worker w1 = new Worker("Bill", quals2, 20000.00);
		Worker w2 = new Worker("Jackie", quals2, 30000.00);

		proj.addWorker(w1);
		proj.addWorker(w2);

		try {
			proj.removeWorker(null);
		}
		catch(NullPointerException e){
			assertEquals(null, e.getMessage());
		}

		Set<Worker> workers = proj.getWorkers();
		assertTrue(workers.contains(w1));
		assertTrue(workers.contains(w2));
	}

	@Test
	public void projectRemoveAllWorkers(){
		Set<Qualification> quals = new HashSet<>();
		quals.add(new Qualification("Java"));
		quals.add(new Qualification("Python"));

		Set<Qualification> quals2 = new HashSet<>();
		quals2.add(new Qualification("Ruby"));

		ProjectSize s = ProjectSize.SMALL;

		Project proj = new Project("proj", quals, s);

		proj.setStatus(ProjectStatus.FINISHED);

		Worker w1 = new Worker("Bill", quals2, 20000.00);
		Worker w2 = new Worker("Jackie", quals2, 30000.00);

		proj.addWorker(w1);
		proj.addWorker(w2);

		proj.removeAllWorkers();
		Set<Worker> workers = proj.getWorkers();
		assertTrue(workers.isEmpty());
	}

	@Test
	public void removeAllWorkersNull(){
		Set<Qualification> quals = new HashSet<>();
		quals.add(new Qualification("Java"));
		quals.add(new Qualification("Python"));

		Set<Qualification> quals2 = new HashSet<>();
		quals2.add(new Qualification("Ruby"));

		ProjectSize s = ProjectSize.SMALL;

		Project proj = new Project("proj", quals, s);

		proj.setStatus(ProjectStatus.FINISHED);

		try {
			proj.removeAllWorkers();
		}
		catch(IllegalArgumentException e){
			assertEquals("Invalid set", e.getMessage());
		}

	}

	@Test
	public void testaddWorker(){
		
		Set<Qualification> quals = new HashSet<>();
		quals.add(new Qualification("Java"));

		Project proj = new Project("proj", quals, ProjectSize.SMALL);
		Worker w1 = new Worker("Bill", quals, 20000.00);

		assertEquals(0,proj.getWorkers().size());
		proj.addWorker(w1);

		assertEquals(1,proj.getWorkers().size());
		assertTrue(proj.getWorkers().contains(w1));
	}

	@Test
	public void testaddWorker2(){
		
		Set<Qualification> quals = new HashSet<>();
		quals.add(new Qualification("Java"));

		Project proj = new Project("proj", quals, ProjectSize.SMALL);
		try{
			proj.addWorker(null);
			fail("XYZ");
		}
		catch (NullPointerException e){
			assertTrue("XYZA",true);
		}
	}
}
