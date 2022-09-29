package com.example.MyApp.Repository;

import com.example.MyApp.Entity.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Integer> {
    Comic findById (int id); //Abstract explicitly declared to make the return parameter Comic instead of
                             //Optional<Comic>
}
