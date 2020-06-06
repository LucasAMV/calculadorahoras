function autoLimpar(elem) {
	if(elem.value=='--:--')
		elem.value='';
};

function isAlterado(elem) {
	if(elem.value==null || elem.value=='')
		elem.value=valorAnterior;
	
}