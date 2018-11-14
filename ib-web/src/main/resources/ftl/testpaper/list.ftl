<#import "pager.ftl" as page>

<table class="table table-striped">
    <thead>
    <tr>
        <th style="width: 5%;">#</th>
        <th>试卷名称</th>
        <th style="width: 6%;">题目数量</th>
        <th style="width: 6%;">关联知识点的数量</th>
        <th>创建时间</th>
        <th>识体系</th>
        <th style="width: 30%;text-align:center;">操作</th>
    </tr>
    </thead>
    <tbody>
    <#list testPapers as item>
    <tr>
        <th scope="row">${item_index+1}</th>
        <td>${item.name}</td>
        <td>${item.testPaperAttr.itemNum}</td>
        <td>${item.testPaperAttr.relationKnowledgeNum}</td>
        <td>${item.getCreateDate()?string("yyyy-MM-dd")}</td>
        <td><a href="javascript:void(0);" tpid="${item.id?c}" ksid="${(item.knowledgeSystem.id)!""}" class="set-knowledge-system">${(item.knowledgeSystem.name)!"设置知识体系"}</a></td>
        <td class="text-right">
            <span id="showBoundKnowledgeBtn" style="display:${((item.knowledgeSystem.name)==null)?string("none","")};">
            <a href="../item/bound-knowledge.html?id=${item.id?c}" target="_blank" class="">绑定题目知识点</a>
            </span>

            <a href="javascript:void(0);" testPaperId="${item.id?c}" class="del-testpaper">删除试卷</a>
        </td>
    </tr>
    </#list>
    </tbody>
</table>

<@page.pager pager=pageInfo css="margin:0;text-align:left;width: 100%;" />