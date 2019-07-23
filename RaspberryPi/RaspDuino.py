import time
import serial
import binascii
import struct
import sys
import pymysql


db=pymysql.connect(host="localhost",user="root",password="kdelet",db="bateria")
cursor=db.cursor()

ser=serial.Serial(
    port='/dev/ttyAMA0',
    baudrate=9600,
    parity=serial.PARITY_NONE,
    stopbits=serial.STOPBITS_ONE,
    bytesize=serial.EIGHTBITS,
    timeout=0.5
     )
arduino=serial.Serial(
    port='/dev/ttyACM0',
    baudrate=19200,
    parity=serial.PARITY_NONE,
    stopbits=serial.STOPBITS_ONE,
    bytesize=serial.EIGHTBITS,
    timeout=0.001
    )

counter=0
long=30
score=0
ser.write('page Logo_UN\xFF\xFF\xFF')

while 1:
    x=binascii.hexlify(ser.readline())
    a=binascii.hexlify(arduino.readline())

    if x != '':
        x=x[6:8]+x[4:6]+x[2:4]+x[0:2]
        x=int(x,16)
        sql="SELECT * FROM usuario WHERE ID=(%s)"
        cursor.execute(sql,(x))
	id=x
        db.commit()
        if cursor.rowcount!=0:
            ID=x
            ser.write('page Menu\xFF\xFF\xFF')
	    Control=1
	    while Control:
		dat=binascii.hexlify(ser.readline())
		if dat != '':
	            d=dat[2:4]
	            c=dat[0:2]
                    d=int(d,16)
                    c=int(c,16)
                    if d==1:
                       d="\x01"
                    if d==2:
                       d="\x02"
                    if d==3:
                       d="\x03"
                    if c==1:
                       arduino.write("\x01\x02\x03\x04\x01\x02\x03\x04\x01\x02\x03\x04\x01\x02\x03\x04\x01\x02\x03\x04\x01\x02\x03\x04\x01\x02\x03\x04\x01\x03"+d)
                    if c==2:
                       arduino.write("\x03\x02\x03\x04\x02\x02\x04\x04\x01\x04\x02\x04\x01\x02\x03\x04\x01\x04\x03\x01\x01\x02\x02\x03\x04\x01\x03\x03\x01\x04"+d)
                    if c==3:
                       arduino.write("\x03\x02\x03\x04\x01\x02\x03\x04\x01\x02\x03\x04\x01\x02\x03\x04\x01\x02\x03\x04\x01\x02\x03\x04\x01\x02\x03\x04\x01\x03"+d)
                    if c==4:
                       arduino.write("\x03\x02\x03\x04\x01\x02\x03\x04\x01\x02\x03\x04\x01\x02\x03\x04\x01\x02\x03\x04\x01\x02\x03\x04\x01\x02\x03\x04\x01\x02"+d)
            	    Control=0
    if a != '':
       	print(str(score))
        k=int(a)
        
        if k==1:
            ser.write('q0.picc=11\xFF\xFF\xFF')
           
            
        if k==2:
            ser.write('q0.picc=12\xFF\xFF\xFF')
            score+=1        
        if k==9:
            ser.write("page Puntaje\xFF\xFF\xFF")
            print("Rutina terminada, su puntaje fue: ")
            print(str(score))
            score_db=str(score) 
            sql="INSERT INTO puntaje (ID,Puntaje) VALUES (%s,%s)"
            cursor.execute(sql,(id,score))
            db.commit()
            
            time.sleep(1)
            comando='t0.txt="'+score_db+'"\xFF\xFF\xFF'
            print(comando)
            ser.write(comando)
            time.sleep(5)
            ser.write('page Login\xFF\xFF\xFF')
            score=0

