package payment.payment.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.codef.api.EasyCodef;
import io.codef.api.EasyCodefServiceType;
import lombok.extern.slf4j.Slf4j;
import payment.payment.ClientInfo;
import payment.payment.EasyCodeUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class CreateAccount {

    public void createAccount() throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
        EasyCodef codef = new EasyCodef();

        codef.setClientInfoForDemo(ClientInfo.DEMO_CLIENT_ID, ClientInfo.DEMO_CLIENT_SECRET);

        codef.setPublicKey(ClientInfo.PUBLIC_KEY);

        // 요청 파라미터 설정
        List<HashMap<String, Object>> accountList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> accountMap = new HashMap<String, Object>();
        accountMap.put("countryCode", "KR");
        accountMap.put("businessType", "CD");
        accountMap.put("clientType", "P");
        accountMap.put("organization", "0311");
        accountMap.put("loginType", "1");
        accountMap.put("id", "rr8602");

        try {
            accountMap.put("password", EasyCodeUtil.encryptRSA("wjdqudwls1!", codef.getPublicKey())); // RSA암호화가 필요한 필드는 encryptRSA(String plainText, String publicKey) 메서드를 이용해 암호화
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        accountList.add(accountMap);

        HashMap<String, Object> parameterMap = new HashMap<String, Object> ();
        parameterMap.put("accountList", accountList);

        /**	#6.요청	*/
        String result = codef.createAccount(EasyCodefServiceType.DEMO, parameterMap);

        log.info("result={}", result);
    }
}
