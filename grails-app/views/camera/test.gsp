<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <h3>Test</h3>
    <div class="row">
        <div class="col-xs-6">
            <g:form controller="camera" action="test" class="camera-test form-horizontal">
                <h4 class="modal-title" id="editlLabel">Usuário teste</h4>
                <div class="form-group">
                    <label for="inputId" class="col-xs-2 control-label">ID:</label>
                    <div class="col-xs-4">
                        <input type="text" class="form-control" name="user.id" id="inputId" placeholder="ID do usuário" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="photoName" class="col-xs-2 control-label">Photo:</label>
                    <div class="col-xs-4">
                        <input type="text" class="form-control" name="photo.name" id="photoName" value="1433852189531.png" placeholder="Foto para reconhecimento" />
                    </div>
                </div>
                <button type="button" name="shoot" class="btn btn-default" data-action="${createLink(controller: 'camera', action: 'shoot')}">Tirar foto</button>
                <button type="submit" name="add" class="btn btn-primary">Testar</button>
            </g:form>
        </div>
    </div>
    <asset:javascript src="camera.js"/>
</body>
</html>