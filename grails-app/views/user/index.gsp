<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <h3>Usuários</h3>
    <div class="row actions">
        <div class="col-xs-12">
            <g:link controller="User" action="create" class="btn btn-primary pull-right">Cadastrar novo</g:link>
        </div>
    </div>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>#</th>
          <th>Código</th>
          <th>Nome</th>
          <th>Email</th>
          <th>Data de criação</th>
          <th>Última atualização</th>
          <th>Editar</th>
        </tr>
      </thead>
      <tbody>
          <g:each in="${users}">
            <tr>
                <td scope="row">${it.id}</td>
                <td>${it.credential.code}</td>
                <td>${it.name}</td>
                <td>${it.email}</td>
                <td><g:formatDate date="${it.dateCreated}" /></td>
                <td><g:formatDate date="${it.lastUpdated}" /></td>
                <td>
                    <g:link controller="user" action="edit" id="${it.id}">
                        <span class="glyphicon glyphicon-edit"></span>
                    </g:link>
                </td>
            </tr>
          </g:each>
      </tbody>
    </table>
</body>
</html>