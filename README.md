currency-converter
===================

[![Build Status](https://travis-ci.org/Apilytic/currency-converter.svg?branch=master)](https://travis-ci.org/Apilytic/currency-converter)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7633098308134afeb2a0a7c15050528f)](https://www.codacy.com/app/gogoluxecs/currency-converter?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=apilytic/currency-converter&amp;utm_campaign=Badge_Grade)

# Introduction

Spring based application that uses Yahoo Finance and Google finance calculator as backup. Currency ISO codes, cross-reference reates and additionl statics data are stored in Redis. Calculation of rates goes in threads in chunks by 200 rates to Yahoo Finance.

You can find brainstorming, stories, ideas and specifications in project's [Apilytic Wiki][].

# Version history

## 0.1.0.M2

* Native java API for currency conversion
* Saving lists in Redis

## 0.1.0.M1

* Ingestion of rates
* Ingestion of cross-referencies
* Wrap spring-integration with simple web interface
* Currency cross-references are imported async in threadpool with size 5
* Support Yahoo Fianance provider

[Apilytic Wiki]: https://github.com/Apilytic/currency-converter/wiki

