package org.mule.modules.googleanalytics.internal.operation;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;
import static org.mule.runtime.extension.api.annotation.param.display.Placement.ADVANCED_TAB;
import static org.mule.runtime.api.meta.ExpressionSupport.SUPPORTED;
import java.util.List;

import javax.inject.Inject;

import org.mule.modules.googleanalytics.api.domain.Output;
import org.mule.modules.googleanalytics.api.domain.SamplingLevel;
import org.mule.modules.googleanalytics.api.params.DimensionParameter;
import org.mule.modules.googleanalytics.api.params.FilterParameter;
import org.mule.modules.googleanalytics.api.params.MetricsParameter;
import org.mule.modules.googleanalytics.api.params.SegmentParameter;
import org.mule.modules.googleanalytics.api.params.SortParameter;
import org.mule.modules.googleanalytics.internal.connection.GoogleAnalyticsConnection;
import org.mule.modules.googleanalytics.internal.exception.GoogleAnalyticsException;
import org.mule.modules.googleanalytics.internal.util.GoogleAnalyticsUtility;
import org.mule.modules.googleanalytics.internal.valueprovider.StartDateValueProvider;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.core.api.el.ExpressionManager;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.Ignore;
import org.mule.runtime.extension.api.annotation.dsl.xml.ParameterDsl;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.annotation.values.OfValues;
//import org.mule.connectors.atlantic.commons.builder.config.exception.DefinedExceptionHandler;

import com.google.api.services.analytics.Analytics;

/**
 * 
 * @author chaitanyachintalapati(https://github.com/ChaitanyaChintalapati)
 * @version 1.0
 * generate report operation responsible for getting google analytics data.
 *
 */

public class GoogleAnalyticsOperations {

	@Inject
    ExpressionManager expressionManager;

    @Ignore
    public void setExpressionManager(ExpressionManager expressionManager) {
        this.expressionManager = expressionManager;
    }
	
	@MediaType(value = ANY, strict = false)
	public String generateReport(@Connection GoogleAnalyticsConnection analyticsConnection,
			@Expression(SUPPORTED) @Summary("An Unique Google Analytics Profile ID to get Analytics Data ") String profileId,
			@Expression(SUPPORTED) @Summary("Start date for fetching Analytics data") @OfValues(StartDateValueProvider.class) String startDate,
			@Expression(SUPPORTED) @Summary("End date for fetching Analytics data") @OfValues(StartDateValueProvider.class) String endDate,
			@Summary("Desired sampling levels to get the response") @Optional @Placement(tab = ADVANCED_TAB) @Expression(ExpressionSupport.NOT_SUPPORTED) @ParameterDsl(allowReferences = false) SamplingLevel samplingLevel,
			@Summary("The first row of data to retrieve") @Optional @Placement(tab = ADVANCED_TAB) @Expression(ExpressionSupport.NOT_SUPPORTED) @ParameterDsl(allowReferences = false) int startIndex,
			@Summary("The maximum number of rows to include in the response") @Optional @Placement(tab = ADVANCED_TAB) @Expression(ExpressionSupport.NOT_SUPPORTED) @ParameterDsl(allowReferences = false) int maxResults,
			@Summary("The desired output type for the Analytics data returned in the response") @Optional @Placement(tab = ADVANCED_TAB) @Expression(ExpressionSupport.NOT_SUPPORTED) @ParameterDsl(allowReferences = false) Output output,
			@Summary("The aggregated statistics for user activity to your site, such as clicks or pageviews") @Expression(ExpressionSupport.NOT_SUPPORTED) @ParameterDsl(allowReferences = false) List<MetricsParameter> metrixParameters,
			@Summary("The dimensions parameter breaks down metrics by common criteria; for example, by ga:browser or ga:city") @Optional @NullSafe @Placement(tab = ADVANCED_TAB) @Expression(ExpressionSupport.NOT_SUPPORTED) @ParameterDsl(allowReferences = false) List<DimensionParameter> dimensionParameters,
			@Summary("Sorting the results of a query enables you to ask different questions about your data. For example, to address the question What are my top countries, and which browsers do they use most?") @Optional @NullSafe @Placement(tab = ADVANCED_TAB) @Expression(ExpressionSupport.NOT_SUPPORTED) @ParameterDsl(allowReferences = false) SortParameter sortParameter,
			@Summary("The filters query string parameter restricts the data returned from your request.For example, the following query requests ga:pageviews and ga:browser for view (profile) 12134, where the ga:browser dimension starts with the string Firefox:") @Optional @NullSafe @Placement(tab = ADVANCED_TAB) @Expression(ExpressionSupport.NOT_SUPPORTED) @ParameterDsl(allowReferences = false) FilterParameter filterParameter,
			@Summary("Isolate and analyze subsets of your data, For example Select users who used Chrome browser in at least one of their sessions") @Optional @NullSafe @Placement(tab = ADVANCED_TAB) @Expression(ExpressionSupport.NOT_SUPPORTED) @ParameterDsl(allowReferences = false) SegmentParameter segmentparameter) throws GoogleAnalyticsException{

		GoogleAnalyticsUtility analyticsUtility = new GoogleAnalyticsUtility();
		return analyticsUtility.generateReport(analyticsConnection.getAnalytics(), profileId, startDate, endDate, samplingLevel,
				startIndex, maxResults, output, metrixParameters, dimensionParameters, sortParameter, filterParameter,
				segmentparameter);
	}

}
