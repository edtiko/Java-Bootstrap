package com.premize.sgp.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class PMZStatusSerializer
 * @since 27/03/2014
 *
 */
public class PMZStatusSerializer extends JsonSerializer<Boolean> {

	private static final String OPEN = "Open";
	private static final String CLOSED = "Closed";
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
	 */
	@Override
	public void serialize(Boolean value, JsonGenerator gen,
			SerializerProvider arg2) throws IOException,
			JsonProcessingException {
		
		if (value)
			gen.writeString(OPEN);
		else
			gen.writeString(CLOSED);

	}

}
