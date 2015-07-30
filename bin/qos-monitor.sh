#!/bin/sh

CONFIG_FILE=$2
if [ -n "$CONFIG_FILE" ]; then
 CONFIG_FILE="-Dconfigs=file:${CONFIG_FILE}"
 echo ${CONFIG_FILE}
fi

start(){  
 now=`date "+%Y%m%d"`  
 exec java -Xms128m -Xmx258m -Dspring.profiles.active=production ${CONFIG_FILE} -jar apple-qos-server-monitor-0.0.2-SNAPSHOT.jar 5 >"$now"_monitor.log &  
}  
#停止方法  
stop(){  
 ps -ef|grep apple-qos-server-monitor|awk '{print $2}'|while read pid  
 do  
    kill -9 $pid  
 done  
}  
  
case "$1" in  
start)  
start  
;;  
stop)  
stop  
;;    
restart)  
stop  
start  
;;  
*)  
printf 'Usage: %s {start|stop|restart}\n' "$prog"  
exit 1  
;;  
esac 
