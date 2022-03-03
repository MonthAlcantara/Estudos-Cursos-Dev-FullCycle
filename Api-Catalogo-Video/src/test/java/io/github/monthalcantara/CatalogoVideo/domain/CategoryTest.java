package io.github.monthalcantara.CatalogoVideo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CategoryTest {

    @ParameterizedTest
    @CsvSource(value = {"''", "' '"})
    @DisplayName("Deve validar quando valor null for passado para variavel name")
    void testeValidationNameBlank(String name) {
        IllegalArgumentException aThrows = Assertions.assertThrows(IllegalArgumentException.class, () -> new Category(name, null));
        Assertions.assertTrue(aThrows.getMessage().contains("Can not be null or empty"));
    }

    @Test
    @DisplayName("Deve validar quando valor null for passado para variavel name")
    void testeValidationNameNull() {
        IllegalArgumentException aThrows = Assertions.assertThrows(IllegalArgumentException.class, () -> new Category(null, null));
        Assertions.assertTrue(aThrows.getMessage().contains("Can not be null or empty"));
    }

    @Test
    void active() {
        Category category = new Category("Test", "Description",false);

        Assertions.assertEquals(Boolean.FALSE, category.getIsActive());

        category.active();
        Assertions.assertEquals(Boolean.TRUE, category.getIsActive());
    }

    @Test
    void desactivate() {
        Category category = new Category("Test", "Description");

        Assertions.assertEquals(Boolean.TRUE, category.getIsActive());

        category.desactivate();
        Assertions.assertEquals(Boolean.FALSE, category.getIsActive());
    }
}