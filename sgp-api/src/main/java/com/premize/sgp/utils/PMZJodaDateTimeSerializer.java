package com.premize.sgp.utils;

import java.io.IOException;
import java.util.Locale;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class PMZJodaDateTimeSerializer
 * @since 27/03/2014
 *
 */
public class PMZJodaDateTimeSerializer extends JsonSerializer<DateTime> {
	
	private static DateTimeFormatter formatter = DateTimeFormat.forStyle("MS").withLocale(Locale.GERMAN);
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
	 */
	@Override
	public void serialize(DateTime value, JsonGenerator gen, SerializerProvider arg2) throws IOException,
			JsonProcessingException {
		
		gen.writeString(formatter.print(value));
		
	}
	
}
