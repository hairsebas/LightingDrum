<?php
$servername = "192.168.4.4";
$username = "root";
$password = "kdelet";
$database ="bateria";

$conexion  mysqli_connect($servername, $username, $password,$database);

if($conexion->connect_error){
  echo "no funciono xd";
}




 ?>
