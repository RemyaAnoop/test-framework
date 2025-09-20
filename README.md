# Test Automation Framework

A simple test automation framework using **Serenity BDD**, **JUnit 5**, and **Rest Assured** for API and UI testing.
Serenity BDD is a open-source library which is built on top of Selenium WebDriver and JUnit, and it provides a rich set of features for writing maintainable and readable automated acceptance tests.
Same repo can be converted to a pure Selenium based UI automation framework or API automation framework by using RestAssured.


## Project Structure

Kept the core framework and test implementation separate for better maintainability.
If there is another test repository, the core framework can be reused. by creating a jar and add as a dependency in the build.gradle file.
Keeping the test implementation and core framework separate also helps in better collaboration between testers and applications.

```
src/main/java/com/project/automation/          - Core Framework Code . 
├── api/
│   ├── client/         - API client implementations
│   ├── endpoints/      - API endpoint definitions
│   └── models/         - API data models
├── baseclass/          - Base classes for tests
│   ├── BaseAPI.java
│   ├── BaseApiSteps.java
│   ├── BaseTest.java
│   ├── BaseUIPage.java
│   └── BaseUISteps.java
├── data/               - Test data management
├── exceptions/         - Custom exceptions
├── listeners/          - Test listeners
└── utils/              - Utility classes
    ├── ApiUtils.java
    ├── ConfigHelper.java
    └── PropertyHelper.java

src/test/java/com/project/testautomation/      - Test Implementation
├── pages/              - Page objects for UI testing
├── steps/              - Test steps (api/ and ui/)
├── testcases/          - Test classes (api/ and ui/)
└── testutils/          - Test helper utilities
```

##  What is Serenity BDD?

Serenity BDD is a testing framework that:
- Generates detailed HTML reports automatically. Reports include test steps, screenshots, and more.
- Also get emailable format reports and in JSON format to integrate with other tools.
- Takes screenshots when tests fail
- Creates living documentation
- Works with JUnit 5 and Rest Assured

## Reports

After running tests, find reports at:
- `target/site/serenity/index.html` - Main HTML report
- `build/reports/tests/test/index.html` - Gradle test report

##  How to Run Tests

```bash
# Run all tests
./gradlew clean test

# Run only API tests
./gradlew test --tests "*.api.*"

# Run only UI tests
./gradlew test --tests "*.ui.*"

# Generate reports
./gradlew test aggregate

# From Run Config in IDE (e.g. IntelliJ)
 -Denvironment= "envt name " "tag name" if any tag to run 
```

##  Logging

Logs are configured in `src/test/resources/logback.xml`:
- Console output with colors
- File logs in `logs/` directory
- Separate error logs

## Environment Setup 
Multiple ways to configure environments:Kept it sinple here.

Configure environments in `src/test/resources/serenity.conf`:
- dev, qa, staging, prod environments
- Set base URLs for different environments

##  Key Files

- `build.gradle` - Dependencies and build configuration
- `serenity.properties` - Serenity BDD settings
- `serenity.conf` - Environment configurations
- `logback.xml` - Logging configuration

##  Dependencies

- **Serenity BDD 4.1.14** - Testing framework and reports
- **JUnit 5** - Test runner
- **Rest Assured** - API testing
- **Jackson** - JSON handling

## Getting Started

1. Clone the project
2. Run `./gradlew clean test` - might see errors since wire mock is not set up
3. Open _**target/site/serenity/index.html**_ to view results

