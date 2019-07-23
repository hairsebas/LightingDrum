byte rutina[30];
byte dificultad;

int count;
int c_score;
int play;
int i; // Contador de la rutina

int led_verde = 4;
int led_amarillo = 5;
int led_rojo = 6;
int led_azul = 7;



void setup() {
  pinMode(led_verde, OUTPUT);  
  pinMode(led_amarillo, OUTPUT);  
  pinMode(led_rojo, OUTPUT);  
  pinMode(led_azul, OUTPUT); 
  Serial.begin(19200); 
  
}

void loop() {
  count=0;
  c_score=0;
  i=0;
  
  while(Serial.available()){
      rutina[count]=Serial.read();   
      count++;
      delay(1);            
      }
  delay(1000);

  play=count-1;
  while(i<=play){
      
      if(rutina[i]==1){
        digitalWrite (led_verde, HIGH);
        delay(3000);
        digitalWrite (led_verde, LOW);
        Serial.print("\x01");  
      }
      
      if(rutina[i]==2){
        digitalWrite (led_amarillo, HIGH);
        delay(3000);
        digitalWrite (led_amarillo, LOW);
        delay(500);
        Serial.print("\x02");  
      }
      if(rutina[i]==3){
        digitalWrite (led_rojo, HIGH);
        delay(3000);
        digitalWrite (led_rojo, LOW);
        delay(500);
        Serial.print("\x01");  
      }
      if(rutina[i]==4){
        digitalWrite (led_azul, HIGH);
        delay(3000);
        digitalWrite (led_azul, LOW);
        delay(500);
        Serial.print("\x02");  
      }           
      i++;
             
    }
    
    c_score++;
    delay(1000);    
  }  
  
