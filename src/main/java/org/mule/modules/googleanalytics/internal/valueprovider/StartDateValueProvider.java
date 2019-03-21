package org.mule.modules.googleanalytics.internal.valueprovider;

import java.util.Set;

import org.mule.runtime.api.value.Value;
import org.mule.runtime.extension.api.values.ValueBuilder;
import org.mule.runtime.extension.api.values.ValueProvider;
import org.mule.runtime.extension.api.values.ValueResolvingException;

public class StartDateValueProvider implements ValueProvider {

	@Override
	public Set<Value> resolve() throws ValueResolvingException {
		// TODO Auto-generated method stub
		return ValueBuilder.getValuesFor("today","yesterday","1daysAgo","2daysAgo","3daysAgo","4daysAgo","5daysAgo","6daysAgo","7daysAgo","8daysAgo","9daysAgo");
	}

	
	
}
