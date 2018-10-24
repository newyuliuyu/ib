(function () {
    "use strict";
    var models = ['jquery',
        'ajax',
        'webuploader',
        'dot',
        'dialog',
        'css!style/dialog',
        'js/commons/JQuery.progress',
        'pin',
        'css!style/pin',
        'bootstrap',
        'css!style/bootstrap/bootstrap.min',
        'css!style/font-awesome',
        'css!style/public',
        'css!style/item',
        'loading',
        'icheck'
    ];
    define(models, function ($, ajax, WebUploader, dot, dialog) {

        return {
            render: function () {
                $('body').show();

            }
        }
    });
})();