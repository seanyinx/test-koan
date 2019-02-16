# Test Koan
A project for Java developers to practice writing effective tests.

It includes the following modules:
* base 
* spring-boot
* integration

## base
test cases for pure java code with:
* Primitive types
* String
* Collection
* Mock
* Remote communication

## spring boot
common test scenarios for spring boot applications like:
* Controller
* Database
* Rest API with consumer driven contract

## integration
integration tests with external dependencies using Docker

**Note**: if you are using Docker Toolbox on Windows, you have to downgrade testcontainers to version `1.5.1`,
and add `checks.disable=true` to `$HOME/.testcontainers.properties`.

Not doing so, you may have a docker container startup error as follows:
`{"invalid volume specification: 'C:\\Users\\Administrator\\AppData\\Local\\Temp\\.testcontainers-tmp-8195821502169083677:/dummy:ro'"}`
