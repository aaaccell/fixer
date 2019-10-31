# Fixer.io API Client

This project provides a Java client for the [Fixer.io](https://fixer.io/) API.

## Contribution

The extent to which this implementation covers the Fixer.io API is driven by our immediate needs:

- [x] Supported Symbols Endpoint
- [ ] Latest Rates Endpoint
- [ ] Historical Rates Endpoint
- [ ] Specify Symbols
- [ ] Changing Base Currency
- [x] Convert Endpoint
- [x] Time-Series Endpoint
- [ ] Fluctuation Endpoint

As can be seen from the above, this project is not completed in terms of the API coverage.
In addition, error handling can be improved and tests extended.
Hence, outside contributors are most welcome and we are happy to review your suggestions in the form of a pull request. 

## Setup

**Gradle dependency:**

```
implementation 'com.aaaccell:fixer:VERSION'
```

or **Maven dependency:**

```
<dependency>
  <groupId>com.aaaccell</groupId>
  <artifactId>fixer</artifactId>
  <version>VERSION</version>
</dependency>
```

## Usage 

It is recommended to use the **FixerRequestBuilder** to form requests:
```java
FixerRequestBuilder builder = builder("API_KEY");
```

**Supported Symbols Endpoint**

```java
SymbolsResponse response = builder
    .symbols()
    .call();
```

**Convert Endpoint**

```java
ConvertResponse response = builder
    .convert()
    .withDate(LocalDate.parse("2019-01-01"))
    .withAmount(BigDecimal.valueOf(1))
    .fromCurrency("CHF")
    .toCurrency("EUR")
    .call();
```

**Time-Series Endpoint**

```java
TimeSeriesResponse response = builder
    .timeSeries()
    .withBase("CHF")
    .forSymbols("EUR", "USD")
    .withStartDate("2012-05-01")
    .withEndDate("2012-05-05")
    .call();
```

**Time-Series Endpoint (segmented)**

Given the fact that the fixer.io API only allows to retrieve a time-series for a maximum period of 1 year,
we provide a request segmentation such that multiple 1-year periods are requested concurrently and their responses combined into a single response.

In this example a total of 3 requests are submitted.

```java
TimeSeriesResponse r = builder
    .timeSeries()
    .withBase("CHF")
    .forSymbols("EUR", "USD")
    .withStartDate("2012-05-01")
    .withEndDate("2014-05-05")
    .call();
```

**Additional examples**

Can be found under [tests](https://github.com/aaaccell/fixer/tree/master/src/test/java/com/aaaccell/fixer).

## Tests

Note that an API key is to be provided either by setting the environment variable `FIXER_API_KEY` or the JVM parameter `fixerApiKey`.

```
./gradlew check
```
