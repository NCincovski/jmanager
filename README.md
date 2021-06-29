## **Job Management**

### Details
The goal of this system is to handle the execution of multiple types of Jobs. The actions performed by these Jobs are not important; possible examples of these Jobs could be performing a data-load into a DWH, performing indexing of some file-based content or sending emails.
### Features of this system are:
- Flexibility<br/>
The types of possible actions performed by the Jobs are not known to the Job Management System. In the future, new Jobs should be supported without re-developing the Job Management System (optional).
- Reliability<br/>
Each Job should either complete successfully or perform no action at all. (i.e., there should be no side effects created by a Job that fails.)
- Internal Consistency<br/>
At any one time a Job has one of four states: QUEUED, RUNNING, SUCCESS, FAILED. Following the execution of a Job, it should be left in an appropriate state.
- Priority (Optional)<br/>
Each Job can be executed based on its priority relative to other Jobs
- Scheduling<br/>
A Job can be executed immediately or according to a schedule.