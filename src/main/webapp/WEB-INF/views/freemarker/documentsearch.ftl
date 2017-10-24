<script type="text/javascript" src="${rc.contextPath}/js/custome/journalentry.js"></script>
<div class="col-md-8 col-sm-8 col-md-offset-2 document_search main">
    <form name="documentsearch" id="documentsearch" action="${rc.contextPath}/documentsearch" method="post">
        <div class="col-md-9">
            <div class="col-sm-6">
                <p>部門の選択</p>
                <select class="form-control" name="cmbSelectDepartment" id="cmbSelectDepartment">
                    <#list devlist as dev>
                		<option value="${dev.devcode}">${dev.devcode}&nbsp;&nbsp;${dev.devname!''}</option>
                	</#list>
                </select>
            </div>
            <div class="col-md-12 slip_scope">
                <p>伝票番号の範囲</p>
                <div class="col-md-5 no_padding">
                    <input type="text" class="form-control" name="txtJournalFrom" id="txtJournalFrom" value="0">
                </div>
                <div class="col-md-1 no_padding">
                    <label class="">から</label>
                </div>
                <div class="col-md-5 no_padding">
                    <input type="text" class="form-control" name="txtJournalTo" id="txtJournalTo" value="999999">
                </div>
                <div class="col-md-1 no_padding">
                    <label class="">まで</label>
                </div>
            </div>
            <div class="col-md-12">
                <p>日付の範囲</p>
                <div class="col-md-3 no_padding">
                    <input type="text" class="form-control" name="txtYearFrom" id="txtYearFrom" value="1996">
                </div>
                <div class="col-md-1 no_padding">
                    <label class="">年</label>
                </div>
                <div class="col-md-3 no_padding">
                    <input type="text" class="form-control" name="txtMonthFrom" id="txtMonthFrom" value="4">
                </div>
                <div class="col-md-1 no_padding">
                    <label class="">月</label>
                </div>
                <div class="col-md-2 no_padding">
                    <input type="text" class="form-control" name="txtDayFrom" id="txtDayFrom" value="1">
                </div>
                <div class="col-md-2 no_padding">
                    <label class="">日</label>
                    <label>から</label>
                </div>
                <div class="col-md-3 no_padding">
                    <input type="text" class="form-control" name="txtYearTo" id="txtYearTo" value="2099">
                </div>
                <div class="col-md-1 no_padding">
                    <label class="">年</label>
                </div>
                <div class="col-md-3 no_padding">
                    <input type="text" class="form-control" name="txtMonthTo" id="txtMonthTo" value="3">
                </div>
                <div class="col-md-1 no_padding">
                    <label class="">月</label>
                </div>
                <div class="col-md-2 no_padding">
                    <input type="text" class="form-control" name="txtDayTo" id="txtDayTo" value="31">
                </div>
                <div class="col-md-2 no_padding">
                    <label class="">日</label>
                    <label>から</label>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <ul>
                <li>
                    <button type="button" class="btn btn-default" name="cmdJournalLog">履歴データ検索</button>
                </li>
                <li>
                    <button type="button" class="btn btn-default" name="cmdJournalSearch"  onclick="journalSearch('${rc.contextPath}')">検索開始</button>
                </li>
                <li>
                    <button type="button" class="btn btn-default" name="cmdJournalPrint">伝票印刷</button>
                </li>
                <li>
                    <button type="button" class="btn btn-default" name="cmdExit" onclick="location.href='${rc.contextPath}/'">閉じる</button>
                </li>
            </ul>
        </div>
        <div class="col-sm-11 amount_range">
            <div class="col-sm-12">
                <p>金額の範囲</p>
            </div>
            <div class="col-sm-5 no_padding_right ">
                <input type="text" class="form-control" name="txtAmountRangeFrom" id="txtAmountRangeFrom" value="-99,999,999,999,999">
            </div>
            <div class="col-sm-1 no_padding">
                <label>から</label>
            </div>
            <div class="col-sm-5 no_padding_right">
                <input type="text" class="form-control" name="txtAmountrangeTo" id="txtAmountrangeTo" value="99,999,999,999,999">
            </div>
            <div class="col-sm-1 no_padding">
                <label>まで</label>
            </div>
        </div>
        <div class="col-sm-10">
            <div class="col-sm-12 no_padding_right">
                <p>科目範囲の選択</p>
            </div>
            <div class="col-sm-4 no_padding_right">
                <select class="form-control" name="cmbAccountSelect1" id="cmbAccountSelect1">
                    <option value=""></option>
                	<#list devSel as dev>
                    <option value="${dev.agkey!''}">${dev.agkey!''}&nbsp;&nbsp;${dev.acname!''}&nbsp;&nbsp;${dev.acsubname!''}</option>
                    </#list>
                </select>
            </div>
            <div class="col-sm-4 no_padding_right">
                <select class="form-control" name="cmbAccountSelect2" id="cmbAccountSelect2">
                    <option value=""></option>
                	<#list devSel as dev>
                    <option value="${dev.agkey!''}">${dev.agkey!''}&nbsp;&nbsp;${dev.acname!''}&nbsp;&nbsp;${dev.acsubname!''}</option>
                    </#list>
                </select>
            </div>
            <div class="col-sm-4 no_padding_right">
                <select class="form-control" name="cmbAccountSelect3" id="cmbAccountSelect3">
                    <option value=""></option>
                	<#list devSel as dev>
                    <option value="${dev.agkey!''}">${dev.agkey!''}&nbsp;&nbsp;${dev.acname!''}&nbsp;&nbsp;${dev.acsubname!''}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="col-sm-12 serach_summary">
            <p>摘要に含まれる文字列</p>
            <input type="text" class="form-control" name="txtMatchDescription" id="txtMatchDescription" style="text-align: left">
            <p>取引先名に含まれる文字列</p>
            <input type="text" class="form-control" name="txtMatchVendor" id="txtMatchVendor" style="text-align: left">
        </div>
    </form>
</div>