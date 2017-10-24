<script type="text/javascript" src="${rc.contextPath}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/custome/bgbalance.js"></script>
<link rel="stylesheet" href="${rc.contextPath}/css/common/jquery.dataTables.min.css" type="text/css"/>
<script>
$(document).ready(function() {
	$('#accdata').DataTable({
        "columnDefs": [
            {
                "targets": [ 0,1,2,3,4,5 ],
                "orderable": false
            }             
        ],
        "scrollY":        "400px",
        "scrollCollapse": true,
        "paging":         false,
        "order":false
    });
})
</script>

<div class="col-sm-12 main opening_balance">
    <div class="col-sm-4">
        <p>期首残高 設定</p>
    </div>
    <div class="col-sm-1 col-sm-offset-5">
        <button type="button" class="btn btn-default" onclick="updatebgbalance('${rc.contextPath}')">更新</button>
    </div>
    <div class="col-sm-2">
        <button type="button" class="btn btn-default opening_balance_button">ログアウト</button>
    </div>
    <div class="col-sm-4 col-sm-offset-5">
        <p>貸方残高はマイナスで入力</p>
    </div>
    <div class="col-sm-12">
    <form name="update_bgbalance" id="update_bgbalance" action="${rc.contextPath}/updatebgbalance" method="post">
        <table id="accdata" class="display acmaintain_table table table-bordered"  >
            <thead>
                <tr>
                    <th class="no_padding">部門</th>
                    <th class="no_border_right"></th>
                    <th class="no_border_left">科目名</th>
                    <th class="no_border_right"></th>
                    <th class="no_border_left">細目名</th>
                    <th>期首残高</th>
                </tr>
            </thead>
            <tbody>
	        	<#list acdata as acdt>
	            <tr>
	            <input type="hidden" id="keycode" name="keycode" value="${acdt.keycode}">
	            <input type="hidden" id="change_${acdt.rowid!''}" name="changetype" value="0"/>
		        <input type="hidden" id="changeVal_${acdt.rowid!''}" value=""/>
	                <td>
	                	${acdt.devcode!''}
	                </td>
	                <td>${acdt.accode!''}
	                </td>
	                <td>
	                	${acdt.acname!''}
	                </td>
	                <td>
	                	${acdt.acsubcode!''}
	                </td>
	                <td>
	                	${acdt.acsubname!''}
	                </td>
	                <td id="td7_${acdt.rowid!''}">
	                	<div style="display:none">${acdt.bgbalance!''}</div>
	                	<input type="text" id="bgbalance" name="bgbalance" value="${acdt.bgbalance!''}" class="bgbalance" onfocus="setmyvalue(this.value,'${acdt.rowid!''}')" onblur="setInputChangeType('${rc.contextPath}',this,'${acdt.rowid!''}',true)" />
	                </td>	            
	            </tr>
	            </#list>
	        </tbody>
        </table>
        </form>
    </div>
</div>
        