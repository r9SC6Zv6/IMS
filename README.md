Coverage: 86.8%
# Inventory Management System: IMS Project

A command line inventory management system written in Java as part of the Fundamentals Project at QA Academy.  
A very simple system designed for an imaginary warship model making shop with basic functions for record keeping and cost calculations. The user is a very old fashioned gent who prefers not having any fancy GUI and likes working in a command line tool instead even though he's typing with two fingers and takes a few moment to find each key (quite the opposite of Sophisticated Sara if you watched The Apprentice).

The system connects with a MySQL database and implements CRUD functionality on 3 domains. That is it can CREATE, READ, UPDATE and DELETE entries from either CUSTOMER, ITEM or ORDER tables of a database. 

## Further development and customisation

These instructions will get you a copy of the project up and running on your local machine for further development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

The system is written in Java and therefore needs the Java SE JDK installed which you can get instructions for from [here](https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html).  
Verify the installation and version using the following command:
```
java -version
```

Maven was used to build the project which you can get from [here](https://maven.apache.org).  
Verify the installation and version using the following command:
```
mvn -version
```

Any text editor can be used to write and modify code but an IDE is very usefull for Java.  

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

Explain what these tests test, why and how to run them

```
Give an example
```

### Integration Tests 
Explain what these tests test, why and how to run them

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Google Cloud service account credential file needs to be added to Environment variables for authentication otherwise connection to GCP database is not possible.

Make sure Java is instaled but running:
```
java -version
```

To run the application, navigate to the root directory of the project and use the following command:
```
java -jar ims.jar
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Ervin Musincki** - *Final impletementation* [r9SC6Zv6](https://github.com/r9SC6Zv6)
* **J Harry** - *Distributing a starter version* [JHarry444](https://github.com/JHarry444)
* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

## Acknowledgments

* I thank [christophperrins](https://github.com/christophperrins) and [JHarry444](https://github.com/JHarry444) for the initial work.
* I also thank my trainers at QA for making this possible.
