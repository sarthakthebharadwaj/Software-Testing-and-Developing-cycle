package edu.colostate.cs415.server;

import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.put;

import static spark.Spark.options;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.redirect;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.google.gson.Gson;

import edu.colostate.cs415.db.DBConnector;
import edu.colostate.cs415.dto.QualificationDTO;
import edu.colostate.cs415.dto.WorkerDTO;
import edu.colostate.cs415.dto.AssignmentDTO;
import edu.colostate.cs415.dto.ProjectDTO;
import edu.colostate.cs415.model.Company;
import edu.colostate.cs415.model.Project;
import edu.colostate.cs415.model.ProjectStatus;
import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.model.Worker;
import spark.Request;
import spark.Response;
import spark.Spark;

public class RestController {

	private static Logger log = Logger.getLogger(RestController.class.getName());
	private static String OK = "OK";
	private static String KO = "KO";

	private DBConnector dbConnector;
	private Company company;
	private Gson gson;

	public RestController(int port, DBConnector dbConnector) {
		port(port);
		this.dbConnector = dbConnector;
		gson = new Gson();
	}

	public void start() {
		// Load data from DB
		company = dbConnector.loadCompanyData();

		// Redirect
		redirect.get("/", "/helloworld");

		// Logging
		after("/*", (req, res) -> logRequest(req, res));
		exception(Exception.class, (exc, req, res) -> handleException(exc, res));

		// Hello World
		get("/helloworld", (req, res) -> helloWorld());

		// API
		path("/api", () -> {
			// Enable CORS
			options("/*", (req, res) -> optionsCORS(req, res));
			after("/*", (req, res) -> enableCORS(res));

			// Qualifications
			path("/qualifications", () -> {
				get("", (req, res) -> getQualifications(), gson::toJson);
				get("/:description", (req, res) -> getQualification(req.params("description")),
						gson::toJson);
				post("/:description", (req, res) -> createQualification(req));
			});

			// Workers
			path("/workers", () -> {
				get("", (req, res) -> getWorkers(), gson::toJson);
				get("/:name", (req, res) -> getWorker(req.params("name")),
						gson::toJson);
				post("/:name", (req, res) -> createWorker(req));
			});

			// Project
			path("/projects", () -> {
				get("", (req, res) -> getProjects(), gson::toJson); 
				get("/:name", (req, res) -> getProject(req.params("name")), gson::toJson); 
				post("/:name", (req, res) -> createProject(req)); 
			});

			//Company
			path("/assign", () -> {
				put("", (req, res) -> companyAssign(req));
			});
			
			path("/unassign", () -> {
				put("", (req, res) -> companyUnassign(req), gson::toJson);
			});

			path("/start", () -> {
				put("", (req, res) -> startProject(req));
			});

			path("/finish", () -> {
				put("", (req, res) -> finishProject(req));
			});
			
		});


	}

	public void stop() {
		Spark.stop();
	}

	private String helloWorld() {
		return "Hello World!";
	}

	private QualificationDTO[] getQualifications() {
		Set<Qualification> qualifications = company.getQualifications();
		QualificationDTO[] qualificationDTOs = new QualificationDTO[qualifications.size()];
		int i = 0;
		for (Qualification qualification : qualifications) {
			qualificationDTOs[i++] = qualification.toDTO();
		}
		return qualificationDTOs;
	}

	private QualificationDTO getQualification(String description) {
		Set<Qualification> qualifications = company.getQualifications();
		QualificationDTO qual = null;
		for (Qualification qualification : qualifications) {
            if (qualification.toString().equals(description)) {
                qual = qualification.toDTO();
            }
        }
		if(qual == null){
			throw new IllegalArgumentException();
		}
        return qual;
    }

	private String createQualification(Request request) {
		QualificationDTO assignmentDTO = gson.fromJson(request.body(), QualificationDTO.class);
		if (request.params("description").equals(assignmentDTO.getDescription())) {
			company.createQualification(assignmentDTO.getDescription());
		} else
			throw new RuntimeException("Qualification descriptions do not match.");
		return OK;
	}

	private WorkerDTO[] getWorkers() {
		Set<Worker> workers = company.getEmployedWorkers();
		WorkerDTO[] workerDTO = new WorkerDTO[workers.size()];
		int i = 0;
		for (Worker worker : workers) {
			workerDTO[i++] = worker.toDTO();
		}
		return workerDTO;
	}

	private WorkerDTO getWorker(String name) {
		for (Worker worker : company.getEmployedWorkers()) {
			if (worker.getName().equals(name)) {
				return worker.toDTO();
			}
		}
		return null;
	}

	private String createWorker(Request request) {
		WorkerDTO workerDTO = gson.fromJson(request.body(), WorkerDTO.class);
		if (request.params("name").equals(workerDTO.getName())) {
			if (workerDTO.getQualifications() != null && workerDTO.getQualifications().length != 0 && workerDTO.getQualifications()[0] != null && !workerDTO.getQualifications()[0].isEmpty() && request.body().contains("salary")) {
				String[] qualifications = workerDTO.getQualifications();
				Set<Qualification> qualificationsSet = new HashSet<>();
				for (int i = 0; i < workerDTO.getQualifications().length; i++) {
					Qualification qualification = new Qualification(qualifications[i]);
					qualificationsSet.add(qualification);
				}
				company.createWorker(workerDTO.getName(), qualificationsSet, workerDTO.getSalary());
				return OK;
			} else {
				throw new RuntimeException("Missing Required Entry");
			}
		} else
			throw new RuntimeException("Worker names do not match.");
	}

	// Logs every request received
	private void logRequest(Request request, Response response) {
		log.info(request.requestMethod() + " " + request.pathInfo() + "\nREQUEST:\n" + request.body() + "\nRESPONSE:\n"
				+ response.body());
	}

	// Exception handling
	private void handleException(Exception exception, Response response) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace();
		exception.printStackTrace(pw);
		log.severe(sw.toString());
		response.body(KO);
		response.status(500);
	}

	// Enable CORS
	private void enableCORS(Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	}

	// Enable CORS
	private String optionsCORS(Request request, Response response) {
		String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
		if (accessControlRequestHeaders != null)
			response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);

		String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
		if (accessControlRequestMethod != null)
			response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
		return OK;
	}
	// Project 
	private ProjectDTO[] getProjects(){
		Set<Project> projects = company.getProjects();
		ProjectDTO[] projectDTOs = new ProjectDTO[projects.size()];
		int i = 0;
		for(Project project:projects){
			projectDTOs[i++]=project.toDTO();
		} 
		return projectDTOs;
	}

	private ProjectDTO getProject(String name){
		for(Project project:company.getProjects()){
			if(project.getName().equals(name)){
				return project.toDTO();
			}
		}
		return null;
	}

	private String createProject(Request request){
		ProjectDTO projectDTO = gson.fromJson(request.body(), ProjectDTO.class);
		if (projectDTO.getName() != null && projectDTO.getQualifications() != null && projectDTO.getSize() != null) {
			
			Set<String> qualificationNames = new HashSet<>();

			for (String qualificationName : projectDTO.getQualifications()){
				qualificationNames.add(qualificationName);
			}
			Set<Qualification> qualifications = convertStringsToQualifications(qualificationNames);
	
			company.createProject(projectDTO.getName(), qualifications, projectDTO.getSize());
			return OK;
	
		}
		else{
			throw new RuntimeException("Missing required project fields. - error testing- projectDTO.getName() = " + projectDTO.getName() + " projectDTO.getQualifications() = " + projectDTO.getQualifications() + " projectDTO.getSize() = " + projectDTO.getSize() + " - error testing");
		}
	}

	private Set<Qualification> convertStringsToQualifications(Set<String> qualificationNames){
		Set<Qualification> qualifications = new HashSet<>();
		for (String name : qualificationNames){
			qualifications.add(new Qualification(name));
		}
		return qualifications;
	}
	private void validateRequestParameter(String parameter, String parameterName) {
		if (parameter == null || parameter.trim().isEmpty()) {
			throw new IllegalArgumentException(parameterName + " is missing or empty in the request.");
		}
	}
	
	private Worker findWorkerByName(String workerName) {
		return company.getEmployedWorkers().stream()
				.filter(w -> w.getName().equals(workerName))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Company Worker not found: '" + workerName + "'"));
	}
	
	private Project findProjectByName(String projectName) {
		return company.getProjects().stream()
				.filter(p -> p.getName().equals(projectName))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Company Project not found: '" + projectName + "'"));
	}

	private String companyAssign(Request request) {
		AssignmentDTO assignmentDTO = gson.fromJson(request.body(), AssignmentDTO.class);
		validateRequestParameter(assignmentDTO.getWorker(), "Worker name");
		validateRequestParameter(assignmentDTO.getProject(), "Project name");
	
		Worker worker = findWorkerByName(assignmentDTO.getWorker());
		Project project = findProjectByName(assignmentDTO.getProject());
	
		if (project.getWorkers().contains(worker)) {
			throw new RuntimeException("Worker '" + worker.getName() + "' is already assigned to Project '" + project.getName() + "'");
		}
		
		company.assign(worker, project);
		return OK;
	}

	private String companyUnassign(Request request) {
		AssignmentDTO assignmentDTO = gson.fromJson(request.body(), AssignmentDTO.class);
		validateRequestParameter(assignmentDTO.getWorker(), "Worker name");
		validateRequestParameter(assignmentDTO.getProject(), "Project name");
	
		Worker worker = findWorkerByName(assignmentDTO.getWorker());
		Project project = findProjectByName(assignmentDTO.getProject());
	
		if (!project.getWorkers().contains(worker)) {
			throw new RuntimeException("Worker '" + worker.getName() + "' is not assigned to Project '" + project.getName() + "'");
		}
	
		company.unassign(worker, project);
		return OK;
	}

	private String startProject(Request request) {
		ProjectDTO projectDTO = gson.fromJson(request.body(), ProjectDTO.class);
		validateRequestParameter(projectDTO.getName(), "Project name");
	
		Project project = findProjectByName(projectDTO.getName());
		company.start(project);
		return OK;
	}
	
	private String finishProject(Request request) {
		ProjectDTO projectDTO = gson.fromJson(request.body(), ProjectDTO.class);
		validateRequestParameter(projectDTO.getName(), "Project name");
	
		Project project = findProjectByName(projectDTO.getName());
		company.finish(project);
		return OK;
	}
}