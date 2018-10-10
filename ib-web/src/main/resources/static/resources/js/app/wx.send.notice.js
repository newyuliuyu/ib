(function () {
    "use strict";
    var models = ['jquery',
        'js/commons/ajax',
        'vue',
        'bootstrap',
        'css!style/bootstrap/bootstrap.min',
        'css!style/public'
    ];
    define(models, function ($, $ajax, Vue) {
        var sendCjURL = window.app.config.wxProxyURL;

        function sendNoticeEvent() {
            $('#sendNotice').click(function () {
                sendNotice();
            });
        }

        function sendNotice() {
            var noticeData = {};
            noticeData.touser = $('#touser').val().trim();
            noticeData.url = $('#url').val().trim();
            var conent = noticeData.data = {};

            conent.first = {value: $('#first').val().trim(), color: '#212529'}
            conent.keyword1 = {value: $('#keyword1').val().trim(), color: '#212529'}
            conent.keyword2 = {value: $('#keyword2').val().trim(), color: '#212529'}
            conent.remark = {value: $('#remark').val().trim(), color: '#212529'}

            $ajax.corsPostJson(sendCjURL + '/wxcjnotice', noticeData).then(function () {
                console.log(arguments)
            }).always(function () {
                console.log(arguments)
            });
        }

        return {
            render: function () {
                sendNoticeEvent();
            }
        }
    });
})();