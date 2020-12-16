## Retail Discount Service
Discount service calculates the net price for a given bill.  

Implemented with Java 8 + Spring Boot Starter

## Requirements
On a retail website, the following discounts apply:
1. If the user is an employee of the store, he gets a 30% discount
2. If the user is an affiliate of the store, he gets a 10% discount
3. If the user has been a customer for over 2 years, he gets a 5% discount.
4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
5. The percentage based discounts do not apply on groceries.
6. A user can get only one of the percentage based discounts on a bill. 

Given a bill, it finds the net payable amount.

### Assumptions
The discount in Requirement 4 is applied to the total amount of the bill with discount prices applied in Requirement 1,2,3.


### Run tests with code coverage
```
mvn test
```
Code coverage report can be found here [/target/site/jacoco/index.html](/target/site/jacoco/index.html)

### SonarQube Report
SonarQube report can be found here [/sonarqube/sonarqube-report.png](/sonarqube/sonarqube-report.png)

### Run project
```
mvn spring-boot:run
```

It runs the service with an example bill as below:

    User user = new User("user1", UserType.CUSTOMER, new SimpleDateFormat("yyyy-MM-dd").parse("2010-01-01"));
    Item item1 = new Item("item1", ItemType.OTHER, new BigDecimal(100));
    Item item2 = new Item("item2", ItemType.OTHER, new BigDecimal(200));
    Item item3 = new Item("item3", ItemType.GROCERY, new BigDecimal(200));
    
    Bill bill = new Bill(user, Arrays.asList(item1, item2, item3));

### UML Class Diagram
UML Class Diagram is in uml-diagram.png file.