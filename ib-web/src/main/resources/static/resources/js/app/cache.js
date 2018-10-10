(function () {
    "use strict";
    var deps = ['jquery', 'ajax', 'dialog','ezconfig',
        'css!style/public', 'css!style/dialog'];
    define(deps, function ($, $ajax, dialog) {
        var sendcjURL = window.app.config.sendcjURL;

        function removeCache() {
            $('#myTable .content').on('click', '.update-cache', function () {
                var $tr = $(this).parents('tr');
                var key = $tr.find('td:eq(0)').text();
                var url = sendcjURL + "/cache/remove/" + key;
                $ajax.corsPostJson(url, {}).then(function () {
                    dialog.fadedialog({
                        tipText: "删除成功!"
                    });
                    $tr.remove();
                });
            });
        }

        function getCaches() {
            var url = sendcjURL + "/cache/info";
            $ajax.corsGetJson(url).then(function (data) {
                var keys = data.keys;
                var trs = new Array();
                for (var i = 0, size = keys.length; i < size; i++) {
                    trs.push(createCacheTr(keys[i]));
                }
                $('#myTable .content').html(trs.join());
                $('body').css('display', '');
                removeCache();
            });
        }

        function createCacheTr(cacheKey) {
            return '<tr><td>' + cacheKey + '</td><td><a href="javascript:void(0);" class="update-cache">删除</a></td></tr>';
        }


        var o = function () {
            this.render = function () {
                getCaches();
            };
            this.list = function () {
                getCaches();
            };
        };
        return new o();
    });
})();