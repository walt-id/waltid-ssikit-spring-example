package lu.sylfax.springdemossikit.services;

import id.walt.model.Did;
import id.walt.model.DidMethod;
import id.walt.services.did.DidService;
import id.walt.services.essif.didebsi.DidEbsiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DIDBusinessServicesImpl implements DIDBusinessServices {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DidEbsiService didEbsiService;
    private final KeysBusinessServices keysBusinessServices;


    public DIDBusinessServicesImpl(KeysBusinessServices keysBusinessServices) {
        this.keysBusinessServices = keysBusinessServices;
        this.didEbsiService = DidEbsiService.Companion.getService();
    }


    @Override
    public String generateDID(String keyId, String didType) {

        String did = DidService.INSTANCE.create(DidMethod.valueOf(didType), keyId, null);
        return did;
    }

    @Override
    public String getDIDbyAlias(String alias) {

        Did did = DidService.INSTANCE.load(alias);
        return did.encodePretty();
    }
}
