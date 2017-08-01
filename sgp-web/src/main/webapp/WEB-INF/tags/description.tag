<%@ tag language="java" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="cssClass" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<dl class="${cssClass}" id="${id}">
	<jsp:doBody />
</dl>
