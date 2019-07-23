///              MP3 PLAYER BRUMSET-PROJECT
/// Dferaso@unal.edu.co
//////////////////////////////////////////


#include <SD.h>  
#include <SPI.h>
#include <TMRpcm.h>  


#define pinSD 10    // pin CS arduino UNO 10 /                 
TMRpcm tmrpcm;                                                                 // variables por pad//
                                                                                                                                                                                                                                     



void setup(){

  tmrpcm.speakerPin = 9; // define el pin en el que está conectado el altavoz (audio output)
  Serial.begin(9600);    

  if (!SD.begin(pinSD)) {  // verifica la coneccion de la tarjeta sd:
    Serial.println("Fallo en la tarjeta SD");  //Aviso de que algo no anda bien 
    return;  
  }
}

void loop(){  

   Serial.println("Tarjeta SD_ONLINE"); 
delay(500);
   tmrpcm.setVolume(2); 
   delay(100);
      tmrpcm.play("nn.wav");  // nombre de archivo a reproducir guardado en la sd.
   delay(2000);

    
 }
