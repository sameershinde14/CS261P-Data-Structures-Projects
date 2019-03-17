#!/bin/bash
dirlist=$(find $1 -mindepth 1 -maxdepth 1 -type d)

for dir in $dirlist
do
  (
  cd $dir
  echo $dir
  for file in *.log; 
  do 
    if [ -f "$file" ]; then 
        echo "File: $file"
        if [[ $file == *"01"* ]]; then
		  echo "TC 01"
		elif [[ $file == *"02"* ]]; then
		  echo "TC 02"
		elif [[ $file == *"03"* ]]; then
		  echo "TC 03"
		elif [[ $file == *"04"* ]]; then
		  echo "TC 04"
		elif [[ $file == *"05"* ]]; then
		  echo "TC 05"
		  grep "INSERTED" $file | awk -F "INSERTED:" '{print $2}'|awk -F "," '{print $2}'| awk -F "=" '{print $2}' > csv/time_inserts_test5.csv
		  grep "INSERTED" $file | awk -F "INSERTED:" '{print $2}'|awk -F "," '{print $1}'| awk -F "=" '{print $2}' > csv/size_inserts_test5.csv
		elif [[ $file == *"06"* ]]; then
		  echo "TC 06"
		  grep "REMOVED" $file | awk -F "REMOVED:" '{print $2}'|awk -F "," '{print $2}'| awk -F "=" '{print $2}' > csv/time_removes_test6.csv
		  grep "REMOVED" $file | awk -F "REMOVED:" '{print $2}'|awk -F "," '{print $1}'| awk -F "=" '{print $2}' > csv/size_removes_test6.csv
		elif [[ $file == *"07"* ]]; then
		  echo "TC 07"
		  grep "TRAVERSED" $file | awk -F "TRAVERSED:" '{print $2}'|awk -F "," '{print $2}'| awk -F "=" '{print $2}' > csv/time_traverse_test7.csv
		  grep "TRAVERSED" $file | awk -F "TRAVERSED:" '{print $2}'|awk -F "," '{print $1}'| awk -F "=" '{print $2}' > csv/size_traverse_test7.csv
		elif [[ $file == *"08"* ]]; then
		  echo "TC 08"
		  grep "INSERTED" $file | awk -F "INSERTED:" '{print $2}'|awk -F "," '{print $2}'| awk -F "=" '{print $2}' > csv/time_sinserts_test8.csv
		  grep "INSERTED" $file | awk -F "INSERTED:" '{print $2}'|awk -F "," '{print $1}'| awk -F "=" '{print $2}' > csv/size_sinserts_test8.csv
		elif [[ $file == *"09"* ]]; then
		  echo "TC 09"
		  grep "REMOVED" $file | awk -F "REMOVED:" '{print $2}'|awk -F "," '{print $2}'| awk -F "=" '{print $2}' > csv/time_sremoves_test9.csv
		  grep "REMOVED" $file | awk -F "REMOVED:" '{print $2}'|awk -F "," '{print $1}'| awk -F "=" '{print $2}' > csv/size_sremoves_test9.csv
		fi
    fi 
  done
  )
done
