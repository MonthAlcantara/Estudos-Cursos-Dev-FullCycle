package io.github.monthalcantara.CatalogoVideo.domain.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CategoryTest {

    @ParameterizedTest
    @CsvSource(value = {"''", "' '"})
    @DisplayName("Deve validar quando valor vazio ou em branco for passado para variável name")
    void validationNameBlankTest(String name) {
        IllegalArgumentException aThrows = Assertions.assertThrows(IllegalArgumentException.class, () -> new Category(name, null));
        Assertions.assertTrue(aThrows.getMessage().contains("Can not be null or empty"));
    }

    @Test
    @DisplayName("Deve validar quando valor null for passado para variável name")
    void validationNameNullTest() {
        IllegalArgumentException aThrows = Assertions.assertThrows(IllegalArgumentException.class, () -> new Category(null, null));
        Assertions.assertTrue(aThrows.getMessage().contains("Can not be null or empty"));
    }

    @Test
    @DisplayName("Deve mudar o valor de 'isActive' pra True")
    void activeTest() {
        Category category = new Category("Test", "Description", false);

        Assertions.assertEquals(Boolean.FALSE, category.getIsActive());

        category.active();
        Assertions.assertEquals(Boolean.TRUE, category.getIsActive());
    }

    @Test
    @DisplayName("Deve mudar o valor de 'isActive' pra False")
    void desactivateTest() {
        Category category = new Category("Test", "Description");

        Assertions.assertEquals(Boolean.TRUE, category.getIsActive());

        category.desactivate();
        Assertions.assertEquals(Boolean.FALSE, category.getIsActive());
    }

    @ParameterizedTest
    @CsvSource(value = {"false, true, true", "true, false, false"})
    @DisplayName("Deve atualizar os valores da categoria")
    void updateTest(Boolean valor, Boolean valorAlterado, Boolean valorEsperado) {
        final var category = new Category("Test", "Description", valor);
        final var nameModificado = "Test Modificado";
        final var descriptionModificada = "Description Modificada";

        category.update(nameModificado, descriptionModificada, valorAlterado);

        Assertions.assertEquals(category.getIsActive(), valorEsperado);
        Assertions.assertEquals(category.getDescription(), descriptionModificada);
        Assertions.assertEquals(category.getName(), nameModificado);
    }
}