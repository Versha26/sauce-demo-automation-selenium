# Selenium Automation Framework – SauceDemo
A hybrid Selenium test automation framework built with Java, Selenium WebDriver, TestNG, and Maven, targeting [saucedemo.com](https://www.saucedemo.com/). The framework follows the Page Object Model (POM) design pattern combined with data-driver testing and automated Extent Reports with screenshot-on-failure.
## Teck Stack
| Category |	Tool/ Library |
| --- | --- |
| Langauge |	Java 17 |
| Test Framework  |	TestNG |
|Automation Tool |	Selenium WebDriver |
| Build Tool |	Maven |
| Driver Management	| WebDriverManager (Bonigarcia) |
| Reporting |	ExtentReports 5 |
| Test Data	| JSON + Java Constants |
| Logging	| Log4j2 |
| IDE |	Eclipse |

## Framework Design
This is a hybrid framework, combining:
*	**Page Object Model (POM)** – UI locators and page interactions are isolated from test logic, so a locator change only needs to be fixed in one place.
*	**Data-Driven Testing** – login scenarios are driven from an external JSON file via a TestNG DataProvider, so new test cases can be added without writing new code.
*	**TestNG** – provides parallel execution, grouping, assertions and listener hooks.
*	**ThreadLocal WebDriver** – each test thread gets its own isolated driver instance, enabling safe parallel execution (parallel=”methods” in testing.xml).
*	**Observer Pattern (ITestListener)** – reporting and screenshot capture are fully decoupled from test logic via a custom TestNG listener.
---

## Project Structure

```
Selenium-Automation
├── src
│   └── test
│       ├── java
│       │   ├── base/            → BaseTest (setup/teardown, driver lifecycle)
│       │   ├── pages/           → Page Object classes (one per screen)
│       │   ├── utils/           → DriverFactory, ConfigReader, WaitUtils,
│       │   │                       ExtentManager, ExtentTestManager,
│       │   │                       ScreenshotUtils, JsonDataReader, TestData
│       │   ├── listeners/       → TestListener (ITestListener implementation)
│       │   └── tests/           → TestNG test classes
│       └── resources
│           ├── config.properties
│           └── testdata/        → JSON test data files
├── test-output/                 → Generated Extent Reports + screenshots (git-ignored)
├── pom.xml
└── testng.xml
```

---
## Test Coverage
The framework automates the full purchase funnel on saucedemo.com:
| Page | Coverage |
| ---| --- |
| **Login** | Valid login, locked-out user, invalid password, empty fields (JSON data-driven) |
| **Inventory** | Page load verification, add/remove items to cart, product sorting (price & name) |
| **Cart** | Single/multiple item verification, item removal, continue shopping navigation |
| **Checkout** | Full end-to-end purchase flow, required-field validations, price/tax total calculation, order confirmation |

---

## Design Decisions

- **Constructor-injected WebDriver in Page Objects** — a single driver session flows through the entire test via dependency injection, rather than each page creating its own driver instance.
- **Fluent page navigation** — methods that cause navigation (e.g. `clickCheckout()`, `clickContinue()`) return the next page's Page Object, so end-to-end flows read like a user journey.
- **Constants vs. JSON test data** — fixed, small datasets (product names, sort labels) live in a `TestData` constants class for compile-time safety; genuinely variable data (login combinations) is externalized to JSON via Gson deserialization into a POJO.
- **Explicit waits only** — centralized in `WaitUtils`, avoiding `Thread.sleep()` and mixed wait strategies that cause flaky tests.
- **Screenshot-on-failure only** — captured via a TestNG listener (`ITestListener`), keeping reports focused on actual failures rather than bloating them with passing-test screenshots.

---
## How to Run

**Prerequisites:** Java 21, Maven, Eclipse (or any IDE with Maven support)

1. Clone the repository
   ```bash
   git clone <repo-url>
   cd Selenium-Automation
   ```

2. Run the full suite via Maven
   ```bash
   mvn test
   ```

3. Or run via `testng.xml` directly in Eclipse:
   Right-click `testng.xml` → **Run As → TestNG Suite**

4. View the report:
   Open the generated file in `test-output/ExtentReport_<timestamp>.html`

---
## Future Enhancements

- [ ] Jenkins CI/CD pipeline integration
- [ ] Cross-browser & headless execution support
- [ ] API testing layer (Rest Assured)
- [ ] Dockerized execution via Selenium Grid
- [ ] BDD layer with Cucumber
