#!/bin/bash

while :;
do
	java -server -Dfile.encoding=UTF-8 -Xmx4G -cp config:./blood.jar:./lib/* blood.Blood > log/stdout.log 2>&1  & echo "$!" > l2gs.pid

	[ $? -ne 2 ] && break
	sleep 30;
done

