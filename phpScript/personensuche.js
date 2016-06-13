"use strict"

//HTML Elemente aus dem document holen
var button = document.getElementById("starteSuche");
var ausgabe = document.getElementById("ausgabe");



var buttenAdd = document.getElementById("personAdd");

buttenAdd.addEventListener('click',function(e){

    let name = document.getElementById("name").value;
    let alter = document.getElementById("alter").value;
    let familienstand = document.getElementById("familienstand").value;
    let wohnort = document.getElementById("wohnort").value;
    let personenDaten = [name,alter,familienstand,wohnort];

    addPerson(personenDaten);
});


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

function addPerson(personenDaten){

    let jSONDaten = JSON.stringify(personenDaten);
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.open('POST','server.php',true);

    xmlhttp.addEventListener('readystatechange',function(){

        if(xmlhttp.readyState ===4 && xmlhttp.status === 200){
            ausgabe.innerHTML = xmlhttp.responseText;
        }
    });
    console.log(jSONDaten);
    //xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlhttp.send('personenDaten='+jSONDaten);

}