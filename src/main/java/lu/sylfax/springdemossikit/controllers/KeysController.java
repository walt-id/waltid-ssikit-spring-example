package lu.sylfax.springdemossikit.controllers;

import id.walt.crypto.Key;
import lu.sylfax.springdemossikit.services.KeysBusinessServices;
import lu.sylfax.springdemossikit.vo.KeyAlgorithmInputVO;
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
    public ResponseEntity createKey(@RequestBody KeyAlgorithmInputVO key) {

        logger.info("Calling key generation services with the following algorithm: " + key.getKeyAlgorithm());
        keysBusinessServices.generateKey(key.getKeyAlgorithm());
        return new ResponseEntity(null, null, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Key>> getAllKeys() {

        logger.info("Calling get all keys services");
        List<Key> keys = keysBusinessServices.getAllStoredKeys();
        return new ResponseEntity(null, null, HttpStatus.OK);
    }
}
