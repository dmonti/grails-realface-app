//= require application
//= require_self

$(function() {
    var $form = $("form.person");
    new PersonForm($form);
});

function PersonForm($form) {
    var self = this;
    var $btnSubmit = $form.find("button[type=submit]");

    var submit = function(e) {
        $btnSubmit.attr("disabled",  "disabled");
        try {
            var url = $form.attr("action");
            var data = $form.serialize();
            $.post(url, data, function(result) {
                if (result.status)
                    toastr.success(result.message)
                else
                    toastr.error(result.message)
            }).always(function() {
                $btnSubmit.removeAttr("disabled");
            });
        } catch (e) {
            toastr.error(response.message);
        } finally {
            return false;
        }
    };

    $form.submit(submit);
    return self;
};