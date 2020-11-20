#!/bin/sh
echo "waiting for $1 to open port $2..."
while ! timeout 1 bash -c "echo > /dev/tcp/$1/$2";  do  
  echo waiting...; 
  sleep 3; 
done
echo "$1:$2 is now awailable"