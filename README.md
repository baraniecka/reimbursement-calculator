### Business Trip Reimbursement Calculator

Copy the project to your computer:
```
git clone https://github.com/baraniecka/reimbursement-calculator
```
Check if you have installed Maven:
```
maven --version
```
and Java:
```
java --version
```

If you don't have, please download and install (Java 11, Maven 3.9.1.)

Then type 
```
mvn compile
```
to compile source code, then

```
mvn test
```

to run tests, and finally

```
mvn install
```
to install packages.

To run the application type:
```
mvn exec:java -Dexec.mainClass="org.baranieckaMain"
```

Next find two `.html` files  under path: 
```/reimbursement-calculation-app/src/main/resources/```

named `admin.html` and user`.html`. Double-click on them to open in your web browser.

To terminate the application, type

`ctrl + c`
in the terminal and confirm with `Y`.