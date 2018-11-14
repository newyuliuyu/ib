(function () {
    "use strict";
    var models = ['jquery',
        'ajax',
        'webuploader',
        'dot',
        'copyutils',
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
    define(models, function ($, ajax, WebUploader, dot, ClipboardJS, dialog) {

        function searchKnowledgeEvent() {
            $('.search-knowledge').click(function () {
                var values = $('#knowledgeContent').val().trim();
                if (!values || values === '') {
                    dialog.fadedialog({tipText: '没有输入知识点'});
                    return;
                }
                var knowledges = values.split("\n");
                var dataset = [];
                $.each(knowledges, function (idx, item) {
                    var tmp = item.trim();
                    if (tmp !== '') {
                        dataset.push(tmp)
                    }
                })

                var url = '/knowledge/search/with-content';
                console.log(dataset)
                ajax.postJson(url, dataset).then(function (dataset1) {
                    console.log(dataset1)
                    var templateText = $("#itemT").text();
                    var arrText = dot.template(templateText);
                    var html = arrText(dataset1);
                    $('.tabContent_body').html(html);
                    $('body').data('knowledgeContentToIds', dataset1.knowledgeContentToIds)


                }).always(function () {
                    $('body').close(arguments[0])
                });
            });
        }

        function getKnowledgeIds() {
            var ids = [];
            var knowledgeContentToIds = $('body').data('knowledgeContentToIds');
            if (knowledgeContentToIds) {
                $.each(knowledgeContentToIds, function (idx, item) {
                    ids.push(item.ids);
                });
            }
            return ids.join('\n');
        }

        function copyKnowledgeIdsEvent() {

            var clipboard = new ClipboardJS('#copyKnowledgeIds', {
                text: function (trigger) {
                    return getKnowledgeIds();
                }
            });
            clipboard.on('success', function (e) {
                // console.log('Action:', e.action);
                // console.log('Text:', e.text);
                // console.log('Trigger:', e.trigger);
                dialog.fadedialog({tipText: '复制成功'})
                e.clearSelection();
            });
            clipboard.on('error', function (e) {
                dialog.fadedialog({tipText: '复制失败'})
                // console.log('Action:', e.action);
                // console.log('Trigger:', e.trigger);
            });
        }

        return {
            render: function () {
                $('body').show();
                searchKnowledgeEvent();
                copyKnowledgeIdsEvent();
            }
        }
    });
})();