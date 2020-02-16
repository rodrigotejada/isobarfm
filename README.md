## Bands application

It is an application that will fetch info from external api 
Format this data and retrieve to user
It was builded with springboot.
It loads all info into memory than uses Redis to manage cache.

## How to run
 docker-compose up
 ./gradlew build
 java -jar build/libs/bands-0.0.1-SNAPSHOT.jar

## Future Improvements
- return response objects
- get all data to redis in the init instead of to the application memory
- mock spring context in unit test
- integration tests
- exception handle
