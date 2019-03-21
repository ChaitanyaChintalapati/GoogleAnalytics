# Google Analytics Connector

Google Analytics Connector 1.0 from Ksquare gives out-of box solution to Integrate your Google Analytics data with other business applications.


Easily design and build and integrate with mule flows to control and access google analytics data using secured pathway(Google Oauth 2.0).

Google Analytics Connector Implements the following features for the MuleSoft-based enterprise solutions:
1) To request report data for a user, your application must identify the user and specify a view (profile) for which to retrieve the data.
2) To query the API for Google Analytics report data, which consists of dimensions and metrics.
3) Oauth 2.0 Protocol for authentication and authorization.


Application/Service  |	Version
-------------------  | ----------
Mule Runtime	     |  4.1.X
Google Analytics Core|  Reporting API	V3
Java	             |  1.8 and later


## Operations:

### Generate report:
Generate report is core operation of the connector which is responsible for getting google analytics data .

Input Parameters for generate report:
Id: String(required)
The unique table ID of the form ga:XXXX, where XXXX is the Analytics view (profile) ID for which the query will retrieve the data.

start-date: String(required)
Start date for fetching Analytics data. Requests can specify a start date formatted as YYYY-MM-DD, or as a relative date (e.g., today, yesterday, or NdaysAgo where N is a positive integer).

End-date: String(required)

End date for fetching Analytics data. Request can specify an end date formatted as YYYY-MM-DD, or as a relative date (e.g., today, yesterday, or NdaysAgo where N is a positive integer).

Metrics: String(required)

A list of comma-separated metrics, such as ga:sessions,ga:bounces( maximum of 10 metrics for any query) 

Dimensions: String(optional)

A list of comma-separated dimensions for your Analytics data, such as ga:browser,ga:city( maximum of 7 dimensions in any query).

Sort: String(optional)

A list of comma-separated dimensions and metrics indicating the sorting order and sorting direction for the returned data.

Filters: String(optional)

Dimension or metric filters that restrict the data returned for your request.

SamplingLevel: String(optional)

The desired sampling levels. Allowed Values:
  
   	DEFAULT   : Returns response with a sample size that balances speed and accuracy.
	FASTER    : Returns a fast response with a smaller sample size.
    HIGHER_PRECISION : Returns a more accurate response using a large sample size, but this may result in the response being slower.
   

Include-empty-rows: Boolean(optional)

Defaults to true; if set to false, rows where all metric values are zero will be omitted from the response.

Start-index: Integer(optional)

The first row of data to retrieve, starting at 1. Use this parameter as a pagination mechanism along with the max-results parameter.

Start-index: Integer(optional)

The first row of data to retrieve, starting at 1. Use this parameter as a pagination mechanism along with the max-results parameter.


Max-results: Integer(optional)

The maximum number of rows to include in the response.


Output: String(optional)

The desired output type for the Analytics data returned in the response. Acceptable values are json and dataTable(Default: json).

## Highlights of Google Analytics Connector:

1.	It allows users to automatically build quires to determine which metrics and dimensions are brought back. We can select the metrics parameters from the dropdown and from these selected parameters connectors build a query.
2.	Providing security using Oauth 2.0 which enables user to pass client id and client secrets and application name Host and server configuration.


Add this dependency to your application pom.xml
```

            <groupId>com.mulesoft.connectors</groupId>
            <artifactId>mule-googleAnalytics-connector</artifactId>
            <version>2.0.6-SNAPSHOT</version>
            <classifier>mule-plugin</classifier>

```
