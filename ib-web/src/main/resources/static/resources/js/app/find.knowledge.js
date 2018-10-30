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

                }).always(function () {
                    $('body').close(arguments[0])
                });
            });
        }

        return {
            render: function () {
                $('body').show();
                searchKnowledgeEvent();
            }
        }
    });
})();