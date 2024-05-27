# Qualifications
### 1. Qualification(String)

| Variable    | Characteristic   | Partition   | Value   |
|-------------|------------------|-------------|---------|
| description | A) size          | A1)=0       | ""      |
|             |                  | A2)0<       | "Ruby"  |
|             | ---------------- | ----------- | ------- |
|             | B) type          | B1)NULL     | null    |
|             |                  | B2)non NULL | "abc"   |
               
- The only input variable for the qualifications is the description and it has two characteristics those being the size and type.
- A1, A2, B1, and B2 are all parition blocks and A and B are characteristics
- A1 and A2 form a disjoint parition of the characteristic A. A is the size of the description which can either be equal to 0 or above 0.
- B1 and B2 form a disjoint partition of B. B is the type of description that can be entered into as either null or non null.


### 2. equals(Object other) (Qualification)

| Variable | Characteristic   | Partition       | Value                   |
|----------|------------------|-----------------|-------------------------|
| other    | A)type           | A1)null         | null                    |
|          |                  | A2)valid type   | type Qualification      |
|          |                  | A3)invalid type | String                  |
|          | ---------------- | -----------     | --------------------    |
|          | B)Equality       | A1)equal        | Same Qualification      |
|          |                  | A2)unequal      | Different Qualification |

- The only input variable other is described by the characteristics of type and equality to the qualification it is being tested on 
-  A, which is type, is partitioned into the disjoint paritions of null, a valid type, and an invlaid type.
-  B, which is equality, is paritioned into the disjoint parition of equal and unequal in relation to the state variable.

#### Base Choice Coverage

| Test |     |     | 
|------|-----|-----|
|Base  |A1   | B1  |
|      |A2   | B1  |
|      |A3   | B1  |
|      |A1   | B2  |
|      |A2   | B2  |
|      |A3   | B2  |


### 3.	public int hashCode() 
| Variable    | Characteristic        | Partition         | value                    | 
|-------------|-----------------------|-------------------|--------------------------|
| description | A) type               | A1) invalid       | description = null       |
|             |                       | A2) valid type    | Qualification("Thrifty") |

- hashCode has one variable, which is the qualification that will be turned into a hashcode.
- There is one characteristic, which is the type of the variable.
- Type is partitioned into whether the input is valid or invalid.

### 4. removeWorker(Object worker)

| Variable | Characteristic        | Partition         | Value                |
|----------|-----------------------|-------------------|----------------------|
| Worker<> | A)Relationship to set | A1)In the set     | []                   |
|          |                       | A2)Not in the set | [w1,w2]              |
|          | ----------------      | -----------       | -------------------- |
|          | B)Relationship        | A1)Empty          | w1                   |
|          |                       | A2)Not Empty      | w3                   |

- The only input variable worker is described by the characteristics of the relationship to the set and the relationship to the worker
- A, which is the relationship to the set, is partitioned into the disjoint paritions of in the set and not in the set.
- B, which is the relationship to the worker, is partitioned into the disjoint paritions of empty and not empty in relation to the state variable.

### 5. addWorker(Object worker)


| Variable | Characteristic        | Partition         | Value                |
|----------|-----------------------|-------------------|----------------------|
| Worker<> | A)Relationship to set | A1)In the set     | []                   |
|          |                       | A2)Not in the set | [w1,w2]              |
|          | ----------------      | -----------       | -------------------- |
|          | B)Relationship        | A1)Empty          | w1                   |
|          |                       | A2)Not Empty      | w3                   |

- The only input variable worker is described by the characteristics of the relationship to the set and the relationship to the worker
- A, which is the relationship to the set, is partitioned into the disjoint paritions of in the set and not in the set.
- B, which is the relationship to the worker, is partitioned into the disjoint paritions of empty and not empty in relation to the state variable.

### 6. public String toString() 
| Variable    | Characteristic        | Partition         | Value                                        |
|-------------|-----------------------|-------------------|----------------------------------------------|
| description | A) type               | A1) valid type    | description = "thrifty" description.toString |
|             |                       | A2) Invalid       | null                                         |

- The toString method has one variable, which is the description of the qualification being returned.
- Description has one characteristic, which is type.
- Type is partitioned into whether the description is a valid type or is invalid.


# Worker

### 1. public Worker(String name, Set<Qualification> qualification, double salary)


| Variable       | Characteristic | Partition                    | Value     |
|----------------|----------------|------------------------------|-----------|
| name           | A) type        | A1) NULL                     | null      |
|                |                | A2) non NULL                 | "name"    |
|                | B) length      | B1) =0 characters            | ""        |
|                |                | B2) >0 characters            | "name"    |
| qualifications | C) present     | C1) qualification is present | "Thrifty" |
|                |                | C2) is not present           | "bad"     |
| Salary         | D) Value       | D1) >0                       | -1.0      |
|                |                | D2) =0                       | 0.0       |
|                |                | D3) <0                       | 40000.0   |

- There are three input variables in the Worker constructor. These variables are the name of the worker, their qualifications and salary.

- A, which is type, is partitioned into whether the value for name is null value or non null.
- B, which is length, is partitioned into the length of the name. This tests whether the number of characters is 0, or greater than 0.
- C, which is present, is partitioned into whether the qualifications are present in the company's set of qualifications.
- D, which is walue, is partitioned into what value is entered for salary. These partitions are less than 0, 0, or greater than 0.
  
### 2. getWorkload()


| Variable | Characteristic        | Partition         | Value                |
|----------|-----------------------|-------------------|----------------------|
| workload | A) size of workload   | A1)=0             | ""                   |
|          |                       | A2)>0             | "workload"           |
|          |                       | A2) non null      | "workload"           |
|          |                       | A3) null          | null                 |


- The only variable workload is the amount of work assigned to a worker given the size of projects assigned to the worker. 
- A, size of workload, is partitioned into 0, greater than 0 , and null or non null. 

### 3. isAvailable(): boolean

| Variable       | Characteristic                    | Partition                 | Value         |
|----------------|-----------------------------------|---------------------------|---------------|
|  isAvailable   | C) Worker workload                | C1) < 12                  | true          |
|                |                                   | *C2) >= 12                | false         |

- isAvailable has the state variable of isAvailable, with one characteristic relating to the workload.
- The characteristic of workload is partitioned into whether the workload is equal to or less then 12, or is greater than 12.
- Depending on these partitions, it is calculated whether the worker is available as a boolean. 

#### Base Choice Coverage

| Test | Oracle                                    |
|------|-------------------------------------------|
| T1   | Returns true if the total workload < 12   |
| T2   | Returns false if the total workload >= 12 |

### 4. willOverload(): boolean

| Variable | Characteristic                 | Partition                          |
|----------|--------------------------------|------------------------------------|
| Project  | A) null or not                 | A1) NULL                           | 
|          |                                | *A2) non NULL                      | 
|          | B) P's effect on workloads     | B1) becomes overloaded (>12)       | 
|          |                                | *B2) is not overloaded (<=12)      | 
| Worker   | C) Relationship to the project | C1) Is a part of the project       | 
|          |                                | *C2)  Is not a part of the project | 

-willOverload has two variables Project, and Worker. The project is the project that might overload the worker and the worker is who the project would overload
-Projects characteristics are(becuase it is an object) null or non null, and it's effect on the workload. For null or non null it is partitioned into those two disjoint categories. For effect on workload it either becomes overloaded or does not become overloaded, changing the workload to something >12 or <=12
-Worker has oone characteristic which is the relation to the project. This is paritioned into whether it is a part of the project or not a part of the project.

#### Base Choice Coverage

| Test |     |     |     | 
|------|-----|-----|-----|
| testWillOverload   | *A2 | *B2 | *C2 | 
| testWillOverloadNull   | A1  | *B2 | *C2 |  
| testWillOverloadTrue   | *A2 | B1  | *C2 | 
| testWillOverloadAlreadyOnProject   | *A2 | *B2 | C1  | 

 ### 5. public int hashCode()
| Variable | Characteristic        | Partition         | Value                     |
|----------|-----------------------|-------------------|---------------------------|
| name     | A) type               | A1) invalid       |      null                 |
|          |                       | A2) valid         |  Worker("name", qs, sal)  |

- hashCode has one variable, which is the name of the worker being turned into a hashcode.
- The name variable has one characteristic, which is type.
- Type is partitioned into whether the name is valid or not.

### 6. public void addProject(Project project) 
| Variable | Characteristic        | Partition            | Value                                                            |
|----------|-----------------------|----------------------|------------------------------------------------------------------|
| project  | A) type               |  A1) valid type      | project = Project("name", qs, sal) worker.addProject(project)    |
|          |                       |  A2) invalid type    | null                                                             |

- addProject has one input variable, which is the project to be added. 
- The project has one characteristic which is the type. 


### 7. public void removeProject(Project project)
| Variable | Characteristic        | Partition                 | Value                                                         |
|----------|-----------------------|---------------------------|---------------------------------------------------------------|
| project  |  A) type              | A1) valid type            | project = Project("name", qs, sal) worker.addProject(project) |
|          |                       | A2) invalid type          |    null                                                       |
|          | B) project presence   | B1) project exists        | projects<project>  worker.removeProject(project)              |
|          |                       | B2) project doesn't exist | projects<> worker.removeProject(project)

- removeProject has one input variable, which is the project to be removed.
- There are two characteristics for project, which are type and project presence.
- Type is partitioned into whether the input is a valid type or not.
- Project presence is partitioned into whether the project is present in the set or is not present.

  
### 8. public boolean equals(Object other)
| Variable | Characteristic        | Partition                 | Value                                   |
|----------|-----------------------|---------------------------|-----------------------------------------|
| other    |  A) Valid             |A1) valid type             | type Worker                             |
|          |                       |A2) invalid type           | String                                  |
|          |                       |A3) null                   | null                                    |
|          |  B) Equal             |B1) Equal                  | Same Worker                             |
|          |                       |B2) Not Equal              | Different Worker                        |

- The only input variable other is described by the characteristics of type and equality to the worker it is being tested on 
-  A, which is Valid, is partitioned into the disjoint paritions of null, a valid type, and an invalid type.
-  B, which is Equal, is paritioned into the disjoint parition of equal and unequal in relation to the state variable.

#### Base Choice Coverage

| Test |     |     | 
|------|-----|-----|
|Base  |A1   | B1  |
|      |A2   | B1  |
|      |A3   | B1  |
|      |A1   | B2  |
|      |A2   | B2  |
|      |A3   | B2  |


# Project
### 1. public Project(String name, Set<Qualification> qs, ProjectSize size)
Input Space Paritioning(base blocks are marked with a *


| Variable           | Characteristic                          | Partition                      | Value              |
|--------------------|-----------------------------------------|--------------------------------|--------------------|
| name               | A) null or non null                     | A1) null                       | null               |
|                    |                                         | *A2) non null                  | "Project1"         |
|                    | B) Current size with respect to 0       | B1) =0                         | ""                 |
|                    |                                         | *B2) >0                        | "Project1"         |
| Set Qualifications | C) null or non null                     | C1) null                       | null               |
|                    |                                         | *C2) non null                  | <"Java", "Python"> |
|                    | D) Qualification in relation to company | D1) *In company qualifications | <"Java", "Python"> |
|                    |                                         | D2) Some in company some not   | <"Java", "banana"> |
|                    |                                         | D3) none in company            | <"banana">         |
|                    | E) Empty or non empty                   | E1) Empty                      | <>                 |
|                    |                                         | *E2) Non empty                 | <"Java", "Python"> |
| Project size       | F) Null or non null                     | F1) null                       | null               |
|                    |                                         | *F2) non null                  | PLANNED            |

- Name has the characteristics null or non null, because it is an object and can therefore be null, and current size with respect to 0, to test the length of the string.
-- Null or non null are partitioned into two partition that are disjoint and are null and non null
-- Current size with respect to 0 has two disjoint partitions that are =0 or >0. There is no <0 because strings cannot have a length less that 0.
  
- Set Qualifications has characteristics null or non null, because it is an object and can therefore be null, Qualification in relation to company, this is because the qualifications set that is added must have qualifications that are in the company, and Empty or non empty, this is to test whether the set has elements.
-- Null or non null are partitioned into two partition that are disjoint and are null and non null
--  Qualification is partitioned into three disjoint paritions which are all qualifications are inocorrect, some of the qualifications are incorrect, and no qualifications are incorrect.
-- Empty or non empty has two partitions which are empty and non empty. A set cannot be technically 'full' so there is no partition for a set being full.

- Project size has one characteristic which is null or non null, this is because it is an object and can therefore be null.
-- Null or non null are partitioned into two partition that are disjoint and are null and non null

#### Base Choice Coverage
| Test |     |     |     |     |     |     | Oracle |
|------|-----|-----|-----|-----|-----|-----|--------|
| happyPathForProjectConstructor   | *A2 | *B2 | *C2 | *D1 | *E2 | *F2 | PASS   |
| testSetNameisNull   | A1  |     | *C2 | *D1 | *E2 | *F2 | FAIL   |
| testSetNameisLength0   | *A2 | B1  | *C2 | *D1 | *E2 | *F2 | FAIL   |
| testSetQualificationsNull   | *A2 | *B2 | C1  |     |     | *F2 | FAIL   |
| T5   | *A2 | *B2 | *C2 | D2  | *E2 | *F2 | FAIL   |
| T6   | *A2 | *B2 | *C2 | D3  | *E2 | *F2 | FAIL   |
| T7   | *A2 | *B2 | *C2 | *D1 | E1  | *F2 | FAIL   |
| testSetProjectSizeIsNotNull   | *A2 | *B2 | *C2 | *D1 | *E2 | F1  | FAIL   |

### 2. equals(Object other) (Project)

| Variable | Characteristic   | Partition       | Value                |
|----------|------------------|-----------------|----------------------|
| other    | A)type           | A1)null         | null                 |
|          |                  | A2)valid type   | type Project         |
|          |                  | A3)invalid type | String               |
|          | ---------------- | -----------     | -------------------- |
|          | B)Equality       | A1)equal        | Same Project         |
|          |                  | A2)unequal      | Different Project    |

- The only input variable other is described by the characteristics of type and equality to the project it is being tested on
- A, which is type, is partitioned into the disjoint paritions of null, a valid type, and an invlaid type.
- B, which is equality, is paritioned into the disjoint parition of equal and unequal in relation to the state variable.

### 3. getMissingQualifications(Object other) (Project)
| Variables | Characteristics              | Partitions                      |
|-----------|------------------------------|---------------------------------|
| Workers   | A) meeting of qualifications | A1) Meets qualifications        |
|           |                              | *A2) Not all qualifications met |
|           | B) Number of qualificatioons | B1) 0                           |
|           |                              | B2) 1                           |
|           |                              | *B3) <1                         |


#### Base Choice Coverage
| Test |     |     |
|------|-----|-----|
| testMissingQualificationsWithQualsMissingAndMoreThanOneQualifcation   | *A2 | *B3 |
| testMissingQualificationsWithMoreThanOneQualifciaton   | A1  | *B3 |
| missingQualificationWhenQualificationsisZero   | *A2 | B1  |
| missingQualificationWhenQualificationsisOne   | *A2 | B2  |

 ### 4. public int hashCode()
| Variable | Characteristic        | Partition         | Value                     |
|----------|-----------------------|-------------------|---------------------------|
| name     | A) type               | A1) invalid       |      null                 |
|          |                       | A2) valid         |  Project("name", qs, sal) |

- hashcode has one variable, which is the name of the project. This variable has one characteristic: type.
- Type is partitioned into whether the name is valid or invalid. 

 ### 5. public void addQualification(Qualification qualification)
| Variable     | Characteristic        | Partition                        |
|--------------|-----------------------|----------------------------------|
| Qualification| A) Nullity            | A1) null                         |   
|              |                       | A2)*not NULL                     |  
|              | B) Duplicates         | B1) already exists               |
|              |                       | B2)* doesnt exists in Project set|

- addQualification has one variable, the qualification to be added.
- The qualification is divided into two characteristics, the nullity and duplicate state.
- The nullity characteristic is split into two partitions: whether the variable is null or not null.
- The Duplicates characterstic is split into two partition: whether the qualification already exists or not.

#### Base Choice Coverage
| Test |     |     |Oracle|
|------|-----|-----|------|
| testAddNewQualifications   | *A2 | *B2  |
| testAddQualificationsWithDuplication   | *A2 |  B1 |fail
| testAddQualificationsWithNull   | A1  | *B2 |fail

### 6. addWorker(Worker w)

| Variable           | Characteristic                          | Partition                      | Value                      |
|--------------------|-----------------------------------------|--------------------------------|----------------------------|
| Worker w           | A) Null or non-null                     | A1) Null                       | null                       |
|                    |                                         | *A2) Non-null                  | Worker object              |
|                    | B) Already assigned to the project      | B1) Yes                        | Worker already in project  |
|                    |                                         | *B2) No                        | Worker not in project      |
|                    | C) Meets project and company constraints| C1) Yes                        | Worker meets constraints   |
|                    |                                         | *C2) No                        | Worker violates constraints|

- addWorker has one variable, which is the worker to be added.
- Worker has three characteristics, Null or non-Null, Already assigned to the project, and meets project and company constraints.
- Null or Non-null is partitioned into whether the worker is null or not.
- Already assigned to the project is partitioned into a boolean represented as yes or no.
- Meets project and company constraints is partitioned into a boolean represented as yes or no.

#### Base Choice Coverage
| Test |     |     |     | Oracle |
|------|-----|-----|-----|--------|
| T1   | *A2 | *B2 | C1  | PASS   |
| T2   | A1  |     |     | FAIL   |
| T3   | *A2 | B1  |     | FAIL   |
| T4   | *A2 | *B2 | C2  | FAIL   |

### 7. removeWorker(Worker w)

| Variable           | Characteristic                          | Partition                      | Value                             |
|--------------------|-----------------------------------------|--------------------------------|-----------------------------------|
| Worker w           | A) Null or non-null                     | A1) Null                       | null                              |
|                    |                                         | *A2) Non-null                  | Worker object                     |
|                    | B) Assigned to the project              | B1) No                         | Worker not in project             |
|                    |                                         | *B2) Yes                       | Worker in project                 |
|                    | C) Impact on project and company        | C1) No impact                  | Removal leaves constraints intact |
|                    |   constraints                           |                                |                                   |
|                    |                                         |*C2) Impact                     | Removal violates constraints      |

- removeWorker has one variable, which is the worker to be removed.
- Worker has three characteristics, Null or non-null, Assigned to the project, and Impact on project and company constraints.
- Null or non-null is partitioned into whether the variable is null or not.
- Assigned to the project is partitioned into a boolean represented as yes or no for whether it is assigned.
- Impact on project and company constraints is partitioned into a boolean of whether the removal will impact constraints or not.

#### Base Choice Coverage
| Test |     |     |     | Oracle |
|------|-----|-----|-----|--------|
| T1   | *A2 | *B2 | C1  | PASS   |
| T2   | A1  |     |     | FAIL   |
| T3   | *A2 | B1  |     | FAIL   |
| T4   | *A2 | *B2 | C2  | FAIL   |


# Company
### 1. public Set<Worker> getUnavailableWorkers()

| Variable  | Characteristic  | Partition         | Values                                              |
|-----------|-----------------|-------------------|-----------------------------------------------------|
| employees | A) Availability | A1) unavailable   | employees<"John", "Will"> available<>               |         
|           |                 | A2) available     | employees<"John", "Will"> available<"John", "Will"> |
| size      | B) current size | B1)  empy set     | employees<"John", "Will"> available<"John", "Will"> |
|           |                 | B2) non empty set | employees<"John", "Will"> available<>               |

- There is one input variable, which is the set of employees that work for the company. Each employee is then checked if they are
available. If they are not, they are added to the unavailable set.
- There is a state variable in size. If there are no available workers, then all workers should be added to the unavailable set.
  If there are no unavailable workers, then the unavailable set should return as empty.


### 2. 	public void unassign(Worker worker, Project project)
| Variable | Characteristic    | Partition                               | Values                                                              |
|----------|-------------------|-----------------------------------------|---------------------------------------------------------------------|
| worker   | A) Exists         | A1) Worker exists                       | employees<"Bill"> unassign("Bill", "work")                          |
|          |                   | A2) Worker does not exist               | employees<"Mat">     unassign("Bill", "work")                       |
|          | B) Is assigned    | B1) Worker is assigned to project       | assigned<"Bill">   unassign("Bill", "work")                         |
|          |                   | B2) Worker is not assigned to project   | employees<"Bill", "Mat"> assigned<"Mat">  unassign("Bill", "work")  |
| project  | C) Valid          | C1) Valid project input                 | Project<"work"> unassign("Bill", "work")                            |
|          |                   | C2) Invalid project input               | Project<"work"> unassign("Bill", 7)                                 |
|          | D) status         | D1) ACTIVE                              | project.ACTIVE                                                      |
|          |                   | D2) SUSPENDED                           | project.SUSPENDED                                                   |
|          |                   | D3) FINISHED                            | project.FINISHED                                                    |
|          |                   | D4) PLANNED                             | project.PLANNED                                                     |
|          | E) Qualifications | E1) Required qualifications are met     | requiredqual = <"thrifty"> project.getQualifications("thrifty")     |
|          |                   | E2) Required qualifications are missing | requiredqual = <"thrifty">   project.getQualification("notthrofty") |
| Size     | F) Worker Size    | F1) Worker set is empty                 | employees<>                                                         |
|          |                   | F2) Worker set is not empty             | employees<"Bill">                                                   |
|          | G) Project Size   | G1) Project set is empty                | Project<>                                                           |
|          |                   | G2) Project set is not empty            | Projects<"work">                                                    |

-There are three variables for unassign, the worker input variable, the project input variable and the size state variable.
-Worker has the characteristics of the worker existing and the worker being assigned. Both characteristics are partitioned into true and false.
-Project has the characteristics of valid, partitioned into true and false. It also has the characteristic of status, partitioned into the different states that a project can be in. Lastly, there is the characteristic of qualifications, which is partitioned into whether the required qualifications are met or not.
-Size has the characteristics of worker size and project size, both are partitioned into whether the set is empty or not.

#### Unassign BCC

| Test |    |    |    |    |    |    |    | Oracle |
|------|----|----|----|----|----|----|----|--------|
| testUnassign   | A1 | B1 | C1 | D1 | E1 | F2 | G2 | PASS   |
| testUnassignNotAssigned   | A2 | B1 | C1 | D1 | E1 | F2 | G2 | FAIL   |
| testUnassignAvailability   | A1 | B2 | C1 | D1 | E1 | F2 | G2 | FAIL   | 
| testUnassignProjectStatus   | A1 | B1 | C2 | D1 | E1 | F2 | G2 | FAIL   |
| testAssignNullWorker   | A1 | B1 | C1 | D2 | E1 | F2 | G2 | PASS   |
| T6   | A1 | B1 | C1 | D3 | E1 | F2 | G2 | PASS   |
| T7   | A1 | B1 | C1 | D3 | E1 | F2 | G2 | PASS   |
| T8   | A1 | B1 | C1 | D4 | E1 | F2 | G2 | PASS   |
| T9   | A1 | B1 | C1 | D1 | E2 | F2 | G2 | PASS   |
| T10  | A1 | B1 | C1 | D4 | E1 | F1 | G2 | FAIL   |
| T11  | A1 | B1 | C1 | D4 | E1 | F2 | G1 | FAIL   |

### 3. public equals(Object other) (Project)

| Variable  | Characteristic  | Partition         | Values                                              |
|-----------|-----------------|-------------------|-----------------------------------------------------|
| employees | A) Availability | A1) unavailable   | employees<"John", "Will"> available<>               |         
|           |                 | A2) available     | employees<"John", "Will"> available<"John", "Will"> |
| size      | B) current size | B1)  empy set     | employees<"John", "Will"> available<"John", "Will"> |
|           |                 | B2) non empty set | employees<"John", "Will"> available<>               |

- There is one input variable, which is the set of employees that work for the company. Each employee is then checked if they are
  available. If they are not, they are added to the unavailable set.
- There is a state variable in size. If there are no available workers, then all workers should be added to the unavailable set.
  If there are no unavailable workers, then the unavailable set should return as empty.


### 4. public Company(String name)
| Variable | Characteristic | Partition   | Value          |
|----------|----------------|-------------|----------------|
| name     | A) validity    | A1) Valid   | "example inc." |
|          |                | A2) Invalid | null           |

- Name has the characteristic of validity, partitioned into valid and invalid.
- The valid partition is that the name is a string
- The invalid partition is that the value is not in a valid form, such as null

### 5. public Set<Worker> assign()
| Variable | Characteristic        | Partition                 | 
|----------|-----------------------|---------------------------|
| Worker   | A) Availability       | *A1) is available         |
|          |                       | A2) not available         |
|          | B) Overloaded         | B1) will overload         |
|          |                       | *B2) will not overload    |
|          | C) Helpfulness        | *C1)is helpful            |
|          |                       | C2)is not helpful         |
|          | D)relation to project | *D1) not already assigned |
|          |                       | D2) Already assigned      |
| Project  | E) State              | E1) ACTIVE                |
|          |                       | E2) FINISHED              |
|          |                       | *E3) PLANNED              |
|          |                       | E4) SUSPENDED             |

- assign has two variables, worker and Project.
- Worker is the worker that is being assigned to a project.
- Project is the project that a worker is being assigned to.
- Worker has four characteristics: the availability, overload, helpfulness and relation to project.
- Availability is partitioned into whether a worker is available or not.
- Overloaded is partitioned into whether the worker will be overloaded or not.
- Helpfulness is partitioned into whether the worker is helpful to the project or not. 
- Relation to the project is partitioned into whether the worker is already assigned to the project or not.
- Project has one characteristic, which is the project's state.
- State is partitioned into the states a project can have: ACTIVE, FINISHED, PLANNED and SUSPENDED

#### Base Choice Coverage

| Test |     |     |     |     |     |
|------|-----|-----|-----|-----|-----|
| testAssignHappyPath   | *A1 | *B2 | *C1 | *D1 | *E3 | 
| testAssignNotAvailable   | A2  | *B2 | *C1 | *D1 | *E3 | 
| testAssignWillOverload   | *A1 | B1  | *C1 | *D1 | *E3 | 
| testAssignIsNotHelpful   | *A1 | *B2 | C2  | *D1 | *E3 |
| testAssignIsAlreadyAssigned   | *A1 | *B2 | *C1 | D2  | *E3 | 
| testAssignState   | *A1 | *B2 | *C1 | *D1 | E1  | 
| testAssignState   | *A1 | *B2 | *C1 | *D1 | E2  | 
| testAssignState   | *A1 | *B2 | *C1 | *D1 | E3  | 


### 6. public void finish(P : Project)
| Variable  | Characteristic     | Partition     | Value          |
|-----------|--------------------|---------------|----------------|
| Project P | A) null or nonnull | A1) null      | null           |
|           |                    | *A2) non null | Project object |
|           | B) Project Status  | B1) Active    | Active         |
|           |                    | B2) Suspended | Suspended      |
|           |                    | B2) Planned   | Planned        |
|           |                    | B2) Finished  | Finished       |

- finish has one variable, project.
- Project has two characteristics, null or nonnull, and project status.
- Null or nonull is partitioned into whether the variable is null or not.
- Project Status is partitioned into the statuses of the project: ACTIVE, SUSPENDED, PLANNED and FINISHED.

### 7. getAvailableWorkers()
| Variable | Characteristic        | Partition                 | Value                                             |
|----------|-----------------------|---------------------------|---------------------------------------------------|
| Worker   | A) Availability       | A1) is available          | workers<"John", "Will"> available<"John", "Will"> |
|          |                       | *A2) not available        | workers<"John", "Will"> available<>               |
|          | B) Overloaded         | B1) will overload         | workers<"John", "Will"> available<"John", "Will"> |
|          |                       | *B2) will not overload    | workers<"John", "Will"> available<>               |
|          | C) Helpfulness        | *C1)is helpful            | workers<"John", "Will"> available<"John", "Will"> |
|          |                       | C2)is not helpful         | workers<"John", "Will"> available<>               |
|          | D)relation to project | *D1) not already assigned | workers<"John", "Will"> available<"John", "Will"> |
|          |                       | D2) Already assigned      | workers<"John", "Will"> available<>               |
| Project  | E) State              | E1) ACTIVE                | workers<"John", "Will"> available<"John", "Will"> |
|          |                       | E2) FINISHED              | workers<"John", "Will"> available<>               |
|          |                       | *E3) PLANNED              | workers<"John", "Will"> available<"John", "Will"> |
|          |                       | E4) SUSPENDED             | workers<"John", "Will"> available<"John", "Will"> |

- There are two variables for getAvailableWorkers, the worker input variable and the project input variable.
- Worker has the characteristics of availability, overloaded, helpfulness, and relation to the project. All of these are partitioned into true and false.
- Project has the characteristics of state, partitioned into the different states that a project can be in.
- The worker set is also partitioned into whether it is empty or not.
- The project set is also partitioned into whether it is empty or not.

### 8. toString()

| Variable | Characteristic | Partition     | Value          |
|----------|----------------|---------------|----------------|
| Company  | name           | A1) Null      | null           |
|          |                | A2) empty     | ""             |
|          |                | A3) non empty | "example inc." |

- The only input variable is the company object. It is partitioned into whether it is null or not.
- The null partition is that the company is null
- The not null partition is that the company is not null

#### Base Choice Coverage

| Test |     |     | Oracle                                         |
|------|-----|-----|------------------------------------------------|
| T1   | *A2 | B1  | Project is marked FINISHED, workers unassigned |
| T2   | A1  |     | Throws NullPointerException                    |
| T3   | *A2 | B2  | No change in project status                    |
| T4   | *A2 | B3  | No change in project status                    |
| T5   | *A2 | *B4 | No change in project status                    |


### 9. public Set<Qualification> getMissingQualifications() 

Input Space Partitioning(base blocks marked with *

| Variable | Characteristic               | Partition                       | Value              |
|----------|------------------------------|---------------------------------|--------------------|
| Workers  | A) Meeting of Qualifciations | A1) Meets all qualifciations    | []                 |
|          |                              | *A2) Not all Qualifications met | [“java”, “python”] |
|          | B) Number of qualifications  | B1)0                            | []                 |
|          |                              | B2)1                            | [“java”]           |
|          |                              | *B3)<1                          | [“java”, “python”] |

- Workers qualifications can either meet all the qualifications or none of them 
- Workers can either have zero qualifications, one qualification, or more than one qualification


#### Base Choice Coverage

| Test |     |     | Oracle |
|------|-----|-----|--------|
| testMissingQualificationsWithMoreThanOneQualifciaton   | *A2 | *B3 | PASS   |
| testMissingQualificationsWithQualsMissingAndMoreThanOneQualifcation   | A1  | *B3 | PASS   |
| missingQualificationWhenQualificationsisZero   | *A2 | B1  | PASS   |
| missingQualificationWhenQualificationsisOne   | *A2 | B2  | PASS   |

- All conditions pass as it will return a correct set of missing or not missing qualifications every time

### 10. public void start() 

Input Space Partitioning(base blocks marked with *

| Variable       | Characteristic      | Partition     | Value |
|----------------|---------------------|---------------|-------|
| ProjectStatus  | A)status            | A1)*PLANNED   | True  |
|                |                     | A2) SUSPENDED | True  |
|                |                     | A3) ACTIVE    | False |
|                |                     | A4) FINISHED  | False |
| Qualifications | B) Met or not Met   | B1)*Met       | True  |
|                |                     | B2) Not Met   | False |
| Project        | C) Null or non null | C1) Null      | False |
|                |                     | C2)* Non null | True  |

- There is one input variable, Project, and three state variables, ProjectStatus, and Qualifications 
- ProjectStatus is disjointly partitioned into its four statuses PLANNED, SUSPENDED, ACTIVE, FINISHED
- Qualifications is disjointly partitioned into whether the qualifications are met by the workers or not. 
- Project is disjointly partitioned into null or non null types.


#### Base Choice Coverage
| Test |     |     |     | Oracle |
|------|-----|-----|-----|--------|
| testStartWithPlannedMetQualifcications   | *A1 | *B1 | *C2 | PASS   |
| testStartWithSuspendedMetQualifcications   | A2  | *B1 | *C2 | PASS   |
| testStartWithActiveMetQualifcications   | A3  | *B1 | *C2 | FAIL   |
| testStartWitFinishedhMetQualifcications   | A4  | *B1 | *C2 | FAIL   |
| testStartWithPlannedNotMetQualifcications   | *A1 | B2  | *C2 | FAIL   |
| testStartWithNull   | *A1 | *B1 | C1  | FAIL   |

### 11. public Project createProject(String name, Set<Qualification> qualifications, ProjectSize size)
 

| Variable       | Characteristic               | Partition                            | 
|----------------|------------------------------|--------------------------------------|
| Name.          | A)Null or not null  or empty | A1)Null                              |
|                |                              | *A2) not null valid                  |
|                |                              | A3) Empty                            |
| Qualifications | B) Validity                  | B1) Null                             | 
|                |                              | *B2) Non empty and all in Company    | 
|                |                              | B3) Non empty but not in Company set |
| Project SIZE.  | C) Validity                  | C1) null                             | 
|                |                              | *C2) not null                        | 

- createProject has three variables: Name, Qualifications, and the project size.
- Name has one characteristic, which is Null or not null or empty. This characteristic is partitioned into whether the name is null, non-null or is empty.
- Qualifications has one characteristic, which is validity. Validity is partitioned into whether the qualifications set is null, not empty and all in the company, or non empty but not in the company set.
- Project SIZE has one characteristic, validity, which is partitioned into whether the size is null or non-null.

#### Base Choice Coverage

| Test |     |     | Oracle | BCC  |
|------|-----|-----|--------|------|
| testCreateProject1   | A3  | *B2 | *C2    | Fail |
| testCreateProject2   | A2  | B1  | *C2    | Fail |
| testCreateProject3   | *A2 | *B2 | C1     | Fail |
| testCreateProject4   | *A2 | *B2 | *C2    | Pass |

### 12. public Worker createWorker(String name, Set<Qualification> qualifications, double salary)

| Variable         | Characteristic           | Partition                                  | 
|------------------|--------------------------|--------------------------------------------|
| Name             | NULL or NOT NULL         | A1) NULL                                   |
|                  |                          | *A2) Not NULL (valid)                      |
|                  |                          | A3) Empty String                           |
| Qualification.   | Validity                 | B1) Null                                   |
|                  |                          | B2) Empty                                  |
|                  |                          | B3) *Not Empty and subset of comapny       |
|                  |                          | qualifications                             |
|                  |                          | B4) Non Empty and not a subset of company  |
|                  |                          | qualifications                             |
|                  |                          | B5) Conatains invalid qualification Object |
| Salary           | Validity                 | C1) Negative or Zero                       |
|                  |                          | *C2) Positive(valid salary)                |
| Worker Creation. | Ability to create worker | D1) Creation fails due to invalid input.   |
|                  |                          | *D2) Creation succeeds                     |
-------------------------------------------------------------------------------------------------

- createWorker has four variables: Name, Qualification, Salary and worker creation.
- Name has one characteristic, null or not null, which is partitioned into whether the name is null, non-null, or empty.
- Qualification has one characteristic, validity, that is partitioned into whether the qualification set is null, empty, not empty and a subset of company, or non empty and not a subset of company.
- Salary has one characteristic, validity, which is partitioned into whether the salary is a positive number, or is negative or zero.
- Worker creation has one characteristic, ability to create worker, which is paritioned into whether the creation fails due to an invalid input or successfully creates the worker.


#### Base Choice Coverage:

| Test | -   | -   | -   | Oracle | BCC  |
|------|-----|-----|-----|--------|------|
| create_worker_test1.  | *A2 | *B3 | *C2 | *D2    | Pass |
| create_worker_test2.  | A1. | *B3 | *C2 | D1     | Fail |
| create_worker_test3.  | *A2 | *B3 | C1  | D1     | Fail |
| create_worker_test4.  | *A2 | B4  | *C2 | D1     | Fail |




#### Base Choice Coverage

| Test | ---- | ----- |     | Oracle |
|------|------|-------|-----|--------|
| testCreateProject1   | A2*  | B3*   | C1  | Fail   |
| testCreateProject2   | A2*  | B1    | C2* | Fail   |
| testCreateProject3   | A1   | B3*   | C2  | Fail   |
| testCreateProject4   | A2*  | B3*   | C2* | Pass   |

### 13. public boolean equals(Object other)

| Variable | Characteristic   | Partition         |
|----------|------------------|-------------------|
| ObjectO  | A) Which type    | A1)*same type     |
|          |                  | A2)different type |
|          | B) Null          | B1) Null          |
|          |                  | B2)*Not null      |
| Name     | C) Do they match | C1)Different      |
|          |                  | *C2) Same         |
-----------------------------------------------------------------------

- equals has two variables, object0 and Name.
- Object0 has two characteristics, type and null.
- Type is partitioned into whether the compared values are the same type or not.
- Null is partitioned into whether the object is null or not.
- Name has one characteristic, do they match, which is partitioned into whether the name and object match or not.

#### Base Choice Coverage

| Test | ---- | ----- | -------- | oracle |
|------|------|-------|----------|--------|
| testforquals1   | A1*  | B2*   | C2*      | pass   |
| testforquals2   | A2   | B1    | C1       | fail   |
| testforquals3   | A1   | B3*   | C1       | fail   |
| testforquals4   | A1*  | B2*   | C1       | fail   |



### 14. public void addWorker(Worker worker)

| Variable      | Characteristic  | Partition                           |
|---------------|-----------------|-------------------------------------|
| Worker        | Null or Notnull | A1) NUll                            |
|               |                 | A2)* Not Null                       |
-----------------------------------------------------------------------

- As per the method defination everything else is checked by the caller and to be determined by them .

| Test |     | Oracle |
|------|-----|--------|
| T1   | A1  | Fail   |
| T2   | A2* | Pass   |
