		function hora(){
			var fecha = new Date()
			var hora = fecha.getHours()
			var minuto = fecha.getMinutes()
			var segundo = fecha.getSeconds()
			if (hora < 10) {hora = "0" + hora}
			if (minuto < 10) {minuto = "0" + minuto}
			if (segundo < 10) {segundo = "0" + segundo}
			var horita = hora + ":" + minuto + ":" + segundo
			document.getElementById('hora').firstChild.nodeValue = horita
			tiempo = setTimeout('hora()',1000)
		}
		function inicio(){
			document.write('<span id="hora">')
			document.write ('000000</span>')
			hora()
		}
		function fecha(){
			var f = new Date();
			document.write(f.getDate() + "/" + (f.getMonth()+1) + "/" + f.getFullYear());
		}