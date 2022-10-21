# Sample Distributed Scheduling PoC

This is a proof of concept project which a very basic level distributed job scheduling scenario
is applied to a very basic microservice architecture.

In microservice architectures, naturally, every microservice may exist as many clones of itself, in other saying
many instances of itself.
This is because of the vertical scalability feature of the microservice architecture.
Microservices are scaled in the vertical axis according to the system load, so the system continues
to serve its functionalities without downtime and with using hardware resources efficiently.
However, in a scenario which a set of jobs needs to be executed periodically, a scheduler must be used and let's say
this scheduler microservice also needs to be scaled vertically.
At this point, every instance of the scheduler executes the jobs(especially the ones
that take a big amount of time to complete its execution, like database related jobs) by itself,
which can create data inconsistencies, unwanted complexity or at least unnecessary load on the system.

In this scenario, our job is basically printing the current calendar time to the standard output. The job is executed by
many instances of scheduler service concurrently. Also, even though the job is basically printing the calendar time to
standard output,
it will take 10 seconds to mimic the typical behaviour of database related processes. On the other hand, the period of
the job is 1 second, so the condition that the instances are going to wait for each other until the one executes the job
finishes it is met.

A global lock in the database is used to meet with this scenario.

## Dependencies

* Oracle JDK 17
* Apache Maven
* Docker
* Docker Compose

## Installation

````text
git clone git@github.com:mryakar/sample-distributed-scheduling.git
````

## Build, Run, Stop

In the command prompt, run the "run.bat"
script that is in the root directory of the project and follow the steps to build,
prepare docker images and run the project.
To stop it, use simply the Ctrl+C key combination.

## Result

After running the project, you are going to see that the calendar time is printed out on the standard output
each time with a different instance of scheduler.