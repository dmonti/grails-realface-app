<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <g:if test="${user}">
        <h3>Pessoa #${user.id}</h3>
    </g:if>
    <g:else>
        <h3>Nova pessoa</h3>
    </g:else>
    <div class="row">
        <div class="col-xs-6">
            <g:form controller="user" action="submit" id="${user?.id}" class="user form-horizontal">
              <div class="form-group">
                <label for="inputName" class="col-sm-2 control-label">Nome:</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" name="name" id="inputName" value="${user?.name}" placeholder="Nome" />
                </div>
              </div>
              <div class="form-group">
                <label for="inputEmail" class="col-sm-2 control-label">Email:</label>
                <div class="col-sm-10">
                  <input type="email" class="form-control" name="email" id="inputEmail" value="${user?.email}" placeholder="Email" />
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
    <asset:javascript src="user.js"/>
</body>
</html>