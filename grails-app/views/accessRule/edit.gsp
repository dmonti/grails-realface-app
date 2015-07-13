<%@ page import="com.realface.RuleType" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <g:if test="${rule}">
        <h3>Regra de acesso #${rule.id}</h3>
    </g:if>
    <g:else>
        <h3>Novo ponto de acesso</h3>
    </g:else>
    <div class="row">
        <div class="col-xs-6">
            <g:form controller="accessRule" action="submit" id="${rule?.id}" class="rule form-horizontal">
                <div class="form-group">
                    <label for="inputCode" class="col-xs-3 control-label">Código:</label>
                    <div class="col-xs-9">
                        <input type="text" class="form-control" name="code" id="inputCode" value="${rule?.code}" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputDescription" class="col-xs-3 control-label">Descrição:</label>
                    <div class="col-xs-9">
                        <textarea class="form-control" rows="4" name="description" id="inputDescription">${rule?.description}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputDescription" class="col-xs-3 control-label">Tipo:</label>
                    <div class="col-xs-9">
                        <select multiple class="form-control rule-type" name="type">
                            <g:each in="${RuleType.values()}">
                                <option value="${it.ordinal()}" ${it.equals(rule?.type) ? 'selected' : ''}><g:message code="${it}" /></option>
                            </g:each>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputAfterTime" class="col-xs-3 control-label">Apartir de:</label>
                    <div class="col-xs-9">
                        <input type="text" class="form-control" name="afterTime" id="inputAfterTime" value="${afterTime}" placeholder="HH:mm" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputBeforeTime" class="col-xs-3 control-label">Até:</label>
                    <div class="col-xs-9">
                        <input type="text" class="form-control" name="beforeTime" id="inputBeforeTime" value="${beforeTime}" placeholder="HH:mm" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">Usuários:</label>
                    <div class="col-xs-9">
                        <table class="users table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Nome</th>
                                    <th>Email</th>
                                    <th>&nbsp;
                                        <g:link controller="Template" action="load" params="${[path: '/role/add_user_modal']}" class="add-user">
                                            <span class="glyphicon glyphicon-plus-sign pull-right" />
                                        </g:link>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <g:each in="${rule?.getUsers()}">
                                    <g:render template="users_row" bean="${it}" />
                                </g:each>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">Papéis:</label>
                    <div class="col-xs-9">
                        <table class="users table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Nome</th>
                                    <th>&nbsp;
                                        <g:link controller="Template" action="load" params="${[path: '/role/add_role_modal']}" class="add-user">
                                            <span class="glyphicon glyphicon-plus-sign pull-right" />
                                        </g:link>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <g:each in="${rule?.getRoles()}">
                                    <g:render template="users_row" bean="${it}" />
                                </g:each>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-9">
                        <button type="submit" data-loading-text="Salvando..." class="btn btn-primary">Salvar</button>
                    </div>
                </div>
            </g:form>
        </div>
    </div>
    <asset:javascript src="view/rule.js"/>
</body>
</html>