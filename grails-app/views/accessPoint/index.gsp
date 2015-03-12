<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <h3>Pontos de acesso</h3>
    <div class="row actions">
        <div class="col-xs-12">
            <g:link controller="AccessPoint" action="create" class="btn btn-primary pull-right">Cadastrar novo</g:link>
        </div>
    </div>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>#</th>
          <th>Nome</th>
          <th>Data criação</th>
          <th>Última atualização</th>
          <th>Editar</th>
        </tr>
      </thead>
      <tbody>
          <g:each in="${points}">
            <tr>
                <th scope="row">${it.id}</th>
                <td>${it.name}</td>
                <td><g:formatDate date="${it.dateCreated}" /></td>
                <td><g:formatDate date="${it.lastUpdated}" /></td>
                <td>
                    <g:link controller="AccessPoint" action="edit" id="${it.id}">
                        <span class="glyphicon glyphicon-edit"></span>
                    </g:link>
                </td>
            </tr>
          </g:each>
      </tbody>
    </table>
</body>
</html>