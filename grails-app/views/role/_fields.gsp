<div class="form-group">
    <label for="inputName" class="col-xs-2 control-label">Nome:</label>
    <div class="col-xs-4">
        <input type="text" class="form-control" name="name" id="inputName" value="${role?.name}" placeholder="Nome" />
    </div>
</div>
<div class="form-group">
    <label class="col-xs-2 control-label">Usuários:</label>
    <div class="col-xs-4">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group">
                    <div class="col-xs-10">
                        <input type="text" class="form-control add-user" name="user" placeholder="ID ou código do usuário" />
                    </div>
                    <div class="col-xs-2">
                        <g:link controller="user" action="add" href="javascript:void(0);" class="add-user">
                            <span class="glyphicon glyphicon-plus-sign" style="margin-top: 8px;" />
                        </g:link>
                    </div>
                </div>
                <table class="users table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Nome</th>
                            <th>Email</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${role?.getUsers()}">
                            <g:render template="users_row" bean="${it}" />
                        </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>