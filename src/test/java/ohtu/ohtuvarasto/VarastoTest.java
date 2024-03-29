package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void tilavuusEiNegatiivinen() {
    	assertEquals(0, new Varasto(-1).getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void toinenConstructor() {
    	assertEquals(0, new Varasto(-1, 1).getTilavuus(), vertailuTarkkuus);
    	assertEquals(1, new Varasto(1, 1).getTilavuus(), vertailuTarkkuus);
    	assertEquals(0, new Varasto(-1, 1).getSaldo(), vertailuTarkkuus); // Bug fixed
    	assertEquals(1, new Varasto(1, 1).getSaldo(), vertailuTarkkuus);
    	assertEquals(0, new Varasto(-1, -1).getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void virheetHylätään() {
    	double vanhaSaldo = varasto.getSaldo();
    	varasto.lisaaVarastoon(-1);
    	assertEquals(vanhaSaldo, varasto.getSaldo(), vertailuTarkkuus);
    	varasto.otaVarastosta(-1);
    	assertEquals(vanhaSaldo, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void loppuiKesken() {
    	varasto.lisaaVarastoon(10);
    	assertEquals(10, varasto.otaVarastosta(11), vertailuTarkkuus);
    	assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiMahtunut() {
    	varasto.lisaaVarastoon(11);
    	assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void merkkijono() {
    	assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

}