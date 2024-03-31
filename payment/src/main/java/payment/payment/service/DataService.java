package payment.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import payment.payment.entity.LocalData;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataService {

    // 데이터 가져오기 및 저장

    private final ExternalApiService externalApiService;
    private final ResponseDataService localDataService;

    public LocalData importDataFromExternalApi(String startDate, String endDate) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException, ParseException {
        JSONArray externalData = externalApiService.fetchData(startDate,endDate);
        LocalData resultData = localDataService.saveToLocalDatabase(externalData);
        return resultData;
    }

    public List<LocalData> getAllLocalData() {
        return localDataService.getAllLocalData();
    }
}
