package payment.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codef.api.EasyCodef;
import io.codef.api.EasyCodefServiceType;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class CreateAccountTest {

//    @Test
    public void createAccount() throws JsonProcessingException, UnsupportedEncodingException, InterruptedException {
        EasyCodef codef = new EasyCodef();

        codef.setClientInfoForDemo(EasyCodeClientInfoTest.DEMO_CLIENT_ID, EasyCodeClientInfoTest.DEMO_CLIENT_SECRET);

        codef.setPublicKey(EasyCodeClientInfoTest.PUBLIC_KEY);

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

        /**	#7.결과 확인	*/
        System.out.println(result);

        HashMap<String, Object> responseMap = new ObjectMapper().readValue(result, HashMap.class);
        HashMap<String, Object> resultMap = (HashMap<String, Object>)responseMap.get("result");

        assertEquals("코드에프 상품 요청 결과 실패(반환된 코드와 메시지 확인 필요)", "CF-00000", (String)resultMap.get("code"));
    }
}
