package kr.rodumani.minecraft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


class ComposingRule {
    public char glyph1;
    public char glyph2;
    public char composed;

    public ComposingRule(char g1, char g2, char com) {
        glyph1 = g1;
        glyph2 = g2;
        composed = com;
    }
}


class CharInput {
    public int type;
    public char glyph;

    public CharInput(int _type, char _glyph) {
        type = _type;
        glyph = _glyph;
    }
}

public class EnglishToKorean3 implements EnglishToKorean {
    private final String firstGlyph = "ㄱㄲㄴㄷㄸㄹㅁㅂㅃㅅㅆㅇㅈㅉㅊㅋㅌㅍㅎ";
    private final String secondGlyph = "ㅏㅐㅑㅒㅓㅔㅕㅖㅗㅘㅙㅚㅛㅜㅝㅞㅟㅠㅡㅢㅣ";
    private final String lastGlyph = "　ㄱㄲㄳㄴㄵㄶㄷㄹㄺㄻㄼㄽㄾㄿㅀㅁㅂㅄㅅㅆㅇㅈㅊㅋㅌㅍㅎ";

    private final int koreanCodeStart = 0xAC00;
    
    private final int firstW = secondGlyph.length() * lastGlyph.length();
    private final int secondW = lastGlyph.length();

    private final Map<Character, Character> enkoMap = new HashMap<>();

    private final String firstGlyphKeys = "0yuiophjkl;'nm";

    private List<List<ComposingRule>> composingRule = new ArrayList<>();
    
    
    public EnglishToKorean3() {
    	// Generate enkoMap 
        enkoMap.put('`', '*');
        enkoMap.put('1', 'ㅎ');
        enkoMap.put('2', 'ㅆ');
        enkoMap.put('3', 'ㅂ');
        enkoMap.put('4', 'ㅛ');
        enkoMap.put('5', 'ㅠ');
        enkoMap.put('6', 'ㅑ');
        enkoMap.put('7', 'ㅖ');
        enkoMap.put('8', 'ㅢ');
        enkoMap.put('9', 'ㅜ');
        enkoMap.put('0', 'ㅋ');
        enkoMap.put('-', ')');
        enkoMap.put('=', '>');
        enkoMap.put('q', 'ㅅ');
        enkoMap.put('w', 'ㄹ');
        enkoMap.put('e', 'ㅕ');
        enkoMap.put('r', 'ㅐ');
        enkoMap.put('t', 'ㅓ');
        enkoMap.put('y', 'ㄹ');
        enkoMap.put('u', 'ㄷ');
        enkoMap.put('i', 'ㅁ');
        enkoMap.put('o', 'ㅊ');
        enkoMap.put('p', 'ㅍ');
        enkoMap.put('[', '(');
        enkoMap.put(']', '<');
        enkoMap.put('\\', ':');
        enkoMap.put('a', 'ㅇ');
        enkoMap.put('s', 'ㄴ');
        enkoMap.put('d', 'ㅣ');
        enkoMap.put('f', 'ㅏ');
        enkoMap.put('g', 'ㅡ');
        enkoMap.put('h', 'ㄴ');
        enkoMap.put('j', 'ㅇ');
        enkoMap.put('k', 'ㄱ');
        enkoMap.put('l', 'ㅈ');
        enkoMap.put(';', 'ㅂ');
        enkoMap.put('\'', 'ㅌ');
        enkoMap.put('z', 'ㅁ');
        enkoMap.put('x', 'ㄱ');
        enkoMap.put('c', 'ㅔ');
        enkoMap.put('v', 'ㅗ');
        enkoMap.put('b', 'ㅜ');
        enkoMap.put('n', 'ㅅ');
        enkoMap.put('m', 'ㅎ');
        enkoMap.put(',', ',');
        enkoMap.put('.', '.');
        enkoMap.put('/', 'ㅗ');

        enkoMap.put('~', '※');
        enkoMap.put('!', 'ㄲ');
        enkoMap.put('@', 'ㄺ');
        enkoMap.put('#', 'ㅈ');
        enkoMap.put('$', 'ㄿ');
        enkoMap.put('%', 'ㄾ');
        enkoMap.put('^', '=');
        enkoMap.put('&', '“');
        enkoMap.put('*', '”');
        enkoMap.put('(', '\'');
        enkoMap.put(')', '~');
        enkoMap.put('_', ';');
        enkoMap.put('+', '+');
        enkoMap.put('Q', 'ㅍ');
        enkoMap.put('W', 'ㅌ');
        enkoMap.put('E', 'ㄵ');
        enkoMap.put('R', 'ㅀ');
        enkoMap.put('T', 'ㄽ');
        enkoMap.put('Y', '5');
        enkoMap.put('U', '6');
        enkoMap.put('I', '7');
        enkoMap.put('O', '8');
        enkoMap.put('P', '9');
        enkoMap.put('{', '%');
        enkoMap.put('}', '/');
        enkoMap.put('|', '\\');
        enkoMap.put('A', 'ㄷ');
        enkoMap.put('S', 'ㄶ');
        enkoMap.put('D', 'ㄼ');
        enkoMap.put('F', 'ㄻ');
        enkoMap.put('G', 'ㅒ');
        enkoMap.put('H', '0');
        enkoMap.put('J', '1');
        enkoMap.put('K', '2');
        enkoMap.put('L', '3');
        enkoMap.put(':', '4');
        enkoMap.put('\"', '·');
        enkoMap.put('Z', 'ㅊ');
        enkoMap.put('X', 'ㅄ');
        enkoMap.put('C', 'ㅋ');
        enkoMap.put('V', 'ㄳ');
        enkoMap.put('B', '?');
        enkoMap.put('N', '-');
        enkoMap.put('M', '\"');
        enkoMap.put('<', ',');
        enkoMap.put('>', '.');
        enkoMap.put('?', '!');
        
        // Construct composingRule
        composingRule.add(Arrays.<ComposingRule>asList(
            new ComposingRule('ㄱ', 'ㄱ', 'ㄲ'),
            new ComposingRule('ㄷ', 'ㄷ', 'ㄸ'),
            new ComposingRule('ㅂ', 'ㅂ', 'ㅃ'),
            new ComposingRule('ㅅ', 'ㅅ', 'ㅆ'),
            new ComposingRule('ㅈ', 'ㅈ', 'ㅉ')
        ));
        composingRule.add(Arrays.<ComposingRule>asList(
            new ComposingRule('ㅗ', 'ㅏ', 'ㅘ'),
            new ComposingRule('ㅗ', 'ㅐ', 'ㅙ'),
            new ComposingRule('ㅗ', 'ㅣ', 'ㅚ'),
            new ComposingRule('ㅜ', 'ㅓ', 'ㅝ'),
            new ComposingRule('ㅜ', 'ㅔ', 'ㅞ'),
            new ComposingRule('ㅜ', 'ㅣ', 'ㅟ'),
            new ComposingRule('ㅡ', 'ㅣ', 'ㅢ')
        ));
        composingRule.add(Arrays.<ComposingRule>asList(
        ));
    };
    

    private CharInput char2input(char enChar) {
    	
        char glyph = enChar;
        if (this.enkoMap.containsKey(enChar)) {
        	glyph = this.enkoMap.get(enChar);
        }
        int type = 0;
        
        if (firstGlyphKeys.indexOf(enChar) >= 0 && firstGlyph.indexOf(glyph) >= 0) {
            type = 1;
        } else if (secondGlyph.indexOf(glyph) >= 0) {
            type = 2;
        } else if (lastGlyph.indexOf(glyph) >= 0) {
            type = 3;
        } else {
            type = 0;
        }
        
        return new CharInput(type, glyph);
    }

    /**
     * 영어를 한글로...
     */
    public String engToKor(String eng) {
    	eng = eng.concat(" ");
        StringBuffer out = new StringBuffer();
        
        int state = 0;
        char[] glyph = new char[4];
        glyph[0] = glyph[1] = glyph[2] = glyph[3] = 0;
        
        for (int i = 0; i < eng.length(); i++) {
            char ch = eng.charAt(i);
            CharInput input = char2input(ch);
            int type = input.type;
            char inputGlyph = input.glyph;

            boolean A, B, C;
            A = (type == 1);	// 입력된 것이 초성
            B = (type == 2);	// 입력된 것이 중성
            C = (type == 3);	// 입력된 것이 종성

            boolean composed = false;
            while (!composed)
            {
                switch (state) {
                case 0:     // 초기상태
                    state = A ? 1 : (B || C? 2 : 0);
                    break;
                case 1:     // 초성만 입력된 상태
                    state = A ? 1 : (B || C? 3 : 0);
                    if (state == 0) {
                        out.append(glyph[1]);
                    }
                    break;
                case 2:     // 중성 또 종성만 입력된 상태
                    state = A ? 3 : (B || C? 2 : 0);
                    if (state == 0) {
                        out.append(glyph[2] != 0 ? glyph[2] : glyph[3]);
                    }
                    break;
                case 3:     // 초성+중성 또는 초성+종성 또는 초성+중성+종성이 입력된 상태
                    state = B || C? 3 : 0;
                    if (state == 0) {
                        int[] code = new int[3];
                        code[0] = firstGlyph.indexOf(glyph[1]);
                        code[1] = glyph[2] == 0 ? -1 : secondGlyph.indexOf(glyph[2]);
                        code[2] = glyph[3] == 0 ? 0 : lastGlyph.indexOf(glyph[3]);
                        if (code[1] < 0) {
                            out.append(glyph[1]);
                        } else {
                            out.append((char)(koreanCodeStart+code[0]*firstW+code[1]*secondW+code[2]));
                        }
                    }
                    break;
                }

                if (type == 0) {
                    composed = true;
                    out.append(inputGlyph);
                }
                if (state == 0) {
                    glyph[0] = glyph[1] = glyph[2] = glyph[3] = 0;
                } else if (A || B || C) {
                    if (glyph[type] == 0) {
                        composed = true;
                        glyph[type] = inputGlyph;
                    } else {
                        for (ComposingRule rule : composingRule.get(type-1)) {
                            if (rule.glyph1 == glyph[type] && rule.glyph2 == inputGlyph) {
                                composed = true;
                                glyph[type] = rule.composed;
                                break;
                            }
                        }
                        if (!composed) {
                            A = B = C = false;
                            continue;
                        }
                    }
                }
                A = (type == 1);
                B = (type == 2);
                C = (type == 3);
            }
            
        }

        return out.toString().trim();
    }

}

