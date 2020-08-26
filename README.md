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
