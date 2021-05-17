package com.example.myBookShopApp.config;

import com.example.myBookShopApp.data.BookRepository;
import com.example.myBookShopApp.data.TestEntity;
import com.example.myBookShopApp.data.TestEntityCrudRepository;
import com.example.myBookShopApp.data.TestEntityDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.logging.Logger;

@Configuration
public class CommandLineRunnerImpl implements CommandLineRunner {

    TestEntityCrudRepository testEntityCrudRepository;
    BookRepository bookRepository;

    @Autowired
    public CommandLineRunnerImpl(TestEntityCrudRepository testEntityCrudRepository, BookRepository bookRepository) {
        this.testEntityCrudRepository = testEntityCrudRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            createTestEntity(new TestEntity());
        }

        TestEntity readTestEntity = readTestEntityById(3L);
        if (readTestEntity != null){
            Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info("read " +readTestEntity.toString());
        }else {
            throw new NullPointerException();
        }

        TestEntity updatedTestEntity = updateTestEntityById(5L);
        if (updatedTestEntity != null){
            Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info("update "+updatedTestEntity.toString());
        }else {
            throw new NullPointerException();
        }

        deleteTesEntityById(4L);

        Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info(bookRepository.findBooksByAuthor_FirstName("Jelene").toString());
        Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName()).info(bookRepository.customFindAllBooks().toString());
    }

    private void deleteTesEntityById(Long id) {
        TestEntity testEntity = testEntityCrudRepository.findById(id).get();
        testEntityCrudRepository.delete(testEntity);
    }

    private TestEntity updateTestEntityById(Long id) {
       TestEntity testEntity = testEntityCrudRepository.findById(id).get();
       testEntity.setData("NEW DATA");
       testEntityCrudRepository.save(testEntity);
       return testEntity;
    }

    private TestEntity readTestEntityById(Long id) {
        return testEntityCrudRepository.findById(id).get();
    }

    private void createTestEntity(TestEntity entity) {
        entity.setData(entity.getClass().getSimpleName()+entity.hashCode());
        testEntityCrudRepository.save(entity);
    }
}
