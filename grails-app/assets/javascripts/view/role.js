$(function() {
    new RoleForm($("form"));
});

function RoleForm($form) {
    var self = this;

    $form.on("click", "a.user-remove", function(e) {
        $(this).parents("tr:first").remove();
    })

    $form.find("a.add-user").click(addUser);


    function addUser(event) {
        var $target = $(this);
        var url = $target.attr("href");
        var codeOrId = $("input.add-user").val();
        $.get(url, { codeOrId: codeOrId }, function(result) {
            console.log(result);
            if (result.status) {
                $("table.users > tbody").prepend(result.user);
            } else {
                toastr.error("Usuário não encontrado!");
            }
        });
        return false;
    }

    var submit = function() {
        try {
            update($form);
        } catch (e) {
            toastr.error(response.message);
        } finally {
            return false;
        }
    };

    var update = function(url, data) {
        var $btn = $form.find("button[type=submit]").button("loading");
        $.post($form.attr("action"), $form.serialize(), function(result) {
            if (result.status) {
                toastr.success(result.message)
            } else {
                toastr.error(result.message)
            }
        }).always(function() {
            $btn.button("reset");
        });
    };

    $form.submit(submit);
    return self;
};