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

Click on the green `Code` button at the top of the page and follow the instructions to clone the repository onto your local machine or use the following command to use git from a command line (must have git installed):
```
git clone https://github.com/r9SC6Zv6/IMS
```

Create a local or remote MySQL server and enter the url and credentials into the db.properties file at `src/main/resources/db.properties` following the template in the db-deffault.properties file.

The database can be setup according to the scema found at `src/main/resources/sql-schema.sql`.

Then open your pregered IDE and import the project by selecting the root folder.

Now, the project can be run by opening the `Runner.java` class, right clicking and selecting Run (In Eclipse anyway).

## Limitations

The current ims.jar file only functions correclty if the Google Cloud service account credentials file is present on the local computer with the `GOOGLE_APPLICATION_CREDENTIALS=path_to_file/credentials.json` added to Environment Varibales. To use with different database please enter credentials into a db.properties file at `src/main/resources/`  and re-package.
## Running the tests

The project has been Unit Tested using JUnit and Coding Style/ Quality Tested using SonarQube.  

### Unit Tests 

Unit Tests have been used to test the indepenedent funcion and behaviour of isolated 'units' of code.  
The tests can be run in Eclipse by right clicking on the `src/test/java` directory and selecting `Run as > JUnit Test`.

### Coding style tests

SonarQube has been used for coding style tests to check for volnurabilitis, maintinablility and to see how well best practices have been followed.  
To run the tests install SonarQube by following the instructions [here](https://www.sonarqube.org/downloads/). Then run the given command in your favourite terminal emulator from the root folder of the project.  
The results will be available from the SonarQube web interface at `http://localhost:9000`

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
