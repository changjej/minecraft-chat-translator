package kr.rodumani.minecraft;

import junit.framework.Assert;
import org.junit.Test;
import kr.rodumani.minecraft.EnglishToKorean;

public class UnitTest {
    @Test
    public void testEnglishToKorean2() {
        EnglishToKorean etk = new EnglishToKorean2();
        Assert.assertEquals("끄려면끄고 켤려면 켜고, 이미 켦", etk.engToKor("RMfuausRmrh zuffuaus zurh, dlal zuFa"));
        Assert.assertEquals("ㅓㅓㅓㅓ ㅓㅓㅓㅓ", etk.engToKor("jjjj JjJJ"));
        Assert.assertEquals("ㄱㄱ ㄱ고", etk.engToKor("rr rrh"));
        Assert.assertEquals("ㅓㄴㅈ", etk.engToKor("jsw"));
    }
    
    @Test
    public void testEnglishToKorean3() {
    	EnglishToKorean etk = new EnglishToKorean3();
    	Assert.assertEquals("안녕하세요", etk.engToKor("jfsheamfncj4"));
    	Assert.assertEquals("ㅋㅋㅋㅋㅋㅋㅋ", etk.engToKor("0000000"));
    	Assert.assertEquals("대도서관", etk.engToKor("uruvntk/fs"));
    	Assert.assertEquals("다람쥐헌쳇바퀴에돌아", etk.engToKor("ufyfzl9dmtsocq;f09djcuvwjf"));
    	Assert.assertEquals("모아치기테스트입니다", etk.engToKor("ivjfdokd'cgn'gj3dhduf"));
    	Assert.assertEquals("쒸쁘뜨끼까안빠쪄요또와쭈쎼요", etk.engToKor("nn9d;;guugkkdkkfjfs;;fllej4uuvj/fllbnn7j4"));
    	Assert.assertEquals("(뭐라는 건지 1도 모르겠다.)", etk.engToKor("[i9tyfhgs ktsld Juv ivygkc2uf.-"));
    }
}

