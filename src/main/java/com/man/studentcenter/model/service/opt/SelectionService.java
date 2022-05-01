package com.man.studentcenter.model.service.opt;

import com.man.studentcenter.model.entity.Selection;
import com.man.studentcenter.model.mapper.SelectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SelectionService
 * @Data 2022/5/1 5:15
 * @Author ruary
 * @Version 1.0
 * @Describe Select Optional courses
 **/
@Service
public class SelectionService {


    private SelectionMapper mapper;

    public int add(Selection selection) {
        return mapper.insert(selection);
    }

    public int delete(int token, String courseid) {
        return mapper.deleteByTokenCourseId(token, courseid);
    }

    public boolean ifSelected(int token, String courseid) {
        return mapper.selectByTokenCourseId(token, courseid) != null;
    }

    public List<Selection> selectAllByToken(int token) {
        return mapper.selectAllByToken(token);
    }


    @Autowired
    public void setMapper(SelectionMapper mapper) { this.mapper = mapper; }
}
