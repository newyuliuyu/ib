package com.ez.ib.web.dao;

import com.ez.ib.web.IbWebApplication;
import com.ez.ib.web.bean.Subject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * ClassName: SubjectDaoTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-22 下午3:44 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IbWebApplication.class)
@Slf4j
public class SubjectDaoTest {

    @Autowired
    private SubjectDao subjectDao;

    @Test
    public void queryAllSubjects() throws Exception{
        List<Subject> subjects = subjectDao.queryAllSubjects();

        log.debug(subjects.size()+"");

    }
}