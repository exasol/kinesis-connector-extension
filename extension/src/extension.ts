import { ExasolExtension, registerExtension } from "@exasol/extension-manager-interface";
import { JavaBaseExtension, ScriptDefinition, convertBaseExtension, jarFileVersionExtractor } from "@exasol/extension-manager-interface/dist/base";
import { EXTENSION_DESCRIPTION } from "./extension-description";

/** Script definitions for the required scripts. */
const SCRIPTS: ScriptDefinition[] = [
    {
        name: "KINESIS_METADATA",
        type: "SET",
        args: `KINESIS_SHARD_ID VARCHAR(130), SHARD_SEQUENCE_NUMBER VARCHAR(2000)`,
        scriptClass: "com.exasol.cloudetl.kinesis.KinesisShardsMetadataReader"
    },
    {
        name: "KINESIS_IMPORT",
        type: "SET",
        args: "...",
        scriptClass: "com.exasol.cloudetl.kinesis.KinesisShardDataImporter"
    },
    {
        name: "KINESIS_CONSUMER",
        type: "SET",
        args: "...",
        scriptClass: "com.exasol.cloudetl.kinesis.KinesisImportQueryGenerator"
    }
]

export function createExtension(): ExasolExtension {
    const baseExtension: JavaBaseExtension = {
        name: "Kinesis Connector Extension",
        description: "Exasol Kinesis Extension for accessing Amazon Kinesis Data Streams",
        category: "cloud-storage-importer",
        version: EXTENSION_DESCRIPTION.version,
        file: { name: EXTENSION_DESCRIPTION.fileName, size: EXTENSION_DESCRIPTION.fileSizeBytes },
        scripts: SCRIPTS,
        scriptVersionExtractor: jarFileVersionExtractor(/exasol-kinesis-connector-extension-(\d+\.\d+\.\d+).jar/)
    }
    return convertBaseExtension(baseExtension)
}

registerExtension(createExtension())
