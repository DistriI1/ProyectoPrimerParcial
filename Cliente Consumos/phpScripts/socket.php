<?php

include "funciones.php";

//Configuracion para conectarse al servidor
$host = "192.168.1.5";
$socket = socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
$puerto = 2001;


if (socket_connect($socket, $host, $puerto))
{
	echo "\nConexion Exitosa, puerto: " . $puerto;
	echo "\n";
	$line ="";

 	while (strcmp($line, "2") !== 0) {
		  //Recibir datos
 		$codigoTour ="2";
		$codigoCamarote ="";
		$codigoItem ="";
		$cantidad ="";
		$referencia ="";
		$fechaConsumo ="";
		$totalConsumo ="";
 		$cuerpo ="";
		$cabecera ="";
		$cuerpo = $codigoTour . "&";  
 		echo "\n1. Registrar Consumo";
 		echo "\n2. Salir";
 		echo "\nIngrese su opcion: ";
		$line = stream_get_line(STDIN, 1024, PHP_EOL);

		if(strcmp($line, "2") !== 0 and strcmp($line, "1")==0){
 			  echo "\nIngrese el numero de camarote: ";
			  $codigoCamarote = validaringreso(1,5,"camarote");
			  $cuerpo.=$codigoCamarote."&";

			  echo "\nIngrese el codigo de : ";
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
		elseif (strcmp($line, "2") !== 0) {
			echo "\nCodigo no identificado";
		}

		}	
}
else
	{
		echo "\nLa conexion TCP no se pudo realizar, puerto: ".$puerto;
	}
	socket_write($socket, "salir", strlen("salir"));

	socket_close($socket);
?>