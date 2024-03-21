#!/bin/bash

if [ -z "$1" ]; then
  echo "Error: JOB_ID is required."
  echo "Usage: $0 <job_id> [wait_seconds] [exit_code]"
  exit 1
fi

JOB_ID=$1
WAIT_SECONDS=${2:-1}  # デフォルトのwait_secondsを設定
EXIT_CODE=${3:-0}  # デフォルトのexit_codeを設定

echo "Triggering job with parameters:"
echo "  job_id: $JOB_ID"
echo "  wait_seconds: $WAIT_SECONDS"
echo "  exit_code: $EXIT_CODE"

databricks jobs run-now --json '{
  "job_id": '$JOB_ID',
  "job_parameters": {
    "wait_seconds": "'$WAIT_SECONDS'",
    "exit_code": "'$EXIT_CODE'"
  }
}'
