# create table user
create user mycloud identified by mycloud 
# there two tables: clouduser and cloudfile
# mycloud.pdc is used for oracle to create tables 
# sequences are used for create sequence for the two tables
# cloudfile_fileid_trig is used for added sequence to fileid of cloudfile automaticly
# clouduser_userid_trig is used for added sequence to userid of clouduser automaticly