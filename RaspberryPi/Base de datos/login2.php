<?php

$servername = "192.168.0.3";
$username = "root";
$password = "28971502";
$database ="bateria";

$conexion = mysqli_connect($servername, $username, $password,$database);

if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

  $ID=$_GET['ID'];
  $prod=array();


$consulta="SELECT * FROM puntaje WHERE ID='$ID'";
$resultado=$conexion->query($consulta);

$resultArray=array();
$tempArray=array();

while($fila=$resultado->fetch_object()){
  $tempArray=$fila;
  array_push($resultArray,$tempArray);
}


echo json_encode($resultArray);
 ?>
