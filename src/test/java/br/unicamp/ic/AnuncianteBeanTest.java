package br.unicamp.ic;

import br.unicamp.ic.beans.AnuncianteBean;
import br.unicamp.ic.beans.AnuncioBean;
import br.unicamp.ic.beans.ProdutoBean;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class AnuncianteBeanTest {

    @Test
    void anuncianteBeanTest() throws MalformedURLException {

        var produto = new ProdutoBean("ETC 001", "Produto Teste",
                "Descrição teste", 50D, "SP");
        var produto2 = new ProdutoBean("XPTO 001", "Produto Teste 2022",
                "Descrição diferente", 100D, "MG");

        var urls = new ArrayList<>(
                List.of(new URL("http://google.com/imagem1.png"),
                        new URL("http://google.com/imagem2.png"))
        );

        var anuncio = new AnuncioBean();
        anuncio.setProduto(produto);
        anuncio.setFotosUrl(urls);
        anuncio.setDesconto(0.1D);

        var anuncio2 = new AnuncioBean();
        anuncio2.setProduto(produto2);
        anuncio2.setFotosUrl(urls);
        anuncio2.setDesconto(0.5D);

        var anunciante = new AnuncianteBean();
        anunciante.setNome("Fulano de Tal");
        anunciante.setCPF("123.456.789-01");
        anunciante.setAnuncios(new ArrayList<>(List.of(anuncio, anuncio2)));

        assertEquals("Fulano de Tal", anunciante.getNome());
        assertEquals("123.456.789-01", anunciante.getCPF());
        assertIterableEquals(List.of(anuncio, anuncio2), anunciante.getAnuncios());

        var anuncio3 = new AnuncioBean();
        anuncio3.setProduto(produto2);
        anuncio3.setFotosUrl(urls);
        anuncio3.setDesconto(0D);

        //Com 2 produtos - 50 - 10% = 45, 100 - 50% = 50
        assertEquals((45 + 50) / 2D, anunciante.valorMedioAnuncios());

        anunciante.addAnuncio(anuncio3);
        assertIterableEquals(List.of(anuncio, anuncio2, anuncio3), anunciante.getAnuncios());

        //Com 3 produtos - 50 - 10% = 45, 100 - 50%, 100 - 0% = 150
        assertEquals((45 + 50 + 100) / 3D, anunciante.valorMedioAnuncios());

        anunciante.removeAnuncio(0);
        assertIterableEquals(List.of(anuncio2, anuncio3), anunciante.getAnuncios());

        //Com 2 produtos - 100 - 50%, 100 - 0% = 150
        assertEquals((50 + 100) / 2D, anunciante.valorMedioAnuncios());

        var anunciante2 = new AnuncianteBean("Sicrano", "987.654.321-09", new ArrayList<>());
        assertEquals(0, anunciante2.getAnuncios().size());
    }
}
