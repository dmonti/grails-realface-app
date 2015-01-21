//= require jquery-2.1.3.min
//= require bootstrap.min
//= require toastr.min
//= require menu
//= require_self

toastr.options = {
    "closeButton": false,
    "progressBar": false,
    "positionClass": "toast-top-center",
    "showDuration": "250",
    "hideDuration": "250",
    "timeOut": "2500",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
};

String.prototype.startsWith = function (str) {
    return this.indexOf(str) == 0;
};
String.prototype.endsWith = function (str) {
    return this.lastIndexOf(str) == (this.length - 1);
};
String.prototype.replaceAll = function (str1, str2) {
    var self = this;
    while (self.indexOf(str1) != -1) { self = self.replace(str1, str2); }
    return self;
};
String.prototype.trim = function () {
    return $.trim(this);
};
String.prototype.isEmpty = function () {
    return this.length <= 0;
};