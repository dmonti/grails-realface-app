<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <h3>Test</h3>
    <div class="row">
        <div class="col-xs-4">
            <g:form controller="camera" action="recognize" class="camera-test form-horizontal">
                <h4 class="modal-title" id="editlLabel">Usuário teste</h4>
                <button type="button" name="shoot" data-target="#photo1" class="btn btn-default" data-action="${createLink(controller: 'camera', action: 'shoot')}">Tirar foto 1</button>
                <button type="button" name="shoot" data-target="#photo2" class="btn btn-default" data-action="${createLink(controller: 'camera', action: 'shoot')}">Tirar foto 2</button>
                <button type="submit" name="add" class="btn btn-primary">Comparar fotos</button>
            </g:form>
        </div>
        <div class="col-xs-4">
            <img class="col-xs-12" id="photo1" data-id="3" src="${createLink(controller: 'photo', action: 'index', id: 3)}" />
        </div>
        <div class="col-xs-4">
            <img class="col-xs-12" id="photo2" data-id="4" src="${createLink(controller: 'photo', action: 'index', id: 4)}" />
        </div>
    </div>
    <asset:javascript src="camera.js"/>
</body>
</html>