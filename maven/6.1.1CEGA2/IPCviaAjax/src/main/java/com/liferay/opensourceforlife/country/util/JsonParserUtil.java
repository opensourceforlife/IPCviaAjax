package com.liferay.opensourceforlife.country.util;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

/**
 * 
 * 
 */
public final class JsonParserUtil
{

	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * Converts the Java Object to Json String
	 * 
	 * @param object
	 * @return
	 * @throws IOException
	 */

	/* Throws specific exception instead of throwing main Exception to resolve sonar issue */
	public static String toJson(final Object object) throws IOException
	{
		return mapper.writeValueAsString(object);
	}

	/**
	 * Get the Java Object from Json String
	 * 
	 * @param jsonString
	 * @param clazz
	 * @return Java Object
	 * @throws IOException
	 */

	/* Throws specific exception instead of throwing main Exception to resolve sonar issue */
	public static Object toPojo(final String jsonString, final Class<?> clazz) throws IOException
	{
		return mapper.readValue(jsonString, clazz);

	}

	/**
	 * Get the Generic type objects i.e . List<MyObject>
	 * 
	 * @param jsonString
	 * @param typeReference
	 * @return
	 * @throws IOException
	 */

	/* Throws specific exception instead of throwing main Exception to resolve sonar issue */
	public static Object toPojo(final String jsonString, final JavaType javaType)
			throws IOException
	{
		return mapper.readValue(jsonString, javaType);

	}

	/* Added private constructor to make this class singlton utility class for resolving sonar issue */
	private JsonParserUtil()
	{
		super();
	}
}
