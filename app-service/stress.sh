#!/bin/bash

while [ true ];
  do
    echo $(curl -s "http://localhost:8088/app/song/name/lagrima")

done
