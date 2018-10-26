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

        function settingKnowledge() {

            $('.tabContent_body').on('click', '.setting-knowledge', function () {
                var $this = $(this);
                var ids = [];
                if ($this.attr('ids')) {
                    var ids = $(this).attr('ids').split(',');
                }

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

                            var treeObj = $.fn.zTree.getZTreeObj("ktree");
                            var nodes = treeObj.getCheckedNodes(true);

                            if (!nodes || nodes.length === 0) {
                                dialog.fadedialog({tipText: '必须选择至少一个知识点'});
                                return;
                            }
                            var knowledges = [];
                            var selectedIds = [];
                            var names=[];
                            $.each(nodes, function (idx, item) {
                                var k = {};
                                k.content = item.content;
                                k.id = item.id;
                                knowledges.push(k);
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
                            itemKnowledge.id = $this.attr('itemId');
                            itemKnowledge.knowledges = knowledges;

                            var $dthis = $(this);
                            var url = '/item/update/knowledge';
                            ajax.postJson(url, itemKnowledge).then(function (dataset) {
                                dialog.fadedialog({tipText: '保存成功'});
                                $this.text(names.join(","));
                                $this.attr('ids',selectedIds.join(','));
                                $dthis.trigger('close');
                            }).always(function () {
                                $('body').close(arguments[0]);
                            });


                        }
                    }]
                };


                var html = '<div id="ktree" class="ztree"></div>'
                dialog.modal({size: 'md', body: html, footer: footer})
                var setting = initTreeSetting();

                window.treeObj = $.fn.zTree.init($("#ktree"), setting, knowledges);

                if (ids.length > 0) {
                    $.each(ids, function (idx, item) {
                        var node = treeObj.getNodeByParam('id', item);
                        if (node) {
                            node.checked = true;
                            treeObj.updateNode(node);
                            treeObj.expandNode(node.getParentNode(), true, true, true);
                        }
                    });
                }
            });
        }

        var knowledges = undefined;
        return {
            render: function () {
                $('body').show();
                var testPaperId = Req.queryString('id');
                $('body').data('testPaper', testPaperId);
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
            }
        }
    });
})();