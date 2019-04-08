<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<html>
<head>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript" src="../lib/Fluster2.packed.js"></script>
<script type="text/javascript">
	
// OnLoad function ...
function initialize() {
  	
	// Create a new map with some default settings
    var myLatlng = new google.maps.LatLng(25,25);
    var myOptions = {
      zoom: 3,
      center: myLatlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
	
	// Initialize Fluster and give it a existing map
	var fluster = new Fluster2(map);
	
	for(var i = 0; i < 200; i++)
	{
		var pos = [
			50 * Math.random(),
			50 * Math.random()
		];
		
		// Create a new marker. Don't add it to the map!
		var marker = new google.maps.Marker({
			position: new google.maps.LatLng(pos[0], pos[1]),
			title: 'Marker ' + i
		});
		
		// Add the marker to the Fluster
		fluster.addMarker(marker);
	}
	
	// Set styles
	// These are the same styles as default, assignment is only for demonstration ...
	fluster.styles = {
		// This style will be used for clusters with more than 0 markers
		0: {
			image: 'http://gmaps-utility-library.googlecode.com/svn/trunk/markerclusterer/1.0/images/m1.png',
			textColor: '#FFFFFF',
			width: 53,
			height: 52
		},
		// This style will be used for clusters with more than 10 markers
		10: {
			image: 'http://gmaps-utility-library.googlecode.com/svn/trunk/markerclusterer/1.0/images/m2.png',
			textColor: '#FFFFFF',
			width: 56,
			height: 55
		},
		20: {
			image: 'http://gmaps-utility-library.googlecode.com/svn/trunk/markerclusterer/1.0/images/m3.png',
			textColor: '#FFFFFF',
			width: 66,
			height: 65
		}
	};
	

	fluster.initialize();
	
}

</script>

</head>
<%-- <body onload="initialize()">
<div id="map_canvas" style="width: 600px; height: 400px"></div>
</body>
</html>
--%>
	<body>
	<h1>Marche</h1>

	<%@include file="/header.jspf"%>
	<br>
	
	<s:set var="selectedPageSize" value="pageSize" scope="request" />
	<display:table name="mainList" requestURI="Marche" id="listid"
		partialList="true" pagesize="${selectedPageSize}" size="count">
		<display:column property="id" title="ID" sortable="true" />
		<display:column property="nome" title="Marca" sortable="true" />
		<display:column media="html" title="Azioni">
			<s:url action="MarchePrepare" id="editUrl">
				<s:param name="id" value="%{#attr.listid.id}" />
			</s:url>
			<a href="<s:property value="#editUrl"/>"><img
				src="../images/edit.png" border="0" />
			</a>

			<s:url action="MarcheDelete" id="deleteUrl">
				<s:param name="id" value="%{#attr.listid.id}" />
			</s:url>
			<a href="<s:property value="#deleteUrl"/>"
				onclick="return confirm('Si è sicuri di voler eliminare il record?');"><img
				src="../images/delete.png" border="0" />
			</a>
		</display:column>
	</display:table>

	<p />
	Inserisci nuovo record
	<s:form action="MarcheInsert" validate="true">
		<s:hidden name="id" />
		<s:hidden name="editMode" />
		<s:textfield name="nome" label="Nome" required="true" />

		<s:set var="mode" value="%{editMode.name()}" />
		<s:if test="%{#mode=='INSERT'}">
			<s:submit value="Inserisci" />
		</s:if>
		<s:elseif test="%{#mode=='EDIT'}">
			<s:submit value="Modifica" />
		</s:elseif>
	</s:form>
</body>
</html>