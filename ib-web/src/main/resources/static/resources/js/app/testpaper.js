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
        'icheck'
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
            $('#subject,#learnSegment').change(function () {
                searchTestPaper();
            })
        }


        function updateTestPaperKnowledgeSystem($a, testPaper) {
            var url = '/testpaper/update/knoledgesystem';
            ajax.postJson(url, testPaper).then(function (dataset) {
                $a.text(testPaper.knowledgeSystem.name);
                $a.attr('ksid', testPaper.knowledgeSystem.id);
                dialog.fadedialog({tipText: '保存试卷【' + testPaper.name + '】知识体系成功'});
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

        return {
            render: function () {
                $('body').show();
                initLearnSegmentAndSubject();
                initSettingKnowledgeSystemEvent();
            }
        }
    });
})();