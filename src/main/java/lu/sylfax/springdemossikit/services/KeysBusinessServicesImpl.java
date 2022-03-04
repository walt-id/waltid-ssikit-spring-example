package lu.sylfax.springdemossikit.services;

import id.walt.crypto.Key;
import id.walt.crypto.KeyAlgorithm;
import id.walt.crypto.KeyId;
import id.walt.servicematrix.ServiceMatrix;
import id.walt.services.key.KeyService;
import lu.sylfax.springdemossikit.vo.output.KeyAliasOutputVO;
import lu.sylfax.springdemossikit.vo.output.KeyOutputVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    public KeyAliasOutputVO generateKey(String keyAlgorithm) {
        logger.info("Generating key ...");
        KeyId keyId = keyService.generate(KeyAlgorithm.valueOf(keyAlgorithm));
        KeyAliasOutputVO keyAliasOutputVO = new KeyAliasOutputVO();
        keyAliasOutputVO.setId(keyId.getId());
        return keyAliasOutputVO;
    }

    @Override
    public List<KeyAliasOutputVO> getAllStoredKeysAlias() {
        logger.info("Getting all stored keys");
        List<Key> keys = keyService.listKeys();
        List<KeyAliasOutputVO> keyAliasOutputVOList = new ArrayList<>();
        for (Key key: keys) {
            KeyAliasOutputVO keyAliasOutputVO = new KeyAliasOutputVO();
            keyAliasOutputVO.setId(key.getKeyId().getId());
            keyAliasOutputVOList.add(keyAliasOutputVO);
        }
        return keyAliasOutputVOList;
    }

    @Override
    public KeyOutputVO getKeyByAlias(String alias) {
        return null;
    }
}
