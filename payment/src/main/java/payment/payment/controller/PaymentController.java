package payment.payment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import payment.payment.entity.LocalData;
import payment.payment.service.DataService;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final DataService dataService;

    @GetMapping("/search")
    public String search(Model model) {
        return "search-form";
    }

    @PostMapping("/search")
    public String searchData(@RequestParam(required = true) String startDate,
                             @RequestParam(required = true) String endDate,
                             Model model){
        return "redirect:/search-result?startDate=" + startDate + "&endDate=" + endDate;
    }

    @GetMapping("/search-result")
    public String result(@RequestParam(required = false) String startDate,
                         @RequestParam(required = false) String endDate,
                         Model model)
            throws UnsupportedEncodingException, ParseException, JsonProcessingException, InterruptedException {

        String resultStartDate = startDate.replace("-", "");
        String resultEndDate = endDate.replace("-", "");

        dataService.importDataFromExternalApi(resultStartDate, resultEndDate);

        List<LocalData> resultData = dataService.getAllLocalData();

        model.addAttribute("resultData", resultData);
        return "search-result";
    }
}
