//= require jquery.js
//= require angular.js
//= require bootstrap.min.js
//= require webcam.js
//= require_self

angular.module("CameraAccess", [])
.controller("SnapshotController", ["$scope", SnapshotController])
.directive("camera", [CameraDirective]);

Webcam.set({
    width: 640, height: 480,
    image_format: "jpeg",
    jpeg_quality: 100
});

function SnapshotController($scope) {
    $scope.takeSnapshot = function(accessPointId) {
        Webcam.snap(function(dataUri) {
            $.post("/access/snapshot", { id: accessPointId, dataUri: dataUri }, function() { });
        });
    }
}

function CameraDirective() {
    function link(scope, element, attrs) {
        if (attrs.camera == "on") {
            Webcam.attach(element[0]);
        }
    }

    return { restrict: "A", link: link };
}