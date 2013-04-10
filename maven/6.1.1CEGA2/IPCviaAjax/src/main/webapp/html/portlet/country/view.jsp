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

<portlet:resourceURL var="selectCountryUrl"></portlet:resourceURL>

<p>Please click on any country from below to get list of state in State Portlet using Inter Portlet Communication(IPC) via Ajax</p>
 
<%-- We'll fill the list of country dynamically via ajax call using resourceURL --%>
<div id="country"></div>



<script type="text/javascript">		
	jQuery(document).ready(function(){
		var selectCountryUrl = '<%=selectCountryUrl%>';
		
		// On load of page we'll load the category via ajax call by using resourceURL
		loadCountry(selectCountryUrl);
		
		// Bink on click event on country list
		bindCountryOnClick();
	});
	
	
	function loadCountry(selectCountryUrl)
	{
		var param="&COMMAND=GETCOUNTRY";		
		
		jQuery.ajax({
			type:"GET",
			url:selectCountryUrl,
			cache:false,
			async:false,
			dataType: "json",
			data:param,
			success: function(data){
				// preparing list of country dynamically
				var htmlText="";
				var count=0;
				htmlText+="<ul>";
				$.each(jQuery.parseJSON(data), function(key,val){
			   	  	htmlText+="<li class='countryLi'><a id='"+ val +"'>"+key+"</a></li>";
				});
				htmlText+="</ul>";
	
				// Replace html data on #country element which we've already placed above 
				$("#country").html(htmlText);
			}
		});		
	}
	
	
	function bindCountryOnClick()
	{
		$("#country li a").click( function() {
			var selectedCountry=$(this);
			var selectedCountryCode=selectedCountry.attr("id");
			var selectedCountryName=selectedCountry.text();
			
			// Fire the UPDATE_STATE event of State Portlet by passing selected country's countyCode. 
			// Check State portlet's view.jsp for detail.
			Liferay.fire('UPDATE_STATE', 
				{
				      countryCode : selectedCountryCode,
				      countryName : selectedCountryName
				}
			);
		});
	}
</script> 

