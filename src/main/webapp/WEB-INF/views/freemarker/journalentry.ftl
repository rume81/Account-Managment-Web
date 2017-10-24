<script type="text/javascript" src="${rc.contextPath}/js/custome/journalentry.js"></script>
<div class="container">
	<form name="journal_entry" id="journal_entry" action="${rc.contextPath}/journalsave" method="post">
    <div class="col-sm-12 journal_entry">
        <div class="col-sm-12 transfer_slip">
            <span class="">振替伝票</span>
            <input type="text" name="txtYear" id="txtYear" class="year" value="<#if FURIDEN_MODE==2>${searchjournal[0].yyyy!''}<#else>${year!''}</#if>">
            <input type="hidden" name="txtJournalNumber" id="txtJournalNumber" value="<#if FURIDEN_MODE==2>${searchjournal[0].je_number!''}</#if>">
            <input type="hidden" name="FURIDEN_MODE" id="FURIDEN_MODE" value="${FURIDEN_MODE}">
            <span class="">年</span>
            <input type="text" name="txtMonth" id="txtMonth" class="month" value="<#if FURIDEN_MODE==2>${searchjournal[0].mm!''}<#else>${month!''}</#if>">
            <span class="">月</span>
            <input type="text" class="day" id="txtDay" name="txtDay" value="<#if FURIDEN_MODE==2>${searchjournal[0].dd!''}<#else>${day!''}</#if>">
            <span class="">日</span>
            <button type="button" class="btn btn-default" onclick="saveJournal('${rc.contextPath}',${FURIDEN_MODE!'1'})">登録</button>
            <button type="button" class="btn btn-default" onclick="saveJournal('${rc.contextPath}',${FURIDEN_MODE!'2'})">登録2</button>
            <button type="button" class="btn btn-default">印刷</button>
            <button type="button" class="btn btn-default" onclick="location.href='${rc.contextPath}/'">閉じる</button>
            <button type="button" class="btn btn-default">挿入</button>
            <button type="button" class="btn btn-default">次の伝票</button>
            <button type="button" class="btn btn-default">前の伝票</button>
            <button type="button" class="btn btn-default">全行コピー</button>
            <button type="button" class="btn btn-default">全行貼付</button>
            <button type="button" class="btn btn-default">全行消去</button>
            <button type="button" class="btn btn-default">伝票削除</button> 
        </div>
        <div class="col-sm-12 slip_bumber">
            <span class="">伝票番号</span>
            <input type="text" name="txtNo" class="t_slip_number" id="txtNo" value="<#if FURIDEN_MODE==2>${searchjournal[0].s_number!''}</#if>">
            <select class="" id="cmbDebCode" name="cmbDebCode" onchange="BumonSansho('${rc.contextPath}',this.value)">
                <#list devlist as dev>
                <option value="${dev.devcode}">${dev.devcode}</option>
                </#list>
            </select>
            <input type="text" class="" id="txtDevName" name="txtDevName" value="${selectedDev.devname!''}">
            <button type="button" class="btn btn-default">取引先登録</button>
            <button type="button" class="btn btn-default">金額のみクリア</button>
            <button type="button" class="btn btn-default">伝票コピー</button>
            <select class="" id="cmbSlipCopy" name="cmbSlipCopy">
            	<#list journalCopy as journ>
                <option value="${journ.s_number!''}">${journ.s_number!''}</option>
                </#list>
            </select>
        </div>
        <div class="col-sm-12 journal_heading">
            <ul class="h_project">
                <li class="l_number">行番</li>
                <li class="debit_account">借方勘定科目</li>
                <li class="debit_amount">借方金額</li>
                <li class="credit_account">貸方勘定科目</li>
                <li class="credit_amount">貸方金額</li>
                <li class="suppliers">取引先</li>
            </ul>
            <ul class="h_debit_details">
                <li class="dr_ac_details">借方勘定細目</li>
                <li class="dr_tax">消費税</li>
                <li class="cr_ac_details">貸方勘定細目</li>
                <li class="cr_tax">消費税</li>
                <li class="project_details">プロジェクト</li>
            </ul>
            <ul class="remarks">
                <li class="">摘　　　　　　　　要</li>
            </ul>
        </div>
        <#assign GY=lineNum/>
        <#assign GY01=GY/>
        <#assign GY=GY+1/>
        <div id="tblContainer" class="col-sm-12" style="max-height:500px;overflow-y:scroll">
        	<#if FURIDEN_MODE!=2>
        		<input type="hidden" id="linenum" name="linenum" value="${GY}"/>
		        <div class="journal_details" id="journal_details_${GY}">
		            <ul class="project">
		                <li class="i_line_number">
		                    <input type="text" name="txtLabelNo" id="txtLabelNo_${GY}" class="form-control" value="${GY01}" readonly/>
		                </li>
		                <li class="dr_sub_code">
		                    <select class="form-control" name="cmbDebitAccount" id="cmbDebitAccount_${GY}" onchange="getDetails('${rc.contextPath}',this.value,1,'${GY}'); addNewRow('${GY}');">
		                    	<option value=""></option>
		                    	<#list devSel as dev>
		                        <option value="${dev.agkey!''}">${dev.agkey!''}&nbsp;&nbsp;${dev.acname!''}&nbsp;&nbsp;${dev.acsubname!''}</option>
		                        </#list>
		                    </select>
		                </li>
		                <li class="i_drsub_code">
		                    <input type="text" class="form-control" name="txtDebitAccode" id="txtDebitAccode_${GY}" onkeyup="addNewRow('${GY}')" readonly/>
		                </li>
		                <li class="drsub_name">
		                    <input type="text" name="txtDebitAccount" id="txtDebitAccount_${GY}" class="form-control" onkeyup="addNewRow('${GY}')" readonly/>
		                </li>
		                <li class="dr_amount">
		                    <input type="text" name="txtDevitAmount" id="txtDevitAmount_${GY}" class="form-control"  onkeyup="setValue('${GY}'); addNewRow('${GY}')" style="text-align: right"/>
		                </li>
		                <li class="cr_course_code">
		                    <select name="cmbCreditAccount" id="cmbCreditAccount_${GY}" class="form-control" onchange="getDetails('${rc.contextPath}',this.value,2,'${GY}');addNewRow('${GY}')">
		                    	<option value=""></option>
		                        <#list devSel as dev>
		                        <option value="${dev.agkey!''}">${dev.agkey!''}&nbsp;&nbsp;${dev.acname!''}&nbsp;&nbsp;${dev.acsubname!''}</option>
		                        </#list>
		                    </select>
		                </li>
		                <li class="i_cr_course_code">
		                    <input type="text" name="txtCreditAccode" id="txtCreditAccode_${GY}" class="form-control" onkeyup="addNewRow('${GY}')" readonly/>
		                </li>
		                <li class="cr_course_name">
		                    <input type="text" name="txtCreditAccount" id="txtCreditAccount_${GY}" class="form-control" onkeyup="addNewRow('${GY}')" readonly/>
		                </li>
		                <li class="cr_amount">
		                    <input type="text" name="txtCreditAmount" id="txtCreditAmount_${GY}" class="form-control" onkeyup="addNewRow('${GY}')" style="text-align: right"/>
		                </li>
		                <li class="customer_code">
		                    <select name="cmbVendor" id="cmbVendor_${GY}" class="form-control" onchange="getVendorDetails('${rc.contextPath}',this.value,'101','${GY}'); addNewRow('${GY}')">
		                    	<option value=""></option>
		                        <#list vendors as vend>
		                        <option value="${vend.vendorcode!''}">${vend.vendorcode!''}&nbsp;&nbsp;${vend.vendorname!''}</option>
		                        </#list>
		                    </select>
		                </li>
		                <li class="customer_name">
		                    <input type="text" name="txtVendor" id="txtVendor_${GY}" class="form-control" onkeyup="addNewRow('${GY}')"/>
		                </li>
		            </ul>
		            <ul class="debit_details">
		                <li class="dr_sub_code">
		                    <input type="text" name="txtDebitAccountSub" id="txtDebitAccountSub_${GY}" class="form-control" onkeyup="addNewRow('${GY}')" readonly/>
		                </li>
		                <li class="dr_sub_name">
		                    <input type="text" name="txtDebitAccountSub2" id="txtDebitAccountSub2_${GY}" class="form-control" onkeyup="addNewRow('${GY}')" readonly/>
		                </li>
		                <li class="dr_tax">
		                    <select name="cmbDebitTax" id="cmbDebitTax_${GY}" class="form-control" onchange="addNewRow('${GY}')">
		                    	<option value=""></option>
		                        <#list debTax as tax>
		                        <option value="${tax.code!''}">${tax.code!''}&nbsp;&nbsp;${tax.name!''}</option>
		                        </#list>
		                    </select>
		                </li>
		                <li class="copy">
		                    <button type="button" class="btn btn-default">Copy</button>
		                </li>
		                <li class="cr_specific_code">
		                    <input type="text" name="txtCreditAccountSub" id="txtCreditAccountSub_${GY}" class="form-control" onkeyup="addNewRow('${GY}')" readonly/>
		                </li>
		                <li class="cr_specific_name">
		                    <input type="text" name="txtCreditAccountSub2" id="txtCreditAccountSub2_${GY}" class="form-control" onkeyup="addNewRow('${GY}')" readonly/>
		                </li>
		                <li class="cr_tax">
		                    <select name="cmbDebitTax2" id="cmbDebitTax2_${GY}" class="form-control" onchange="addNewRow('${GY}')">
		                    	<option value=""></option>
		                        <#list debTax as tax>
		                        <option value="${tax.code!''}">${tax.code!''}&nbsp;&nbsp;${tax.name!''}</option>
		                        </#list>
		                    </select>
		                </li>
		                <li class="paste">
		                    <button type="button" class="btn btn-default">Paste</button>
		                </li>
		                <li class="prjcode">
		                    <select name="cmbProject" id="cmbProject_${GY}" class="form-control" onchange="getProjectDetails('${rc.contextPath}',this.value,'${GY}'); addNewRow('${GY}')">
		                    	<option value=""></option>
		                        <#list prjlist as prj>
		                        <option value="${prj.prjcode!''}">${prj.prjcode!''}&nbsp;&nbsp;${prj.prjname!''}</option>
		                        </#list>
		                    </select>
		                </li>
		                <li class="prjname">
		                    <input type="text" name="txtProject" id="txtProject_${GY}" class="form-control" onkeyup="addNewRow('${GY}')"/>
		                </li>
		            </ul>
		            <ul class="summary">
		                <li class="summary_code">
		                    <select name="cmbDescription" id="cmbDescription_${GY}" class="form-control" onchange="getDescriptionDetails('${rc.contextPath}',this.value,'${GY}'); addNewRow('${GY}')">
		                    	<option value=""></option>
		                    	<#list description as desc>
		                        <option value="${desc.desid!''}">${desc.descode!''}&nbsp;&nbsp;${desc.abstractname!''}</option>
		                       	</#list>
		                    </select>
		                </li>
		                <li class="summary_name">
		                    <input type="text" name="txtDescription" id="txtDescription_${GY}" class="form-control" onkeyup="addNewRow('${GY}')"/>
		                </li>
		            </ul>
		        	<hr>
		        </div>
	        <#else>
	        	<input type="hidden" id="linenum" name="linenum" value="${lineNum}"/>	   
	        	<#list searchjournal as journal> 
	        		<#assign GY=journal.l_number/>	
			     	<div class="journal_details" id="journal_details_${GY}">
			            <ul class="project">
			                <li class="i_line_number">
			                    <input type="text" name="txtLabelNo" id="txtLabelNo_${GY}" class="form-control" value="${journal.l_number!''}" readonly/>
			                </li>
			                <li class="dr_sub_code">
			                    <select class="form-control" name="cmbDebitAccount" id="cmbDebitAccount_${GY}" onchange="getDetails('${rc.contextPath}',this.value,1,'${GY}');">
			                    	<option value=""></option>
			                    	<#list devSel as dev>
			                        <option value="${dev.agkey!''}" <#if dev.agkey==(journal.draccode+journal.dracsubcode)>selected</#if>>${dev.agkey!''}&nbsp;&nbsp;${dev.acname!''}&nbsp;&nbsp;${dev.acsubname!''}</option>
			                        </#list>
			                    </select>
			                </li>
			                <li class="i_drsub_code">
			                    <input type="text" class="form-control" name="txtDebitAccode" id="txtDebitAccode_${GY}" value="${journal.draccode!''}"  readonly/>
			                </li>
			                <li class="drsub_name">
			                    <input type="text" name="txtDebitAccount" id="txtDebitAccount_${GY}" class="form-control" value="${journal.dracname!''}"  readonly/>
			                </li>
			                <li class="dr_amount">
			                    <input type="text" name="txtDevitAmount" id="txtDevitAmount_${GY}" class="form-control"  onkeyup="setValue('${GY}');" value="${journal.dramount!''}" style="text-align: right"/>
			                </li>
			                <li class="cr_course_code">
			                    <select name="cmbCreditAccount" id="cmbCreditAccount_${GY}" class="form-control" onchange="getDetails('${rc.contextPath}',this.value,2,'${GY}');">
			                    	<option value=""></option>
			                        <#list devSel as dev>
			                        <option value="${dev.agkey!''}" <#if dev.agkey==(journal.craccode+journal.cracsubcode)>selected</#if>>${dev.agkey!''}&nbsp;&nbsp;${dev.acname!''}&nbsp;&nbsp;${dev.acsubname!''}</option>
			                        </#list>
			                    </select>
			                </li>
			                <li class="i_cr_course_code">
			                    <input type="text" name="txtCreditAccode" id="txtCreditAccode_${GY}" class="form-control" value="${journal.craccode!''}"  readonly/>
			                </li>
			                <li class="cr_course_name">
			                    <input type="text" name="txtCreditAccount" id="txtCreditAccount_${GY}" class="form-control" value="${journal.cracname!''}"  readonly/>
			                </li>
			                <li class="cr_amount">
			                    <input type="text" name="txtCreditAmount" id="txtCreditAmount_${GY}" class="form-control" value="${journal.cramount!''}" style="text-align: right"/>
			                </li>
			                <li class="customer_code">
			                    <select name="cmbVendor" id="cmbVendor_${GY}" class="form-control" onchange="getVendorDetails('${rc.contextPath}',this.value,'101','${GY}');">
			                    	<option value=""></option>
			                        <#list vendors as vend>
			                        <option value="${vend.vendorcode!''}" <#if vend.vendorcode==journal.vendorcode>selected</#if>>${vend.vendorcode!''}&nbsp;&nbsp;${vend.vendorname!''}</option>
			                        </#list>
			                    </select>
			                </li>
			                <li class="customer_name">
			                    <input type="text" name="txtVendor" id="txtVendor_${GY}" class="form-control" value="${journal.vendorname!''}"/>
			                </li>
			            </ul>
			            <ul class="debit_details">
			                <li class="dr_sub_code">
			                    <input type="text" name="txtDebitAccountSub" id="txtDebitAccountSub_${GY}" class="form-control" value="${journal.dracsubcode!''}" readonly/>
			                </li>
			                <li class="dr_sub_name">
			                    <input type="text" name="txtDebitAccountSub2" id="txtDebitAccountSub2_${GY}" class="form-control" value="${journal.dracsubname!''}" readonly/>
			                </li>
			                <li class="dr_tax">
			                    <select name="cmbDebitTax" id="cmbDebitTax_${GY}" class="form-control">
			                    	<option value=""></option>
			                        <#list debTax as tax>
			                        <option value="${tax.code!''}" <#if tax.code==journal.drctax>selected</#if>>${tax.code!''}&nbsp;&nbsp;${tax.name!''}</option>
			                        </#list>
			                    </select>
			                </li>
			                <li class="copy">
			                    <button type="button" class="btn btn-default">Copy</button>
			                </li>
			                <li class="cr_specific_code">
			                    <input type="text" name="txtCreditAccountSub" id="txtCreditAccountSub_${GY}" class="form-control" value="${journal.cracsubcode!''}" readonly/>
			                </li>
			                <li class="cr_specific_name">
			                    <input type="text" name="txtCreditAccountSub2" id="txtCreditAccountSub2_${GY}" class="form-control" value="${journal.cracsubname!''}" readonly/>
			                </li>
			                <li class="cr_tax">
			                    <select name="cmbDebitTax2" id="cmbDebitTax2_${GY}" class="form-control">
			                    	<option value=""></option>
			                        <#list debTax as tax>
			                        <option value="${tax.code!''}" <#if tax.code==journal.crctax>selected</#if>>${tax.code!''}&nbsp;&nbsp;${tax.name!''}</option>
			                        </#list>
			                    </select>
			                </li>
			                <li class="paste">
			                    <button type="button" class="btn btn-default">Paste</button>
			                </li>
			                <li class="prjcode">
			                    <select name="cmbProject" id="cmbProject_${GY}" class="form-control" onchange="getProjectDetails('${rc.contextPath}',this.value,'${GY}');">
			                    	<option value=""></option>
			                        <#list prjlist as prj>
			                        <option value="${prj.prjcode!''}" <#if prj.prjcode==journal.prjcode>selected</#if>>${prj.prjcode!''}&nbsp;&nbsp;${prj.prjname!''}</option>
			                        </#list>
			                    </select>
			                </li>
			                <li class="prjname">
			                    <input type="text" name="txtProject" id="txtProject_${GY}" class="form-control" value="${journal.prjname!''}"/>
			                </li>
			            </ul>
			            <ul class="summary">
			                <li class="summary_code">
			                    <select name="cmbDescription" id="cmbDescription_${GY}" class="form-control" onchange="getDescriptionDetails('${rc.contextPath}',this.value,'${GY}');">
			                    	<option value=""></option>
			                    	<#list description as desc>
			                        <option value="${desc.desid!''}" <#if (desc.desid?string)==journal.descode>selected</#if>>${desc.descode!''}&nbsp;&nbsp;${desc.abstractname!''}</option>
			                       	</#list>
			                    </select>
			                </li>
			                <li class="summary_name">
			                    <input type="text" name="txtDescription" id="txtDescription_${GY}" class="form-control" value="${journal.desname!''}"/>
			                </li>
			            </ul>
			        	<hr>
			        </div>
		        </#list>
	     	</#if>
	     	
	     	
	     	
		</div> 
    </div>
    </form>
</div>

<div id="tblContainer_backup" class="col-sm-12" style="height:200px; display:none;">
    <div class="journal_details" id="journal_details">
        <ul class="project">
            <li class="i_line_number">
                <input type="text" name="txtLabelNo" id="txtLabelNo_#lno#" class="form-control" value="0" readonly/>
            </li>
            <li class="dr_sub_code">
                <select class="form-control" name="cmbDebitAccount" id="cmbDebitAccount_#lno#" onchange="getDetails('${rc.contextPath}',this.value,1,'#lno#');addNewRow('#lno#')">
                	<option value=""></option>
                	<#list devSel as dev>
                    <option value="${dev.agkey!''}">${dev.agkey!''}&nbsp;&nbsp;${dev.acname!''}&nbsp;&nbsp;${dev.acsubname!''}</option>
                    </#list>
                </select>
            </li>
            <li class="i_drsub_code">
                <input type="text" class="form-control" name="txtDebitAccode" id="txtDebitAccode_#lno#" onkeyup="addNewRow('#lno#')" readonly/>
            </li>
            <li class="drsub_name">
                <input type="text" name="txtDebitAccount" id="txtDebitAccount_#lno#" class="form-control" onkeyup="addNewRow('#lno#')" readonly/>
            </li>
            <li class="dr_amount">
                <input type="text" name="txtDevitAmount" id="txtDevitAmount_#lno#" class="form-control"  onkeyup="setValue('#lno#'); addNewRow('#lno#')" style="text-align: right"/>
            </li>
            <li class="cr_course_code">
                <select name="cmbCreditAccount" id="cmbCreditAccount_#lno#" class="form-control" onchange="getDetails('${rc.contextPath}',this.value,2,'#lno#'); addNewRow('#lno#')">
                	<option value=""></option>
                    <#list devSel as dev>
                    <option value="${dev.agkey!''}">${dev.agkey!''}&nbsp;&nbsp;${dev.acname!''}&nbsp;&nbsp;${dev.acsubname!''}</option>
                    </#list>
                </select>
            </li>
            <li class="i_cr_course_code">
                <input type="text" name="txtCreditAccode" id="txtCreditAccode_#lno#" class="form-control" onkeyup="addNewRow('#lno#')" readonly/>
            </li>
            <li class="cr_course_name">
                <input type="text" name="txtCreditAccount" id="txtCreditAccount_#lno#" class="form-control" onkeyup="addNewRow('#lno#')" readonly/>
            </li>
            <li class="cr_amount">
                <input type="text" name="txtCreditAmount" id="txtCreditAmount_#lno#" class="form-control" onkeyup="addNewRow('#lno#')" style="text-align: right"/>
            </li>
            <li class="customer_code">
                <select name="cmbVendor" id="cmbVendor_#lno#" class="form-control" onchange="getVendorDetails('${rc.contextPath}',this.value,'101','#lno#'); addNewRow('#lno#')">
                	<option value=""></option>
                    <#list vendors as vend>
                    <option value="${vend.vendorcode!''}">${vend.vendorcode!''}&nbsp;&nbsp;${vend.vendorname!''}</option>
                    </#list>
                </select>
            </li>
            <li class="customer_name">
                <input type="text" name="txtVendor" id="txtVendor_#lno#" class="form-control" onkeyup="addNewRow('#lno#')" readonly/>
            </li>
        </ul>
        <ul class="debit_details">
            <li class="dr_sub_code">
                <input type="text" name="txtDebitAccountSub" id="txtDebitAccountSub_#lno#" class="form-control" onkeyup="addNewRow('#lno#')" readonly/>
            </li>
            <li class="dr_sub_name">
                <input type="text" name="txtDebitAccountSub2" id="txtDebitAccountSub2_#lno#" class="form-control" onkeyup="addNewRow('#lno#')" readonly/>
            </li>
            <li class="dr_tax">
                <select name="cmbDebitTax" id="cmbDebitTax_#lno#" class="form-control" onkeyup="addNewRow('#lno#')">
                	<option value=""></option>
                    <#list debTax as tax>
                    <option value="${tax.code!''}">${tax.code!''}&nbsp;&nbsp;${tax.name!''}</option>
                    </#list>
                </select>
            </li>
            <li class="copy">
                <button type="button" class="btn btn-default">Copy</button>
            </li>
            <li class="cr_specific_code">
                <input type="text" name="txtCreditAccountSub" id="txtCreditAccountSub_#lno#" class="form-control" onkeyup="addNewRow('#lno#')" readonly/>
            </li>
            <li class="cr_specific_name">
                <input type="text" name="txtCreditAccountSub2" id="txtCreditAccountSub2_#lno#" class="form-control" onkeyup="addNewRow('#lno#')" readonly/>
            </li>
            <li class="cr_tax">
                <select name="cmbDebitTax2" id="cmbDebitTax2_#lno#" class="form-control" onchange="addNewRow('#lno#')">
                	<option value=""></option>
                    <#list debTax as tax>
                    <option value="${tax.code!''}">${tax.code!''}&nbsp;&nbsp;${tax.name!''}</option>
                    </#list>
                </select>
            </li>
            <li class="paste">
                <button type="button" class="btn btn-default">Paste</button>
            </li>
            <li class="prjcode">
                <select name="cmbProject" id="cmbProject_#lno#" class="form-control" onchange="getProjectDetails('${rc.contextPath}',this.value,'#lno#'); addNewRow('#lno#')">
                	<option value=""></option>
                    <#list prjlist as prj>
                    <option value="${prj.prjcode!''}">${prj.prjcode!''}&nbsp;&nbsp;${prj.prjname!''}</option>
                    </#list>
                </select>
            </li>
            <li class="prjname">
                <input type="text" name="txtProject" id="txtProject_#lno#" class="form-control" onkeyup="addNewRow('#lno#')" readonly/>
            </li>
        </ul>
        <ul class="summary">
            <li class="summary_code">
                <select name="cmbDescription" id="cmbDescription_#lno#" class="form-control" onchange="getDescriptionDetails('${rc.contextPath}',this.value,'#lno#'); addNewRow('#lno#')">
                	<option value=""></option>
                	<#list description as desc>
                    <option value="${desc.desid!''}">${desc.descode!''}&nbsp;&nbsp;${desc.abstractname!''}</option>
                   	</#list>
                </select>
            </li>
            <li class="summary_name">
                <input type="text" name="txtDescription" id="txtDescription_#lno#" class="form-control" onkeyup="addNewRow('#lno#')" readonly/>
            </li>
        </ul>
        <hr>
    </div>
    
</div> 