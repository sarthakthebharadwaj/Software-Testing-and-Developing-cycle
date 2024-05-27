package edu.colostate.cs415.model;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import edu.colostate.cs415.dto.WorkerDTO;

public class Worker {

	public static final int MAX_WORKLOAD = 12;

	private String name;
	private double salary;
	private Set<Project> projects;
	private Set<Qualification> qualifications;

	public Worker(String name, Set<Qualification> qualification, double salary) {
		if(name == null || qualification == null){
			throw new NullPointerException("Invalid null");
		}
		this.name = name;
		Set<Qualification> copyOfQualifications = new HashSet<>(qualification);
		this.qualifications = copyOfQualifications;
		this.salary = salary;
		this.projects = new HashSet<Project>();
	}

    @Override
	public boolean equals(Object other) {
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		Worker worker = (Worker) other;
		return name.equals(worker.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return this.name + ":" + this.projects.size() + ":" + this.qualifications.size() + ":" + ((int)this.salary);
	}

	public String getName() {
		return this.name;
	}

	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Set<Qualification> getQualifications() {
		Set<Qualification> copyOfQualifications = new HashSet<>(qualifications);
    	return copyOfQualifications;
		}

	public void addQualification(Qualification qualification) {
		if(qualification == null || (qualifications.contains(qualification))){
			throw new IllegalArgumentException("Invalid qualification");
		}	
		else {

			qualifications.add(qualification);
		}
	}

	public Set<Project> getProjects() {
		Set<Project> copyOfProjects = new HashSet<>(projects);
		return copyOfProjects;
	}

	public void addProject(Project project) {
		if(project == null){
			throw new NullPointerException("Invalid project");
		}
		else{
			projects.add(project);
		}
	}

	public void removeProject(Project project) {
		if(project != null){
			projects.remove(project);
		}
		else{
			throw new NullPointerException("Invalid project");
		}
	}

	public int getWorkload() {
		int workload = 0;
		Set<Project> copyOfProjects = new HashSet<>(projects);
		for (Project project : copyOfProjects) {
			int work = project.getSize().getValue();
			workload += work;
		}
		return workload;
	}

	public boolean willOverload(Project project) {
		if(project == null){
			throw new NullPointerException("Invalid project");
		}
		if(project.getWorkers().contains(this)){
			return false;
		}
		int workload = this.getWorkload();
		int newWork = project.getSize().getValue();
		workload += newWork;
		if(workload > 12){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isAvailable() {
		if(getWorkload() >= 12) {
			return false;
		}
		return true;
	}

	public WorkerDTO toDTO() {
		String[] projectNames = this.projects.stream().map(Project::getName).toArray(String[]::new);
    
		String[] qualificationNames = this.qualifications.stream().map(Qualification::toString).toArray(String[]::new);
		
		int workload = this.getWorkload();

		WorkerDTO workerDTO = new WorkerDTO(this.name, this.salary, workload, projectNames, qualificationNames);
	
		return workerDTO;
	}
}
