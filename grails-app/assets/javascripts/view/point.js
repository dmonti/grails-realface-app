//= require_self

$(function() {
    var $form = $("form.point");
    new AccessPointForm($form);
});

function AccessPointForm($form) {
    var self = this;

    $form.find("a.add-rule").click(function() {
        var $target = $(this);
        var url = $target.attr("href");
        var codeOrId = $("input.add-rule").val();
        $.get(url, { codeOrId: codeOrId }, function(result) {
            if (result.status) {
                $("table.rules > tbody").prepend(result.rule);
                $("input.add-rule").val("");
            } else {
                toastr.error("Regra de acesso não encontrado!");
            }
        });
        return false;
    });

    $form.on("click", "a.rule-remove", function(e) {
        $(this).parents("tr:first").remove();
    })

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