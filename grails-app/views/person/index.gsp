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
          <th>Editar</th>
        </tr>
      </thead>
      <tbody>
          <g:each in="${persons}">
            <tr>
            <th scope="row">${it.id}</th>
            <td>${it.name}</td>
            <td>${it.email}</td>
            <td>${it.dateCreated}</td>
            <td>
                <g:link controller="person" action="edit" id="${it.id}">
                    <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                </g:link>
            </td>
            </tr>
          </g:each>
      </tbody>
    </table>
</body>
</html>