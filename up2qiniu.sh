#!/bin/bash

bucket="your bucket"
access_key="your access key"
secret_key="your secret key"
domain="your domain"
java -jar /Users/pingqc/dev/shell/jar/qiniu-upload.jar $bucket $access_key $secret_key $domain 
