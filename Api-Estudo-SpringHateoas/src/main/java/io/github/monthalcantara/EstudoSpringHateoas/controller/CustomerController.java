package io.github.monthalcantara.EstudoSpringHateoas.controller;

import io.github.monthalcantara.EstudoSpringHateoas.dto.CustomerResponse;
import io.github.monthalcantara.EstudoSpringHateoas.model.Customer;
import io.github.monthalcantara.EstudoSpringHateoas.repository.CustomerRepository;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastraCustomer(@RequestBody Customer customer) {

        final var customerSalvo = customerRepository.save(customer);

        return new ResponseEntity<CustomerResponse>(geraLink(customerSalvo), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> buscaCustomerPeloId(@PathVariable("id") Long id) {

        final var link = WebMvcLinkBuilder
            //linkTo vai inspecionar a classe passada como parametro
                .linkTo(
            //methodOn vai inspecionar o metodo que informo da classe passada como parametro, nesse caso buscaTodosCustomer()
                        WebMvcLinkBuilder.methodOn(CustomerController.class).buscaTodosCustomer()

                ).withSelfRel();
        final var customer = new CustomerResponse(buscaCustomerPeloIdSeExistir(id));

        customer.add(link);

        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> buscaTodosCustomer() {

        final var customers = customerRepository.findAll()
                .stream()
                .map(this::geraLink)
                .collect(Collectors.toList());

        return ResponseEntity.ok(customers);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @DeleteMapping("/{id}")
    public void deletaCustomer(@PathVariable("id") Long id) {

        final var customer = buscaCustomerPeloIdSeExistir(id);

        customerRepository.delete(customer);
    }

    private Customer buscaCustomerPeloIdSeExistir(Long id) {

        final var customer = customerRepository.findById(id);

        if (customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return customer.get();
    }

    private CustomerResponse geraLink(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse(customer);
        //linkTo vai inspecionar a classe passada como parametro
        final var link = WebMvcLinkBuilder.linkTo(CustomerController.class).slash(customer.getId()).withSelfRel();
        customerResponse.add(link);
        return customerResponse;

    }


}
