function Modal(html, data) {
    var self = this;
    var $modal = $(html);

    var initialize = function() {
        if (data && data.appendTo) {
            $modal.appendTo(appendTo);
        } else {
            $("body").append($modal);
        }
        if (data && data.removeAfterHide) {
            $modal.on('hidden.bs.modal', function() { self.remove() });
        }
    };

    self.get = function() {
        return $modal;
    }

    self.find = function(selector) {
        return $modal.find(selector);
    }

    self.remove = function() {
        $modal.remove();
    };

    self.show = function() {
        $modal.modal("show");
    };

    initialize();
    return self;
}