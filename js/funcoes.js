<!-- https://stackoverflow.com/questions/11038252/how-can-i-calculate-the-difference-between-two-times-that-are-in-24-hour-format -->

var REGEX_HORAS = new RegExp("^(([0-2]{1}[0-9]{1})|[0-9]{1}):[0-5]{1}[0-9]{1}$");
var REGEX_HORAS_APLICAR_MASCARA = new RegExp("^([0-1]{1}[0-9]{1})$|^(2{1}[0-3]{1})$|^([3-9]{1})$");

var REGEX_DIGITO = new RegExp("^\\d+$");

function f1() {
	var precisaFazer = document.getElementById('h5').value;
	var campo1 = document.getElementById('h1').value;
	var campo2 = document.getElementById('h2').value;
	if(REGEX_HORAS.test(campo1) && REGEX_HORAS.test(campo2)) {
		var horasFeitas = horasEntre(campo1, campo2);
		document.getElementById('horasFeitas').value = horasFeitas;
		
		var faltando = horasEntre(horasFeitas, precisaFazer);
		document.getElementById('horasFaltando').value = faltando;
		
		var campo3 = document.getElementById('h3').value;
		if(REGEX_HORAS.test(campo3)) {
			var campo4 = document.getElementById('h4').value;
			if(REGEX_HORAS.test(campo4)) {
				var horasFeitasFinal = somarHoras(horasFeitas,horasEntre(campo3,campo4));
				
				document.getElementById('horasFeitas').value = horasFeitasFinal;
				
				if(extrairHora(horasFeitasFinal) >= extrairHora(precisaFazer)) {
					document.getElementById('horasFaltando').value = "00:00";
					document.getElementById('horaSair').value = "00:00";
				} else {
					faltando = horasEntre(horasFeitasFinal, precisaFazer);
					document.getElementById('horasFaltando').value = faltando;
				}
			} else {
				document.getElementById('horaSair').value = somarHoras(campo3,faltando);
			}
		} else {
			document.getElementById('horaSair').value = "--:--";
		}
	}
};

function horasEntre(valueCampoData1,valueCampoData2) {
	var hEntrada = extrairHora(valueCampoData1);
	var mEntrada = extrairMinutos(valueCampoData1);
	var hSaida = extrairHora(valueCampoData2);
	var mSaida = extrairMinutos(valueCampoData2);
	
	if(hEntrada > hSaida) {
		hSaida += 24;
	} else if(hEntrada == hSaida) {
		if(mEntrada >= mSaida)
			hSaida += 24;
	}
	
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
	
	return (hFeitas<10? '0'+hFeitas : hFeitas) + ':' + (mFeitos<10? '0'+mFeitos : mFeitos);
};

function extrairHora(valueCampo) {
	var valorString = valueCampo.length==4? valueCampo.charAt(0) : (valueCampo.substr(0,2).charAt(0)=='0'? valueCampo.substr(1,1) : valueCampo.substr(0,2));
	return parseInt(valorString);
}
function extrairMinutos(valueCampo) {
	return parseInt(valueCampo.substr(valueCampo.length-2,2));
}

function somarHoras(horaValue1, horaValue2) {
	var h1 = extrairHora(horaValue1);
	var m1 = extrairMinutos(horaValue1);
	
	var h2 = extrairHora(horaValue2);
	var m2 = extrairMinutos(horaValue2);
	
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

function aplicarMascara(elem) {
	if(REGEX_HORAS_APLICAR_MASCARA.test(elem.value))
		elem.value += ":";
};

function avaliarFormatacao(key, id) {
	if(REGEX_DIGITO.test(key)) {
		aplicarMascara(document.getElementById(id));
	}
};

function isCampoPreenchido(campoValue, idProximoCampo) {
	if(REGEX_HORAS.test(campoValue) && idProximoCampo!=null)
		document.getElementById(idProximoCampo).focus();
};

//https://www.gavsblog.com/blog/detect-single-and-multiple-keypress-events-javascript
//https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent/code
document.getElementById('h1').addEventListener('keyup', (event) => {
	avaliarFormatacao(event.key, 'h1');
	isCampoPreenchido(document.getElementById('h1').value, 'h2');
});	
document.getElementById('h2').addEventListener('keyup', (event) => {
	avaliarFormatacao(event.key, 'h2');
	isCampoPreenchido(document.getElementById('h2').value, 'h3');
});	
document.getElementById('h3').addEventListener('keyup', (event) => {
	avaliarFormatacao(event.key, 'h3');
	isCampoPreenchido(document.getElementById('h3').value, 'h4');
});	
document.getElementById('h4').addEventListener('keyup', (event) => {
	avaliarFormatacao(event.key, 'h4');
	isCampoPreenchido(document.getElementById('h4').value, 'calcular');
});
document.getElementById('h5').addEventListener('keyup', (event) => {
	avaliarFormatacao(event.key, 'h5');
	isCampoPreenchido(document.getElementById('h5').value, 'h1');
});