package ApiTest;

import java.time.LocalDate;

public abstract class BaseSetUp {
  protected static final String APOD_URL = "https://api.nasa.gov/planetary/apod";
  protected static final String API_KEY = "08Q06wHyDvJ12JrQxpeg49mNv4LaIWebew5yHNvK";
  protected static final String API_KEY_MISSING_ERROR = "API_KEY_MISSING";
  protected static final String API_KEY_INVALID_ERROR = "API_KEY_INVALID";
  protected static final String TODAYS_DATE_OF_THE_APOD_IMAGE = LocalDate.now().toString();
  protected static final String DATE_OF_THE_APOD_IMAGE = "2021-10-06";
  protected static final String INVALID_DATEFORMAT_OF_THE_APOD_IMAGE = "2021-13-01";
  protected static final String FUTURE_DATE_OF_THE_APOD_IMAGE = "2090-10-12";
  protected static final String COUNT_IN_RANGE_ERROR = "Count must be positive and cannot exceed 100";
  protected static final String INVALID_COMBINATION_PARAM_ERROR = "Bad Request: invalid field combination passed. Allowed request fields for apod method are 'concept_tags', 'date', 'hd', 'count', 'start_date', 'end_date', 'thumbs'";
  protected static final String THUMBS_DATE_APOD_VIDEO = "2021-09-28";
  protected static final String INVALID_DATEFORMAT_ERROR = "does not match format '%Y-%m-%d'";
  protected static final String DATERANGE_ERROR = "Date must be between Jun 16, 1995 and ";
}
