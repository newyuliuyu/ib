<#macro pager id="pager" pager={"pageNum":"1","pages":"0","pageSize":"15","total":"0"} css="margin-top: 20px; text-align: center;">
<div class="row noMargin">
<div class="col-xl-12 pager-container pagination">
  <div id="${id}"  class="pager" style="${css}">
    <input type="hidden" id="pageNum" value="${(pager.pageNum)!1}">
    <input type="hidden" id="pageCount" value="${(pager.pages)!0}">
    <input type="hidden" id="pageSize" value="${(pager.pageSize)!10}">
    <input type="hidden" id="totalRows" value="${(pager.total)!0}">
  </div>
</div>
</div>
</#macro>