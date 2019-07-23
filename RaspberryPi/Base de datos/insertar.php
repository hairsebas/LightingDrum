<?php

$servername = "192.168.0.3 ";
$username = "root";
$password = "28971502";
$database ="bateria";

$conexion = mysqli_connect($servername, $username, $password,$database);

$ID=$_POST['ID'];
$Nombre=$_POST['Nombre'];
$Apellido=$_POST['Apellido'];



$consulta="INSERT into usuario (ID, Nombre, Apellido) values('".$ID."','".$Nombre."','".$Apellido."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);
 ?>
