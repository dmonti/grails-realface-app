<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <h3>Testar reconhecimento</h3>
    <g:form controller="camera" action="recognize" class="camera-test form-horizontal">
        <div class="row text-center">
            <div class="col-xs-5">
                <button type="button" name="shoot" data-target="#photo1" class="btn btn-default" data-action="${createLink(controller: 'camera', action: 'shoot')}">Capturar</button>
                <input type="text" name="photo" data-target="#photo1" value="1" />
                <img style="width: 480px;" id="photo1" data-id="1" src="/photo/resource/1" />
            </div>
            <div class="col-xs-5">
                <button type="button" name="shoot" data-target="#photo2" class="btn btn-default" data-action="${createLink(controller: 'camera', action: 'shoot')}">Capturar</button>
                <input type="text" name="photo" data-target="#photo2" value="2" />
                <img style="width: 480px;" id="photo2" data-id="2" src="/photo/resource/2" />
            </div>
        </div>
        <div class="row text-center">
            <div class="col-xs-10">
                <button type="submit" name="add" class="btn btn-primary">Comparar fotos</button>
            </div>
        </div>
    </g:form>
    <asset:javascript src="camera.js"/>
</body>
</html>