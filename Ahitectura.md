# Nivel inalt #

Rich client scris in GWT se ocupa exclusiv de UI. Serverul **nu** trimite instructiuni de desenare.

Serverul consta din servicii care fac operatii logice.
Ex:
  * updateaza contractul
  * asociaza student cu laborator

Serviciile opereaza cu DTO (data transfer objects) obiecte simple java .

Serviciile persista modificarile in BD folosind HIBERNATE si eventual tranzactii si control concurenta.

# subsisteme #
  * CRUD pt entitati care nu-s focusul aplicatiei dar de care depindem
> > (facultati, profesori ...)
  * notificari/anunturi ( a la twitter stream)
  * contract studiu
  * laboratoare/teme

# versiunea 1 #

  * serverul ramane simplu: servleturi
  * autentificare minimala si nesigura
  * CRUD pt entitati care nu-s focusul aplicatiei dar de care depindem
  * finalizat contracte

# versiunea 2 #
  * sa dea domu' sa ajungem aici