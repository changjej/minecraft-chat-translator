package kr.rodumani.minecraft;

import junit.framework.Assert;
import org.junit.Test;
import kr.rodumani.minecraft.EnglishToKorean;

public class UnitTest {
    @Test
    public void testEnglishToKorean() {
        EnglishToKorean etk = new EnglishToKorean();
        Assert.assertEquals("끄려면끄고 켤려면 켜고, 이미 켦", etk.engToKor("RMfuausRmrh zuffuaus zurh, dlal zuFa"));
        Assert.assertEquals("ㅓㅓㅓㅓ ㅓㅓㅓㅓ", etk.engToKor("jjjj JjJJ"));
        Assert.assertEquals("ㄱㄱ ㄱ고", etk.engToKor("rr rrh"));
        Assert.assertEquals("ㅓㄴㅈ", etk.engToKor("jsw"));
    }
}

