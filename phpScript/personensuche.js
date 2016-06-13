"use strict"

//HTML Elemente aus dem document holen
var button = document.getElementById("starteSuche");
var ausgabe = document.getElementById("ausgabe");


//EventListener am Button registrieren
button.addEventListener('click', function(e){
    //bei Click das Value aus dem textfeld holen
    var  gesuchtePerson = document.getElementById("eingabe").value;

    //Funktion aufrufen
    personenSuche(gesuchtePerson);
});


function personenSuche(gesuchtePerson){
    //neuen XMLhttpRequest erstellen
   let xmlhttp = new XMLHttpRequest();

    //request öffnen, Methoe = GET, URL = server.php, ?=eingabe+gesuchtePerson -> Variablen übergeben, true -> Asynchroner Modus, Script läuft weiter
    xmlhttp.open('GET','server.php?eingabe='+gesuchtePerson,true);

    //Eventlistener am Request registrieren, sobald sich der readystate ändet, werden Befehle ausgeführt
    xmlhttp.addEventListener('readystatechange',function(){
        if(xmlhttp.readyState === 4 && xmlhttp.status === 200){
            //Inhalt des Responsetextes in das div "ausgabe" schreiben
            ausgabe.innerHTML = xmlhttp.responseText;
        }
    });


    //request absenden...----> WICHTIG!!

    xmlhttp.send();
}