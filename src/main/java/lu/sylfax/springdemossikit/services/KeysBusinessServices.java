package lu.sylfax.springdemossikit.services;

import id.walt.crypto.Key;
import id.walt.crypto.KeyId;

import java.util.List;

public interface KeysBusinessServices {

    KeyId generateKey(String keyAlgorithm);

    List<Key> getAllStoredKeys();
}
