<div class="modal fade" id="editModal" role="dialog" aria-labelledby="editlLabel" aria-hidden="true">
    <div class="modal-dialog">
        <g:form controller="role" action="submit" id="${role?.id}" class="role modal-content form-horizontal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span>&times;</span></button>
                <h4 class="modal-title" id="editlLabel">Adicionar usuário</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="inputEmail" class="col-xs-2 control-label">Email:</label>
                    <div class="col-xs-4">
                        <input type="text" class="form-control" name="value" id="inputEmail" placeholder="Email ou código..." />
                        <input type="hidden" id="person" />
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" name="cancel" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button type="button" name="add" class="btn btn-primary" data-dismiss="modal">Adicionar</button>
            </div>
        </g:form>
    </div>
</div>