
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
