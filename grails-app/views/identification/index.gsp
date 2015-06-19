<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <h3>Logs de identificação</h3>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>#</th>
          <th>Imagem 1</th>
          <th>Imagem 2</th>
          <th>Status</th>
          <th>Score</th>
          <th>Data de criação</th>
        </tr>
      </thead>
      <tbody>
          <g:each in="${logs}">
            <tr>
                <td scope="row">${it.id}</td>
                <td>
                    <a style="width: 128px;" class="thumbnail">
                        <img src="${createLink(controller: 'photo', action: 'resource', id: it.source.id)}" />
                    </a>
                </td>
                <td>
                    <a style="width: 128px;" class="thumbnail">
                        <img src="${createLink(controller: 'photo', action: 'resource', id: it.target.id)}" />
                    </a>
                </td>
                <td>${it.status}</td>
                <td>${it.score}</td>
                <td><g:formatDate date="${it.dateCreated}" /></td>
            </tr>
          </g:each>
      </tbody>
    </table>
</body>
</html>