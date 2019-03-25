package org.mule.modules.googleanalytics.internal.util;

import java.io.IOException;
import java.util.List;

import javax.swing.SortOrder;

import org.mule.modules.googleanalytics.api.domain.FilterKey;
import org.mule.modules.googleanalytics.api.domain.FilterOperation;
import org.mule.modules.googleanalytics.api.domain.FilterOperator;
import org.mule.modules.googleanalytics.api.domain.Output;
import org.mule.modules.googleanalytics.api.domain.SamplingLevel;
import org.mule.modules.googleanalytics.api.domain.SegmentKey;
import org.mule.modules.googleanalytics.api.domain.SegmentOperator;
import org.mule.modules.googleanalytics.api.domain.SegmentType;
import org.mule.modules.googleanalytics.api.params.DimensionParameter;
import org.mule.modules.googleanalytics.api.params.FilterParameter;
import org.mule.modules.googleanalytics.api.params.FilterParameterType;
import org.mule.modules.googleanalytics.api.params.MetricsParameter;
import org.mule.modules.googleanalytics.api.params.SegmentParameter;
import org.mule.modules.googleanalytics.api.params.SegmentParameterType;
import org.mule.modules.googleanalytics.api.params.SortParameter;
import org.mule.modules.googleanalytics.api.params.SortParameterType;
import org.mule.modules.googleanalytics.internal.exception.GoogleAnalyticsError;
import org.mule.modules.googleanalytics.internal.exception.GoogleAnalyticsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.Analytics.Data.Ga.Get;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.Profiles;
import com.google.api.services.analytics.model.Webproperties;

public class GoogleAnalyticsUtility {

	private static final Logger log = LoggerFactory.getLogger(GoogleAnalyticsUtility.class);

	public String generateReport(Analytics analytics, String profileId, String startDate, String endDate,
			SamplingLevel samplingLevel, int startIndex, int maxResults, Output output, List<MetricsParameter> metrix,
			List<DimensionParameter> dimensions, SortParameter sort, FilterParameter filters,
			SegmentParameter segment) throws GoogleAnalyticsException{

		String result = null;
		try {
			String profile = getFirstProfileId(analytics, profileId);
			result = getResults(analytics, profile, startDate, endDate, samplingLevel, metrix, startIndex, maxResults,
					output, dimensions, sort, filters, segment).toPrettyString();
		} catch (GoogleAnalyticsException e) {
			log.error("Error occured in GoogleAnalyticsUtility::generateReport()", e);
			throw e;
		} catch (IOException e) {
			log.error("Error occured in GoogleAnalyticsUtility::generateReport()", e);
			throw new GoogleAnalyticsException("Error while parsing google analytics result into json", GoogleAnalyticsError.JSON_PARSER_EXCEPTION);
		}
		return result;
	}

	// Get the first view (profile) ID for the authorized user.
	private String getFirstProfileId(Analytics analytics, String profileId) throws GoogleAnalyticsException{

		// Query for the list of all accounts associated with the service
		// account.
		Accounts accounts = null;
		try {
			accounts = analytics.management().accounts().list().execute();
		} catch (IOException e) {
			log.error("Error occured in GoogleAnalyticsUtility::getFirstProfileId()", e);
			throw new GoogleAnalyticsException("Account ID not found", GoogleAnalyticsError.ACCOUNT_NOT_FOUND);
		}

		if (accounts == null || accounts.getItems().isEmpty()) {
			log.error("Error occured in GoogleAnalyticsUtility::getFirstProfileId() No accounts found");
		} else {
			String firstAccountId = accounts.getItems().get(0).getId();

			// Query for the list of properties associated with the first
			// account.
			Webproperties properties = null;
			try {
				properties = analytics.management().webproperties().list(firstAccountId).execute();
			} catch (IOException e) {
				log.error("Error occured in GoogleAnalyticsUtility::getFirstProfileId()", e);
				throw new GoogleAnalyticsException("Account ID not found", GoogleAnalyticsError.ACCOUNT_NOT_FOUND);
			}

			if (properties == null || properties.getItems().isEmpty()) {
				log.error("Error occured in GoogleAnalyticsUtility::getFirstProfileId() No Webproperties found");
			} else {
				String firstWebpropertyId = properties.getItems().get(0).getId();

				// Query for the list views (profiles) associated with the
				// property.
				Profiles profiles = null;
				try {
					profiles = analytics.management().profiles().list(firstAccountId, firstWebpropertyId).execute();
				} catch (IOException e) {
					log.error("Error occured in GoogleAnalyticsUtility::getFirstProfileId()", e);
					throw new GoogleAnalyticsException("No Profiles found", GoogleAnalyticsError.NO_PROFILES_FOUND);
				}

				if (profiles == null || profiles.getItems().isEmpty()) {
					log.error("Error occured in GoogleAnalyticsUtility::getFirstProfileId() No views (profiles) found");
				} else {
					// Return the first (view) profile associated with the
					// property.
					profileId = profiles.getItems().get(0).getId();
				}
			}
		}
		return profileId;
	}

	private GaData getResults(Analytics analytics, String profileId, String startDate, String endDate,
			SamplingLevel samplingLevel, List<MetricsParameter> metrix, int startIndex, int maxResults, Output output,
			List<DimensionParameter> dimensions, SortParameter sort, FilterParameter filters, SegmentParameter segment)
			throws GoogleAnalyticsException {
		// Query the Core Reporting API for given query parameters.

		String metricValue = "";
		String dimentionValue = "";
		String sortValue = "";
		String filterValue = "";
		String samplingLevelValue = "";
		String segmentValue = "";

		metricValue = createMetrixQuery(metrix, metricValue);
		dimentionValue = createDimentionQuery(dimensions, dimentionValue);
		sortValue = createSortQuery(sort, sortValue);
		filterValue = createFilterQuery(filters, filterValue);
		segmentValue = createSegment(segment, segmentValue);

		Get getQuery = null;

		try {
			getQuery = analytics.data().ga().get(String.format("ga:%s", profileId), startDate, endDate, metricValue);
		} catch (IOException e) {
			log.error("Error occured in GoogleAnalyticsUtility::getResults()", e);
			throw new GoogleAnalyticsException("Internal error occurred please try again or contact help desk",
					GoogleAnalyticsError.SERVERE_RROR);
		}

		if (!metricValue.isEmpty()) {
			getQuery = getQuery.setMetrics(metricValue);
		}

		if (!dimentionValue.isEmpty()) {
			getQuery = getQuery.setDimensions(dimentionValue);
		}

		if (!samplingLevelValue.isEmpty()) {
			getQuery = getQuery.setSamplingLevel(samplingLevel.getSamplingLevel().toString());

		}

		if (!sortValue.isEmpty()) {
			getQuery = getQuery.setSort(sortValue);
		}

		if (!filterValue.isEmpty()) {
			getQuery = getQuery.setFilters(filterValue);
		}

		if (!segmentValue.isEmpty()) {
			getQuery = getQuery.setSegment(segmentValue);
		}

		if (output != null && !output.getOutput().isEmpty()) {
			getQuery = getQuery.setOutput(output.getOutput());
		}

		if (startIndex != 0) {
			getQuery = getQuery.setStartIndex(startIndex);
		}

		if (maxResults != 0) {
			getQuery = getQuery.setMaxResults(maxResults);
		}

		GaData gaData = null;
		try {
			gaData = getQuery.execute();
		} catch (IOException e) {
			log.error("Error occured in GoogleAnalyticsUtility::getResults()", e);
			throw new GoogleAnalyticsException("Error in executing Google Analytics Query",
					GoogleAnalyticsError.QUERY_ERROR);

		}

		return gaData;
	}

	// Query builder for Segment Parameter
	private String createSegment(SegmentParameter segment, String segmentValue) {

		if (segment != null) {
			List<SegmentParameterType> segmentParameterTypes = segment.getSegmentparams();
			if (segmentParameterTypes != null && segmentParameterTypes.size() > 0) {
				StringBuilder builder = new StringBuilder();

				SegmentKey lastKey = null;

				for (SegmentParameterType parameterType : segmentParameterTypes) {
					SegmentKey key = parameterType.getSegmentKey();
					SegmentType type = parameterType.getSegmentType();
					FilterKey filter = parameterType.getSegmentFilter();
					SegmentOperator operator = parameterType.getSegmentOpertor();
					String value = parameterType.getSegmentValue();

					if (lastKey == null) {
						builder.append(key.getSegmentKey()).append(type.getSegmentType()).append(filter.getFilterKey())
								.append(operator.getSegmentOperator()).append(value).append(";");
					} else if (key == SegmentKey.Users && lastKey == SegmentKey.Users) {
						builder.append(filter.getFilterKey()).append(operator.getSegmentOperator()).append(value)
								.append(";");
					} else if (key == SegmentKey.Sessions && lastKey == SegmentKey.Sessions) {
						builder.append(filter.getFilterKey()).append(operator.getSegmentOperator()).append(value)
								.append(";");
					} else {
						builder.append(key.getSegmentKey()).append(type.getSegmentType()).append(filter.getFilterKey())
								.append(operator.getSegmentOperator()).append(value).append(";");
					}

					lastKey = key;

					segmentValue = builder.toString();

					if (segmentValue.endsWith(";") || segmentValue.endsWith(",")) {
						segmentValue = segmentValue.substring(0, segmentValue.length() - 1);
					}
				}
			}
		}
		return segmentValue;
	}

	// Query builder for Filter Parameter
	private String createFilterQuery(FilterParameter filters, String filterValue) {
		if (filters != null) {
			List<FilterParameterType> filterParameterTypes = filters.getFilterParams();

			if (filterParameterTypes != null && filterParameterTypes.size() > 0) {
				StringBuilder builder = new StringBuilder();

				for (FilterParameterType parameterType : filterParameterTypes) {
					FilterKey key = parameterType.getKey();
					FilterOperator operator = parameterType.getOperator();
					String value = parameterType.getValue();
					FilterOperation operation = parameterType.getOperation();

					builder.append(key.getFilterKey()).append(operator.getFilterOperator()).append(value);

					if (operation != null) {
						builder.append(operation.getOperation());
					}
				}

				filterValue = builder.toString();

				if (filterValue.endsWith(";") || filterValue.endsWith(",")) {
					filterValue = filterValue.substring(0, filterValue.length() - 1);
				}
			}
		}

		return filterValue;
	}

	// Query Builder for Sort Parameter
	private String createSortQuery(SortParameter sort, String sortValue) {

		if (sort != null) {
			List<SortParameterType> sortParameterTypes = sort.getSortparms();

			if (sortParameterTypes != null && sortParameterTypes.size() > 0) {
				StringBuilder builder = new StringBuilder();
				for (SortParameterType sortParameter : sortParameterTypes) {
					SortOrder order = sortParameter.getSortOrder();
					if (order == SortOrder.ASCENDING) {
						builder.append(sortParameter.getSortparamValue().getFilterKey()).append(",");
					} else if (order == SortOrder.DESCENDING) {
						builder.append("-").append(sortParameter.getSortparamValue().getFilterKey()).append(",");

					}
				}
				sortValue = builder.toString();
				if (sortValue.endsWith(",")) {
					sortValue = sortValue.substring(0, sortValue.length() - 1);
				}
			}
		}

		return sortValue;
	}

	// Query builder for Dimension Parameter
	private String createDimentionQuery(List<DimensionParameter> dimensions, String dimentionValue) {
		if (dimensions != null && dimensions.size() > 0) {
			StringBuilder builder = new StringBuilder();
			for (DimensionParameter dimensionParameter : dimensions) {
				builder.append(dimensionParameter.getValue().getDimension()).append(",");
			}
			dimentionValue = builder.substring(0, builder.length() - 1);

		}
		return dimentionValue;
	}

	// Query builder for metrics parameters
	private String createMetrixQuery(List<MetricsParameter> metrix, String metricValue) {
		if (metrix != null && metrix.size() > 0) {
			StringBuilder builder = new StringBuilder();
			for (MetricsParameter metrixParameter : metrix) {
				builder.append(metrixParameter.getValue().getMetrics()).append(",");
			}
			metricValue = builder.substring(0, builder.length() - 1);

		}
		return metricValue;
	}
}
