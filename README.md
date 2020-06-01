# Test Koan
A project for Java developers to practice writing effective tests.

It includes the following modules:
* base 
* spring-boot
* integration

## Restrictions: 
* you are not allowed to change the test cases or production code.
* you shall only add assertions / mocking / configuration / annotations / dependencies to tests

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

## integration
integration tests with external dependencies using Docker

**Note**: if you are using Docker Toolbox on Windows, you have to downgrade testcontainers to version `1.5.1`,
and add `checks.disable=true` to `$HOME/.testcontainers.properties`.

Not doing so, you may have a docker container startup error as follows:
`{"invalid volume specification: 'C:\\Users\\Administrator\\AppData\\Local\\Temp\\.testcontainers-tmp-8195821502169083677:/dummy:ro'"}`
