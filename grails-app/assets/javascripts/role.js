$(function() {
    new RoleForm($("form"));
});

function RoleForm($form) {
    var self = this;

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
            modal.find("#inputEmail").autocomplete({
                source: "/user/search",
                minLength: 2,
                select: function(event, ui) {
                }
            });
            modal.show();
        });
    };

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