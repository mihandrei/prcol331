
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
 
 select g from OrgGroup as g
inner join g.orgSection.curiculs c 
where c.curCourse.id = 1