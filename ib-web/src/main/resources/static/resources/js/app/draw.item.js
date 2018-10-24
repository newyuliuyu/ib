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
        'loading',
        'icheck'
    ];
    define(models, function ($, ajax, WebUploader, dot, dialog) {

        var hasError = false;
        var myDialog;

        function initUploadStudentBtn() {
            var url = window.app.rootPath + "upload";
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
                pick: '#importDocxPaper',
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
                var templateText = $("#docxPaperT").text();
                var arrText = dot.template(templateText);
                var html = arrText(mydataset);
                $('#docxPapers').append(html);
            });
            $('#docxPapers').on('click', '.removeFile', function () {
                var $parent = $(this).parent();
                $parent.remove();
                uploader.removeFile($parent.attr('id'), true);
            });
            uploader.on('uploadSuccess', function () {
                var file = arguments[0];
                var dataset = arguments[1];
                if (dataset.status && dataset.status.code && dataset.status.code === "0") {
                    var msg = dataset.status.detail;
                    hasError = true;
                    dialog.alter(msg, "关闭");
                    return;
                }
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
                if (!hasError) {
                    fetchItem();
                }
            });
        }

        function fetchItem() {
            $('body').loading();
            var docxname = "";
            $('#docxPapers>div').each(function (idx, item) {
                var oldfile = $(item).attr('old');
                var newfile = $(item).attr('new');
                docxname = newfile;
            });

            var url = "/fetch/docx/paper/item?wordname=" + docxname;

            ajax.getJson(url).then(function (dataset) {
                myDialog.find('.dialog-footer > button').click();

                localStorage['ezitemhtml'] = dataset.html;

                //$('.tabContent_body').html(dataset.html);
                UIshowItem();
            }).always(function () {
                $('body').close(arguments[0]);
            });
        }

        function UIshowItem() {
            if (localStorage['ezitemhtml']) {
                $('.tabContent_body').html(localStorage['ezitemhtml']);
            }
        }

        function uploadDocxPaper() {
            $('#uploadDocxPaper').click(function () {
                var html = $("#uploadDialogT").text();
                myDialog = dialog.modal({body: html});
                initUploadStudentBtn();
            });
        }


        function getClazzName() {
            var clazzName = "";
            if ($(".attribute:checked").val() === "1") {
                clazzName = "ez-item";
            } else if ($(".attribute:checked").val() === "2") {
                clazzName = "ez-analysis-item";
            } else if ($(".attribute:checked").val() === "3") {
                clazzName = "ez-answer-item";
            } else if ($(".attribute:checked").val() === "4") {
                clazzName = "ez-review-item";
            } else {
                $(".attribute:eq(0)").iCheck('check');
                clazzName = "ez-item";
            }
            return clazzName;
        }

        function selectItem() {
            // $('.tabContent_body').on('mouseup', function () {
            //     var range = window.getSelection().getRangeAt(0);
            //     var html = range.cloneContents();
            //     console.log(html)
            //     console.log($(html));
            // })

            $('#delSelected').click(function () {
                $('div.ez-selected > *:eq(0)').unwrap();
            });

            $('.tabContent_body').on('mouseup', '.ez-item,.ez-analysis-item,.ez-answer-item,.ez-answer-item,.ez-review-item', function (event) {

                console.log(arguments)
                event.stopPropagation();
            });

            $('.tabContent_body').on('click', '.ez-item,.ez-analysis-item,.ez-answer-item,.ez-review-item', function (event) {
                $('.ez-item,.ez-analysis-item,.ez-answer-item,.ez-answer-item,.ez-review-item').removeClass('ez-selected');
                $(this).addClass('ez-selected');
                var val = "1";
                if ($(this).hasClass('ez-item')) {
                    val = "1";
                } else if ($(this).hasClass('ez-analysis-item')) {
                    val = "2";
                }
                if ($(this).hasClass('ez-answer-item')) {
                    val = "3";
                }
                if ($(this).hasClass('ez-review-item')) {
                    val = "4";
                }
                $('.attribute[value="' + val + '"]').iCheck('check');

            });
            // $('.tabContent_body').on('mouseup','.ez-analysis-item', function (event) {
            //
            //     console.log(arguments)
            //     event.stopPropagation();
            // });
            // $('.tabContent_body').on('mouseup','.ez-answer-item', function (event) {
            //
            //     console.log(arguments)
            //     event.stopPropagation();
            // });
            // $('.tabContent_body').on('mouseup','.ez-review-item', function (event) {
            //
            //     console.log(arguments)
            //     event.stopPropagation();
            // });
            $('.tabContent_body').on('mouseup', function () {
                // var range = window.getSelection().getRangeAt(0);


                var selection = window.getSelection();
                var range = selection.getRangeAt(0);
                if (range.collapsed) {
                    return;
                }

                var className = getClazzName();
                var wrapHTML = '<div class="' + className + '"></div>';
                var div = $(wrapHTML)[0];
                div.append(range.cloneContents())
                // console.log(div)
                // console.log($(div).children().size())
                console.log($(div).children().size())
                if ($(div).children().size() === 0) {
                    var tmpHTML = "<span>" + $(div).text() + "</span>";
                    $(div).html(tmpHTML);
                }

                range.deleteContents();
                range.insertNode(div)
                selection.removeAllRanges()

                // $($('div.aa').find(':first-child')[0]).unwrap()
                // $('div.aa > *:eq(0)').unwrap()

                // console.log('选择题目.....')
                // var divs = $('<div></div>')
                // $(divs).html(window.getSelection())
                // console.log($(divs).html())
                // var begin = window.getSelection().anchorOffset
                // var endin = window.getSelection().focusOffset
                // console.log(begin,endin)
            });

        }

        function previewItem() {
            $('#previewItem').click(function () {

                var items = getItems();
                var mydataset = {items: items};
                var templateText = $("#viewItemT").text();
                var arrText = dot.template(templateText);
                var html = arrText(mydataset);

                dialog.modalFull({body: html});
            });
        }

        function getItems() {
            var items = [];
            var item = undefined;
            $('.ez-item,.ez-analysis-item,.ez-answer-item,.ez-review-item').each(function () {
                if ($(this).hasClass('ez-item')) {
                    if (item !== undefined) {
                        items.push(item)
                    }
                    item = {};
                    item.item = $(this).html();
                } else if ($(this).hasClass('ez-analysis-item')) {
                    item.analysis = $(this).html();
                } else if ($(this).hasClass('ez-answer-item')) {
                    item.answer = $(this).html();
                } else if ($(this).hasClass('ez-review-item')) {
                    item.review = $(this).html();
                }
            });
            return items;
        }

        function checkItemIntegrity() {
            $('#checkItemIntegrity').click(function () {
                checkItemIntegrity2(true);
            });
        }

        function checkItemIntegrity2(showDialog) {
            var itemNum = $('.ez-item').size();
            var analysisItemNum = $('.ez-analysis-item').size();
            var answerItemNum = $('.ez-answer-item').size();
            var reviewItemNum = $('.ez-review-item').size();

            var checkResult = true;
            var msg = [];
            if (itemNum == 0) {
                msg.push("没有题目数据<br/>");
                checkResult = false;
            }
            if (itemNum < analysisItemNum) {
                msg.push("题目数据小于分析数<br/>");
                checkResult = false;
            }
            if (itemNum < answerItemNum) {
                msg.push("题目数据小于解析数<br/>");
                checkResult = false;
            }
            if (itemNum < reviewItemNum) {
                msg.push("题目数据小于点评数<br/>");
                checkResult = false;
            }
            msg.push("题目：" + itemNum);
            msg.push("<br/>分析：" + analysisItemNum);
            msg.push("<br/>解析：" + answerItemNum);
            msg.push("<br/>点评：" + reviewItemNum);


            if (showDialog || !checkResult) {
                dialog.alter('<div style="font-size: 15px;color: red;">' + msg.join("") + '</div>')
            }
            return checkResult;
        }


        function highlightShowItem() {
            $('#highlightShowItem').click(function () {
                $('.ez-item').css('background-color', '#d6eadb');
            });

        }

        function icheckClicked() {
            var className = getClazzName();
            $('div.ez-selected').removeClass('ez-item');
            $('div.ez-selected').removeClass('ez-analysis-item');
            $('div.ez-selected').removeClass('ez-answer-item');
            $('div.ez-selected').removeClass('ez-review-item');
            $('div.ez-selected').addClass(className);
        }

        function tmpSave() {
            $('#tmpSave').click(function () {
                localStorage['ezitemhtml'] = $('.tabContent_body').html();
                dialog.fadedialog({tipText: '保存成功'})
            });
        }

        function save() {
            $('#save').click(function () {
                if (!checkItemIntegrity2()) {
                    return;
                }


                var footer = {
                    show: true,
                    buttons: [{
                        type: 'button',
                        text: "取消",
                        clazz: 'btn-default',
                        callback: function () {
                            $(this).trigger('close');
                        }
                    }, {
                        type: 'button',
                        text: "保存",
                        clazz: 'btn-primary',
                        callback: function () {
                            if (saveToBackend()) {
                                $(this).trigger('close');
                            }
                        }
                    }]
                };


                $.when(ajax.getJson('/subject/list'), ajax.getJson('/learnsegment/list')).then(function (subjectData, learnSegmentData) {

                    var dataset = {
                        subjects: subjectData[0].subjects,
                        learnSegments: learnSegmentData[0].learnSegments
                    };

                    var templateText = $("#formT").text();
                    var arrText = dot.template(templateText);
                    var html = arrText(dataset);
                    dialog.modal({size: 'md', body: html, footer: footer})
                });


            });
        }

        function saveToBackend() {
            var itemdataset = getItems();
            var items = [];

            $.each(itemdataset, function (idx, data) {
                var item = {};
                item.itemStem = {content: data.item};
                item.analysis = {content: data.analysis};
                item.answer = {content: data.answer};
                item.comment = {content: data.review};
                items.push(item);
            });


            var testPaper = {};
            testPaper.name = $('#paperName').val();
            testPaper.subject = {id: $('#subject option:selected').val(), name: $('#subject option:selected').text()};
            testPaper.learnSegment = {
                id: $('#learnSegment option:selected').val(),
                name: $('#learnSegment option:selected').text()
            };

            if (!testPaper.name || testPaper.name === '') {
                dialog.alter('试卷名称不能为空')
                return false;
            }
            if (!testPaper.subject.id) {
                dialog.alter('必须选择科目')
                return false;
            }
            if (!testPaper.learnSegment.id) {
                dialog.alter('必须选择学段')
                return false;
            }


            var dataset = {};
            dataset.testPaper = testPaper;
            dataset.items = items;
            var url = "/testpaper/savetestpaperitem";
            $('body').loading();
            ajax.postJson(url, dataset).then(function (value) {
                dialog.fadedialog({tipText: "保存成功"});
            }).always(function () {
                $('body').close(arguments[0]);
            });
            return true;
        }

        return {
            render: function () {

                $('body').show();
                UIshowItem();


                uploadDocxPaper();
                selectItem();
                $(".attribute").iCheck({
                    checkboxClass: 'icheckbox_square-blue',
                    radioClass: 'iradio_square-blue'
                }).on('ifChecked', function () {
                    icheckClicked();
                });
                previewItem();
                checkItemIntegrity();
                highlightShowItem();
                tmpSave();
                save();
            }
        }
    });
})();