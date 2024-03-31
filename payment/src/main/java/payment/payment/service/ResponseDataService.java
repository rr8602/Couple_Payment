package payment.payment.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import payment.payment.entity.LocalData;
import payment.payment.repository.PaymentRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseDataService {

    // 로컬 DB에 데이터 저장

    private final PaymentRepository paymentRepository;

    LocalData localData=null;

    public LocalData saveToLocalDatabase(JSONArray externalData) {

        for (int i = 0; i < externalData.size(); i++) {
            JSONObject parseResult = (JSONObject) externalData.get(i);
            Object cardName = parseResult.get("resCardName");
            Object usedAmount = parseResult.get("resUsedAmount");
            Object storeName = parseResult.get("resMemberStoreName");
            String strUsedDate = (String) parseResult.get("resUsedDate");
            String strUsedTime = (String) parseResult.get("resUsedTime");

            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate usedDate = LocalDate.parse(strUsedDate, formatterDate);
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HHmmss");
            LocalTime usedTime = LocalTime.parse(strUsedTime, formatterTime);

            LocalData localData = new LocalData();
            localData.setCardName((String) cardName);
            localData.setUsedAmount((String) usedAmount);
            localData.setStoreName((String) storeName);
            localData.setUsedDate(usedDate);
            localData.setUsedTime(usedTime);

            paymentRepository.save(localData);
        }
        return localData;
    }

    public List<LocalData> getAllLocalData() {
        return paymentRepository.findAll();
    }
}
