<html>
<head>
    <meta name="layout" content="admin/main">
</head>
<body>
    <h3>Foto</h3>
    <g:form controller="photo" action="capture" class="camera-test form-horizontal">
        <div class="row text-center">
            <div class="col-xs-12">
                User ID: <input type="text" name="user.id" />
            </div>
        </div>
        <div class="row text-center">
            <div class="col-xs-offset-3 col-xs-6">
                <asset:image class="photo" src="icon-unknown.png" width="480" style="width: 480px;" />
            </div>
        </div>
        <div class="row text-center">
            <div class="col-xs-12">
                <button type="submit" name="shoot" class="btn btn-primary">Tirar foto</button>
            </div>
        </div>
    </g:form>
    <asset:javascript src="view/photo.js"/>
</body>
</html>