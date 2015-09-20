<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <h3>Dashboard</h3>
    <div class="row">
        <div class="col-xs-5 text-center">
            <h2>Acessos permitidos</h2>
            <table class="accesses table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Usuário</th>
                        <th>Ponto de acesso</th>
                        <th>Data</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${accessGranted}">
                        <tr>
                            <td>${it.id}</td>
                            <td>${it.user.credential.code}</td>
                            <td>${it.accessPoint.name}</td>
                            <td><g:formatDate date="${it.dateCreated}" /></td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </div>
        <div class="col-xs-5 text-center">
            <h2>Acessos negados</h2>
            <table class="accesses table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Usuário</th>
                        <th>Ponto de acesso</th>
                        <th>Data</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${accessDenied}">
                        <tr>
                            <td>${it.id}</td>
                            <td>${it.user.credential.code}</td>
                            <td>${it.accessPoint.name}</td>
                            <td><g:formatDate date="${it.dateCreated}" /></td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>