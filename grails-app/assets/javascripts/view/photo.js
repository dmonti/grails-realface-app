//= require_self

$(function() {
    var self = this;

    var submitEvent = function(e) {
        var $form = $(this);
        $.post($form.attr("action"), $form.serialize(), function(photo) {
            toastr.success("Photo #" + photo.id + " tirada com sucesso!");
            $form.find("img.photo").attr("src", "/photo/resource/" + photo.id);
        });
        return false;
    };

    self.initialize = function() {
        $("form").submit(submitEvent);
    };

    self.initialize();
});