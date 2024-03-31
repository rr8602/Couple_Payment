package payment.payment.repository;

import payment.payment.dto.PaymentSearchCondition;
import payment.payment.entity.LocalData;

import java.util.List;

public interface PaymentRepositoryCustom {

    List<String> search(PaymentSearchCondition condition);
}
