# speedtest
Internet connection speed test demo

### Original Design
Source: [Building fast.com](https://netflixtechblog.com/building-fast-com-4857fe0f8adb)
![](https://miro.medium.com/max/1000/1*zm_48AMVsyrLl64TVDbSmA.png)

### Components
#### test-starter
Corresponds to module api.fast.com in the original design. This service handles client requests for initiating a speed test by performing the following steps:
* generates a unique "testkey"
* determines a "test end" timestamp (test duration is 15 min by default)
* forwards the client IP address to a "Fleet" service which returns a list of "traffic" hosts with capacity to handle speed test requests
* generates a signature token for each (endpoint, timestamp, testkey)
* returns the testkey, the test end timestamp, and the endpoints with their respective tokens
* the client can start issuing request to the traffic endpoints which are accessibe for the duration of the tests (validation based on token)
#### endpoints-selector
This is a Spring Boot "contract stub" used for verification on the test-starter side. Also handy to spin up a stubbed server when running test-starter locally. The purpose of an actual endpoints-selector service is to encapsulate the knowledge about the "fleet" of traffic nodes that handle the speed test requests by the clients - and match those nodes based on live activity monitoring (i.e. available capacity) and geo proximity to the client
#### (Note: no other components are implemented at this stage)

### Running locally
Run **endpoints-selector stub**
```
cd endpoint-selector
mvn clean install -DskipTests
wget -O stub-runner.jar 'https://search.maven.org/remote_content?g=org.springframework.cloud&a=spring-cloud-contract-stub-runner-boot&v=2.2.4.RELEASE'
java -jar stub-runner.jar --stubrunner.ids="com.speedtest.svc:endpoint-selector:0.0.1-SNAPSHOT:stubs:8081" --stubrunner.workOffline=true --stubrunner.stubs-mode=local --server.port=8083
```
In a new console window, run **test-starter**
```
# first, test out the stub
curl -H "Authorization: Basic ZmxlZXRrZXk6ZmxlZXRzZWNyZXQ=" localhost:8081
cd ../test-starter
mvn clean test spring-boot:run
# finally, test out the service
curl -X POST localhost:8080/api/v1/test
```

### Metrics / operational details
* Actuator endpoint: `curl localhost:8080/actuator`
* Generic Java and application specific metrics (e.g. speedtests.initialization, speedtest.endpoint.selection) are emitted via Micrometer to Datadog
* Test coverage: 82%
![](./docs/screenshot.jpg)

### Aspects to further build out / consider
* Client Credentials authNZ flow to traffic endpoint selector service
* Distributed tracing
* HTTP client connection pooling
* Resilience techniques like circuit breaker (resilience4j)
* API docs endpoint
* SSL
* Secrets Management


