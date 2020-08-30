# speedtest
Internet connection speed test demo

### Components
#### test-starter
This service handles client requests for initiating a speed test by performing the following steps:
* generates a unique "testkey"
* determines a "test end" timestamp (test duration is 15 min by default)
* forwards the client IP address to a "Fleet" service which returns a list of "traffic" hosts with capacity to handle speed test requests
* generates a signature token for each (endpoint, timestamp, testkey)
* returns a the testkey, the test end timestamp, and the endpoints with their respective tokens
* the client can start issuing request to the traffic endpoints which are accessibe for the duration of the tests (validation based on token)

Aspects to flesh out / consider
* p3 client credentials authN to traffic endpoint selector service
* p2 distributed tracing
* p2 http client connection pooling / resilience
* p1 apidocs endpoint
* p1 emitting application-specific metrics - client and server
* p1 standard metrics - micrometer/datadog
* p1 application monitoring endpoint - actuator
* p1 logging
* p1 javadocs
* p1 unit testing
* p1 integration testing
* p1 docker-compose with mock endpoint selector service for demo
* p1 csrf, cors
* P1 producer contract verification

- screenshots: datadog, jacoco-lombok
