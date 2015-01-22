<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <g:if test="${role}">
        <h3>Papel #${role.id}</h3>
    </g:if>
    <g:else>
        <h3>Novo papel</h3>
    </g:else>
    <div class="row">
        <div class="col-xs-12">
            <g:form controller="role" action="submit" id="${role?.id}" class="role form-horizontal">
                <g:render template="fields" />
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" data-loading-text="Salvando..." class="btn btn-primary">Salvar</button>
                    </div>
                </div>
            </g:form>
        </div>
    </div>
    <asset:javascript src="role.js"/>
</body>
</html>