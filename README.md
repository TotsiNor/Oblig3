# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Sondre Trodahl, S351957, s351957@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 så gikk jeg frem ved å kopiere Programkode 5.2.3.a) som oppfordret til i oppgavetekst. Gjorde endringer slik at
noden man legger inn får riktig forelder. 

I oppgave 2 så brukte jeg tom og inneholder for å sjekke om treet er tomt eller ikke inneholder verdien. I så fall
returnerer vi 0. Deretter brukte jeg iterasjonsmetoden til inneholder for å gå gjennom treet. Ved verdi funnet avslutter 
vi ikke funksjonen som i inneholder, men vi øker antall av verdien og går videre til høyre. 


I oppgave 3 så har vi to forskjellige funksjoner. Den første er førstePostorden. Den første post orden med noden p som rot vil være
nederste venstre node uten barn. Derfor går vi venstre om det er mulig, dersom ikke går vi høyre. Når vi hverken kan gå høyre
eller venstre er vi i første post orden. Da returnerer vi denne. Andre funkksjon er nestepostorden. Da sjekker vi først om 
p har forelder. Dersom den ikke har det er den rotnode og sist i post orden. Returnerer da null. Videre om den har forelder sjekker vi om
den er høyre barn, i så fall er forelder neste. Dersom p er venstre enebarn er forelder neste. Dersom ingen av disse stemmer er første post orden
med forelder som rot neste. Da bruker vi funksjonen førstepostorden for å finne dennne. 

I oppgave 4 har vi som i oppgave 3 to funksjoner. Den første er postorden(Oppgave<? super T> oppgave). Her bruker jeg først
funksjonen førstepostorden på rot slik at jeg får satt opp en "test-node" som er den første i postorden. Deretter bruker jeg
en while løkke som kjører til noden vi ser på er null, i så fall har vi traversert hele treet. På den rekursive oppgave funksjonen 
brukte jeg en if statement for å sjekke om noden er null. I så fall går vi ut av funksjonen. Dersom noden ikke er null gjøres oppgaven 
også kaller vi funksjonen på nytt med neste node i postorden. 