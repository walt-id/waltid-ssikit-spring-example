package lu.sylfax.springdemossikit.services;

public interface DIDBusinessServices {

    String generateDID(String keyId, String didType);

    String getDIDbyAlias(String alias);
}
