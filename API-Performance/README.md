# APIPerformance
  APIPerformance is a DropWizard application which provides RESTful API. It sends 100 request for getting uuid and then sends 100 post request to the service url "http://surya-interview.appspot.com/message". After getting the successful response, it finds the following statistics:
  1) 10th Percentile
  2) 50th Percentile
  3) 90th Percentile
  4) 95th Percentile
  5) 99th Percentile
  6) Mean
  7) Standard Deviation

 Prerequisites:
---
  Java and Maven.


How to start the APIPerformance application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/API-Performance-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Available API
-------------
1. Use to send the get and post request
    `http://localhost:8080/performance-test/`

    This api lets you create new message in the Queue.

    Sample JSON response:

    {
    	"getPerformanceReport": {
    		"Percentile10": 259.1,
    		"Percentile50": 274,
    		"Percentile90": 884.2000000000002,
    		"Percentile95": 896.95,
    		"Percentile99": 1435.1399999999976,
    		"Mean": 362.62000000000006,
    		"StandardDeviation": 226.02099571732367
    	},
    	"postPerformanceReport": {
    		"Percentile10": 259,
    		"Percentile50": 272.5,
    		"Percentile90": 313.5,
    		"Percentile95": 445.55000000000007,
    		"Percentile99": 1257.5199999999977,
    		"Mean": 301.6,
    		"StandardDeviation": 127.63798237771759
    	}
    }

    Here getPerformanceReport key has statistic for "Get response time". And Here postPerformanceReport key has statistic for "Post response time".