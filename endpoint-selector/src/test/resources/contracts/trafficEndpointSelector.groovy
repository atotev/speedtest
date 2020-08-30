package contracts

org.springframework.cloud.contract.spec.Contract.make {
	request {
		method 'GET'
		url '/'
		headers {
			header(authorization(), 'Basic ZmxlZXRrZXk6ZmxlZXRzZWNyZXQ=')
		}
	}
	response {
		status OK()
		body([
			urls: ['https://a']
		])
		headers {
			contentType('application/json')
		}
	}
}