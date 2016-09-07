/**
 * 
 */
function pedirPosicion(pos) {
 	var lat1=pos.coords.latitude;
	var lon1=pos.coords.longitude;
	var lat2=-12.0720497;
	var lon2=-76.9419327;
	var R = 6371;
	var dLat = toRad(lat2-lat1);
	var dLon = toRad(lon2-lon1);
	var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
        Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
        Math.sin(dLon/2) * Math.sin(dLon/2);
	var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	var d = R * c;
	document.getElementById('idInput').value=d;
	document.getElementById('idInput1').value=d;
}

function toRad(Value) {
    /** Converts numeric degrees to radians */
    return Value * Math.PI / 180;
}
navigator.geolocation.getCurrentPosition(pedirPosicion);