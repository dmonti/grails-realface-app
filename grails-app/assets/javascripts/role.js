$(function() {
    new RoleForm($("form"));
});

function RoleForm($form) {
    var self = this;

    $form.find("a.add-person").click(function(e) {
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
                source: "/person/search",
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

    var addUser = function(person) {
        var $tbody = $("table.persons > tbody");
        var $tr = $tbody.find("tr#person-" + person.id);
        if ($tr.length > 0) {
            $tbody.prepend($tr);
        } else {
            $.get("/template/load", { path: "/role/persons_row", bean: "User", id: person.id }, function(html) {
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