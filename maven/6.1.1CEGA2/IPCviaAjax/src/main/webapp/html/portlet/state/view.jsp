<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@include file="./init.jsp"%>

<portlet:resourceURL var="selectStateUrl"></portlet:resourceURL>

<p>Please click on any country from below to get list of state in State Portlet using Inter Portlet Communication(IPC) via Ajax</p>

<div id="state"></div>

<script type="text/javascript">		
	jQuery(document).ready(function(){
		var selectStateUrl = '<%=selectStateUrl%>';
		
		// Bind UPDATE_STATE event on page load so that It can catch the event being called from another portlet
		Liferay.on(
		   'UPDATE_STATE',
		   function(data) {
			   // When event is being executed, getState of the selected countryCode via ajax call using resourceURL
			   getState(selectStateUrl, data.countryCode, data.countryName)
		   }		
		);
	});
	
	function getState(selectStateUrl, countryCode, countryName)
	{
		var param="&COMMAND=GETSTATE&COUNTRYCODE="+countryCode;		
		
		jQuery.ajax({
			type:"GET",
			url:selectStateUrl,
			cache:false,
			async:false,
			dataType: "json",
			data:param,
			success: function(data){
				// preparing list of states dynamically
				var htmlText="";
				var count=0;
				htmlText+="<p>States list for country "+ countryName +" are as below</p>";
				htmlText+="<ul>";
				$.each(jQuery.parseJSON(data), function(index,state){
			   	  	htmlText+="<li class='stateLi'>"+state+"</li>";
				});
				htmlText+="</ul>";
	
				// Replace html data on #state element which we've already placed above 
				$("#state").html(htmlText);
			}
		});		
	}
</script> 

