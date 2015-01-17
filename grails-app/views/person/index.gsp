<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <h3>Pessoas cadastradas</h3>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>#</th>
          <th>Nome</th>
          <th>Email</th>
          <th>Data de criação</th>
          <th>Última atualização</th>
          <th>Editar</th>
        </tr>
      </thead>
      <tbody>
          <g:each in="${persons}">
            <tr>
                <td scope="row">${it.id}</td>
                <td>${it.name}</td>
                <td>${it.email}</td>
                <td><g:formatDate date="${it.dateCreated}" /></td>
                <td><g:formatDate date="${it.lastUpdated}" /></td>
                <td>
                    <g:link controller="person" action="edit" id="${it.id}">
                        <span class="glyphicon glyphicon-edit"></span>
                    </g:link>
                </td>
            </tr>
          </g:each>
      </tbody>
    </table>
</body>
</html>