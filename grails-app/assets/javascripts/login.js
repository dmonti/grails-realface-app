//= require jquery-2.1.3.min
//= require bootstrap.min
//= require toastr.min
//= require_self

window.homePage = "/";

$(function() {
    $("form.form-login").submit(function() {
        var login = new Login($(this));
        try {
            login.submit();
        } catch (e) {
            login.error(e.message);
        } finally {
            return false;
        }
    });
});

toastr.options = {
    "closeButton": false,
    "progressBar": false,
    "positionClass": "toast-top-center",
    "showDuration": "250",
    "hideDuration": "250",
    "timeOut": "2500",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
};

function Login($form) {
    var self = this;
    var $btn = $form.find("button[type=submit]");

    this.submit = function() {
        $btn.button("loading");
        $.get($form.attr("action"), $form.serialize(), function(response) {
            if (response.status) {
                self.login();
            } else {
                toastr.error(response.message);
                $btn.button("reset");
            }
        });
    };

    this.login = function() {
        window.location.href = window.homePage;
    };

    this.error = function(message) {
        toastr.error(message);
    };
};