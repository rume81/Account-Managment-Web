<script type="text/javascript" src="${rc.contextPath}/js/custome/journalentry.js"></script>
<div class="col-md-6 col-sm-8 col-md-offset-3 make_journal main">
    <form name="journal_create" id="journal_create" action="${rc.contextPath}/journalcreate" method="post">
        <div class="col-md-6">
            <div class="form-group">
                <p>部門の選択</p>
                <select class="form-control" name="cmbDepartment" id="cmbDepartment">
                    <#list devlist as dev>
                		<option value="${dev.devcode}">${dev.devcode}&nbsp;&nbsp;${dev.devname!''}</option>
                	</#list>
                </select>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group">
                <p>並替選択</p>
                <select class="form-control" name="cmbRearrangement" id="cmbRearrangement">
                    <option value="1">1&nbsp;&nbsp;入力順</option>
                    <option value="2">2&nbsp;&nbsp;日付順</option>
                </select>
            </div>
        </div>
        <div class="col-md-11">
            <p class="col-md-11 no_padding">出力月日の選択</p>
            <div class="col-md-4 no_padding">
                <input type="text" class="form-control" name="txtMonth" id="txtMonth" value="4">
            </div>
            <p class="col-md-1 no_padding">月</p>
            <div class="col-md-4 no_padding">
                <input type="text" class="form-control" name="txtFromDay" id="txtFromDay" value="1">
            </div>
            <p class="col-md-2 text_left no_padding">日から</p>
            <div class="col-md-4 no_padding">
                <input type="text" class="form-control" name="txtMonth2" id="txtMonth2" value="3">
            </div>
            <p class="col-md-1 no_padding">月</p>
            <div class="col-md-4 no_padding">
                <input type="text" class="form-control" name="txtUntilday" id="txtUntilday" value="31">
            </div>
            <p class="col-md-2 text_left no_padding">日まで</p>
        </div>
        <div class="col-md-12">
            <p class="col-md-12 no_padding">伝票番号の範囲</p>
            <div class="col-md-4 no_padding">
                <input type="text" class="form-control" name="txtDocumentRangeFrom" id="txtDocumentRangeFrom" value="0">
            </div>
            <p class="col-md-2 no_padding">から</p>
            <div class="col-md-4 no_padding">
                <input type="text" class="form-control" name="txtDocumentRangeTo" id="txtDocumentRangeTo" value="999999">
            </div>
            <p class="col-md-2 no_padding">まで</p>
            <div class="col-md-6 no_padding">
                <button type="button" class="btn btn-default" name="cmdRun" id="cmdRun" onclick="createJournal('${rc.contextPath}')"><img src="images/execute.jpg" alt="execute" />  実　行</button>
            </div>
            <div class="col-md-6 no_padding">
                <button type="button" class="btn btn-default" name="cmdClose" onclick="location.href='${rc.contextPath}/'"><img src="images/exit.png" alt="exit" />  閉じる</button>
            </div>
        </div>
    </form>
</div>