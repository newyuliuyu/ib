<#import "pager.ftl" as page>

<table class="table table-striped">
    <thead>
    <tr>
        <th style="width: 5%;">#</th>
        <th>试卷名称</th>
        <th>所属知识体系</th>
        <th style="width: 30%;text-align:center;">操作</th>
    </tr>
    </thead>
    <tbody>
    <#list testPapers as item>
    <tr>
        <th scope="row">${item_index+1}</th>
        <td>${item.name}</td>
        <td><a href="javascript:void(0);" tpid="${item.id?c}" ksid="${(item.knowledgeSystem.id)!""}" class="set-knowledge-system">${(item.knowledgeSystem.name)!"设置知识体系"}</a></td>
        <td class="text-right">

            <a href="../item/bound-knowledge.html?id=${item.id?c}" type="button" class="btn btn-outline-primary btn-sm">绑定题目知识点</a>
        </td>
    </tr>
    </#list>
    </tbody>
</table>

<@page.pager pager=pageInfo css="margin:0;text-align:left;width: 100%;" />