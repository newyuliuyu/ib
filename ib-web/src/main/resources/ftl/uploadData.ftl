[<#list files as item>
<#if item_index != 0>
,
</#if>
{"old":"${item.old}","new":"${item.new}"}
</#list>]