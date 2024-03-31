package payment.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.codef.api.EasyCodef;
import io.codef.api.EasyCodefServiceType;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@Slf4j
public class ApprovalTest {

//    @Test
    public void dateRange() throws UnsupportedEncodingException, ParseException, JsonProcessingException, InterruptedException {
        approval("20240201", "20240205");
    }

    public void approval(String startDate, String endDate) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException, ParseException {

        JSONParser parser = new JSONParser();

        EasyCodef codef = new EasyCodef();
        codef.setClientInfoForDemo(EasyCodeClientInfoTest.DEMO_CLIENT_ID, EasyCodeClientInfoTest.DEMO_CLIENT_SECRET);
        codef.setPublicKey(EasyCodeClientInfoTest.PUBLIC_KEY);

        HashMap<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("connectedId", "4ohe-CG04iSbN6POoE.4qs");
        parameterMap.put("organization", "0311");
        parameterMap.put("inquiryType", "0");
        parameterMap.put("cardName", "경차 smart 롯데카드|MASTER");
        parameterMap.put("cardNo", "5137920050981731");
        parameterMap.put("startDate", startDate);
        parameterMap.put("endDate", endDate);
        parameterMap.put("orderBy", "1");

        String url = "/v1/kr/card/p/account/approval-list";
        String result = codef.requestProduct(url, EasyCodefServiceType.DEMO, parameterMap);

        System.out.println("result = " + result);
        System.out.println(result.getClass()); // String

        JSONObject object = (JSONObject) parser.parse(result);
        System.out.println("object.getClass() = " + object.getClass()); // JSONObject
        System.out.println("object = " + object);

        JSONArray dataResult = (JSONArray) object.get("data");
        System.out.println("dataResult.getClass() = " + dataResult.getClass());
        System.out.println("dataResult = " + dataResult);

        for (int i = 0; i < dataResult.size(); i++) {
            object = (JSONObject) dataResult.get(i);
            Object cardName = object.get("resCardName");
            System.out.println("cardName = " + cardName);
        }

//        HashMap<String, Object> responseMap = new ObjectMapper().readValue(result, HashMap.class);
//        HashMap<String, Object> resultMap = (HashMap<String, Object>) responseMap.get("result");
//        log.info("resultMap={}", resultMap);
//
//        ArrayList resultData = (ArrayList) responseMap.get("data");
//        log.info("resultData={}", resultData);

//        for (Object data : resultData) {
//
//            log.info("data={}", data); // {key, value}
//
//            HashMap<String, Object> mapData = (HashMap<String, Object>) data;
//            log.info("mapData={}", mapData);
//
//            String cardName = (String) mapData.get("resCardName");
//            String usedAmount = (String) mapData.get("resUsedAmount");
//            String usedDate = (String) mapData.get("resUsedDate");
//
//            infoMap.put("cardName", cardName);
//            infoMap.put("usedAmount", usedAmount);
//            infoMap.put("usedDate", usedDate);
//
//            jsonObject.putAll(infoMap);
//
//            log.info("jsonObject={}", jsonObject);
//        }

//        Assertions.assertEquals("CF-00000", (String)resultMap.get("code"));
    }
}
