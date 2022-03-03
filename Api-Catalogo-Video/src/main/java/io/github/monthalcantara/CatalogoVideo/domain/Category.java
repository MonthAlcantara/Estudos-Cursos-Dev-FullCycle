package io.github.monthalcantara.CatalogoVideo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "name")
public final class Category {

    private UUID id;
    private final String name;
    private final String description;
    private Boolean isActive = true;

    /* Programação defensiva...Uma vez que a regra de negócio exige 'name' para a categoria...
     * Valido no construtor se esse campo está sendo passado
     */
    public Category(String name, String description) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Can not be null or empty");
        }
        this.name = name;
        this.description = description;
    }

    public Category(String name, String description, boolean isActive) {
        this(name, description);
        this.isActive = isActive;

    }

    public void active() {
        this.isActive = true;
    }
    public void desactivate() {
        this.isActive = false;
    }
}
