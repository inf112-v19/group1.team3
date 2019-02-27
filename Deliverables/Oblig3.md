Deloppgave 1: Prosjekt og prosjektstruktur

Roller og kommunikasjon
De rollene vi har, fungerer bra. Vi bytter på roller mellom hver oblig, slik at alle får prøvd seg og vært med på ulike deler av prosjektet. 
Det fungerer godt med en teamlead som har god oversikt over fremgang, oppgaver som jobbes med og hva planen er fremover. 
Det er teamlead som tar en beslutning på om ekstra møter trengs, hva som bør gjøres til neste gang. 
Kundekontakten har oversikt over krav til neste innlevering og hva som har blitt gjort. Kunekontaten formidler videre til de andre i teamet 
hvis det er blitt endringer i krav fra kunden eller i prioritering. Det er kundekontakten som setter opp prioriteringsliste sammen med teamlead. 

Om vi skulle hatt flere roller, er det en Kode-ansvarlig. Som har god oversikt over alt i koden, strukturen, og hva som har blitt gjort. 
Kode-ansvarlig ville hatt ansvar for at koden er riktig, ryddig, strukturert og kommentert riktig. Siden vi er mange som skriver på 
samme prosjekt er det viktig med gode navn på variabler/metoder i koden slik at alle forstår hva det innebærer. 

Gruppedynamikken er god. Det er mange ulike styrker i gruppen som kommer godt med i dette prosjektet. Vi begynner og bli bedre kjent
med hverandre og hva de ulike medlemmene kan. Kommunikasjonen fungerer greit, vi har en chat vi snakker i når vi ikke har møter. 
Vi kan forbedre oss på å kommunisere mer, både mellom  møtene og om hvem som kommer til møter. Dette har vært litt misforståelser på, 
da vi ikke visste helt hvem som skulle komme og hva vi egentlgi skulle gjøre på et møte. Dette møte ble ikke veldig effektivt. 
På møter snakkes det om hva som har blitt gjort, hva man jobber med og om noen trenger hjelp.

Det er noen på gruppen som er mer komfortabel med koding enn andre, og dette er helt greit. Alle bidrar med hvordan vi synes koden skal 
bygges opp, hva skal skrives, kommentarer og alle kan skrive pseudo-kode. Så kan de som vil, skrive selve koden. Andre bidrar med med 
text-filen til innlevering. 

Deloppgave 2:

De nye kravene som har kommet fra kundene er 
-	Kunne få alle typer bevegelse kort som Roborally inkluderer: Bevege seg framover 1-3 felter eller bakover 1 felt, rotere høyre eller venstre 90 grader, eller rotere 180 grader
-	Dele ut 9 programkort kort fra din egen kortstokk som inneholder 20 kort
-	Velge ut fem programkort som man vil spille også bekrefte at man er klar for neste runde
-	Utføre programmet etter spiller har valg sine 5 kort
-	Besøke ulike flagg som er plassert rundt på brettet
-	Hvis en robot går av brettet vil den bli automatisk ødelagt og man vil gå tilbake til den siste backupen man hadde i spillet
-	Oppdatere backupen til en spiller hvis man en robot er stående på en skiftenøkkel i slutten av en fase
-	Flytte backupen til en spiller når man har besøkt et flagg
-	Kunne spille en fullverdig runde som inkluderer alle fem faser
-	Få nye kort til neste runde

Vår prioritert for denne fasen av utviklingen vil være å få på plass de tre første punktene i de nye kravene til kunden. Som er få kortstokken utdelt og spilleren til å godkjenne disse fem kortene. Vi vil også få på plass et flagg som spilleren kan besøka. Vi vil at spilleren skal funne få utdelt ni kort og kunne velge fem av disse kortene for å godkjennes til neste runder. Vi har gjort noen endringer i kundens prioritet siden vi føler det vil være enklere i utviklingen og begynne med å fullføre runden med kortene og vi har nedpriotert backupen og flagg delen av spillet siden dette er ikke så viktig akkurat nå siden vi ikke har flere spillere med i spillet og det vil være lettere å implementere dette etter vi har fått på plass bevegelse kortene og rundene.

Vi vil verifisere kravene med å sjekke at det fungerer når vi har implementert dette i koden. Vi vil kjøre tester for å sjekke at kravene fungerer som vi ønsker i spillet. Vi vil også forhøre oss med kundekontakten og sjekke at kravene står i stil med det kunder ønsker og at de er fornøyd med det vi fokuserer på akkurat nå. 


Referat 13.02
Snakket om innleveringen, sjekket at innlevert kode fungerer hos alle.
Snakket litt om videre jobbing, litt uklart her uten at vi vet hva vi skal gjøre til neste innlevering.
Blitt enige om presentasjonen. Alle har en slide hver - sjekk pp. Kanskje lage klasse-diagram til tirsdag?
Eirik og Amanda var ikke der.

Referat 20.02
Hva har vi til nå? Brett. Skal vi holde brett som bilde, eller sjekke ut tiles? Ingen har erfaring med dette.
Gikk gjennom regler av spillet, slik at alle forstår det. Bli enige om evt. antagelser av spill/kort. Vi antar nå at en brikke vil bli flyttet på rullebånd i starten av ny fase, og ikke med en gang den "lander" på ruten.
Gå gjennom krav til neste innlevering. Hvordan skal vi bygge opp koden, hvilke klasser skal vi ha og hvilken klasse skal være ansvarlig for hva. Hvordan gjør vi det grafisk? Brikkens posisjon, skal brettet bestemme. Velger å ha en Game-klasse som styrer hele spillet.
Virker som mange krav som skal på plass til neste innlevering. Vi tar en ting av gangen og jobber så godt vi kan.
Brikken kan nå flyttes rundt med piltaster
Prioriteringer til neste gang: 
-Klassediagram - få oversikt over klasser.
-Brett skal fungere med alle sine elementer
-Kort-klassen og ulike bevegelseskort skal lages
-Spiller-klasse?
William var syk idag.
Avtalt møte på tirsdag 26.02.

Referat 26.02
Nå har vi endelig fått oblig 3 med krav og innleveringsspesifikasjoner. Dette gikk vi gjennom i plenum slik at alle forstår og er med. 
Det har gått litt sakte før vi fikk denne filen, da vi var litt usikre på hva vi egentlig skulle levere. På møtet snakket vi 
om som har blitt gjort siden sist, hva man vil jobbe med denne timen og hva vi må gjøre ferdig til i morgen. 
Noen jobber med kode til brett, noen jobber med koden til kort, andre lager klassediagram. Det er viktig at alle er inneforstått med hva
som skal gjørs til innlevering, men også til neste møte. Det er avtalt hva som skal være ferdig til gruppetimen i morgen, det nærmer seg 


Oppfølging til neste innlevering: 
-Mer og litt bedre kommunikasjon
-Alle gjør sine deler til fristen 
-Lage klassediragram og kode for noen nye klasser 





