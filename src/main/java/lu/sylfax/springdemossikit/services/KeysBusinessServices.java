package lu.sylfax.springdemossikit.services;

import id.walt.crypto.Key;
import id.walt.crypto.KeyId;
import lu.sylfax.springdemossikit.vo.output.KeyAliasOutputVO;
import lu.sylfax.springdemossikit.vo.output.KeyOutputVO;

import java.util.List;

public interface KeysBusinessServices {

    KeyAliasOutputVO generateKey(String keyAlgorithm);

    List<KeyAliasOutputVO> getAllStoredKeysAlias();

    KeyOutputVO getKeyByAlias(String alias);
}
