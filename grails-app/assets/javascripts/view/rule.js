//= require_self

function RuleForm($form) {
    var self = this;
    var $btn = $form.find("button[type=submit]");

    $form.find("a.add-user").click(function() {
        var $target = $(this);
        var url = $target.attr("href");
        var codeOrId = $("input.add-user").val();
        $.get(url, { codeOrId: codeOrId }, function(result) {
            if (result.status) {
                $("table.users > tbody").prepend(result.user);
                $("input.add-user").val("");
            } else {
                toastr.error("Usuário não encontrado!");
            }
        });
        return false;
    });

    $form.on("click", "a.user-remove", function(e) {
        $(this).parents("tr:first").remove();
    })

    $form.find("a.add-role").click(function() {
        var $target = $(this);
        var url = $target.attr("href");
        var nameOrId = $("input.add-role").val();
        $.get(url, { nameOrId: nameOrId }, function(result) {
            if (result.status) {
                $("table.roles > tbody").prepend(result.role);
                $("input.add-role").val("");
            } else {
                toastr.error("Papel não encontrado!");
            }
        });
        return false;
    });

    $form.on("click", "a.role-remove", function(e) {
        $(this).parents("tr:first").remove();
    })

    var submit = function() {
        try {
            update($form);
        } catch (e) {
            toastr.error(response.message);
        } finally {
            return false;
        }
    };

    var update = function($form) {
        $btn.button("loading");
        $.post($form.attr("action"), $form.serialize(), function(result) {
            if (result.status) {
                toastr.success(result.message);
                if (result.returnPage) {
                    window.location = result.returnPage;
                }
            }
            else
                toastr.error(result.message);
        }).always(function() {
            $btn.button("reset");
        });
    };

    $form.submit(submit);

    return self;
};

$(function() { new RuleForm($("form")); });