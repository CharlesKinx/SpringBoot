package com.example.demo.service;

import com.example.demo.dto.PageDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PageDTO list(Integer pages, Integer size) {

        Integer totalPage;
        PageDTO pageDTO = new PageDTO();
        Integer totalCount = questionMapper.count();
        if(totalCount%size==0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size+1;
        }


        if (pages < 1) {
            pages = 1;
        }
        if (pages > totalPage) {
            pages = totalPage;
        }

        pageDTO.setPagenation(totalPage, pages);
        Integer offset = size * (pages - 1);

        List<Question> questionList = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setQuestion(questionDTOS);


        return pageDTO;
    }

    public PageDTO list(Integer userId, Integer pages, Integer size) {

        Integer totalPage;
        PageDTO pageDTO = new PageDTO();
        Integer totalCount = questionMapper.countByUserId(userId);
        if(totalCount%size == 0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size+1;
        }

        if (pages < 1) {
            pages = 1;
        }
        if (pages > totalPage) {
            pages = totalPage;
        }

        pageDTO.setPagenation(totalPage, pages);
        Integer offset = size * (pages - 1);

        List<Question> questionList = questionMapper.listByUserId(userId,offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        pageDTO.setQuestion(questionDTOS);

        return pageDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            questionMapper.create(question);
        }else{
            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }
    }
}
