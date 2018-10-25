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

        function initTreeSetting(){
            var setting = {
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                check:{
                    enable:true,
                    chkStyle:'radio',
                    radioType:'all'
                }
            };
            return setting;
        }


        function initTreeData(){
            var dataset =[
                { id:1, pId:0, name:"知识点1", open:true},
                { id:11, pId:1, name:"知识点11"},
                { id:111, pId:11, name:"知识点111"},
                { id:112, pId:11, name:"知识点112"},
                { id:12, pId:1, name:"知识点12"},
                { id:121, pId:12, name:"知识点121"},
                { id:122, pId:12, name:"知识点122"},
                { id:2, pId:0, name:"知识点2",chkDisabled:true,nocheck:true},
                { id:21, pId:2, name:"知识点21"},
                { id:211, pId:21, name:"知识点211"},
                { id:212, pId:21, name:"知识点212"},
                { id:22, pId:2, name:"知识点22"},
                { id:221, pId:22, name:"知识点221"},
                { id:222, pId:22, name:"知识点222"},
                { id:3, pId:0, name:"知识点3",myname:'ddddd'},
                { id:31, pId:3, name:"知识点31"},
                { id:311, pId:31, name:"知识点311"},
                { id:312, pId:31, name:"知识点312"},
                { id:32, pId:3, name:"知识点32"},
                { id:321, pId:32, name:"知识点321"},
                { id:322, pId:32, name:"知识点322"}
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

        function initKnowledgeTree(){
            var setting = initTreeSetting();
            var dataset = initTreeData();
           window.treeObj =  $.fn.zTree.init($("#knowledgeTree"), setting, dataset);
        }



        return {
            render: function () {
                $('body').show();
                initKnowledgeTree();
            }
        }
    });
})();