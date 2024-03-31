package payment.payment.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class SearchResultDto {

    private Long id;
    private String cardName;
    private String usedAmount;
    private String storeName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private LocalTime usedTime;
}
