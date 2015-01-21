<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <h3>Papéis</h3>
    <div class="row actions">
        <div class="col-xs-12">
            <g:link controller="Role" action="create" class="btn btn-primary pull-right">Cadastrar novo</g:link>
        </div>
    </div>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>#</th>
          <th>Nome</th>
          <th>Data de criação</th>
          <th>Última atualização</th>
          <th>Editar</th>
        </tr>
      </thead>
      <tbody>
          <g:each in="${roles}">
            <tr>
                <td scope="row">${it.id}</td>
                <td>${it.name}</td>
                <td><g:formatDate date="${it.dateCreated}" /></td>
                <td><g:formatDate date="${it.lastUpdated}" /></td>
                <td>
                    <g:link class="edit" controller="Role" action="editModal" id="${it.id}">
                        <span class="glyphicon glyphicon-edit"></span>
                    </g:link>
                </td>
            </tr>
          </g:each>
      </tbody>
    </table>
    <asset:javascript src="role.js"/>
</body>
</html>