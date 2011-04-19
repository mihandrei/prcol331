use proj_col;

-- queriuri utile pentru a investiga baza de date 

-- curiculele 

select c.*,cc.* from curicule as cc , grupecurs as gc, cursuri as c
where cc.grupid = gc.grupid and gc.cursid = c.id