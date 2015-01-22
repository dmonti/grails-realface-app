<div class="form-group">
    <label for="inputName" class="col-xs-2 control-label">Nome:</label>
    <div class="col-xs-4">
        <input type="text" class="form-control" name="name" id="inputName" value="${role?.name}" placeholder="Nome" />
    </div>
</div>
<div class="form-group">
    <label class="col-xs-2 control-label">Usuários:</label>
    <div class="col-xs-4">
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Código
                        <g:link controller="Role" action="addUserModal" class="add-user">
                            <span class="glyphicon glyphicon-plus-sign pull-right" />
                        </g:link>
                    </th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>