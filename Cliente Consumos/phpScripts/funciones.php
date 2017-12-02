<?php 

	function cabeceraRQ($cuerpo)
	{
		$tipo = "RQ";
		$originador = "CONS";
		$fecha = date('Ymdhis');
		$id_Mensaje = "REGCONSTUR";
		$longitud = strlen($cuerpo);
		$longitud = "0000";
		$hash = md5($cuerpo);

		return $tipo . $originador.$fecha .$id_Mensaje.$longitud.$hash;

	}

	function validaringreso($tipo, $tam, $atributo)
	{
		switch ($tipo) {
			case '1':
				do {
					$ingreso = stream_get_line(STDIN, 1024, PHP_EOL);
					flush();
					if (strlen($ingreso) > $tam || !ctype_digit($ingreso)) {
						echo "\nDebe ser de maximo $tam digitos";
						echo "\nIngrese nuevamente el $atributo: ";
					}
				} while (strlen($ingreso) > $tam || !ctype_digit($ingreso));
				$ingreso = rellenar($ingreso,$tam);
				break;
			case '2':
				do {
					$ingreso = stream_get_line(STDIN, 1024, PHP_EOL);
					flush();
					if (strlen($ingreso) > $tam) {
						echo "\nNo se debe superar los $tam caracteres";
						echo "\nIngrese nuevamente la informacion: ";
					}
				} while (strlen($ingreso) > $tam);
				break;
			case '3':
				do {
					flush();
					$ingreso = stream_get_line(STDIN, 1024, PHP_EOL);
					if (strlen($ingreso) > $tam || !is_numeric($ingreso)) {
						echo "\nDebe ser un numero decimal $tam digitos";
						echo "\nIngrese nuevamente el $atributo: ";
					}
				} while (strlen($ingreso) > $tam || !is_numeric($ingreso));
				break;
			
			default:
				# code...
				break;
		}
		return $ingreso;
	}

	function rellenar($num,$tam){
		$final = str_pad($num,$tam, "0", STR_PAD_LEFT);
		return $final;
	}

?>