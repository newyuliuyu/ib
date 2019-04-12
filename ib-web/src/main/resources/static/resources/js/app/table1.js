(function () {
    "use strict";
    var models = ['jquery',
        'vue',
        'pin',
        'bootstrap',
        'css!style/pin',
        'css!style/bootstrap/bootstrap.min',
        'css!style/public',
        'TableFreeze'
    ];
    define(models, function ($, Vue) {

        function init() {
            $('#tab_Test0').FrozenTable(1, 0, 1);
            $('#genetable').FrozenTable(1, 0, 1);
        }

        return {
            render: function () {
                init();
            }
        }
    });
})();