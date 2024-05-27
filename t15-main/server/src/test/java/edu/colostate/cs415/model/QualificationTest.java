package edu.colostate.cs415.model;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import edu.colostate.cs415.dto.QualificationDTO;
import edu.colostate.cs415.model.Qualification;

public class QualificationTest {
//  @Test
//  public void testGetWorker(){
//      Qualification Q = new Qualification("Java");
//      Worker W1 = new Worker("Sarthak",null,1000);
//      Worker W2 = new Worker("Bharadwaj",null,1200);
//
//      Q.addWorker(W1);
//      Q.addWorker(W2);
//
//      Set<Worker> W = Q.getWorkers();
//      assertEquals(2,W.size());
//      assertTrue(W.contains(W1));
//      assertTrue(W.contains(W2));
//
//  }


  @Test
  public void testQualificationZeroLength() {
    try {
      Qualification qual = new Qualification("");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid input", e.getMessage());
    }
  }

  @Test
  public void testQualificationWithNULL() {
    try {
      Qualification qual = new Qualification(null);
    } catch (NullPointerException e) {
      assertEquals("Invalid input of null", e.getMessage());
    }

  }

  @Test
  public void testEquals() {
    Qualification qual = new Qualification("test1");
    Qualification qual2 = new Qualification("test1");
    boolean eq = qual.equals(qual2);
    assertTrue(eq);
  }

  @Test
  public void testEqualsFail() {
    Qualification qual = new Qualification("test1");
    Qualification qual2 = new Qualification("test2");
    boolean eq = qual.equals(qual2);
    assertFalse(eq);
  }

  @Test
  public void testEqualsInput() {
    Qualification qual = new Qualification("test1");
    boolean eq = qual.equals("banana");
    assertEquals(false, eq);
  
  }

  @Test
  public void testEqualsInputNULL() {
    Qualification qual = new Qualification("test1");
    assertNotNull(qual);

  }

  @Test
  public void testHashCode() {
    String a = "Foo bar";
    String b = "Foo bar";
    String c = "bar foo";
    Qualification qual = new Qualification(a);
    assertTrue(qual.hashCode() == b.hashCode());
    assertTrue(qual.hashCode() != c.hashCode());
  }

  @Test
  public void testAddWorkerEmpty() {
    Qualification manager = new Qualification("test");
    Worker worker = new Worker("", new HashSet<>(), 0);

    try {
      manager.addWorker(worker);
      fail("Expected an Error to be thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Incorrect worker object", e.getMessage());
    }
  }

  @Test
  public void testAddWorkerCorrectly() {
    Qualification qualification = new Qualification("Software Engineering");
    Set<Qualification> qualifications = new HashSet<>();
    qualifications.add(qualification);
    Worker worker = new Worker("Valid Name", qualifications, 50000);

    qualification.addWorker(worker);

    assertTrue(qualification.getWorkers().contains(worker));

  }

  @Test
  public void testRemoveWorkerEmpty() {
    Qualification manager = new Qualification("test");
    Worker worker = new Worker("", new HashSet<>(), 0);
    try {
      manager.removeWorker(worker);
      fail("Expected an Error to be thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Incorrect worker object", e.getMessage());
    }
  }

  @Test
  public void testRemoveWorkerCorrectly() {
    Qualification qualification = new Qualification("Software Engineering");
    Set<Qualification> qualifications = new HashSet<>();
    qualifications.add(qualification);
    Worker worker = new Worker("Valid Name", qualifications, 50000);
    qualification.addWorker(worker);
    qualification.removeWorker(worker);
    assertFalse(qualification.getWorkers().contains(worker));
  }

  @Test
  public void DTOObjectWithOneWorker(){
    //Set a qualificatiom
    Qualification qualification = new Qualification("Java");
    //Add qualification to a qual set
    Set<Qualification> qualifications = new HashSet<>();
    qualifications.add(qualification);
    //Create a worker
    Worker worker1 = new Worker("Bill", qualifications, 20000);
    //Add worker to the qualification
    qualification.addWorker(worker1);
    //Create DTO
    QualificationDTO qualDTO = qualification.toDTO();
    //Get the worker from DTO
    String[] qualDTOWorker = qualDTO.getWorkers();
    Set<Worker> qualWorker = qualification.getWorkers();

    //compare worker with DTO object
    int count = 0;
    for(Worker work: qualWorker){
      assertEquals(qualDTOWorker[count], work.getName());
      count++;
    }
    

  }

  @Test
  public void DTOObjectWithSeveralWorkers(){
    //Set a qualificatiom
    Qualification qualification = new Qualification("Java");
    //Add qualification to a qual set to put into worker
    Set<Qualification> qualifications = new HashSet<>();
    qualifications.add(qualification);
    //Create a worker
    Worker worker1 = new Worker("Bill", qualifications, 20000);
    Worker worker2 = new Worker("Billy Joe", qualifications, 200000);
    Worker worker3 = new Worker("Maria", qualifications, 30000);
    //Add worker to the qualification
    qualification.addWorker(worker1);
    qualification.addWorker(worker2);
    qualification.addWorker(worker3);
    //Create DTO
    QualificationDTO qualDTO = qualification.toDTO();
    //Get the worker from DTO
    String[] qualDTOWorker = qualDTO.getWorkers();
    Set<Worker> qualWorker = qualification.getWorkers();
    //Compare DTO with the qual object
    int count = 0;
    for(Worker work: qualWorker){
      assertEquals(qualDTOWorker[count], work.getName());
      count++;
    }
    

  }


  @Test
  public void DTOObjectWithDescription(){
    Qualification qualification = new Qualification("Java");
    Set<Qualification> qualifications = new HashSet<>();
    qualifications.add(qualification);
    Worker worker1 = new Worker("Bill", qualifications, 20000);
    qualification.addWorker(worker1);
    QualificationDTO qualDTO = qualification.toDTO();
    assertEquals("Java", qualDTO.getDescription());
  }

}
