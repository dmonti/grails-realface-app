$(function() {
    $("a.edit").click(function() {
        try {
            var href = $(this).attr("href");
            new RoleModal(href).show();
        } catch(e) {
            toastr.error(response.message);
        } finally {
            return false;
        }
    });
});

function RoleModal(href) {
    var self = this;
    var $content = $("#content");

    var initialize = function() {
        $.get(href, null, initModal);
    };

    var initModal = function(html) {
        $modal = $(html);
        $modal.on('hidden.bs.modal', function() { $modal.remove() });
        $modal.find("form").submit(submit);
        $content.append($modal);
        $modal.modal("show");
    }

    var submit = function(e) {
        try {
            update($(this));
        } catch(e) {
            toastr.error(response.message);
        } finally {
            return false;
        }
    }

    var update = function($form) {
        var $btn = $form.find("button[type=submit]").button("loading");
        $.post($form.attr("action"), $form.serialize(), function(result) {
            if (result.status) {
                $form.parents(".modal:first").modal("hide");
                toastr.success(result.message)
            } else {
                toastr.error(result.message)
            }
        }).always(function() {
            $btn.button("reset");
        });
    };

    self.show = function() {
    }

    initialize();
    return self;
};