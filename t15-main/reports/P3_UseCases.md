## Use Cases

#### Use Case Name: View Company Qualifications 

##### Summary 
- User uses the system to view qualifications through the qualifications tab
##### Actor
- End user of the system
##### Precondition 
- The system is displaying the homepage with all the tabs available, as well as displaying the welcome name
##### Description 
- User clicks on the Qualifications tab in the header
- The system will render a new page where it will display all of the companies qualifications

##### Description 
-Two column representation 

|Actor Actions| System Reponse|
|-------------|---------------|
| Go to the homepage ||
||Display the company name |
| Navegate to the upper section of the page ||
| Click on the Qualifications tab ||
|| Display Qualifications by the description in a list |

##### Post Condition:

- The system displays all the company's employed workers in a user-readable format on the new page , if clicked it takes to the Workers Tab.

#### Use Case Name: View Company Employed Workers  

##### Summary 
- User uses the system to view employed workers through the workers tab
##### Actor
- End user of the system
##### Precondition 
- The system is displaying the homepage with all the tabs available, as well as displaying the welcome name
##### Description 
- User clicks on the Workers tab in the header
- The system will render a new page where it will display all of the companies employed workers

##### Description 
-Two column representation 

|Actor Actions| System Reponse|
|-------------|---------------|
| Go to the homepage ||
||Display the company name |
| Navegate to the upper section of the page ||
| Click on the Workers tab ||
|| Display Worker by their name in a list |

##### Post Condition:
- The system displays all the company's employed workers in a user-readable format on the new page , if clicked it takes to the Qualifications or projects Tab.

#### Use Case Name: View Company Projects

##### Summary
- User uses the system to view projects through projects tab
##### Actor
- End user of the system
##### Precondition
- The system is displaying the homepage with all the tabs available, as displaying the welcome name
##### Description
- User clicks on the Projects tab in the header
- The system will render a new page where it will display all of the companies projects

##### Description 
-Two column representation 

|Actor Actions| System Reponse|
|-------------|---------------|
| Go to the homepage ||
||Display the project name |
| Navegate to the upper section of the page ||
| Click on the Projects tab ||
|| Display Projects by name and status in a list |

##### Post Condition:

- The system displays all the company's projects in a user-readable format on the new page , if clicked it takes user to workers page or qualifications tab depending on where the user clicks.


#### Use Case Name: View Company Qualifications with Details 

##### Summary 
- User uses the system to view qualifications through the qualifications tab
##### Actor
- End user of the system
##### Precondition 
- The system is displaying the homepage with all the tabs available, as well as displaying the welcome name
##### Description 
- User clicks on the Qualifications tab in the header
- The system will render a new page where it will display all of the companies qualifications
- The user will click on the qualification that they would like to see the details of from the list of the companies qualifications which are denoted by the discription
- The system will display a list of worker that have that qualification below the description

##### Description 
-Two column representation 

|Actor Actions| System Reponse|
|-------------|---------------|
| Go to the homepage ||
||Display the company name |
| Navegate to the upper section of the page ||
| Click on the Qualifications tab ||
|| Display Qualifications by the description in a list |
| Navegate to the Qualifciation of choice by their description ||
| Click on the Qualificiation of choice ||
|| A list of workers that have that qualification will be displayed |

##### Post Condition

- The system displays detailed information about the selected qualification, including a list of workers who possess that qualification, if the user clicks on the worker it takes the user to ther workers page.

### Use Case: View Worker Details

##### Summary 
- User uses the system to view worker details through the worker tab
##### Actor 
- End user of the system
##### Precondition 
- The system is displaying the homepage with all the tabs available, as well as displaying the welcome name
##### Description 
- User clicks on the Workers tab in the header
- The system will render a new page where it will display all of the workers
- The user will click on the worker they wish to see the details of from the list of workers which are denoted by description
- The system will display the worker name, their salary, their current workload value, projects they are assigned to, and a list of their qualifications

###### Description
- Two column representation 

|Actor Actions | System Response|
|--------------|----------------|
| Go to the homepage ||
|| Display the company name  |
| Navigate to the upper section of the page ||
| Click on the Workers tab ||
|| Display the employed workers by name in a list |
| Navigate to the worker of choice by name ||
| Click on the worker of choice ||
|| display worker name, salary, current workload value, projects they are assigned to, list of qualifications|

###### Post Condition

- The system displays detailed information about the selected worker, including their name, salary, current workload, projects they are assigned to, and their qualifications , if clicked on the name , projects the user will be taken to the respective tabs.

### Use Case: View Project Details

##### Summary
- User uses the system to view project details through the project tab
##### Actor
- End user of the system
##### Precondition
- The system is displaying the homepage with all the tabs available, as well as displaying the welcome name
##### Description
- User clicks on the Projects tab in the header
- The system will render a new page where it will display all of the projects
- user will click on the project they wish to see the details of from the list of projects which are denoted by the description
- The system will display the project name, size, status, assigned employees, required qualifications and missing qualifications
- The missing qualifications will be colored red, while qualifications met will be green

#### Post Condition 

- The system displays detailed information about the selected project, including project name, size, status, assigned employees, and qualifications needed, with unmet qualifications highlighted in red and met qualifications in green.
  
#### Use Case Name: Create New Qualification 
##### Summary 
- User uses the system to create a new qualificatioon for the company
##### Actor
- End user of the system
##### Precondition 
- The system is displaying the homepage with all the tabs available, as well as displaying the welcome name
##### Description 
- User clicks on the Create tab
- The system will render a new page where it will display the buttons create qualification, create worker, or create project.
- User will then click on the qualifications tab
- The system will display a pop up window with the field to enter the description for the qualification and a save and cancel button.
- User enters the description for the qualification and hits save button
- System renders a message 'Qualification Saved' and returns back to the create tab

##### Description 
-Two column representation 

|Actor Actions| System Reponse|
|-------------|---------------|
| Go to homepage ||
|| Display welcome message |
| Click on the create tab ||
|| Display buttons create qualification, create worker, and create project |
| Click on the create qualification button ||
|| Display a pop up window with the field descritption |
| Enter a description for the qualification ||
| Click save button ||
|| Displays a Qualification Saved after message |

##### Post Condition

- A new qualification is successfully created and saved in the system, and the user is notified of the successful save with a confirmation message.

#### Use Case Name: Create New Worker 
##### Summary 
- User uses the system to create a new worker for the company
##### Actor
- End user of the system
##### Precondition 
- The system is displaying the homepage with all the tabs available, as well as displaying the welcome name
##### Description 
- User clicks on the Create tab
- The system will render a new page where it will display the buttons create qualification, create worker, or create project.
- User will then click on the create worker tab
- The system will display a pop up window with the field to enter the name, qualifications, and salary, of the worker along with a save and cancel button.
- The user will enter the name, select qualifications from a drop down menu, and enter a salary. The user will then click save
- The system will generate a message saying 'Worker Saved' and go back to the create page

##### Description 
-Two column representation 

|Actor Actions| System Reponse|
|-------------|---------------|
| Go to homepage ||
|| Display welcome message |
| Click on the create tab ||
|| Display buttons create qualification, create worker, and create project |
| Click on the create worker button ||
|| Display a pop up window with the fields name, qualifications, and salary|
| Enter the name, select qualifications from a drop down menu, and enter a salary ||
| Click save button ||
|| Displays a Worker Saved after message |

#### Post Condition

- A new worker is successfully created and saved in the system, including their name, qualifications, and salary, and the user is notified of the successful save with a confirmation message.

#### Use Case Name: Create New Project 
##### Summary 
- User uses the system to create a new project for the company
##### Actor
- End user of the system
##### Precondition 
- The system is displaying the homepage with all the tabs available, as well as displaying the welcome name
##### Description 
- User clicks on the Create tab
- The system will render a new page where it will display the buttons create qualification, create worker, or create project.
- User will then click on the create project tab
- The system will display a pop up window with the field to enter the name, qualifications, and size, of the project along with a save and cancel button.
- The user will enter the name, select qualifications from a drop down menu, and select a size from a dropdown menu. The user will then click save.
- The system will generate a message saying 'Project Saved' and go back to the create page

##### Description
-Two column represenation

|Actor Actions | System Response|
|--------------|----------------|
|Go to the homepage ||
|| Display the project name |
| Navigate to the upper section of the page ||
| Click on the Projects tab ||
|| Display the projects by name in a list |
| Navigate to the project of choice by name ||
| click on the project of choice ||
|| display the project name, size, status, assigned employees, required qualifications and missing qualifications |

#### Post Condition

- A new project is successfully created and saved in the system, including its name, size, required qualifications, and the user is notified of the successful save with a confirmation message.

### Use Case: Finish project

##### Summary
- User finalises a project marking it as completed.
##### Actor
- End user of the system
##### Precondition
- The system is displaying the homepage with all the tabs available, as well as displaying the welcome name
##### Description
- The system is displaying the homepage of all tabs available
- The user has appropriate permission to end the project

#### Post Condition
- Project is marked complete
- Relevant stakeholders are notified of project completion.
  
##### Description
-Two column represenation

|Actor Actions | System Response|
|--------------|----------------|
|Go to the homepage |
| Naviagate to the projects tab|
||Display a list of projects ||
| Select the project to finish ||
|| Display the projects info |
| Verify that tasks and requirements are met ||
|| Provide finish / end / suspend button||
|Click on finish / end / suspend button ||
|| Update the status to complete / ended||

### Use Case Name: Start Project

##### Summary 
- User uses this to initiate the execution of project, changing its status from planed paused to active.
##### Actor
- End user of the system
##### Precondition 
- The system is displaying the homepage with all the tabs available
- The user has the necessary permission to start the project 
- The project selected to start is either in 'paused' or 'planned' state.

##### Description 
- The user navigates to the project tab to view available projects and selects a project to start.The system changes the project status to 'active' and updates the related field.
##### Description 

-Two column representation 

|Actor Actions| System Reponse|
|-------------|---------------|
| Go to homepage ||
|| Display welcome message |
| Click on the create tab ||
|| Display buttons create qualification, create worker, and create project |
| Click on the create project button ||
|| Display a pop up window with the fields name, qualifications, and size|
| Enter the name, select qualifications from a drop down menu, and select size from a dropdown menu ||
| Click save button ||
|| Displays a Project Saved after message |
| Go to the homepage ||
||Display the homepage with navigation options |
| Navigate the project tabs ||
||Display a list of all projects, each with current status indicated|
| Select a project marked as 'planned' or 'paused'||
||Display the selected project details|
|Click the start project button||
||Confirm the actions|
|Confirm the action to start ||
||Update the status to active , Display success|

#### Post Condition
- The selected project's status is updated to 'active' in the system, signaling the commencement of work on the project.

### Use Case Name: Assign Worker

##### Summary 
- The project manager assigns a worker to a project. This process involves selecting a worker based on availability and for the project tasks and the qualification requirements.
##### Actor
- End user of the system (with authorisation)
##### Precondition 
- The system is displaying the homepage with all the tabs available
- The user has the necessary permission to assign the worker.

##### Description 
- The project manager navigates to the project details and assigns a worker to ensure that the project's needs are met effectively.
##### Description 
-Two column representation 

|Actor Actions| System Reponse|
|-------------|---------------|
| Navigate to the "Projects" tab from the homepage. ||
||Displays a list of all projects with current status. |
|  Select a project to assign workers to ||
||System displays project details including required qualifications and current team|
| Click on the "Assign Worker" button||
||System presents a searchable list of available workers with their qualifications, availability, and workload|
|Search for and select a worker based on their suitability for the project’s requirements||
||System displays the selected worker's detailed profile for further verification|
|Confirm the worker assignment ||
||System updates the project to include the assigned worker, adjusts the worker's workload, and confirms the assignment|

#### Post Condition

- A worker is successfully assigned to a project in the system, and the system updates to reflect the worker's new workload and project assignment details.

### Use Case Name: UnAssign Worker

##### Summary 
- The project manager unassigns a worker to a project. This process involves selecting a worker to unassign and the system should update the workers workload and availability. 
##### Actor
- End user of the system (with authorisation)
##### Precondition 
- The system is displaying the homepage with all the tabs available
- The user has the necessary permission to unassign the worker.

##### Description 
- The project manager navigates to the project details and unassigns a worker.
##### Description 
-Two column representation 

|Actor Actions| System Reponse|
|-------------|---------------|
| Navigate to the "Projects" tab from the homepage. ||
||Displays a list of all projects with current status. |
|  Select a project to unassign workers from ||
||System displays project details including required qualifications and current team|
| Click on the "Unassign Worker" button||
||System presents a searchable list of assigned workers with their qualifications, availability, and workload|
|Search for and select a worker that you want to unassign||
||System displays the selected worker's detailed profile for further verification|
|Confirm the worker's unassignment ||
||System updates the project to not include the unassigned worker, adjusts the worker's workload, and confirms the unassignment|

##### Post Condition

- A worker is successfully unassigned from a project in the system, and the system updates to reflect the worker's adjusted workload and availability.

