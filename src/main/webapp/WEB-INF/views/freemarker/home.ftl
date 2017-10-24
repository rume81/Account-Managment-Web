<script type="text/javascript" src="${rc.contextPath}/js/custome/home.js"></script>
<script>
$(document).ready(function() {
		
})
</script>
<div class="body col-md-8 col-md-offset-2">
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/振替伝票  入力/1'" id="cmdJournalEntry"><img src="images/journalentry.png" alt="振替伝票  入力" />&nbsp;&nbsp; 振替伝票  入力</button>
    </div>
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/仕訳帳'" id="cmdJournalCreate"><img src="images/journalcreate.png" alt="仕　訳　帳" />&nbsp;&nbsp; 仕　訳　帳</button>
    </div>
    
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/振替伝票修正・印刷'" id="cmdJournalReviseOrPrint"><img src="images/journalsearch.png" alt="振替伝票　修正・印刷" />&nbsp;&nbsp; 振替伝票　修正・印刷</button>
    </div>
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/期首残高設定'" id="cmdOpeningBalance"><img src="images/openingbalance.png" alt="期首残高設定" />&nbsp;&nbsp; 期首残高設定</button>
    </div>
    
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/元帳'" id="cmdOriginalBook"><img src="images/ledger.png" alt="元  帳" />&nbsp;&nbsp; 元  帳</button>
    </div>
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/勘定科目メンテ'" id="cmdAccountMaintenance"><img src="images/accountmaintanence.png" alt="勘定科目メンテ" />&nbsp;&nbsp; 勘定科目メンテ</button>
    </div>
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/残高試算表'" id="cmdTrialBalance"><img src="images/trialbalance.png" alt="残高試算表" />&nbsp;&nbsp; 残高試算表</button>
    </div>
    <div class="col-md-6 vis_hidden">
        <button class="btn btn-default" type="button" onclick="" id="cmdCreatingBalance"><img src="images/trialbalance.png" alt="asdfs" />&nbsp;&nbsp; asdfs</button>
    </div>
    
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/摘要登録'" id="cmdAbstractRegistration"><img src="images/register.png" alt="摘要登録" />&nbsp;&nbsp; 摘要登録</button>
    </div>
    <div class="col-md-6 vis_hidden">
        <button class="btn btn-default" type="button" onclick="" id="cmdMonthlyReport"></button>
    </div>
    
    <div class="col-md-6 vis_hidden">
        <button class="btn btn-default" type="button" onclick="" id="cmdQuarterlyBudget"></button>
    </div>
    <div class="col-md-6 vis_hidden">
        <button class="btn btn-default" type="button" onclick="" id="cmdProjectCode"></button>
    </div>
    
    <div class="col-md-6 vis_hidden">
        
    </div>
    <div class="col-md-6">
        <button class="btn btn-default" type="button" onclick="location.href='${rc.contextPath}/user/logout'" id="cmdEnd"><img src="images/logout.png" alt="ログアウト" />&nbsp;&nbsp; ログアウト</button>
    </div>
</div>
        