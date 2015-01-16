<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <h3>Log de accessos ao sistema</h3>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>#</th>
          <th>Código</th>
          <th>Estado</th>
          <th>Data</th>
        </tr>
      </thead>
      <tbody>
          <g:each in="${logs}">
            <tr>
            <th scope="row">${it.id}</th>
            <td>${it.userCode}</td>
            <td>${it.status}</td>
            <td>${it.dateCreated}</td>
            </tr>
          </g:each>
      </tbody>
    </table>
</body>
</html>