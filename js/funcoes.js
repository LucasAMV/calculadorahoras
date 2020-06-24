// https://stackoverflow.com/questions/11038252/how-can-i-calculate-the-difference-between-two-times-that-are-in-24-hour-format

var REGEX_HORAS = new RegExp("^(([0-2]{1}[0-9]{1})|[0-9]{1}):[0-5]{1}[0-9]{1}$");
var REGEX_HORAS_APLICAR_MASCARA = new RegExp("^([0-1]{1}[0-9]{1})$|^(2{1}[0-3]{1})$|^([3-9]{1})$");

var REGEX_DIGITO = new RegExp("^\\d+$");

function f1() {
	var precisaFazer = document.getElementById('horasNecessarias').value;
	var blocoEntradasSaidas = document.getElementById('blocoEntradasSaidas');
	var horasFeitasFinal = "00:00";
	var faltando = precisaFazer;
	for(var i=0; i < blocoEntradasSaidas.childElementCount; i++) {
    var blocoAtual = blocoEntradasSaidas.children[i];
		var entrada = blocoAtual.children[0].children[1].value;
		var saida = blocoAtual.children[1].children[1].value;
		if(REGEX_HORAS.test(entrada) && REGEX_HORAS.test(saida)) {
			horasFeitasFinal = somarHoras(horasFeitasFinal, contabilizarBloco(entrada, saida));
			document.getElementById('horasFeitas').value = horasFeitasFinal;
			if(!isCargaHorariaDoDiaCumprida(horasFeitasFinal, precisaFazer)) {
				document.getElementById('horasFaltando').value = horasEntre(horasFeitasFinal, precisaFazer);
				document.getElementById('horaSair').value = "--:--";
			}
		} else if(REGEX_HORAS.test(entrada) && !REGEX_HORAS.test(saida)) {
				faltando = horasEntre(horasFeitasFinal, precisaFazer);
				document.getElementById('horasFaltando').value = faltando;
				document.getElementById('horaSair').value = somarHoras(entrada,faltando);
		}
		if(isCargaHorariaDoDiaCumprida(horasFeitasFinal, precisaFazer)) {
			document.getElementById('horasFaltando').value = "00:00";
			document.getElementById('horasExtras').value = calcularHoraExtra(horasFeitasFinal, precisaFazer);
			document.getElementById('horaSair').value = "--:--";
		}
	}
}

function contabilizarBloco(campo1, campo2) {
	if(REGEX_HORAS.test(campo1) && REGEX_HORAS.test(campo2)) {
		return horasEntre(campo1, campo2);
	}
	return null;
}

function calcularHoraExtra(horasFeitasFinal, precisaFazer) {
	var hFeitas = extrairHora(horasFeitasFinal);
	var mFeitas = extrairMinutos(horasFeitasFinal);
	var hPrecisaFazer = extrairHora(precisaFazer);
	var mPrecisaFazer = extrairMinutos(precisaFazer);

	if(mFeitas < mPrecisaFazer) {
		hFeitas -= 1;
		mFeitas += 60;
	}

	hFeitas = hFeitas - hPrecisaFazer;
	mFeitas = mFeitas - mPrecisaFazer;

	return (hFeitas<10? '0'+hFeitas : hFeitas) + ':' + (mFeitas<10? '0'+mFeitas : mFeitas);
}

function isCargaHorariaDoDiaCumprida(feitas,precisaFazer) {
	var hFeitas = extrairHora(feitas);
	var mFeitos = extrairMinutos(feitas);
	var hNecessarias = extrairHora(precisaFazer);
	var mNecessarios = extrairMinutos(precisaFazer);

		if(hFeitas > hNecessarias)
			return true;

		if(hFeitas == hNecessarias) {
			if(mFeitos >= mNecessarios)
				return true;
		}

		return false;
}

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
}

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
}

function aplicarMascara(elem) {
	if(REGEX_HORAS_APLICAR_MASCARA.test(elem.value))
		elem.value += ":";
}

function avaliarFormatacao(key, id) {
	if(REGEX_DIGITO.test(key)) {
		aplicarMascara(document.getElementById(id));
	}
}

function isCampoPreenchido(campoValue, idProximoCampo) {
	if(REGEX_HORAS.test(campoValue) && idProximoCampo!==null)
		document.getElementById(idProximoCampo).focus();
}

function listenerKeyUp(event, campoMascarar, focarEmSeguida) {
	event = event || window.event;
	avaliarFormatacao(event.key, campoMascarar);
	isCampoPreenchido(document.getElementById(campoMascarar).value, focarEmSeguida);
}

//https://www.gavsblog.com/blog/detect-single-and-multiple-keypress-events-javascript
//https://developer.mozilla.org/en-US/docs/Web/API/KeyboardEvent/code
//https://stackoverflow.com/questions/6504914/how-can-i-capture-keyboard-events-are-from-which-keys
document.getElementById('horasNecessarias').onkeyup = function(e) { avaliarFormatacao(e.key, 'horasNecessarias'); };
document.getElementById('h1').onkeyup = function(e) { listenerKeyUp(e,'h1','h2'); };
document.getElementById('h2').onkeyup = function(e) { listenerKeyUp(e,'h2','h3'); };
document.getElementById('h3').onkeyup = function(e) { listenerKeyUp(e,'h3','h4'); };
document.getElementById('h4').onkeyup = function(e) { listenerKeyUp(e,'h4','calcular'); };


