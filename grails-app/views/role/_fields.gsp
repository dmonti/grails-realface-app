<div class="form-group">
    <label for="inputName" class="col-xs-2 control-label">Nome:</label>
    <div class="col-xs-4">
        <input type="text" class="form-control" name="name" id="inputName" value="${role?.name}" placeholder="Nome" />
    </div>
</div>
<div class="form-group">
    <label class="col-xs-2 control-label">Usuários:</label>
    <div class="col-xs-4">
        <table class="persons table table-striped table-bordered">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>&nbsp;
                        <g:link controller="Template" action="load" params="${[path: '/role/add_person_modal']}" class="add-person">
                            <span class="glyphicon glyphicon-plus-sign pull-right" />
                        </g:link>
                    </th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${role.persons}">
                    <g:render template="persons_row" bean="${it}" />
                </g:each>
            </tbody>
        </table>
    </div>
</div>