function toggle(source) {
  checkboxes = document.getElementsByName('chk');
  for(var i=0, n=checkboxes.length;i<n;i++) {
    checkboxes[i].checked = source.checked;
  }
}

function toggleselect() {
  checkboxes = document.getElementsByName('chk');
  var se = 1;
  for(var i=0, n=checkboxes.length;i<n;i++) {
	  if(!checkboxes[i].checked)
	  {
		  se = 0;
		  break;
	  }
  }
  if(se==0)
  {
	  document.getElementById('chkAll').checked  = false;
  }else{
	  document.getElementById('chkAll').checked  = true;
  }
}

function setChangeType(rowid) {
	jQuery('#change_'+rowid).val('1');
}

function setmyvalue(myvalue,rowid) {
	jQuery('#changeVal_'+rowid).val(myvalue);
}

function setInputChangeType(base,me,rowid,svalidate) {
	var prevval= jQuery('#changeVal_'+rowid).val();
	var curval=me.value;
	if(curval!=prevval)
	{
		if(svalidate){
			var devcode= jQuery('#devcode_'+rowid).val();
			var accode= jQuery('#accode_'+rowid).val();
			var acsubcode= jQuery('#acsubcode_'+rowid).val();
			
			var pars=devcode+accode+acsubcode;
			ajaxHelper.complexAjaxRequest(base+"/keycodevalidate", pars, function(res){
			   	if(res!="-1"){
			   		jQuery('#change_'+rowid).val('1');
			    } else{
			    	showMsg("コードが重複しています。","エラー");
			    	me.value=prevval;
			    	return;
			    }
			});
		}
		else{
			jQuery('#change_'+rowid).val('1');
		}
	}
}

function addNewRow(base) {
	deptcode = jQuery('#deptcode').val();
	coursecode = jQuery('#coursecode').val();
	coursename = jQuery('#coursename').val();
	specificcode = jQuery('#specificcode').val();
	specificname = jQuery('#specificname').val();
	fsc = jQuery('#fsc').val();
	fschtml = jQuery('#fschtml').html();
	fschtml = fschtml.replace("value=\""+fsc+"\"","value=\""+fsc+"\" selected");
	
	if(deptcode==""){
		showMsg("部門コードを入力してください。","エラー");
		return;
	}
	
	if(specificcode==""){
		showMsg("対象コードの値を入力してください。","エラー");
		return;
	}
	
	var pars=deptcode+coursecode+specificcode;
	ajaxHelper.complexAjaxRequest(base+"/keycodevalidate", pars, function(res){
	   	if(res=="-1"){
	   		showMsg("コードが重複しています。","エラー");
	    	return;
	    }
	   	else{
			var tableRef = document.getElementById('accdata').getElementsByTagName('tbody')[0];
		
			// Insert a row in the table at the last row
			var newRow   = tableRef.insertRow(0);
			
			// Insert a cell in the row at index 0
			var newCell0  = newRow.insertCell(0);
			var newCell1  = newRow.insertCell(1);
			var newCell2  = newRow.insertCell(2);
			var newCell3  = newRow.insertCell(3);
			var newCell4  = newRow.insertCell(4);
			var newCell5  = newRow.insertCell(5);
			var newCell6  = newRow.insertCell(6);
			//var newCell7  = newRow.insertCell(7);
			
			// Append a text node to the cell
			newCell0.innerHTML ="<input type='hidden' id='rowid' name='rowid' value=''/><input type='hidden' id='' name='changetype' value='2'/>";
			newCell1.innerHTML ="<div style='display:none'>"+deptcode+"</div><input type='text' id='devcode' name='devcode' value='"+deptcode+"'/>";
			newCell2.innerHTML ="<div style='display:none'>"+coursecode+"</div><input type='text' id='accode' name='accode' value='"+coursecode+"'/>";
			newCell3.innerHTML ="<div style='display:none'>"+coursename+"</div><input type='text' id='acname' name='acname' value='"+coursename+"'/>";
			newCell4.innerHTML ="<div style='display:none'>"+specificcode+"</div><input type='text' id='acsubcode' name='acsubcode' value='"+specificcode+"'/>";
			newCell5.innerHTML ="<div style='display:none'>"+specificname+"</div><input type='text' id='acsubname' name='acsubname' value='"+specificname+"'/>";
			newCell6.innerHTML ="<div style='display:none'>"+fsc+"</div>"+fschtml;
			//newCell7.innerHTML ="";
			
			jQuery('#deptcode').val('');
			jQuery('#coursecode').val('');
			jQuery('#coursename').val('');
			jQuery('#specificcode').val('');
			jQuery('#specificname').val('');
	   	}
	});
}

function copyRow() {
  checkboxes = document.getElementsByName('chk');
  var copy="";
  for(var i=0, n=checkboxes.length;i<n;i++) {
    if(checkboxes[i].checked) {
    	if(copy=="")
    		copy=checkboxes[i].value;
    	else
    		copy=copy+","+checkboxes[i].value;
    }
  }
  jQuery('#copy_list').val(copy);
  jQuery('#r_paste').prop('disabled', false);
  
  //alert('クリップボードにコピー');
  showMsg("クリップボードにコピー","メッセージ");
}

function pastRow() {
	var copy_list = jQuery('#copy_list').val(); 
	var copys = copy_list.split(',');
	
	for(var r=0;r<copys.length;r++){
		//var rowToClone= document.getElementById("tr_"+copys[r]);
		var table = document.getElementById('accdata').getElementsByTagName('tbody')[0];
		var newRow   = table.insertRow(0);
		
		// Insert  cell in the row at index 0
		var newCell0  = newRow.insertCell(0);
		var newCell1  = newRow.insertCell(1);
		var newCell2  = newRow.insertCell(2);
		var newCell3  = newRow.insertCell(3);
		var newCell4  = newRow.insertCell(4);
		var newCell5  = newRow.insertCell(5);
		var newCell6  = newRow.insertCell(6);
		//var newCell7  = newRow.insertCell(7);
		
				
		newCell0.innerHTML ="<input type='hidden' id='' name='chk' value=''/><input type='hidden' id='' name='changetype' value='2'/>";
		newCell1.innerHTML =jQuery('#td2_'+copys[r]).html();
		newCell2.innerHTML =jQuery('#td3_'+copys[r]).html();
		newCell3.innerHTML =jQuery('#td4_'+copys[r]).html();
		newCell4.innerHTML =jQuery('#td5_'+copys[r]).html();
		newCell5.innerHTML =jQuery('#td6_'+copys[r]).html();
		newCell6.innerHTML =jQuery('#td7_'+copys[r]).html();
		//newCell7.innerHTML ="";
		jQuery('#chk_'+copys[r]).attr('checked', false);
	}
	
	jQuery('#copy_list').val('');
	jQuery('#r_paste').prop('disabled', true);
}

function deleteRow(base){
	var copy="";
	checkboxes = document.getElementsByName('chk');
	var tBody = document.getElementById('accdata').getElementsByTagName('tbody')[0];
	
	for(var i=0, n=checkboxes.length;i<n;i++) {
		if(checkboxes[i].checked) {
			if(copy=="")
	    		copy=checkboxes[i].value;
	    	else
	    		copy=copy+","+checkboxes[i].value;
	    }
	}
	var copys = copy.split(',');
	if(copys.length>0){
		var r = confirm("削除しますか？");
		if (r == true) {
			
			
			ajaxHelper.complexAjaxRequest(base+"/accountdelete", copy, function(res){
			   	if(res!="-1"){
			   		for(var r=0;r<copys.length;r++){
						var row =document.getElementById("tr_"+copys[r]);
				    	tBody.removeChild(row);
					}
			   		showMsg("削除しました。","メッセージ");
			   		//window.location.href=base+"/勘定科目メンテ";
			    }
			});
		}
	}
}

function saveRecords(base){
	var r = confirm("登録しますか？");
	if (r == true) {
		ajaxHelper.wait();
		jQuery('#account_register').ajaxForm({
		    success:function(res) {
		    	if(res != "-1"){
		    		showMsg("登録しました。","メッセージ");
		    		jQuery('#databody').html(res);
		    		jQuery.unblockUI();
			    }
		     },
		     dataType:"text"
		   }).submit();
	}
}

function rptACListPrint(base){
	var devcode = jQuery('#cmbPrintByDev').val();
	if(devcode=="")
	{
		alert("デベロッパーコードを選択");
		return;
	}
	ajaxHelper.wait(); 
	var param = devcode;
	location.href=base+"/rptview/rptACListPrint/"+param;
	
}