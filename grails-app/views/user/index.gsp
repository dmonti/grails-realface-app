<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <h3>Usuários</h3>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>#</th>
          <th>Código</th>
          <th>Nome</th>
          <th>Email</th>
          <th>Data de criação</th>
        </tr>
      </thead>
      <tbody>
          <g:each in="${users}">
            <tr>
            <th scope="row">${it.id}</th>
            <td>${it.code}</td>
            <td>${it.name}</td>
            <td>${it.email}</td>
            <td>${it.dateCreated}</td>
            </tr>
          </g:each>
      </tbody>
    </table>
</body>
</html>