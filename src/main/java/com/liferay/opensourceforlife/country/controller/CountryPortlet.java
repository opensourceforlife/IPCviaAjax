/**
 * 
 */
package com.liferay.opensourceforlife.country.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.liferay.opensourceforlife.country.util.JsonParserUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * @author Tejas Kanani
 * 
 */
public class CountryPortlet extends MVCPortlet
{

	@Override
	public void doView(final RenderRequest renderRequest, final RenderResponse renderResponse)
			throws IOException, PortletException
	{
		super.doView(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(final ResourceRequest resourceRequest,
			final ResourceResponse resourceResponse) throws IOException, PortletException
	{
		String command = ParamUtil.getString(resourceRequest, "COMMAND");
		String jsonOutput = StringPool.BLANK;
		if ("GETCOUNTRY".equalsIgnoreCase(command))
		{
			jsonOutput = getCountries();
		}

		final PrintWriter writer = resourceResponse.getWriter();

		try
		{
			final String jsonString = JsonParserUtil.toJson(jsonOutput);
			writer.write(jsonString);
		} catch (final Exception e)
		{
			LOG.warn("Unable to parse the Json response map");
		} finally
		{
			writer.flush();
			writer.close();
		}
	}

	private String getCountries()
	{
		String countryList = StringPool.BLANK;
		Map<String, String> countryMap = new HashMap<String, String>();
		countryMap.put("India", "IN");
		countryMap.put("USA", "US");

		try
		{
			countryList = JsonParserUtil.toJson(countryMap);
		} catch (IOException e)
		{
			LOG.error(e.getMessage(), e);
		}

		return countryList;
	}

	private static final Log LOG = LogFactoryUtil.getLog(CountryPortlet.class);
}
