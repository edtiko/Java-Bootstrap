<%@ tag language="java" description="Extended input tag to allow for sophisticated errors" pageEncoding="UTF-8"%>
<%@attribute name="path" required="true" type="java.lang.String"%>
<%@attribute name="cssClass" required="false" type="java.lang.String"%>
<%@attribute name="label" required="false" type="java.lang.String"%>
<%@attribute name="required" required="false" type="java.lang.Boolean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${empty label}">
	<c:set var="label" value="${fn:toUpperCase(fn:substring(path, 0, 1))}${fn:toLowerCase(fn:substring(path, 1,fn:length(path)))}" />
</c:if>
<div class="control-group ${status.error ? 'error' : '' }">
	<label class="control-label" for="${path}">${label}<c:if test="${required}"><span class="required">*</span></c:if></label>
	<div class="controls">
		<form:input type="text" path="${path}" cssClass="${empty cssClass ? 'input-medium' : cssClass}" placeholder="${label}" />
		<c:if test="${status.error}">
			<span class="help-inline">${status.errorMessage}</span>
		</c:if>
	</div>
</div>