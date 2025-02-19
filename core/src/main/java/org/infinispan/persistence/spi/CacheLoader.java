package org.infinispan.persistence.spi;

import org.infinispan.commons.api.Lifecycle;

import net.jcip.annotations.ThreadSafe;

/**
 * Defines the logic for loading data from an external storage. The writing of data is optional and coordinated through
 * a {@link CacheWriter}.
 *
 * @author Mircea Markus
 * @since 6.0
 * @deprecated since 11.0 replaced by {@link NonBlockingStore}
 */
@ThreadSafe
@Deprecated(forRemoval=true)
public interface CacheLoader<K, V> extends Lifecycle {

   /**
    * Used to initialize a cache loader.  Typically invoked by the {@link org.infinispan.persistence.manager.PersistenceManager}
    * when setting up cache loaders.
    *
    * @throws PersistenceException in case of an error, e.g. communicating with the external storage
    */
   void init(InitializationContext ctx);

   /**
    * Fetches an entry from the storage. If a {@link MarshallableEntry} needs to be created here, {@link
    * InitializationContext#getMarshallableEntryFactory()} ()} and {@link
    * InitializationContext#getByteBufferFactory()} should be used.
    *
    * @return the entry, or null if the entry does not exist
    * @throws PersistenceException in case of an error, e.g. communicating with the external storage
    */
   MarshallableEntry<K, V> loadEntry(Object key);

   /**
    * Returns true if the storage contains an entry associated with the given key.
    *
    * @throws PersistenceException in case of an error, e.g. communicating with the external storage
    */
   boolean contains(Object key);

   /**
    * @return true if the writer can be connected to, otherwise false
    */
   default boolean isAvailable() {
      return true;
   }
}
