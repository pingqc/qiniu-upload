#!/bin/bash
if [ $# != 1 ]; then
    echo "tell me the file name in this directory!"
    exit
fi
current_dir=`pwd`
bucket="不告诉你"
file="$current_dir/$1"

if [ ! -f $file ]; then
    echo "only support upload a single file"
    exit
fi
access_key="a764640F1yw6F5Oy9dpIP3hxcx_WpTe4H3piYRU-"
secret_key="不告诉你"
domain="http://不告诉你"
java -jar /Users/pingqc/dev/shell/jar/qiniu-upload.jar $bucket $file $access_key $secret_key $domain