$(function() {
    new RuleForm($("form"));
});

function RuleForm($form) {
    var self = this;

    $form.find("a.add-role").click(function() {
        console.log(this);
        return false;
    });

    $form.find("a.add-user").click(function() {
        console.log(this);
        return false;
    });

    return self;
};