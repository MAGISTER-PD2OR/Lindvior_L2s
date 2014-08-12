#!/bin/sh
USER=auth
PASS=auth
DBNAME=auth_l2s
DBHOST=192.168.1.2

for sqlfile in install/*.sql
do
        echo Loading $sqlfile ...
        mysql -h $DBHOST -u $USER --password=$PASS -D $DBNAME < $sqlfile
done
