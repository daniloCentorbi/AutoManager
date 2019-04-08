<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modelli auto</title>
</head>

<body>
	<%@include file="/header.jspf"%>

	<h1>Modelli</h1>

	<s:set var="selectedPageSize" value="pageSize" scope="request"/>
	<display:table name="mainList" requestURI="Modelli" id="listid" partialList="true" pagesize="${selectedPageSize}" size="count">
		<display:column property="id" title="ID" sortable="true"/>
		<display:column property="marca.nome" title="Marca" sortable="true"/>
		<display:column property="modello" title="Modello" sortable="true"/>
		<display:column property="cilindrata" title="Cilindrata" sortable="true"/>
		<display:column property="anno" title="Anno" sortable="true"/>
		<display:column media="html" title="Azioni">
			<s:url action="ModelliPrepare" id="editUrl">
				<s:param name="id" value="%{#attr.listid.id}"/>
			</s:url>
			<a href="<s:property value="#editUrl"/>"><img src="../images/edit.png" border="0"/></a>
			
			<s:url action="ModelliDelete" id="deleteUrl">
				<s:param name="id" value="%{#attr.listid.id}"/>
			</s:url>
			<a href="<s:property value="#deleteUrl"/>" onclick="return confirm('Si è sicuri di voler eliminare il record?');"><img src="../images/delete.png" border="0"/></a>
		</display:column>
	</display:table>
	
	<p/>
	Inserisci nuovo record
	<s:form action="ModelliInsert" validate="true">
		<s:hidden name="id"/>
		<s:hidden name="editMode"/>
    	<s:select list="marche" name="idmarca" emptyOption="true" label="Marca" listKey="id" listValue="nome" required="true"/>
		<s:textfield name="modello" label="Modello" required="true"/>
		<s:textfield name="cilindrata" label="Cilindrata"/>
		<s:textfield name="anno" label="Anno" maxlength="4" size="4"/>
    	
    	<s:set var="mode" value="%{editMode.name()}"/>
    	<s:if test="%{#mode=='INSERT'}"><s:submit value="Inserisci"/></s:if>
    	<s:elseif test="%{#mode=='EDIT'}"><s:submit value="Modifica"/></s:elseif>
	</s:form>
</body>
</html>