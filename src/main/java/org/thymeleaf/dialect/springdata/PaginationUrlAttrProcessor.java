package org.thymeleaf.dialect.springdata;

import java.util.Collections;
import java.util.Map;

import org.thymeleaf.Arguments;
import org.thymeleaf.Configuration;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.attr.AbstractAttrProcessor;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;

final class PaginationUrlAttrProcessor extends AbstractAttrProcessor {
	private static final String ATTR_NAME = "pagination-url";

	protected PaginationUrlAttrProcessor() {
		super(ATTR_NAME);
	}

	@Override
	protected ProcessorResult processAttribute(Arguments arguments, Element element, String attrName) {
		final Configuration configuration = arguments.getConfiguration();
	    final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
	    final String attributeValue = element.getAttributeValue(attrName);
	    final IStandardExpression expression = parser.parseExpression(configuration, arguments, attributeValue);
	    
	    ProcessorUtils.removeAttribute(element, ATTR_NAME);
	    
	    String url = (String) expression.execute(configuration, arguments);
		Map<String, Object> vars = Collections.<String, Object>singletonMap(Keys.PAGINATION_URL_KEY, url);
		
		return ProcessorResult.setLocalVariables(vars);
	}
	
	@Override
	public int getPrecedence() {
		return 999;
	}

}
