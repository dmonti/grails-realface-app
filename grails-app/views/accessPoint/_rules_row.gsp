<%@ page import="com.realface.AccessRule" %>
<g:if test="${it == null}">
    <g:set var="it" value="${AccessRule.get(id)}" />
</g:if>
<tr id="rule-${it.id}" data-id="${it.id}">
    <td>${it.id}</td>
    <td>${it.name}</td>
    <td><a class="rule-remove" href="javascript:void(0);"><span class="glyphicon glyphicon-minus-sign pull-right" /></a></td>
</tr>