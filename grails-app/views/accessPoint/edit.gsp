<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <g:if test="${point}">
        <h3>Ponto de acesso #${point.id}</h3>
    </g:if>
    <g:else>
        <h3>Novo ponto de acesso</h3>
    </g:else>
    <div class="row">
        <div class="col-xs-6">
            <g:form controller="point" action="submit" id="${point?.id}" class="point form-horizontal">
                <div class="form-group">
                    <label for="inputName" class="col-sm-2 control-label">Nome:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="name" id="inputName" value="${point?.name}" placeholder="Nome" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputDescription" class="col-sm-2 control-label">Descrição:</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" rows="4" name="description" id="inputDescription" placeholder="Descrição">${point?.description}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label">Regras de acesso:</label>
                    <div class="col-xs-10">
                        <table class="users table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Nome</th>
                                    <th>&nbsp;
                                        <g:link controller="Template" action="load" params="${[path: '/accessPoint/add_rule_modal', 'accessPoint.id': point?.id]}" class="add-rule">
                                            <span class="glyphicon glyphicon-plus-sign pull-right" />
                                        </g:link>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <g:each in="${point?.rules}">
                                    <g:render template="rule_row" bean="${it}" />
                                </g:each>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" data-loading-text="Salvando..." class="btn btn-primary">Salvar</button>
                    </div>
                </div>
            </g:form>
        </div>
    </div>
    <asset:javascript src="point.js"/>
</body>
</html>