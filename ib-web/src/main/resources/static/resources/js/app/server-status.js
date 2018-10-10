(function () {
    "use strict";
    var models = ['jquery', 'ajax',
        'bootstrap',
        'ezconfig',
        'css!style/bootstrap/bootstrap.min',
        'css!style/public',
        'css!style/font-awesome'
    ];
    define(models, function ($, $ajax) {

        function checkServer(url, idx) {
            $ajax.getJson(url + "/ping").then(function (value) {
                $('#server-status-tbody tr:eq(' + idx + ')').find('td:eq(1)').html('<span class="text-success">服务器正常</span>')
            }).always(function () {
                if (arguments[1] === 'error') {
                    $('#server-status-tbody tr:eq(' + idx + ')').find('td:eq(1)').html('<span class="text-danger">服务器不通</span>')
                }
            });
        }

        function init() {
            var $tbody = $('#server-status-tbody');
            var url = window.app.config.sendcjURL;
            var tr = '<tr><td>' + url + '</td><td>正在检查...</td></tr>';
            $tbody.append(tr);
            checkServer(url, 0);
            url = window.app.config.wxProxyURL;
            tr = '<tr><td>' + url + '</td><td>正在检查...</td></tr>';
            $tbody.append(tr);
            checkServer(url, 1);
        }

        return {
            render: function () {
                $('body').show();
                init();
            }
        }
    });
})();