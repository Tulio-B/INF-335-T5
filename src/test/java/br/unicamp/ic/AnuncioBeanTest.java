package br.unicamp.ic;

import br.unicamp.ic.beans.AnuncioBean;
import br.unicamp.ic.beans.ProdutoBean;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnuncioBeanTest {

    @Test
    void anuncioBeanTest() throws MalformedURLException {
        assertEquals(1L, AnuncioBean.getSerialversionuid());

        var produto = new ProdutoBean("ETC 001", "Produto Teste",
                "Descrição teste", 50D, "SP");
        var urls = new ArrayList<>(
                List.of(new URL("http://google.com/imagem1.png"),
                        new URL("http://google.com/imagem2.png"))
        );
        var anuncio = new AnuncioBean();
        anuncio.setProduto(produto);
        anuncio.setFotosUrl(urls);
        anuncio.setDesconto(0.1D);

        assertEquals(produto, anuncio.getProduto());
        assertEquals(urls, anuncio.getFotosUrl());
        assertEquals(0.1D, anuncio.getDesconto());
        assertEquals(45D, anuncio.getValor());

        var anuncio2 = new AnuncioBean(produto, urls, 0D);
        assertEquals(50D, anuncio2.getValor());
    }
}
