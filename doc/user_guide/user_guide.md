# User Guide 

## Prerequisites

* A running Exasol cluster with a version 6.0 or later.
* An AWS account with all necessary permissions to read from a Kinesis Stream.
* [AWS Access Keys][aws-credentials]
* An AWS Kinesis producer which sends the data to a stream in the valid JSON
 format. 
 An example of a valid JSON string: 
 
    {"sensorId": 17,"currentTemperature": 147,"status": "WARN"}
  
  
See an example of an invalid JSON format. Note the trailing comma.
 
     {"sensorId": 17,"currentTemperature": 147,"status": "WARN",}
     
 The Exasol Kinesis Connector will not parse this string correctly.

## Download the JAR file

You can download the latest assembled (with all dependencies included) JAR file
from [Github releases][jars].

## Create Exasol Bucket

Next, you need to upload the jar file to a bucket in the Exasol bucket file
system (BucketFS). This will allow us to reference the jar in UDF scripts.

> Please see the section "The synchronous cluster file system BucketFS"
> in the EXASolution User Manual for more details about BucketFS.

For this guide we are going to use an example bucket named `kinesis`.

## Upload the JAR file to the bucket

Then, upload the jar file to the bucket `kinesis`. However, before uploading the
jar, please make sure that the BucketFS ports are open. In this example, we use
the port number `2580` for http.

Upload the jar file using curl:

```bash
curl -X PUT -T cloud-storage-etl-udfs-<VERSION>.jar \
  http://w:write-password@exasol.datanode.domain.com:2580/kinesis/exasol-kinesis-connector-extension-<VERSION>.jar
```

> Please also check out Exasol [BucketFS Explorer][bucketfs-explorer] as an
> alternative option to upload jar file to buckets in BucketFS.

## Preparing a Table for Data

To store the data from a stream, we need a table. 
The columns in the Exasol table have to imitate the data types of the data
 stored in the stream and be in the exact order.
 
The table also requires two additional columns to store the Kinesis metadata: 

    KINESIS_SHARD_ID VARCHAR(2000), 
    SHARD_SEQUENCE_NUMBER VARCHAR(2000)

These two columns must be at the end of the table.
 
As an example, take the following stream of data:

    {"sensorId": 17,"currentTemperature": 147,"status": "WARN"}
     
The  corresponding Exasol table should look like:

```sql
CREATE SCHEMA <schema_name>;

CREATE OR REPLACE TABLE <schema_name>.<table_name> 
    (SENSORID DECIMAL(18,0), 
    CURRENTTEMPERATURE DECIMAL(18,0), 
    STATUS VARCHAR(100), 
-- Required for importing data from Kinesis
    KINESIS_SHARD_ID VARCHAR(130), 
    SHARD_SEQUENCE_NUMBER VARCHAR(2000));

```

### JSON Data Mapping

| JSON Data Type       | Support | Recommended Exasol Column Types |
|----------------------|---------|---------------------------------|
| Array                | *       | VARCHAR, CHAR                   |
| Boolean              | Yes     | BOOLEAN                         |
| Number               | Yes     | DECIMAL, DOUBLE PRECISION       |
| Object (JSON Object) | *       | VARCHAR, CHAR                   |
| String               | Yes     | VARCHAR, CHAR                   |

Null values are supported.

* Currently, the connector has a flat data mapping and does not support mapping of the nested JSON Objects and Arrays.
All nested Objects and Arrays will be mapped to a String. That means you need to prepare a VARCHAR column for them.

## Create ETL UDFs Scripts

Create the following UDF scripts. Please do not change the names of the scripts.

```sql
--/
CREATE OR REPLACE JAVA SET SCRIPT KINESIS_METADATA (...) 
EMITS (KINESIS_SHARD_ID VARCHAR(130), SHARD_SEQUENCE_NUMBER VARCHAR(2000)) AS
  %scriptclass com.exasol.cloudetl.kinesis.KinesisShardsMetadataReader;
  %jar /buckets/bfsdefault/kinesis/exasol-kinesis-connector-extension-<VERSION>.jar;
/
;
  
--/
CREATE OR REPLACE JAVA SET SCRIPT KINESIS_IMPORT (...) EMITS (...) AS
  %scriptclass com.exasol.cloudetl.kinesis.KinesisShardDataImporter;
  %jar /buckets/bfsdefault/kinesis/exasol-kinesis-connector-extension-<VERSION>.jar;
/
;
    
--/
CREATE OR REPLACE JAVA SET SCRIPT KINESIS_PATH (...) EMITS (...) AS
  %scriptclass com.exasol.cloudetl.kinesis.KinesisImportQueryGenerator;
  %jar /buckets/bfsdefault/kinesis/exasol-kinesis-connector-extension-<VERSION>.jar;
/
; 
```

## Import Data

You can provide AWS credentials using:

1. UDF parameters 
1. [Exasol named connection object][exa-connection]. 

We highly recommend you **use connection objects** to provide credentials 
to UDFs so that credentials do not display in audit logs.

### Using the Connection Object

First, create a named connection object and encode credentials as key-value
pairs separated by semicolon (`;`).

`AWS_SESSION_TOKEN` is an optional property. You can remove it from the string
if you don't need it. 

```sql
CREATE OR REPLACE CONNECTION KINESIS_CONNECTION
TO ''
USER ''
IDENTIFIED BY 'AWS_ACCESS_KEY=<AWS_ACCESS_KEY>;AWS_SECRET_KEY=<AWS_SECRET_KEY>;AWS_SESSION_TOKEN=<AWS_SESSION_TOKEN>';
```

Run an import query.
 
```sql
IMPORT INTO <table_name>
FROM SCRIPT KINESIS_PATH WITH
  TABLE_NAME = '<table_name>'
  CONNECTION_NAME = 'KINESIS_CONNECTION'
  STREAM_NAME = '<stream_name>'
  REGION = '<region>'
;
``` 

### Without Connection Object

Provide credentials as properties and run an import query.

**Attention! Providing credentials via properties is 
deprecated and will be removed in future releases.**
 
```sql
IMPORT INTO <table_name>
FROM SCRIPT KINESIS_PATH WITH
  TABLE_NAME = '<table_name>'
  AWS_ACCESS_KEY = '<access_key>'
  AWS_SECRET_KEY = '<secret_key>'
  AWS_SESSION_TOKEN = '<session_token>'
  STREAM_NAME = '<stream_name>'
  REGION = '<region>'
;
```

### Properties

Property name        | Required    | Description
---------------------|-------------|----------------------------------------------------------------
AWS_ACCESS_KEY       |  Mandatory  | An AWS access key id. Can be provided via connection object.                    
AWS_SERVICE_ENDPOINT |  Optional   | An endpoint is the URL of the entry point for an AWS web service.                    
AWS_SECRET_KEY       |  Mandatory  | An AWS secret key. Can be provided via connection object.                   
AWS_SESSION_TOKEN    |  Optional   | An AWS session token. Can be provided via connection object.                   
CONNECTION_NAME      |  Optional   | A name of connection with defined credentials properties.                   
STREAM_NAME          |  Mandatory  | A name of the stream to consume the data from   
TABLE_NAME           |  Mandatory  | A name of an Exasol table to store the data.                                   
MAX_RECORDS_PER_RUN  |  Optional   | The maximum number of records to return per shard. A value between 1 and 10000.                   
REGION               |  Mandatory  | An AWS region where a stream is localed                   

[aws-credentials]: https://docs.aws.amazon.com/general/latest/gr/aws-sec-cred-types.html
[exa-connection]: https://docs.exasol.com/sql/create_connection.htm
[jars]: https://github.com/exasol/kinesis-connector-extension/releases
[bucketfs-explorer]: https://github.com/exasol/bucketfs-explorer