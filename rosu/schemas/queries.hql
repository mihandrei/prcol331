--contractele active (utimile versiuni)
 from
  ContracteStudiu as cs   
 where
  cs.id.contractVersion =   (
   select
    max(cs.id.contractVersion) 
   from
    ContracteStudiu as cs   
   where
    cs.studenti.nrMatr=1040
  )
  and cs.id.nrmat = 1040
  
  
 select cs.id.idCurs, max(cs.id.contractVersion) as latestGradedVersion
 from
  ContracteStudiu as cs
 where
  cs.id.nrmat = 1040
  and 
  cs.nota is not null
 group by cs.id.idCurs 
 
select gr from OrgGroup as gr , Curicul as c 
where gr.an = c.an and gr.orgSection.id = c.orgSection.id 
and c.curCourse.id = 1


select cur.an, cur.optionalGroup
from Curicul as cur where
cur.orgSection in
( select g.orgSection
  from Studenti as s 
  join s.orgGroups as g
  where s.nrMatr=1040
)
and cur.semester = 2


 select gr
 from OrgGroup as gr 
 join gr.studentis as s
 where gr.an=2 
 group by gr
 having count(s) <= 2
 

--selecteaza intrarile in orar care corespund contractului ales de student
--adica din orarele grupelor la care-i inscris filtreaza doar materiile din contract
-- BUG? daca joinurile pe orar apar primele atunci selectarea 
-- lui orar.curCourse.abbrev produce un query gresit
select orar
from 
Studenti as s 
join s.contracteStudius as con
join con.curicul as cur

join s.orgGroups as gr
join gr.orars as orar

where 
orar.curCourse = cur.curCourse 
and s.nrMatr=1021
and   con.id.contractVersion = 
(select max(c.id.contractVersion) from ContracteStudiu as c
  where c.studenti.nrMatr=1021)