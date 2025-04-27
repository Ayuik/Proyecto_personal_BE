package dev.ayelen.categories;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @InjectMocks
    CategoryService service;

    @Mock
    CategoryRepository repository;

    @BeforeEach
    void setUp() {
        this.service = new CategoryService(repository);
    };

    @Test
    void testStore() {
        Category newCategory = new Category();
        newCategory.setCategoryName("new category");

        when(repository.save(newCategory)).thenAnswer(invocation -> {
            Category c = invocation.getArgument(0);
            ReflectionTestUtils.setField(c, "categoryId", 1L);
            return c;
        });

        Category storedCategory = service.store(newCategory);

        assertThat(storedCategory.getCategoryName(), is("new category"));
        assertThat(storedCategory.getCategoryId(), is(1L));
    }

    @Test
    void testUpdate() {
        Category persistentCategory = new Category("persistent category");
        ReflectionTestUtils.setField(persistentCategory, "categoryId", 1L);

        Category categoryNewData = new Category("new title");

        when(repository.save(Mockito.any(Category.class))).thenAnswer(invocation -> {
            Category c = invocation.getArgument(0);
            ReflectionTestUtils.setField(c, "categoryId", 1L);
            return c;
        });

        when(repository.findById(1L)).thenReturn(Optional.of(persistentCategory));

        service.store(persistentCategory);
        Long categoryId = persistentCategory.getCategoryId();
        Category updatedCategory = service.update(categoryId, categoryNewData);

        assertThat(updatedCategory.getCategoryName(), is("new title"));

    }

    @Test
    void getById() {
        Category category = new Category();
        ReflectionTestUtils.setField(category, "categoryId", 2L);
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        when(repository.findById(2L)).thenReturn(Optional.of(category));
        Category returned = service.getById(2L);
        assertThat(returned, is(category));
    }

    @Test
    void delete() {
        Category category = new Category();
        ReflectionTestUtils.setField(category, "categoryId", 2L);

        when(repository.findById(2L)).thenReturn(Optional.of(category));

        service.delete(2L);

        when(repository.findAll()).thenReturn(new ArrayList<>());

        List<Category> list = service.getAll();
        assertThat(list.size(), is(0));

    }

    @Test
    void testGetAll() {
        List<Category> categories = new ArrayList<>();
        Category newcategory = new Category();
        categories.add(newcategory);
        when(repository.findAll()).thenReturn(categories);
        List<Category> list = service.getAll();
        assertThat(list.size(), is(1));
    }

}
