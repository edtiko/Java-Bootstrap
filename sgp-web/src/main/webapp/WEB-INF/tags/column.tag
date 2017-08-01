<%@ tag language="java" %>
<%@ attribute name="field" required="true" %>
<%@ attribute name="label" required="true" rtexprvalue="true" %>
<%@ attribute name="width" required="false" %>
<%@ attribute name="cssclass" required="false" %>
<%@ attribute name="styleColumnHeader" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:choose>
  <c:when test="${org_languagetool_tags_table_outputmode == 'TABLE'}">
    <th style="${styleColumnHeader}"><spring:message code="${label}" /></th>
  </c:when>
  <c:when test="${org_languagetool_tags_table_outputmode == 'SCRIPT'}">
    <c:choose>
      <c:when test="${org_languagetool_tags_table_firstcolumn == 'TRUE' }">
        <c:set var="org_languagetool_tags_table_firstcolumn" value="FALSE" scope="request" />
      </c:when>
      <c:otherwise>,</c:otherwise>
    </c:choose>
    {"mDataProp":"${field}",
     "sWidth"   :'${width}',
     "sClass"	:"${cssclass}"}
  </c:when>
</c:choose>