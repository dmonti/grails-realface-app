//= require_self

function RuleForm($form) {
    var self = this;
    var $btn = $form.find("button[type=submit]");

    $form.find("a.add-role").click(function() {
        console.log(this);
        return false;
    });

    $form.find("a.add-user").click(function() {
        console.log(this);
        return false;
    });

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