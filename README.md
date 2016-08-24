# banking
Banking system written in Scana

## Main class 

### Compile
scalac bank.scala

#### Run each task
scala Balance
scala TimeInterval
scala RemoveTransaction

## Test

### Compile
scalac -cp /usr/share/java/hamcrest-core-1.3.jar:/usr/share/java/junit-4.12.jar test.scala -deprecation

### Run
scala -cp /usr/share/java/hamcrest-core-1.3.jar:/usr/share/java/junit-4.12.jar org.junit.runner.JUnitCore BankingTest
