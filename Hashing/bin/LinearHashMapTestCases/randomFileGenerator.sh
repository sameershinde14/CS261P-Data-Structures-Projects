
#!/bin/bash

MAXCOUNT=$1
count=1
rm $3
echo $MAXCOUNT >> $3
while [ "$count" -le $MAXCOUNT ]      # Generate 10 ($MAXCOUNT) random integers.
do
  RANGE=$2
  number=$RANDOM
  let "number %= $RANGE"
  echo $number "," $number >> $3 
  let "count += 1"  # Increment count.
done
