<%@ page import="com.realface.User" %>
<g:if test="${it == null}">
    <g:set var="it" value="${User.get(id)}" />
</g:if>
<tr id="user-${it.id}">
    <input type="hidden" name="users.id" value="${it.id}" />
    <td>${it.id}</td>
    <td>${it.name}</td>
    <td>${it.email}</td>
    <td><a class="user-remove" href="javascript:void(0);"><span class="glyphicon glyphicon-minus-sign pull-right" /></a></td>
</tr>