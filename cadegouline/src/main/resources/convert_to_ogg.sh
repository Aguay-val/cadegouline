#!/bin/bash

for file in *.mp3;
   do ffmpeg -i "${file}" -vn -acodec libvorbis "${file/%mp3/ogg}"; 
done
