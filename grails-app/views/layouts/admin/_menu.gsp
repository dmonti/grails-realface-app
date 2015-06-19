<ul class="nav nav-pills nav-stacked">
    <li><g:link controller="Home">Dashboard</g:link></li>
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Sistema <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><g:link controller="Role">Papéis</g:link></li>
            <li><g:link controller="User">Usuários</g:link></li>
            <li><g:link controller="AccessRule">Regras de acesso</g:link></li>
            <li><g:link controller="AccessPoint">Pontos de acesso</g:link></li>
        </ul>
    </li>
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Cameras <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><g:link controller="Photo">Fotos</g:link></li>
            <li><g:link controller="Identification">Logs de identificação</g:link></li>
            <li><g:link controller="Camera" action="test">Teste de reconhecimento</g:link></li>
        </ul>
    </li>
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Logs <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><g:link controller="UserAccessLog">Acesso do sistema</g:link></li>
        </ul>
    </li>
</ul>