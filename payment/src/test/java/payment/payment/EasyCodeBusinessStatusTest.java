package payment.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codef.api.EasyCodef;
import io.codef.api.EasyCodefServiceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class EasyCodeBusinessStatusTest {

    // 개인 보유카드 조회 (Connected ID 사용)
//    @Test
    public void usageExample() throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
        EasyCodef codef = new EasyCodef();

        codef.setClientInfoForDemo(EasyCodeClientInfoTest.DEMO_CLIENT_ID, EasyCodeClientInfoTest.DEMO_CLIENT_SECRET);

        codef.setPublicKey(EasyCodeClientInfoTest.PUBLIC_KEY);

        // 요청 파라미터 설정
        HashMap<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("connectedId", "4ohe-CG04iSbN6POoE.4qs");
        parameterMap.put("organization", "0311");
        parameterMap.put("birthDate", "1993-03-26");
        parameterMap.put("inquiryType", "0");

        // 코드에프 정보 조회 요청 - 서비스타입(API:정식, DEMO:데모, SANDBOX:샌드박스)
        String productUrl = "/v1/kr/card/p/account/card-list";
        String result = codef.requestProduct(productUrl, EasyCodefServiceType.DEMO, parameterMap);

        // 코드에프 정보 결과 확인
        System.out.println("result = " + result);

        HashMap<String, Object> responseMap = new ObjectMapper().readValue(result, HashMap.class);
        System.out.println("responseMap = " + responseMap);
        HashMap<String, Object> resultMap = (HashMap<String, Object>) responseMap.get("result");
        System.out.println("resultMap = " + resultMap);

        Assertions.assertEquals("CF-00000", (String)resultMap.get("code"), "코드에프 상품 요청 결과 실패(반환된 코드와 메시지 확인 필요)");
    }

}
