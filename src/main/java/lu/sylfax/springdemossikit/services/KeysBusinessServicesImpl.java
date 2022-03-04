package lu.sylfax.springdemossikit.services;

import id.walt.crypto.Key;
import id.walt.crypto.KeyAlgorithm;
import id.walt.crypto.KeyId;
import id.walt.servicematrix.ServiceMatrix;
import id.walt.services.key.KeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public class KeysBusinessServicesImpl implements KeysBusinessServices {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ServiceMatrix serviceMatrix;          // from SSIKIT
    private final KeyService keyService;                // from SSIKIT

    public KeysBusinessServicesImpl() throws FileNotFoundException {
        // Load walt.id SSI Kit services from "resources/service-matrix.properties"
        File serviceMatrixFile = ResourceUtils.getFile("classpath:service-matrix.properties");
        serviceMatrix = new ServiceMatrix(serviceMatrixFile.toString());
        serviceMatrix.registerServiceDefinitions();
        logger.info("Services matrix is loaded!");
        keyService = KeyService.Companion.getService();
    }

    @Override
    public KeyId generateKey(String keyAlgorithm) {
        logger.info("Generating key ...");
        KeyId keyId = keyService.generate(KeyAlgorithm.valueOf(keyAlgorithm));
        return keyId;
    }

    @Override
    public List<Key> getAllStoredKeys() {
        logger.info("Getting all stored keys");
        List<Key> keys = keyService.listKeys();
        return keys;
    }
}
