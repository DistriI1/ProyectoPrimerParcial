<?php

include "funciones.php";

//Configuracion para conectarse al servidor
$host = "192.168.1.9";
$socket = socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
$puerto = 2000;

//Variables
$codigoTour ="12345";
$codigoCamarote ="";
$codigoItem ="";
$cantidad ="";
$referencia ="";
$fechaConsumo ="";
$totalConsumo ="";

$cuerpo = $codigoTour . "&";

if (socket_connect($socket, $host, $puerto))
{
	echo "\nConexion Exitosa, puerto: " . $puerto;
	echo "\n";
	$line ="";
	$out = socket_read($socket, 1024);
	echo $out; 
	flush();


	while (strcmp($line, "salir") !== 0) {
		  //Recibir datos
		  $out = socket_read($socket, 1024);
		  echo $out; 
		  flush();

		  echo "\nIngrese el codigo de camarote: ";
		  $codigoCamarote = validaringreso(1,5,"camarote");
		  $cuerpo.=$codigoCamarote."&";

		  echo "\nIngrese el codigo de Item: ";
		  $codigoItem = validaringreso(1,5,"Item");
		  $cuerpo.=$codigoItem."&";

		  echo "\nIngrese el codigo de cantidad: ";
		  $cantidad = validaringreso(1,3,"cantidad");
		  $cuerpo.=$cantidad."&";

		  echo "\nIngrese la referencia: ";
		  $referencia = validaringreso(2,100,"referencia");
		  $cuerpo.=$referencia."&";

		  $fechaConsumo = date('Ymdhis');
		  $cuerpo.=$fechaConsumo."&";

		  echo "\nIngrese el total consumo: ";
		  $totalConsumo = validaringreso(3,8,"consumo");
		  $cuerpo.=$totalConsumo;

		  //Agrego la cabecera
		  $cabecera = cabeceraRQ($cuerpo);

		  //Mensaje Final
		  $trama = $cabecera . $cuerpo;
		  $trama.= "\n";
		  $len = strlen($trama); 
		  socket_write($socket, $trama, strlen($trama));
		
		  
		  //Recibir datos
		  $out = socket_read($socket, 1024);
		  echo $out; 
		}	
}
else
	{
		echo "\nLa conexion TCP no se pudo realizar, puerto: ".$puerto;
	}

	socket_close($socket);
?>