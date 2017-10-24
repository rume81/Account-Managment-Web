<script type="text/javascript" src="${rc.contextPath}/js/custome/bgbalance.js"></script>

<div class="col-sm-12 main abstract_registration">
    <div class="col-sm-12">
        <div class="col-sm-3 no_padding">
            <p>摘要登録・修正</p>
        </div>
        <div class="col-sm-1 col-sm-offset-2">
            <button type="button" class="btn btn-default" onclick="location.href='${rc.contextPath}/'" name="cmdExit">閉じる</button>
        </div>
        <div class="col-sm-1 no_padding">
            <p>部門別 印刷</p>
        </div>
        <div class="col-sm-2">
            <select class="form-control" name="cmbDebCode" id="cmbDebCode">
                <#list devcode as dev>
                	<option value="${dev.devcode}">${dev.devcode} ${dev.devname}</option>
                </#list>
            </select>
        </div>
        <div class="col-sm-1">
            <button type="button" class="btn btn-default" name="cmdPrinting" id="cmdPrinting">印　刷</button>
        </div>
        <div class="col-sm-1">
            <button type="button" class="btn btn-default" name="addRow" id="addRow">add Row</button>
        </div>
    </div>
    <div class="col-sm-12 abstract_summary">
        <ul class="abstract_level">
            <li class="summary_code">
                <p>摘要コード</p>
            </li>
            <li class="papaer_content">
                <p>摘　要　の　内　容</p>
            </li>
            <li class="debit_account">
                <p>借方勘定科目</p>
                <p>借方勘定細目</p>
            </li>
            <li class="debit_amount">
                <p>借方金額</p>
                <p>消費税</p>
            </li>
            <li class="credit_account">
                <p>貸方勘定科目</p>
                <p>貸方勘定細目</p>
            </li>
            <li class="credit_amount">
                <p>貸方金額</p>
                <p>消費税</p>
            </li>
        </ul>
        <form action="${rc.contextPath}/updateDescription" method="post">
        <#list description as des>
        	<input type="hidden" name="txtDesid" id="txtDesid_${des.desid}" value="${des.desid}" />
            <ul class="department">
                <li class="devcode">
                    <select class="form-control" name="cmbDebCode1" id="cmbDebCode1_${des.desid}">
                        <#list devcode as dev>
		                	<option value="${dev.devcode}">${dev.devcode} ${dev.devname}</option>
		                </#list>
                    </select>
                </li>
                <li class="devname">
                    <input type="text" class="form-control" id="txtDevname1_${des.desid}" name="txtDevname1" value="${des.devname!''}"/>
                </li>
            </ul>
            <ul class="summary_input">
                <li class="summary_code">
                    <input type="text" class="form-control" id="txtDescode_${des.desid}" name="txtDescode" value="${des.descode!''}" /> 
                </li>
                <li class="summary_name">
                    <textarea rows="2" cols="30" class="form-control" id="txtDesname_${des.desid}" name="txtDesname">${des.desname!''}</textarea>
                </li>
                <li class="debit_subcode">
                    <select class="form-control" name="cmbDraccode" id="cmbDraccode_${des.desid}">
                    	<#list devSelect as devsel>
                        	<option value="${devsel.agkey}" <#if des.draccode==devsel.agkey>selected</#if>> ${devsel.agkey} ${devsel.acname} ${devsel.acsubname}</option>
                        </#list>
                    </select>
                    <input type="text" class="form-control" name="txtDracsubcode" id="txtDracsubcode_${des.desid}" value="${des.dracsubcode!''}"/>
                </li>
                <li class="debit_subname">
                    <input type="text" class="form-control" name="txtDracname" id="txtDracname_${des.desid}" value="${des.dracname!''}"/>
                    <input type="text" class="form-control" name="txtDracsubname" id="txtDracsubname_${des.desid}" value="${des.dracsubname!''}"/>
                </li>
                <li class="debit_amount">
                    <input type="text" class="form-control" name="txtDramount" id="txtDramount_${des.desid}" value="${des.dramount!''}" />
                    <select class="form-control" name="cmbDrctax" id="cmbDrctax_${des.desid}">
                    	<#list loadCtc as ctc>
                    		<option value="${ctc.code!''}" <#if des.drctax==ctc.code>selected</#if>>${ctc.code!''} ${ctc.name}</option>
                    	</#list>
                    </select>
                </li>
                <li class="credit_item_code">
                    <select class="form-control" name="cmbCraccode" id="cmbCraccode_${des.desid}">
                    	<#list devSelect as devsel>
                        	<option value="${devsel.agkey}" <#if des.craccode==devsel.agkey>selected</#if>> ${devsel.agkey} ${devsel.acname} ${devsel.acsubname}</option>
                        </#list>
                    </select>
                    <input type="text" class="form-control" name="txtCracsubcode" id="txtCracsubcode_${des.desid}" value="${des.cracsubcode!''}" />
                </li>
                <li class="credit_item_name">
                    <input type="text" class="form-control" name="txtCracname" id="txtCracname_${des.desid}" value="${des.cracname!''}"/>
                    <input type="text" class="form-control" name="txtCracsubname" id="txtCracsubname_${des.desid}" value="${des.cracsubname!''}"/>
                </li>
                <li class="credit_amount">
                    <input type="text" class="form-control" name="txtCramount" id="txtCramount_${des.desid}" value="${des.cramount!''}" />
                    <select class="form-control" name="cmbCrctax" id="cmbCrctax_${des.desid}">
                        <#list loadCtc as ctc>
                    		<option value="${ctc.code!''}"<#if des.crctax==ctc.code>selected</#if>>${ctc.code!''} ${ctc.name}</option>
                    	</#list>
                    </select>
                </li>
                <li class="edit_delete">
                	<div class="btn-group" role="group" aria-label="...">
						<button type="button" class="btn btn-default" title="編集" onclick="updateDescription('${rc.contextPath}',${des.desid})"><i class="fa fa-pencil-square-o"></i> 更新</button>
						<button type="button" class="btn btn-default" onclick="deleteDescription('${rc.contextPath}',${des.desid})"><img src="images/deletered.png" alt="save" /> 削除</button>
					</div>
                </li>
            </ul>
            <hr>
            </#list>
			
		<#assign GY=lineNum/>
        <#assign GY01=GY/>
        <#assign GY=GY+1/>
        <input type="hidden" id="linenum" name="linenum" value="${GY}"/>
			<div class ="addnewrow" id ="addnewrow">
				<ul class="department">
	                <li class="devcode">
	                    <select class="form-control" name="cmbDebCode1" id="cmbDebCode1_i_${GY}" onchange="getDetails('${rc.contextPath}',this.value,'${GY}');">
	                    	<option value=""></option>
	                        <#list devcode as dev>
			                	<option value="${dev.devcode}">${dev.devcode} ${dev.devname}</option>
			                </#list>
	                    </select>
	                </li>
	                <li class="devname">
	                    <input type="text" class="form-control" id="txtDevname1_i_${GY}" name="txtDevname1_i" value=""/>
	                </li>
	            </ul>
	            <ul class="summary_input">
	                <li class="summary_code">
	                    <input type="text" class="form-control" id="txtDescode_i_${GY}" name="txtDescode" value="" /> 
	                </li>
	                <li class="summary_name">
	                    <textarea rows="2" cols="30" class="form-control" id="txtDesname_i_${GY}" name="txtDesname"></textarea>
	                </li>
	                <li class="debit_subcode">
	                    <select class="form-control" name="cmbDraccode" id="cmbDraccode_i_${GY}" onchange="debitDetails('${rc.contextPath}',this.value,'${GY}');">
	                    <option value=""></option>
	                    	<#list devSelect as devsel>
	                        	<option value="${devsel.agkey}">${devsel.agkey} ${devsel.acname} ${devsel.acsubname}</option>
	                        </#list>
	                    </select>
	                    <input type="text" class="form-control" name="txtDracsubcode" id="txtDracsubcode_i_${GY}" value=""/>
	                </li>
	                <li class="debit_subname">
	                    <input type="text" class="form-control" name="txtDracname" id="txtDracname_i_${GY}" value=""/>
	                    <input type="text" class="form-control" name="txtDracsubname" id="txtDracsubname_i_${GY}" value=""/>
	                </li>
	                <li class="debit_amount">
	                    <input type="text" class="form-control" name="txtDramount" id="txtDramount_i_${GY}" value="" />
	                    <select class="form-control" name="cmbDrctax" id="cmbDrctax_i_${GY}">
	                    <option value=""></option>
	                    	<#list loadCtc as ctc>
	                    		<option value="${ctc.code!''}">${ctc.code!''} ${ctc.name}</option>
	                    	</#list>
	                    </select>
	                </li>
	                <li class="credit_item_code">
	                    <select class="form-control" name="cmbCraccode" id="cmbCraccode_i_${GY}" onchange="creditDetails('${rc.contextPath}',this.value,'${GY}');">
	                    	<option value=""></option>
	                    	<#list devSelect as devsel>
	                        	<option value="${devsel.agkey}">${devsel.agkey} ${devsel.acname} ${devsel.acsubname}</option>
	                        </#list>
	                    </select>
	                    <input type="text" class="form-control" name="txtCracsubcode" id="txtCracsubcode_i_${GY}" value="" />
	                </li>
	                <li class="credit_item_name">
	                    <input type="text" class="form-control" name="txtCracname" id="txtCracname_i_${GY}" value=""/>
	                    <input type="text" class="form-control" name="txtCracsubname" id="txtCracsubname_i_${GY}" value=""/>
	                </li>
	                <li class="credit_amount">
	                    <input type="text" class="form-control" name="txtCramount" id="txtCramount_i_${GY}" value="" />
	                    <select class="form-control" name="cmbCrctax" id="cmbCrctax_i_${GY}">
	                    	<option value=""></option>
	                        <#list loadCtc as ctc>
	                    		<option value="${ctc.code!''}">${ctc.code!''} ${ctc.name}</option>
	                    	</#list>
	                    </select>
	                </li>
	                <li class="edit_delete">
						<button type="button" class="btn btn-default" onclick="insertDescription('${rc.contextPath}','${GY}')" ><img src="images/save_icon.gif" alt="save" /> 登録</button>
	                </li>
	            </ul>
            	<hr>
			</div>
        </form>
    </div>
</div>
        