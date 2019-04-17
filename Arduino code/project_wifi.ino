#include <FS.h>                  

#include <ESP8266WiFi.h>         

//needed for library
#include <DNSServer.h>
#include <ESP8266WebServer.h>
#include <WiFiManager.h>          
WiFiManager wifiManager;

//Firebase Libraries
#include <Firebase.h>
#include <FirebaseArduino.h>/
#include <FirebaseCloudMessaging.h>
#include <FirebaseError.h>
#include <FirebaseHttpClient.h>
#include <FirebaseObject.h>

#define HOST "https://www.google.com"

//Enter Firebase auth and host link(without https://)
#define FIREBASE_HOST "homeautomation-ce8a4.firebaseio.com"

#define FIREBASE_AUTH "50UBmObusGXhOaX9jnnCisjJgSawVWatcahuSDsJ"



#define Motion 5  //D7
#define Relay1 13  //D1
#define Relay2 4   //D2
#define Relay3 14   //D5
#define Relay4 12   //D6


unsigned int AnalogValue;
int motion_detect=HIGH;
int automatic=1;


void setup() {
  
  // put your setup code here, to run once:
  Serial.begin(115200);
  Serial.println();

 
  //exit after config instead of connecting
  wifiManager.setBreakAfterConfig(true);

  //reset settings - for testing
  //wifiManager.resetSettings();


  //tries to connect to last known settings
  if (!wifiManager.autoConnect("IOT WiFi", "")) {
    Serial.println("failed to connect, we should reset as see if it connects");
    delay(3000);
    ESP.reset();
    delay(5000);
  }

  //if you get here you have connected to the WiFi
  Serial.println("connected...:)");


  Serial.println("local ip");
  Serial.println(WiFi.localIP());


  automatic=0;
  
  //setting pin modes
  pinMode(Relay1,OUTPUT);
  pinMode(Relay2,OUTPUT);
  pinMode(Relay3,OUTPUT);
  pinMode(Relay4,OUTPUT);
  pinMode(Motion,OUTPUT);

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);

}

void loop() {
 
  // put your main code here, to run repeatedly:
  //if arduino board is not connected to wifi it enter below while loop 
  while (WiFi.status() != WL_CONNECTED){
  if (!wifiManager.autoConnect("IOT WiFi", "")) {
    Serial.println("failed to connect, we should reset as see if it connects");
    delay(3000);
    ESP.reset();
    delay(5000);
    
  }
  }
  
  FirebaseObject obj=Firebase.get("Boards/Zx-zsdgs=asg");

  if(obj.getInt("pin1")==0){
    digitalWrite(Relay1,HIGH);
  }
  else if(obj.getInt("pin1")==1)
  {
    digitalWrite(Relay1,LOW);
  }
  
  if(obj.getInt("pin2")==0){
    digitalWrite(Relay2,1);
  }
  else if(obj.getInt("pin2")==1)
  {
    digitalWrite(Relay2,0);
  }

  if(obj.getInt("pin3")==0){
    digitalWrite(Relay3,1);
  }
  else if(obj.getInt("pin3")==1)
  {
    digitalWrite(Relay3,0);
  }

  if(obj.getInt("pin4")==0){
    digitalWrite(Relay4,1);
  }
  else if(obj.getInt("pin4")==1)
  {
    digitalWrite(Relay4,0);
  }
  
  automatic = obj.getInt("Auto");


 //automatic code
  while(automatic){
    //automatic code start
    FirebaseObject obj=Firebase.get("Boards/Zx-zsdgs=asg");
    AnalogValue=analogRead(A0);
    Serial.println("a=");
    Serial.println(AnalogValue);
    if(AnalogValue >= 660){
      digitalWrite(Relay1,HIGH);
      digitalWrite(Relay2,HIGH);
      digitalWrite(Relay3,HIGH);
    }
    else{ 
      digitalWrite(Relay1,LOW);
      digitalWrite(Relay2,LOW);
      digitalWrite(Relay3,LOW);
    }
    motion_detect=digitalRead(Motion);
    Serial.println(motion_detect);
    if(motion_detect==HIGH){
     digitalWrite(Relay4,HIGH);
    }  
    else{
      digitalWrite(Relay4,LOW);   
     }
    //automatic code end
  automatic=obj.getInt("Auto");
  delay(1000);
  }
  delay(1000);
}
