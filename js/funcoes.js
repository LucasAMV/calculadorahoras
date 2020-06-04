<!-- https://stackoverflow.com/questions/11038252/how-can-i-calculate-the-difference-between-two-times-that-are-in-24-hour-format -->

var PADRAO_REFERENCIA = "01/01/2007 ";
var REGEX_HORAS = new RegExp("^[0-2]{1}[0-9]{1}:[0-5]{1}[0-9]{1}$");
var REGEX_HORAS_APLICAR_MASCARA = new RegExp("^[0-2]{1}[0-9]{1}$");
var REGEX_DIGITO = new RegExp("^\\d+$");

function f1() {
	var precisaFazer = document.getElementById('h5').value;
	var horasFeitas = horasEntre(document.getElementById('h1').value, document.getElementById('h2').value);
	document.getElementById('horasFeitas').value = horasEntreAsString(horasFeitas);
	
	var faltando;
	if(horasFeitas.getHours() > 0 || horasFeitas.getMinutes() > 0) {
		faltando = horasEntre(horasEntreAsString(horasFeitas), precisaFazer);
		document.getElementById('horasFaltando').value = horasEntreAsString(faltando);
	}
	
	var campo3 = document.getElementById('h3');
	if(campo3.value != null && campo3.value.length > 0 && campo3.value != "00:00") {
		var d3 = new Date(PADRAO_REFERENCIA+campo3.value);
		
		var campo4 = document.getElementById('h4');
		if(campo4 != null && campo4.value.length>0 && campo4.value != "00:00") {
			var horasFeitasFinal = somarHoras(horasFeitas,horasEntre(campo3.value,campo4.value));
			
			document.getElementById('horasFeitas').value = horasFeitasFinal;
			
			if(new Date(PADRAO_REFERENCIA+horasFeitasFinal).getHours() >= 8) {
				document.getElementById('horasFaltando').value = "00:00";
				document.getElementById('horaSair').value = "00:00";
			} else {
				faltando = horasEntre(horasFeitasFinal, precisaFazer);
				document.getElementById('horasFaltando').value = horasEntreAsString(faltando);
			}
		} else {
			document.getElementById('horaSair').value = somarHoras(d3,faltando);
		}
	} else {
		document.getElementById('horaSair').value = "00:00";
	}
};

function horasEntre(valueCampoData1,valueCampoData2) {
	d1 = new Date(PADRAO_REFERENCIA+ valueCampoData1);
	d2 = new Date(PADRAO_REFERENCIA+ valueCampoData2);
	
	var hEntrada = d1.getHours();
	var mEntrada = d1.getMinutes();
	var hSaida = d2.getHours();
	var mSaida = d2.getMinutes();
	
	var hFeitas = hSaida - hEntrada;
	var mFeitos = mSaida - mEntrada;
	
	if(mFeitos < 0) {
		mFeitos += 60;
		hFeitas--;
	}
	if (mFeitos > 59) {
		mFeitos -=60;
		hFeitas++;
	}
	
	var horasFeitas = (hFeitas<10? '0'+hFeitas : hFeitas) + ':' + (mFeitos<10? '0'+mFeitos : mFeitos);
	return new Date(PADRAO_REFERENCIA+horasFeitas);
};

function horasEntreAsString(dataParam) {
	var hFeitas = dataParam.getHours();
	var mFeitos = dataParam.getMinutes();
	return (hFeitas<10? '0'+hFeitas : hFeitas) + ':' + (mFeitos<10? '0'+mFeitos : mFeitos);
};

function somarHoras(date1, date2) {
	var h1 = date1.getHours();
	var m1 = date1.getMinutes();
	
	var h2 = date2.getHours();
	var m2 = date2.getMinutes();
	
	var horasSomadas = h1 + h2;
	var minutosSomados = m1 + m2;
	
	while(minutosSomados < 0) {
		minutosSomados += 60;
		horasSomadas--;
	} 
	while(minutosSomados>59) {
		minutosSomados -= 60;
		horasSomadas++;
	}
	return (horasSomadas<10? '0'+horasSomadas : horasSomadas) + ':' + (minutosSomados<10? '0'+minutosSomados : minutosSomados);
};

function autoLimpar(elem) {
	elem.value=='00:00'? elem.value='' : elem.value;
}

function aplicarMascara(elem) {
	console.log(REGEX_HORAS_APLICAR_MASCARA.test(elem.value));
	console.log(elem.value);
	if(REGEX_HORAS_APLICAR_MASCARA.test(elem.value))
		elem.value += ":";
};

function avaliarFormatacao(key, id) {
	if(REGEX_DIGITO.test(key))
			aplicarMascara(document.getElementById(id));
}

//https://www.gavsblog.com/blog/detect-single-and-multiple-keypress-events-javascript
//https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent/code
document.getElementById('h1').addEventListener('keyup', (event) => {
	avaliarFormatacao(event.key, 'h1');
});	
document.getElementById('h2').addEventListener('keyup', (event) => {
	avaliarFormatacao(event.key, 'h2');
});	
document.getElementById('h3').addEventListener('keyup', (event) => {
	avaliarFormatacao(event.key, 'h3');
});	
document.getElementById('h4').addEventListener('keyup', (event) => {
	avaliarFormatacao(event.key, 'h4');
});	
