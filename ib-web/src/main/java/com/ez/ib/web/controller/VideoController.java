package com.ez.ib.web.controller;

import com.ez.common.mvc.ModelAndViewFactory;
import com.ez.ib.web.bean.Video;
import com.ez.ib.web.service.VideoService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * ClassName: VideoController <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-5-20 下午4:02 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@RestController
@RequestMapping(value = "/video")
@Slf4j
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/playvideo")
    public ModelAndView playVideo(@RequestParam(name = "s", defaultValue = "") String subjectName,
                                  @RequestParam(name = "k", defaultValue = "") String knowledge,
                                  HttpServletRequest req,
                                  HttpServletResponse res) throws Exception {
        log.debug("播放视屏");
        if (StringUtils.isBlank(subjectName) || StringUtils.isBlank(knowledge)) {
            throw new RuntimeException("必须要有科目名称知识点");
        }

        Video video = videoService.queryWithSubjectAndKnowledge(subjectName, knowledge);
        return ModelAndViewFactory.instance("video/playvideo").with("video", video).build();
    }

    @RequestMapping(value = "/query/{subject}")
    public ModelAndView query(@PathVariable String subject,
                              @RequestBody Set<String> knowledges,
                              HttpServletRequest req,
                              HttpServletResponse res) throws Exception {
        log.debug("查询知识点视屏地址");

        if ("理数".equalsIgnoreCase(subject) || "文数".equals(subject)) {
            subject = "数学";
        }


        List<Video> videos = Lists.newArrayList();
        for (String k : knowledges) {
            Video video = videoService.queryWithSubjectAndKnowledge(subject, k);
            if (video != null) {
                videos.add(video);
            }
        }
        return ModelAndViewFactory.instance().with("videos", videos).build();
    }

}
