<%@ page import="com.realface.Role" %>
<g:if test="${it == null}">
    <g:set var="it" value="${Role.get(id)}" />
</g:if>
<tr id="role-${it.id}">
    <input type="hidden" name="roles.id" value="${it.id}" />
    <td>${it.id}</td>
    <td>${it.name}</td>
</tr>