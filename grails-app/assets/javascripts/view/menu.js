//= require_self

function Menu() {
    var self = this;
    var locationControllerName;

    self.initialize = function() {
        var locationPathName = window.location.pathname;
        locationControllerName = getControllerName(locationPathName);
        locationActionName = getActionName(locationPathName);

        var currentItem;

        $("#menu").find("li > a").each(function(i, element) {
            var href = $(element).attr("href");
            var linkControllerName = getControllerName(href);
            var linkActionName = getActionName(href);

            if (locationControllerName == linkControllerName) {
                currentItem = $(element).parents("li:last");
                if (locationActionName == linkActionName) {
                    return false;
                }
            }
        });

        currentItem.addClass("active");
    };

    var getControllerName = function(pathName) {
        return (pathName.indexOf("/") != -1) ? pathName.split("/")[1] : null;
    }

    var getActionName = function(pathName) {
        var paths = pathName.split("/")
        return (pathName.indexOf("/") != -1 && paths.length > 1) ? pathName.split("/")[2] : null;
    }

    self.initialize();
};

$(function() { new Menu(); });