package edu.colostate.cs415.model;

import java.util.Set;

import javax.management.InvalidAttributeValueException;

import java.util.HashSet;

import edu.colostate.cs415.dto.ProjectDTO;
import edu.colostate.cs415.model.ProjectStatus;


public class Project {

	private String name;
	private ProjectSize size;
	private ProjectStatus status;
	private Set<Worker> workers;
	private Set<Qualification> qualifications;

	public Project(String name, Set<Qualification> qualifications, ProjectSize size) {
		if(qualifications == null || name == null || size == null){
			throw new NullPointerException("Invalid input of null");
		}
		else if(!name.isEmpty() && !qualifications.isEmpty()){
			Set<Qualification>copyOfQualifications = new HashSet<>(qualifications);
			this.qualifications = copyOfQualifications;
			this.name = name;
			this.size = size;
			this.status = ProjectStatus.PLANNED;
			this.workers = new HashSet<>();
		}
		else{ 
			throw new IllegalArgumentException("Invalid input");
		}
	}

	@Override
	public boolean equals(Object other) {
		if(other == null){
			return false;
		}
		if(!(other instanceof Project)){
			return false;
		}
		if(this.getName().equals(((Project)other).getName())){
			return this.getName().equals(((Project)other).getName());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		if (this.workers.isEmpty()){
			return this.name + ":" + 0 + ":" + this.status.toString();
		}
		return this.name + ":" + this.workers.size() + ":" + this.status.toString();
	}

	public String getName() {
		return this.name;
	}

	public ProjectSize getSize() {
		return this.size;
	}

	public ProjectStatus getStatus() {
		return this.status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public void addWorker(Worker worker) {
		if (worker == null){
			throw new NullPointerException();
		}
		else {
			workers.add(worker);
		}
	}

	public void removeWorker(Worker worker) {
		if (worker != null){
			workers.remove(worker);
		}
		else{
			throw new NullPointerException();
		}
	}

	public Set<Worker> getWorkers() {
		return this.workers;
	}

	public void removeAllWorkers() {
		if(workers.isEmpty()){
			throw new IllegalArgumentException("Invalid set");
		}
		else{
			workers.clear();

		}
	}

	public Set<Qualification> getRequiredQualifications() {
		return this.qualifications;
	}

	public void addQualification(Qualification qualification) {
		if(this.qualifications.contains(qualification)){
			throw new IllegalArgumentException("Invalid Input");
		}
		if(qualification == null ){
			throw new NullPointerException();
		}
		this.qualifications.add(qualification);

	}

	public Set<Qualification> getMissingQualifications() {
		Set<Qualification> workerQualifications = new HashSet<>();
		Set<Qualification> reqQualifications = this.qualifications;
		Set<Qualification> missingQualifications = new HashSet<>();

		for(Worker w: this.workers){
			for(Qualification q: w.getQualifications()){
				workerQualifications.add(q);
			}
		}

		int count;
		if(workerQualifications.size() == 0){
			for(Qualification q: reqQualifications){
				missingQualifications.add(q);
			}
			return missingQualifications;
		}
		
		for(Qualification q: reqQualifications){
			count = 0;
			for(Qualification workqual: workerQualifications){
				if(workqual.equals(q)){
					break;
				}
				else{
					count++;
					if(count == (workerQualifications.size())){
						missingQualifications.add(q);
					}

				}
			}
		}

		return missingQualifications;
	}

	public boolean isHelpful(Worker worker) {
		if (worker == null) {
			return false;
		}
		if (worker.getQualifications().isEmpty()) {
			return false;
		}

		for(Qualification qualification: getMissingQualifications()){
			if(worker.getQualifications().contains(qualification)){
			return true;
		}
		}

		return false;
	}

	public ProjectDTO toDTO() {
		String[] newWorkers = new String[this.workers.size()];


       int i = 0;
       for(Worker worker: this.workers){
           newWorkers[i] = worker.getName();
           i++;
       }


       String[] newQualifications = new String[this.qualifications.size()];


       int j = 0;
       for(Qualification qual: this.qualifications){
           newQualifications[j] = qual.toString();
           j++;
       }


       Set<Qualification> missingQualifications = this.getMissingQualifications();    
       String[] newMissingQualifications = new String[missingQualifications.size()];


       int a = 0;
       for(Qualification qual: missingQualifications){
           newMissingQualifications[a] = qual.toString();
           a++;
       }


       ProjectDTO projDTO = new ProjectDTO(this.name, this.size, this.status, newWorkers, newQualifications, newMissingQualifications);
       return projDTO;
	}

	
}
