<%@ page import="com.realface.RuleType" %>
<div class="modal fade" id="editModal" role="dialog" aria-labelledby="editlLabel" aria-hidden="true">
    <div class="modal-dialog">
        <g:form controller="AccessRule" action="create" class="rule modal-content form-horizontal">
            <input type="hidden" name="accessPoint.id" value="${accessPoint.id}">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span>&times;</span></button>
                <h4 class="modal-title" id="editlLabel">Adicionar nova regra</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="inputType" class="col-xs-3 control-label">Tipo:</label>
                    <g:each in="${RuleType.values()}" id="inputType" var="type" status="i">
                        <label class="radio-inline">
                            <input type="radio" name="type" value="${i}"><g:message message="${type}" />
                        </label>
                    </g:each>
                </div>
                <div class="form-group">
                    <label for="inputAfterTime" class="col-xs-3 control-label">Após:</label>
                    <div class="col-xs-3">
                        <input type="text" class="form-control" id="inputAfterTime" name="afterTime" placeholder="HH:mm">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputBeforeTime" class="col-xs-3 control-label">Antes:</label>
                    <div class="col-xs-3">
                        <input type="text" class="form-control" id="inputBeforeTime" name="beforeTime" placeholder="HH:mm">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">Papéis:</label>
                    <div class="col-xs-6">
                        <table class="roles table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Nome</th>
                                    <th>&nbsp;
                                        <g:link controller="Template" action="load" params="${[path: '/rule/add_role_modal']}" class="add-role">
                                            <span class="glyphicon glyphicon-plus-sign pull-right" />
                                        </g:link>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <g:each in="${rule?.roles}">
                                    <g:render template="roles_row" bean="${it}" />
                                </g:each>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">Usuários:</label>
                    <div class="col-xs-6">
                        <table class="users table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Nome</th>
                                    <th>Email</th>
                                    <th>&nbsp;
                                        <g:link controller="Template" action="load" params="${[path: '/rule/add_user_modal']}" class="add-user">
                                            <span class="glyphicon glyphicon-plus-sign pull-right" />
                                        </g:link>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <g:each in="${rule?.users}">
                                    <g:render template="users_row" bean="${it}" />
                                </g:each>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" name="add" class="btn btn-primary" data-dismiss="modal">Criar</button>
                <button type="button" name="cancel" class="btn btn-default" data-dismiss="modal">Cancelar</button>
            </div>
        </g:form>
    </div>
</div>
<asset:javascript src="view/rule.js"/>