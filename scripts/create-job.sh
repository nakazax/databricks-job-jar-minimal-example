#!/bin/bash

if [ -z "$1" ] || [ -z "$2" ]; then
  echo -e "Usage: $0 <jar_path>"
  exit 1
fi

JAR_PATH=$1
echo Creating job with parameters: jar_path: $JAR_PATH

databricks jobs create --json '{
  "name": "databricks_job_jar_minimal_example",
  "tasks": [
    {
      "task_key": "Task1",
      "spark_jar_task": {
        "main_class_name": "com.example.Main",
        "parameters": [
          "{{job.parameters.wait_seconds}}",
          "{{job.parameters.exit_code}}"
        ],
        "run_as_repl": true
      },
      "job_cluster_key": "single_node_job_cluster",
      "libraries": [
        {
          "jar": "'"$JAR_PATH"'"
        }
      ]
    }
  ],
  "job_clusters": [
    {
      "job_cluster_key": "single_node_job_cluster",
      "new_cluster": {
        "spark_version": "14.3.x-scala2.12",
        "spark_conf": {
          "spark.master": "local[*, 4]",
          "spark.databricks.cluster.profile": "singleNode"
        },
        "aws_attributes": {
          "first_on_demand": 1,
          "availability": "SPOT_WITH_FALLBACK",
          "spot_bid_price_percent": 100,
          "ebs_volume_count": 0
        },
        "node_type_id": "i3.xlarge",
        "custom_tags": {
          "ResourceClass": "SingleNode"
        },
        "enable_elastic_disk": false,
        "data_security_mode": "SINGLE_USER",
        "runtime_engine": "STANDARD",
        "num_workers": 0
      }
    }
  ],
  "parameters": [
    {
      "name": "exit_code",
      "default": "0"
    },
    {
      "name": "wait_seconds",
      "default": "10"
    }
  ]
}'
