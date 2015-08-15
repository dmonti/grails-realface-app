<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Acesso por Camera</title>
        <asset:stylesheet src="camera.css" />
    </head>
    <body ng-app="CameraAccess">
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">RealFace</a>
                </div>
            </div>
        </nav>
        <div class="container" ng-controller="SnapshotController">
            <div class="starter-template">
                <h1>Camera de accesso: ${accessPoint?.name}</h1>
                <p class="lead">
                    <div class="camera" id="camera"></div>
                    <button type="submit" class="btn btn-default" ng-click="takeSnapshot()">Identificar</button>
                </p>
            </div>
        </div>
        <asset:javascript src="camera.js" />
    </body>
</html>