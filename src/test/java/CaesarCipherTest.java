
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CaesarCipherTest {

    private CaesarCipher cifra;

    public CaesarCipherTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        cifra = new CaesarCipher();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of encryp method, of class CezarCipher.
     */
    @Test
    public void testDencrypWord() {
        assertEquals("abc", cifra.dencryp("def", 3));

    }

    @Test
    public void testDencrypPhrase() {
        assertEquals("a ligeira raposa marrom saltou sobre o cachorro cansado",
                cifra.dencryp("d oljhlud udsrvd pduurp vdowrx vreuh r fdfkruur fdqvdgr", 3));

    }

    @Test
    public void testDencrypPhareWithAccent() {
        assertEquals("meninas tambem jogam", cifra.dencryp("phqlqdv wdpéhp mrjdp",
                3));

    }

    @Test
    public void testDencrypPhraseAndNumberAndPoint() {
        assertEquals("cpf 012.345.678.00", cifra.dencryp("fsi 012.345.678.00", 3));

    }

    @Test
    public void testDencrypUpperCase() {
        assertEquals("meninas tambem jogam", cifra.dencryp("PHQLQDV WDPEHP MRJDP", 3));

    }

    @Test
    public void testDencrypBigPhrase() {
        assertEquals("prefiro ser essa metamorfose ambulante. eu prefiro ser "
                        + "essa metamorfose ambulante",
                cifra.dencryp("suhilur vhu hvvd phwdpruirvh dpexodqwh. hx "
                        + "suhilur vhu hvvd phwdpruirvh dpexodqwh", 3));
    }

    /**
     * Test of isCharEncryp method, of class CezarCipher.
     */
    @Test
    public void testIsCharDencrypPoint() {
        assertEquals(false, cifra.isCharDencryp('.'));
    }

    @Test
    public void testIsCharDencrypNumber() {
        assertEquals(false, cifra.isCharDencryp('5'));
    }

    @Test
    public void testIsNotCharDencryp() {
        assertEquals(true, cifra.isCharDencryp('a'));
    }
    /**
     * Test of removeAccents method, of class CezarCipher.
     */
    @Test
    public void testRemoveAccentsFromAWordWithAccents() {
        assertEquals("tambem", cifra.removeAccents("também"));
    }
    @Test
    public void testRemoveAccentsFromAWordWithoutAccents() {
        assertEquals("meninas", cifra.removeAccents("meninas"));
    }
    /**
     * Test of rotateAlphabet method, of class CezarCipher.
     */
    @Test
    public void testRotatingFromAToZ() {
        assertEquals('{', cifra.rotateAlphabet('a',3));
    }
    @Test
    public void testRotatingFromBToX() {
        assertEquals('|', cifra.rotateAlphabet('b',3));
    }
    @Test
    public void testRotatingFromDToA() {
        assertEquals('d', cifra.rotateAlphabet('d',3));
    }
}
