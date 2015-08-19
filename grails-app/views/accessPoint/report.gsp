<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <h3>Relatório de últimos acessos</h3>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>#</th>
          <th>Ponto de acesso</th>
          <th>Usuário</th>
          <th>Estado</th>
          <th>Data</th>
        </tr>
      </thead>
      <tbody>
          <g:each in="${events}">
            <tr>
            <th scope="row">${it.id}</th>
            <td>${it.accessPoint.name}</td>
            <td>${it.user.name}</td>
            <td>${it.status}</td>
            <td><g:formatDate date="${it.dateCreated}" /></td>
            </tr>
          </g:each>
      </tbody>
    </table>
</body>
</html>