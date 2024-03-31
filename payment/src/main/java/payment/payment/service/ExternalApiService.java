package payment.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.codef.api.EasyCodef;
import io.codef.api.EasyCodefServiceType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import payment.payment.EasyCodeClientInfo;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@Service
public class ExternalApiService {

    // 외부 api에서 데이터 get

    @Value("${external.api.url}")
    private String apiUrl;

    public JSONArray fetchData(String startDate, String endDate) throws JsonProcessingException, UnsupportedEncodingException, InterruptedException, ParseException {
        JSONParser parser = new JSONParser();
        EasyCodef codef = new EasyCodef();

        codef.setClientInfoForDemo(EasyCodeClientInfo.DEMO_CLIENT_ID, EasyCodeClientInfo.DEMO_CLIENT_SECRET);

        codef.setPublicKey(EasyCodeClientInfo.PUBLIC_KEY);

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
        String result = codef.requestProduct(url, EasyCodefServiceType.DEMO, parameterMap); // JSON 출력

        JSONObject parseResult = (JSONObject) parser.parse(result);
        JSONArray dataResult = (JSONArray) parseResult.get("data");

        return dataResult;

//        ExternalApiResponse[] responseArray = restTemplate.getForObject(apiUrl, ExternalApiResponse[].class);
//        System.out.println("responseArray = " + responseArray);
//        return Arrays.asList(responseArray);
    }
}
