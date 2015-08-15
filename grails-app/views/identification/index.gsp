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
          <th>Usuário ID 1</th>
          <th>Imagem</th>
          <th>Usuário ID 2</th>
          <th>Imagem</th>
          <th>Status</th>
          <th>Score</th>
          <th>Data de criação</th>
        </tr>
      </thead>
      <tbody>
          <g:each in="${logs}">
            <tr>
                <td scope="row">${it.id}</td>
                <td>#${it.source.user?.id}</td>
                <td>
                    <a style="width: 128px;" class="thumbnail">
                        <img src="${createLink(controller: 'photo', action: 'resource', id: it.source.id)}" />
                    </a>
                </td>
                <td>#${it.target?.user?.id}</td>
                <td>
                    <g:if test="${it.target}">
                        <a style="width: 128px;" class="thumbnail">
                            <img src="${createLink(controller: 'photo', action: 'resource', id: it.target?.id)}" />
                        </a>
                    </g:if>
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