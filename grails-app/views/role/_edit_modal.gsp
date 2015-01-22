<div class="modal fade" id="editModal" role="dialog" aria-labelledby="editlLabel" aria-hidden="true">
    <div class="modal-dialog">
        <g:form controller="role" action="submit" id="${role?.id}" class="role modal-content form-horizontal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span>&times;</span></button>
                <h4 class="modal-title" id="editlLabel"></h4>
            </div>
            <div class="modal-body">
                <g:render template="fields" />
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                <button type="submit" data-loading-text="Salvando..." class="btn btn-primary">Salvar</button>
            </div>
        </g:form>
    </div>
</div>