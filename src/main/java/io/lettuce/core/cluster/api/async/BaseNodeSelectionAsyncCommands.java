/*
 * Copyright 2017-Present, Redis Ltd. and Contributors
 * All rights reserved.
 *
 * Licensed under the MIT License.
 *
 * This file contains contributions from third-party contributors
 * licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.lettuce.core.cluster.api.async;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import io.lettuce.core.output.CommandOutput;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.ProtocolKeyword;

/**
 * Asynchronous executed commands on a node selection for basic commands.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Mark Paluch
 * @author Ali Takavci
 * @since 4.0
 * @generated by io.lettuce.apigenerator.CreateAsyncNodeSelectionClusterApi
 */
public interface BaseNodeSelectionAsyncCommands<K, V> {

    /**
     * Post a message to a channel.
     *
     * @param channel the channel type: key.
     * @param message the message type: value.
     * @return Long integer-reply the number of clients that received the message.
     */
    AsyncExecutions<Long> publish(K channel, V message);

    /**
     * Lists the currently *active channels*.
     *
     * @return List&lt;K&gt; array-reply a list of active channels, optionally matching the specified pattern.
     */
    AsyncExecutions<List<K>> pubsubChannels();

    /**
     * Lists the currently *active channels*.
     *
     * @param channel the key.
     * @return List&lt;K&gt; array-reply a list of active channels, optionally matching the specified pattern.
     */
    AsyncExecutions<List<K>> pubsubChannels(K channel);

    /**
     * Returns the number of subscribers (not counting clients subscribed to patterns) for the specified channels.
     *
     * @param channels channel keys.
     * @return array-reply a list of channels and number of subscribers for every channel.
     */
    AsyncExecutions<Map<K, Long>> pubsubNumsub(K... channels);

    /**
     * Lists the currently *active shard channels*.
     *
     * @return List&lt;K&gt; array-reply a list of active channels.
     */
    AsyncExecutions<List<K>> pubsubShardChannels();

    /**
     * Lists the currently *active shard channels*.
     *
     * @param pattern the pattern type: patternkey (pattern).
     * @return List&lt;K&gt; array-reply a list of active channels, optionally matching the specified pattern.
     */
    AsyncExecutions<List<K>> pubsubShardChannels(K pattern);

    /**
     * Returns the number of subscribers (not counting clients subscribed to patterns) for the specified shard channels.
     *
     * @param shardChannels channel keys.
     * @return array-reply a list of channels and number of subscribers for every channel.
     * @since 6.4
     */
    AsyncExecutions<Map<K, Long>> pubsubShardNumsub(K... shardChannels);

    /**
     * Returns the number of subscriptions to patterns.
     *
     * @return Long integer-reply the number of patterns all the clients are subscribed to.
     */
    AsyncExecutions<Long> pubsubNumpat();

    /**
     * Post a message to a shard channel.
     *
     * @param shardChannel the shard channel type: key.
     * @param message the message type: value.
     * @return Long integer-reply the number of clients that received the message.
     * @since 6.4
     */
    AsyncExecutions<Long> spublish(K shardChannel, V message);

    /**
     * Echo the given string.
     *
     * @param msg the message type: value.
     * @return V bulk-string-reply.
     */
    AsyncExecutions<V> echo(V msg);

    /**
     * Return the role of the instance in the context of replication.
     *
     * @return List&lt;Object&gt; array-reply where the first element is one of master, slave, sentinel and the additional
     *         elements are role-specific.
     */
    AsyncExecutions<List<Object>> role();

    /**
     * Ping the server.
     *
     * @return String simple-string-reply.
     */
    AsyncExecutions<String> ping();

    /**
     * Instructs Redis to disconnect the connection. Note that if auto-reconnect is enabled then Lettuce will auto-reconnect if
     * the connection was disconnected. Use {@link io.lettuce.core.api.StatefulConnection#close} to close connections and
     * release resources.
     *
     * @return String simple-string-reply always OK.
     */
    AsyncExecutions<String> quit();

    /**
     * Wait for replication.
     *
     * @param replicas minimum number of replicas.
     * @param timeout timeout in milliseconds.
     * @return number of replicas.
     */
    AsyncExecutions<Long> waitForReplication(int replicas, long timeout);

    /**
     * Dispatch a command to the Redis Server. Please note the command output type must fit to the command response.
     *
     * @param type the command, must not be {@code null}.
     * @param output the command output, must not be {@code null}.
     * @param <T> response type.
     * @return the command response.
     * @deprecated since 6.2, as {@link CommandOutput} is being reused for all responses of all nodes and that leads to unwanted
     *             behavior. Use {@link #dispatch(ProtocolKeyword, Supplier)} instead.
     */
    @Deprecated
    <T> AsyncExecutions<T> dispatch(ProtocolKeyword type, CommandOutput<K, V, T> output);

    /**
     * Dispatch a command to the Redis Server. Please note the command output type must fit to the command response.
     *
     * @param type the command, must not be {@code null}.
     * @param outputSupplier the command output supplier, must not be {@code null}.
     * @param <T> response type.
     * @return the command response.
     * @since 6.2
     */
    <T> AsyncExecutions<T> dispatch(ProtocolKeyword type, Supplier<CommandOutput<K, V, T>> outputSupplier);

    /**
     * Dispatch a command to the Redis Server. Please note the command output type must fit to the command response.
     *
     * @param type the command, must not be {@code null}.
     * @param output the command output, must not be {@code null}.
     * @param args the command arguments, must not be {@code null}.
     * @param <T> response type.
     * @return the command response.
     * @deprecated since 6.2, as {@link CommandOutput} is being reused for all responses of all nodes and that leads to unwanted
     *             behavior. Use {@link #dispatch(ProtocolKeyword, Supplier, CommandArgs)} instead.
     */
    @Deprecated
    <T> AsyncExecutions<T> dispatch(ProtocolKeyword type, CommandOutput<K, V, T> output, CommandArgs<K, V> args);

    /**
     * Dispatch a command to the Redis Server. Please note the command output type must fit to the command response.
     *
     * @param type the command, must not be {@code null}.
     * @param outputSupplier the command output supplier, must not be {@code null}.
     * @param args the command arguments, must not be {@code null}.
     * @param <T> response type.
     * @return the command response.
     * @since 6.2
     */
    <T> AsyncExecutions<T> dispatch(ProtocolKeyword type, Supplier<CommandOutput<K, V, T>> outputSupplier,
            CommandArgs<K, V> args);

}
