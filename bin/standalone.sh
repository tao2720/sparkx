#!/usr/bin/env bash


./sbin/start-master.sh
./sbin/start-slave.sh <master-spark-URL>



Argument	Meaning
-h HOST, --host HOST	Hostname to listen on
-i HOST, --ip HOST	Hostname to listen on (deprecated, use -h or --host)
-p PORT, --port PORT	Port for service to listen on (default: 7077 for master, random for worker)
--webui-port PORT	Port for web UI (default: 8080 for master, 8081 for worker)
-c CORES, --cores CORES	Total CPU cores to allow Spark applications to use on the machine (default: all available); only on worker
-m MEM, --memory MEM	Total amount of memory to allow Spark applications to use on the machine, in a format like 1000M or 2G (default: your machine's total RAM minus 1 GB); only on worker
-d DIR, --work-dir DIR	Directory to use for scratch space and job output logs (default: SPARK_HOME/work); only on worker
--properties-file FILE	Path to a custom Spark properties file to load (default: conf/spark-defaults.conf)



