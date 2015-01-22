$(function() {
    new RoleForm($("form"));
});

function RoleForm($form) {
    var self = this;

    $form.find(".add-user").click(function() {
        $.get($(this).attr("href"), null, function(htmlModal) {
            var $modal = showHtmlModal(htmlModal);
            $modal.find("#inputEmail").autocomplete({
                source: "/user/search",
                minLength: 2,
                select: function(event, ui) {
                    console.log(ui.item ? "Selected: " + ui.item.value + " aka " + ui.item.id : "Nothing selected, input was " + this.value );
                }
            });
        })
        return false;
    });

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