<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <h3>Ativar camera</h3>
    <g:form controller="camera" action="start" class="camera-test form-horizontal">
        <div class="row text-center">
            <div class="form-group">
                <label for="inputDescription" class="col-sm-2 control-label">Dispositivos:</label>
                <div class="col-sm-5">
                    <select class="form-control" name="index">
                        <g:each status="i" var="device" in="${devices}">
                            <option value="${i}">${device}</option>
                        </g:each>
                    </select>
                </div>
            </div>
            <div class="col-xs-12"><br />
                <input type="submit" name="on" value="Ativar" />
                <input type="button" name="off" value="Desativar" action="${createLink(controller: 'camera', action: 'stop')}" />
            </div>
        </div>
    </g:form>
    <asset:javascript src="view/camera.js"/>
</body>
</html>