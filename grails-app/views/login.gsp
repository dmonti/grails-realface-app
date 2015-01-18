<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>RealFace - Login</title>
    <asset:stylesheet src="login.css"/>
</head>
<body>
    <div class="container">
        <div class="form-container">
            <g:form controller="access" action="login" class="form-login form-horizontal">
              <div class="form-group">
                <label for="inputLoginEmail" class="col-sm-2 control-label">Email:</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" name="username" id="inputLoginEmail" placeholder="Email">
                </div>
              </div>
              <div class="form-group">
                <label for="inputLoginPassword" class="col-sm-2 control-label">Senha:</label>
                <div class="col-sm-10">
                  <input type="password" class="form-control" name="password" id="inputLoginPassword" placeholder="Senha">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                  <button type="submit" data-loading-text="Carregando..." class="btn btn-default">Logar</button>
                </div>
              </div>
            </g:form>
        </div>
    </div>
    <asset:javascript src="login.js"/>
</body>
</html>