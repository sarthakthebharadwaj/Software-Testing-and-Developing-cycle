package edu.colostate.cs415.model;

import java.util.Set;
import java.util.Collections;


import java.util.HashSet;
import edu.colostate.cs415.model.Project;


public class Company {

	private String name;
	private Set<Worker> employees;
	private Set<Worker> available;
	private Set<Worker> assigned;
	private Set<Project> projects;
	private Set<Qualification> qualifications;

	public Company(String name) {
		if(name == null || name.trim().isEmpty()){
			throw new NullPointerException();
		}

		this.name = name;
		this.qualifications = new HashSet<>();
		this.employees=new HashSet<>();
		this.available = new HashSet<>();
		this.assigned = new HashSet<>();
		this.projects = new HashSet<>();


	}

	@Override
	public boolean equals(Object other) {
		if(other == null || getClass() != other.getClass()){
			return false;
		}
		Company company = (Company) other;
		return name.equals(company.name);
	}

	@Override
	public int hashCode() {
		int result = java.util.Objects.hash(name);
		for(Qualification qualification : qualifications ){
			result = 31 * result +qualification.hashCode();
		}
		return result;
	}

	@Override
	public String toString() {
		return this.name + ":" + this.available.size() + ":" + this.projects.size(); 	
	}

	public String getName() {
		return this.name;
	}

	public Set<Worker> getEmployedWorkers() {
		if(this.employees.isEmpty()){
			return Collections.emptySet();
		}
		else{
			return new HashSet<>(this.employees);
		}
	}

	public Set<Worker> getAvailableWorkers() {
		if(this.available.isEmpty()){
			return Collections.emptySet();
		}
		else{
			return new HashSet<>(this.available);
		}
	}

	public Set<Worker> getUnavailableWorkers() {
		Set<Worker> unavailable = new HashSet<>();
		for(Worker a: employees){
			if(!available.contains(a)){
				unavailable.add(a);
			}
		}

		return unavailable;
		
	}

	public Set<Worker> getAssignedWorkers() {
		return this.assigned;
	}

	public Set<Worker> getUnassignedWorkers() {
		Set<Worker> unassigned = new HashSet<>();
		for(Worker a: employees){
			if(!assigned.contains(a)){
				unassigned.add(a);
			}
		}
		return unassigned;
	}

	public Set<Project> getProjects() {
		return this.projects;
	}

	public Set<Qualification> getQualifications() {
		return this.qualifications;
	}

	public Worker createWorker(String name, Set<Qualification> qualifications, double salary) {

		if(name == null || name.trim().isEmpty() || salary < 0.0 || qualifications == null || qualifications.isEmpty() ){
			return null;
		}
		if(!this.getQualifications().containsAll(qualifications)){
			return null;
		}
		
		Worker newWorker = new Worker(name, qualifications, salary);

		employees.add(newWorker);
		available.add(newWorker);

		for (Qualification q : qualifications) {
			q.addWorker(newWorker);
		}

		return newWorker;
    }

	public Qualification createQualification(String description) {

         if(description == null || description.trim().isEmpty()){
             return null;
         }

         Qualification newQualification = new Qualification(description);
		 if (qualifications.contains(newQualification)){
			 return null;
		 }
		 qualifications.add(newQualification);
    
		 return newQualification;
    }

	

	public Project createProject(String name, Set<Qualification> qualifications, ProjectSize size) {
		if(name == null || name.trim().isEmpty() || qualifications == null || qualifications.isEmpty() || size == null
		|| !this.getQualifications().containsAll(qualifications)){
			return null;
		}

		Project newProject = new Project(name,qualifications,size);
		projects.add(newProject);

		return newProject;
	}



	public void start(Project project) {
		if(project == null){
			throw new NullPointerException("Invalid null input");
		}

		if(project.getStatus().equals(ProjectStatus.PLANNED) || project.getStatus().equals(ProjectStatus.SUSPENDED) && project.getMissingQualifications().isEmpty()){
			project.setStatus(ProjectStatus.ACTIVE);
		} else {
			throw new IllegalArgumentException("Incorrect ProjectStatus");
		}

	}

	public void finish(Project project) {
			if(project == null) {
				throw new NullPointerException("Project cannot be null");
			}
					if(project.getStatus() != ProjectStatus.ACTIVE) {
				throw new IllegalArgumentException("Only active projects can be finished");
			}
			project.setStatus(ProjectStatus.FINISHED);
		
			Set<Worker> workersToUnassign = new HashSet<>(project.getWorkers());

			for(Worker worker : workersToUnassign) {
				unassign(worker, project);
		
				if(worker.getProjects().isEmpty()) {
					available.add(worker);
					assigned.remove(worker);
				}
			}
		
	}

	public void assign(Worker worker, Project project) {
		if(project == null) {
			throw new NullPointerException("project is null");
		}
		if(worker == null) {
			throw new NullPointerException("worker is null");
		}
		if(project.getWorkers().contains(worker)){
			throw new IllegalArgumentException("worker is already assigned");
		}
		if(!worker.isAvailable()){
			throw new IllegalArgumentException("Worker is not available");
		}
		if(project.getStatus() == ProjectStatus.FINISHED || (project.getStatus() == ProjectStatus.ACTIVE)){
			throw new IllegalArgumentException("Project is in the wrong state");
		}
		if(!project.isHelpful(worker)){
			throw new IllegalArgumentException("worker is not helpful");
		}
		if(worker.willOverload(project)){
			throw new IllegalArgumentException("project will overload worker");
		}
		if(!assigned.contains(worker)){
			assigned.add(worker);
		}
		
		project.addWorker(worker);
		worker.addProject(project);
		if(!worker.isAvailable()){
			available.remove(worker);
		}

	}

	public void unassign(Worker worker, Project project) {
		if(project.getWorkers().contains(worker) && worker.getProjects().contains(project)){
				project.removeWorker(worker);
				worker.removeProject(project);
			if(worker.getProjects().size() == 1){
				assigned.remove(worker);
				available.add(worker);

			}
			if(project.getStatus().equals(ProjectStatus.ACTIVE) && ((project.getWorkers().size() < 1 ) || (project.getMissingQualifications().contains(project.getRequiredQualifications())))){
				project.setStatus(ProjectStatus.SUSPENDED);
			}

		}
		else {
			throw new IllegalArgumentException("Worker is not present");
		}
	}

	public void unassignAll(Worker worker) {
		if (worker.getProjects().isEmpty()) {
			throw new IllegalArgumentException("Worker has no assigned projects");
		} else {
			Set<Project> projects = worker.getProjects();

			for (Project project : projects) {
				unassign(worker, project);
			}
		}
	}
}
