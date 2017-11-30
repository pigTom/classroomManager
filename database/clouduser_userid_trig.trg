create or replace trigger clouduser_userid_trig
 before insert
 on clouduser
 for each row
   when ( new.userid is null or new.userid<=0)
  begin
     select clouduser_seq.nextval into :new.userid from dual;
  end;
/
