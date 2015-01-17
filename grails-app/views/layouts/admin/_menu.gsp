<ul class="nav nav-pills nav-stacked">
    <li><g:link controller="Home">Dashboard</g:link></li>
    <li><g:link controller="User">Usuários</g:link></li>
    <li><g:link controller="Person">Pessoas</g:link></li>
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Logs <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><g:link controller="UserAccessLog">Acesso do sistema</g:link></li>
            <li><g:link controller="AccessPointLog">Pontos de autenticação</g:link></li>
        </ul>
    </li>
</ul>