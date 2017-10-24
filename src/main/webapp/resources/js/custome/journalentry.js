function BumonSansho(base,val){
	var param = val;
	ajaxHelper.complexAjaxRequest(base+"/bumonsansho", param, function(res){
	   	if(res!="-1"){
	   		var results = res.split('~');
	   		jQuery('#txtDevName').val(results[0]);
	   		jQuery('#tblContainer').html(results[1]);
	    }
	});
}

function getDetails(base,val,shori,rono){
	var param = val;
	if(param==""){
		jQuery('#txtDebitAccode_'+rono).val("");
		jQuery('#txtDebitAccount_'+rono).val("");
		jQuery('#txtDebitAccountSub_'+rono).val("");
		jQuery('#txtDebitAccountSub2_'+rono).val("");
		jQuery('#cmbDebitTax_'+rono).val("");
		return;
	}
	ajaxHelper.complexAjaxRequest(base+"/getdetails", param, function(res){
	   	var results = res.split('~');
	   	if(shori==1){
	   		if(results[0]!='-1')
	   			jQuery('#txtDebitAccode_'+rono).val(results[0]);
	   		if(results[1]!='-1')
	   			jQuery('#txtDebitAccount_'+rono).val(results[1]);
	   		if(results[2]!='-1')
	   			jQuery('#txtDebitAccountSub_'+rono).val(results[2]);
	   		if(results[3]!='-1')
	   			jQuery('#txtDebitAccountSub2_'+rono).val(results[3]);
	   		if(results[4]!='-1')
	   			jQuery('#cmbDebitTax_'+rono).val(results[4]);
	   	} else{
	   		if(results[0]!='-1')
	   			jQuery('#txtCreditAccode_'+rono).val(results[0]);
	   		if(results[1]!='-1')
	   			jQuery('#txtCreditAccount_'+rono).val(results[1]);
	   		if(results[2]!='-1')
	   			jQuery('#txtCreditAccountSub_'+rono).val(results[2]);
	   		if(results[3]!='-1')
	   			jQuery('#txtCreditAccountSub2_'+rono).val(results[3]);
	   		if(results[4]!='-1')
	   			jQuery('#cmbDebitTax2_'+rono).val(results[4]);
	   	}
	});
}

function setValue(rono){
	var drval=jQuery('#txtDevitAmount_'+rono).val();
	jQuery('#txtCreditAmount_'+rono).val(drval);
}

function getVendorDetails(base,val,shori,rono){
	var param = val+"~"+shori;
	ajaxHelper.complexAjaxRequest(base+"/tori_sansho", param, function(res){
	   	if(res!="-1"){
	   		jQuery('#txtVendor_'+rono).val(res);
	    } else{
	    	jQuery('#txtVendor_'+rono).val('');
	    	showMsg("該当科目がありません","エラー");
	    }
	});
}

function getProjectDetails(base,val,rono){
	var param = val;
	ajaxHelper.complexAjaxRequest(base+"/prjdetails", param, function(res){
	   	if(res!="-1"){
	   		jQuery('#txtProject_'+rono).val(res);
	    } else{
	    	jQuery('#txtProject_'+rono).val('');
	    	showMsg("該当科目がありません","エラー");
	    }
	});
}

function getDescriptionDetails(base,val,rono){
	var param = val;
	ajaxHelper.complexAjaxRequest(base+"/descdetails", param, function(res){
	   	if(res!="-1"){
	   		var v = res.split('~');
	   		if(jQuery('#txtDebitAccode_'+rono).val()=="" && v[0]!=""){
	   			jQuery('#txtDebitAccode_'+rono).val(v[0]);
	   			jQuery('#cmbDebitAccount_'+rono).val(v[0]);	   			
	   		}
	   		if(jQuery('#txtDebitAccount_'+rono).val()=="" && v[1]!="")
	   			jQuery('#txtDebitAccount_'+rono).val(v[1]);
	   		if(jQuery('#txtDebitAccountSub_'+rono).val()=="" && v[2]!="")
	   			jQuery('#txtDebitAccountSub_'+rono).val(v[2]);
	   		if(jQuery('#txtDebitAccountSub2_'+rono).val()=="" && v[3]!="")
	   			jQuery('#txtDebitAccountSub2_'+rono).val(v[3]);
	   		if(jQuery('#txtDevitAmount_'+rono).val()=="" && v[4]!="")
	   			jQuery('#txtDevitAmount_'+rono).val(v[4]);
	   		
	   		if(jQuery('#txtCreditAccode_'+rono).val()=="" && v[5]!=""){
	   			jQuery('#txtCreditAccode_'+rono).val(v[5]);
	   			jQuery('#cmbCreditAccount_'+rono).val(v[5]);
	   		}
	   		if(jQuery('#txtCreditAccount_'+rono).val()=="" && v[6]!="")
	   			jQuery('#txtCreditAccount_'+rono).val(v[6]);
	   		if(jQuery('#txtCreditAccountSub_'+rono).val()=="" && v[7]!="")
	   			jQuery('#txtCreditAccountSub_'+rono).val(v[7]);
	   		if(jQuery('#txtCreditAccountSub2_'+rono).val()=="" && v[8]!="")
	   			jQuery('#txtCreditAccountSub2_'+rono).val(v[8]);
	   		if(jQuery('#txtCreditAmount_'+rono).val()=="" && v[9]!="")
	   			jQuery('#txtCreditAmount_'+rono).val(v[9]);
	   		
	   		/*if(jQuery('#txtDescription_'+rono).val()=="")*/
	   			jQuery('#txtDescription_'+rono).val(v[10]);
	   		if(jQuery('#cmbDebitTax_'+rono).val()=="" && v[11]!="")
	   			jQuery('#cmbDebitTax_'+rono).val(v[11]);
	   		if(jQuery('#cmbDebitTax2_'+rono).val()=="" && v[12]!="")
	   			jQuery('#cmbDebitTax2_'+rono).val(v[12]);
	   		
	    } else{
	    	jQuery('#txtDescription_'+rono).val('');
	    	showMsg("該当摘要コードがありません。部門コードと摘要コードを確認して下さい。","エラー");
	    }
	});
}

function addNewRow(callLine){
	var linenum = jQuery('#linenum').val();
	var callFrom = jQuery('#txtLabelNo_'+callLine).val();
	if(callFrom==0){
		var newlinenum=parseInt(linenum)+1;
		
		//update line number
		jQuery('#linenum').val(newlinenum);
		
		var dihtml = jQuery('#journal_details').html();
		//replace  #lno# to linenum
		var reshtml = dihtml.replace(/#lno#/g, newlinenum);
		var nDiv = document.createElement('div');
		nDiv.id = 'journal_details_'+newlinenum;
		nDiv.className = 'journal_details';
		nDiv.innerHTML = reshtml;
		
		var contentdiv = document.getElementById('tblContainer');
		contentdiv.appendChild(nDiv);
		//jQuery('#tblContainer').html(contentdiv + reshtml);
		
		//reset previous line value
		jQuery('#txtLabelNo_'+linenum).val(linenum);
		
		
	}
}

function rptTransferslip(base){
	var devcode = jQuery('#cmbDebCode').val();
	 
	var param = devcode;
	location.href=base+"/rptview/rptTransferslip/"+param;
	
}

function saveJournal(base,FURIDEN_MODE){
	var linenum = jQuery('#linenum').val();
	if(FURIDEN_MODE!=2 && linenum==1){
		return;
	}
	var msg = "";
	var err = false;
	for(var l=1;l<linenum;l++){
		var cmbDebitAccount = jQuery('#cmbDebitAccount_'+l).val();
		var txtDevitAmount = jQuery('#txtDevitAmount_'+l).val();
		var cmbCreditAccount = jQuery('#cmbCreditAccount_'+l).val();
		var txtCreditAmount = jQuery('#txtCreditAmount_'+l).val();
		var cmbVendor = jQuery('#cmbVendor_'+l).val();
		var txtVendor = jQuery('#txtVendor_'+l).val();
		
		var cmbDebitTax = jQuery('#cmbDebitTax_'+l).val();
		var cmbDebitTax2 = jQuery('#cmbDebitTax2_'+l).val();
		
		var cmbProject = jQuery('#cmbProject_'+l).val();
		var txtProject = jQuery('#txtProject_'+l).val();
		
		var cmbDescription = jQuery('#cmbDescription_'+l).val();
		var txtDescription = jQuery('#txtDescription_'+l).val();
			
		
		if(cmbDebitAccount=="" || cmbDebitAccount==null){
			msg = msg+"行番号 "+l+"に必要 "+"借方勘定科目<br>";
			err = true;
		}
		if(txtDevitAmount==""){
			msg = msg+"行番号 "+l+"に必要 "+"借方金額<br>";
			err = true;
		}
		if(cmbCreditAccount=="" || cmbCreditAccount==null){
			msg = msg+"行番号 "+l+"に必要 "+"貸方勘定科目<br>";
			err = true;
		}
		if(txtCreditAmount==""){
			msg = msg+"行番号 "+l+"に必要 "+"貸方金額<br>";
			err = true;
		}
		if((cmbVendor=="" || cmbVendor==null) && (txtVendor=="")){
			msg = msg+"行番号 "+l+"に必要 "+"取引先<br>";
			err = true;
		}
		if(cmbDebitTax=="" || cmbDebitTax==null){
			msg = msg+"行番号 "+l+"に必要 "+"消費税<br>";
			err = true;
		}
		if(cmbDebitTax2=="" || cmbDebitTax2==null){
			msg = msg+"行番号 "+l+"に必要 "+"消費税<br>";
			err = true;
		}
		if((cmbProject=="" || cmbProject==null) && (txtProject=="")){
			msg = msg+"行番号 "+l+"に必要 "+"プロジェクト<br>";
			err = true;
		}
		if((cmbDescription=="" || cmbDescription==null) && (txtDescription=="")){
			msg = msg+"行番号 "+l+"に必要 "+"摘要<br>";
			err = true;
		}
	}
	
	if(err==true){
		showMsg(msg,"エラー");
	} else{
		ajaxHelper.wait();
		jQuery('#journal_entry').ajaxForm({
			success:function(res) {
				jQuery.unblockUI();
				if(res != "-1"){
					if(res.includes("伝票番号")){
						//setTimeout(showMsg(res,"メッセージ"), 15000);
						var r = confirm(res);
						if(r==true){
							rptTransferslip(base);
						} else{
							location.href=base+"振替伝票  入力/1";
						}
					} else if(res.includes("updated")){
						location.href=base+"/振替伝票  入力/2";
					} else if(res.includes("次の伝票はありません。")){ 
						var r = confirm(res);
						//if(r==true){
							location.href=base+"/振替伝票修正・印刷";
						//}
					}else{
						showMsg(res,"メッセージ");
					}						    	
			    	
				}
			},
			dataType:"text"
		}).submit();
	}
}


function createJournal(base){
	var cmbDepartment = jQuery('#cmbDepartment').val();
	var cmbRearrangement = jQuery('#cmbRearrangement').val();
	var txtMonth = jQuery('#txtMonth').val();
	var txtMonth2 = jQuery('#txtMonth2').val();
	var txtFromDay = jQuery('#txtFromDay').val();
	var txtUntilday = jQuery('#txtUntilday').val();
	var txtDocumentRangeFrom = jQuery('#txtDocumentRangeFrom').val();
	var txtDocumentRangeTo = jQuery('#txtDocumentRangeTo').val();
	
	if(cmbDepartment=="") {
		jQuery('#cmbDepartment').val('010');
	}
	if(cmbRearrangement=="") {
		jQuery('#cmbRearrangement').val('1');
	}
	
	if(txtMonth=="") {
		jQuery('#txtMonth').val('4');
	}
	if(txtMonth2=="") {
		jQuery('#txtMonth2').val('4');
	}
	if(txtFromDay=="") {
		jQuery('#txtFromDay').val('1');
	}
	if(txtUntilday=="") {
		jQuery('#txtUntilday').val('31');
	}
	if(txtDocumentRangeFrom=="") {
		jQuery('#txtDocumentRangeFrom').val('1');
	}
	if(txtDocumentRangeTo=="") {
		jQuery('#txtDocumentRangeTo').val('999999');
	}
	
	ajaxHelper.wait();
	jQuery('#journal_create').ajaxForm({
		success:function(res) {
			jQuery.unblockUI();
			if(res != "-1"){
				location.href=base+"/rptview/"+res;
			}
		},
		dataType:"text"
	}).submit();
}


function journalSearch(base){
	/*var cmbDepartment = jQuery('#cmbDepartment').val();
	var cmbRearrangement = jQuery('#cmbRearrangement').val();
	var txtMonth = jQuery('#txtMonth').val();
	var txtMonth2 = jQuery('#txtMonth2').val();
	var txtFromDay = jQuery('#txtFromDay').val();
	var txtUntilday = jQuery('#txtUntilday').val();
	var txtDocumentRangeFrom = jQuery('#txtDocumentRangeFrom').val();
	var txtDocumentRangeTo = jQuery('#txtDocumentRangeTo').val();
	
	if(cmbDepartment=="") {
		jQuery('#cmbDepartment').val('010');
	}
	if(cmbRearrangement=="") {
		jQuery('#cmbRearrangement').val('1');
	}
	
	if(txtMonth=="") {
		jQuery('#txtMonth').val('4');
	}
	if(txtMonth2=="") {
		jQuery('#txtMonth2').val('4');
	}
	if(txtFromDay=="") {
		jQuery('#txtFromDay').val('1');
	}
	if(txtUntilday=="") {
		jQuery('#txtUntilday').val('31');
	}
	if(txtDocumentRangeFrom=="") {
		jQuery('#txtDocumentRangeFrom').val('1');
	}
	if(txtDocumentRangeTo=="") {
		jQuery('#txtDocumentRangeTo').val('999999');
	}*/
	
	ajaxHelper.wait();
	jQuery('#documentsearch').ajaxForm({
		success:function(res) {
			jQuery.unblockUI();
			if(res == "2"){
				location.href=base+"/振替伝票  入力/"+res;
			} else if(res=="-1"){
				
			} else{
				showMsg(res,"メッセージ");
			}
		},
		dataType:"text"
	}).submit();
}
