<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <title><g:layoutTitle default="RealFace - Administração"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/bootstrap/img/favicon.ico">
    <g:layoutHead/>
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>
</head>
<body>
<g:render template="/layouts/admin/header" />
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-2">
            <g:render template="/layouts/admin/menu" />
        </div>
        <div class="col-xs-10">
            <g:layoutBody />
        </div>
    </div>
</div>
</body>
</html>