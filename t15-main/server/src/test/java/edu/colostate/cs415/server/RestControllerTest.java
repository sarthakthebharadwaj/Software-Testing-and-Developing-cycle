package edu.colostate.cs415.server;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import edu.colostate.cs415.db.DBConnector;
import edu.colostate.cs415.dto.*;
import edu.colostate.cs415.model.*;

import com.google.gson.Gson;

import org.apache.hc.client5.http.HttpResponseException;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.evosuite.runtime.MockitoExtension;
import org.apache.hc.core5.http.ContentType;
import java.util.Set;




import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken; 

public class RestControllerTest {

private static DBConnector dbConnector = mock(DBConnector.class);
private static RestController restController = new RestController(4567, dbConnector);
private static Company testCompany;
private Gson gson = new Gson();
private static final String BASE_URL = "http://localhost:4567";

    @BeforeClass
    public static void init() {
        when(dbConnector.loadCompanyData()).thenAnswer(i -> testCompany);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetWorkers1() throws IOException {
        testCompany = new Company("TestCompany");
        Qualification python = testCompany.createQualification("Python");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(python);
        Worker worker = testCompany.createWorker("John", qualifications, 15000.0);
        Project project = testCompany.createProject("TestProject", qualifications, ProjectSize.BIG);
        worker.addProject(project);
        restController.start();

        WorkerDTO[] workerDTO = gson.fromJson(Request.get("http://localhost:4567/api/workers").execute().returnContent().asString(), WorkerDTO[].class);
        assertEquals(workerDTO[0].getQualifications()[0], "Python");
        assertEquals(workerDTO[0].getProjects()[0], "TestProject");
        assertEquals(workerDTO[0].getSalary(), 15000.0, 0.0);
        assertEquals(workerDTO[0].getName(), "John");
    }

    @Test
    public void testGetWorkers2() throws IOException {
        testCompany = new Company("TestCompany");
        restController.start();
        WorkerDTO[] workerDTO = gson.fromJson(Request.get("http://localhost:4567/api/workers").execute().returnContent().asString(), WorkerDTO[].class);
        assertEquals(workerDTO.length, 0);
    }


    @Test
    public void testGetWorkers3() throws IOException {
        testCompany = new Company("TestCompany");
        Qualification python = testCompany.createQualification("Python");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(python);
        Worker worker = testCompany.createWorker("John", qualifications, 15000.0);
        restController.start();
        WorkerDTO[] workerDTO = gson.fromJson(Request.get("http://localhost:4567/api/workers").execute().returnContent().asString(), WorkerDTO[].class);
        assertEquals(workerDTO.length, 1);
        assertEquals(workerDTO[0].getProjects().length, 0);
    }

    @Test
    public void testGetworkersMultiProject() throws IOException {
        testCompany = new Company("TestCompany");
        Qualification qual = testCompany.createQualification("Thrifty");
        Qualification qual2 = testCompany.createQualification("Python");
        Set<Qualification> quals = new HashSet<>();
        quals.add(qual);
        quals.add(qual2);
        Set<Worker> team = new HashSet<>();
        Worker worker = testCompany.createWorker("Bill", quals, 15000.0);
        Worker worker2 = testCompany.createWorker("Jake", quals, 40000.0);
        team.add(worker);
        team.add(worker2);
        Project project = testCompany.createProject("TestProject", quals, ProjectSize.SMALL);
        Project project2 = testCompany.createProject("AnotherProject", quals, ProjectSize.SMALL);
        worker.addProject(project);
        worker.addProject(project2);
        worker2.addProject(project);
        worker2.addProject(project2);
        restController.start();
        WorkerDTO[] workerDTO = gson.fromJson(Request.get("http://localhost:4567/api/workers").execute().returnContent().asString(), WorkerDTO[].class);
        if(workerDTO[0].getName().equals("Bill")){
            assertEquals(workerDTO[0].getSalary(), 15000.0, 0.0);
            assertEquals(workerDTO[0].getName(), "Bill");
        }
        else{
            assertEquals(workerDTO[0].getSalary(), 40000.0, 0.0);
            assertEquals(workerDTO[0].getName(), "Jake");            
        }

        if(workerDTO[0].getQualifications()[0].equals("Thrifty")){
            assertEquals(workerDTO[0].getQualifications()[0], "Thrifty");
            assertEquals(workerDTO[0].getQualifications()[1], "Python");
        }
        else{
            assertEquals(workerDTO[0].getQualifications()[1], "Thrifty");
            assertEquals(workerDTO[0].getQualifications()[0], "Python");
        }

        if(workerDTO[0].getProjects()[0].equals("TestProject")){
            assertEquals(workerDTO[0].getProjects()[0], "TestProject");
            assertEquals(workerDTO[0].getProjects()[1], "AnotherProject");
        }
        else{
            assertEquals(workerDTO[0].getProjects()[1], "TestProject");
            assertEquals(workerDTO[0].getProjects()[0], "AnotherProject");            
        }
        

    }

    @Test
    public void testPushWorker() throws IOException {
        testCompany = new Company("TestCompany");
        Qualification qual = testCompany.createQualification("Thrifty");
        Set<Qualification> quals = new HashSet<>();
        quals.add(qual);
        testCompany.createProject("TestProject", quals, ProjectSize.SMALL);
        String json = "{ \"name\": \"Kelsey\", \"salary\": 150000.0," +
                "\"qualifications\": [\"Python\"]}";
        restController.start();
        String response = gson.fromJson(Request.post("http://localhost:4567/api/workers/Kelsey")
                .bodyByteArray(json.getBytes())
                .execute().returnContent().asString(), String.class);
        assertEquals(response, "OK");
    }

    @Test
    public void testFalsePushWorker() throws IOException {
        testCompany = new Company("TestCompany");
        Qualification qual = testCompany.createQualification("Thrifty");
        Set<Qualification> quals = new HashSet<>();
        quals.add(qual);
        testCompany.createProject("TestProject", quals, ProjectSize.SMALL);
        String json = "{ \"name\": \"Kelsey\", \"salary\": 150000.0," +
                "\"qualifications\": [\"Python\"]}";
        exception.expect(HttpResponseException.class);
        restController.start();
        Request.post("http://localhost:4567/api/workers/Dave")
                .bodyByteArray(json.getBytes())
                .execute().returnContent().asString();

    }

    @Test
    public void testGetProjectsReturnsListOfProjects() throws IOException {
        String jsonResponse = Request.get(BASE_URL + "/api/projects")
                                     .execute()
                                     .returnContent()
                                     .asString();
        Type projectListType = new TypeToken<List<ProjectDTO>>(){}.getType();
        List<ProjectDTO> projectDTOs = gson.fromJson(jsonResponse, projectListType);

        assertNotNull("The response should not be null", projectDTOs);
        assertFalse("The project list should not be empty", projectDTOs.isEmpty());
    }

    @Test
    public void testGetProjectByNameReturnsProjectDTO() throws IOException{
        String projectName = "Google";
        Set<Qualification> qualsGetProject = new HashSet<>();
        qualsGetProject.add(testCompany.createQualification("Dancing"));
        Project project  = testCompany.createProject(projectName, qualsGetProject, ProjectSize.MEDIUM);

        restController.start();

        String url = BASE_URL + "/api/projects/" + projectName;
        String jsonResponse = Request.get(url).execute().returnContent().asString();

        ProjectDTO projectDTO = new Gson().fromJson(jsonResponse, ProjectDTO.class);
        assertNotNull("ProjectDTO should not be null", projectDTO);
        assertEquals(projectName, projectDTO.getName());
    }

    @Test 
    public void testGetQualifciationsWithName() throws IOException{
        testCompany = new Company("Company");

        
        testCompany.createQualification("Ruby");
        testCompany.createQualification("Java");
        testCompany.createQualification("Python");


        String java = "Java";

        restController.start();

        
        QualificationDTO qualDTO = gson.fromJson(Request.get("http://localhost:4567/api/qualifications/" + java).execute().returnContent().asString(), QualificationDTO.class);

        assertNotNull("QualificationDTO should not be null", qualDTO);
        assertEquals("Java", qualDTO.getDescription());

        String ruby = "Ruby";

        QualificationDTO qualDTOTwo = gson.fromJson(Request.get("http://localhost:4567/api/qualifications/" + ruby).execute().returnContent().asString(), QualificationDTO.class);

        assertNotNull("QualificationDTO should not be null", qualDTO);
        assertEquals("Ruby", qualDTOTwo.getDescription());

        String python = "Python";

        QualificationDTO qualDTOThree = gson.fromJson(Request.get("http://localhost:4567/api/qualifications/" + python).execute().returnContent().asString(), QualificationDTO.class);

        assertNotNull("QualificationDTO should not be null", qualDTO);
        assertEquals("Python", qualDTOThree.getDescription());
    }

    @Test
    public void testStart() throws IOException {
        testCompany = new Company("TestCompany");
        Qualification qualification = testCompany.createQualification("Java");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(qualification);
        Worker worker = testCompany.createWorker("Jane", qualifications, 20000.0);
        Project project = testCompany.createProject("Project Alpha", qualifications, ProjectSize.MEDIUM);
        testCompany.assign(worker, project);
        restController.start();

        ProjectDTO projDTO = project.toDTO();
        String response = Request.put("http://localhost:4567/api/start")
            .bodyString(gson.toJson(projDTO), ContentType.APPLICATION_JSON)
            .execute()
            .returnContent()
            .asString();
        assertEquals(response, "OK");
    }

    @Test
    public void testStartUnsuccessful() throws IOException {
        testCompany = new Company("TestCompany");
        Qualification quali = testCompany.createQualification("Thrifty");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(quali);
        Worker work = testCompany.createWorker("John", qualifications, 20000.0);
        Project project = testCompany.createProject("Project Oof", qualifications, ProjectSize.MEDIUM);
        testCompany.assign(work, project);
        project.setStatus(ProjectStatus.FINISHED);
        restController.start();

        ProjectDTO projDTO = project.toDTO();
        HttpResponse response = Request.put("http://localhost:4567/api/start")
            .bodyString(gson.toJson(projDTO), ContentType.APPLICATION_JSON)
            .execute()
            .returnResponse();
        assertEquals(500, response.getCode());
    }

    @BeforeEach
    public void setUp() {
        gson = new Gson();
        dbConnector = mock(DBConnector.class); // Mockito.mock(DBConnector.class) if using Mockito
        testCompany = new Company("TestCompany");
        when(dbConnector.loadCompanyData()).thenReturn(testCompany);
        restController = new RestController(4567, dbConnector);
        restController.start(); // Make sure your RestController can be started like this for testing
    }


    @Test
    public void testAssignWorkerToProjectSuccess() throws IOException {
        Qualification qualification = testCompany.createQualification("Java");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(qualification);
        Worker worker = testCompany.createWorker("Jane", qualifications, 20000.0);
        Project project = testCompany.createProject("Project Alpha", qualifications, ProjectSize.MEDIUM);

        AssignmentDTO assignment = new AssignmentDTO("Jane", "Project Alpha");
        String jsonRequest = gson.toJson(assignment);

        String response = Request.put(BASE_URL + "/api/assign")
                                .bodyString(jsonRequest, ContentType.APPLICATION_JSON)
                                .execute()
                                .returnContent()
                                .asString();

        assertEquals("OK", response);
    }
    @Test
    public void testUnassignWorkerFromProjectSuccess() throws IOException {
        // First, assign a worker to ensure they can be unassigned
        Qualification qualification = testCompany.createQualification("Java");
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(qualification);
        Worker worker = testCompany.createWorker("Jane", qualifications, 20000.0);
        Project project = testCompany.createProject("Project Alpha", qualifications, ProjectSize.MEDIUM);
        testCompany.assign(worker, project); // Simulate successful assignment
    
        AssignmentDTO unassignment = new AssignmentDTO("Jane", "Project Alpha");
        String jsonRequest = gson.toJson(unassignment);
    
        String response = Request.put(BASE_URL + "/api/unassign")
                            .bodyString(jsonRequest, ContentType.APPLICATION_JSON)
                            .execute()
                            .returnContent()
                            .asString();
    
        assertEquals("OK", response.replace("\"", ""));
    }
    @Test
    public void testUnassignWorkerFromProjectSuccess2() throws IOException {
        // Pre-conditions: Assign worker to the project
        Qualification javaQualification = testCompany.createQualification("Java");
        Worker jane = testCompany.createWorker("Jane", new HashSet<>(Arrays.asList(javaQualification)), 20000.0);
        Project projectAlpha = testCompany.createProject("Project Alpha", new HashSet<>(Arrays.asList(javaQualification)), ProjectSize.MEDIUM);
        testCompany.assign(jane, projectAlpha);

        AssignmentDTO unassignmentDTO = new AssignmentDTO(jane.getName(), projectAlpha.getName());
        String jsonRequest = gson.toJson(unassignmentDTO);

        HttpResponse response = Request.put("http://localhost:4567/api/unassign")
                                    .bodyString(jsonRequest, ContentType.APPLICATION_JSON)
                                    .execute()
                                    .returnResponse();

        assertEquals(200, response.getCode());
        // Post-condition: Check that the worker is no longer assigned to the project
        assertFalse(projectAlpha.getWorkers().contains(jane));
        assertFalse(jane.getProjects().contains(projectAlpha));
    }
    public class CompanyAssignTest {

        private Company company;
        private Gson gson = new Gson();
        private Qualification java;
        private Worker worker;
        private Project project;

        @BeforeEach
        public void setUp() {
    
            java = company.createQualification("Java");
            worker = company.createWorker("Heidi", new HashSet<Qualification>(Arrays.asList(java)), 1000);
            project = company.createProject("Project2", new HashSet<Qualification>(Arrays.asList(java)), ProjectSize.SMALL);
        }

        @AfterEach
        public void tearDown() {
			java = null;
			worker = null;
			project = null;
			company = null;
        }

        private HttpResponse sendAssignRequest(String workerName, String projectName) throws IOException {
            AssignmentDTO assignmentDTO = new AssignmentDTO(workerName, projectName);
            return Request.put("http://localhost:4567/api/assign")
                    .bodyString(gson.toJson(assignmentDTO), ContentType.APPLICATION_JSON)
                    .execute()
                    .returnResponse();
        }

        @Test
        public void testAssign() throws IOException {
            HttpResponse response = sendAssignRequest(worker.getName(), project.getName());
			assertEquals(200, response.getCode());
			assertTrue(project.getWorkers().contains(worker));
			assertTrue(worker.getProjects().contains(project));
        }
        @Test
        public void testAssign2() throws IOException {
            assertFalse(project.getWorkers().contains(worker));
			assertFalse(worker.getProjects().contains(project));
        }
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"InvalidProject", ""})
        public void testAssign_InvalidProjectNames(String projectName) throws IOException {
            HttpResponse response = sendAssignRequest(worker.getName(), projectName);

			assertEquals(500, response.getCode());
			assertFalse(worker.getProjects().contains(project));
        }

    
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"InvalidWorker", ""})
        public void testAssign_InvalidWorkerNames(String workerName) throws IOException {
            HttpResponse response = sendAssignRequest(workerName, project.getName());

            assertEquals(500, response.getCode());
			assertFalse(project.getWorkers().contains(worker));
        }

        @Test
        public void testAssign_ProjectAlreadyContainsWorker() throws IOException {
            testCompany.assign(worker, project);
            HttpResponse response = sendAssignRequest(worker.getName(), project.getName());

            assertEquals(500, response.getCode());
			assertTrue(project.getWorkers().contains(worker));
			assertTrue(worker.getProjects().contains(project));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {""})
        public void testAssign_EmptyWorkerOrProjectName(String input) throws IOException {
            HttpResponse responseWorker = sendAssignRequest(input, project.getName());
            HttpResponse responseProject = sendAssignRequest(worker.getName(), input);

            assertEquals(500, responseWorker.getCode());
            assertEquals(500, responseProject.getCode());
            assertFalse(project.getWorkers().contains(worker));
            assertFalse(worker.getProjects().contains(project)); 
        }
    
    
        @Test
        public void testAssign_FailToAssign() throws IOException {
            Qualification java = company.createQualification("Java");
            Project project = company.createProject("Project", new HashSet<>(Arrays.asList(java)), ProjectSize.SMALL);
            HttpResponse response = sendAssignRequest(worker.getName(), project.getName());
    
            assertEquals(500, response.getCode());
            assertFalse(project.getWorkers().contains(worker));
            assertFalse(worker.getProjects().contains(project));
        }

        
    }
    @Test 
    public void testPostQualifciationsTwo() throws IOException{
        testCompany = new Company("TheCompany");
        String name = "MyProject";
        Set<Qualification> quals = new HashSet<>();

        Qualification qualOne = new Qualification("Banana");
        Qualification qualTwo = new Qualification("Apple");
        Qualification qualThree = new Qualification("Pear");

        quals.add(qualOne);
        quals.add(qualTwo);
        quals.add(qualThree);
        
        testCompany.createQualification("Banana");
        testCompany.createQualification("Apple");
        testCompany.createQualification("Pear");

        String json = "{ \"name\": \"MyProject\"," +  
        " \"qualifications\": [\"Banana\", \"Apple\", \"Pear\"]," + 
        " \"size\": SMALL}";

        restController.start();
        String response = gson.fromJson(Request.post("http://localhost:4567/api/projects/MyProject").bodyByteArray(json.getBytes()).execute().returnContent().asString(), String.class);
        assertEquals(response, "OK");

        ProjectDTO projDTO= gson.fromJson(Request.get("http://localhost:4567/api/projects/MyProject").execute().returnContent().asString(), ProjectDTO.class);

        String[] qualsString = new String[quals.size()];
        
        int index = 0;
        for(Qualification q: quals){
            qualsString[index] = q.toString();
            index++;
        }
        assertNotNull("ProjectDTO should not be null", projDTO);
        assertEquals(name, projDTO.getName());

        boolean bol = false;
        for(String s:  projDTO.getQualifications()){
            for(String str: qualsString){
                if(s.equals(str)){
                    bol = true;
                }
            }
            assertTrue(bol);
        }
        assertEquals(ProjectSize.SMALL, projDTO.getSize());
    
    }

    public class CompanyUnassignTest {

        private Company testCompany;
        private Gson gson;
        private RestController restController;
        private DBConnector dbConnector;
        private static final String BASE_URL = "http://localhost:4567";
    
        @BeforeEach
        public void setUp() {
            gson = new Gson();
            dbConnector = mock(DBConnector.class);
            testCompany = new Company("TestCompany");
            when(dbConnector.loadCompanyData()).thenReturn(testCompany);
            restController = new RestController(4567, dbConnector);
            restController.start(); 
        }
    
        private HttpResponse sendUnassignRequest(AssignmentDTO assignmentDTO) throws IOException {
            return Request.put(BASE_URL + "/api/unassign")
                    .bodyString(gson.toJson(assignmentDTO), ContentType.APPLICATION_JSON)
                    .execute()
                    .returnResponse();
        }
    
        @Test
        public void testUnassignWorkerFromProject_Success() throws IOException {
            Qualification qualification = testCompany.createQualification("Java");
            Worker worker = testCompany.createWorker("Alice",new HashSet<>(Arrays.asList(qualification)), 50000);
            Project project = testCompany.createProject("ProjectX", new HashSet<>(Arrays.asList(qualification)), ProjectSize.MEDIUM);
            testCompany.assign(worker, project);
    
            AssignmentDTO assignmentDTO = new AssignmentDTO(worker.getName(), project.getName());
            HttpResponse response = sendUnassignRequest(assignmentDTO);
            
            assertEquals(200, response.getCode());
        }
    
        @Test
        public void testUnassignWorkerNotAssigned() throws IOException {
            Qualification qualification = testCompany.createQualification("C#");
            Worker worker = testCompany.createWorker("Bob", new HashSet<>(Arrays.asList(qualification)), 60000);
            Project project = testCompany.createProject("ProjectY", new HashSet<>(Arrays.asList(qualification)), ProjectSize.SMALL);
    
            AssignmentDTO assignmentDTO = new AssignmentDTO(worker.getName(), project.getName());
            HttpResponse response = sendUnassignRequest(assignmentDTO);
            
            assertNotEquals(200, response.getCode());
        }
    
        @Test
        public void testUnassignWithInvalidWorkerName() throws IOException {
            Qualification qualification = testCompany.createQualification("Python");
            Project project = testCompany.createProject("ProjectZ", new HashSet<>(Arrays.asList(qualification)), ProjectSize.SMALL);
    
            AssignmentDTO assignmentDTO = new AssignmentDTO("NonExistentWorker", project.getName());
            HttpResponse response = sendUnassignRequest(assignmentDTO);
    
            assertNotEquals(200, response.getCode());
        }
    
        @Test
        public void testUnassignWithInvalidProjectName() throws IOException {
            Qualification qualification = testCompany.createQualification("Go");
            Worker worker = testCompany.createWorker("Eve", new HashSet<>(Arrays.asList(qualification)), 70000);
    
            AssignmentDTO assignmentDTO = new AssignmentDTO(worker.getName(), "NonExistentProject");
            HttpResponse response = sendUnassignRequest(assignmentDTO);
    
            assertNotEquals(200, response.getCode());
        }
    
    }
    public class CompanyFinishProjectTest {

        private Company testCompany;
        private Gson gson;
        private static final String BASE_URL = "http://localhost:4567";

        @BeforeEach
        public void setUp() {
            gson = new Gson();
            dbConnector = mock(DBConnector.class);
            testCompany = new Company("TestCompany");
            when(dbConnector.loadCompanyData()).thenReturn(testCompany);
            restController = new RestController(4567, dbConnector);
            restController.start();       
        }

        private ClassicHttpResponse sendFinishRequest(ProjectDTO projectDTO) throws IOException {
            return (ClassicHttpResponse) Request.put(BASE_URL + "/api/finish")
                    .bodyString(gson.toJson(projectDTO), ContentType.APPLICATION_JSON)
                    .execute()
                    .returnResponse();
        }

        @Test
        public void testFinishProject_Success() throws IOException {
            Qualification qualification = testCompany.createQualification("Java");
            Worker worker = testCompany.createWorker("Alice", new HashSet<>(Arrays.asList(qualification)), 50000);
            Project project = testCompany.createProject("ProjectX", new HashSet<>(Arrays.asList(qualification)), ProjectSize.MEDIUM);
            testCompany.assign(worker, project);
            testCompany.start(project);

            ProjectDTO projectDTO =  project.toDTO(); 
            ClassicHttpResponse response = sendFinishRequest(projectDTO);
            
            assertEquals(200, response.getCode(), "Expected success status code 200.");
        }


        @Test
        public void testFinishProject_InvalidStatus() throws IOException {
            Project project = testCompany.createProject("ProjectY", new HashSet<>(Arrays.asList(testCompany.createQualification("Python"))), ProjectSize.MEDIUM);
            String[] workers = {};
            String[] qualifications = project.getRequiredQualifications().stream().toArray(String[]::new);
            String[] missingQualifications = {}; 
        
            ProjectDTO projectDTO = new ProjectDTO(
                    project.getName(),
                    project.getSize(),
                    ProjectStatus.PLANNED, 
                    workers,
                    qualifications,
                    missingQualifications);
        
            ClassicHttpResponse response = sendFinishRequest(projectDTO);
            assertNotEquals(200, response.getCode(), "Finishing a project not started should fail.");
        }
        @Test
        public void testFinishProject_MissingQualifications() throws IOException {
            Project project = testCompany.createProject("ProjectZ", new HashSet<>(Arrays.asList(testCompany.createQualification("JavaScript"))), ProjectSize.MEDIUM);
            testCompany.start(project); 
            String[] workers = {}; 
            String[] qualifications = {}; 
            String[] missingQualifications = {"NodeJS"}; 

            ProjectDTO projectDTO = new ProjectDTO(
                    project.getName(),
                    project.getSize(),
                    ProjectStatus.ACTIVE, 
                    workers,
                    qualifications,
                    missingQualifications);

            ClassicHttpResponse response = sendFinishRequest(projectDTO);
            assertNotEquals(200, response.getCode(), "Finishing a project with missing qualifications should fail.");
        }
    }

    @Test 
    public void testPostQualifciations() throws IOException{
        testCompany = new Company("TegsCompany");
        testCompany.createQualification("Python");

        String json = "{ \"description\": \"Javascript\"}";

        String javascript = "Javascript";

        restController.start();
        String response = gson.fromJson(Request.post("http://localhost:4567/api/qualifications/Javascript").bodyByteArray(json.getBytes()).execute().returnContent().asString(), String.class);
        assertEquals(response, "OK");

        QualificationDTO qualDTO= gson.fromJson(Request.get("http://localhost:4567/api/qualifications/" + javascript).execute().returnContent().asString(), QualificationDTO.class);

        assertNotNull("QualificationDTO should not be null", qualDTO);
        assertEquals("Javascript", qualDTO.getDescription());
    }

    @Test 
    public void testGetProjectNoName() throws IOException{
        testCompany = new Company("Apple");

        String projectNameOne = "Project Name";
        String projectNameTwo = "Project Banana";
        String projectNameThree = "Project Pear";

        String[] projectNames = {projectNameOne, projectNameTwo, projectNameThree};

        Set<Qualification> qualsGetProject = new HashSet<>();
        Qualification qualGetProject = new Qualification("Running");
        Qualification qualGetProjectTwo = new Qualification("Soccer");
        Qualification qualGetProjectThree = new Qualification("baseball");

        testCompany.createQualification("Running");
        testCompany.createQualification("Soccer");
        testCompany.createQualification("baseball");

        qualsGetProject.add(qualGetProject);
        qualsGetProject.add(qualGetProjectTwo);
        qualsGetProject.add(qualGetProjectThree);

        Project projectOne  = testCompany.createProject(projectNameOne, qualsGetProject, ProjectSize.SMALL);
        Project projectTwo  = testCompany.createProject(projectNameTwo, qualsGetProject, ProjectSize.SMALL);
        Project projectThree  = testCompany.createProject(projectNameThree, qualsGetProject, ProjectSize.BIG);

        Project[] projectsToLoop = {projectOne, projectTwo, projectThree};

        restController.start();

        ProjectDTO[] projDTOs = gson.fromJson(Request.get("http://localhost:4567/api/projects").execute().returnContent().asString(), ProjectDTO[].class);

        assertNotNull("ProjectDTO should not be null", projDTOs);

        boolean bol = false;
        for(ProjectDTO projDTO: projDTOs){
            bol = false;
            for(Project proj: projectsToLoop){
                if(proj.getName().equals(projDTO.getName())){
                    
                    for(Qualification q: proj.getRequiredQualifications()){
                        boolean hasQual = false;
                        for(String s: projDTO.getQualifications()){
                            if(s.equals(q.toString())){
                                hasQual = true;
                            }
                        }
                        assertTrue(hasQual);
                    }

                    assertEquals(proj.getSize(), projDTO.getSize());
                    
                    bol = true;
                }
            }
            assertTrue(bol);
        }
    }


}




