package payment.payment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import payment.payment.dto.PaymentSearchCondition;
import payment.payment.entity.LocalData;

import java.util.List;

import static payment.payment.entity.QLocalData.localData;

public class PaymentRepositoryImpl extends QuerydslRepositorySupport implements PaymentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PaymentRepositoryImpl(JPAQueryFactory queryFactory) {
        super(LocalData.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<String> search(PaymentSearchCondition condition) {

        List<String> result = queryFactory
                .select(localData.storeName)
                .from(localData)
                .fetch();

        return result;
    }
}
