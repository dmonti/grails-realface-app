function Menu() {
    var self = this;

    self.initialize = function() {
        var urlPathName = window.location.pathname;
        $("#menu").find("li > a").each(function(i, element) {
            var href = $(element).attr("href");
            if (urlPathName == href) {
                $(element).parents("li").addClass("active");
                return;
            }
        });
    };

    self.initialize();
};

$(function() { new Menu(); });