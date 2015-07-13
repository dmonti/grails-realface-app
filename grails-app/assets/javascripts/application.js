//= require jquery-ui.min.js
//= require bootstrap.min.js
//= require toastr.min.js
//= require view/menu.js
//= require view/modal.js
//= require_self

function $post($form, data, $btn) {
    if ($btn == null) {
        $btn = $form.find("button[type=submit]")
    }
    $btn.button("loading");
    if (data == null) {
        data = $form.serialize();
    }
    $.post($form.attr("action"), data, function(result) {
        if (result.status) {
            toastr.success(result.message);
        } else {
            toastr.error(result.message);
        }
    }).always(function() {
        $btn.button("reset");
    });
}

toastr.options = {
    "closeButton": false,
    "progressBar": false,
    "positionClass": "toast-top-center",
    "showDuration": "500",
    "hideDuration": "250",
    "timeOut": "2500",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
};

String.prototype.startsWith = function (str) {
    return this.indexOf(str) == 0;
};
String.prototype.endsWith = function (str) {
    return this.lastIndexOf(str) == (this.length - 1);
};
String.prototype.replaceAll = function (str1, str2) {
    var self = this;
    while (self.indexOf(str1) != -1) { self = self.replace(str1, str2); }
    return self;
};
String.prototype.trim = function () {
    return $.trim(this);
};
String.prototype.isEmpty = function () {
    return this.length <= 0;
};