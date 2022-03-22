package br.com.molina.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import br.com.molina.model.User;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {
     User findByUsername(String username);

}
