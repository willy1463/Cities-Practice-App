# Cities-Practice-App
Practice applicaiton using Spring Boot and Angular to display a list of cities.

To startup in localhost, start up the backend application on port 8080 (tomcat default) and then the frontend using ng serve (on port 4200).

You can access the cities list directly via call to the api, such as 'http://localhost:8080/api/cities/queryByPage?page={pageNum}&size={N}' and view the results JSON in the browser

To view the output of the biggest sequence method, you can call the method from your browser at 'http://localhost:8080/api/cities/sequence?sample={N}&start={startLetter} and view the JSON output in the browser or the list in the tomcat logs
