package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    //CRUD
    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ) {

    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request){
        Customer customer = customerRepository.getReferenceById(id);
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }








    //EXAMPLES
//    //@GetResponse uses the method GET, and then uses a custom url to map what is inside.
//    @GetMapping("/")
//    public String index() {
//        return "Index Page";
//    }
//
//    //Method GreetResponse converts the data sent into JSON format and send it as response
//    @GetMapping("/greet")
//    public GreetResponse greet() {
//        return new GreetResponse("Hello World");
//    }
//    record GreetResponse (String greet) {}
//
//
//    //Another way of getting a JSON response
//    @GetMapping("/greet-2")
//    public SecondGreetResponse secondGreet() {
//        return new SecondGreetResponse("Hello");
//    }
//
//    class SecondGreetResponse{
//        private final String greet;
//
//        public SecondGreetResponse(String greet) {
//            this.greet = greet;
//        }
//
//        public String getGreet() {
//            return greet;
//        }
//
//        @Override
//        public String toString() {
//            return "SecondGreetResponse{" +
//                    "greet='" + greet + '\'' +
//                    '}';
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            SecondGreetResponse that = (SecondGreetResponse) o;
//            return Objects.equals(greet, that.greet);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(greet);
//        }
//    }
//
//    @GetMapping("/greet-3")
//    public ThirdGreetResponse thirdGreet() {
//        ThirdGreetResponse response = new ThirdGreetResponse("Hello World",
//                List.of("Java", "Kotlin", "JavaScript"),
//                new Person("Abdelsalam", 22, 1_000)
//        );
//        return response;
//    }
//
//    record Person (String name,
//                   int age,
//                   double savings){}
//
//    record ThirdGreetResponse (String greet,
//                               List<String> favProgrammingLanguages,
//                               Person person) {}
}
