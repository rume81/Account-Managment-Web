<script type="text/javascript" src="${rc.contextPath}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/custome/accountmaintenance.js"></script>
<link rel="stylesheet" href="${rc.contextPath}/css/common/jquery.dataTables.min.css" type="text/css"/>
<script>
$(document).ready(function() {
	$('#accdata').DataTable({
        "columnDefs": [
            {
                "targets": [ 0 ],
                "searchable": false,
                "orderable": false
            }
        ],
        "scrollY":        "400px",
        "scrollCollapse": true,
        "paging":         false,
        "order": [ 2, "asc" ]
    });
})
</script>
<div class="col-sm-12 account_registration">
    <div class="col-sm-12">
        <div class="col-md-2">
            <p class="account_regi_corr">勘定科目　登録・修正</p>
        </div>
        <div class="col-sm-5">
            <button class="btn btn-default r_save" type="button" onclick="saveRecords('${rc.contextPath}')"><img src="images/save_icon.gif" alt="save" /> 登録</button>
            <button class="btn btn-default r_delete" type="button" onclick="deleteRow('${rc.contextPath}')"><img src="images/deletered.png" alt="delete" /> 削除</button>
            <button class="btn btn-default r_exit" type="button" onclick="location.href='${rc.contextPath}/'"><img src="images/exit.png" alt="exit" /> 印刷</button>
        </div>
        <div class="col-sm-2">
            <p class="account_regi_corr">部門別印刷</p>
            <select class="r_print_dev" id="cmbPrintByDev" name="cmbPrintByDev">
            	<#list devlist as dev>
                <option value="${dev.devcode}">${dev.devcode}&nbsp;&nbsp;${dev.devname}</option>
                </#list>
            </select>
        </div>
    </div>
    <div class="col-sm-12">
        <div class="col-sm-5 col-sm-offset-2">
        	<input type="hidden" id="copy_list" name="changetype" value=""/>
            <button class="btn btn-default r_copy" id="r_copy" type="button" onclick="copyRow();"><img src="images/copy.png" alt="copy" /> コピー</button>
            <button class="btn btn-default r_paste" id="r_paste" type="button" disabled onclick="pastRow()"> <img src="images/paste.png" alt="paste" /> 貼り付け</button>
        </div>
        <div class="col-sm-3">
            <button class="btn btn-default r_print" type="button" onclick="rptACListPrint('${rc.contextPath}')"><img src="images/print.png" alt="save" /> 印刷</button>
        </div>
    </div>
    <div class="com-sm-12 fix">
        <table class="table table-bordered table-responsive account_registration_tb">
            <tr>
                <th>部門コード</th>
                <th>科目コード</th>
                <th>科目名称</th>
                <th>細目コード</th>
                <th>細目名称</th>
                <th>財務諸表区分</th>
                <th></th>
            </tr>
            <tr>
            	<td>
                	<input type="text" id="deptcode" name="" value=""/>
                </td>
                <td>
                	<input type="text" id="coursecode" name="" value=""/>
                </td>
                <td>
                	<input type="text" id="coursename" name="" value=""/>
                </td>
                <td>
                	<input type="text" id="specificcode" name="" value=""/>
                </td>
                <td>
                	<input type="text" id="specificname" name="" value=""/>
                </td>
                <td>
                	<div id="fschtml">
                	<select class="r_financial" id="fsc">
                		<option value=""></option>
                        <#list fscr as fs>
                        <option value="${fs.fscr}">${fs.fscrname!''}</option>
                        </#list>
                    </select>
                    </div>
                </td>
                <td>
                	<button type="button" title="リストに追加する" class="btn btn-primary" onclick="addNewRow('${rc.contextPath}')"><i class="fa fa-pencil-square-o"></i></button>
                </td>
            </tr>
         </table>
    </div>
    <div class="com-sm-12">
    	<form name="account_register" id="account_register" action="${rc.contextPath}/accountregister" method="post">
	        <table id="accdata" class="display acmaintain_table" cellspacing="0" width="100%">
		        <thead>
		            <tr>
		            	<th><input type="checkbox" name="" id="chkAll" value="All" onClick="toggle(this)"/></th>
		                <th>部門コード</th>
		                <th>科目コード</th>
		                <th>科目名称</th>
		                <th>細目コード</th>
		                <th>細目名称</th>
		                <th>財務諸表区分</th>
		                <!--<th>KeyCode</th>-->
		            </tr>
		        </thead>
		        <tfoot>
		            <tr>
		            	<th></th>
		                <th>部門コード</th>
		                <th>科目コード</th>
		                <th>科目名称</th>
		                <th>細目コード</th>
		                <th>細目名称</th>
		                <th>財務諸表区分</th>
		                <!--<th>KeyCode</th>-->
		            </tr>
		        </tfoot>
		        <tbody id="databody">
		        	<#list acdata as acdt>
		            <tr id="tr_${acdt.rowid!''}">
		            	<td id="td1_${acdt.rowid!''}">
		            		<input type="checkbox" name="chk" id="chk_${acdt.rowid!''}" value="${acdt.rowid!''}" onClick="toggleselect()"/>
		            		<input type="hidden" id="rowid" name="rowid" value="${acdt.rowid!''}"/>
		            		<input type="hidden" id="change_${acdt.rowid!''}" name="changetype" value="0"/>
		            		<input type="hidden" id="changeVal_${acdt.rowid!''}" value=""/>
		            	</td>
		                <td id="td2_${acdt.rowid!''}">
		                	<div style="display:none">${acdt.devcode!''}</div>
		                	<input type="text" id="devcode_${acdt.rowid!''}" name="devcode" value="${acdt.devcode!''}" onfocus="setmyvalue(this.value,'${acdt.rowid!''}')" onblur="setInputChangeType('${rc.contextPath}',this,'${acdt.rowid!''}',true)"/>
		                </td>
		                <td id="td3_${acdt.rowid!''}">
		                	<div style="display:none">${acdt.accode!''}</div>
		                	<input type="text" id="accode_${acdt.rowid!''}" name="accode" value="${acdt.accode!''}" onfocus="setmyvalue(this.value,'${acdt.rowid!''}')" onblur="setInputChangeType('${rc.contextPath}',this,'${acdt.rowid!''}',true)"/>
		                </td>
		                <td id="td4_${acdt.rowid!''}">
		                	<div style="display:none">${acdt.acname!''}</div>
		                	<input type="text" id="acname_${acdt.rowid!''}" name="acname" value="${acdt.acname!''}" onfocus="setmyvalue(this.value,'${acdt.rowid!''}')" onblur="setInputChangeType('${rc.contextPath}',this,'${acdt.rowid!''}',false)"/>
		                </td>
		                <td id="td5_${acdt.rowid!''}">
		                	<div style="display:none">${acdt.acsubcode!''}</div>
		                	<input type="text" id="acsubcode_${acdt.rowid!''}" name="acsubcode" value="${acdt.acsubcode!''}" onfocus="setmyvalue(this.value,'${acdt.rowid!''}')" onblur="setInputChangeType('${rc.contextPath}',this,'${acdt.rowid!''}',true)"/>
		                </td>
		                <td id="td6_${acdt.rowid!''}">
		                	<div style="display:none">${acdt.acsubname!''}</div>
		                	<input type="text" id="acsubname_${acdt.rowid!''}" name="acsubname" value="${acdt.acsubname!''}" onfocus="setmyvalue(this.value,'${acdt.rowid!''}')" onblur="setInputChangeType('${rc.contextPath}',this,'${acdt.rowid!''}',false)"/>
		                </td>
		                <td id="td7_${acdt.rowid!''}">
		                	<div style="display:none">${acdt.fscr!''}</div>
		                    <select class="r_financial" onchange="setChangeType('${acdt.rowid!''}')" id="fscr_${acdt.rowid!''}" name="fscr">
		                        <option value=""></option>
		                        <#list fscr as fs>
		                        <option value='${fs.fscr}' <#if acdt.fscr==fs.fscr>selected</#if>>${fs.fscrname!''}</option>
		                        </#list>
		                    </select>
		                </td>
		                <!--<td id="td8_${acdt.rowid!''}">${acdt.rowid!''}</td>-->
		            </tr>
		            </#list>
		        </tbody>
	        </table>
		</form>
    </div>
</div>