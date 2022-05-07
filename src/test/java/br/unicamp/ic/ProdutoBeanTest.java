package br.unicamp.ic;

import br.unicamp.ic.beans.ProdutoBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoBeanTest {

    @Test
    void produtoBeanTest() {
        assertEquals(1L, ProdutoBean.getSerialversionuid());

        var produtoValorMenor = new ProdutoBean();
        produtoValorMenor.setCodigo("XPTO 0001");
        produtoValorMenor.setNome("Produto Teste");
        produtoValorMenor.setDescricao("Descrição do produto");
        produtoValorMenor.setValor(100D);
        produtoValorMenor.setEstado("SP");

        assertEquals("XPTO 0001", produtoValorMenor.getCodigo());
        assertEquals("Produto Teste", produtoValorMenor.getNome());
        assertEquals("Descrição do produto", produtoValorMenor.getDescricao());
        assertEquals(100D, produtoValorMenor.getValor());
        assertEquals("SP", produtoValorMenor.getEstado());

        var produtoValorMaior = new ProdutoBean("XPTO 0002", "Produto Caro",
                "Descrição do produto caro", 200D, "RJ");

        assertEquals(-1, produtoValorMenor.compareTo(produtoValorMaior));
        assertEquals(1, produtoValorMaior.compareTo(produtoValorMenor));

        produtoValorMaior.setValor(100D);
        assertEquals(0, produtoValorMenor.compareTo(produtoValorMaior));
    }

}
