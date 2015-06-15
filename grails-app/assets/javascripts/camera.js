//= require_self

var test = false;

function Camera() {
    var self = this;

    var submitEvent = function(e) {
        var $form = $(this);
        $.post($form.attr("action"), $form.serialize(), function() {
            test = null;
        });
        return false;
    };

    var shootEvent = function(e) {
        var $btn = $(this);
        var id = $("input[name=user\\.id]").val()
        var action = $btn.data("action") + "?user=" + id;
        $.get(action, function(data) {
            toastr.success("Foto capturada!");
            $("#photoName").val(data.name);
            $("#photo1").attr("src", "/camera/photo/" + id + "?" + new Date().getTime());
            $("#photo2").attr("src", "/camera/photo/test?" + new Date().getTime());
        });
    };

    self.initialize = function() {
        $("form.camera-test").submit(submitEvent);
        $("button[name=shoot]").click(shootEvent);
    };

    self.initialize();
};

function updateTest() {
    setTimeout(function() {
        $.get("/camera/check", function(data) {
            if (data.test != null && test == null)
            {
                test = data.test;
                //if (test)
                    //toastr.success("Usuário validado!");
                //else
                    //toastr.error("Usuário não validado!");
            }
        });
        updateTest();
    }, 1000);
}

$(function() {
    new Camera();
    updateTest();
});
