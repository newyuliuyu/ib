(function () {
    "use strict";
    var models = ['jquery',
        'ajax',
        'webuploader',
        'dot',
        'dialog',
        'css!style/dialog',
        'js/commons/JQuery.progress',
        'bootstrap',
        'css!style/bootstrap/bootstrap.min',
        'css!style/font-awesome',
        'css!style/public',
        'css!style/item',
        'loading',
        'icheck',
        'ztree'
    ];
    define(models, function ($, ajax, WebUploader, dot, dialog) {

        function initTreeSetting() {
            var setting = {
                data: {
                    simpleData: {
                        enable: true,
                        idKey: 'id',
                        pIdKey: 'parentId',
                        rootPId: '0'
                    }
                },
                check: {
                    enable: true,
                    chkStyle: 'checkbox',
                    radioType: 'all'
                }
            };
            return setting;
        }


        function initTreeData() {
            var dataset = [
                {id: 1, pId: 0, name: "知识点1", open: true},
                {id: 11, pId: 1, name: "知识点11"},
                {id: 111, pId: 11, name: "知识点111"},
                {id: 112, pId: 11, name: "知识点112"},
                {id: 12, pId: 1, name: "知识点12"},
                {id: 121, pId: 12, name: "知识点121"},
                {id: 122, pId: 12, name: "知识点122"},
                {id: 2, pId: 0, name: "知识点2", chkDisabled: true, nocheck: true},
                {id: 21, pId: 2, name: "知识点21"},
                {id: 211, pId: 21, name: "知识点211"},
                {id: 212, pId: 21, name: "知识点212"},
                {id: 22, pId: 2, name: "知识点22"},
                {id: 221, pId: 22, name: "知识点221"},
                {id: 222, pId: 22, name: "知识点222"},
                {id: 3, pId: 0, name: "知识点3", myname: 'ddddd'},
                {id: 31, pId: 3, name: "知识点31"},
                {id: 311, pId: 31, name: "知识点311"},
                {id: 312, pId: 31, name: "知识点312"},
                {id: 32, pId: 3, name: "知识点32"},
                {id: 321, pId: 32, name: "知识点321"},
                {id: 322, pId: 32, name: "知识点322"}
            ];
            // var dataset =[
            //     { id:1, pId:0, name:"知识点1", open:true},
            //     { id:2, pId:0, name:"知识点2"},
            //     { id:3, pId:0, name:"知识点3"},
            //     { id:31, pId:3, name:"知识点31"},
            //     { id:32, pId:3, name:"知识点32"}
            //
            // ];
            return dataset;
        }

        function initKnowledgeTree() {
            var setting = initTreeSetting();
            var dataset = initTreeData();
            window.treeObj = $.fn.zTree.init($("#knowledgeTree"), setting, dataset);
        }

        function showItem(dataset) {
            console.log(dataset)
            var templateText = $("#itemT").text();
            var arrText = dot.template(templateText);
            var html = arrText(dataset);
            $('.tabContent_body').append(html);
        }

        function showItemAnalysis() {
            $('.tabContent_body').on('click', '.item-analysis-btn', function () {
                var offset = $(this).parents('.item-list').offset();
                $(document).scrollTop(offset.top - 100);
                if ($(this).hasClass('item-analysis-btn-active')) {
                    $(this).removeClass('item-analysis-btn-active');
                    $(this).text("解析");
                    $(this).parents('.item-list').find('.item-detail').hide();
                    return;
                }
                $('.item-analysis-btn').removeClass('item-analysis-btn-active');
                $('.item-detail').hide();
                $(this).addClass('item-analysis-btn-active');
                $(this).parents('.item-list').find('.item-detail').show();
                $(this).text("收起")

            });
        }

        function setAllItemKnowledgeEvent() {

            $('#setAllItemKnowledge').click(function () {
                showKnowledgeDialog(function ($theDialog, knowledges) {

                    var selectedIds = [];
                    var names = [];
                    $.each(knowledges, function (idx, item) {
                        selectedIds.push(item.id);
                        names.push(item.content);
                    });

                    var itemKnowledges = [];
                    $('.item-list').each(function () {
                        var itemKnowledge = {};
                        itemKnowledge.id = $(this).data('itemid');
                        itemKnowledge.knowledges = knowledges;
                        itemKnowledges.push(itemKnowledge);
                    });

                    var testPaperId = $('body').data('testPaperId');
                    var url = '/testpaper/update/itemKnowledges/' + testPaperId;
                    ajax.postJson(url, itemKnowledges).then(function (dataset) {
                        dialog.fadedialog({tipText: '保存成功'});
                        $('.item-list').each(function () {
                            var $knowledgeA = $(this).find('.setting-knowledge');
                            $knowledgeA.text(names.join(","));
                            $knowledgeA.attr('ids', selectedIds.join(','));
                        });

                        $theDialog.trigger('close');
                    }).always(function () {
                        $('body').close(arguments[0]);
                    });

                });
            });
        }


        function showKnowledgeDialog(clickOkBtnClallBackFun, showDialogCallBackFun) {

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
                        var nodes = getTreeCheckedNodes();
                        if (!nodes || nodes.length === 0) {
                            dialog.fadedialog({tipText: '必须选择至少一个知识点'});
                            return;
                        }
                        var knowledges = [];
                        $.each(nodes, function (idx, item) {
                            var k = {};
                            k.content = item.content;
                            k.id = item.id;
                            knowledges.push(k);
                        });

                        if (clickOkBtnClallBackFun) {
                            clickOkBtnClallBackFun($(this), knowledges);
                        }
                    }
                }]
            };

            var html = '<div class="input-group mb-3">' +
                '  <input id="knowledgeContents" type="text" class="form-control" placeholder="输入知识点">' +
                '  <div class="input-group-append">' +
                '    <a class="input-group-text" id="searchKnolwdge" style="cursor: pointer;">查询</a>' +
                '  </div>' +
                '</div><div id="ktree" class="ztree"></div>'
            dialog.modal({size: 'md', body: html, footer: footer})
            var setting = initTreeSetting();
            window.treeObj = $.fn.zTree.init($("#ktree"), setting, knowledges);

            $('#searchKnolwdge').click(function () {
                var contents = $('#knowledgeContents').val().trim();
                if (!contents || contents === '') {
                    return;
                }
                var contentArray = contents.split("|");
                $.each(contentArray, function (idx, item) {
                    item = item.trim();
                    //console.log(item)
                    var nodes = treeObj.getNodesByParamFuzzy("name", item, null);
                    $.each(nodes, function (idx1, item1) {
                        item1.checked = true;
                        treeObj.updateNode(item1);
                        treeObj.expandNode(item1.getParentNode(), true, false, true);
                    });
                });
            });

            if (showDialogCallBackFun) {
                showDialogCallBackFun(treeObj);
            }

        }

        function getTreeCheckedNodes() {
            var treeObj = $.fn.zTree.getZTreeObj("ktree");
            var nodes = treeObj.getCheckedNodes(true);
            return nodes;
        }

        function settingKnowledge() {

            $('.tabContent_body').on('click', '.setting-knowledge', function () {
                var $knowledgeA = $(this);
                var ids = [];
                if ($knowledgeA.attr('ids')) {
                    var ids = $knowledgeA.attr('ids').split(',');
                }

                showKnowledgeDialog(function ($theDialog, knowledges) {
                    var selectedIds = [];
                    var names = [];
                    $.each(knowledges, function (idx, item) {
                        selectedIds.push(item.id);
                        names.push(item.content);
                    });

                    selectedIds.sort(function (a, b) {
                        return a - b
                    });
                    ids.sort(function (a, b) {
                        return a - b
                    });
                    if (ids.equals(selectedIds)) {
                        return;
                    }

                    var itemKnowledge = {};
                    itemKnowledge.id = $knowledgeA.attr('itemId');
                    itemKnowledge.knowledges = knowledges;

                    var testPaperId = $('body').data('testPaperId');
                    var url = '/testpaper/update/itemKnowledge/' + testPaperId;
                    ajax.postJson(url, itemKnowledge).then(function (dataset) {
                        dialog.fadedialog({tipText: '保存成功'});
                        $knowledgeA.text(names.join(","));
                        $knowledgeA.attr('ids', selectedIds.join(','));
                        $theDialog.trigger('close');
                    }).always(function () {
                        $('body').close(arguments[0]);
                    });


                }, function (treeObj) {
                    $.each(ids, function (idx, item) {
                        var node = treeObj.getNodeByParam('id', item);
                        if (node) {
                            node.checked = true;
                            treeObj.updateNode(node);
                            treeObj.expandNode(node.getParentNode(), true, false, true);
                        }
                    });
                });
            });

        }


        function edititembtnEvent() {
            $('.tabContent_body').on('click', '.edit-item-btn', function () {
                var dialogStyle = {'width': '230mm'};
                var dialogBodyStyle = {'width': '210mm', 'padding': '0px', 'min-height': '100px'};
                var header = {show: false};
                var $itemBody = $(this).parents('.item-list').find('.item-body');
                var itemStemId = $itemBody.attr('itemStemId');
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
                            var $thisDialog = $(this);
                            var itemStem = {};
                            itemStem.id = itemStemId;
                            itemStem.content = $('#itemStemContent').html();
                            var url = '/item/update/itemstem';
                            ajax.postJson(url, itemStem).then(function () {
                                $itemBody.html(itemStem.content);
                                dialog.fadedialog({tipText: '保存成功'});
                                $thisDialog.trigger('close');
                            }).always(function () {
                                $('body').close(arguments[0]);
                            });
                        }
                    }]
                };
                var html = $itemBody.html();
                dialog.modal({
                    size: 'md',
                    body: '<div id="itemStemContent" style="width: 205mm;" contenteditable="true">' + html + '</div>',
                    dialogStyle: dialogStyle,
                    dialogBodyStyle: dialogBodyStyle,
                    moveable: false,
                    header: header,
                    footer: footer
                });
            });
        }

        var knowledges = undefined;
        return {
            render: function () {
                $('body').show();
                var testPaperId = Req.queryString('id');
                $('body').data('testPaperId', testPaperId);
                var url = '/knowledge/search/' + testPaperId;
                $('body').loading();
                ajax.getJson(url).then(function (dataset) {
                    knowledges = dataset.knowledges;
                    $.each(knowledges, function (idx, item) {
                        item.name = item.content + '(' + item.id + '-' + item.parentId + '-' + item.deep + ')';
                        if (item.deep !== 3) {
                            item.nocheck = true;
                        }
                    })
                    var url = '/item/search/' + testPaperId;
                    return ajax.getJson(url)
                }).then(function (dataset) {
                    showItem(dataset);
                }).always(function () {
                    $('body').close(arguments[0]);
                });
                showItemAnalysis();
                settingKnowledge();
                //initKnowledgeTree();
                edititembtnEvent();
                setAllItemKnowledgeEvent();
            }
        }
    });
})();