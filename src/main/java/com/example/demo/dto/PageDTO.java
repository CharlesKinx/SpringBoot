package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class PageDTO {
    private List<QuestionDTO> question;
    private boolean hasPrevious;
    private boolean firstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();

    public List<QuestionDTO> getQuestion() {
        return question;
    }

    public void setQuestion(List<QuestionDTO> questions) {
        this.question = questions;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowEndPage() {
        return showEndPage;
    }

    public void setShowEndPage(boolean showEndPage) {
        this.showEndPage = showEndPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public void setPagenation(Integer totalCount, Integer page, Integer size) {

        this.page = page;
        if(totalCount%size==0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size+1;
        }

        if(page<1){
            page = 1;
        }
        if(page>totalPage){
            page = totalPage;
        }
        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if(page-i>0){
                pages.add(0,page-i);

            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }

        if(page ==1 ){
            firstPage =false;
        }else{
            firstPage =true;
        }

        if(totalPage == page){
            showEndPage = false;
        }else{
            showEndPage = true;
        }

        if(pages.contains(1)){
            firstPage = false;
        }else {
            firstPage = true;
        }

        if(pages.contains(totalPage)){
            showEndPage = false;
        }else{
            showEndPage = true;
        }
    }
}
