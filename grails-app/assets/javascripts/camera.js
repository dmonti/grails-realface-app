//= require jquery-2.1.3.min.js
//= require angular.js
//= require bootstrap.min.js
//= require webcam.js
//= require_self

angular.module("CameraAccess", [])
.controller("SnapshotController", ["$scope", SnapshotController]);

Webcam.set({
    width: 320,
    height: 240,
    image_format: "jpeg",
    jpeg_quality: 90
});

function SnapshotController($scope) {
    $scope.takeSnapshot = function() {
        Webcam.snap(snapshot);
    }

    Webcam.attach("#camera");
}

function snapshot(dataUri) {
    console.log(dataUri);
}