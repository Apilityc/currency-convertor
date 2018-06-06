currency-converter
===================

[![Build Status](https://travis-ci.org/Apilytic/currency-converter.svg?branch=master)](https://travis-ci.org/Apilytic/currency-converter)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7633098308134afeb2a0a7c15050528f?branch=master)](https://www.codacy.com/app/gogoluxecs/currency-converter?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=apilytic/currency-converter&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/Apilytic/currency-converter/branch/master/graph/badge.svg)](https://codecov.io/gh/Apilytic/currency-converter)
[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)
# Introduction

Backend that fetches country ISO codes and currency exchange rates and stores data to Redis.
There are options to query the backend via cli for following [operations](#Operations).

Exchange rates are fetched from Yahoo Finance and duckduckgo.com that uses xe.com API.

Obsolute wiki page: [Apilytic Wiki][].

# Operations

* Download currency iso codes to Redis
* Ingestion rate exchanges
* Query for exchange rate

# Version history

## 0.2.0.M1

* Complete rewriting
* Integrate new rate exchang services
  * yahoo finance
  * duckduckgo xe.com API
* RxJava with Java8 streams
* Spring 5 and Junit 5

# TODO

Organize micro services for different layers that communicates with message bus.
Code coverarage tool integration

[Apilytic Wiki]: https://github.com/Apilytic/currency-converter/wiki
