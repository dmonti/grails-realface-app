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
                    <label for="inputDescription" class="col-sm-2 control-label">Dispositivos:</label>
                    <div class="col-sm-5">
                        <select class="form-control">
                            <g:each in="${devices}">
                                <option>${it}</option>
                            </g:each>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-offset-2 col-xs-10">
                            <div class="panel panel-default">
                                <div class="panel-heading">Regras de acesso:</div>
                                <div class="panel-body">
                                    <input type="text" class="form-control" name="rule" placeholder="Procurar regra de acesso..." />
                                </div>
                                <table class="users table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nome</th>
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
    <asset:javascript src="view/point.js"/>
</body>
</html>