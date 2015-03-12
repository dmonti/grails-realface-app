//= require_self

$(function() {
    var $form = $("form.point");
    new AccessPointForm($form);
});

function AccessPointForm($form) {
    var self = this;

    $form.find("a.add-rule").click(function(e) {
        try {
            var url = $(this).attr("href");
            loadAddRuleModal(url);
        } catch (e) {
            toastr.error(response.message);
        } finally {
            return false;
        }
    });

    var loadAddRuleModal = function(url) {
        $.get(url, null, function(html) {
            var modal = new Modal(html, { removeAfterHide: true });
            modal.on("remove", function(e) {
                //addRule(modal.data());
                console.log("REMOVED!")
            });
            modal.show();
        });
    };

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