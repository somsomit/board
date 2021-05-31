/* chkSubmit(유효성 검사 대상, 메시지 내용) */
function chkSubmit(v_item, v_msg){
	alert("----");
	alert("v_item.val() :" + v_item);
	if(v_item.val().replace(/\s/g,'')==''){
		alert("확인");
		alert(v_msg + "확인해주세요");
		v_item.val();
		v_item.focus();
		return false;
		
	}else{
		return true;
	}
}