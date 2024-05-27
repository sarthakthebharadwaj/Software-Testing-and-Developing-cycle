## SpotBugs
### Dead Stores
#### Qualficiation Q

<img src="SpotBugsImages/DeadStoreQ.png" width="400" height="312">
<img src="SpotBugsImages/DeadStoreQCode.png" width="800" height="300">
<img src="SpotBugsImages/DeadStoreQCodeRefactored.png" width="800" height="300">
<img src="SpotBugsImages//DeadStoreQAfter.png" width="500" height="300">

  In this bug there was storage of a dead variable. This means that variable is not used or read in any subsequent code. This bug is realatively easy to fix as all one has to do is delete the code. The first two images show the SpotBugs report before deletion and the code before refactoring. The second two images show the refactored code and the change in the SpotBugs report. It can be seen that the Qualification Q that is not used is deleted and the Spotbugs report for Dead Stores goes from 32 to 31.

#### Qualification Q1

<img src="SpotBugsImages/DeadStoreQ1.png" width="600" height="312">
<img src="SpotBugsImages/DeadStoreQ1Code.png" width="600" height="312">
<img src="SpotBugsImages/DeadStoreQ1CodeRefactored.png" width="600" height="312">
<img src="SpotBugsImages/DeadStoreQ1After.png" width="800" height="312">

  In this bug there was storage of a dead variable. This means that variable is not used or read in any subsequent code. This bug is very similar to the one above in that all you have to do is delete the dead store. The first two images show the SpotBugs report before deletion and the code before refactoring. The second two images show the refactored code and the change in the SpotBugs report. It can be seen that the Qualification q1 that is not used is deleted and the Spotbugs report for Dead Stores goes from 31 to 30.


#### Project proj5

<img src="SpotBugsImages/DeadStoreProj5.png" width="650" height="312">
<img src="SpotBugsImages/DeadStoreProj5Code.png" width="600" height="312">
<img src="SpotBugsImages/DeadStoreProj5CodeRefactored.png" width="600" height="312">
<img src="SpotBugsImages/DeadStoreProj5After.png" width="650" height="312">

This bug is similar to the two above in the way that it is the storage of a variable that is not used in subsequent code. In this section of the code there were 3 tests that all stored the object Project proj5 when it is not used. I chose to delete all of these instances so in the first two images the items in the Dead Store was 30 and proj5 is shown to be declared. In the last two images show Dead store as 27 and show the code refactored without Project proj5.


### Useless Object Created

#### Set<Worker> workers

<img src="SpotBugsImages/UselessObjectWorkers.png" width="800" height="312">
<img src="SpotBugsImages/UselessObjectWorkersCode.png" width="600" height="312">
<img src="SpotBugsImages/UselessObjectWorkersCodeRefactored.png" width="600" height="312">
<img src="SpotBugsImages/UselessObjectWorkersAfter.png" width="800" height="312">

This bug means an object is created and stored, but never utilized or referenced to making it ultimately useless. For this bug in particular the useless object that was created was the workers hashset. It is never utlized in this particular test because it was testing a function that had to do with worker in relation to a project not workers in relation to a project. Therefore the hashset was created but never used and was taking up space uselessly. The first two images show the hashset workers and also show the Useless Objects Created at 10 items. The second two images show the useless object deleted as well as the Useless Objects Created at 9 items

## PMD

### Local Variable nameing Conventions 

This mistakes was a common one in our code and was a coding style problem. If a variable was capitalized or named amgibuously it was flagged for this mistake. To fix this mistake one simply has to change the name of the variable. All of these mistakes were addressed and they are displayed through the screenshot below. Since all of these mistakes were very similar to fix they will not be individually explained.

#### Company Equals

<img src="PMDScreenshots/CompanyEquals.png" width="800" height="312">
<img src="PMDScreenshots/CompanyEqualsCode.png" width="800" height="312">
<img src="PMDScreenshots/CompanyEqualsCodeRefactored.png" width="800" height="312">
<img src="PMDScreenshots/CompanyEqualsAfter.png" width="700" height="312">

#### Test For Equals 1 and 2 

<img src="PMDScreenshots/TestForEquals1Code.png" width="600" height="312">
<img src="PMDScreenshots/TestForEquals1CodeRefactored.png" width="600" height="312">

#### Equals 

<img src="PMDScreenshots/EqualsCode.png" width="800" height="312">
<img src="PMDScreenshots/EqualsCodeRefactored.png" width="800" height="312">

#### Test For Create Project 

<img src="PMDScreenshots/TestForCreateProjectCode.png" width="800" height="312">
<img src="PMDScreenshots/TestForCreateProjectCodeRefactored.png" width="800" height="312">

#### Test For Create Project 2

<img src="PMDScreenshots/TestForCreateProject2Code.png" width="600" height="312">
<img src="PMDScreenshots/TestForCreateProject2CodeRefactored.png" width="600" height="312">

#### Test For Create Project 3

<img src="PMDScreenshots/TestForCreateProject3Code.png" width="800" height="312">
<img src="PMDScreenshots/TestForCreateProject3CodeRefactored.png" width="800" height="312">

#### Test For Create Project 4

<img src="PMDScreenshots/TestCreateProject4Code.png" width="800" height="312">
<img src="PMDScreenshots/TestCreateProject4CodeRefactored.png" width="800" height="312">

#### Test No Qualifications

<img src="PMDScreenshots/NoQualificationsCode.png" width="900" height="312">
<img src="PMDScreenshots/NoQualificationsCodeRefactored.png" width="900" height="312">

#### PMD After

<img src="PMDScreenshots/LocalVariableAfter.png" width="700" height="312">

### Equals Null

This mistake is if there is an equal to null in the code.

#### PMD Before

<img src="PMDScreenshots/TestForEqualsTwo.png" width="1000" height="312">

#### Test For Equals Two

<img src="PMDScreenshots/TestForEqualsTwoCode.png" width="900" height="312">
<img src="PMDScreenshots/TestForEqualsTwoCodeRefactored.png" width="900" height="312">

For this error in the assertFalse there was the statement company.equals(null) and this was fixed by replacing the statement with assertNotNull(company)

#### Test Equals Input Null

<img src="PMDScreenshots/TestEqualsInputNullCode.png" width="900" height="312">
<img src="PMDScreenshots/TestEqualsInputNullCodeRefactored.png" width="900" height="312">

#### PMD After

<img src="PMDScreenshots/PMDAfter.png" width="600" height="312">
