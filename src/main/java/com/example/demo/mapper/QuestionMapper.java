package com.example.demo.mapper;

import com.example.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper

public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag,avatar_url)  values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag},#{avatar_url})")
    public void create(Question question);

    @Select("select * from question")
    List<Question> list();
}
