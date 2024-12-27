# Running Selenium Java TestNG u'sing Page Object Model / Data Driven - Extent Report Manager and Retry Method

## The project uses the following:

* Java 17 as the programming language.
* Selenium WebDriver as the web browser automation framework using Java binding.
* TestNG as the testing framework.
* Page Object Model as the design pattern
* POM with Page Factory
* Assert as the assertion library.
* Extent Reports 5 as the test reporting strategy.
* Selenium Shutterbug for capturing screenshots.
* Maven as the Java build tool.
* Eclipse as the IDE.

### Configuration
  The project uses a [*config.properties*](./src/test/resources/config.Properties) file to manage global configurations such as browser type and base url.
  
## Page Objects: 
Leverage the Page Object Model (POM) pattern to encapsulate web page elements and actions, promoting reusability and reducing code duplication.

## Test Data Management: 
Manage test data efficiently, ensuring that tests are repeatable and maintainable.

## Custom Utilities: 
A collection of utility functions and libraries are included to simplify common automation tasks and enhance the framework's capabilities.

## Getting Started
Clone this repository, and follow the detailed documentation in the README to set up your environment and run the tests using Java, Selenium, TestNG, ExtentReport, 
Eclipse IDE, and the JXL (JExcel) Library for Data-Driven.

```bash
git clone https://github.com/Ranees33/Live-GPS-Tracking-App-TestNG-Maven-ExtentReportManager.git
```

## Tests
The project uses TestNG as the test runner.

### Install dependencies
Before running the tests, we need to install all 
necessary dependencies. Maven, a software 
project management tool, can do this for us. 
Run the following command:

```bash
mvn test-compile
```

### Run all tests
To verify if everything is installed correctly and 
functioning properly, we should run all 
available tests. This can be done with the following command:

```bash
mvn test
```

## Contributions
We welcome contributions from the open-source community! Feel free to submit pull requests, report issues, or share your ideas to help improve and expand this java automation framework.

Start automating your tests efficiently and enjoy the benefits of TestNG, Data-Driven Development with this Selenium Java Automation Framework. Happy testing! 🚀
