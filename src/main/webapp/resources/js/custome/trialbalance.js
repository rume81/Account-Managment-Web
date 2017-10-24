function TrialBalance(base,SHORI){
	
	var cmbDepartment = jQuery('#cmbDepartment').val();
	var txtMonth = jQuery('#txtMonth').val();
	var txtFromDay = jQuery('#txtFromDay').val();
	var txtMonth2 = jQuery('#txtMonth2').val();
	var txtUntilday = jQuery('#txtUntilday').val();
	
	var err = false;
	var msg ="";		
	if(cmbDepartment=="" || cmbDepartment==null){
		msg = msg+"部門の選択"+" 必要とされている<br>";
		err = true;
	}
	
	if(txtMonth==""|| txtMonth==null){
		msg = msg+"月"+" 必要とされている<br>";
		err = true;
	}
	
	if(txtFromDay=="" || txtFromDay==null){
		msg = msg+"日から"+" 必要とされている<br>";
		err = true;
	}
	
	if(txtMonth2=="" || txtMonth2==null){
		msg = msg+"月 "+" 必要とされている<br>";
		err = true;
	}
	
	if(txtUntilday=="" || txtUntilday==null){                                        
		msg = msg+="日まで"+" 必要とされている<br>";
		err = true;
	}
			
		
	if(err==true){
		showMsg(msg,"エラー");
	} else{
		jQuery('#SHORI').val(SHORI);
		ajaxHelper.wait();
		jQuery('#trialbalance').ajaxForm({
			success:function(res) {
				jQuery.unblockUI();
				if(res != "-1"){
					var param = "報告する";
					location.href=base+"/rptview/"+res+"/"+param;			    	
				}
			},
			dataType:"text"
		}).submit();
	}
}