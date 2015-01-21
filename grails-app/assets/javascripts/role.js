//= require application
//= require_self

$(function() {
    new RoleForm($("form.role"));
});

function RoleForm($form) {
    var self = this;
    var $btn = $form.find("button[type=submit]");

    var submit = function(e) {
        try {
            update($form.attr("action"), $form.serialize());
        } catch (e) {
            toastr.error(response.message);
        } finally {
            return false;
        }
    };

    var update = function(url, data) {
        $btn.button("loading");
        $.post(url, data, function(result) {
            if (result.status)
                toastr.success(result.message)
            else
                toastr.error(result.message)
        }).always(function() {
            $btn.button("reset");
        });
    };

    $form.submit(submit);
    return self;
};