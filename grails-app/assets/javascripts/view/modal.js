function Modal(html, config) {
    var self = this;

    var data;
    var $modal = $(html);

    var initialize = function() {
        if (config && config.appendTo) {
            $modal.appendTo(appendTo);
        } else {
            $("body").append($modal);
        }
        $modal.on("hidden.bs.modal", function(e, o) {
            $modal.trigger("hide", self);
            if (config && config.removeAfterHide) {
                $modal.remove();
            }
        });
    };

    self.get = function() {
        return $modal;
    }

    self.data = function(value) {
        if (typeof value != "undefined") {
            data = value;
        }
        return data;
    }

    self.on = function(eventName, callback) {
        $modal.on(eventName, callback);
    }

    self.show = function() {
        $modal.modal("show");
    };

    initialize();
    return self;
}