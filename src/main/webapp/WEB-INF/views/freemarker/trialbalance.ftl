<script type="text/javascript" src="${rc.contextPath}/js/custome/trialbalance.js"></script>
<div class="col-sm-5 col-sm-offset-3 main trial_calculation">
	<form name="trialbalance" id="trialbalance" action="${rc.contextPath}/trialbalance" method="post">
	    <div class="col-sm-12">
	        <div class="col-sm-6">
	            <p>部門の選択</p>
	            <select class="form-control" name="cmbDepartment" id="cmbDepartment">
	                <#list devlist as dev>
	                <option value="${dev.devcode}">${dev.devcode}&nbsp;&nbsp;${dev.devname!''}</option>
	                </#list>
	            </select>
	        </div>
	    </div>
	    
	    <div class="col-sm-10 output_date">
	        <div class="col-sm-12 no_padding_right">
	            <p>出力月日の選択</p>
	        </div>
	        <div class="col-sm-4">
	        	<input type="hidden" id="SHORI" name="SHORI" value="1"/>
	            <input type="text" class="form-control" name="txtMonth" id="txtMonth" value="4">
	        </div>
	        <div class="col-sm-2 no_padding">
	            <p class="text_left">月</p>
	        </div>
	        <div class="col-sm-4">
	            <input type="text" class="form-control" name="txtFromDay" id="txtFromDay" value="1">
	        </div>
	        <div class="col-sm-2 no_padding">
	            <p class="text_left">日から</p>
	        </div>
	        <div class="col-sm-4">
	            <input type="text" class="form-control" name="txtMonth2" id="txtMonth2" value="3"> 
	        </div>
	        <div class="col-sm-2 no_padding">
	            <p class="text_left">月</p>
	        </div>
	        <div class="col-sm-4">
	            <input type="text" class="form-control" name="txtUntilday" id="txtUntilday" value="31">
	        </div>
	        <div class="col-sm-2 no_padding">
	            <p class="text_left">日まで</p>
	        </div>
	    </div>
	    <div class="col-sm-12">
	        <div class="col-sm-6">
	            <button type="button" class="btn btn-default" name="cmdSubjects" onclick="TrialBalance('${rc.contextPath}',3)">科目別</button>
	        </div>
	        <div class="col-sm-6">
	            <button type="button" class="btn btn-default" name="cmdDetails" onclick="TrialBalance('${rc.contextPath}',1)">細目別</button>
	        </div>
	        <div class="col-sm-6">
	            <button type="button" class="btn btn-default" name="cmdProjectExpenses" onclick="TrialBalance('${rc.contextPath}',4)">プロジェクト別経費</button>
	        </div>
	    
	        <div class="col-sm-6">
	            <button type="button" class="btn btn-default" name="cmdBudget" onclick="TrialBalance('${rc.contextPath}',5)">プロジェクト別収支</button>
	        </div>
	        <div class="col-sm-6">
	            <button type="button" class="btn btn-default" name="cmdPartner" onclick="TrialBalance('${rc.contextPath}',2)">取引先別</button>
	        </div>
	        <div class="col-sm-6">
	            <button type="button" class="btn btn-default" name="cmdClose" onclick="location.href='${rc.contextPath}/'"><img src="images/exit.png" alt="exit"/>  閉じる</button>
	        </div>
	    </div>
	</form>
</div>