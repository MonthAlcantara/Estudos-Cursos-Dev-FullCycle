package io.github.monthalcantara.EstudoSpringHateoas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.hateoas.*;
        ;

import javax.persistence.*;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Entity
@Table(name = "customers")
/*Com essa extensão do RepresentationModel da lib do hateoas o objeto passa a ter o método add
* onde poderei adicionar o link para o mesmo
*/
public class Customer extends RepresentationModel<Customer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer age;

    public Customer(String nome, Integer age) {
        this.nome = nome;
        this.age = age;
    }

    private Customer(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(nome, customer.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
