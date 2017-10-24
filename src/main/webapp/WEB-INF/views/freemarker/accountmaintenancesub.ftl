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
                <#list fscr as fs>
                <option value='${fs.fscr}' <#if acdt.fscr==fs.fscr>selected</#if>>${fs.fscrname!''}</option>
                </#list>
            </select>
        </td>
        <!--<td id="td8_${acdt.rowid!''}">${acdt.rowid!''}</td>-->
    </tr>
 </#list>