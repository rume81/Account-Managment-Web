${selectedDev!''}~
<div class="journal_details">
    <ul class="project">
        <li class="i_line_number">
            <input type="text" name="txtLabelNo" id="txtLabelNo" class="form-control" value="${lineNum!''}"/>
        </li>
        <li class="dr_sub_code">
            <select class="form-control" name="cmbDebitAccount" id="cmbDebitAccount" onchange="getDetails('${rc.contextPath}',this.value,1)">
            	<option value=""></option>
            	<#list devSel as dev>
                <option value="${dev.agkey!''}">${dev.agkey!''}&nbsp;&nbsp;${dev.acname!''}&nbsp;&nbsp;${dev.acsubname!''}</option>
                </#list>
            </select>
        </li>
        <li class="i_drsub_code">
            <input type="text" class="form-control" name="txtDebitAccode" id="txtDebitAccode" />
        </li>
        <li class="drsub_name">
            <input type="text" name="txtDebitAccount" id="txtDebitAccount" class="form-control" />
        </li>
        <li class="dr_amount">
            <input type="text" name="txtDevitAmount" id="txtDevitAmount" class="form-control"  onkeyup="setValue()"/>
        </li>
        <li class="cr_course_code">
            <select name="cmbCreditAccount" id="cmbCreditAccount" class="form-control" onchange="getDetails('${rc.contextPath}',this.value,2)">
            	<option value=""></option>
                <#list devSel as dev>
                <option value="${dev.agkey!''}">${dev.agkey!''}&nbsp;&nbsp;${dev.acname!''}&nbsp;&nbsp;${dev.acsubname!''}</option>
                </#list>
            </select>
        </li>
        <li class="i_cr_course_code">
            <input type="text" name="txtCreditAccode" id="txtCreditAccode" class="form-control"/>
        </li>
        <li class="cr_course_name">
            <input type="text" name="txtCreditAccount" id="txtCreditAccount" class="form-control"/>
        </li>
        <li class="cr_amount">
            <input type="text" name="txtCreditAmount" id="txtCreditAmount" class="form-control"/>
        </li>
        <li class="customer_code">
            <select name="cmbVendor" id="cmbVendor" class="form-control" onchange="getVendorDetails('${rc.contextPath}',this.value,'101')">
            	<option value=""></option>
                <#list vendors as vend>
                <option value="${vend.vendorcode!''}">${vend.vendorcode!''}</option>
                </#list>
            </select>
        </li>
        <li class="customer_name">
            <input type="text" name="txtVendor" id="txtVendor" class="form-control" />
        </li>
    </ul>
    <ul class="debit_details">
        <li class="dr_sub_code">
            <input type="text" name="txtDebitAccountSub" id="txtDebitAccountSub" class="form-control"/>
        </li>
        <li class="dr_sub_name">
            <input type="text" name="txtDebitAccountSub2" id="txtDebitAccountSub2" class="form-control"/>
        </li>
        <li class="dr_tax">
            <select name="cmbDebitTax" id="cmbDebitTax" class="form-control">
            	<option value=""></option>
                <#list debTax as tax>
                <option value="${tax.keycode!''}">${tax.taxcr!''}</option>
                </#list>
            </select>
        </li>
        <li class="copy">
            <button type="button" class="btn btn-default">Copy</button>
        </li>
        <li class="cr_specific_code">
            <input type="text" name="txtCreditAccountSub" id="txtCreditAccountSub" class="form-control"/>
        </li>
        <li class="cr_specific_name">
            <input type="text" name="txtCreditAccountSub2" id="txtCreditAccountSub2" class="form-control"/>
        </li>
        <li class="cr_tax">
            <select name="cmbDebitTax2" id="cmbDebitTax2" class="form-control">
            	<option value=""></option>
                <#list debTax as tax>
                <option value="${tax.keycode!''}">${tax.taxcr!''}</option>
                </#list>
            </select>
        </li>
        <li class="paste">
            <button type="button" class="btn btn-default">Paste</button>
        </li>
        <li class="prjcode">
            <select name="cmbProject" id="cmbProject" class="form-control" onchange="getProjectDetails('${rc.contextPath}',this.value)">
            	<option value=""></option>
                <#list prjlist as prj>
                <option value="${prj.prjcode!''}">${prj.prjcode!''}</option>
                </#list>
            </select>
        </li>
        <li class="prjname">
            <input type="text" name="txtProject" id="txtProject" class="form-control"/>
        </li>
    </ul>
    <ul class="summary">
        <li class="summary_code">
            <select name="cmbDescription" id="cmbDescription" class="form-control">
            	<#list description as desc>
                <option value="${desc.descode!''}">${desc.descode!''}</option>
               	</#list>
            </select>
        </li>
        <li class="summary_name">
            <input type="text" name="summary_name" id="summary_name" class="form-control" />
        </li>
    </ul>
</div>