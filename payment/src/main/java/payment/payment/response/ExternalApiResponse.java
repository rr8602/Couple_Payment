package payment.payment.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
public class ExternalApiResponse {

    private Long id;
    private String cardName;
    private String usedAmount;
    private String storeName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate usedDate;
    private LocalTime usedTime;
}
