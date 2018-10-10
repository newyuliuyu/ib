(function () {
    "use strict";
    var models = ['jquery',
        'ajax',
        'dialog',
        'css!style/dialog',
        'ezconfig',
        'js/commons/JQuery.progress',
        'bootstrap',
        'css!style/bootstrap/bootstrap.min',
        'css!style/font-awesome',
        'css!style/public'
    ];
    define(models, function ($, $ajax, dialog) {

        var sendCjURL = window.app.config.sendcjURL;

        function getSendCjURL() {
            return sendCjURL;
        }

        $(window).resize(function () {          //当浏览器大小变化时
            // console.log($(window).height());          //浏览器时下窗口可视区域高度
            // console.log($(document).height());        //浏览器时下窗口文档的高度
            // console.log($(document.body).height());   //浏览器时下窗口文档body的高度
            // console.log($(document.body).outerHeight(true)); //浏览器时下窗口文档body的总高度 包括border padding margin
            initUI();
        });

        function initUI() {
            var h = $(window).height() - 300;
            if (h < 250) {
                h = 250;
            }
            $('.card .card-body').height(h);
        }

        function changeTab($tabBtn) {
            if ($tabBtn.hasClass('disabled')) {
                return;
            }
            $('#noticeTab a').removeClass('active');
            $tabBtn.addClass('active');
            var idx = $tabBtn.attr('idx');
            $('.card .card-body').hide();
            $('.card .card-body:eq(' + idx + ')').show();
        }

        function init() {
            $('#sendNotinceExamBtn').click(function () {
                changeTab($(this));
            });
            $('#sendNotinceStudentBtn').click(function () {
                changeTab($(this));
            });
            $('#publishExamStudentCjBtn').click(function () {
                publishExamCj();
            });
            $('#yetpublishstudenttable').on('click', '.republishstudentcjBtn', function () {
                publishExamStudentCj($(this));
            });
        }

        function publishExamStudentCj($btn) {
            var publishExamCjURL = getSendCjURL() + '/publish/student/cj/' + getExamId();
            var zkzh = [];
            zkzh.push({id: $btn.attr('objId'), zkzh: $btn.attr('zkzh')});
            $ajax.corsPostJson(publishExamCjURL, zkzh).then(function (dataset) {

            }).always(function () {

            });
        }

        function publishExamCj() {
            var publishExamCjURL = getSendCjURL() + '/publish/cj/' + getExamId();
            $ajax.corsPostJson(publishExamCjURL, {}).then(function (dataset) {
                showPublishProgress();
            }).always(function () {

            });
        }

        function loadYetPublishStudent() {
            var checkURL = getSendCjURL() + '/publish/loadpublishstudent/' + getExamId();
            $ajax.corsGetJson(checkURL).then(function (dataset) {
                var students = dataset.students;
                console.log(students)
                var $table = $('#yetpublishstudenttable tbody');
                $.each(students, function (idx, item) {
                    var htmls = [];
                    htmls.push('<tr>');
                    htmls.push('<td>' + (idx + 1) + '</td>');
                    htmls.push('<td>' + item.name + '</td>');
                    if (item.statusNum) {
                        htmls.push('<td class="text-success">成功</td>');
                        htmls.push('<td></td>');
                        htmls.push('<td></td>');
                    } else {
                        htmls.push('<td class="text-danger">失败</td>');
                        htmls.push('<td>' + item.msg + '</td>');
                        htmls.push('<td><button type="button" objId="' + item.id + '" zkzh="' + item.zkzh + '" class="btn btn-outline-dark republishstudentcjBtn">重新发布</button></td>');
                    }
                    htmls.push('</tr>');
                    $table.append(htmls.join(''));
                });
            }).always(function () {
                //console.log(arguments)
            });
        }

        function getExamId() {
            return $('body').data('examId');
        }

        function showPublishProgress() {
            var onlyKey = 'publishexamcj-' + getExamId();
            var url = getSendCjURL() + '/progress';
            $('#progressDIV').show();
            $('#progressDIV').progress({
                url: url,
                onlyKey: onlyKey,
                cors: true,
                finishedCallBack: function () {
                    console.log("运行完毕")
                }
            });
        }

        function checkPublicStudentCj() {
            var examId = getExamId();
            var checkURL = getSendCjURL() + '/publish/checksendcj/' + examId;
            $ajax.corsGetJson(checkURL).then(function (dataset) {
                if (dataset.sendStatus === 1) {
                    //已经发布完毕
                    var $tab0 = $('#noticeTab a:eq(0)').addClass('active');
                    $tab0.addClass('disabled');
                    var $tab1 = $('#noticeTab a:eq(1)').addClass('active');
                    changeTab($tab1);
                } else if (dataset.sendStatus === 2) {
                    //正在发布成绩
                    showPublishProgress();
                }
            }).always(function () {
                if (arguments[1] === "error") {
                    dialog.alter("<span class='text-danger'>微信成绩发布服务器未启动，请联系管理人员</span>");
                }
            });
        }

        return {
            render: function () {
                $('body').show();
                $('body').data('examId', Rqs.queryString('examId'));
                initUI();
                init();
                checkPublicStudentCj();
                loadYetPublishStudent();
            }
        }
    });
})();