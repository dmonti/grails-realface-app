//= require_self

function Camera() {
    var self = this;

    var submitEvent = function(e) {
        var $form = $(this);
        var id1 = $("#photo1").data("id");
        var id2 = $("#photo2").data("id");
        $.post($form.attr("action"), { id1: id1, id2: id2 }, function() { });
        return false;
    };

    var shootEvent = function(e) {
        var $btn = $(this);
        var action = $btn.data("action")
        $.get(action, function(data) {
            var target = $btn.data("target");
            $(target).data("id", data.id).attr("src", "/photo/index/" + data.id);
            toastr.success("Foto capturada!");
        });
    };

    self.initialize = function() {
        $("form.camera-test").submit(submitEvent);
        $("button[name=shoot]").click(shootEvent);
    };

    self.initialize();
};

$(function() { new Camera(); });