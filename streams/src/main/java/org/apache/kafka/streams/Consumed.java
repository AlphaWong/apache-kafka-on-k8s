/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.kafka.streams;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.processor.TimestampExtractor;

/**
 * The {@code Consumed} class is used to define the optional parameters when using {@link StreamsBuilder} to
 * build instancs of {@link KStream}, {@link KTable}, and {@link GlobalKTable}.
 *
 * @param <K>
 * @param <V>
 */
public class Consumed<K, V> {

    protected Serde<K> keySerde;
    protected Serde<V> valueSerde;
    protected TimestampExtractor timestampExtractor;
    protected Topology.AutoOffsetReset resetPolicy;

    private Consumed(final Serde<K> keySerde,
                     final Serde<V> valueSerde,
                     final TimestampExtractor timestampExtractor,
                     final Topology.AutoOffsetReset resetPolicy) {

        this.keySerde = keySerde;
        this.valueSerde = valueSerde;
        this.timestampExtractor = timestampExtractor;
        this.resetPolicy = resetPolicy;
    }

    /**
     * Create an instance of {@link Consumed} from an existing instance.
     * @param consumed  the instance of {@link Consumed} to copy
     */
    protected Consumed(final Consumed<K, V> consumed) {
        this(consumed.keySerde, consumed.valueSerde, consumed.timestampExtractor, consumed.resetPolicy);
    }

    /**
     * Create an instance of {@link Consumed} with the supplied arguments. {@code null} values are acceptable.
     *
     * @param keySerde           the key serde. If {@code null} the default key serde from config will be used
     * @param valueSerde         the value serde. If {@code null} the default value serde from config will be used
     * @param timestampExtractor the timestamp extractor to used. If {@code null} the default timestamp extractor from config will be used
     * @param resetPolicy        the offset reset policy to be used. If {@code null} the default reset policy from config will be used
     * @param <K>                key type
     * @param <V>                value type
     * @return a new instance of {@link Consumed}
     */
    public static <K, V> Consumed<K, V> with(final Serde<K> keySerde,
                                             final Serde<V> valueSerde,
                                             final TimestampExtractor timestampExtractor,
                                             final Topology.AutoOffsetReset resetPolicy) {
        return new Consumed<>(keySerde, valueSerde, timestampExtractor, resetPolicy);

    }

    /**
     * Create an instance of {@link Consumed} with key and value {@link Serde}s.
     *
     * @param keySerde   the key serde. If {@code null}the default key serde from config will be used
     * @param valueSerde the value serde. If {@code null} the default value serde from config will be used
     * @param <K>        key type
     * @param <V>        value type
     * @return a new instance of {@link Consumed}
     */
    public static <K, V> Consumed<K, V> with(final Serde<K> keySerde,
                                             final Serde<V> valueSerde) {
        return new Consumed<>(keySerde, valueSerde, null, null);
    }

    /**
     * Create an instance of {@link Consumed} with a {@link TimestampExtractor}.
     *
     * @param timestampExtractor the timestamp extractor to used. If {@code null} the default timestamp extractor from config will be used
     * @param <K>                key type
     * @param <V>                value type
     * @return a new instance of {@link Consumed}
     */
    public static <K, V> Consumed<K, V> with(final TimestampExtractor timestampExtractor) {
        return new Consumed<>(null, null, timestampExtractor, null);
    }

    /**
     * Create an instance of {@link Consumed} with a {@link Topology.AutoOffsetReset}.
     *
     * @param resetPolicy the offset reset policy to be used. If {@code null} the default reset policy from config will be used
     * @param <K>         key type
     * @param <V>         value type
     * @return a new instance of {@link Consumed}
     */
    public static <K, V> Consumed<K, V> with(final Topology.AutoOffsetReset resetPolicy) {
        return new Consumed<>(null, null, null, resetPolicy);
    }

    /**
     * Configure the instance of {@link Consumed} with a key {@link Serde}.
     *
     * @param keySerde the key serde. If {@code null}the default key serde from config will be used
     * @return this
     */
    public Consumed<K, V> withKeySerde(final Serde<K> keySerde) {
        this.keySerde = keySerde;
        return this;
    }

    /**
     * Configure the instance of {@link Consumed} with a value {@link Serde}.
     *
     * @param valueSerde the value serde. If {@code null} the default value serde from config will be used
     * @return this
     */
    public Consumed<K, V> withValueSerde(final Serde<V> valueSerde) {
        this.valueSerde = valueSerde;
        return this;
    }

    /**
     * Configure the instance of {@link Consumed} with a {@link TimestampExtractor}.
     *
     * @param timestampExtractor the timestamp extractor to used. If {@code null} the default timestamp extractor from config will be used
     * @return this
     */
    public Consumed<K, V> withTimestampExtractor(final TimestampExtractor timestampExtractor) {
        this.timestampExtractor = timestampExtractor;
        return this;
    }

    /**
     * Configure the instance of {@link Consumed} with a {@link Topology.AutoOffsetReset}.
     *
     * @param resetPolicy the offset reset policy to be used. If {@code null} the default reset policy from config will be used
     * @return this
     */
    public Consumed<K, V> withOffsetResetPolicy(final Topology.AutoOffsetReset resetPolicy) {
        this.resetPolicy = resetPolicy;
        return this;
    }
}