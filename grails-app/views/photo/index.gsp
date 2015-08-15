<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <h3>Fotos</h3>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>#</th>
          <th>Usuário</th>
          <th>Imagem</th>
          <th>Autenticidade</th>
          <th>Status do template</th>
          <th>Ponto de acesso</th>
          <th>Data de criação</th>
        </tr>
      </thead>
      <tbody>
          <g:each in="${photos}">
            <tr>
                <td scope="row">${it.id}</td>
                <td scope="row">${it.user ? "${it.user.name} #${it.user.id}" : "-"}</td>
                <td>
                    <a style="width: 128px;" class="thumbnail">
                        <img src="${createLink(controller: 'photo', action: 'resource', id: it.id)}" />
                    </a>
                </td>
                <td>${it.authenticity}</td>
                <td>${it.status}</td>
                <td>${it.accessPoint?.name}</td>
                <td><g:formatDate date="${it.dateCreated}" /></td>
            </tr>
          </g:each>
      </tbody>
    </table>
</body>
</html>