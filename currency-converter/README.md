Currency Converter
=================================

# Introduction

This artifact includes domain and business logic layers. 

Dependency is used as underlying artifact for other part of the projects.

## Running persistence layer integration tests

	mvn test -P domainIntegrationTest
	
## Running business layer unit tests

	mvn test -P unitTests

## Running business layer integration tests

	mvn test -P serviceIntegrationTest

## Running api layer unit and integration tests

	mvn test -P apiTest -pl currency-converter
