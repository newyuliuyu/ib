(function () {
    "use strict";
    var models = ['jquery',
        'ajax',
        'webuploader',
        'dot',
        'js/commons/UI',
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
        'icheck',
        'datetimepicker'
    ];
    define(models, function ($, ajax, WebUploader, dot, UI, dialog) {


        function searchTestPaper(page) {
            var learnSegment = $('#learnSegment').val();
            var subject = $('#subject').val();
            var name = $('#name').val();

            var url = "/testpaper/search?1=1";
            if (learnSegment !== '选择学段') {
                url += '&learnSegmentId=' + learnSegment;
            }
            if (subject !== '选择科目') {
                url += '&subjectId=' + subject;
            }
            if (name !== '选择科目') {
                url += '&name=' + name;
            }
            if ($.isPlainObject(page)) {
                url += '&pageNum=' + page.pageNum + "&pageSize=" + page.pageSize;
            }
            var showRelationKnowledgeState = $('#showRelationKnowledgeState').val();
            if (showRelationKnowledgeState !== "0") {
                url += '&showRelationKnowledgeState=' + showRelationKnowledgeState;
            }

            var beginDate = $('#beginDate').val();
            var endDate = $('#endDate').val();

            if (beginDate !== '' && endDate === '') {
                dialog.alter('<span class="text-danger">结束时间不能为空</span>');
                return;
            }
            if (beginDate === '' && endDate !== '') {
                dialog.alter('<span class="text-danger">开始时间不能为空</span>');
                return;
            }
            var date1 = new Date(beginDate);
            var date2 = new Date(endDate)
            if (date2 - date1 < 0) {
                dialog.alter('<span class="text-danger">开始时间必须小于结束时间</span>');
                return;
            }

            if (beginDate !== '' && endDate !== '') {
                url += '&beginDate=' + beginDate + "&endDate=" + endDate;
            }

            ajax.getHTML(url).then(function (html) {
                $('.tabContent_body').html(html);
                UI.pager().create('pager', function (page) {
                    searchTestPaper(page);
                });
            });

        }

        function initLearnSegmentAndSubject() {
            $.when(ajax.getJson('/subject/list'), ajax.getJson('/learnsegment/list')).then(function (subjectData, learnSegmentData) {
                var learnSegments = learnSegmentData[0].learnSegments;
                var learnSegmentHtml = [];
                $.each(learnSegments, function (idx, item) {
                    learnSegmentHtml.push('<option value="' + item.id + '">' + item.name + '</option>')
                });
                $('#learnSegment').append(learnSegmentHtml.join(''));

                var subjects = subjectData[0].subjects;
                var subjectHtml = [];
                $.each(subjects, function (idx, item) {
                    subjectHtml.push('<option value="' + item.id + '">' + item.name + '</option>')
                });
                $('#subject').append(subjectHtml.join(''));
                searchTestPaper();
            });

            $('#searchName').click(function () {
                searchTestPaper();
            });
            $('#subject,#learnSegment,#showRelationKnowledgeState').change(function () {
                searchTestPaper();
            })

        }


        function updateTestPaperKnowledgeSystem($a, testPaper) {
            var url = '/testpaper/update/knoledgesystem';
            ajax.postJson(url, testPaper).then(function (dataset) {
                $a.text(testPaper.knowledgeSystem.name);
                $a.attr('ksid', testPaper.knowledgeSystem.id);
                dialog.fadedialog({tipText: '保存试卷【' + testPaper.name + '】知识体系成功'});
                $a.parents('tr').find('#showBoundKnowledgeBtn').show();
            }).always(function () {
                $('body').close(arguments[0]);
            });
        }

        function initSettingKnowledgeSystemEvent() {
            $('.tabContent_body').on('click', '.set-knowledge-system', function () {
                var url = '/knowledge/knowledgesystem/list';

                var testPaperId = $(this).attr("tpid");
                var knowledgeSystemId = $(this).attr("ksid");
                var $this = $(this);
                var testPaperName = $this.parents('tr').find('td:eq(0)').text();
                var footer = {
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
                            var selectedKsId = $('.ksid:checked').val();
                            var text = $('.ksid:checked').attr('text');
                            var knowledgeSystem = {};
                            knowledgeSystem.id = selectedKsId;
                            knowledgeSystem.name = text;
                            var testPaper = {};
                            testPaper.id = testPaperId;
                            testPaper.name = testPaperName;
                            testPaper.knowledgeSystem = knowledgeSystem;
                            if (selectedKsId && selectedKsId !== knowledgeSystemId) {
                                updateTestPaperKnowledgeSystem($this, testPaper);
                            }
                            $(this).trigger('close');
                        }
                    }]
                };

                $('body').loading();
                ajax.getJson(url).then(function (dataset) {

                    var myDataset = {curKsId: knowledgeSystemId, knowledgeSystems: dataset.knowledgeSystems};
                    var templateText = $("#knowledgeSystemT").text();
                    var arrText = dot.template(templateText);
                    var html = arrText(myDataset);
                    dialog.modal({size: 'md', body: html, footer: footer})
                    $(".ksid").iCheck({
                        checkboxClass: 'icheckbox_square-blue',
                        radioClass: 'iradio_square-blue'
                    });
                }).always(function () {
                    $('body').close(arguments[0]);
                });

            });
        }

        function deleteTestPaperBtnEvent() {

            $('.tabContent_body').on('click', '.del-testpaper', function () {
                var testPaperId = $(this).attr("testpaperid");
                dialog.confirm("删除试卷", "<span class='text-danger'>删除试卷同时也删除试卷关联的所有题目.<br/>你确定要删除吗？</span>", function () {
                    deleteTestPaper(testPaperId)
                }, "确定");
            });
        }

        function clickCurPage() {
            if ($('#pager>.pages').size() === 0) {
                searchTestPaper();
            } else {
                $('#pager').clickCurPage();
            }
        }

        function deleteTestPaper(testpaperId) {
            var url = '/testpaper/del';
            console.log(testpaperId)
            ajax.postJson(url, testpaperId).then(function (dataset) {
                dialog.fadedialog({tipText: '<span class="text-success">保存成功</span>'});
                clickCurPage();
            }).always(function () {
                $('body').close(arguments[0]);
            });
        }


        return {
            render: function () {
                $('body').show();
                // var date = new Date().format("yyyy-MM-dd");
                $('.form_datetime').datetimepicker({
                    format: "yyyy-mm-dd",
                    minView: 2,
                    autoclose: 1,
                    todayBtn: 1
                });
                initLearnSegmentAndSubject();
                initSettingKnowledgeSystemEvent();
                deleteTestPaperBtnEvent();
            }
        }
    });
})();