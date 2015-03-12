//= require_self

$(function() {
    var $form = $("form.user");
    new UserForm($form);
});

function UserForm($form) {
    var self = this;

    var submit = function() {
        try {
            $post($form);
        } catch (e) {
            toastr.error(response.message);
        } finally {
            return false;
        }
    };

    $form.submit(submit);
    return self;
};