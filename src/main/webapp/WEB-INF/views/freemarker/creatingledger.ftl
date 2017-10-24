<script type="text/javascript" src="${rc.contextPath}/js/custome/ledger.js"></script>
<div class="col-sm-10 col-sm-offset-1 main create_ledger">
<form name="ledger" id="ledger" action="${rc.contextPath}/createLedger" method="post">
	<input type="hidden" id="SHORI" name="SHORI" value="1"/>
    <div class="col-sm-12">
        <div class="col-sm-4">
            <p>部門の選択</p>
            <select class="form-control" name="cmbBumon" id="cmbBumon">
                <#list devcode as dev>
                <option value="${dev.devcode}">${dev.devcode} &nbsp; &nbsp; ${dev.devname}</option>
                </#list>
            </select>
        </div>
        <div class="col-sm-4">
            <p>並替選択</p>
            <select class="form-control" name="cmbNarabi" id="cmbNarabi">
                <option value="1">1 &nbsp; &nbsp; 入力順</option>
                <option value="2">2 &nbsp; &nbsp; 日付順</option>
            </select>
        </div>
        <div class="col-sm-4">
            <p>印刷頁条件</p>
            <select class="form-control" name="cmbPrjoken" id="cmbPrjoken">
                <option value="1">1 &nbsp; &nbsp; 明細がないページを印刷しない</option>
                <option value="2">2 &nbsp; &nbsp; 明細がないページも印刷する</option>
            </select>
        </div>
    </div>
    
    <div class="col-sm-12 subject_range">
        <div class="col-sm-12">
            <p>科目範囲の選択</p>
        </div>
        <div class="col-sm-4">
            <select class="form-control" name="cmbAcFrom" id="cmbAcFrom">            
	            <#list acfrom as ac>
	            <option value="${ac.accode}">${ac.accode} &nbsp; ${ac.acname}</option>
	            </#list>
        </select>
        </div>
        <div class="col-sm-1 no_padding">
            <p>から</p>
        </div>
        <div class="col-sm-4">
            <select class="form-control" name="cmbAcTo" id="cmbAcTo">
            	<#list acfrom as ac>
	            <option value="${ac.accode}">${ac.accode} &nbsp; ${ac.acname}</option>
	            </#list>
        	</select>
        </div>
        <div class="col-sm-1 no_padding">
            <p>まで</p>
        </div>
    </div>
    <div class="col-sm-12 project">
        <div class="col-sm-4">
            <p>プロジェクト</p>
        </div>
        <div class="col-sm-5">
            <select class="form-control" name="cmbPrjCode" id="cmbPrjCode">
                <option value=""></option>
                <#list prjcode as pcode>
                <option value="${pcode.prjcode}">${pcode.prjcode} &nbsp; ${pcode.prjname}</option>
                </#list>
            </select>
        </div>
    </div>
    <div class="col-sm-12 select_course">
        <div class="col-sm-12">
            <p>科目の個別選択</p>
        </div>
        <div class="col-sm-4">
            <select class="form-control" name="cmbAcSel1" id="cmbAcSel1">
                <option value=""></option>
                <#list acfrom as ac>
	            <option value="${ac.accode}">${ac.accode} &nbsp; ${ac.acname}</option>
	            </#list>
            </select>
        </div>
        <div class="col-sm-4">
            <select class="form-control" name="cmbAcSel2" id="cmbAcSel2">
                <option value=""></option>
                <#list acfrom as ac>
	            <option value="${ac.accode}">${ac.accode} &nbsp; ${ac.acname}</option>
	            </#list>
            </select>
        </div>
        <div class="col-sm-4">
            <select class="form-control" name="cmbAcSel3" id="cmbAcSel3">
                <option value=""></option>
                <#list acfrom as ac>
	            <option value="${ac.accode}">${ac.accode} &nbsp; ${ac.acname}</option>
	            </#list>
            </select>
        </div>
        <div class="col-sm-4">
            <select class="form-control" name="cmbAcSel4" id="cmbAcSel4">
                <option value=""></option>
                <#list acfrom as ac>
	            <option value="${ac.accode}">${ac.accode} &nbsp; ${ac.acname}</option>
	            </#list>
            </select>
        </div>
        <div class="col-sm-4">
            <select class="form-control" name="cmbAcSel5" id="cmbAcSel5">
                <option value=""></option>
                <#list acfrom as ac>
	            <option value="${ac.accode}">${ac.accode} &nbsp; ${ac.acname}</option>
	            </#list>
            </select>
        </div>
        <div class="col-sm-4">
            <select class="form-control" name="cmbAcSel6" id="cmbAcSel6">
                <option value=""></option>
                <#list acfrom as ac>
	            <option value="${ac.accode}">${ac.accode} &nbsp; ${ac.acname}</option>
	            </#list>
            </select>
        </div>
        <div class="col-sm-3 col-sm-offset-5">
            <p>細目まで</p>
        </div>
        <div class="col-sm-4">
            <select class="form-control" name="cmbSaiSel" id="cmbSaiSel">
                <option value=""></option>
                <#list devsel as dev>
                <option value="${dev.agkey}">${dev.agkey} &nbsp; ${dev.accode} &nbsp; ${dev.acname} &nbsp; ${dev.acsubcode} &nbsp; ${dev.acsubname}</option>
                </#list>
            </select>
        </div>
    </div>
    <div class="col-sm-12 output_date">
        <div class="col-sm-8">
            <p>出力月日の選択</p>
            <div class="col-sm-4 no_padding">
                <input type="text" class="form-control" name="txtTukiFrom" id="txtTukiFrom" value="4">
            </div>
            <div class="col-sm-2">
                <p class="text_left">月</p>
            </div>
            <div class="col-sm-4 no_padding">
                <input type="text" class="form-control" name="txtHiFrom" id="txtHiFrom" value="1">
            </div>
            <div class="col-sm-2 no_padding_right">
                <p>日から</p>
            </div>
            <div class="col-sm-4 no_padding">
                <input type="text" class="form-control" name="txtTukiTo" id="txtTukiTo" value="3">
            </div>
            <div class="col-sm-2">
                <p class="text_left">月</p>
            </div>
            <div class="col-sm-4 no_padding">
                <input type="text" class="form-control" name="txtHiTo" id="txtHiTo" value="31">
            </div>
            <div class="col-sm-2 no_padding_right">
                <p>日まで</p>
            </div>
        </div>
    </div>
    <div class="col-sm-12">
        <div class="col-sm-4">
            <button type="button" class="btn btn-default" name="cmdOriginalBook" onclick="subledger('${rc.contextPath}',1)">元　　帳</button>
        </div>
        <div class="col-sm-4">
            <button type="button" class="btn btn-default" name="cmdCashBook" onclick="subledger('${rc.contextPath}',2)">現金出納簿</button>
        </div>
    </div>
    <div class="col-sm-12">
        <div class="col-sm-4">
            <button type="button" class="btn btn-default" name="cmdSubsidiaryLedger" onclick="subledger('${rc.contextPath}',3)">補助元帳</button>
        </div>
        <div class="col-sm-4">
            <button type="button" class="btn btn-default" name="cmdCashierAppendix" onClick="subledger('${rc.contextPath}',4)">現金出納付表</button>
        </div>
        <div class="col-sm-4">
            <button type="button" class="btn btn-default"  onclick="location.href='${rc.contextPath}/'" name="cmdClose">閉じる</button>
        </div>
    </div>
    </form>
</div>