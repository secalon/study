#!/bin/sh
logserver=`ps -ef |grep dlap-nosp-1.0.jar | grep -v grep | awk '{print $2}'`
kill -9 $logserver
