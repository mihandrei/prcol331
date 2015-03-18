### GWT : o idee buna, partea sofisticata implementata bine, partea simpla un gunoi grrr ###

+ nici o problema cu compilatorul sau domul
+ debug
- hibe majore de api
- expune o gramada de matze nedocumentate
- documentatie insuficienta
- comunitate nula
~ uibinder ii ok dar inflexibil, parca mai de folos ar fi un templating simplu
~ css resource : buna idee da prea mut config pt cazul banal
~ requestfactory: nu mai am rabdare

## Activity framework cartonas galben ##

A trebuit sa mapez manual place-uri la activitati ocolind defaultul.
Default care nu face multe desi face codegen.
Ma asteptam la mapari declarative rest prin regexuri .
In schimb avem tokenizere, mappere, anotari , complexitate inutila
Nu poti sa mapezi #noutati la o activitate ci cel mult #noutati:
Nici #noutati/lala #noutati la activitati diferite

## Activity Framework alta hiba ##

gwt code split mai bate un cui in activity frameworku din GWT
Flowul din activity framework e sincron:
onurlchange : placemapper -> place -> activitymapper -> activity -> ...

startul din activitate poate fi implementat asincron deci macar ui-u poate fi gwt.runasync
DAR gramadoiu de cod din activitati ii incarcat sincron!
ca activitymapperu tre sa intoarca o activitate.

## LayoutPanels inca-un cui in sicriu la activity framework ##
recomandate
le tre parinti care provideresize
nu exista un panel implements providesresize, acceptsonewidget
insa la activitymanager ii tre un aceptsonewidget
deci by default nu poti folosi activity fr. si layoupanale

## html + position absolute + % = nice win ##

is uimit cat de repede si fara probleme mari am implementat  orarul
GWT
+ am scris in java, refactoring ii fain
- in afar de compilator restul din gwt nu o fost de folos,
am incercat cu layoupanele dar am renuntat si am facut cu html+css

## security ##
ii enervant, ca-i cross cutting, tre sa imprastii cod prin applicatie
Alternativa (care ma manca sa o scriu) e sa imbrac rpc-urile in comenzi
si sa le centralizez asa.
Da asta mai pune un strat la ceapa de abstractizari
Las deocamdata in paragina.

## hibernate ##

- crapa daca are pe classpath un jar numit gwt-servlet-suport sau asemanator
pentru ca se pare ca in jar e o implementare pe jumate a javax.validation
- lectie daca te bazezi pe hbm2java atunci nu fa relatii nestandard in bd
( 2 pk-uri duble si un fk care lega doar o pereche) ca-i dezastru
Iar daca crapa **nu** sterge anotarile ce par vinovate ca face fallback la conventie
sii te dai de ceasu morti sa pricepi sqlexception table not found , si de ce vrea sa
> gaseasca o tabela neexistenta
- hibernate tools is buggy dar odata ce le-ai configurat se merita
- HQL is fainutz

alfa kenny buddys