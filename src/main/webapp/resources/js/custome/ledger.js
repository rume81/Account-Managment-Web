function subledger(base,SHORI){
	
	var cmbBumon 	= jQuery('#cmbBumon').val();
	var cmbNarabi 	= jQuery('#cmbNarabi').val();
	var cmbPrjoken 	= jQuery('#cmbBumon').val();
	var cmbAcFrom 	= jQuery('#cmbAcFrom').val();
	var cmbAcTo 	= jQuery('#cmbAcTo').val();
	var cmbPrjCode 	= jQuery('#cmbPrjCode').val();
	var cmbAcSel1 	= jQuery('#cmbAcSel1').val();
	var cmbAcSel2 	= jQuery('#cmbAcSel2').val();
	var cmbAcSel3 	= jQuery('#cmbAcSel3').val();
	var cmbAcSel4 	= jQuery('#cmbAcSel4').val();
	var cmbAcSel5 	= jQuery('#cmbAcSel5').val();
	var cmbAcSel6 	= jQuery('#cmbAcSel6').val();
	var cmbSaiSel 	= jQuery('#cmbSaiSel').val();
	var txtTukiFrom = jQuery('#txtTukiFrom').val();
	var txtHiFrom 	= jQuery('#txtHiFrom').val();
	var txtTukiTo 	= jQuery('#txtTukiTo').val();
	var txtHiTo 	= jQuery('#txtHiTo').val();
	
	if(cmbBumon==""|| cmbBumon==null){
		cmbBumon = "";
	}
	if(cmbNarabi==""|| cmbNarabi==null){
		cmbNarabi = "1";
	}
	if(cmbAcFrom==""|| cmbAcFrom==null){
		cmbAcFrom = "0000";
	}
	if(cmbAcTo==""|| cmbAcTo==null){
		cmbAcTo = "9999";
	}
	if(cmbAcSel1==""|| cmbAcSel1==null){
		cmbAcSel1 = "0";
	}
	if(cmbAcSel2==""|| cmbAcSel2==null){
		cmbAcSel2 = "0";
	}
	if(cmbAcSel3==""|| cmbAcSel3==null){
		cmbAcSel3 = "0";
	}
	if(cmbAcSel4==""|| cmbAcSel4==null){
		cmbAcSel4 = "0";
	}
	if(cmbAcSel5==""|| cmbAcSel5==null){
		cmbAcSel5 = "0";
	}
	if(cmbAcSel6==""|| cmbAcSel6==null){
		cmbAcSel6 = "0";
	}
	if(cmbSaiSel==""|| cmbSaiSel==null){
		cmbSaiSel = "";
	}
	if(txtTukiFrom==""|| txtTukiFrom==null){
		txtTukiFrom = "";
	}
	if(txtHiFrom==""|| txtHiFrom==null){
		txtHiFrom = "";
	}
	if(txtTukiTo==""|| txtTukiTo==null){
		txtTukiTo = "";
	}
	if(txtHiTo==""|| txtHiTo==null){
		txtHiTo = "";
	}
	
	jQuery('#SHORI').val(SHORI);
	ajaxHelper.wait();
	jQuery('#ledger').ajaxForm({
		success:function(res) {
			jQuery.unblockUI();
			if(res != "-1"){
				var results = res.split('~');
				var param = "補助元帳";
				if(results[1]!="")
					param = results[1];
				location.href=base+"/rptview/"+results[0]+"/"+param;			    	
			}
		},
		dataType:"text"
	}).submit();
	
}