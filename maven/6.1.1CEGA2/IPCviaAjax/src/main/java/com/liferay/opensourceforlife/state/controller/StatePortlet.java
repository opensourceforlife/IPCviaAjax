/**
 * 
 */
package com.liferay.opensourceforlife.state.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class StatePortlet extends MVCPortlet
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
		String countryCode = ParamUtil.getString(resourceRequest, "COUNTRYCODE");

		String jsonOutput = StringPool.BLANK;
		if ("GETSTATE".equalsIgnoreCase(command))
		{
			jsonOutput = getStates(countryCode);
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

	private String getStates(final String countryCode)
	{
		String states = StringPool.BLANK;
		List<String> stateList = getStateMap().get(countryCode);

		try
		{
			states = JsonParserUtil.toJson(stateList);
		} catch (IOException e)
		{
			LOG.error(e.getMessage(), e);
		}

		return states;
	}

	private Map<String, List<String>> getStateMap()
	{
		Map<String, List<String>> states = new HashMap<String, List<String>>();
		List<String> stateList = new ArrayList<String>();
		stateList.add("Gujarat");
		stateList.add("Maharashtra");
		states.put("IN", stateList);

		stateList = new ArrayList<String>();
		stateList.add("PA");
		stateList.add("CA");
		states.put("US", stateList);

		return states;
	}

	private static final Log LOG = LogFactoryUtil.getLog(StatePortlet.class);
}
