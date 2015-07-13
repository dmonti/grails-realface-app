<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <g:if test="${user}">
        <h3>Usuário #${user.id}</h3>
    </g:if>
    <g:else>
        <h3>Novo usuário</h3>
    </g:else>
    <div class="row">
        <div class="col-xs-6">
            <g:form controller="user" action="submit" id="${user?.id}" class="user form-horizontal" autocomplete="off">
                <div class="form-group">
                    <label for="inputName" class="col-sm-2 control-label">Nome:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="name" id="inputName" value="${user?.name}" placeholder="Nome" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputName" class="col-sm-2 control-label">Código:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="credential.code" id="inputName" value="${user?.credential?.code}" placeholder="Código" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Email:</label>
                    <div class="col-sm-10">
                        <input type="email" class="form-control" name="email" id="inputEmail" value="${user?.email}" placeholder="Email" />
                    </div>
                </div>
                <g:if test="${!user}">
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-2 control-label">Senha:</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" name="credential.password" id="inputPassword" placeholder="Senha" />
                        </div>
                    </div>
                </g:if>
                <g:else>
                    <div class="form-group">
                        <label class="col-xs-2 control-label">Papéis:</label>
                        <div class="col-xs-10">
                            <table class="users table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Nome</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <g:each in="${user?.getRoles()}">
                                        <g:render template="role_row" bean="${it}" />
                                    </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </g:else>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                      <button type="submit" data-loading-text="Salvando..." class="btn btn-primary">Salvar</button>
                    </div>
                </div>
            </g:form>
        </div>
    </div>
    <asset:javascript src="view/user.js"/>
</body>
</html>