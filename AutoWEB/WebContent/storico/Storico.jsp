<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Storico auto</title>
</head>

<body>
	<%@include file="/header.jspf"%>

	<h1>Storico</h1>

	<s:set var="selectedPageSize" value="pageSize" scope="request"/>
	<display:table name="mainList" requestURI="Storico" id="listid" partialList="true" pagesize="${selectedPageSize}" size="count">
		<display:column title="Proprietario" sortable="true">
			<s:property value="%{#attr.listid.proprietario.nome+' '+#attr.listid.proprietario.cognome}"/>
		</display:column>
		<display:column title="Modello" sortable="true">
			<s:property value="%{#attr.listid.modello.marca.nome+' '+#attr.listid.modello.modello+' '+#attr.listid.modello.cilindrata+' '+#attr.listid.modello.anno}"/>
		</display:column>
		<display:column property="targa" title="Targa" sortable="true"/>
		<display:column media="html" title="Azioni">
			<s:url action="StoricoPrepare" id="editUrl" escapeAmp="false">
				<s:param name="idproprietario" value="%{#attr.listid.idproprietario}"/>
				<s:param name="idmodello" value="%{#attr.listid.idmodello}"/>
				<s:param name="targa" value="%{#attr.listid.targa}"/>
			</s:url>
			<a href="<s:property value="#editUrl"/>"><img src="../images/edit.png" border="0"/></a>
			
			<s:url action="StoricoDelete" id="deleteUrl" escapeAmp="false">
				<s:param name="idproprietario" value="%{#attr.listid.idproprietario}"/>
				<s:param name="idmodello" value="%{#attr.listid.idmodello}"/>
				<s:param name="targa" value="%{#attr.listid.targa}"/>
			</s:url>
			<a href="<s:property value="#deleteUrl"/>" onclick="return confirm('Si è sicuri di voler eliminare il record?');"><img src="../images/delete.png" border="0"/></a>
		</display:column>
	</display:table>
	
	<p/>
	Inserisci nuovo record
	<s:form action="StoricoInsert" validate="true">
		<s:hidden name="storicoOld.idproprietario" value="%{idproprietario}"/>
		<s:hidden name="storicoOld.idmodello" value="%{idmodello}"/>
		<s:hidden name="storicoOld.targa" value="%{targa}"/>
		<s:hidden name="editMode"/>
		
    	<s:select list="proprietari" name="idproprietario" emptyOption="true" label="Proprietario" listKey="id" listValue="%{nome+' '+cognome}" required="true"/>
    	<s:select list="modelli" name="idmodello" emptyOption="true" label="Modello" listKey="id" listValue="%{marca.nome+' '+modello+' '+cilindrata+' '+anno}" required="true"/>
    	<s:textfield name="targa" label="Targa" required="true"/>
    	
    	<s:set var="mode" value="%{editMode.name()}"/>
    	<s:if test="%{#mode=='INSERT'}"><s:submit value="Inserisci"/></s:if>
    	<s:elseif test="%{#mode=='EDIT'}"><s:submit value="Modifica"/></s:elseif>
	</s:form>
</body>
</html>