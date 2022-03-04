package lu.sylfax.springdemossikit.controllers;

import id.walt.crypto.Key;
import lu.sylfax.springdemossikit.services.KeysBusinessServices;
import lu.sylfax.springdemossikit.vo.KeyAlgorithmInputVO;
import lu.sylfax.springdemossikit.vo.output.KeyAliasOutputVO;
import lu.sylfax.springdemossikit.vo.output.KeyOutputVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/keys")
public class KeysController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final KeysBusinessServices keysBusinessServices;

    public KeysController(KeysBusinessServices keysBusinessServices) {
        this.keysBusinessServices = keysBusinessServices;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KeyAliasOutputVO> createKey(@RequestBody KeyAlgorithmInputVO key) {

        logger.info("Calling key generation services with the following algorithm: " + key.getKeyAlgorithm());
        KeyAliasOutputVO keyAliasOutputVO = keysBusinessServices.generateKey(key.getKeyAlgorithm());
        return new ResponseEntity(keyAliasOutputVO, null, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/alias", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KeyAliasOutputVO>> getAllKeysAlias() {

        logger.info("Calling get all keys services");
        List<KeyAliasOutputVO> keyAliasOutputVOList = keysBusinessServices.getAllStoredKeysAlias();
        return new ResponseEntity(keyAliasOutputVOList, null, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/alias/{alias}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KeyOutputVO> getKeyByAlias(@PathVariable("alias") String alias) {

        logger.info("Calling get key by alias services");
        KeyOutputVO keyOutputVO = keysBusinessServices.getKeyByAlias(alias);
        return new ResponseEntity(keyOutputVO, null, HttpStatus.OK);
    }
}
