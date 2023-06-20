To run the code - execute in root directory:
````
1. ./gradlew clean
2. ./gradlew build
3. ./gradlew run
````

To test - execute in browser
```
http://localhost:8080/convert/number?inputNumber=10111&inputFormat=BINARY&outputFormat=ROMAN
```

To implement support of new conversion type you need to extend from 
`com.cflox.challenge.numberconverter.services.NumberConverter` class, and if new data type is provided - add
it to `com.cflox.challenge.numberconverter.common.enums.DataFormat`, no other changes are required. 