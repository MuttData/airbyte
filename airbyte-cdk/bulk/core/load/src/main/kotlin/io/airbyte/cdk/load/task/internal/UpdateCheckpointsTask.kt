/*
 * Copyright (c) 2024 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.cdk.load.task.internal

import io.airbyte.cdk.load.message.CheckpointMessage
import io.airbyte.cdk.load.message.CheckpointMessageWrapped
import io.airbyte.cdk.load.message.GlobalCheckpointWrapped
import io.airbyte.cdk.load.message.MessageQueue
import io.airbyte.cdk.load.message.StreamCheckpointWrapped
import io.airbyte.cdk.load.state.CheckpointManager
import io.airbyte.cdk.load.state.Reserved
import io.airbyte.cdk.load.state.SyncManager
import io.airbyte.cdk.load.task.SelfTerminating
import io.airbyte.cdk.load.task.Task
import io.airbyte.cdk.load.task.TerminalCondition
import io.github.oshai.kotlinlogging.KotlinLogging
import io.micronaut.context.annotation.Secondary
import jakarta.inject.Singleton

interface UpdateCheckpointsTask : Task

@Singleton
@Secondary
class DefaultUpdateCheckpointsTask(
    private val syncManager: SyncManager,
    private val checkpointManager: CheckpointManager<Reserved<CheckpointMessage>>,
    private val checkpointMessageQueue: MessageQueue<Reserved<CheckpointMessageWrapped>>
) : UpdateCheckpointsTask {
    val log = KotlinLogging.logger {}

    override val terminalCondition: TerminalCondition = SelfTerminating

    override suspend fun execute() {
        log.info { "Starting to consume checkpoint messages (state) for updating" }
        checkpointMessageQueue.consume().collect {
            when (it.value) {
                is StreamCheckpointWrapped -> {
                    val (_, stream, index, message) = it.value
                    log.info { "Updating checkpoint for stream $stream with index $index" }
                    checkpointManager.addStreamCheckpoint(stream, index, it.replace(message))
                }
                is GlobalCheckpointWrapped -> {
                    val (_, streamIndexes, message) = it.value
                    log.info { "Updating global checkpoint for streams $streamIndexes" }
                    checkpointManager.addGlobalCheckpoint(streamIndexes, it.replace(message))
                }
            }
        }
        syncManager.markCheckpointsProcessed()
        log.info { "All checkpoints (state) updated" }
    }
}
