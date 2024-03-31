package payment.payment;

import io.codef.api.EasyCodef;
import io.codef.api.EasyCodefServiceType;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class EasyCodeTokenTest {

//    @Test
    public void usageExapmple() throws IOException {
        EasyCodef codef = new EasyCodef();

        codef.setClientInfoForDemo(EasyCodeClientInfoTest.DEMO_CLIENT_ID, EasyCodeClientInfoTest.DEMO_CLIENT_SECRET);

        codef.setPublicKey(EasyCodeClientInfoTest.PUBLIC_KEY);

        // 토큰 요청
        String accessToken1 = codef.requestToken(EasyCodefServiceType.DEMO);
        System.out.println("accessToken1 = " + accessToken1);

        String accessToken2 = codef.requestToken(EasyCodefServiceType.DEMO);
        System.out.println("accessToken2 = " + accessToken2);

        assertEquals(accessToken1, accessToken2, "성능 향상을 위한 재사용 토큰 이용 확인");

//        // 신규 토큰 요청
//        String accessToken3 = codef.requestToken(EasyCodefServiceType.SANDBOX);
//        System.out.println("accessToken3 = " + accessToken3);
//
//        String accessToken4 = codef.requestToken(EasyCodefServiceType.SANDBOX);
//        System.out.println("accessToken4 = " + accessToken4);
//
//        assertNotEquals(accessToken3, accessToken4);
    }
}
