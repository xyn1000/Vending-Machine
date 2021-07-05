package test.entity;

import entity.Category;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


/** 
* Category Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 4, 2020</pre> 
* @version 1.0 
*/ 
public class CategoryTest {



/** 
* 
* Method: getCategoryId() 
* 
*/ 
@Test
public void testGetCategoryId() throws Exception {
    Category category = new Category();
    category.setCategoryId(1);
    int id = category.getCategoryId();
    Assert.assertEquals(1, id);
} 

/** 
* 
* Method: setCategoryId(Integer categoryId) 
* 
*/ 
@Test
public void testSetCategoryId() throws Exception {
    Category category = new Category();
    category.setCategoryId(1);
    int id = category.getCategoryId();
    Assert.assertEquals(1, id);
} 

/** 
* 
* Method: getCategoryName() 
* 
*/ 
@Test
public void testGetCategoryName() throws Exception {
    Category category = new Category();
    category.setCategoryName("Ethan");
    String name = category.getCategoryName();
    Assert.assertEquals("Ethan", name);
} 

/** 
* 
* Method: setCategoryName(String categoryName) 
* 
*/ 
@Test
public void testSetCategoryName() throws Exception {
    Category category = new Category();
    category.setCategoryName("Ethan");
    String name = category.getCategoryName();
    Assert.assertEquals("Ethan", name);
    category.setCategoryName(null);

    Assert.assertNull(category.getCategoryName());
} 

/** 
* 
* Method: getParentId() 
* 
*/ 
@Test
public void testGetParentId() throws Exception {
    Category category = new Category();
    category.setParentId(1);
    int parentId = category.getParentId();
    Assert.assertEquals(1, parentId);
} 

/** 
* 
* Method: setParentId(Integer parentId) 
* 
*/ 
@Test
public void testSetParentId() throws Exception {
    Category category = new Category();
    category.setParentId(1);
    int parentId = category.getParentId();
    Assert.assertEquals(1, parentId);

} 

/** 
* 
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception {
    Category category = new Category();
    category.setParentId(1);
    category.setCategoryId(0);
    category.setCategoryName("coke");
    Assert.assertNotNull(category.toString());
} 




} 
