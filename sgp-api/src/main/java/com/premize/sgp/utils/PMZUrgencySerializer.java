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
 * @class PMZUrgencySerializer
 * @since 27/03/2014
 *
 */
public class PMZUrgencySerializer extends JsonSerializer<Integer> {

	private static final String BAJO= "Low";
	private static final String MEDIO= "Medium";
	private static final String ALTO= "High";
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
	 */
	@Override
	public void serialize(Integer value, JsonGenerator gen,
			SerializerProvider arg2) throws IOException,
			JsonProcessingException {

		if (value == 1)
			gen.writeString(BAJO);
		else if (value == 2)
			gen.writeString(MEDIO);
		else if (value == 3)
			gen.writeString(ALTO);

	}


}
