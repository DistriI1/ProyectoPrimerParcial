<?php
/*
*http://www.php.net/manual/en/ref.sockets.php
*/

$host = "192.168.1.114";

$socket = socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
$puerto = 2000;

if (socket_connect($socket, $host, $puerto))
{
  echo "\nConexion Exitosa, puerto: " . $puerto;
  $line = stream_get_line(STDIN, 1024, PHP_EOL)
}
else
{
echo "\nLa conexion TCP no se pudo realizar, puerto: ".$puerto;
}
socket_close($socket);
?>