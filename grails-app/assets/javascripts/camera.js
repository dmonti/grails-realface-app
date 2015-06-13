//= require_self

function Camera() {
    var self = this;

    var submitEvent = function(e) {
        var $form = $(this);
        $.post($form.attr("action"), $form.serialize(), function(data) {
            console.log(data);
            if (data.test)
                toastr.success(data.message);
            else
                toastr.error(data.message);
        });
        return false;
    };

    var shootEvent = function(e) {
        var $btn = $(this);
        var action = $btn.data("action");
        $.get(action, function(data) {
            toastr.success("Foto capturada!");
            $("#photoName").val(data.name);
        });
    };

    self.initialize = function() {
        $("form.camera-test").submit(submitEvent);
        $("button[name=shoot]").click(shootEvent);
    };

    self.initialize();
};

$(function() { new Camera(); });