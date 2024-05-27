package edu.colostate.cs415.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import edu.colostate.cs415.dto.QualificationDTO;
import edu.colostate.cs415.model.Worker;


public class Qualification {

	private String description;
	private Set<Worker> workers;

	public Qualification(String description) {
		if(description == null){
			throw new NullPointerException("Invalid input of null");
		}
		else  {
			this.description = description;
			this.workers = new HashSet<>();
		}
	}



	@Override
	public boolean equals(Object other) {
		if(other == null){
			return false;
		}
		if(!(other instanceof Qualification)){
			return false;
		}
		if(this.description.equals(other.toString())){
			return this.description.equals(((Qualification)other).description);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return description.hashCode();
	}

	@Override
	public String toString() {
		return this.description;
	}

	public Set<Worker> getWorkers() {
		Set<Worker> copyOfWorkers = new HashSet<>(workers);
		return copyOfWorkers;
    }

	public void addWorker(Worker worker) {
		if((worker.getName().isEmpty())) {
			throw new IllegalArgumentException("Incorrect worker object");
		} else {
			workers.add(worker);
		}
	}

	public void removeWorker(Worker worker) {
    if ((worker.getName().isEmpty())) {
			throw new IllegalArgumentException("Incorrect worker object");
		} else {
		workers.remove(worker);
		}
	}

	public QualificationDTO toDTO() {
		String[] newWorkers = new String[this.workers.size()];

		int i = 0;
		QualificationDTO qualDTO = null;
		for (Worker worker : this.workers) {
			newWorkers[i] = worker.getName();
			i++;
		}

		qualDTO = new QualificationDTO(this.description, newWorkers);

		return qualDTO;
	}
}
