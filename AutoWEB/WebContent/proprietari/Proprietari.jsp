<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Proprietari auto</title>
</head>

<body>
	<%@include file="/header.jspf"%>

	<h1>Proprietari</h1>

	<s:set var="selectedPageSize" value="pageSize" scope="request"/>
	<display:table name="mainList" requestURI="Proprietari" id="listid" partialList="true" pagesize="${selectedPageSize}" size="count">
		<display:column property="id" title="ID" sortable="true"/>
		<display:column property="nome" title="Nome" sortable="true"/>
		<display:column property="cognome" title="Cognome" sortable="true"/>
		<display:column media="html" title="Azioni">
			<s:url action="ProprietariPrepare" id="editUrl">
				<s:param name="id" value="%{#attr.listid.id}"/>
			</s:url>
			<a href="<s:property value="#editUrl"/>"><img src="../images/edit.png" border="0"/></a>
			
			<s:url action="ProprietariDelete" id="deleteUrl">
				<s:param name="id" value="%{#attr.listid.id}"/>
			</s:url>
			<a href="<s:property value="#deleteUrl"/>" onclick="return confirm('Si è sicuri di voler eliminare il record?');"><img src="../images/delete.png" border="0"/></a>
		</display:column>
	</display:table>
	
	<p/>
	Inserisci nuovo record
	<s:form action="ProprietariInsert" validate="true">
		<s:hidden name="id"/>
		<s:hidden name="editMode"/>
    	<s:textfield name="nome" label="Nome" required="true"/>
    	<s:textfield name="cognome" label="Cognome" required="true"/>
    	
    	<s:set var="mode" value="%{editMode.name()}"/>
    	<s:if test="%{#mode=='INSERT'}"><s:submit value="Inserisci"/></s:if>
    	<s:elseif test="%{#mode=='EDIT'}"><s:submit value="Modifica"/></s:elseif>
	</s:form>
</body>
</html>