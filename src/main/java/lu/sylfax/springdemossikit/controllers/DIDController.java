package lu.sylfax.springdemossikit.controllers;

import lu.sylfax.springdemossikit.services.DIDBusinessServices;
import lu.sylfax.springdemossikit.vo.DIDInputVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/dids")
public class DIDController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DIDBusinessServices didBusinessServices;


    public DIDController(DIDBusinessServices didBusinessServices) {
        this.didBusinessServices = didBusinessServices;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createDID(@RequestBody DIDInputVO did) {

        logger.info("Calling DID generation services for the following type: " + did.getDidType());
        String result = didBusinessServices.generateDID(did.getKeyId(), did.getDidType());
        return new ResponseEntity(result, null, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/{alias}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDIDByAlias(@PathVariable("alias") String alias) {

        logger.info("Calling DID loading services for the following alias: " + alias);
        String result = didBusinessServices.getDIDbyAlias(alias);
        return new ResponseEntity(result, null, HttpStatus.OK);
    }


}
