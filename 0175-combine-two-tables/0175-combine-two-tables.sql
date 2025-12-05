# Write your MySQL query statement below

select p.firstname, p.lastname, a.city, a.state from person as p
left outer join address as a
on p.personid = a.personid;
