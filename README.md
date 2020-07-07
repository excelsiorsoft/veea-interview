Hello.  Thank you for visiting. 

This is the resulting Code Challenge Application as per supplied [requirements](https://github.com/excelsiorsoft/veea-interview/blob/master/Veea%20Coding%20Challange.pdf).

The code challenge application is deployed on free version of Heroku at:

[https://veea-interview.herokuapp.com/](https://veea-interview.herokuapp.com/)

Heroku free tier shuts down inactive applications for cost savings.  In order to restart the app in such a case of shutdown, one can utilize the specially crafter 'restart' endpoint:

[https://veea-interview.herokuapp.com/restart](https://veea-interview.herokuapp.com)

It exposes its functionality via REST and all endpoints are described here:

[https://veea-interview.herokuapp.com/swagger-ui.html](https://veea-interview.herokuapp.com/swagger-ui.html)

The application implemented on top of Spring Boot utilizing several extra auxiliary frameworks as specified in its `pom.xml` file. 

Unit tests could be found in a regular Maven test location folder.  Specifically, `SuperCsvExperiments.java`  is dedicated to testing different variants of csv formats (one can find the file in the resources folder) as described in the requirements doc while `VeeaInterviewApplicationTests.java` is for testing some internal utilities.

End-to-end testing was done with Postman whose exported test collection attached as `Veea Interview.postman_collection.json`  for convenience.

Please refrain from reusing my personal FourSquare credentials.

If you have any question, please don't hesitate to email or call me.
