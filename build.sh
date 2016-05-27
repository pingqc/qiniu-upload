#!/bin/bash

echo "starting package"
mvn clean assembly:assembly

echo "copying the packaged jar to the dir  ~/dev/shell/jar"
cp target/qiniu-upload-0.0.1-SNAPSHOT-jar-with-dependencies.jar ~/dev/shell/jar/qiniu-upload.jar

echo "done"