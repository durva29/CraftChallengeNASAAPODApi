## CraftChallengeNASAAPODApi
This project tests NASA APOD(Astronomy Picture of the Day) API related test cases.

## Table of contents
* [API Information](#api-info)
* [APOD - api_key](#apd-apikey)
* [APOD - date](#apd-date)
* [APOD - start_date And end_date](#apd-startenddate)
* [APOD - count](#apd-count)
* [APOD - thumbs](#apd-thumbs)
* [Setup](#setup)

## API Information
API URL: GET https://api.nasa.gov/planetary/apod
### Available Query Parameters
* api_key - which user can generate from https://api.nasa.gov/
* date (YYYY-MM-DD)
* start_date (YYYY-MM-DD)
* end_date (YYYY-MM-DD)
* count (Positive number between 1 t0o 100)
* thumbs (True or False)

## APOD - api_key
Retrive the APOD metadata of current date (default).
GET https://api.nasa.gov/planetary/apod?api_key=API_KEY
#### Below Test cases for the api_key are covered in ApiTest.ApiKeyTest class
* Valid api_key
* Invalid api_key
* Missing api_key

## APOD - date
Retrive the APOD metadata based on date provided in YYYY-MM-DD format or default to current date.
GET https://api.nasa.gov/planetary/apod?api_key=API_KEY&date=DATE
#### Below Test cases for the date are covered in ApiTest.DateTest class
* Valid date with api_key
* Valid date without api_key
* Invalid date format with api_key
* date as future date with api_key

## APOD - start_date and end_date
Retrive the APOD metadata based on start_date and end_date provided in YYYY-MM-DD format.
GET https://api.nasa.gov/planetary/apod?api_key=API_KEY&start_date=START_DATE&end_date=END_DATE
#### Below Test cases for the start_date and end_date are covered in ApiTest.StartAndEndDateTest class
* Valid start_date with api_key
* Valid start_date and end_date with api_key
* end_date greater than today's date with api_key
* Invalid combination of parameters - date & start_date with api_key
* Invalid combination of parameters - only end_date with the APOD Image with API KEY
* Invalid end_date format with valid start_date with api_key

## APOD - count
If this is specified then randomly chosen images will be returned.
GET https://api.nasa.gov/planetary/apod?api_key=API_KEY&count=COUNT
#### Below Test cases for the count are covered in ApiTest.CountTest class
* Valid count with api_key
* Count value as zero with api_key
* Count value as negative with api_key
* Count value greater than 100 with API Key
* Invalid combination of parameters - date & start_date with count and API KEY

## APOD - thumbs
Return the URL of video thumbnail. If an APOD is not a video, this parameter is ignored.
GET https://api.nasa.gov/planetary/apod?api_key=api_key&date=2021-10-06&thumbs=true
#### Below Test cases for the thumbs are covered in ApiTest.ThumbsTest class
* thumbs value as true with date and api_key
* thumbs value as false with date and API KEY

## Technologies
Project is created with:
* Java
* TestNG
* Maven
* Rest Assured

## Setup
* Checkout this project
* Import existing maven project in Java IDE
* Project has testng.xml file which has all the test cases files. 
* To run project locally: Right click on testng.xml file->Run As->TestNG Suite
* After running the test cases, test-output folder will be created which has index.html file. Open index.html file with the browser to see the test results in visual format. 
