//= require_self

function Menu() {
    var self = this;
    var locationControllerName;

    self.initialize = function() {
        var locationPathName = window.location.pathname;
        locationControllerName = getControllerName(locationPathName);

        $("#menu").find("li > a").each(function(i, element) {
            var href = $(element).attr("href");
            if (matchLocationPathName(href)) {
                $(element).parents("li:last").addClass("active");
                return;
            }
        });
    };

    var matchLocationPathName = function(pathName) {
        var linkControllerName = getControllerName(pathName);
        return (linkControllerName == locationControllerName);
    }

    var getControllerName = function(pathName) {
        return (pathName.indexOf("/") != -1) ? pathName.split("/")[1] : null;
    }

    self.initialize();
};

$(function() { new Menu(); });