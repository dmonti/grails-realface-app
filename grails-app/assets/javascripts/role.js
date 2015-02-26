$(function() {
    new RoleForm($("form"));
});

function RoleForm($form) {
    var self = this;

    $form.on("click", "a.user-remove", function(e) {
        $(this).parents("tr:first").remove();
    })

    $form.find("a.add-user").click(function(e) {
        try {
            var url = $(this).attr("href");
            loadAddUserModal(url);
        } catch (e) {
            toastr.error(response.message);
        } finally {
            return false;
        }
    });

    var loadAddUserModal = function(url) {
        $.get(url, null, function(html) {
            var modal = new Modal(html, { removeAfterHide: true });
            modal.get().find("#inputEmail").autocomplete({
                source: "/user/search",
                minLength: 2,
                select: function(event, ui) {
                    modal.data(ui.item);
                }
            });
            modal.on("remove", function(e) {
                addUser(modal.data());
            });
            modal.show();
        });
    };

    var addUser = function(user) {
        var $tbody = $("table.users > tbody");
        var $tr = $tbody.find("tr#user-" + user.id);
        if ($tr.length > 0) {
            $tbody.prepend($tr);
        } else {
            $.get("/template/load", { path: "/role/users_row", bean: "User", id: user.id }, function(html) {
                console.log(html);
                $tbody.prepend(html);
            });
        }
    }

    var submit = function(e) {
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
            if (result.status)
                toastr.success(result.message)
            else
                toastr.error(result.message)
        }).always(function() {
            $btn.button("reset");
        });
    };

    $form.submit(submit);
    return self;
};