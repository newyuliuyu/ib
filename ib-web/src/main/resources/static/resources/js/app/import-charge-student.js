(function () {
    "use strict";
    var models = ['jquery', 'ajax', 'dialog',
        'webuploader',
        'dot',
        'bootstrap',
        'ezconfig',
        'js/commons/JQuery.download',
        'js/commons/JQuery.progress',
        'css!style/bootstrap/bootstrap.min',
        'css!style/public',
        'css!style/font-awesome',
        'css!style/dialog'
    ];
    define(models, function ($, $ajax, dialog, WebUploader, dot) {
        var sendCjURL = window.app.config.sendcjURL;

        function getSendCjURL() {

            return sendCjURL;
        }

        $(window).resize(function () {          //当浏览器大小变化时
            // console.log($(window).height());          //浏览器时下窗口可视区域高度
            // console.log($(document).height());        //浏览器时下窗口文档的高度
            // console.log($(document.body).height());   //浏览器时下窗口文档body的高度
            // console.log($(document.body).outerHeight(true)); //浏览器时下窗口文档body的总高度 包括border padding margin
            setHeight();
        });

        function setHeight() {
            var h = $(window).height() - 300;
            if (h < 250) {
                h = 250;
            }
            $('#guideContent').height(h);
        }

        function initUI() {
            setHeight();
            var width = $('#guideContent').width();
            var $pages = $('#guideContent>div');
            $pages.css('width', width);
            var $lengthDiv = $('<div/>', {
                'id': 'liuyuID',
                css: {
                    'height': '100%',
                    'width': width * $pages.size()
                }
            });
            $pages.wrapAll($lengthDiv);
        }

        function initNextStepEvent() {
            $('.nextStep').on('click', function () {
                var idx = parseInt($(this).attr('idx'));
                if (!check(idx)) {
                    return;
                }
                $('.li' + (idx + 1)).addClass('selected');
                var width = $('#guideContent').width();
                $('#guideContent').animate({
                    scrollLeft: (idx * width)
                }, 500);
            });
            $('.prevStep').on('click', function () {
                var idx = parseInt($(this).attr('idx'));
                $('.li' + (idx)).removeClass('selected');
                var width = $('#guideContent').width();
                $('#guideContent').animate({
                    scrollLeft: (idx - 2) * width
                }, 500);
            });
        }

        function check(idx) {
            if (idx === 1) {
                var files = $('#studentFiles>div');
                if (files.size() == 0) {
                    dialog.alter("还没选择学生信息文件", "关闭");
                    return false;
                }
            }
            return true;
        }


        function initUploadStudentBtn() {
            var url = getSendCjURL() + "/upload";
            var flashURL = window.app.rootPath + 'Uploader.swf';
            var uploader = WebUploader.create({
                // 选完文件后，是否自动上传。
                auto: true,
                // swf文件路径
                swf: flashURL,
                // 文件接收服务端。
                server: url,
                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                pick: '#studentUpload',
                fileVal: 'liuyufile',
                // 只允许选择图片文件。
                accept: {
                    title: 'Applications',
                    extensions: '*',
                    mimeTypes: '*/*'
                }
            });

            uploader.on('uploadProgress', function () {
                var file = arguments[0];
                var percent = (arguments[1] * 100).toFixed2(0);
                if (percent === 100) {
                    percent = 99;
                }
                $('#' + file.id + ' .progress-bar').css('width', percent + '%').text(percent + '%');
            });
            uploader.on('error', function () {
                var file = arguments[1];
                var text = file.name + "格式不对,只能为xls和xlsx文件"
                if ('F_DUPLICATE' === arguments[0]) {
                    text = file.name + "已经存在"
                }
                dialog.alter(text, "关闭");
            });
            uploader.on('fileQueued', function () {
                var mydataset = {
                    'file': arguments[0]
                };
                var templateText = $("#studentT").text();
                var arrText = dot.template(templateText);
                var html = arrText(mydataset);
                $('#studentFiles').append(html);
            });
            $('#studentFiles').on('click', '.removeFile', function () {
                var $parent = $(this).parent();
                $parent.remove();
                uploader.removeFile($parent.attr('id'), true);
            });
            uploader.on('uploadSuccess', function () {
                var file = arguments[0];
                var dataset = arguments[1];
                $.each(dataset, function (idx, item) {
                    $('#' + file.id).attr('old', item.old);
                    $('#' + file.id).attr('new', item['new']);
                });
            });
            uploader.on('uploadError', function () {
                // console.log('uploadError', arguments)
            });
            uploader.on('uploadComplete', function () {
                var file = arguments[0];
                $('#' + file.id + ' .progress-bar').css('width', '100%').text('100%').addClass('bar-over').removeClass('bar-unOver');
            });
        }

        function init() {
            $('.downloadTemplate').click(function () {
                var url = getSendCjURL() + "/download/studentInfoTemplate";
                $.download(url);
            });

            $('.okButton').click(function () {
                $(this).attr('disabled', "true");
                var fileNames = [];
                $('#studentFiles>div').each(function (idx, item) {
                    var oldfile = $(item).attr('old');
                    var newfile = $(item).attr('new');
                    fileNames.push({oldfile: oldfile, newfile: newfile});
                });
                console.log(fileNames)
                var url = getSendCjURL() + "/process";
                $('#overShow').hide();
                $('#progressDIV').show();
                $ajax.corsPostJson(url, fileNames).then(function (dataset) {
                    console.log('then', arguments)
                    var onlyKey = dataset.onlyKey;
                    $('#progressDIV').progress({
                        url: getSendCjURL() + '/progress',
                        onlyKey: onlyKey,
                        cors: true,
                        finishedCallBack: function () {
                            console.log("运行完毕")
                        }
                    });
                }).always(function () {
                    console.log('always', arguments)
                });
            });
        }

        return {
            render: function () {
                $('body').show();
                initUI();
                initNextStepEvent();
                initUploadStudentBtn();
                init();

            }
        }
    });
})();