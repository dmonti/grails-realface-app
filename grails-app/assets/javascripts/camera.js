//= require_self

function Camera() {
    var self = this;

    var submitEvent = function(e) {
        var $form = $(this);
        var action = $form.data("action");
        $.post(action, $form.serialize(), function(data) {
        });
        return false;
    };

    var shootEvent = function(e) {
        var $btn = $(this);
        var action = $btn.data("action");
        $.get(action, function(data) {
            $("#photoName").val(data.name);
        })
    };

    self.initialize = function() {
        $("form.camera-test").submit(submitEvent);
        $("button[name=shoot]").click(shootEvent);
    };

    self.initialize();
};

$(function() { new Camera(); });