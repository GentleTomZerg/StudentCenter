package com.man.studentcenter;

import com.man.studentcenter.model.entity.Selection;
import com.man.studentcenter.model.entity.Student;
import com.man.studentcenter.model.mapper.SelectionMapper;
import com.man.studentcenter.model.mapper.StudentMapper;
import com.man.studentcenter.model.service.optin.SelectionService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseTest {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SelectionMapper selectionMapper;

    @Test
    @Order(1)
    void studentInsert() {
        Student student = new Student();
        student.setToken(123);
        student.setStatus(0);
        studentMapper.insert(student);
    }

    @Test
    @Order(2)
    void studentUpdate() {
        Student student = new Student();
        student.setToken(123);
        student.setStatus(1);
        int num = studentMapper.update(student);
        Assert.isTrue(num == 1, "Update student Fail");
    }

    @Test
    @Order(3)
    void studentSelectAll() {
        List<Student> students = studentMapper.selectAll();
        students.forEach(System.out::println);
    }

    @Test
    @Order(4)
    void studentSelectByToken() {
        Student student = studentMapper.selectByToken(123);
        Student expectedStudent = new Student();
        expectedStudent.setToken(123);
        expectedStudent.setStatus(1);
        Assert.isTrue(student.equals(expectedStudent), "SelectByToken Fail");
    }

    @Test
    @Order(5)
    void studentDelete() {
        int num = studentMapper.delete(123);
        Assert.isTrue(num == 1, "Delete student Fail");
    }

    @Test
    @Order(6)
    void selectionInsert() {
        Selection selection = new Selection();
        selection.setToken(123);
        selection.setCourseid("100");
        selectionMapper.insert(selection);
    }

//    @Test
//    @Order(7)
//    void selectionUpdate() {
//        Selection selection = new Selection();
//        selection.setId(1);
//        selection.setToken(123);
//        selection.setCourseid(100);
//        int num = selectionMapper.update(selection);
//        Assert.isTrue(num == 1, "Update selection Fail");
//    }

    @Test
    @Order(8)
    void selectionSelectAll() {
        List<Selection> selections = selectionMapper.selectAll();
        selections.forEach(System.out::println);
    }

    @Test
    @Order(9)
    void selectionSelectById() {
        Selection selection = selectionMapper.selectById(6);
        Selection expectedSelection = new Selection();
        expectedSelection.setId(1);
        expectedSelection.setToken(123);
        expectedSelection.setCourseid("100");
        Assert.isTrue(selection.equals(expectedSelection), "SelectById Fail");
    }

    @Test
    @Order(10)
    void selectionDelete() {
        int num = selectionMapper.delete(6);
        Assert.isTrue(num == 1, "Delete selection Fail");
    }

    @Autowired
    private SelectionService selectionService;

    @Test
    @Order(11)
    void testSelectServiceAdd() {
        Selection selection = new Selection();
        selection.setToken(123);
        selection.setCourseid("2");
        selectionService.add(selection);
    }

    @Test
    @Order(12)
    void testSelectServiceSelectAll() {
        int token = 123;
        List<Selection> selections = selectionService.selectAllByToken(token);
        Assert.isTrue(selections.size() == 1, "Student:" + token + "takes 1 course.");
    }

    @Test
    @Order(13)
    void testSelectServiceifSelected() {
        boolean ifSelect = selectionService.ifSelected(123, "2");
        Assert.isTrue(ifSelect == true, "Student:123 took the courseid:2.");
    }

    @Test
    @Order(14)
    void testSelectServiceifNotSelected() {
        boolean ifSelect = selectionService.ifSelected(123, "20");
        Assert.isTrue(ifSelect == false, "Student:123 didn't take the courseid:20.");
    }

    @Test
    @Order(15)
    void testSelectServiceDelete() {
        int result = selectionService.delete(123, "2");
        Assert.isTrue(result == 1, "Delete works.");
    }

    @Test
    @Order(15)
    void testSelectServiceDeleteFail() {
        int result = selectionService.delete(123, "2");
        Assert.isTrue(result == 0, "Delete non-exist course fails.");
    }
}
