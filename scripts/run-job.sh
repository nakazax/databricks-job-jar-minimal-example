#!/bin/bash

if [ -z "$1" ]; then
  echo "Error: job_id is required."
  echo "Usage: $0 <job_id> [wait_seconds] [exit_code]"
  exit 1
fi

JOB_ID=$1
WAIT_SECONDS=${2:-0}
EXIT_CODE=${3:-0}

cat <<EOM
Triggering job with parameters:
  job_id: $JOB_ID
  wait_seconds: $WAIT_SECONDS
  exit_code: $EXIT_CODE
EOM

databricks jobs run-now --json '{
  "job_id": '$JOB_ID',
  "job_parameters": {
    "wait_seconds": "'$WAIT_SECONDS'",
    "exit_code": "'$EXIT_CODE'"
  }
}'

job_run_status=$?
if [ $job_run_status -eq 0 ]; then
  echo "Job completed successfully."
  exit 0
else
  echo "Job failed with status: $job_run_status"
  exit 1
fi
