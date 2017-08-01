<%@tag description="Extended input tag to allow for sophisticated errors" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<%@attribute name="path" required="true" type="java.lang.String"%>
<%@attribute name="cssClass" required="false" type="java.lang.String"%>
<%@attribute name="label" required="false" type="java.lang.String"%>
<%@attribute name="required" required="false" type="java.lang.Boolean"%>
<%@attribute name="prepend" required="false" type="java.lang.Boolean"%>
<%@attribute name="append" required="false" type="java.lang.Boolean"%>
<%@attribute name="icon" required="false" type="java.lang.String"%>
 
<c:if test="${empty label}">
    <c:set var="label" value="${fn:toUpperCase(fn:substring(path, 0, 1))}${fn:toLowerCase(fn:substring(path, 1,fn:length(path)))}" />
</c:if>
<c:if test="${fn:length(icon) gt 1 }">
  <c:set var="icon" value='<i class="${icon}"></i>'/>
</c:if>
 
<div class="control-group ${status.error ? 'error' : '' }">
  <label class="control-label" for="${path}">
    ${label}
    <c:if test="${required}">
      <span class="required"> *</span>
    </c:if>
  </label>
  <div class="controls">
    <c:choose>
      <c:when test="${prepend}">
        <div class="input-prepend">
          <span class="add-on">${icon}</span>
          <form:input path="${path}" cssClass="${empty cssClass ? 'input-xlarge' : cssClass}"/>
        </div>
      </c:when>
      <c:when test="${append}">
        <div class="input-append">
          <form:input path="${path}" cssClass="${empty cssClass ? 'input-xlarge' : cssClass}"/>
          <span class="add-on">${icon}</span>
        </div>
      </c:when>
      <c:otherwise>
        <form:input path="${path}" cssClass="${empty cssClass ? 'input-xlarge' : cssClass}"/>
      </c:otherwise>
    </c:choose>
    <c:if test="${status.error}">
        <span class="help-inline">${status.errorMessage}</span>
    </c:if>
  </div>
</div>