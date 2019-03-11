package org.mule.modules.googleanalytics.internal.utility;

import java.io.IOException;
import java.util.LinkedList;
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
import org.mule.modules.googleanalytics.internal.exception.GoogleAnalyticsException;

import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.Analytics.Data.Ga.Get;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.Profiles;
import com.google.api.services.analytics.model.Webproperties;

public class GoogleAnalyticsUtility {

	@SuppressWarnings("finally")
	public String generateReport(Analytics analytics, String profileId, String startDate, String endDate,
			SamplingLevel samplingLevel, int startIndex, int maxResults, Output output, List<MetricsParameter> metrix,
			List<DimensionParameter> dimensions, SortParameter sort, FilterParameter filters,
			SegmentParameter segment) {
		String result = null;
		try {
			String profile = getFirstProfileId(analytics, profileId);
			result = getResults(analytics, profile, startDate, endDate, samplingLevel, metrix, startIndex, maxResults,
					output, dimensions, sort, filters, segment).toPrettyString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	private String getFirstProfileId(Analytics analytics, String profileId) throws IOException {

		// Query for the list of all accounts associated with the service
		// account.
		Accounts accounts = analytics.management().accounts().list().execute();

		if (accounts.getItems().isEmpty()) {
			System.err.println("No accounts found");
		} else {
			String firstAccountId = accounts.getItems().get(0).getId();

			// Query for the list of properties associated with the first
			// account.
			Webproperties properties = analytics.management().webproperties().list(firstAccountId).execute();

			if (properties.getItems().isEmpty()) {
				System.err.println("No Webproperties found");
			} else {
				String firstWebpropertyId = properties.getItems().get(0).getId();

				// Query for the list views (profiles) associated with the
				// property.
				Profiles profiles = analytics.management().profiles().list(firstAccountId, firstWebpropertyId)
						.execute();

				if (profiles.getItems().isEmpty()) {
					System.err.println("No views (profiles) found");
				} else {
					// Return the first (view) profile associated with the
					// property.
					profileId = profiles.getItems().get(0).getId();
				}
			}
		}
		return profileId;
	}

	@SuppressWarnings("unused")
	private GaData getResults(Analytics analytics, String profileId, String startDate, String endDate,
			SamplingLevel samplingLevel, List<MetricsParameter> metrix, int startIndex, int maxResults, Output output,
			List<DimensionParameter> dimensions, SortParameter sort, FilterParameter filters, SegmentParameter segment)
			throws  GoogleAnalyticsException {

		String metricValue = "";
		String dimentionValue = "";
		String sortValue = "";
		String filterValue = "";
		String samplingLevelValue = "";
		String OutputValue = "";
		Integer startIndexValue = null;
		Integer maxResultsValue = null;
		String segmentValue = "";

		metricValue = createMetrixQuery(metrix, metricValue);
		dimentionValue = createDimentionQuery(dimensions, dimentionValue);
		sortValue = createSortQuery(sort, sortValue);
		filterValue = createFilterQuery(filters, filterValue);
		segmentValue = createSegment(segment, segmentValue);

		Get getQuery= null;
		
		try {
			getQuery = analytics.data().ga().get(String.format("ga:%s", profileId), startDate,
					endDate, metricValue);
		} catch (IOException e) {
			throw new GoogleAnalyticsException("Internal error occurred please try again or contatct help desk");
		}

		if (metricValue != "") {
			getQuery = getQuery.setMetrics(metricValue);
		}

		if (dimentionValue != "") {
			getQuery = getQuery.setDimensions(dimentionValue);
		}

		if (samplingLevelValue != "") {
			getQuery = getQuery.setSamplingLevel(samplingLevel.getSamplingLevel().toString());

		}

		if (sortValue != "") {
			getQuery = getQuery.setSort(sortValue);
		}

		if (filterValue != "") {
			getQuery = getQuery.setFilters(filterValue);
		}

		if (OutputValue != "") {
			getQuery = getQuery.setOutput(output.getOutput().toString());

		}

		if (startIndexValue != null) {
			getQuery = getQuery.setStartIndex(startIndex);

		}

		if (maxResultsValue != null) {
			getQuery = getQuery.setMaxResults(maxResults);

		}

		if (segmentValue != "") {
			getQuery = getQuery.setSegment(segmentValue);

		}

		GaData gaData = null;
		try {
			gaData = getQuery.execute();
		} catch (IOException e) {
			
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
					FilterKey value = sortParameter.getSortparamValue();
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
