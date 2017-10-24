function updatebgbalance(base) {
	var key_code = jQuery("#keycode").val();
	var bgbalance = jQuery("#bgbalance").val();

	ajaxHelper.wait();
	jQuery('#update_bgbalance').ajaxForm({
		success : function(res) {
			if (res != "-1") {
				window.location.href = base + "/期首残高設定";
				jQuery.unblockUI();
			} else {
				showMsg("Not able to Update.");
			}
		},
		dataType : "text"
	}).submit();
}
function insertDescription(base,rono) {
	
	var devcode = jQuery("#cmbDebCode1_i_"+rono).val();
	var devname = jQuery("#txtDevname1_i_"+rono).val();
	var draccode  = jQuery("#cmbDraccode_i_"+rono).val();
	var dracname = jQuery("#txtDracname_i_"+rono).val();
	var dracsubcode = jQuery("#txtDracsubcode_i_"+rono).val();
	var dracsubname = jQuery("#txtDracsubname_i_"+rono).val();
	var craccode = jQuery("#cmbCraccode_i_"+rono).val();
	var cracname = jQuery("#txtCracname_i_"+rono).val();
	var cracsubcode = jQuery("#txtCracsubcode_i_"+rono).val();
	var cracsubname = jQuery("#txtCracsubname_i_"+rono).val();
	var dramount = jQuery("#txtDramount_i_"+rono).val();
	var cramount = jQuery("#txtCramount_i_"+rono).val();
	var descode = jQuery("#txtDescode_i_"+rono).val();
	var desname = jQuery("#txtDesname_i_"+rono).val();
	var drctax = jQuery("#cmbDrctax_i_"+rono).val();
	var crctax = jQuery("#cmbCrctax_i_"+rono).val();
	
	var pars =  devcode+"~"+devname+"~"+draccode+"~"+dracname+"~"+dracsubcode+"~"+dracsubname+"~"+craccode+"~"+cracname+"~"+cracsubcode+"~"+cracsubname+"~"+dramount+"~"+cramount+"~"+descode+"~"+desname+"~"+drctax+"~"+crctax;
	ajaxHelper.complexAjaxRequest(base+"/insertDescription", pars, function(res){
		//alert(res);
	 	if(res!="-1"){
	 		window.location.href=base+"/摘要登録";
	    } else{
	    	alert("Not able to Save.");
	    }
	});
}

function updateDescription(base,rono) {
	var desid = jQuery("#txtDesid_"+rono).val();
	var devcode = jQuery("#cmbDebCode1_"+rono).val();
	var devname = jQuery("#txtDevname1_"+rono).val();
	var draccode  = jQuery("#cmbDraccode_"+rono).val();
	var dracname = jQuery("#txtDracname_"+rono).val();
	var dracsubcode = jQuery("#txtDracsubcode_"+rono).val();
	var dracsubname = jQuery("#txtDracsubname_"+rono).val();
	var craccode = jQuery("#cmbCraccode_"+rono).val();
	var cracname = jQuery("#txtCracname_"+rono).val();
	var cracsubcode = jQuery("#txtCracsubcode_"+rono).val();
	var cracsubname = jQuery("#txtCracsubname_"+rono).val();
	var dramount = jQuery("#txtDramount_"+rono).val();
	var cramount = jQuery("#txtCramount_"+rono).val();
	var descode = jQuery("#txtDescode_"+rono).val();
	var desname = jQuery("#txtDesname_"+rono).val();
	var drctax = jQuery("#cmbDrctax_"+rono).val();
	var crctax = jQuery("#cmbCrctax_"+rono).val();
	
	var pars =  desid+"~"+devcode+"~"+devname+"~"+draccode+"~"+dracname+"~"+dracsubcode+"~"+dracsubname+"~"+craccode+"~"+cracname+"~"+cracsubcode+"~"+cracsubname+"~"+dramount+"~"+cramount+"~"+descode+"~"+desname+"~"+drctax+"~"+crctax;
	ajaxHelper.complexAjaxRequest(base+"/updateDescription", pars, function(res){
		//alert(res);
	 	if(res!="-1"){
	 		window.location.href=base+"/摘要登録";
	    } else{
	    	alert("Not able to Save.");
	    }
	});
}
function deleteDescription(base,rono) {
	var desid = jQuery("#txtDesid_"+rono).val();
	
	var pars = desid;
	ajaxHelper.complexAjaxRequest(base+"/deleteDescription",pars,function(res){
		if(res!=-1) {
			window.location.href=base+"/摘要登録";
		} else {
			alert("Not able to save")
		}
	});
}

function setmyvalue(myvalue, rowid) {
	jQuery('#changeVal_' + rowid).val(myvalue);
}

function setInputChangeType(base, me, rowid, svalidate) {
	var prevval = jQuery('#changeVal_' + rowid).val();
	var curval = me.value;
	if (curval != prevval) {
		jQuery('#change_' + rowid).val('1');
	}
}


$(document).ready(function(){
	$(document).on('click', '#addRow', function() {
		 $(".addnewrow").css({"visibility": "visible","height":"auto"});
	});
});


function getDetails(base,val,rono){
	var param = val;
	if(param==""){
		jQuery('#txtDevname1_i_'+rono).val("");
		return;
	}
	ajaxHelper.complexAjaxRequest(base+"/getDevname", param, function(res){
	   	if(res!="-1"){
	   		jQuery('#txtDevname1_i_'+rono).val(res);
	    } else{
	    	jQuery('#txtDevname1_i_'+rono).val('');
	    	showMsg("該当科目がありません","エラー");
	    }
	});
}
function debitDetails(base, val, rono) {
	var param = val; 
	if(param=="") {
		jQuery('#cmbDraccode_i_'+rono).val("");
		jQuery('#txtDracsubcode_i_'+rono).val("");
		jQuery('#txtDracname_i_'+rono).val("");
		jQuery('#txtDracsubname_i_'+rono).val("");
		jQuery('#cmbDrctax_i_'+rono).val("");
		return;
	}
	ajaxHelper.complexAjaxRequest(base+"/debitDetails", param, function(res){
	   	var results = res.split('~');

	   	
   		if(results[1]!='-1')
   			jQuery('#txtDracsubcode_i_'+rono).val(results[0]);
   		if(results[2]!='-1')
   			jQuery('#txtDracname_i_'+rono).val(results[1]);
   		if(results[3]!='-1')
   			jQuery('#txtDracsubname_i_'+rono).val(results[2]);
   		if(results[4]!='-1')
   			jQuery('#cmbDrctax_i_'+rono).val(results[3]);
	});
}

function creditDetails(base, val, rono) {
	var param = val; 
	if(param=="") {
		jQuery('#cmbDraccode_i_'+rono).val("");
		jQuery('#txtDracsubcode_i_'+rono).val("");
		jQuery('#txtDracname_i_'+rono).val("");
		jQuery('#txtDracsubname_i_'+rono).val("");
		jQuery('#cmbDrctax_i_'+rono).val("");
		return;
	}
	ajaxHelper.complexAjaxRequest(base+"/debitDetails", param, function(res){
	   	var results = res.split('~');

	   	
   		if(results[1]!='-1')
   			jQuery('#txtCracsubcode_i_'+rono).val(results[0]);
   		if(results[2]!='-1')
   			jQuery('#txtCracname_i_'+rono).val(results[1]);
   		if(results[3]!='-1')
   			jQuery('#txtCracsubname_i_'+rono).val(results[2]);
   		if(results[4]!='-1')
   			jQuery('#cmbCrctax_i_'+rono).val(results[3]);
	});
}
