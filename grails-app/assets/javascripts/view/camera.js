//= require_self

function Camera() {
    var self = this;

    var submitEvent = function(e) {
        var $form = $(this);
        $.post($form.attr("action"), $form.serialize(), function() {
            toastr.success("Camera ativada!");
        });
        return false;
    };

    var captureEvent = function(e) {
        var $btn = $(this);
        var action = $btn.data("action")
        $.get(action, function(data) {
            var id = data.id;
            var target = $btn.data("target");
            $(target).data("id", id).attr("src", "/photo/resource/" + id).siblings("input[type=text]").val(id);
            toastr.success("Foto capturada!");
        });
    };

    var photoChangeEvent = function(e) {
        var $input = $(e.target);
        var id = $input.val();
        if ($.isNumeric(id))
        {
            var targetSelector = $input.data("target");
            $(targetSelector).data("id", id).attr("src", "/photo/resource/" + id + "?" + new Date().getTime());
        }
    };

    self.initialize = function() {
        $("form").submit(submitEvent);
        $("button[name=capture]").click(captureEvent);
        $("input[name=photo]").change(photoChangeEvent);
    };

    self.initialize();
};

$(function() { new Camera(); });