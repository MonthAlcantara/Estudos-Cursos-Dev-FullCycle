package io.github.monthalcantara.EstudoSpringHateoas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.monthalcantara.EstudoSpringHateoas.model.Customer;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Objects;



@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
/*Com essa extensão do RepresentationModel da lib do hateoas o objeto passa a ter o método add
 * onde poderei adicionar o link para o mesmo
 */
public class CustomerResponse extends RepresentationModel<CustomerResponse> {

    private Long id;
    private String nome;
    private Integer age;

    public CustomerResponse(Customer customer) {
        this.id = customer.getId();
        this.nome = customer.getNome();
        this.age = customer.getAge();
    }

    private CustomerResponse(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerResponse customer = (CustomerResponse) o;
        return Objects.equals(id, customer.id) && Objects.equals(nome, customer.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
