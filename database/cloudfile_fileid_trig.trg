create or replace trigger cloudfile_fileid_trig
 before insert
 on cloudfile
 for each row
   when ( new.fileid is null or new.fileid<=0)
  begin
     select clouduser_seq.nextval into :new.fileid from dual;
  end;
/
