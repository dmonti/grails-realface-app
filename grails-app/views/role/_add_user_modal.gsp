<div class="modal fade" id="editModal" role="dialog" aria-labelledby="editlLabel" aria-hidden="true" tabindex="-1">
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
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                <button type="submit" data-loading-text="Salvando..." class="btn btn-primary">Adicionar</button>
            </div>
        </g:form>
    </div>
</div>